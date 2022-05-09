package com.example.billing.controller;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.billing.dto.CommandDto;
import com.example.billing.service.CommandService;

@ExtendWith(MockitoExtension.class)
public class CommandControllerTest {

	@InjectMocks
	private CommandController commandController;
	@Mock
	private CommandService commandService;
	
	@Test
	public void findCommandsById_test() {
		CommandDto commandDtoMock=new CommandDto();
		commandDtoMock.setId(1);
		when(commandService.findCommandsById(1)).thenReturn(commandDtoMock);
		CommandDto result=commandController.findCommandById(1);
		assertTrue(result.getId()==1);
	}
}
