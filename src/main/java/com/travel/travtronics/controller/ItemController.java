package com.travel.travtronics.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.travel.travtronics.enums.SortType;
import com.travel.travtronics.request.ItemRequest;
import com.travel.travtronics.response.APIResponse;
import com.travel.travtronics.response.APIResponsePaging;
import com.travel.travtronics.response.MessageStatusResponse;
import com.travel.travtronics.service.ItemService;

@RestController
public class ItemController {
	@Autowired
	ItemService componentService;

	@PostMapping(value = "create-item")
	public MessageStatusResponse createComponent(@RequestBody ItemRequest model) {
		return componentService.createComponet(model);
	}

	@PutMapping(value = "modify-item/{id}")
	public MessageStatusResponse modifyComponent(@RequestBody ItemRequest model, @PathVariable Long id) {
		return componentService.modifyComponent(model, id);
	}

	@GetMapping(value = "get-item/{id}")
	public APIResponse getComponent(@PathVariable Long id) {
		return componentService.getComponent(id);
	}

	@GetMapping(value = "get-item-by-organizationId")
	public APIResponse getComponents(@RequestParam Long organizationId) {
		return componentService.getComponents(organizationId);
	}

	@GetMapping(value = "/get-pagenation-by-organization")
	public APIResponsePaging getPagenationByOrganization(@RequestParam(required = false) Long organizationId,
			@RequestParam(required = false) String name, @RequestParam(defaultValue = "0") int pageNo,
			@RequestParam(defaultValue = "10") int pageSize, @RequestParam(defaultValue = "id") String sortBy,
			@RequestParam(defaultValue = "asc", required = false) SortType sortType) {
		return componentService.getPagenationByOrganization(organizationId, name, pageNo, pageSize, sortBy, sortType);
	}

}
