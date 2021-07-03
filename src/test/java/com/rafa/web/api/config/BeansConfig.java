package com.rafa.web.api.config;

import com.rafa.web.api.config.jwt.JwtAuthenticationEntryPoint;
import com.rafa.web.api.config.jwt.JwtTokenUtil;
import com.rafa.web.api.config.jwt.JwtUserDetailsService;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@TestConfiguration
public class BeansConfig {

    @MockBean
    private JwtUserDetailsService jwtUserDetailsService;

    @MockBean
    private BCryptPasswordEncoder passwordEncoder;

    @MockBean
    private JavaMailSender javaMailSender;

    @MockBean
    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    @MockBean
    private JwtTokenUtil jwtTokenUtil;

}
