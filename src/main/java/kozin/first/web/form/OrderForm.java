package kozin.first.web.form;

import lombok.Data;

@Data
public class OrderForm {
    private String orderId;
    private String itemName;
    private String itemPrice;

    public OrderForm(String orderId, String itemName, String itemPrice) {
        this.orderId = orderId;
        this.itemName = itemName;
        this.itemPrice = itemPrice;
    }
}
