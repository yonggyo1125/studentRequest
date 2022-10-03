# Entity

## 공통 Entity

```java
package models.entity;

import java.time.LocalDateTime;

import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@MappedSuperclass
public class BaseEntity {
	
	@CreationTimestamp
	private LocalDateTime regDt;
	
	@UpdateTimestamp
	private LocalDateTime modDt;
}
```

####  models.entity.Goods.java

```java 
package models.entity;

import javax.persistence.*;

@Entity
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

```

#### constants.PayMethod.java

```java
package constants;

public enum PayMethod {
	CARD, // 신용카드
	DIRECTBANK, // 실시간 계좌이체
	VBANK // 가상계좌
}

```

#### models.entity.Order.java

```java
package models.entity;

import javax.persistence.*;

import constants.PayMethod;

@Entity
public class Order extends BaseEntity {
	@Id @GeneratedValue
	private Long orderNo; // 주문번호
	private String orderer; // 주문자명
	private String orderEmail; // 주문자 이메일
	private String orderCellPhone; // 주문자 휴대전화
	private String receiver; // 받는사람명
	private String receiverCellPhone; // 받는사람 휴대전화
	private String receiverZonecode; // 받는사람 우편번호
	private String receiverAddress; // 받는사람 주소
	private String receiverAddressSub; // 받는사람 나머지 주소
	private String deliveryMemo; // 배송 요청사항
	
	@Enumerated(EnumType.STRING)
	private PayMethod payMethod; // 결제방식
	
	private Long payPrice; // 결제금액 
	
	@ManyToOne(fetch=FetchType.LAZY)
	private Goods goods; // 상품 정보
	
	public Long getOrderNo() {
		return orderNo;
	}
	
	public void setOrderNo(Long orderNo) {
		this.orderNo = orderNo;
	}
	
	public String getOrderer() {
		return orderer;
	}
	
	public void setOrderer(String orderer) {
		this.orderer = orderer;
	}
	
	public String getOrderEmail() {
		return orderEmail;
	}
	
	public void setOrderEmail(String orderEmail) {
		this.orderEmail = orderEmail;
	}
	
	public String getOrderCellPhone() {
		return orderCellPhone;
	}
	
	public void setOrderCellPhone(String orderCellPhone) {
		this.orderCellPhone = orderCellPhone;
	}
	
	public String getReceiver() {
		return receiver;
	}
	
	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}
	
	public String getReceiverCellPhone() {
		return receiverCellPhone;
	}
	
	public void setReceiverCellPhone(String receiverCellPhone) {
		this.receiverCellPhone = receiverCellPhone;
	}
	
	public String getReceiverZonecode() {
		return receiverZonecode;
	}
	
	public void setReceiverZonecode(String receiverZonecode) {
		this.receiverZonecode = receiverZonecode;
	}
	
	public String getReceiverAddress() {
		return receiverAddress;
	}
	
	public void setReceiverAddress(String receiverAddress) {
		this.receiverAddress = receiverAddress;
	}
	
	public String getReceiverAddressSub() {
		return receiverAddressSub;
	}
	
	public void setReceiverAddressSub(String receiverAddressSub) {
		this.receiverAddressSub = receiverAddressSub;
	}
	
	public String getDeliveryMemo() {
		return deliveryMemo;
	}
	
	public void setDeliveryMemo(String deliveryMemo) {
		this.deliveryMemo = deliveryMemo;
	}
	
	public Long getPayPrice() {
		return payPrice;
	}
	
	public void setPayPrice(Long payPrice) {
		this.payPrice = payPrice;
	}

	public PayMethod getPayMethod() {
		return payMethod;
	}

	public void setPayMethod(PayMethod payMethod) {
		this.payMethod = payMethod;
	}

	public Goods getGoods() {
		return goods;
	}

	public void setGoods(Goods goods) {
		this.goods = goods;
	}
}
```

* * * 

