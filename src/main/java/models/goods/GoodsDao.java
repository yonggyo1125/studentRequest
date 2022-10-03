package models.goods;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;

import models.entity.Goods;

public class GoodsDao {
	
	@Autowired
	private EntityManager em;
	
	/**
	 * 상품 등록 
	 * @param param
	 */
	public GoodsDto register(GoodsDto param) {
		
		Goods entity = GoodsDto.toEntity(param);
		em.persist(entity);
		em.flush();
		
		Goods newEntity = em.find(Goods.class, entity.getGoodsNo());
		return GoodsDto.toDto(newEntity);
	}
	
	/** 
	 * 상품 조회
	 * 
	 */
	public GoodsDto get(Long goodsNo) {
		
		Goods entity = em.find(Goods.class, goodsNo);
		return GoodsDto.toDto(entity);
	}
	
	/**
	 * 상품 목록 
	 * 
	 */
	public List<GoodsDto> get(int page, int limit) {
		page = page <= 0 ? 1:page;
		limit = limit <= 0 ? 20:limit;
		int offset = (page - 1) * limit;
		TypedQuery<Goods> tq = em.createNamedQuery("Goods.list", Goods.class);
		tq.setFirstResult(offset).setMaxResults(limit);
		
		List<GoodsDto> goods = tq.getResultStream().map(GoodsDto::toDto).toList();
									
		return goods;
	}
}
