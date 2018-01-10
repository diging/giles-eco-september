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

<!-- Pagination Plugin -->

<script type="text/javascript" src="<c:url value="/resources/bootstrap/js/jquery.simplePagination.js" />"></script>
<script>
jQuery(document).ready(function(){
	 jQuery.each($(".date"), function(elem) {
	        var date = new Date(elem.text());
	        elem.text(date.toLocaleDateString());
	    });
// Pagination plugin input values

	 jQuery(".pagination").pagination({
	        pages: ${totalPages},
	        cssStyle: 'light-theme',
	        hrefTextPrefix: '?page=',
	        	currentPage: ${currentPageValue + 1}  
	 }
	 );
	    });
</script>
<sec:authorize access="isAuthenticated()">

		 <form:form method="POST" commandName="filterMessage">
			<table>
	            <tr>
	                <td>Choose the Message Type:</td>
	                <td><form:checkboxes path="messageTypes" items="${messageTypes}" />
	                </td>
	            </tr>
	            <tr>
	                <td><input type="submit" name="submit" value="Submit"></td>
	            </tr>
	        </table>
	    </form:form>

<nav aria-label="Page navigation" class="pull-right">
<ul class="pagination"></ul>
</nav>

<div class="clearfix"></div>
<c:forEach items="${messages}" var="msg" varStatus = "loopTracker">
	<div class="panel ${msg.type}" style="max-height: 200px; overflow-y: scroll;">
	  <div class="panel-body">
	    <span class="label label-primary"><spring:message code="appname.${msg.applicationId}" /></span> <span class="date2">${msg.exceptionTimePrint}</span>
	    <p style="padding-bottom: 5px; border-bottom-width: 1px;border-bottom-color: #eaeaea; border-bottom-style: solid;">
	    <c:if test="${msg.type == 'ERROR' }">
	    <span class="label label-danger test">Error</span> 
	    </c:if>
	    <c:if test="${msg.type == 'WARNING' }">
	    <span class="label label-warning test">Warning</span>
	    </c:if>
	    <c:if test="${msg.type == 'INFO' }">
	    <span class="label label-info test">Info</span>
	    </c:if>
	    
	    <b>${msg.title}</b>
	    </p>
	    <p>${msg.message}</p>
	    <p>${msg.stackTrace}</p>
	  </div>
	</div>
</c:forEach>

<nav aria-label="Page navigation" class="pull-right">
<ul class="pagination"></ul>
</nav>
</sec:authorize>