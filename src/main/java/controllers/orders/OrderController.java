package controllers.orders;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import constants.PayMethod;
import models.orders.OrderDto;
import models.orders.service.OrderProcessService;
import models.orders.service.OrderRegisterService;

@Controller
@RequestMapping("/order")
public class OrderController {
	
	@Autowired
	private OrderRegisterService service;
	
	@Autowired
	private OrderProcessService processService;
	
	@GetMapping
	public String form(Model model) {
		OrderDto orderDto = new OrderDto();
		orderDto.setPayMethod(PayMethod.VBANK);
		model.addAttribute("isPayProcess", false);
		model.addAttribute("orderDto", orderDto);
		
		return "order/form";
	}
	
	@PostMapping
	public String process(@Valid OrderDto orderDto, Errors errors, Model model) {
		
		boolean isPayProcess = true;
		
		if (errors.hasErrors()) {
			isPayProcess = false;
		}
		
		Map<String, String> payData = service.process(orderDto);
		
		model.addAllAttributes(payData);
		
		model.addAttribute("isPayProcess", isPayProcess);
		
		return "order/form";
	}
	
	@RequestMapping("/callback")
	public String callback() {
		try {
			processService.process();
			
		} catch (Exception e) {
			e.printStackTrace();
			return "redirect:/order/fail"; // 주문 실패 
		}
		
		return "redirect:/order/done"; // 주문 성공
	}
	
	@RequestMapping("/close")
	public String close() {
		
		return "order/close";
	}
	
	@GetMapping("/done")
	@ResponseBody
	public String done() {
	
		return "Done";
	}
	
	@GetMapping("/fail")
	@ResponseBody
	public String fail() {
	
		return "fail";
	}
	
	
}