# DTO

## 공통 DTO

```java
package models;

import java.time.LocalDateTime;

public class BaseDto {
	
	protected LocalDateTime regDt;
	protected LocalDateTime modDt;
	
	public LocalDateTime getRegDt() {
		return regDt;
	}
	
	public void setRegDt(LocalDateTime regDt) {
		this.regDt = regDt;
	}
	
	public LocalDateTime getModDt() {
		return modDt;
	}
	
	public void setModDt(LocalDateTime modDt) {
		this.modDt = modDt;
	}
}
```

####  models.entity.Order.java

```java
package models.entity;

import javax.persistence.*;

@Entity
public class Order extends BaseEntity {
	@Id @GeneratedValue
	private Long orderNo; // 주문번호
	private String orderer; // 주문자명
	private String orderEmail; // 주문자 이메일
	private String orderCellPhone; // 주문자 휴대전화
	private String receiver; // 받는사람명
	private String receiverCellPhone; // 받는사람 휴대전화
	private String receiverZonecode; // 받는사람 우편번호
	private String receiverAddress; // 받는사람 주소
	private String receiverAddressSub; // 받는사람 나머지 주소
	private String deliveryMemo; // 배송 요청사항
	private Long payPrice; // 결제금액 
	
	@ManyToOne(fetch=FetchType.LAZY)
	private Goods goods; // 상품 정보
	
	public Long getOrderNo() {
		return orderNo;
	}
	
	public void setOrderNo(Long orderNo) {
		this.orderNo = orderNo;
	}
	
	public String getOrderer() {
		return orderer;
	}
	
	public void setOrderer(String orderer) {
		this.orderer = orderer;
	}
	
	public String getOrderEmail() {
		return orderEmail;
	}
	
	public void setOrderEmail(String orderEmail) {
		this.orderEmail = orderEmail;
	}
	
	public String getOrderCellPhone() {
		return orderCellPhone;
	}
	
	public void setOrderCellPhone(String orderCellPhone) {
		this.orderCellPhone = orderCellPhone;
	}
	
	public String getReceiver() {
		return receiver;
	}
	
	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}
	
	public String getReceiverCellPhone() {
		return receiverCellPhone;
	}
	
	public void setReceiverCellPhone(String receiverCellPhone) {
		this.receiverCellPhone = receiverCellPhone;
	}
	
	public String getReceiverZonecode() {
		return receiverZonecode;
	}
	
	public void setReceiverZonecode(String receiverZonecode) {
		this.receiverZonecode = receiverZonecode;
	}
	
	public String getReceiverAddress() {
		return receiverAddress;
	}
	
	public void setReceiverAddress(String receiverAddress) {
		this.receiverAddress = receiverAddress;
	}
	
	public String getReceiverAddressSub() {
		return receiverAddressSub;
	}
	
	public void setReceiverAddressSub(String receiverAddressSub) {
		this.receiverAddressSub = receiverAddressSub;
	}
	
	public String getDeliveryMemo() {
		return deliveryMemo;
	}
	
	public void setDeliveryMemo(String deliveryMemo) {
		this.deliveryMemo = deliveryMemo;
	}
	
	public Long getPayPrice() {
		return payPrice;
	}
	
	public void setPayPrice(Long payPrice) {
		this.payPrice = payPrice;
	}

	public Goods getGoods() {
		return goods;
	}

	public void setGoods(Goods goods) {
		this.goods = goods;
	}
}
```

## models.goods.GoodsDto.java

```java
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
```

## models.orders.OrderDto.java

