<%@ page session="false"%>
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

		<h1>All Players</h1>
<spring:url value="/players/add" var="urlAddPlayer" />
<button class="btn btn-success" onclick="location.href='${urlAddPlayer}'">Add Player</button>
		<table class="table table-striped">
			<thead>
				<tr>
					<th>#ID</th>
					<th>Player</th>
				</tr>
			</thead>

			<c:forEach var="play" items="${players}">
				<tr>
					<td>
						${play.id}
					</td>
					<td>${play.name}</td>
					<td align="right">
						<spring:url value="/players/${play.id}" var="playUrl" />
						<spring:url value="/players/${play.id}/delete" var="deleteUrl" /> 
						<spring:url value="/players/${play.id}/update" var="updateUrl" />

						<button class="btn btn-info" onclick="location.href='${playUrl}'">Query</button>
						<button class="btn btn-primary" onclick="location.href='${updateUrl}'">Update</button>
						<button class="btn btn-danger" onclick="location.href='${deleteUrl}'">Delete</button>
						</td>
				</tr>
			</c:forEach>
		</table>

	</div>

	<jsp:include page="../fragments/footer.jsp" />

</body>
</html>