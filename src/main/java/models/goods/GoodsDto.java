package models.goods;

import javax.validation.constraints.NotBlank;

import models.BaseDto;
import models.entity.Goods;

public class GoodsDto extends BaseDto {
	
	private Long goodsNo; // 상품번호
	
	@NotBlank(message="상품명을 입력하세요.")
	private String goodsNm; // 상품명 
	private Long goodsPrice; // 판매가
	
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

	@Override
	public String toString() {
		return "GoodsDto [goodsNo=" + goodsNo + ", goodsNm=" + goodsNm + ", goodsPrice=" + goodsPrice + ", regDt="
				+ regDt + ", modDt=" + modDt + "]";
	}
	
	public static Goods toEntity(GoodsDto goods) {
		if (goods == null) {
			return null;
		}
		
		Goods entity = new Goods();
		entity.setGoodsNo(goods.getGoodsNo());
		entity.setGoodsNm(goods.getGoodsNm());
		entity.setGoodsPrice(goods.getGoodsNo());
		
		return entity;
	}
	
	public static GoodsDto toDto(Goods entity) {
		if (entity == null) {
			return null;
		}
		
		GoodsDto goods = new GoodsDto();
		goods.setGoodsNo(entity.getGoodsNo());
		goods.setGoodsNm(entity.getGoodsNm());
		goods.setGoodsPrice(entity.getGoodsNo());
		goods.setRegDt(entity.getRegDt());
		goods.setModDt(entity.getModDt());
		
		return goods;
	}
}
