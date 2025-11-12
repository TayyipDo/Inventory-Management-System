package com.envanter.envaterapp.controller;

import com.envanter.envaterapp.model.Category;
import com.envanter.envaterapp.model.IncomingProduct;
import com.envanter.envaterapp.service.CategoryService;
import com.envanter.envaterapp.service.IncomingProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.envanter.envaterapp.util.IncomingProductPDFExporter;
import com.envanter.envaterapp.util.IncomingProductExcelExporter;
import jakarta.servlet.http.HttpServletResponse;
import com.itextpdf.text.DocumentException;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


@Controller
@RequestMapping("/purchases")
public class IncomingProductController {

    @Autowired
    private IncomingProductService incomingProductService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public String showPurchasePage(Model model) {
        model.addAttribute("incomingProduct", new IncomingProduct());
        model.addAttribute("purchases", incomingProductService.getAllIncomingProducts());
        model.addAttribute("categories", categoryService.getAllCategories());
        return "purchases";
    }

    @PostMapping("/add")
    public String addIncomingProduct(@ModelAttribute("incomingProduct") IncomingProduct incomingProduct,
                                     @RequestParam("category") Long categoryId) {
        Category category = categoryService.getCategoryById(categoryId);
        incomingProduct.setCategory(category);
        incomingProduct.setPurchased(false);
        incomingProductService.saveIncomingProduct(incomingProduct);
        return "redirect:/purchases";
    }

    @GetMapping("/delete/{id}")
    public String deleteIncomingProduct(@PathVariable Long id) {
        incomingProductService.deleteIncomingProduct(id);
        return "redirect:/purchases";
    }

    @GetMapping("/mark-purchased/{id}")
    public String markAsPurchased(@PathVariable Long id) {
        incomingProductService.markAsPurchased(id);
        return "redirect:/purchases";
    }

    @GetMapping("/export/pdf")
    public void exportToPDF(HttpServletResponse response) throws DocumentException, IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
        String headerValue = "attachment; filename=alinacak_urunler_" + dateFormatter.format(new Date()) + ".pdf";
        response.setHeader("Content-Disposition", headerValue);

        List<IncomingProduct> list = incomingProductService.getAllIncomingProducts();
        IncomingProductPDFExporter exporter = new IncomingProductPDFExporter(list);
        exporter.export(response);
    }

    @GetMapping("/export/excel")
    public void exportToExcel(HttpServletResponse response) throws IOException {
        response.setContentType("application/octet-stream");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
        String headerValue = "attachment; filename=alinacak_urunler_" + dateFormatter.format(new Date()) + ".xlsx";
        response.setHeader("Content-Disposition", headerValue);

        List<IncomingProduct> list = incomingProductService.getAllIncomingProducts();
        IncomingProductExcelExporter exporter = new IncomingProductExcelExporter(list);
        exporter.export(response);
    }
    @GetMapping("/home")
    public String homePage() {
        return "home"; // src/main/resources/templates/home.html dosyasını açar
    }
}
