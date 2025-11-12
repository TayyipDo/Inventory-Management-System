package com.envanter.envaterapp.util;

import com.envanter.envaterapp.model.IncomingProduct;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;

import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class IncomingProductPDFExporter {

    private List<IncomingProduct> list;

    public IncomingProductPDFExporter(List<IncomingProduct> list) {
        this.list = list;
    }

    public void export(HttpServletResponse response) throws DocumentException, IOException {
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());
        document.open();

        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font.setSize(16);

        Paragraph title = new Paragraph("Alınacak Ürünler Listesi", font);
        title.setAlignment(Paragraph.ALIGN_CENTER);
        title.setSpacingAfter(20);
        document.add(title);

        PdfPTable table = new PdfPTable(7);
        table.setWidthPercentage(100);
        table.setWidths(new float[]{1.2f, 2f, 2.5f, 1.2f, 1.5f, 2f, 2f});
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

        String[] headers = {"ID", "Ad", "Açıklama", "Miktar", "Fiyat", "Kategori", "Durum"};
        for (String header : headers) {
            cell.setPhrase(new Phrase(header, font));
            table.addCell(cell);
        }
    }

    private void writeTableData(PdfPTable table) {
        for (IncomingProduct p : list) {
            table.addCell(String.valueOf(p.getId()));
            table.addCell(p.getName());
            table.addCell(p.getDescription());
            table.addCell(String.valueOf(p.getQuantity()));
            table.addCell(String.valueOf(p.getPrice()));
            table.addCell(p.getCategory().getName());
            table.addCell(p.isPurchased() ? "Alındı" : "Bekliyor");
        }
    }
}
