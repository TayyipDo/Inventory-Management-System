package com.envanter.envaterapp.controller;

import com.envanter.envaterapp.model.Category;
import com.envanter.envaterapp.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public String showCategoryPage(Model model) {
        model.addAttribute("category", new Category());
        model.addAttribute("categories", categoryService.getAllCategories());
        return "categories";
    }

    @PostMapping("/add")
    public String addCategory(@ModelAttribute("category") Category category, Model model) {
        if (categoryService.categoryExists(category.getName())) {
            model.addAttribute("message", "Bu kategori zaten mevcut!");
        } else {
            categoryService.saveCategory(category);
            model.addAttribute("message", "Kategori eklendi.");
        }
        model.addAttribute("categories", categoryService.getAllCategories());
        model.addAttribute("category", new Category());
        return "redirect:/categories";
    }

    @GetMapping("/delete/{id}")
    public String deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
        return "redirect:/categories";
    }
    @GetMapping("/home")
    public String homePage() {
        return "home"; // src/main/resources/templates/home.html dosyasını açar
    }
}
