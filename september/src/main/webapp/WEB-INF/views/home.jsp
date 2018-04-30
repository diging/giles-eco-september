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

<script>
	var messageTable;
	var filter;
	function dtConstruction() {
		messageTable = jQuery('#messageTable').dataTable({
			"processing" : true,
			"serverSide" : true,
			"paging" : true,
			"ordering": false,
			"bFilter" : false,
			"bLengthChange" : false,
			"info" : true,
			"dom" : '<"top"iflp<"clear">>rt<"bottom"iflp<"clear">>',
			"deferRender" : true,
			"ajax" : {
				"url" : "<c:url value="/admin/messages" />",
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
				"data" : "type",
				"render": function ( data, type, row, meta ) {
				    if (data == 'ERROR') {
				        return '<span class="label label-danger">Error</span>';
				    }
				    if (data == 'INFO') {
				        return '<span class="label label-primary">Info</span>';
				    }
				    if (data == 'WARNING') {
				        return '<span class="label label-warning">Warning</span>';
				    }
				    if (data == 'DEBUG') {
                        return '<span class="label label-info">Debug</span>';
                    }
				    return '<span class="label label-default">' + data + '</span>';
			    }
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
						name="type" value="ERROR|WARNING|INFO|DEBUG"> All 
					<input style="margin-left: 10px;" onchange="filterCheckBoxSelection()" type="checkbox" name="type"
						value="ERROR"> <span class="label label-danger">Error</span> 
					<input style="margin-left: 10px;" onchange="filterCheckBoxSelection()" type="checkbox" name="type"
						value="WARNING"> <span class="label label-warning">Warning</span> 
					<input style="margin-left: 10px;" onchange="filterCheckBoxSelection()" type="checkbox" name="type"
						value="DEBUG"> <span class="label label-info">Debug</span> 
					<input style="margin-left: 10px;" onchange="filterCheckBoxSelection()" type="checkbox" name="type"
						value="INFO"> <span class="label label-primary">Info</span>
					<hr>
				</div>
			</form>
			<table id="messageTable" class="table table-striped table-bordered" data-page-length='${pageSize}'>
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