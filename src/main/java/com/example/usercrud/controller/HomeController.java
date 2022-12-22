package com.example.usercrud.controller;

import com.example.usercrud.entity.AppUser;
import com.example.usercrud.jwt.JwtTokenProvider;
import com.example.usercrud.mapper.AppUserMapper;
import com.example.usercrud.payload.LoginRequest;
import com.example.usercrud.payload.LoginResponse;
import com.example.usercrud.payload.RandomStuff;
import com.example.usercrud.util.CustomUserDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class HomeController {
    @Autowired
    AppUserMapper appUserMapper;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider tokenProvider;
    @PostMapping("/register")
    public LoginResponse register(@Valid @RequestBody LoginRequest loginRequest) {
        AppUser user = AppUser.builder()
                .username(loginRequest.getUsername())
                .password(passwordEncoder.encode(loginRequest.getPassword()))
                .build();
        appUserMapper.save(user);
        return null;
    }

    @PostMapping("/login")
    public LoginResponse authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
//         Xac thuc
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(),loginRequest.getPassword());
        Authentication authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);

        System.out.println("here1");
        SecurityContextHolder.getContext().setAuthentication(authentication);
        System.out.println("here2");
        // Tra JWT
        String jwt = tokenProvider.generateToken((CustomUserDetail) authentication.getPrincipal());
        return new LoginResponse(jwt);
    }
    @GetMapping("/random")
    public RandomStuff randomStuff(){
        return new RandomStuff("JWT Hợp lệ mới có thể thấy được message này");
    }
}
