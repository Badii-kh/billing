package com.khila.billing.application.service;

import com.khila.billing.domain.pricing.model.Order;
import com.khila.billing.domain.pricing.port.out.OrderRepositoryPort;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OrderServiceTest {

    @Test
    void retrieve_shouldCallRepositoryWithGivenId_andReturnItsResult() {
        // Arrange
        OrderRepositoryPort repository = Mockito.mock(OrderRepositoryPort.class);
        OrderService service = new OrderService(repository);

        long orderId = 123L;
        Order expected = Mockito.mock(Order.class);

        when(repository.findById(orderId)).thenReturn(expected);

        // Act
        Order result = service.retrieve(orderId);

        // Assert — vérifie la valeur de retour
        assertThat(result).isSameAs(expected);

        // Assert — vérifie l’appel avec le bon argument (exact)
        verify(repository, times(1)).findById(orderId);
        verifyNoMoreInteractions(repository);
    }

    @Test
    void retrieve_shouldPassExactId_usingArgumentCaptor() {
        // Arrange
        OrderRepositoryPort repository = Mockito.mock(OrderRepositoryPort.class);
        OrderService service = new OrderService(repository);

        long orderId = 456L;
        when(repository.findById(anyLong())).thenReturn(Mockito.mock(Order.class));

        // Act
        service.retrieve(orderId);

        // Assert — capture et vérifie l’argument transmis
        ArgumentCaptor<Long> captor = ArgumentCaptor.forClass(Long.class);
        verify(repository).findById(captor.capture());
        assertThat(captor.getValue()).isEqualTo(orderId);
    }
}