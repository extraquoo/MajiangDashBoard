<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<link rel="stylesheet" href="https://cdn.datatables.net/1.10.15/css/jquery.dataTables.min.css">

<jsp:include page="../fragments/header.jsp" />
<script type="text/javascript" src="https://cdn.datatables.net/1.10.12/js/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
<script type="text/javascript">
$(document).ready(function (){
var data =eval('${allBoards}');
var table = $('#dashBoard').DataTable( {
"aaData": data,
"order": [[ 0, "desc" ]],
"aoColumns": [
{ "mData": "id"},
{ "mData": "winner"},
{ "mData": "comment"},
{ "mData": "enddate"}
]});
});

//Load the Visualization API and the piechart package.
google.charts.load('current', {'packages':['corechart']});
  
// Set a callback to run when the Google Visualization API is loaded.
google.charts.setOnLoadCallback(drawChart);
  
function drawChart() {
  var jsonData = eval('${poolStatistics}');
  var data = new google.visualization.DataTable();

  data.addColumn('number', 'board');
  data.addColumn('number', '${playerNames[0]}');
  data.addColumn('number', '${playerNames[1]}');
  data.addColumn('number', '${playerNames[2]}');
  data.addColumn('number', '${playerNames[3]}');

  for (var i = 0; i < jsonData.length; i++) {
      data.addRow([jsonData[i].id, jsonData[i].one, jsonData[i].two, jsonData[i].three, jsonData[i].four]);
  }

  var options = {
          title: 'Statistics',
          curveType: 'function',
          legend: { position: 'middle' },
          width :800,
          height: 400,
          hAxis: {
          title: 'board #',
          gridlines: {color: '#000000', count: jsonData.length}
          },
          vAxis: {
	          title: 'Volumn (dollars)',
	          scaleType: 'mirrorLog',
	          ticks: [-200,-50,-10, 0, 10, 50, 200]
	        }
        };

        var chart = new google.visualization.LineChart(document.getElementById('curve_chart'));

        chart.draw(data, options);
}
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

		<h1><spring:message code ="boardDetail.boardDetail"></spring:message></h1>
        <br/>
        <br/>
        <div class="col-lg-4">
    <spring:url value="/games/${id}/boards" var="newBoardActionUrl" />
    <spring:url value="/games/${id}/end" var="boardEndUrl" />	

	<form:form class="form-horizontal" modelAttribute="newBoardForm" action="${newBoardActionUrl}">
		<form:hidden path="id" />
        <form:hidden path="game_id" value="${id}" />   
		<spring:bind path="winner">
			<div class="form-group ${status.error ? 'has-error' : ''}">
				<label class="col-sm-5 control-label"><spring:message code ="boardDetail.winner"></spring:message></label>
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
				<label class="col-sm-5 control-label"><spring:message code ="boardDetail.handType"></spring:message></label>
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
				<label class="col-sm-5 control-label"><spring:message code ="boardDetail.winType"></spring:message></label>
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
				<label class="col-sm-5 control-label"><spring:message code ="boardDetail.flowerCount"></spring:message></label>
				<div class="col-sm-5">
					<form:select path="flowers" class="form-control">
					    <form:option value="0" label="--- Select ---" />
						<form:options items="${flowers}" />
					</form:select>
					<form:errors path="flowers" class="control-label" />
				</div>
			</div>
		</spring:bind>	
		
		<spring:bind path="clearDoor">
		  <div class="form-group ${status.error ? 'has-error' : ''}">
			<label class="col-sm-5 control-label"><spring:message code ="boardDetail.clearDoor"></spring:message></label>
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
				<label class="col-sm-5 control-label"><spring:message code ="boardDetail.contractOne"></spring:message></label>
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
				<label class="col-sm-5 control-label"><spring:message code ="boardDetail.contractTwo"></spring:message></label>
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
				<label class="col-sm-5 control-label"><spring:message code ="boardDetail.contractThree"></spring:message></label>
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
				<label class="col-sm-5 control-label"><spring:message code ="boardDetail.loser"></spring:message></label>
				<div class="col-sm-5">
					<form:select path="loser" class="form-control">
						<form:option value="" label="--- Select ---" />
						<form:options items="${boardPlayerList}" />
					</form:select>
					<form:errors path="loser" class="control-label" />
				</div>
			</div>
		</spring:bind>							
		<div class="btn-toolbar">
		<button type="submit" class="btn btn-primary pull-left"><spring:message code ="button.save"></spring:message></button>	
					
		</div>
	</form:form>
	<div style="padding-top:5px">
	
	<button class="btn btn-warning pull-left" onclick="location.href='${boardEndUrl}'"><spring:message code ="button.end"></spring:message></button>
    </div>
    </div>   
    <div class="col-lg-8">
    <div id="curve_chart" style="width:800; height:400">
    </div>
    </div>
    <div class="col-lg-12" style="padding-top:20px">
    
  <table id="dashBoard" class="display" style="width:100%" >
        <thead>
            <tr>
                <th><spring:message code ="boardDetail.boardSequence"></spring:message></th>
                <th><spring:message code ="boardDetail.winner"></spring:message></th>
                <th><spring:message code ="boardDetail.comments"></spring:message></th>
                <th><spring:message code ="boardDetail.endDate"></spring:message></th>
            </tr>
        </thead>
    </table>
    </div>	
       
</div>                  
	<jsp:include page="../fragments/footer.jsp" />

</body>
</html>