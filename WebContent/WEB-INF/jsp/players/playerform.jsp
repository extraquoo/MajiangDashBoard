<%@ page session="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html lang="en">

<jsp:include page="../fragments/header.jsp" />

<div class="container">

	<spring:url value="/players" var="playerActionUrl" />

	<form:form class="form-horizontal" method="post" modelAttribute="playerForm" action="${playerActionUrl}">

		<form:hidden path="id" />
    
						<spring:bind path="name">
			<div class="form-group ${status.error ? 'has-error' : ''}">
				<label class="col-sm-2 control-label">Player Name</label>
				<div class="col-sm-10">
					<form:input path="name" type="text" class="form-control " id="name" placeholder="name" />
					<form:errors path="name" class="control-label" />
				</div>
			</div>
			</spring:bind>
				<spring:bind path="wallet.amount">
			<div class="form-group ${status.error ? 'has-error' : ''}">
				<label class="col-sm-2 control-label">Wallet Amount</label>
				<div class="col-sm-10">
					<form:input path="wallet.amount" type="text" class="form-control " id="amount" placeholder="amount" />
					<form:errors path="wallet.amount" class="control-label" />
				</div>
			</div>
		</spring:bind>

            <div class="form-group">
			<div class="col-sm-offset-2 col-sm-10">
				<c:choose>
					<c:when test="${newFlag}">
					<button type="submit" class="btn-lg btn-primary pull-right">Add</button>
					</c:when>
					<c:otherwise>
					<button type="submit" class="btn-lg btn-primary pull-right">Update</button>			
					</c:otherwise>
				</c:choose>
			</div>
		</div>
	</form:form>

</div>

<jsp:include page="../fragments/footer.jsp" />

</body>
</html>