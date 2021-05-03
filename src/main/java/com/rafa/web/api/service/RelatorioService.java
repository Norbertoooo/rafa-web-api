package com.rafa.web.api.service;

import net.sf.jasperreports.engine.*;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

@Service
public class RelatorioService {

    public byte[] gerarRelatorio() throws IOException, JRException {
        // String caminhoRelatorio = ResourceUtils.getFile("classpath:static/relatorios/ficha.jrxml").getAbsolutePath();
        InputStream caminhoRelatorio = new ClassPathResource("static/relatorios/ficha.jrxml").getInputStream();
        JasperReport jasperReport = JasperCompileManager.compileReport(caminhoRelatorio);
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, new HashMap<>(), new JREmptyDataSource());
        return JasperExportManager.exportReportToPdf(jasperPrint);
    }
}
