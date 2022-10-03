package models.entity;

import javax.persistence.*;

@NamedQueries({
	@NamedQuery(
			name="Goods.list",
			query = "SELECT g FROM Goods g ORDER BY g.regDt DESC"
	)
})
public class Goods extends BaseEntity {
	
	@Id @GeneratedValue
	private Long goodsNo; 
	@Column(nullable = false)
	
	private String goodsNm;
	private Long goodsPrice;
	
	public Long getGoodsNo() {
		return goodsNo;
	}
	
	public void setGoodsNo(Long goodsNo) {
		this.goodsNo = goodsNo;
	}
	
	public String getGoodsNm() {
		return goodsNm;
	}
	
	public void setGoodsNm(String goodsNm) {
		this.goodsNm = goodsNm;
	}
	
	public Long getGoodsPrice() {
		return goodsPrice;
	}
	
	public void setGoodsPrice(Long goodsPrice) {
		this.goodsPrice = goodsPrice;
	}
}
