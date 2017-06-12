<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<jsp:include page="../fragments/header.jsp" />

<body>

	<div class="container">

		<c:if test="${not empty msg}">
			<div class="alert alert-${css} alert-dismissible" role="alert">
				<button type="button" class="close" data-dismiss="alert" aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<strong>${msg}</strong>
			</div>
		</c:if>

		<h1><spring:message code ="gameList.gameList"></spring:message></h1>
<spring:url value="/games/setup" var="urlSetupGame" />
<button class="btn btn-success" onclick="location.href='${urlSetupGame}'"><spring:message code ="gameList.setupGame"></spring:message></button>
		<table class="table table-striped">
			<thead>
				<tr>
					<th><spring:message code ="gameList.gameId"></spring:message></th>
					<th><spring:message code ="gameList.playerOne"></spring:message></th>
					<th><spring:message code ="gameList.playerTwo"></spring:message></th>
					<th><spring:message code ="gameList.playerThree"></spring:message></th>
					<th><spring:message code ="gameList.playerFour"></spring:message></th>
					<th><spring:message code ="gameList.boardCount"></spring:message></th>
					<th><spring:message code ="gameList.startDate"></spring:message></th>
					<th><spring:message code ="gameList.endDate"></spring:message></th>
				</tr>
			</thead>

			<c:forEach var="gameBoards" items="${gameBoards}">
				<tr>
					<td>
						${gameBoards.id}
					</td>
					<td>${gameBoards.playerOne}</td>
					<td>${gameBoards.playerTwo}</td>
					<td>${gameBoards.playerThree}</td>
					<td>${gameBoards.playerFour}</td>
					<td>${gameBoards.countBoards}</td>
					<td><fmt:formatDate value="${gameBoards.startDate}" type="both"  pattern="MM-dd-yyyy HH:mm:ss" /></td>
					<td><fmt:formatDate value="${gameBoards.endDate}" type="both"  pattern="MM-dd-yyyy HH:mm:ss" /></td>
					<td align="right">
						<spring:url value="/games/${gameBoards.id}" var="gameUrl" />
						<spring:url value="/games/${gameBoards.id}/start" var="startUrl" />
                        <spring:url value="/games/${gameBoards.id}/update" var="updateUrl" />
						<button class="btn btn-info" onclick="location.href='${gameUrl}'"><spring:message code ="button.query"></spring:message></button>		
					    <button class="btn btn-primary" onclick="location.href='${updateUrl}'"><spring:message code ="button.update"></spring:message></button>
					<c:choose>
					<c:when test="${empty gameBoards.startDate && empty gameBoards.endDate}">
					    <button class="btn btn-default" onclick="location.href='${startUrl}'"><spring:message code ="button.start"></spring:message></button>
					</c:when>
					 	<c:when test="${not empty gameBoards.startDate && empty gameBoards.endDate}">
					    <button class="btn btn-default" onclick="location.href='${startUrl}'"><spring:message code ="button.reload"></spring:message></button>
					</c:when>
					 	<c:when test="${not empty gameBoards.startDate && not empty gameBoards.endDate}">
					 	<b><spring:message code ="text.end">&nbsp;&nbsp;&nbsp;</spring:message></b>
					</c:when>					
				</c:choose>
						</td>
				</tr>
			</c:forEach>
		</table>

	</div>

	<jsp:include page="../fragments/footer.jsp" />

</body>
</html>