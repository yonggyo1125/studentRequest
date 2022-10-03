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
	protected LocalDateTime regDt;
	
	@UpdateTimestamp
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

####  models.entity.Goods.java

```java 
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

#### constants.OrderStatus.java

```java
package constants;

public enum OrderStatus {
	PROGRESS, // 주문 진행중 
	REQUEST, // 입금 요청 중 
	INCASH, // 입금확인
	PREPARE, // 상품준비중
	DELIVERY, // 배송중 
	ARRIVAL,  // 배송완료
	DONE, // 주문완료
	FAILED // 주문실패
}
```

#### models.entity.Order.java

```java
package models.entity;

import javax.persistence.*;

import constants.OrderStatus;
import constants.PayMethod;

@Entity
public class Order extends BaseEntity {
	@Id @GeneratedValue
	private Long orderNo; // 주문번호
	
	@Enumerated(EnumType.STRING)
	@Column(length=30, nullable=false)
	private OrderStatus orderStatus; // 주문상태
	
	@Column(length=40, nullable=false)
	private String orderer; // 주문자명
	@Column(length=100, nullable=false)
	private String orderEmail; // 주문자 이메일
	@Column(length=11, nullable=false)
	private String orderCellPhone; // 주문자 휴대전화
	@Column(length=40, nullable=false)
	private String receiver; // 받는사람명
	@Column(length=11, nullable=false)
	private String receiverCellPhone; // 받는사람 휴대전화
	@Column(length=6, nullable=false)
	private String receiverZonecode; // 받는사람 우편번호
	@Column(length=100, nullable=false)
	private String receiverAddress; // 받는사람 주소
	@Column(length=100, nullable=false)
	private String receiverAddressSub; // 받는사람 나머지 주소
	private String deliveryMemo; // 배송 요청사항
	
	@Enumerated(EnumType.STRING)
	@Column(length=30)
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
	
	public OrderStatus getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(OrderStatus orderStatus) {
		this.orderStatus = orderStatus;
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

import constants.OrderStatus;
import constants.PayMethod;

@Entity
public class Orders extends BaseEntity {
	@Id @GeneratedValue
	private Long orderNo; // 주문번호
	
	@Enumerated(EnumType.STRING)
	@Column(length=30, nullable=false)
	private OrderStatus orderStatus = OrderStatus.PROGRESS; // 주문상태
	
	@Column(length=40, nullable=false)
	private String orderer; // 주문자명
	@Column(length=100, nullable=false)
	private String orderEmail; // 주문자 이메일
	@Column(length=11, nullable=false)
	private String orderCellPhone; // 주문자 휴대전화
	@Column(length=40, nullable=false)
	private String receiver; // 받는사람명
	@Column(length=11, nullable=false)
	private String receiverCellPhone; // 받는사람 휴대전화
	@Column(length=6, nullable=false)
	private String receiverZonecode; // 받는사람 우편번호
	@Column(length=100, nullable=false)
	private String receiverAddress; // 받는사람 주소
	@Column(length=100, nullable=false)
	private String receiverAddressSub; // 받는사람 나머지 주소
	private String deliveryMemo; // 배송 요청사항
	
	@Enumerated(EnumType.STRING)
	@Column(length=30)
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
	
	public OrderStatus getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(OrderStatus orderStatus) {
		this.orderStatus = orderStatus;
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

## models.goods.GoodsDto.java

```java
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
```

## models.orders.OrderDto.java

```java
package models.orders;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import constants.OrderStatus;
import constants.PayMethod;
import models.BaseDto;
import models.entity.Goods;
import models.entity.Orders;

public class OrderDto extends BaseDto {
	private Long orderNo; // 주문번호
	private OrderStatus orderStatus; // 주문상태
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
	
	public OrderStatus getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(OrderStatus orderStatus) {
		this.orderStatus = orderStatus;
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
		return "OrderDto [orderNo=" + orderNo + ", orderStatus=" + orderStatus + ", orderer=" + orderer
				+ ", orderEmail=" + orderEmail + ", orderCellPhone=" + orderCellPhone + ", receiver=" + receiver
				+ ", receiverCellPhone=" + receiverCellPhone + ", receiverZonecode=" + receiverZonecode
				+ ", receiverAddress=" + receiverAddress + ", receiverAddressSub=" + receiverAddressSub
				+ ", deliveryMemo=" + deliveryMemo + ", payPrice=" + payPrice + ", payMethod=" + payMethod + ", goods="
				+ goods + ", regDt=" + regDt + ", modDt=" + modDt + "]";
	}
	
	public static Orders toEntity(OrderDto order) {
		if (order == null) {
			return null;
		}
		
		Orders entity = new Orders();
		entity.setOrderNo(order.getOrderNo());
		entity.setOrderStatus(order.getOrderStatus());
		entity.setOrderer(order.getOrderer());
		entity.setOrderEmail(order.getOrderEmail());
		entity.setOrderCellPhone(order.getOrderCellPhone());
		entity.setReceiver(order.getReceiver());
		entity.setReceiverCellPhone(order.getReceiverCellPhone());
		entity.setReceiverZonecode(order.getReceiverZonecode());
		entity.setReceiverAddress(order.getReceiverAddress());
		entity.setReceiverAddressSub(order.getReceiverAddressSub());
		entity.setGoods(order.getGoods());
		
		return entity;
	}
	
	public static OrderDto toDto(Orders entity) {
		if (entity == null) {
			return null;
		}
		
		OrderDto order = new OrderDto();
		order.setOrderNo(entity.getOrderNo());
		order.setOrderStatus(entity.getOrderStatus());
		order.setOrderer(entity.getOrderer());
		order.setOrderEmail(entity.getOrderEmail());
		order.setOrderCellPhone(entity.getOrderCellPhone());
		order.setReceiver(entity.getReceiver());
		order.setReceiverCellPhone(entity.getReceiverCellPhone());
		order.setReceiverZonecode(entity.getReceiverZonecode());
		order.setReceiverAddress(entity.getReceiverAddress());
		order.setReceiverAddressSub(entity.getReceiverAddressSub());
		order.setGoods(entity.getGoods());
		
		return order;
	}
}
```

* * * 
# DAO 

## models.entity.Goods.java

```
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
```

