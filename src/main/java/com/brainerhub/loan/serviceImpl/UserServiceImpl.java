package com.brainerhub.loan.serviceImpl;

import com.brainerhub.loan.commonutil.Constant;
import com.brainerhub.loan.config.TokenProvider;
import com.brainerhub.loan.entity.Role;
import com.brainerhub.loan.entity.User;
import com.brainerhub.loan.repository.RoleRepository;
import com.brainerhub.loan.repository.UserRepository;
import com.brainerhub.loan.requestDto.UserReqDto;
import com.brainerhub.loan.responseDto.APIResponseBuilder;
import com.brainerhub.loan.responseDto.GenericResponse;
import com.brainerhub.loan.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;


@Slf4j
@Service(value = "userService")
public class UserServiceImpl implements UserService, UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private BCryptPasswordEncoder bcryptEncoder;

    @Override
    public GenericResponse createUser(User user) {
        try {
            User existUser = userRepository.findUserByEmail(user.getEmail());
            if (existUser != null) {
                return APIResponseBuilder.build(Boolean.FALSE, user.getEmail(), "User already exists. Please try with other email");
            } else {
                user.setPassword(bcryptEncoder.encode(user.getPassword()));
                user.setRole(roleRepository.findByRole(Constant.ROLE_USER));
                user.setStatus(Boolean.TRUE);
                user = userRepository.save(user);
                return APIResponseBuilder.build(Boolean.TRUE, user, "User registered success");
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            return APIResponseBuilder.build(Boolean.FALSE, e.getMessage(), Constant.EXCEPTION);
        }
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findUserByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), getAuthority(user));
    }

    private Set getAuthority(User user) {
        Set authorities = new HashSet<>();
        SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(user.getRole().getRole());
        authorities.add(simpleGrantedAuthority);
        return authorities;
    }
}


