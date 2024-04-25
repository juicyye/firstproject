package kozin.first.domain.service;

import kozin.first.domain.entity.Item;
import kozin.first.domain.entity.Member;
import kozin.first.domain.entity.Order;
import kozin.first.domain.entity.OrderItem;
import kozin.first.domain.repository.ItemRepository;
import kozin.first.domain.repository.MemberRepository;
import kozin.first.domain.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OrderService {
    private final MemberRepository memberRepository;
    private final ItemRepository itemRepository;
    private final OrderRepository orderRepository;

    @Transactional
    public Long order(Long memberId, Long itemId, int count) {
        Member member = memberRepository.findById(memberId).get();
        Item item = itemRepository.findById(itemId).get();

        OrderItem orderItem = OrderItem.createOrderItem(item, item.getPrice(), count);
        Order order = Order.createOrder(member, orderItem);

        orderRepository.save(order);
        return order.getId();
    }

    public List<Order> findAll(){
        return orderRepository.findAll();

    }

    @Transactional
    public void cancelOrder(Long OrderId){
        Order order = orderRepository.findById(OrderId).get();
        order.cancel();
    }


}
