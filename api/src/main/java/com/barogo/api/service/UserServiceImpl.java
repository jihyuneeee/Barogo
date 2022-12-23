package com.barogo.api.service;

import java.util.List;
import java.util.regex.Pattern;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
// import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.barogo.api.config.JwtTokenProvider;
import com.barogo.api.domain.ResponseInfo;
import com.barogo.api.domain.TokeInfo;
import com.barogo.api.domain.UserInfo;
import com.barogo.api.domain.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    // private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;
    @Autowired
    private UserRepository userRepository;

    private static final String PASSWORD_REGEX = "^(?:(?=.*\\d)(?=.*[A-Z])(?=.*[a-z])|" +
            "(?=.*\\d)(?=.*[^A-Za-z0-9])(?=.*[a-z])|" +
            "(?=.*[^A-Za-z0-9])(?=.*[A-Z])(?=.*[a-z])|" +
            "(?=.*\\d)(?=.*[A-Z])(?=.*[^A-Za-z0-9]))(?!.*(.)\\1{2,})" +
            "[A-Za-z0-9!~<>,;:_=?*+#.\"&§%°()\\|\\[\\]\\-\\$\\^\\@\\/]" +
            "{8,32}$";

    private static final Pattern PASSWORD_PATTERN = Pattern.compile(PASSWORD_REGEX);

    @Override
    public ResponseInfo singup(UserInfo userInfo) {

        ResponseInfo responseInfo;

        if (userRepository.findById(userInfo.getId()).isPresent()) {
            // responseInfo.setResponse("fail");
            // responseInfo.setMessage("the id that already exists.");
            responseInfo = ResponseInfo.builder().response("fail").message("the id that already exists.").build();
            return responseInfo;
        }

        // 비밀번호 유효성 검사
        if (PASSWORD_PATTERN.matcher(userInfo.getPassword()).matches()) {

            userInfo.setPassword(passwordEncoder.encode(userInfo.getPassword()));
            userRepository.save(userInfo);
            responseInfo = ResponseInfo.builder().response("success").message("success").build();
            // responseInfo.setResponse("success");
            // jsonObject.put("response", "success");

        } else {
            // responseInfo.setResponse("fail");
            // responseInfo.setMessage("password that does not meet the conditions.");
            responseInfo = ResponseInfo.builder().response("fail")
                    .message("password that does not meet the conditions.").build();
        }

        return responseInfo;
    }

    @Override
    public TokeInfo login(UserInfo userDTO) {

        String id = userDTO.getId();
        String password = userDTO.getPassword();
        TokeInfo tokenInfo = null;

        // 존재하는 ID인지 확인
        Object data = userRepository.findById(id);

        if (data == null) {
            return tokenInfo;
        }

        // if (data.getPassword().equals(userDTO.getPassword())) {

        // JWT
        System.out.println("!!!!????????");
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(id,
                password);
        System.out.println("2222222 authenticationToken : " + authenticationToken);

        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        System.out.println("33333333 authentication : " + authenticationToken);

        tokenInfo = jwtTokenProvider.generateToken(authentication);

        System.out.println("tokenInfo : " + tokenInfo);

        return tokenInfo;
    }

}
