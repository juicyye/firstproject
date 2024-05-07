package kozin.first.domain.repository.dto;

import lombok.Data;

import java.time.LocalDateTime;
@Data
public class SimpleOrderDto {
    private Long orderId;
    private String name;
    private LocalDateTime orderDate;

    public SimpleOrderDto(Long orderId, String name, LocalDateTime orderDate) {
        this.orderId = orderId;
        this.name = name;
        this.orderDate = orderDate;
    }
}
