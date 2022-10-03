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
