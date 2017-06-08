<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html lang="en">

<jsp:include page="../fragments/header.jsp" />

<div class="container">

	<spring:url value="/games" var="gameActionUrl" />

	<form:form class="form-horizontal" method="post" modelAttribute="gameForm" action="${gameActionUrl}">

		<form:hidden path="id" />
            <tr>
			<spring:bind path="playerOne">
			<div class="form-group ${status.error ? 'has-error' : ''}">
				<label class="col-sm-2 control-label">Player One</label>
				<div class="col-sm-3">
					<form:select path="playerOne" class="form-control">
						<form:option value="NONE" label="--- Select ---" />
						<form:options items="${playerList}" />
					</form:select>
					<form:errors path="playerOne" class="control-label" />
				</div>
				<div class="col-sm-2"></div>
			</div>
		</spring:bind>
		
		<spring:bind path="playerOneMoneyPool">
			<div class="form-group ${status.error ? 'has-error' : ''}">
				<label class="col-sm-2 control-label">Player One Money Pool</label>
				<div class="col-sm-2">
					<form:input path="playerOneMoneyPool" type="text" class="form-control "
					 id="playerOneMoneyPool" size="10" maxlength="10"/>
					<form:errors path="playerOneMoneyPool" class="control-label" />
				</div>
			</div>			
			</spring:bind>	
			
		
		<spring:bind path="playerTwo">
			<div class="form-group ${status.error ? 'has-error' : ''}">
				<label class="col-sm-2 control-label">Player Two</label>
				<div class="col-sm-3">
					<form:select path="playerTwo" class="form-control">
						<form:option value="NONE" label="--- Select ---" />
						<form:options items="${playerList}" />
					</form:select>
					<form:errors path="playerTwo" class="control-label" />
				</div>
				<div class="col-sm-5"></div>
			</div>
		</spring:bind>	
		
		<spring:bind path="playerTwoMoneyPool">
			<div class="form-group ${status.error ? 'has-error' : ''}">
				<label class="col-sm-2 control-label">Player Two Money Pool</label>
				<div class="col-sm-2">
					<form:input path="playerTwoMoneyPool" type="text" class="form-control "
					 id="playerTwoMoneyPool" size="10" maxlength="10"/>
					<form:errors path="playerTwoMoneyPool" class="control-label" />
				</div>
			</div>			
			</spring:bind>	
			
		<spring:bind path="playerThree">
			<div class="form-group ${status.error ? 'has-error' : ''}">
				<label class="col-sm-2 control-label">Player Three</label>
				<div class="col-sm-3">
					<form:select path="playerThree" class="form-control">
						<form:option value="NONE" label="--- Select ---" />
						<form:options items="${playerList}" />
					</form:select>
					<form:errors path="playerThree" class="control-label" />
				</div>
				<div class="col-sm-2"></div>
			</div>
		</spring:bind>	
		
		<spring:bind path="playerThreeMoneyPool">
			<div class="form-group ${status.error ? 'has-error' : ''}">
				<label class="col-sm-2 control-label">Player Three Money Pool</label>
				<div class="col-sm-2">
					<form:input path="playerThreeMoneyPool" type="text" class="form-control " 
					id="playerThreeMoneyPool"  size="10" maxlength="10"/>
					<form:errors path="playerThreeMoneyPool" class="control-label" />
				</div>
			</div>			
			</spring:bind>	
			
		<spring:bind path="playerFour">
			<div class="form-group ${status.error ? 'has-error' : ''}">
				<label class="col-sm-2 control-label">Player Four</label>
				<div class="col-sm-3">
					<form:select path="playerFour" class="form-control">
						<form:option value="NONE" label="--- Select ---" />
						<form:options items="${playerList}" />
					</form:select>
					<form:errors path="playerFour" class="control-label" />
				</div>
				<div class="col-sm-2"></div>
			</div>
		</spring:bind>	
		
		<spring:bind path="playerFourMoneyPool">
			<div class="form-group ${status.error ? 'has-error' : ''}">
				<label class="col-sm-2 control-label">Player Four Money Pool</label>
				<div class="col-sm-2">
					<form:input path="playerFourMoneyPool" type="text" class="form-control " 
					id="playerFourMoneyPool"  size="10" maxlength="10"/>
					<form:errors path="playerFourMoneyPool" class="control-label" />
				</div>
			</div>			
			</spring:bind>	
			
			
		<spring:bind path="baseAmount">
			<div class="form-group ${status.error ? 'has-error' : ''}">
				<label class="col-sm-2 control-label">Base Amount</label>
				<div class="col-sm-2">
					<form:input path="baseAmount" type="text" class="form-control " 
					id="baseAmount"  size="10" maxlength="10"/>
					<form:errors path="baseAmount" class="control-label" />
				</div>
			</div>
		</spring:bind>
		
		<spring:bind path="flowerAmount">
			<div class="form-group ${status.error ? 'has-error' : ''}">
				<label class="col-sm-2 control-label">Flower Amount</label>
				<div class="col-sm-2">
					<form:input path="flowerAmount" type="text" class="form-control " 
					id="flowerAmount"  size="10" maxlength="10"/>
					<form:errors path="flowerAmount" class="control-label" />
				</div>
			</div>
		</spring:bind>
		
			</tr>

            <div class="form-group">
			<div class="col-sm-offset-2 col-sm-10">
				
					<button type="submit" class="btn-lg btn-primary pull-right">Setup</button>			
			</div>
		</div>
	</form:form>

</div>

<jsp:include page="../fragments/footer.jsp" />

</body>
</html>