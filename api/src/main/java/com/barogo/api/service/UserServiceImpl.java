package com.barogo.api.service;

import java.util.List;
import java.util.Optional;
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
import com.barogo.api.domain.TokenInfo;
import com.barogo.api.domain.UserInfo;
import com.barogo.api.domain.UserRepository;
import com.barogo.api.utils.TokenUtil;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    TokenUtil tokenUtil;

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
            responseInfo = ResponseInfo.builder().status("fail").message("the id that already exists.").build();
            return responseInfo;
        }

        // 비밀번호 유효성 검사
        if (PASSWORD_PATTERN.matcher(userInfo.getPassword()).matches()) {

            userInfo.setPassword(passwordEncoder.encode(userInfo.getPassword()));
            userRepository.save(userInfo);
            responseInfo = ResponseInfo.builder().status("success").message("success").build();

        } else {

            responseInfo = ResponseInfo.builder().status("fail")
                    .message("password that does not meet the conditions.").build();
        }

        return responseInfo;
    }

    @Override
    public TokenInfo login(UserInfo userInfo) {

        String id = userInfo.getId();
        String password = userInfo.getPassword();
        String enc_password = passwordEncoder.encode(password);
        TokenInfo tokenInfo = null;

        // 존재하는 ID인지 확인
        Optional<UserInfo> data = userRepository.findById(id);

        if (!data.isPresent()) {

            return tokenInfo;
        }

        if (passwordEncoder.matches(password, enc_password)) {

            tokenInfo = tokenUtil.makeToken(id, password);
            // // JWT
            // UsernamePasswordAuthenticationToken authenticationToken = new
            // UsernamePasswordAuthenticationToken(id,
            // password);

            // Authentication authentication =
            // authenticationManagerBuilder.getObject().authenticate(authenticationToken);

            // tokenInfo = jwtTokenProvider.generateToken(authentication);

        }

        return tokenInfo;
    }

}
