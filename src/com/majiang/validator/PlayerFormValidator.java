package com.majiang.validator;

import java.math.BigDecimal;

import org.apache.commons.validator.routines.BigDecimalValidator;
import org.apache.commons.validator.routines.CurrencyValidator;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.majiang.model.Player;

@Component
public class PlayerFormValidator implements Validator {


	@Override
	public boolean supports(Class<?> clazz) {
		return Player.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {

		Player player = (Player) target;

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "NotEmpty.playerForm.name");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "walletAmount", "NotEmpty.playerForm.amount");

		 BigDecimalValidator validator = CurrencyValidator.getInstance();

	     BigDecimal amount = validator.validate(String.valueOf(player.getWalletAmount()));

	     if(amount != null && amount.toString().length()>7){
	    		errors.rejectValue("walletAmount", "Invalid.Amount");
	     }

	}

}