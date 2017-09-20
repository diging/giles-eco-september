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

<script>
$(function() {
    $.each($(".date"), function(elem) {
        var date = new Date(elem.text());
        elem.text(date.toLocaleDateString());
    });
 });
</script>
<sec:authorize access="isAuthenticated()">

<c:set var="prev" value="${currentPageValue-1 >= 0 ? currentPageValue-1 : 0 }" />
<c:set var="next" value="${currentPageValue+1 < totalPages ? currentPageValue+1 : totalPages-1 }" />

<nav aria-label="Page navigation" class="pull-right">
  <ul class="pagination">
    <li>
      <a href="<c:url value="?page=${prev}" />" aria-label="Previous">
        <span aria-hidden="true">&laquo;</span>
      </a>
    </li>
    <li <c:if test="${currentPageValue == 0}">class="active"</c:if> >
     <a href="<c:url value="?page= 0" />" label="1">
     <span aria-hidden="false"></span>
     1</a>
   </li>
<c:forEach begin="2" end="${totalPages-1}" varStatus="loop">
   <li <c:if test="${loop.index-1 == currentPageValue}">class="active"</c:if> ><a href="<c:url value="?page=${loop.index-1}" />">${loop.index}</a></li>
   </c:forEach> 
<li <c:if test="${currentPageValue == totalPages-1}">class="active"</c:if> >
      <a href="<c:url value="?page=${totalPages -1}" />" label="${totalPages}">
      <span aria-hidden="false"></span>
      ${totalPages}</a>
    </li>
   <li>
      <a href="<c:url value="?page=${next}" />" aria-label="Next">
        <span aria-hidden="true">&raquo;</span>
      </a>
    </li>
  </ul>
</nav>

<div class="clearfix"></div>
<c:forEach items="${messages}" var="msg">
<div class="panel panel-default" style="max-height: 200px; overflow-y: scroll;">
  <div class="panel-body">
    <span class="label label-primary"><spring:message code="appname.${msg.applicationId}" /></span> <span class="date2">${msg.exceptionTimePrint}</span>
    <p style="padding-bottom: 5px; border-bottom-width: 1px;border-bottom-color: #eaeaea; border-bottom-style: solid;">
    <c:if test="${msg.type == 'ERROR' }">
    <span class="label label-danger">Error</span> 
    </c:if>
    <c:if test="${msg.type == 'WARNING' }">
    <span class="label label-warning">Warning</span>
    </c:if>
    <c:if test="${msg.type == 'INFO' }">
    <span class="label label-info">Info</span>
    </c:if>
    
    <b>${msg.title}</b>
    </p>
    <p>${msg.message}</p>
    <p>${msg.stackTrace}</p>
  </div>
</div>
</c:forEach>

<nav aria-label="Page navigation" class="pull-right">
  <ul class="pagination">
    <li>
      <a href="<c:url value="?page=${prev}" />" aria-label="Previous">
        <span aria-hidden="true">&laquo;</span>
      </a>
    </li>
    <li <c:if test="${currentPageValue == 0}">class="active"</c:if> >
     <a href="<c:url value="?page= 0" />" label="1">
     <span aria-hidden="false"></span>
     1</a>
   </li>
<c:forEach begin="2" end="${totalPages-1}" varStatus="loop">
<li <c:if test="${loop.index-1 == currentPageValue}">class="active"</c:if> ><a href="<c:url value="?page=${loop.index-1}" />">${loop.index}</a></li>
</c:forEach>
   <li <c:if test="${currentPageValue == totalPages-1}">class="active"</c:if> >
      <a href="<c:url value="?page=${totalPages -1}" />" label="${totalPages}">
      <span aria-hidden="false"></span>
      ${totalPages}</a>
    </li>
   <li>
      <a href="<c:url value="?page=${next}" />" aria-label="Next">
        <span aria-hidden="true">&raquo;</span>
      </a>
    </li>
  </ul>
</nav>
</sec:authorize>