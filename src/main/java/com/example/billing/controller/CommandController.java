package com.example.billing.controller;

import com.example.billing.dto.CommandDto;
import com.example.billing.service.CommandService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class CommandController {

	private final CommandService commandService;
	
	@GetMapping("/command/{id}")
	public CommandDto findCommandById(@PathVariable long id){
		return commandService.findCommandsById(id);
	}
}
