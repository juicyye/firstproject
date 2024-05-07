package kozin.first.domain.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import kozin.first.domain.entity.Order;
import kozin.first.domain.entity.QMember;
import kozin.first.domain.entity.QOrder;
import kozin.first.domain.repository.dto.SimpleOrderDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static kozin.first.domain.entity.QMember.*;
import static kozin.first.domain.entity.QOrder.*;

@Repository
public class OrderApiRepository {
    private EntityManager em;
    private JPAQueryFactory queryFactory;

    public OrderApiRepository(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    public List<Order> findAllWithMember(){
        return queryFactory.select(order)
                .from(order)
                .join(order.member, member).fetchJoin()
                .fetch();
    }

    public List<SimpleOrderDto> findOrderDtos() {
        return queryFactory.select(Projections.fields(SimpleOrderDto.class,
                order.id,member.name,order.orderDate
                ))
                .from(order)
                .join(order.member, member)
                .fetch();

    }

    public List<Order> findAllWithItem() {
        return queryFactory.select(order)
                .from(order)
                .join(order.member, member).fetchJoin()
                .fetch();
    }
}
