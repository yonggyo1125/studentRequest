package models.goods;

import javax.validation.constraints.NotBlank;

import models.BaseDto;

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
}
