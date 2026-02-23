package com.example.billing.service;


import com.example.billing.dto.CommandDto;
import com.example.billing.dto.CommandItemDto;
import com.example.billing.dto.ProductDto;
import com.example.billing.entity.Command;
import com.example.billing.exception.NotFoundException;
import com.example.billing.repository.CommandRepository;
import com.example.billing.utils.TaxUtil;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

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
				.map((this::buildCommand))
				.orElseThrow(() -> new NotFoundException("Commmand with id : [" + id + "] not found"));
	}

    private CommandDto buildCommand(Command command) {
        CommandDto commandeDto = modelMapper.map(command, CommandDto.class);
        double totalTax = 0.0;
        for(CommandItemDto item : commandeDto.getItems()) {
            double priceHT = item.getProduct().getPrice() * item.getQuantity();
            double tax = priceHT * computeTax(item.getProduct());
            double priceTTC = TaxUtil.roundToHigherCent(priceHT + tax);
            commandeDto.setTotalTTC(commandeDto.getTotalTTC()+priceTTC);
            totalTax += tax;
        }
        commandeDto.setTotalTax(totalTax);
        return commandeDto;
    }

	private double computeTax(ProductDto product) {
        return product.getType().getTax()
				+ (product.getOrigin().equals(FRANCE) ? 0 : FOREIGN_TAX);
	}
}
