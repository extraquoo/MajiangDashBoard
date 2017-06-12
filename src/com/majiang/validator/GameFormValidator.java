package com.majiang.validator;

import java.math.BigDecimal;

import org.apache.commons.validator.routines.BigDecimalValidator;
import org.apache.commons.validator.routines.CurrencyValidator;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.majiang.model.Game;

@Component
public class GameFormValidator implements Validator {


	@Override
	public boolean supports(Class<?> clazz) {
		return Game.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {

		Game game = (Game) target;

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "baseAmount","NotEmpty.gameForm.baseAmount");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "flowerAmount", "NotEmpty.gameForm.flowerAmount");

		if(game.getPlayerOne()==null || game.getPlayerOne().length()==0){
			errors.rejectValue("playerOne", "NotEmpty.gameForm.playerOne");
		}
		if(game.getPlayerTwo()==null || game.getPlayerTwo().length()==0){
			errors.rejectValue("playerTwo", "NotEmpty.gameForm.playerTwo");
		}
		if(game.getPlayerThree()==null || game.getPlayerThree().length()==0){
			errors.rejectValue("playerThree", "NotEmpty.gameForm.playerThree");
		}
		if(game.getPlayerFour()==null || game.getPlayerFour().length()==0){
			errors.rejectValue("playerFour", "NotEmpty.gameForm.playerFour");
		}
		
		if(game.getPlayerOne().equals(game.getPlayerTwo())
				||game.getPlayerOne().equals(game.getPlayerThree())
				||game.getPlayerOne().equals(game.getPlayerFour())
				){
			errors.rejectValue("playerOne", "Unique.gameForm.player");
		}
		
		if(game.getPlayerTwo().equals(game.getPlayerThree())
				||game.getPlayerTwo().equals(game.getPlayerFour())){
			errors.rejectValue("playerTwo", "Unique.gameForm.player");
		}
		
		if(game.getPlayerThree().equals(game.getPlayerFour())){
			errors.rejectValue("playerThree", "Unique.gameForm.player");
		}
		

		 BigDecimalValidator validator = CurrencyValidator.getInstance();

	     BigDecimal flowerAmount = validator.validate(String.valueOf(game.getFlowerAmount()));

	     if(flowerAmount != null && flowerAmount.toString().length()>7){
	    		errors.rejectValue("flowerAmount", "Invalid.Amount");
	     }
	    
	     BigDecimal baseAmount = validator.validate(String.valueOf(game.getBaseAmount()));
	     if(baseAmount != null && baseAmount.toString().length()>7){
	    		errors.rejectValue("baseAmount", "Invalid.Amount");
	     }
	     
	     if(game.getMaxFlowers()==0){
	    		errors.rejectValue("maxFlowers", "NotEmpty.gameForm.maxFlowers");
	     }
	}

}