package com.majiang.controller;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.majiang.model.Board;
import com.majiang.model.DashBoard;
import com.majiang.model.Game;
import com.majiang.model.Player;
import com.majiang.service.BoardService;
import com.majiang.service.GameService;
import com.majiang.service.PlayerService;

@Controller
public class GameController {

	private final Logger logger = LoggerFactory.getLogger(PlayerController.class);

	@Autowired
	@Qualifier("gameService")
	private GameService gameService;
	
	@Autowired
	@Qualifier("playerService")
	private PlayerService playerService;
	
	@Autowired
	@Qualifier("boardService")
	private BoardService boardService;
	
	
	
	@RequestMapping(value = "/games", method = RequestMethod.GET)
	public String showAllgames(Model model) {
		model.addAttribute("games", gameService.findAllGames());
		return "games/gameList";

	}


	@RequestMapping(value = "/games", method = RequestMethod.POST)
	public String saveOrUpdateGame(@ModelAttribute("gameForm") @Validated Game game,
			BindingResult result, Model model, final RedirectAttributes redirectAttributes) {


		if (result.hasErrors()) {
			populateGameModel(model);
			return "games/gameForm";
		} else {

			redirectAttributes.addFlashAttribute("css", "success");
			
			redirectAttributes.addFlashAttribute("msg", "game updated successfully!");
			
			
			gameService.saveOrUpdate(game);
			
			// POST/REDIRECT/GET
			return "redirect:/games/" + game.getId();

			// POST/FORWARD/GET
			// return "player/list";

		}

	}
	
	@RequestMapping(value = "/games/{id}/boards")
	public String saveOrUpdateBoard(@ModelAttribute("newBoardForm") @Validated Board newBoard,
			@PathVariable int id,
			BindingResult result, Model model, final RedirectAttributes redirectAttributes) {

		Game game = gameService.findById(newBoard.getGame_id());
		if (result.hasErrors()) {			
			populateNewBoardModel(model, game);
			return "boards/boardDetail";
		} else {

			redirectAttributes.addFlashAttribute("css", "success");
			
			redirectAttributes.addFlashAttribute("msg", "board add successfully!");
			
			
			//boardService.saveGameBoard(newBoard);
           boardService.saveGameBoard(newBoard);
			id = newBoard.getGame_id();
			populateNewBoardModel(model, game);
			model.addAttribute("newBoardForm", new Board());
			List<DashBoard> dashBoards =populateDashBoard(newBoard);
			final GsonBuilder builder = new GsonBuilder();
		    final Gson gson = builder.create();	
			model.addAttribute("allBoards", gson.toJson(dashBoards));
			return "boards/boardDetail";
		}

	}
	

	@RequestMapping(value = "/games/setup", method = RequestMethod.GET)
	public String setupGame(Model model) {


		Game game = new Game();		
		model.addAttribute("gameForm", game);

		populateGameModel(model);

		return "games/gameForm";

	}

	
	@RequestMapping(value = "/games/{id}/update", method = RequestMethod.GET)
	public String showGameForm(@PathVariable("id") int id, Model model) {


		Game game = gameService.findById(id);
		model.addAttribute("gameForm", game);
		
		populateGameModel(model);
		
		return "games/gameForm";
	}
	

	@RequestMapping(value = "/games/{id}/start", method = RequestMethod.GET)
	public String showUpdateGameForm(@PathVariable("id") int id, Model model) {


		Game game = gameService.findById(id);
		populateNewBoardModel(model, game);
		
		List<Board> populateBoards = game.getBoards();
		model.addAttribute("BoardSheet", populateBoards);
		Board newBoard = new Board();
		model.addAttribute("newBoardForm", newBoard);
			
		return "boards/boardDetail";
	}
	
	
	@RequestMapping(value = "/games/{id}", method = RequestMethod.GET)
	public String showGame(@PathVariable("id") int id, Model model) {


		Game game = gameService.findById(id);
		if (game == null) {
			model.addAttribute("css", "danger");
			model.addAttribute("msg", "game not found");
		}
		model.addAttribute("game", game);

		return "games/gameDetail";

	}

	private void populateGameModel(Model model) {


		List<Player> playerList = playerService.findAll();	
		List<String> renderPlayerList = new ArrayList<String>();
		for(Player player : playerList){
			renderPlayerList.add(player.getName());
		}
		model.addAttribute("playerList", renderPlayerList);

	}
	
	
	private void populateNewBoardModel(Model model, Game game) {

		Map<String, String> boardPlayers = new LinkedHashMap<String, String>(4);
		boardPlayers.put("PlayerOne",game.getPlayerOne());
		boardPlayers.put("PlayerTwo",game.getPlayerTwo());
		boardPlayers.put("PlayerThree",game.getPlayerThree());
		boardPlayers.put("PlayerFour",game.getPlayerFour());
		
		model.addAttribute("boardPlayerList", boardPlayers);
		
		//populate Win type
		Map<String, String> handTypeOptions  = new LinkedHashMap<String, String>();
		
		handTypeOptions.put("HYS", "MixedColor");
		handTypeOptions.put("QYS", "SuitColor");
		handTypeOptions.put("PPH", "Allpair");
		handTypeOptions.put("HPH", "MixedSuitPair");
		handTypeOptions.put("QPH", "OneSuitPair");
		handTypeOptions.put("DDC", "Fishing");
		handTypeOptions.put("QFX", "AllWind");	
		handTypeOptions.put("QFP", "WindPair");
		model.addAttribute("handTypeOptions", handTypeOptions);
		//populate Hand type
		Map<String, String> winTypeOptions = new LinkedHashMap<String, String>();
		winTypeOptions.put("DP", "Blast");
		winTypeOptions.put("ZM", "SelfDraw");
		model.addAttribute("winTypeOptions", winTypeOptions);
		//populate flowers
		List<Integer> flowers = new ArrayList<Integer>(10);
		int maxFlowers = game.getMaxFlowers();
		for(int i=1; i<= maxFlowers;i++){
			flowers.add(i);			
		}
		model.addAttribute("flowers", flowers);

	}

	@ExceptionHandler(EmptyResultDataAccessException.class)
	public ModelAndView handleEmptyData(HttpServletRequest req, Exception ex) {

		logger.error("Request: {}, error ", req.getRequestURL(), ex);

		ModelAndView model = new ModelAndView();
		model.setViewName("games/gameDetail");
		model.addObject("msg", "game not found");

		return model;

	}

	private List<DashBoard> populateDashBoard(Board board){
		List<Board> allBoards = boardService.findAllBoards();
		List<DashBoard> dashBoards = new ArrayList<DashBoard>();
		
		for(Board currentBoard : allBoards){
			DashBoard dashBoard = new DashBoard();
			dashBoard.setId(currentBoard.getId());
			dashBoard.setWinner(currentBoard.getWinner());
			dashBoard.setComments(currentBoard.getComment());
			dashBoard.setEndDate(currentBoard.getBoardDate());
			dashBoards.add(dashBoard);
		}
		return dashBoards;
	}
}
