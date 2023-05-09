package com.example.listam.controller;

import com.example.listam.entity.Comment;
import com.example.listam.entity.Item;
import com.example.listam.repository.CommentRepository;
import com.example.listam.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
public class CommentController {
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private ItemRepository itemRepository;

    @PostMapping("/comments")
    public String saveComment(@RequestParam("comment") String com, @RequestParam("itemId") int itemId,
                              ModelMap modelMap) {
        Comment comment = new Comment();
        comment.setComment(com);
        Optional<Item> itemOptional = itemRepository.findById(itemId);
        if (itemOptional.isPresent()) {
            Item item = itemOptional.get();
            comment.setItem(item);
            commentRepository.save(comment);
            List<Comment> comments = commentRepository.findByItemId(itemId);
            modelMap.addAttribute("comments", comments);
            modelMap.addAttribute("item", item);
        }
        return "/singleItem";
    }

    @GetMapping("/comments/remove")
    public String removeComment(@RequestParam("id") int id, @RequestParam("item") int itemId, ModelMap modelmap) {
        commentRepository.deleteById(id);
        Optional<Item> byId = itemRepository.findById(itemId);
        List<Comment> comments = commentRepository.findByItemId(itemId);
        if (byId.isPresent()) {
            Item item = byId.get();
            modelmap.addAttribute(item);
        }
        modelmap.addAttribute("comments", comments);
        return "/singleItem";
    }
}
