package models.orders.service;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import models.orders.OrderDao;
import models.orders.OrderDto;
import commons.SHA256;
import constants.OrderStatus;
import constants.PayMethod;

@Service
public class OrderRegisterService {
	
	@Autowired
	private OrderDao orderDao;
		
	@Autowired
	private HttpServletRequest request;
	
	public Map<String, String> process(OrderDto param) {
				
		Map<String, String> payData = new HashMap<>();
		
		param.setOrderStatus(OrderStatus.PREPARE);
		OrderDto orderDto = orderDao.register(param);
		long orderNo = orderDto.getOrderNo();
		long timestamp = System.currentTimeMillis();
		int price = 1000;
		String mid = "INIpayTest";
		String signKey = "SU5JTElURV9UUklQTEVERVNfS0VZU1RS";
		String qs = "oid="+ orderNo + "&price=" + price + "&timestamp=" + timestamp;
		
		String mKey = null;
		String signature = null;
		String domain = request.getRequestURL().toString().replace(request.getRequestURI(),"") + request.getContextPath();
		String returnUrl = domain + "/order/callback";
		String closeUrl = domain + "/order/close";
		try {
		mKey = SHA256.encrypt(signKey);
		signature = SHA256.encrypt(qs);
		
		} catch (Exception e) {}
		
		payData.put("mid", mid);
		payData.put("mKey", mKey);
		payData.put("signature", signature);
		payData.put("orderNo", "" + orderNo);
		payData.put("signKey", signKey);
		payData.put("price","" + price);
		payData.put("timestamp", "" + timestamp);
		payData.put("returnUrl", returnUrl);
		payData.put("closeUrl", closeUrl);
		PayMethod payMethod = orderDto.getPayMethod();
		String pm = null;
		switch (payMethod) {
			case CARD :
				pm = "Card";
				break;
			case DIRECTBANK :
				pm = "DirectBank";
				break;
			default : 
				pm = "VBank";
		}
		payData.put("payMethod", pm);
		return payData;
	}
	
	
	
}
