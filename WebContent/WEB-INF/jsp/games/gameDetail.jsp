<%@ page session="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html lang="en">

<jsp:include page="../fragments/header.jsp" />

<div class="container">

	<c:if test="${not empty msg}">
		<div class="alert alert-${css} alert-dismissible" role="alert">
			<button type="button" class="close" data-dismiss="alert" aria-label="Close">
				<span aria-hidden="true">&times;</span>
			</button>
			<strong>${msg}</strong>
		</div>
	</c:if>

	<h1>Game Detail</h1>
	<br />

	<div class="row">
		<label class="col-sm-2">Game ID</label>
		<div class="col-sm-10">${game.id}</div>
	</div>
<br/>
	<br/>
	<div class="row">
		<label class="col-sm-2">Player One</label>
		<div class="col-sm-10">${game.playerOne}</div>
	</div>
	
	<div class="row">
		<label class="col-sm-2">Player One Money Pool</label>
		<div class="col-sm-10">${game.playerOneMoneyPool}</div>
	</div>
	
	<br/>
	<br/>
	<div class="row">
		<label class="col-sm-2">Player Two</label>
		<div class="col-sm-10">${game.playerTwo}</div>
	</div>
	
	<div class="row">
		<label class="col-sm-2">Player Two Money Pool</label>
		<div class="col-sm-10">${game.playerTwoMoneyPool}</div>
	</div>
	<br/>
	<br/>
	<div class="row">
		<label class="col-sm-2">Player Three</label>
		<div class="col-sm-10">${game.playerThree}</div>
	</div>
	
	<div class="row">
		<label class="col-sm-2">Player Three Money Pool</label>
		<div class="col-sm-10">${game.playerThreeMoneyPool}</div>
	</div>
	<br/>
	<br/>
	<div class="row">
		<label class="col-sm-2">Player Four</label>
		<div class="col-sm-10">${game.playerFour}</div>
	</div>
	
	<div class="row">
		<label class="col-sm-2">Player Four Money Pool</label>
		<div class="col-sm-10">${game.playerFourMoneyPool}</div>
	</div>
	
<br/>
	<br/>
     <div class="row">
		<label class="col-sm-2">Base Amount</label>
		<div class="col-sm-10">
		<c:choose>
  <c:when test="${empty game.baseAmount}">$0.00</c:when>
  <c:otherwise><fmt:formatNumber value = "${game.baseAmount}"  type = "currency"/></c:otherwise>
   </c:choose>
	
		</div>
	</div>

   <div class="row">
		<label class="col-sm-2">Flower Amount</label>
		<div class="col-sm-10">
		<c:choose>
  <c:when test="${empty game.flowerAmount}">$0.00</c:when>
  <c:otherwise><fmt:formatNumber value = "${game.flowerAmount}"  type = "currency"/></c:otherwise>
   </c:choose>
	
		</div>
	</div>


</div>

<jsp:include page="../fragments/footer.jsp" />

</body>
</html>