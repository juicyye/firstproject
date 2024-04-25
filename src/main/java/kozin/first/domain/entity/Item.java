package kozin.first.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
/**
 * 책방이라고 생각
 */
public class Item {
    @Id @GeneratedValue
    @Column(name = "item_id")
    private Long id;

    private String title;
    private String author;
    private Integer price;
    private Integer stockQuantity;

    public void addQuantity(Integer quantity) {
        this.stockQuantity += quantity;
    }

    public void removeQuantity(Integer quantity) {
        Integer result = this.stockQuantity - quantity;
        if(result < 0) {
            throw new IllegalArgumentException("수량 부족");
        }
        this.stockQuantity -= quantity;
    }


    public Item(String title, String author, int price, int stockQuantity) {
        this.title = title;
        this.author = author;
        this.price = price;
        this.stockQuantity = stockQuantity;
    }
}
