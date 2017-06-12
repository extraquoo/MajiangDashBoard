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

	<spring:url value="/players" var="playerActionUrl" />

	<form:form class="form-horizontal" method="post" modelAttribute="playerForm" action="${playerActionUrl}">

		<form:hidden path="id" />
    
						<spring:bind path="name">
			<div class="form-group ${status.error ? 'has-error' : ''}">
				<label class="col-sm-2 control-label"><spring:message code ="playFrom.playerName"></spring:message></label>
				<div class="col-sm-3">
					<form:input path="name" type="text" class="form-control " id="name"  />
					<form:errors path="name" class="control-label" />
				</div>
			</div>
			</spring:bind>
				<spring:bind path="walletAmount">
			<div class="form-group ${status.error ? 'has-error' : ''}">
				<label class="col-sm-2 control-label"><spring:message code ="playFrom.playerWallet"></spring:message></label>
				<div class="col-sm-3">
					<form:input path="walletAmount" type="number"  step="any" class="form-control " id="walletAmount" size="10" maxlength="10" />
					<form:errors path="walletAmount" class="control-label" />
				</div>
			</div>
		</spring:bind>
           <div class="btn-toolbar">			
				<c:choose>
					<c:when test="${id==0}">
					<button type="submit" class="btn btn-primary pull-right"><spring:message code ="button.add"></spring:message></button>
					</c:when>
					<c:otherwise>
					<button type="submit" class="btn btn-primary pull-right"><spring:message code ="button.update"></spring:message></button>			
					</c:otherwise>
				</c:choose>						
		</div>	
	</form:form>
	<div style="padding-top:5px">
	 <spring:url value="/players" var="playUrl" /> 
	     <button class="btn btn-info pull-right" onclick="location.href='${playUrl}'"><spring:message code ="button.cancel"></spring:message></button>
	     </div>
</div>

<jsp:include page="../fragments/footer.jsp" />

</body>
</html>