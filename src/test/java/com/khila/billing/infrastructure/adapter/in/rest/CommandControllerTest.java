package com.khila.billing.infrastructure.adapter.in.rest;

import com.khila.billing.application.service.OrderService;
import com.khila.billing.domain.pricing.model.Order;
import com.khila.billing.infrastructure.adapter.in.rest.dto.OrderDto;
import com.khila.billing.infrastructure.adapter.in.rest.mapper.OrderMapper;
import com.khila.billing.infrastructure.adapter.out.persistence.OrderJpaRepository;
import com.khila.billing.infrastructure.adapter.out.persistence.entity.OrderEntity;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CommandController.class)
class CommandControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private OrderService orderService;

    @MockitoBean
    private OrderMapper orderMapper;

    @MockitoBean
    private OrderJpaRepository orderJpaRepository;

    @Test
    void shouldReturnOrderDtoWhenCommandExists() throws Exception {
        long id = 42L;

        // Given — mocks
        Order mockOrder = Mockito.mock(Order.class);

        OrderDto mockOrderDto = new OrderDto();
        mockOrderDto.setItems(Collections.emptySet());
        mockOrderDto.setTotalTTC(123.45);
        mockOrderDto.setTotalTax(23.45);

        OrderEntity mockOrderEntity = new OrderEntity();
        mockOrderEntity.setId(id);
        mockOrderEntity.setItems(Collections.emptySet());

        Mockito.when(orderService.retrieve(id)).thenReturn(mockOrder);
        Mockito.when(orderMapper.apply(mockOrder)).thenReturn(mockOrderDto);
        Mockito.when(orderJpaRepository.findById(id)).thenReturn(Optional.of(mockOrderEntity));

        // When + Then
        mockMvc.perform(get("/api/command/{id}", id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.totalTTC").value(123.45))
                .andExpect(jsonPath("$.totalTax").value(23.45))
                .andExpect(jsonPath("$.items").isArray());
    }
}