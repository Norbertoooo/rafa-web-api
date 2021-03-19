package com.rafa.web.api.service;

import net.sf.jasperreports.engine.*;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.FileNotFoundException;
import java.util.HashMap;

@Service
public class RelatorioService {

    public byte[] gerarRelatorio() throws FileNotFoundException, JRException {
        String caminho = ResourceUtils.getFile("classpath:static/relatorios/Ficha.jrxml").getAbsolutePath();
        JasperReport jasperReport = JasperCompileManager.compileReport(caminho);
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, new HashMap<>(), new JREmptyDataSource());
        return JasperExportManager.exportReportToPdf(jasperPrint);
    }
}
