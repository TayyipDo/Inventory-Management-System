package com.envanter.envaterapp.util;

import com.envanter.envaterapp.model.Product;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;

import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ProductPDFExporter {

    private List<Product> productList;

    public ProductPDFExporter(List<Product> productList) {
        this.productList = productList;
    }

    public void export(HttpServletResponse response) throws DocumentException, IOException {
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());

        document.open();

        Font fontTitle = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        fontTitle.setSize(18);
        fontTitle.setColor(BaseColor.DARK_GRAY);

        Paragraph title = new Paragraph("Ürün Listesi", fontTitle);
        title.setAlignment(Paragraph.ALIGN_CENTER);
        title.setSpacingAfter(20);
        document.add(title);

        PdfPTable table = new PdfPTable(6);
        table.setWidthPercentage(100);
        table.setWidths(new float[]{1.2f, 2.5f, 2.5f, 1.5f, 2f, 2f});
        table.setSpacingBefore(10);

        writeTableHeader(table);
        writeTableData(table);

        document.add(table);
        document.close();
    }

    private void writeTableHeader(PdfPTable table) {
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
        cell.setPadding(5);

        Font font = FontFactory.getFont(FontFactory.HELVETICA);
        font.setColor(BaseColor.BLACK);

        cell.setPhrase(new Phrase("ID", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Ad", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Açıklama", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Miktar", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Fiyat", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Kategori", font));
        table.addCell(cell);
    }

    private void writeTableData(PdfPTable table) {
        for (Product p : productList) {
            table.addCell(String.valueOf(p.getId()));
            table.addCell(p.getName());
            table.addCell(p.getDescription());
            table.addCell(String.valueOf(p.getQuantity()));
            table.addCell(String.valueOf(p.getPrice()));
            table.addCell(p.getCategory().getName());
        }
    }
}
