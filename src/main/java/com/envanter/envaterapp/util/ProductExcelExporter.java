package com.envanter.envaterapp.util;

import com.envanter.envaterapp.model.Product;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;
import java.util.List;

public class ProductExcelExporter {

    private XSSFWorkbook workbook;
    private Sheet sheet;
    private List<Product> productList;

    public ProductExcelExporter(List<Product> productList) {
        this.productList = productList;
        workbook = new XSSFWorkbook();
        sheet = workbook.createSheet("Ürünler");
    }

    private void writeHeaderRow() {
        Row row = sheet.createRow(0);
        CellStyle style = workbook.createCellStyle();
        Font font = workbook.createFont();
        font.setBold(true);
        font.setFontHeightInPoints((short) 12);
        style.setFont(font);

        String[] headers = {"ID", "Ad", "Açıklama", "Miktar", "Fiyat", "Kategori"};

        for (int i = 0; i < headers.length; i++) {
            Cell cell = row.createCell(i);
            cell.setCellValue(headers[i]);
            cell.setCellStyle(style);
        }
    }

    private void writeDataRows() {
        int rowCount = 1;

        for (Product p : productList) {
            Row row = sheet.createRow(rowCount++);
            row.createCell(0).setCellValue(p.getId());
            row.createCell(1).setCellValue(p.getName());
            row.createCell(2).setCellValue(p.getDescription());
            row.createCell(3).setCellValue(p.getQuantity());
            row.createCell(4).setCellValue(p.getPrice());
            row.createCell(5).setCellValue(p.getCategory().getName());
        }

        for (int i = 0; i <= 5; i++) {
            sheet.autoSizeColumn(i);
        }
    }

    public void export(HttpServletResponse response) throws IOException {
        writeHeaderRow();
        writeDataRows();

        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();
        outputStream.close();
    }
}
