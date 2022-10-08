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
		entity.setPayMethod(order.getPayMethod());
		
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
		order.setPayMethod(entity.getPayMethod());
		
		return order;
	}
}