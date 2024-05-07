package kozin.first.domain.repository.dto;

import kozin.first.domain.entity.OrderItem;
import lombok.Data;

@Data
public class OrderItemQueryDto {
    private String itemName;
    private int orderPrice;
    private int count;

    public OrderItemQueryDto(OrderItem item) {
        this.itemName = item.getItem().getTitle();
        this.orderPrice = item.getPrice();
        this.count = item.getCount();
    }
}
