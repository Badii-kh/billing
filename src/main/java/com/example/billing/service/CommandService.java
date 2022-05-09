package com.example.billing.service;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.example.billing.dto.CommandDto;
import com.example.billing.dto.CommandItemDto;
import com.example.billing.dto.ProductDto;
import com.example.billing.entity.Command;
import com.example.billing.exception.NotFoundException;
import com.example.billing.repository.CommandRepository;
import com.example.billing.utils.TaxUtil;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class CommandService {

	private static final String FRANCE = "FR";
	private static final double FOREIGN_TAX = 0.05;

	private final CommandRepository commandRepository;
	private final ModelMapper modelMapper;

	public CommandDto findCommandsById(long id) {
		return commandRepository.findById(id)
				.map((command -> buildCommand(command)))
				.orElseThrow(() -> new NotFoundException("Commmand with id : [" + id + "] not found"));
	}
	
	private CommandDto buildCommand(Command command) {
		CommandDto commandeDto = modelMapper.map(command, CommandDto.class);
		double totalHT = 0.0;
		for(CommandItemDto item : commandeDto.getItems()) {
			item.setPriceTTC(computePriceTTC(item));
			commandeDto.setTotalTTC(commandeDto.getTotalTTC()+item.getPriceTTC());
			totalHT += item.getProduct().getPrice() * item.getQuantity();
		}
		commandeDto.setTotalTax(commandeDto.getTotalTTC() - totalHT);
		return commandeDto;
	}

	private double computePriceTTC(CommandItemDto commandItem) {
		double tax = computeTax(commandItem.getProduct());
		double p = commandItem.getProduct().getPrice() * commandItem.getQuantity()
				+ (commandItem.getQuantity() * commandItem.getProduct().getPrice() * tax);
		return TaxUtil.roundToHigherCent(p);
	}

	private double computeTax(ProductDto product) {
		double tax = product.getType().getTax()
				+ (product.getOrigin().equals(FRANCE) ? 0 : FOREIGN_TAX);
		return tax;
	}
}
