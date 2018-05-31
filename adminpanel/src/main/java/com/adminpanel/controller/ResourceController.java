package com.adminpanel.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


import com.adminpanel.service.ItemsService;
@RestController
public class ResourceController {

	@Autowired
	private ItemsService itemsService;
	
	@RequestMapping(value="/item/removeList", method=RequestMethod.POST)
	public String removeList(
			@RequestBody ArrayList<String> itemIdList, Model model
			){
		
		for (String id : itemIdList) {
			String itemtId =id.substring(8);
			itemsService.removeOne(Long.parseLong(itemtId));
		}
		
		return "delete success";
	}
}
