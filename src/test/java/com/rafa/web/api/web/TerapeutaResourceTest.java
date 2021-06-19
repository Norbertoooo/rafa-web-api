package com.rafa.web.api.web;

import com.rafa.web.api.config.jwt.JwtAuthenticationEntryPoint;
import com.rafa.web.api.config.jwt.JwtTokenUtil;
import com.rafa.web.api.config.jwt.JwtUserDetailsService;
import com.rafa.web.api.domain.Terapeuta;
import com.rafa.web.api.service.TerapeutaService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static com.rafa.web.api.util.JsonUtil.converterParaJson;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(TerapeutaResource.class)
@ActiveProfiles("test")
@AutoConfigureMockMvc
public class TerapeutaResourceTest {

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
    private TerapeutaService terapeutaServiceMock;

    @Test
    public void cadastrarTerapeuta() throws Exception {
        Terapeuta terapeuta = montarTerapeutaMock();
        when(terapeutaServiceMock.cadastrarTerapeuta(terapeuta)).thenReturn(terapeuta);
        this.mockMvc.perform(MockMvcRequestBuilders
                .post("/api/terapeutas")
                .content(converterParaJson(terapeuta))
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    public void listarTerapeutas() throws Exception {
        when(terapeutaServiceMock.listarTerapeutas(0, 10)).thenReturn(Page.empty());
        this.mockMvc.perform(MockMvcRequestBuilders
                .get("/api/terapeutas"))
                .andDo(print())
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    public void buscarTerapeutaPeloId() throws Exception {
        when(terapeutaServiceMock.buscarTerapeutaPeloId(1L)).thenReturn(Terapeuta.builder().construir());
        this.mockMvc.perform(MockMvcRequestBuilders
                .get("/api/terapeutas").param("id", "1"))
                .andDo(print())
                .andExpect(status().is2xxSuccessful());
    }

    private Terapeuta montarTerapeutaMock() {
        return Terapeuta.builder()
                .comCpf("324324324")
                .comCrfa(123456L)
                .comEspecialidade("nada com nada")
                .comFormacao("em incrivelmente nada")
                .comNomeCompleto("nadinha da silva")
                .comTelefone(3213231L)
                .construir();
    }

}
