package kozin.first.domain.repository;

import kozin.first.domain.entity.Order;
import kozin.first.web.form.OrderForm;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long>, OrderRepositoryCustom {
    @Override
    List<OrderForm> findAll(SearchCond searchCond);
}
