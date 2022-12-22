package com.barogo.api.service;

import java.util.List;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
// import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.barogo.api.config.JwtTokenProvider;
import com.barogo.api.model.TokeInfo;
import com.barogo.api.model.UserDTO;
import com.barogo.api.model.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    // private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;

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
    public String singup(UserDTO userDTO) {

        String id = userDTO.getId();
        String password = userDTO.getPassword();

        // 존재하는 ID인지 확인
        Object search_id = userRepository.findById(id);
        System.out.println("search_id :" + search_id);

        if (search_id != null) {
            return "";
        }

        // 비밀번호 유효성 검사
        if (PASSWORD_PATTERN.matcher(password).matches()) {
            System.out.print("The Password " + password + " is valid");

            UserDTO test = userRepository.save(userDTO);
            System.out.println("!!!!!!! test :" + test);
        } else {
            System.out.print("The Password " + password + " isn't valid");
        }

        return null;
    }

    @Override
    public TokeInfo login(UserDTO userDTO) {

        String id = userDTO.getId();
        String password = userDTO.getPassword();
        TokeInfo tokenInfo = null;
        System.out.println("id :" + id);
        System.out.println("password :" + password);

        // 존재하는 ID인지 확인
        Object data = userRepository.findById(id);
        System.out.println("data :" + data);

        if (data == null) {
            return tokenInfo;
        }

        // if (data.getPassword().equals(userDTO.getPassword())) {
        System.out.println("DDDDDDDDDDDDDDD");
        // JWT

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(id,
                password);
        System.out.println("authenticationToken : " + authenticationToken);

        // System.out.println("authenticationToken : " + authenticationToken);
        // System.out.println("11111111" +
        // authenticationManagerBuilder.getObject().authenticate(authenticationToken));
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        // Authentication authentication =
        // authenticationManager.authenticate(authenticationToken);
        System.out.println("authentication : " + authenticationToken);

        tokenInfo = jwtTokenProvider.generateToken(authentication);

        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        System.out.println("tokenInfo : " + tokenInfo);
        // }

        return tokenInfo;
    }

}
