package controllers;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import models.calendar.CalendarRequest;
import models.calendar.CalendarService;

@Controller
@RequestMapping("/")
public class MainController {
	
	@GetMapping
	public String main(CalendarRequest request, Model model) {
		
		Map<String, Object> data = new CalendarService().get(request);
		model.addAttribute("cal", data);
		
		return "main";
	}
}
