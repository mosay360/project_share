package com.adminpanel.controller;
import com.adminpanel.domain.Items;
import com.adminpanel.service.ItemsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

@Controller
@RequestMapping("/items")
public class ItemsController {

	@Autowired
	private ItemsService itemsService;

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String addBook(Model model) {
		Items items= new Items();
		model.addAttribute("items", items);
		return "addItem";
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String addItemPost(@ModelAttribute("items") Items items, HttpServletRequest request) {
		itemsService.save(items);

		MultipartFile itemImage = items.getItemImage();

		try {
			byte[] bytes = itemImage.getBytes();
			String name = items.getId() + ".jpg";
			BufferedOutputStream stream = new BufferedOutputStream(
					new FileOutputStream(new File("src/main/resources/static/image/item/" + name)));
			stream.write(bytes);
			stream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "redirect:itemList";
	}
	
	@RequestMapping("/itemInfo")
	public String itemInfo(@RequestParam("id") Long id, Model model) {
		Items items = itemsService.findOne(id);
		model.addAttribute("items", items);
		
		return "itemInfo";
	}
	
	@RequestMapping("/updateItem")
	public String updateItem(@RequestParam("id") Long id, Model model) {
		Items items = itemsService.findOne(id);
		model.addAttribute("items", items);
		
		return "updateItem";
	}
	
	@RequestMapping(value="/updateItem", method=RequestMethod.POST)
	public String updateItemPost(@ModelAttribute("items")  Items items, HttpServletRequest request) {
		itemsService.save(items);
		
		MultipartFile itemImage = items.getItemImage();
		
		if(!itemImage.isEmpty()) {
			try {
				byte[] bytes = itemImage.getBytes();
				String name = items.getId() + ".jpg";
				
				Files.delete(Paths.get("src/main/resources/static/image/item/"+name));
				
				BufferedOutputStream stream = new BufferedOutputStream(
						new FileOutputStream(new File("src/main/resources/static/image/item/" + name)));
				stream.write(bytes);
				stream.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return "redirect:/items/itemInfo?id="+items.getId();
	}
	
	@RequestMapping("/itemList")
	public String itemList(Model model) {
		List<Items> itemList = itemsService.findAll();
		model.addAttribute("itemList", itemList);
		return "itemList";
		
	}
	
	@RequestMapping(value="/remove", method=RequestMethod.POST)
	public String remove(
			@ModelAttribute("id") String id, Model model
			) {
		itemsService.removeOne(Long.parseLong(id.substring(8)));
		List<Items> itemList = itemsService.findAll();
		model.addAttribute("itemList", itemList);
		
		return "redirect:/items/itemList";
	}

}
