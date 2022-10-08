package models.orders.service;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import commons.SHA256;
import constants.OrderStatus;
import models.entity.*;

@Service
public class OrderProcessService {
	
	@Autowired
	private HttpServletRequest request;
	
	@Autowired
	private EntityManager em;
	
	public Map<String, String> process() {
		Map<String, String> resultMap = new HashMap<>();
		String resultCode = request.getParameter("resultCode");
		String resultMsg = request.getParameter("resultMsg");
		System.out.println(resultCode);
		System.out.println(resultMsg);
		
		if (resultCode == null || !resultCode.equals("0000")) {
			throw new RuntimeException("결제 실패하였습니다.");
		}
		
		long timestamp = System.currentTimeMillis();
		String mid = request.getParameter("mid");
		String charset = request.getParameter("charset");
		String authToken = request.getParameter("authToken");
		String authUrl = request.getParameter("authUrl");
		String qs = "authToken=" + authToken +  "&timestamp=" + timestamp;
		String signature = null;
		try {
			signature = SHA256.encrypt(qs);
		} catch (Exception e) {}
		
        try {
        	StringBuffer sb = new StringBuffer();
    		
    		sb.append("mid=");
    		sb.append(URLEncoder.encode(mid, "UTF-8")); 
    		sb.append("&authToken=");
    		sb.append(URLEncoder.encode(authToken, "UTF-8"));
            sb.append("&timestamp=");
            sb.append(URLEncoder.encode("" + timestamp, "UTF-8"));
            sb.append("&signature=");
            sb.append(URLEncoder.encode(signature, "UTF-8"));
            sb.append("&charset=");
            sb.append(URLEncoder.encode(charset, "UTF-8"));
            sb.append("&format=JSON");
	        String params = sb.toString();
	        System.out.println(params);
	        
	        String contentType = "application/x-www-form-urlencoded";
	       URLEncoder.encode( params, "UTF-8" ); 
	        URL url = new URL(authUrl);
	        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	        conn.setDoOutput(true);
	        conn.setDoInput(true);
	        conn.setRequestMethod("POST");
	        conn.setRequestProperty( "Content-Type", contentType);
	        conn.setRequestProperty( "Content-Length", String.valueOf(params.length()));
	        OutputStream os = conn.getOutputStream();
	        os.write(params.getBytes());
	    	StringBuffer sb2 = new StringBuffer();
	        try (InputStream in = conn.getInputStream();
	              BufferedReader br = new BufferedReader(new InputStreamReader(in))) {
	           
	        	String line = null;
	            while ((line = br.readLine()) != null) {
	                sb2.append(line);
	            }
	        }
	        
	        String data = sb2.toString();
	        System.out.println("--------------- 응답 데이터 S -------------");
	        System.out.println(data);
	        System.out.println("--------------- 응답 데이터 E -------------");
	        
	        
	        String[] fields = { "resultCode", "resultMsg", "VACT_Date", "VACT_Name", "tid", "MOID", "payMethod" };
	        JsonElement element = JsonParser.parseString(data);
	        JsonObject object = element.getAsJsonObject();
	        
	        for (String field : fields) {
	        	JsonElement el = object.get(field);
	        	if (el == null) continue;
	        	
	        	resultMap.put(field, el.getAsString());
	        }
	        
	        String rc = resultMap.get("resultCode");
	        if (rc == null  || !rc.equals("0000")) {
	        	throw new RuntimeException("결제에 실패하였습니다.");
	        }
	        
	        if (rc.equals("0000")) {
	        	// 결제 성공한 경우 DB 처리 
	        	EntityTransaction tx = em.getTransaction();
	        	try {
		        	tx.begin();
		        	Long orderNo = Long.valueOf(resultMap.get("MOID"));
		        	Orders entity = em.find(Orders.class, orderNo);
		        	if (entity == null) {
		        		throw new RuntimeException("등록되지 않은 주문서 입니다.");
		        	}
		        	String payMethod = resultMap.get("payMethod");
		        	// 가상 계좌는 주문상태를 입금 요청으로 변경, 다른 결제 수단은 입금 확인으로 변경
		        	// 추가적으로 가상계좌는 응답 데이터 중 VACT_Num (가상계좌 번호) 항목을 따로 DB에 저장할 것
		        	OrderStatus orderStatus = payMethod.equals("VBank")?OrderStatus.REQUEST:OrderStatus.INCASH;
		        	entity.setOrderStatus(orderStatus);
		        	
		        	em.flush();
		        	tx.commit();
	        	} catch (Exception e) {
	        		e.printStackTrace();
	        		tx.rollback();
	        	}
	        }
	        
	        conn.disconnect();
        } catch (Exception e) {
        	e.printStackTrace();
        	
        }
        
        return resultMap;
	}
}