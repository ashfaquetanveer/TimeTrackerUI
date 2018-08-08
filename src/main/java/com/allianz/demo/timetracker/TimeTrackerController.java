package com.allianz.demo.timetracker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TimeTrackerController {

	@Autowired
	private TimeTrackerService timeTrackerService;

	@RequestMapping("/")
	public String welcome() {
		return "welcome";
	}

	@RequestMapping("/getrecords")
	public String welcome(@ModelAttribute(name = "email") String email, @ModelAttribute(name = "start") String start,
			@ModelAttribute(name = "end") String end, Model model) {

		model.addAttribute("records", timeTrackerService.getData(email, start, end));
		return "welcome";
	}

	@RequestMapping("/saverecord")
	public String save(@ModelAttribute(name = "email") String email, @ModelAttribute(name = "start") String start,
			@ModelAttribute(name = "end") String end, Model model) {

		if (timeTrackerService.saveData(email, start, end)) {
			return "welcome";
		}
		return "welcome";
	}

}
