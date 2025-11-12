package com.envanter.envaterapp.controller;

import com.envanter.envaterapp.model.Category;
import com.envanter.envaterapp.model.Product;
import com.envanter.envaterapp.service.CategoryService;
import com.envanter.envaterapp.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.envanter.envaterapp.util.ProductPDFExporter;
import com.envanter.envaterapp.util.ProductExcelExporter;
import jakarta.servlet.http.HttpServletResponse;
import com.itextpdf.text.DocumentException;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public String showProductPage(Model model) {
        model.addAttribute("product", new Product());
        model.addAttribute("products", productService.getAllProducts());
        model.addAttribute("categories", categoryService.getAllCategories());
        return "products";
    }

    @PostMapping("/add")
    public String addProduct(@ModelAttribute("product") Product product, @RequestParam("category") Long categoryId) {
        Category category = categoryService.getCategoryById(categoryId);
        product.setCategory(category);
        productService.saveProduct(product);
        return "redirect:/products";
    }


    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return "redirect:/products";
    }
    @GetMapping("/export/pdf")
    public void exportToPDF(HttpServletResponse response) throws DocumentException, IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
        String headerValue = "attachment; filename=urunler_" + dateFormat.format(new Date()) + ".pdf";
        response.setHeader("Content-Disposition", headerValue);

        List<Product> list = productService.getAllProducts();
        ProductPDFExporter exporter = new ProductPDFExporter(list);
        exporter.export(response);
    }
    @GetMapping("/export/excel")
    public void exportToExcel(HttpServletResponse response) throws IOException {
        response.setContentType("application/octet-stream");
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
        String headerValue = "attachment; filename=urunler_" + dateFormat.format(new Date()) + ".xlsx";
        response.setHeader("Content-Disposition", headerValue);

        List<Product> list = productService.getAllProducts();
        ProductExcelExporter exporter = new ProductExcelExporter(list);
        exporter.export(response);
    }
    @GetMapping("/home")
    public String homePage() {
        return "home"; // src/main/resources/templates/home.html dosyasını açar
    }
}
