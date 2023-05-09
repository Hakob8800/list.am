package com.example.listam.controller;

import com.example.listam.entity.Category;
import com.example.listam.entity.Comment;
import com.example.listam.entity.Item;
import com.example.listam.repository.CategoryRepository;
import com.example.listam.repository.CommentRepository;
import com.example.listam.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class ItemController {
    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private CommentRepository commentRepository;

    @GetMapping("/items")
    public String items(ModelMap modelMap) {
        List<Item> all = itemRepository.findAll();
        modelMap.addAttribute("allItems", all);
        return "/items";
    }

    @GetMapping("/items/add")
    public String addItem(ModelMap modelMap) {
        List<Category> all = categoryRepository.findAll();
        modelMap.addAttribute("categories", all);
        return "/addItem";
    }

    @PostMapping("/items/add")
    public String saveItem(@ModelAttribute Item item){
        itemRepository.save(item);
        return "redirect:/items";
    }

    @GetMapping("/items/remove")
    public String removeCategory(@RequestParam("id") int id){
        itemRepository.deleteById(id);
        return "redirect:/items";
    }

    @GetMapping("/items/{id}")
    public String singleItemPage(@PathVariable("id") int id,
                                 ModelMap modelMap) {
        Optional<Item> optionalItem = itemRepository.findById(id);
        if (optionalItem.isPresent()) {
            Item item = optionalItem.get();
            List<Comment> comments = commentRepository.findByItemId(item.getId());
            modelMap.addAttribute("item", item);
            modelMap.addAttribute("comments", comments);
            return "/singleItem";
        } else {
            return "redirect:/items";
        }
    }
}
