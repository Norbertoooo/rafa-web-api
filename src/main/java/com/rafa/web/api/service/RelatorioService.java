package com.rafa.web.api.service;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.util.JRSaver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@Service
public class RelatorioService {

    @Autowired
    private DataSource dataSource;

    public byte[] gerarRelatorio(Integer pacienteId) throws IOException, JRException, SQLException {
        // String caminhoRelatorio = ResourceUtils.getFile("classpath:static/relatorios/ficha.jrxml").getAbsolutePath();

        InputStream caminhoRelatorio = new ClassPathResource("static/relatorios/relatorio_de_acompanhamento.jrxml").getInputStream();
        InputStream caminhoRelatorioResponsavel = new ClassPathResource("static/relatorios/responsaveis.jrxml").getInputStream();
        InputStream caminhoRelatorioTerapeuta = new ClassPathResource("static/relatorios/terapeuta.jrxml").getInputStream();
        InputStream caminhoRelatorioAtendimentos = new ClassPathResource("static/relatorios/atendimentos.jrxml").getInputStream();
        InputStream caminhoRelatorioDesafios = new ClassPathResource("static/relatorios/desafios.jrxml").getInputStream();

        JasperReport jasperReportTerapeuta = JasperCompileManager.compileReport(caminhoRelatorioTerapeuta);
        JasperReport jasperReportResponsavel = JasperCompileManager.compileReport(caminhoRelatorioResponsavel);
        JasperReport jasperReportAtendimentos = JasperCompileManager.compileReport(caminhoRelatorioAtendimentos);
        JasperReport jasperReportDesafios= JasperCompileManager.compileReport(caminhoRelatorioDesafios);

        JRSaver.saveObject(jasperReportTerapeuta, "terapeuta.jasper");
        JRSaver.saveObject(jasperReportResponsavel, "responsaveis.jasper");
        JRSaver.saveObject(jasperReportAtendimentos, "atendimentos.jasper");
        JRSaver.saveObject(jasperReportDesafios, "desafios.jasper");

        JasperReport jasperReport = JasperCompileManager.compileReport(caminhoRelatorio);

        Map<String, Object> parametros = new HashMap<>();
        parametros.put("id_paciente", pacienteId);

        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parametros, dataSource.getConnection());

        return JasperExportManager.exportReportToPdf(jasperPrint);
    }
}
