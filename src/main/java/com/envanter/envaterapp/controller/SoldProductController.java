package com.envanter.envaterapp.controller;

import com.envanter.envaterapp.model.SoldProduct;
import com.envanter.envaterapp.service.SoldProductService;
import com.envanter.envaterapp.service.CategoryService;
import com.envanter.envaterapp.model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.envanter.envaterapp.util.SoldProductPDFExporter;
import com.envanter.envaterapp.util.SoldProductExcelExporter;
import jakarta.servlet.http.HttpServletResponse;
import com.itextpdf.text.DocumentException;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


@Controller
@RequestMapping("/sales")
public class SoldProductController {

    @Autowired
    private SoldProductService soldProductService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public String showSalesPage(Model model) {
        model.addAttribute("soldProduct", new SoldProduct());
        model.addAttribute("sales", soldProductService.getAllSoldProducts());
        model.addAttribute("categories", categoryService.getAllCategories());
        return "sales";
    }

    @PostMapping("/add")
    public String addSale(@ModelAttribute("soldProduct") SoldProduct soldProduct, @RequestParam("category") Long categoryId) {
        Category category = categoryService.getCategoryById(categoryId);
        soldProduct.setCategory(category);
        soldProductService.saveSoldProduct(soldProduct);
        return "redirect:/sales";
    }


    @GetMapping("/delete/{id}")
    public String deleteSale(@PathVariable Long id) {
        soldProductService.deleteSoldProduct(id);
        return "redirect:/sales";
    }

    @GetMapping("/export/pdf")
    public void exportToPDF(HttpServletResponse response) throws DocumentException, IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=satislar_" + dateFormatter.format(new Date()) + ".pdf";
        response.setHeader(headerKey, headerValue);

        List<SoldProduct> list = soldProductService.getAllSoldProducts();
        SoldProductPDFExporter exporter = new SoldProductPDFExporter(list);
        exporter.export(response);
    }

    @GetMapping("/home")
    public String homePage() {
        return "home"; // src/main/resources/templates/home.html dosyasını açar
    }
}
