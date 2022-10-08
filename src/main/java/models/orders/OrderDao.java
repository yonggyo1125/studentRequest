package models.orders;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import models.entity.Orders;

@Component
public class OrderDao {
	/**
	 * 주문등록 
	 */
	@Autowired
	private EntityManager em;
	
	public OrderDto register(OrderDto param) {
		Orders entity = OrderDto.toEntity(param);
		
		em.persist(entity);
		
		return get(entity.getOrderNo());
		
	}
	
	/**
	 * 주문 조회
	 * 
	 * @param id
	 * @return
	 */
	public OrderDto get(Long id) {
		Orders order = em.find(Orders.class, id);
		
		return OrderDto.toDto(order);		
	}
}
