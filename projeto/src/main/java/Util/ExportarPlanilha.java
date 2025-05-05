package Util;

import java.io.FileOutputStream;
import java.sql.*;

import org.apache.poi.sl.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExportarPlanilha {

    public static void gerarPlanilhaRespostas(Connection conn) {
        String sql = "SELECT * FROM resposta";

        try (
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            Workbook workbook = new XSSFWorkbook()
        ) {
            Sheet planilha = (Sheet) workbook.createSheet("Respostas");

            // Cabeçalho
            ResultSetMetaData meta = rs.getMetaData();
            int colCount = meta.getColumnCount();
            Row header = ((org.apache.poi.ss.usermodel.Sheet) planilha).createRow(0);
            for (int i = 1; i <= colCount; i++) {
                header.createCell(i - 1).setCellValue(meta.getColumnName(i));
            }

            // Dados
            int rowIndex = 1;
            while (rs.next()) {
                Row row = ((org.apache.poi.ss.usermodel.Sheet) planilha).createRow(rowIndex++);
                for (int i = 1; i <= colCount; i++) {
                    row.createCell(i - 1).setCellValue(rs.getString(i));
                }
            }

            // Salvar planilha
            try (FileOutputStream out = new FileOutputStream("respostas.xlsx")) {
                workbook.write(out);
                System.out.println("Planilha 'respostas.xlsx' gerada com sucesso!");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