```java
package models.orders;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import constants.PayMethod;
import models.entity.Goods;

public class OrderDto {
	private Long orderNo; // 주문번호
	
	@NotBlank(message="주문자명을 입력하세요.")
	private String orderer; // 주문자명
	@NotBlank(message="주문자 이메일을 입력하세요.")
	@Email(message="이메일 형식이 아닙니다.")
	private String orderEmail; // 주문자 이메일
	@NotBlank(message="휴대전화번호를 입력하세요.")
	private String orderCellPhone; // 주문자 휴대전화
	@NotBlank(message="받는사람명을 입력하세요.")
	private String receiver; // 받는사람명
	@NotBlank(message="받는사람 휴대전화번호를 입력하세요.")
	private String receiverCellPhone; // 받는사람 휴대전화
	@NotBlank(message="받는사람 우편번호를 입력하세요.")
	private String receiverZonecode; // 받는사람 우편번호
	@NotBlank(message="받는사람 주소를 입력하세요.")
	private String receiverAddress; // 받는사람 주소
	@NotBlank(message="받는사람 나머지 주소를 입력하세요.")
	private String receiverAddressSub; // 받는사람 나머지 주소
	private String deliveryMemo; // 배송 요청사항
	private Long payPrice; // 결제금액 
	
	private PayMethod payMethod; // 결제방식 
	
	private Goods goods; // 상품 정보
	
	public Long getOrderNo() {
		return orderNo;
	}
	
	public void setOrderNo(Long orderNo) {
		this.orderNo = orderNo;
	}
	
	public String getOrderer() {
		return orderer;
	}
	
	public void setOrderer(String orderer) {
		this.orderer = orderer;
	}
	
	public String getOrderEmail() {
		return orderEmail;
	}
	
	public void setOrderEmail(String orderEmail) {
		this.orderEmail = orderEmail;
	}
	
	public String getOrderCellPhone() {
		return orderCellPhone;
	}
	
	public void setOrderCellPhone(String orderCellPhone) {
		this.orderCellPhone = orderCellPhone;
	}
	
	public String getReceiver() {
		return receiver;
	}
	
	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}
	
	public String getReceiverCellPhone() {
		return receiverCellPhone;
	}
	
	public void setReceiverCellPhone(String receiverCellPhone) {
		this.receiverCellPhone = receiverCellPhone;
	}
	
	public String getReceiverZonecode() {
		return receiverZonecode;
	}
	
	public void setReceiverZonecode(String receiverZonecode) {
		this.receiverZonecode = receiverZonecode;
	}
	
	public String getReceiverAddress() {
		return receiverAddress;
	}
	
	public void setReceiverAddress(String receiverAddress) {
		this.receiverAddress = receiverAddress;
	}
	
	public String getReceiverAddressSub() {
		return receiverAddressSub;
	}
	
	public void setReceiverAddressSub(String receiverAddressSub) {
		this.receiverAddressSub = receiverAddressSub;
	}
	
	public String getDeliveryMemo() {
		return deliveryMemo;
	}
	
	public void setDeliveryMemo(String deliveryMemo) {
		this.deliveryMemo = deliveryMemo;
	}
	
	public Long getPayPrice() {
		return payPrice;
	}
	
	public void setPayPrice(Long payPrice) {
		this.payPrice = payPrice;
	}

	public PayMethod getPayMethod() {
		return payMethod;
	}

	public void setPayMethod(PayMethod payMethod) {
		this.payMethod = payMethod;
	}

	public Goods getGoods() {
		return goods;
	}

	public void setGoods(Goods goods) {
		this.goods = goods;
	}

	@Override
	public String toString() {
		return "OrderDto [orderNo=" + orderNo + ", orderer=" + orderer + ", orderEmail=" + orderEmail
				+ ", orderCellPhone=" + orderCellPhone + ", receiver=" + receiver + ", receiverCellPhone="
				+ receiverCellPhone + ", receiverZonecode=" + receiverZonecode + ", receiverAddress=" + receiverAddress
				+ ", receiverAddressSub=" + receiverAddressSub + ", deliveryMemo=" + deliveryMemo + ", payPrice="
				+ payPrice + ", payMethod=" + payMethod + ", goods=" + goods + "]";
	}
}
```

* * * 
