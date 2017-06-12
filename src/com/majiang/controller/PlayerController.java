package com.majiang.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.majiang.model.Player;
import com.majiang.service.PlayerService;
import com.majiang.validator.PlayerFormValidator;



@Controller
public class PlayerController {

	private final Logger logger = LoggerFactory.getLogger(PlayerController.class);

	@Autowired
	@Qualifier("playerService")
	private PlayerService playerService;

	@Autowired
	private PlayerFormValidator playerFormValidator;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index(Model model) {
		return "redirect:/players";
	}

	// list page
	@RequestMapping(value = "/players", method = RequestMethod.GET)
	public String showAllplayers(Model model) {
		model.addAttribute("players", playerService.findAll());
		return "players/list";

	}

	// save or update player
	@RequestMapping(value = "/players", method = RequestMethod.POST)
	public String saveOrUpdateplayer(@ModelAttribute("playerForm") Player player,
			BindingResult result, Model model, final RedirectAttributes redirectAttributes) {

		playerFormValidator.validate(player, result);
		if (result.hasErrors()) {
			populateDefaultModel(model);
			return "players/playerform";
		} else {

			redirectAttributes.addFlashAttribute("css", "success");
			
			redirectAttributes.addFlashAttribute("msg", "player updated successfully!");
			
			
			playerService.saveOrUpdate(player);
			
			// POST/REDIRECT/GET
			return "redirect:/players/" + player.getId();

			// POST/FORWARD/GET
			// return "player/list";

		}

	}

	// show add player form
	@RequestMapping(value = "/players/add", method = RequestMethod.GET)
	public String showAddplayerForm(Model model) {


		Player player = new Player();
		
		model.addAttribute("playerForm", player);

		populateDefaultModel(model);

		return "players/playerform";

	}

	// show update form
	@RequestMapping(value = "/players/{id}/update", method = RequestMethod.GET)
	public String showUpdateplayerForm(@PathVariable("id") int id, Model model) {


		Player player = playerService.findById(id);
		model.addAttribute("playerForm", player);
		
		//populateDefaultModel(model);
		
		return "players/playerform";

	}

	// delete player
	@RequestMapping(value = "/players/{id}/delete", method = RequestMethod.GET)
	public String deleteplayer(@PathVariable("id") int id, final RedirectAttributes redirectAttributes) {

		playerService.delete(id);
		
		redirectAttributes.addFlashAttribute("css", "success");
		redirectAttributes.addFlashAttribute("msg", "player is deleted!");
		
		return "redirect:/players";

	}

	// show player
	@RequestMapping(value = "/players/{id}", method = RequestMethod.GET)
	public String showplayer(@PathVariable("id") int id, Model model) {


		Player player = playerService.findById(id);
		if (player == null) {
			model.addAttribute("css", "danger");
			model.addAttribute("msg", "player not found");
		}
		model.addAttribute("player", player);

		return "players/playerDetail";

	}

	private void populateDefaultModel(Model model) {

//
//		Map<String, String> skill = new LinkedHashMap<String, String>();
//		skill.put("Hibernate", "Hibernate");
//		skill.put("Spring", "Spring");
//		skill.put("Struts", "Struts");
//		skill.put("Groovy", "Groovy");
//		skill.put("Grails", "Grails");
//		model.addAttribute("javaSkillList", skill);
//
//		List<Integer> numbers = new ArrayList<Integer>();
//		numbers.add(1);
//		numbers.add(2);
//		numbers.add(3);
//		numbers.add(4);
//		numbers.add(5);
//		model.addAttribute("numberList", numbers);
//
//		Map<String, String> country = new LinkedHashMap<String, String>();
//		country.put("US", "United Stated");
//		country.put("CN", "China");
//		country.put("SG", "Singapore");
//		country.put("MY", "Malaysia");
//		model.addAttribute("countryList", country);

	}

	@ExceptionHandler(EmptyResultDataAccessException.class)
	public ModelAndView handleEmptyData(HttpServletRequest req, Exception ex) {

		logger.error("Request: {}, error ", req.getRequestURL(), ex);

		ModelAndView model = new ModelAndView();
		model.setViewName("players/playerDetail");
		model.addObject("msg", "player not found");

		return model;

	}

}