<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<link rel="stylesheet" href="https://cdn.datatables.net/1.10.15/css/jquery.dataTables.min.css">


<style>
.left{
width:400px;
position:relative;
float: left;
}
.right{
margin-left:200px;
}
</style>

<jsp:include page="../fragments/header.jsp" />

<script type="text/javascript">
$(document).ready(function (){
var data =eval('${allBoards}');
var table = $('#dashBoard').DataTable( {
"aaData": data,
"aoColumns": [
{ "mData": "id"},
{ "mData": "winner"},
{ "mData": "comment"},
{ "mData": "enddate"}
]
});
});
</script>
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

		<h1>Board Detail</h1>

        <div class="left">
    <spring:url value="/games/${id}/boards" var="newBoardActionUrl" />

	<form:form class="form-horizontal" modelAttribute="newBoardForm" action="${newBoardActionUrl}">
		<form:hidden path="id" />
        <form:hidden path="game_id" value="${id}" />   
		<spring:bind path="winner">
			<div class="form-group ${status.error ? 'has-error' : ''}">
				<label class="col-sm-5 control-label">Winner</label>
				<div class="col-sm-5">
					<form:select path="winner" class="form-control">
						<form:option value="" label="--- Select ---" />
						<form:options items="${boardPlayerList}" />					
					</form:select>
					<form:errors path="winner" class="control-label" />
				</div>
			</div>
		</spring:bind>
		
		<spring:bind path="handType">
			<div class="form-group ${status.error ? 'has-error' : ''}">
				<label class="col-sm-5 control-label">Hand Type</label>
				<div class="col-sm-5">
					<form:select path="handType" class="form-control">
						<form:option value="" label="--- Select ---" />
						<c:forEach var="item" items="${handTypeOptions}">
                        <form:option value="${item.key}">
                        <spring:message code="${item.value}" />
                        </form:option>
                      </c:forEach>
					</form:select>
					<form:errors path="handType" class="control-label" />
				</div>
			</div>
		</spring:bind>	
		
		<spring:bind path="winType">
			<div class="form-group ${status.error ? 'has-error' : ''}">
				<label class="col-sm-5 control-label">Win Type</label>
				<div class="col-sm-5">
					<form:select path="winType" class="form-control">
						<form:option value="" label="--- Select ---" />
						<c:forEach var="item" items="${winTypeOptions}">
                        <form:option value="${item.key}">
                        <spring:message code="${item.value}" />
                        </form:option>
                      </c:forEach>
					</form:select>
					<form:errors path="winType" class="control-label" />
				</div>
			</div>
		</spring:bind>	
		
		<spring:bind path="flowers">
			<div class="form-group ${status.error ? 'has-error' : ''}">
				<label class="col-sm-5 control-label">Flowers</label>
				<div class="col-sm-5">
					<form:select path="flowers" class="form-control">
						<form:options items="${flowers}" />
					</form:select>
					<form:errors path="flowers" class="control-label" />
				</div>
			</div>
		</spring:bind>	
		
		<spring:bind path="clearDoor">
		  <div class="form-group ${status.error ? 'has-error' : ''}">
			<label class="col-sm-5 control-label">Clear Door</label>
			<div class="col-sm-5">
				<div class="checkbox">
				  <label>
                  <form:checkbox path="clearDoor" id="clearDoor" />
				  </label>
				     <form:errors path="clearDoor" class="control-label" />
				</div>
			</div>
		  </div>
		</spring:bind>
		
		<spring:bind path="contractOne">
		 <div class="form-group ${status.error ? 'has-error' : ''}">
				<label class="col-sm-5 control-label">Contract One</label>
				<div class="col-sm-5">
					<form:select path="contractOne" class="form-control">
						<form:option value="" label="--- Select ---" />
						<form:options items="${boardPlayerList}" />
					</form:select>
					<form:errors path="contractOne" class="control-label" />
				</div>
			</div>
		</spring:bind>
		
		<spring:bind path="contractTwo">
		 <div class="form-group ${status.error ? 'has-error' : ''}">
				<label class="col-sm-5 control-label">Contract Two</label>
				<div class="col-sm-5">
					<form:select path="contractTwo" class="form-control">
						<form:option value="" label="--- Select ---" />
						<form:options items="${boardPlayerList}" />
					</form:select>
					<form:errors path="contractTwo" class="control-label" />
				</div>
			</div>
		</spring:bind>
		
		<spring:bind path="contractThree">
		 <div class="form-group ${status.error ? 'has-error' : ''}">
				<label class="col-sm-5 control-label">Contract Three</label>
				<div class="col-sm-5">
					<form:select path="contractThree" class="form-control">
						<form:option value="" label="--- Select ---" />
						<form:options items="${boardPlayerList}" />
					</form:select>
					<form:errors path="contractThree" class="control-label" />
				</div>
			</div>
		</spring:bind>
		
		
		<spring:bind path="loser">
			<div class="form-group ${status.error ? 'has-error' : ''}">
				<label class="col-sm-5 control-label">Loser</label>
				<div class="col-sm-5">
					<form:select path="loser" class="form-control">
						<form:option value="" label="--- Select ---" />
						<form:options items="${boardPlayerList}" />
					</form:select>
					<form:errors path="loser" class="control-label" />
				</div>
			</div>
		</spring:bind>							
		
		<div class="form-group">
			<div class="col-sm-offset-0 col-sm-5">			
		<button type="submit" class="btn-lg btn-primary pull-left">Save</button>			
			</div>
		</div>
	</form:form>
    </div>
       
    <div class="right">
   
    </div>		
  <table id="dashBoard" class="display" style="width:100%" >
        <thead>
            <tr>
                <th>Board id</th>
                <th>winner</th>
                <th>comment</th>
                <th>end Date</th>
            </tr>
        </thead>
    </table>
</div>                  
	<jsp:include page="../fragments/footer.jsp" />

</body>
</html>