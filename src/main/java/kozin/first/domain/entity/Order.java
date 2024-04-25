package kozin.first.domain.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SequenceGenerator(name = "order_id", sequenceName = "order_seq", initialValue = 1, allocationSize = 50)
@Table(name = "orders")
/**
 *주소 빼고 상태빼고 장바구니랑 멤버만 아는걸로
 */
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order_id")
    @Column(name = "order_id")
    private Long id;
    private LocalDateTime orderDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> orderItems = new ArrayList<>();

    //== 편의 메서드 ==//
    public void addOrderItem(OrderItem orderItem) {
        orderItems.add(orderItem);
        orderItem.setOrder(this);
    }

    //== 비즈니스 로직 ==//
    public static Order createOrder(Member member,OrderItem... orderItems) {
        Order order = new Order();
        order.setOrderDate(LocalDateTime.now());
        order.setMember(member);
        for (OrderItem item : orderItems) {
            order.addOrderItem(item);
        }
        return order;
    }

    public void cancel(){
        for (OrderItem orderItem : orderItems) {
            orderItem.cancel();
        }
    }

    public int getTotalPrice(){
        int totalPrice = 0;
        for (OrderItem orderItem : orderItems) {
            totalPrice += orderItem.getTotalPrice();
        }
        return totalPrice;
    }


}
