package kozin.first.api;

import kozin.first.domain.entity.Order;
import kozin.first.domain.repository.OrderApiRepository;
import kozin.first.domain.repository.dto.OrderQueryDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class OrderApiController {

    private final OrderApiRepository orderApiRepository;

    @GetMapping("/api/v3/orders")
    public List<OrderQueryDto> orderV3(){
        List<Order> orders = orderApiRepository.findAllWithItem();
        return orders.stream().map(OrderQueryDto::new).collect(Collectors.toList());
    }
}
