<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<sec:authorize access="isAnonymous()">
	<div class="jumbotron col-md-12">
		<h1>Congratulation!</h1>
		<p>You did it. This basic webapp is all set up now. Try to login
			as "admin" with password "admin".</p>
	</div>
</sec:authorize>
<!-- Bootstrap CSS Files -->
<link href="/resources/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<link href="/resources/bootstrap/css/bootstrap-theme.css"
	rel="stylesheet">
<link href="/resources/bootstrap/css/dataTables.bootstrap.css"
	rel="stylesheet">

<!-- JQuery Plugin -->
<script type="text/javascript"
	src="https://code.jquery.com/jquery-2.2.4.js"></script>
<!-- Bootstrap Plugin -->
<script type="text/javascript"
	src="<c:url value="/resources/bootstrap/js/bootstrap.min.js" />"></script>
<!-- Data Table Plugin -->
<script type="text/javascript"
	src="<c:url value="/resources/bootstrap/js/jquery.dataTables.js" />"></script>
<!-- Data Table Bootstrap Plugin -->
<script type="text/javascript"
	src="<c:url value="/resources/bootstrap/js/dataTables.bootstrap.js" />"></script>
<!-- Pagination Plugin -->
<script type="text/javascript"
	src="<c:url value="/resources/bootstrap/js/jquery.simplePagination.js" />"></script>

<script>
	var messageTable;
	var filter;
	function dtConstruction() {
		messageTable = jQuery('#messageTable').dataTable({
			"processing" : true,
			"serverSide" : true,
			"paging" : true,
			"bFilter" : false,
			"bLengthChange" : false,
			"info" : true,
			"dom" : '<"top"iflp<"clear">>rt<"bottom"iflp<"clear">>',
			"deferRender" : true,
			"ajax" : {
				"url" : "datatable",
				"contentType" : "application/json",
				"data" : function(d) {
					d.type = filter;
				}
			},
			"dataSrc" : "",
			"columns" : [ {
				"data" : "applicationId"
			}, {
				"data" : "exceptionTimePrint"
			}, {
				"data" : "type"
			}, {
				"data" : "title"
			}, {
				"data" : "message"
			} ]
		});
	}
	jQuery(document).ready(function() {
		jQuery.each($(".date"), function(elem) {
			var date = new Date(elem.text());
			elem.text(date.toLocaleDateString());
		});

		//Invoke the dataTable
		filter = "";
		dtConstruction();

	});

	function filterCheckBoxSelection() {
		//build a regex filter string with an or(|) condition
		filter = jQuery('input:checkbox[name="type"]:checked').map(function() {
			return this.value;
		}).get().join('|');
		//reloads the dataTable with the new filtering options
		messageTable.api().ajax.reload();
	}
</script>
<sec:authorize access="isAuthenticated()">

	<!-- Table display -->

	<div class="row">
		<div class="col-xs-12">
			<b>Filter by Message Type:</b></br>
			<form>
				<div>
					<input onchange="filterCheckBoxSelection()" type="checkbox"
						name="type" value="ERROR|WARNING|INFO|DEBUG">All <input
						onchange="filterCheckBoxSelection()" type="checkbox" name="type"
						value="ERROR">Error <input
						onchange="filterCheckBoxSelection()" type="checkbox" name="type"
						value="WARNING">Warning <input
						onchange="filterCheckBoxSelection()" type="checkbox" name="type"
						value="DEBUG">Debug <input
						onchange="filterCheckBoxSelection()" type="checkbox" name="type"
						value="INFO">Info
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
			</table>
		</div>

	</div>

</sec:authorize>