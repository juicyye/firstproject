package kozin.first.web.form;

import lombok.Data;

@Data
public class ItemForm {
    private String title;
    private String author;
    private Integer price;
    private Integer stockQuantity;
}
