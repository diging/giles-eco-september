<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<sec:authorize access="isAnonymous()">
<div class="jumbotron col-md-12">
<h1>Congratulation!</h1>
<p>
You did it. This basic webapp is all set up now. Try to login as "admin" with password "admin".
</p>
</div>
</sec:authorize>
<!-- Bootstrap CSS Files -->
<link href="/resources/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<link href="/resources/bootstrap/css/bootstrap-theme.css" rel="stylesheet">
<link href="/resources/bootstrap/css/dataTables.bootstrap.css" rel="stylesheet">

<!-- JQuery Plugin -->
<script type="text/javascript" src="https://code.jquery.com/jquery-2.2.4.js"></script>
<!-- Bootstrap Plugin -->
<script type="text/javascript" src="<c:url value="/resources/bootstrap/js/bootstrap.min.js" />"></script>
<!-- Data Table Plugin -->
<script type="text/javascript" src="<c:url value="/resources/bootstrap/js/jquery.dataTables.js" />"></script>
<!-- Data Table Bootstrap Plugin -->
<script type="text/javascript" src="<c:url value="/resources/bootstrap/js/dataTables.bootstrap.js" />"></script>
<!-- Pagination Plugin -->
<script type="text/javascript" src="<c:url value="/resources/bootstrap/js/jquery.simplePagination.js" />"></script>

<script>
	var messageTable;
	jQuery(document).ready(function() {
		console.log("Ready");
		jQuery.each($(".date"), function(elem) {
			var date = new Date(elem.text());
			elem.text(date.toLocaleDateString());
		});
		//Testing for checkbox
			messageTable = jQuery('#messageTable').dataTable({
				"processing" : true,
				"serverSide" : true,
				"paging": true,
				"bFilter": false,
				"bLengthChange": false,
				"info" : true,
				"dom" : '<"top"iflp<"clear">>rt<"bottom"iflp<"clear">>',
				"deferRender": true,
				"ajax" : {
					"url": "/september/datatable",
					"contentType":"application/json"
				},
				//"fnServerParams": function (aoData) {
			     //   var includeUsuallyIgnored = $("#include-checkbox").is(":checked");
			      //  aoData.push({name: "includeUsuallyIgnored", value: includeUsuallyIgnored});
			  //  },
				"dataSrc": "",
				"columns" : [ 
					{ "data" : "applicationId" }, 
					{ "data" : "exceptionTimePrint" },
					{ "data" : "type" },
					{ "data" : "title" }, 
					{ "data" : "message" } ]
			});
			
	});
		
		function filterme() {
				 //build a regex filter string with an or(|) condition
				 var types = jQuery('input:checkbox[name="type"]:checked').map(
				 function() {
				 return  this.value;
				 }).get().join('|');
				 //alert (types);
				 //filter in column 2, with a regex, no smart filtering, no inputbox,not case sensitive
				 m = document.getElementById('messageTable');
				 console.log(m);
				console.log(m.fnFilter('info', 2).draw());
				 }
		</script>
<sec:authorize access="isAuthenticated()">

<!-- Table display -->

	<div class="row">
		<div class="col-xs-12">
			<b>Filter by Message Type:</b></br>
			<form>
			<div> 
			<input onchange="filterme()" type="checkbox" name="type" value="Error|Warning|Info">All 
			<input onchange="filterme()" type="checkbox" name="type" value="Error">Error
			<input onchange="filterme()" type="checkbox" name="type" value="Warning">Warning 
			<input onchange="filterme()" type="checkbox" name="type" value="Info">Info
			<hr>
			</div>
			</form>
			<table id="messageTable" class="table table-striped table-bordered">
				<thead>
					<tr>
						<th>App Name</th>
						<th>Exception Time</th>
						<th>Message Type</th>
						<th>Message Title</th>
						<th>Message</th>
					</tr>
				</thead>
				<tbody>
				
				</tbody>
				<!-- 
				<tbody>
					<div class="clearfix"></div>
					<c:forEach items="${messages}" var="msg" varStatus="loopTracker">  -->

						<!-- <div class="panel ${msg.type}" style="max-height: 200px; overflow-y: scroll;"> -->

						<!-- <div class="panel-body"> -->
				<!-- 		<tr>
							<td><span class="label label-primary"><spring:message
										code="appname.${msg.applicationId}" /></span></td>
							<td><span class="date2">${msg.exceptionTimePrint}</span></td>
							<td><c:if test="${msg.type == 'ERROR' }">
									<span class="label label-danger test">Error</span>
								</c:if> <c:if test="${msg.type == 'WARNING' }">
									<span class="label label-warning test">Warning</span>
								</c:if> <c:if test="${msg.type == 'INFO' }">
									<span class="label label-info test">Info</span>
								</c:if></td>
							<td><b>${msg.title}</b></td>

							<td>
								<p>${msg.message}</p>
								<p>${msg.stackTrace}</p>
							</td>
						</tr>  -->
				<!-- 	</c:forEach>  
				</tbody> -->
			</table>
		</div>

	</div>

</sec:authorize>