package com.example.listam.controller;

import com.example.listam.entity.Category;
import com.example.listam.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class CategoriesController {

    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping("/categories")
    public String categoriesPage(ModelMap modelMap){
        List<Category> all = categoryRepository.findAll();
        modelMap.addAttribute("allCategories",all);
        return "/categories";
    }
    @PostMapping("/categories/add")
    public String addCategory(@RequestParam("name") String name){
        Category category = new Category();
        category.setName(name);
        categoryRepository.save(category);
        return "redirect:/categories";
    }
    @GetMapping("/categories/remove")
    public String removeCategory(@RequestParam("id") int id){
        categoryRepository.deleteById(id);
        return "redirect:/categories";
    }
}
