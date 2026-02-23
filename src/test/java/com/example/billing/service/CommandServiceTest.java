package com.example.billing.service;

import com.example.billing.dto.CommandDto;
import com.example.billing.dto.CommandItemDto;
import com.example.billing.dto.ProductDto;
import com.example.billing.dto.ProductTypeDto;
import com.example.billing.entity.Command;
import com.example.billing.entity.CommandItem;
import com.example.billing.entity.Product;
import com.example.billing.entity.ProductType;
import com.example.billing.exception.NotFoundException;
import com.example.billing.repository.CommandRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CommandServiceTest {

	@InjectMocks
	private CommandService commandService;
	@Mock
	private CommandRepository commandRepository;
	@Mock
	private ModelMapper modelMapper;
	
	@Test
	void findCommandsById_test_totalTTC() {
		Command commandMock=new Command();
		commandMock.setId(1);
		CommandItem commandItem=new CommandItem();
		commandItem.setId(1);
		commandItem.setQuantity(2);
		Product product=new Product();
		product.setId(1);
		product.setName("test");
		product.setPrice(27.9);
		product.setOrigin("FR");
		ProductType productType=new ProductType();
		productType.setId(1);
		productType.setLabel("OTHER");
		productType.setTax(0.1);
		product.setType(productType);
		commandItem.setProduct(product);
		commandMock.setItems(new HashSet<CommandItem>(List.of(commandItem)));
		when(commandRepository.findById(1L)).thenReturn(Optional.of(new Command()));
		
		
		CommandDto commandDtoMock=new CommandDto();
		commandDtoMock.setId(1);
		CommandItemDto commandDtoItem=new CommandItemDto();
		commandDtoItem.setId(1);
		commandDtoItem.setQuantity(2);
		ProductDto productDto=new ProductDto();
		productDto.setId(1);
		productDto.setName("test");
		productDto.setPrice(27.9);
		productDto.setOrigin("FR");
		ProductTypeDto productTypeDto=new ProductTypeDto();
		productTypeDto.setId(1);
		productTypeDto.setLabel("OTHER");
		productTypeDto.setTax(0.1);
		productDto.setType(productTypeDto);
		commandDtoItem.setProduct(productDto);
		commandDtoMock.setItems(new HashSet<CommandItemDto>(List.of(commandDtoItem)));
		when(modelMapper.map(org.mockito.ArgumentMatchers.any(), org.mockito.ArgumentMatchers.any())).thenReturn(commandDtoMock);
		
		CommandDto result = commandService.findCommandsById(1);
        assertEquals(61.4, result.getTotalTTC());
	}
	
	@Test
	void findCommandsById_test_exception() {
        when(commandRepository.findById(1L)).thenThrow(NotFoundException.class);
        assertThrows(NotFoundException.class, () -> {
			commandService.findCommandsById(1);
		});
	}
}
