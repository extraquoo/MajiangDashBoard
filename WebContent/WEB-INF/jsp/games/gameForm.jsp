<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
				<label class="col-sm-2 control-label"><spring:message code ="gameFrom.playerOne"></spring:message></label>
				<div class="col-sm-3">
					<form:select path="playerOne" class="form-control">
						<form:option value="" label="--- Select ---" />
						<form:options items="${playerList}" />
					</form:select>
					<form:errors path="playerOne" class="control-label" />
				</div>
				<div class="col-sm-2"></div>
			</div>
		</spring:bind>
					
		
		<spring:bind path="playerTwo">
			<div class="form-group ${status.error ? 'has-error' : ''}">
				<label class="col-sm-2 control-label"><spring:message code ="gameFrom.playerTwo"></spring:message></label>
				<div class="col-sm-3">
					<form:select path="playerTwo" class="form-control">
						<form:option value="" label="--- Select ---" />
						<form:options items="${playerList}" />
					</form:select>
					<form:errors path="playerTwo" class="control-label" />
				</div>
				<div class="col-sm-5"></div>
			</div>
		</spring:bind>	
		
		
			
		<spring:bind path="playerThree">
			<div class="form-group ${status.error ? 'has-error' : ''}">
				<label class="col-sm-2 control-label"><spring:message code ="gameFrom.playerThree"></spring:message></label>
				<div class="col-sm-3">
					<form:select path="playerThree" class="form-control">
						<form:option value="" label="--- Select ---" />
						<form:options items="${playerList}" />
					</form:select>
					<form:errors path="playerThree" class="control-label" />
				</div>
				<div class="col-sm-2"></div>
			</div>
		</spring:bind>	
		
		
			
		<spring:bind path="playerFour">
			<div class="form-group ${status.error ? 'has-error' : ''}">
				<label class="col-sm-2 control-label"><spring:message code ="gameFrom.playerFour"></spring:message></label>
				<div class="col-sm-3">
					<form:select path="playerFour" class="form-control">
						<form:option value="" label="--- Select ---" />
						<form:options items="${playerList}" />
					</form:select>
					<form:errors path="playerFour" class="control-label" />
				</div>
				<div class="col-sm-2"></div>
			</div>
		</spring:bind>	
			
		<c:if test="${id==0}">	
		<spring:bind path="baseAmount">
			<div class="form-group ${status.error ? 'has-error' : ''}">
				<label class="col-sm-2 control-label"><spring:message code ="gameFrom.baseAmount"></spring:message></label>
				<div class="col-sm-2">
					<form:input path="baseAmount" type="number" step="any" class="form-control " 
					id="baseAmount"  size="10" maxlength="10" />
					<form:errors path="baseAmount" class="control-label" />
				</div>
			</div>
		</spring:bind>
		
		<spring:bind path="flowerAmount">
			<div class="form-group ${status.error ? 'has-error' : ''}">
				<label class="col-sm-2 control-label"><spring:message code ="gameFrom.flowerAmount"></spring:message></label>
				<div class="col-sm-2">
					<form:input path="flowerAmount" type="number" step="any" class="form-control " 
					id="flowerAmount"  size="10" maxlength="10" />
					<form:errors path="flowerAmount" class="control-label" />
				</div>
			</div>
		</spring:bind>
		
			<spring:bind path="maxFlowers">
			<div class="form-group ${status.error ? 'has-error' : ''}">
				<label class="col-sm-2 control-label"><spring:message code ="gameFrom.maxFlowers"></spring:message></label>
				<div class="col-sm-2">
					<form:select path="maxFlowers" class="form-control">
						<form:option value="0" label="--- Select ---" />
						<form:options items="${flowers}" />
					</form:select>
					<form:errors path="maxFlowers" class="control-label" />
				</div>
				<div class="col-sm-2"></div>
			</div>
		</spring:bind>
		</c:if>	
			</tr>

            <div class="form-group">
			<div class="col-sm-offset-2 col-sm-10">
				<c:choose>
					<c:when test="${id==0}">
					<button type="submit" class="btn btn-primary pull-right"><spring:message code ="button.add"></spring:message></button>
					</c:when>
					<c:otherwise>
					<button type="submit" class="btn btn-primary pull-right"><spring:message code ="button.update"></spring:message></button>			
					</c:otherwise>
				</c:choose>			
		</div>
		</div>
	</form:form>
	 <spring:url value="/games" var="gameUrl" /> 
	 <button class="btn btn-info pull-right" onclick="location.href='${gameUrl}'"><spring:message code ="button.cancel"></spring:message></button>
</div>

<jsp:include page="../fragments/footer.jsp" />

</body>
</html>