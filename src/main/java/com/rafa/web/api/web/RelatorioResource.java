package com.rafa.web.api.web;

import com.rafa.web.api.service.RelatorioService;
import lombok.extern.slf4j.Slf4j;
import net.sf.jasperreports.engine.JRException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.SQLException;

@RestController
@RequestMapping("/api/relatorios")
@Slf4j
public class RelatorioResource {

    private final RelatorioService relatorioService;

    public RelatorioResource(RelatorioService relatorioService) {
        this.relatorioService = relatorioService;
    }

    @GetMapping("/{pacienteId}")
    public void exportarRelatorioFichaPaciente(HttpServletResponse response, @PathVariable Integer pacienteId) throws IOException, JRException, SQLException {
        // Configura a resposta para o tipo PDF
        response.setContentType("application/pdf");
        // Define que o arquivo pode ser visualizado no navegador e também nome final do arquivo
        // para fazer download do relatório troque 'inline' por 'attachment'
        response.setHeader("Content-Disposition", "inline; filename=ficha.pdf");
        OutputStream outStream = response.getOutputStream();
        outStream.write(relatorioService.gerarRelatorio(pacienteId));
    }
}
