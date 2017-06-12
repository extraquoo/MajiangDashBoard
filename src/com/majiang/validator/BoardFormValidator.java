package com.majiang.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.majiang.constant.Constants;
import com.majiang.model.Board;

@Component
public class BoardFormValidator implements Validator {


	@Override
	public boolean supports(Class<?> clazz) {
		return Board.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {

		Board board = (Board) target;

		if(board.getWinner()!=null && board.getWinner().length()!=0){
			
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "winType","NotEmpty.boardDetail.winType");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "handType", "NotEmpty.boardDetail.handType");
		if(board.getFlowers()==0){
    		errors.rejectValue("flowers", "NotEmpty.boardDetail.flowers");
         }
		if(Constants.DIAN_PAO.equals(board.getWinType())){
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "loser", "NotEmpty.boardDetail.loser");
		}
		else{
		
		}
		}

	}
}