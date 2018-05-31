package com.moccons.controller;


import com.moccons.domain.User;
import com.moccons.domain.Items;
import com.moccons.service.ItemService;
import com.moccons.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.List;

@Controller
public class SearchController {
	@Autowired
	private UserService userService;
	
	@Autowired
	private ItemService itemService;

	@RequestMapping("/searchByCategory")
	public String searchByCategory(
			@RequestParam("category") String category,
			Model model, Principal principal
			){
		if(principal!=null) {
			String username = principal.getName();
			User user = userService.findByUsername(username);
			model.addAttribute("user", user);
		}
		
		String classActiveCategory = "active"+category;
		classActiveCategory = classActiveCategory.replaceAll("\\s+", "");
		classActiveCategory = classActiveCategory.replaceAll("&", "");
		model.addAttribute(classActiveCategory, true);
		
		List<Items> itemList = itemService.findByCategory(category);
		
		if (itemList.isEmpty()) {
			model.addAttribute("emptyList", true);
			return "bookshelf";
		}
		
		model.addAttribute("itemList", itemList);
		
		return "bookshelf";
	}
	
	@RequestMapping("/searchBook")
	public String searchBook(
			@ModelAttribute("keyword") String keyword,
			Principal principal, Model model
			) {
		if(principal!=null) {
			String username = principal.getName();
			User user = userService.findByUsername(username);
			model.addAttribute("user", user);
		}
		
		List<Items> itemList = itemService.blurrySearch(keyword);
		
		if (itemList.isEmpty()) {
			model.addAttribute("emptyList", true);
			return "bookshelf";
		}
		
		model.addAttribute("itemList", itemList);
		
		return "bookshelf";
	}
}
