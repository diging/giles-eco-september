<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>



<sec:authorize access="isAnonymous()">
<div class="jumbotron col-md-12">
<h1>Congratulation!</h1>
<p>
You did it. This basic webapp is all set up now. Try to login as "admin" with password "admin".
</p>
</div>
</sec:authorize>

<script>
$(function() {
    $.each($(".date"), function(elem) {
        var date = new Date(elem.text());
        elem.text(date.toLocaleDateString());
    });
 });
</script>
<sec:authorize access="isAuthenticated()">
<c:forEach items="${messages}" var="msg">
<div class="panel panel-default" style="max-height: 200px; overflow-y: scroll;">
  <div class="panel-body">
    <span class="date2">${msg.exceptionTimePrint}</span>
	<p style="padding-bottom: 5px; border-bottom-width: 1px;border-bottom-color: #eaeaea; border-bottom-style: solid;">
	<c:if test="${msg.type == 'ERROR' }">
    <span class="label label-danger">Error</span> 
    </c:if>
    <b>${msg.message}</b>
	</p>
	<p>${msg.stackTrace}</p>
  </div>
</div>



</c:forEach>
</sec:authorize>