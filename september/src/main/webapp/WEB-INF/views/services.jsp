<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sec"
    uri="http://www.springframework.org/security/tags"%>

 <c:forEach items="${groups}" var="group">
 <div class="panel panel-default">
  <div class="panel-heading"> ${group.groupName}</div>
  <div class="panel-body">
    <table class="table">
    <c:forEach items="${group.instances}" var="instance">
        <tr><td>
            <span class="label label-primary">${instance.instanceName}</span> ${instance.instanceUrl} 
            <span class="pull-right">
            <c:if test="${instance.status == 'OK'}">
                <span class="label label-success">OK</span>
            </c:if>
            <c:if test="${instance.status == 'ERROR'}">
                <span class="label label-danger">ERROR</span>
            </c:if>
            </span>
        </td></tr>
    </c:forEach>
    </table>
  </div>
</div>
 </c:forEach>