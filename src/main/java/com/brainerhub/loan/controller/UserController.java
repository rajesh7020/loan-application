package com.brainerhub.loan.controller;

import com.brainerhub.loan.commonutil.Constant;
import com.brainerhub.loan.config.TokenProvider;
import com.brainerhub.loan.entity.User;
import com.brainerhub.loan.repository.UserRepository;
import com.brainerhub.loan.requestDto.UserReqDto;
import com.brainerhub.loan.responseDto.APIResponseBuilder;
import com.brainerhub.loan.responseDto.GenericResponse;
import com.brainerhub.loan.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenProvider jwtTokenUtil;

    @PostMapping("/register")
    public ResponseEntity<GenericResponse> registerUser(@Validated @RequestBody User user) {
        return ResponseEntity.ok(userService.createUser(user));
    }

    @PostMapping("/login")
    public GenericResponse loginUser(@Validated @RequestBody UserReqDto userReqDto) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        try {
            User user = userRepository.findUserByEmail(userReqDto.getEmail());
            if (user != null) {
                if (passwordEncoder.matches(userReqDto.getPassword(), user.getPassword()) && (userReqDto.getEmail().equalsIgnoreCase(user.getEmail()))) {
                    final Authentication authentication = authenticationManager.authenticate(
                            new UsernamePasswordAuthenticationToken(
                                    userReqDto.getEmail(),
                                    userReqDto.getPassword()
                            )
                    );
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                    final String token = jwtTokenUtil.generateToken(authentication);
                    log.info("You are log in successfully with token");
                    return APIResponseBuilder.build(true, token, Constant.LOGIN_SUCCESS);
                } else {
                    return APIResponseBuilder.build(Boolean.FALSE, null, Constant.INVALID_USER_PASS);
                }
            }
            return APIResponseBuilder.build(Boolean.FALSE, null, Constant.INVALID_USER_PASS);
        } catch (Exception e) {
            log.error(e.getMessage());
            return APIResponseBuilder.build(Boolean.FALSE, e.getMessage(), Constant.EXCEPTION);
        }
    }

}
