package com.rafa.web.api.web;

import com.rafa.web.api.config.jwt.JwtAuthenticationEntryPoint;
import com.rafa.web.api.config.jwt.JwtTokenUtil;
import com.rafa.web.api.config.jwt.JwtUserDetailsService;
import com.rafa.web.api.service.ResponsavelService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ResponsavelResource.class)
@ActiveProfiles("test")
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
public class ResponsavelResourceTest {

    @Autowired
    private MockMvc mockMvc;

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

    @MockBean
    private ResponsavelService responsavelServiceMock;

    @Test
    public void listarResponsaveis() throws Exception {
        when(responsavelServiceMock.listarResponsaveis(0, 10)).thenReturn(Page.empty());
        this.mockMvc.perform(MockMvcRequestBuilders
                .get("/api/responsaveis"))
                .andDo(print())
                .andExpect(status().is2xxSuccessful());
    }
}
