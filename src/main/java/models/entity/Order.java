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
