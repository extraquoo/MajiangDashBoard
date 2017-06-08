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

	<h1>Player Detail</h1>
	<br />

	<div class="row">
		<label class="col-sm-2">ID</label>
		<div class="col-sm-10">${player.id}</div>
	</div>

	<div class="row">
		<label class="col-sm-2">Player Name</label>
		<div class="col-sm-10">${player.name}</div>
	</div>

     <div class="row">
		<label class="col-sm-2">Player Wallet</label>
		<div class="col-sm-10">
		<c:choose>
  <c:when test="${empty player.wallet.amount}">$0.00</c:when>
  <c:otherwise><fmt:formatNumber value = "${player.wallet.amount}"  type = "currency"/></c:otherwise>
   </c:choose>
	
		</div>
	</div>

</div>

<jsp:include page="../fragments/footer.jsp" />

</body>
</html>