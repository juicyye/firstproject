package kozin.first.api;

import kozin.first.domain.entity.Order;
import kozin.first.domain.repository.OrderApiRepository;
import kozin.first.domain.repository.OrderRepository;
import kozin.first.domain.repository.dto.SimpleOrderDto;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/ap2")
@RequiredArgsConstructor
public class OrderSimpleApiController {
    private final OrderRepository orderRepository;
    private final OrderApiRepository orderApiRepository;

    @GetMapping("/v3/simple-orders")
    public List<SimpleOrderDto> orders3(){
        return orderApiRepository.findAllWithMember().stream().map(
                o -> new SimpleOrderDto(o.getId(), o.getMember().getName(), o.getOrderDate())
        ).collect(Collectors.toList());
    }

    @GetMapping("/v3/simple-orders")
    public List<SimpleOrderDto> orders4(){
        return orderApiRepository.findOrderDtos();
    }
}
