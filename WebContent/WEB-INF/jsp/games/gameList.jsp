<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

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

		<h1>All Games</h1>
<spring:url value="/games/setup" var="urlSetupGame" />
<button class="btn btn-success" onclick="location.href='${urlSetupGame}'">Setup a New Game</button>
		<table class="table table-striped">
			<thead>
				<tr>
					<th>Game ID</th>
					<th>Player One</th>
					<th>Player Two</th>
					<th>Player Three</th>
					<th>Player Four</th>
				</tr>
			</thead>

			<c:forEach var="game" items="${games}">
				<tr>
					<td>
						${game.id}
					</td>
					<td>${game.playerOne}</td>
					<td>${game.playerTwo}</td>
					<td>${game.playerThree}</td>
					<td>${game.playerFour}</td>
					<td align="right">
						<spring:url value="/games/${game.id}" var="gameUrl" />
						<spring:url value="/games/${game.id}/start" var="startUrl" />
                        <spring:url value="/games/${game.id}/update" var="updateUrl" />
						<button class="btn btn-info" onclick="location.href='${gameUrl}'">Detail</button>		
					    <button class="btn btn-primary" onclick="location.href='${updateUrl}'">Update</button>
					    <button class="btn btn-default" onclick="location.href='${startUrl}'">Start</button>
						</td>
				</tr>
			</c:forEach>
		</table>

	</div>

	<jsp:include page="../fragments/footer.jsp" />

</body>
</html>