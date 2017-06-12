<%@ page session="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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

	<h1><spring:message code ="playDetail.playerDetail"></spring:message></h1>
	<br />


	<div class="row">
		<label class="col-sm-2"><spring:message code ="playDetail.playerName"></spring:message></label>
		<div class="col-sm-10">${player.name}</div>
	</div>

     <div class="row">
		<label class="col-sm-2"><spring:message code ="playDetail.playerWallet"></spring:message></label>
		<div class="col-sm-10">
		<c:choose>
  <c:when test="${empty player.walletAmount}">$0.00</c:when>
  <c:otherwise><fmt:formatNumber value = "${player.walletAmount}"  type = "currency"/></c:otherwise>
   </c:choose>
	
		</div>
	</div>
	<br/>
	<br/>
	<div class="col-sm-offset-2 col-sm-10">
	<spring:url value="/players" var="playUrl" />
	<button class="btn btn-info pull-right" onclick="location.href='${playUrl}'"><spring:message code ="button.return"></spring:message></button>
	</div>
</div>

<jsp:include page="../fragments/footer.jsp" />

</body>
</html>