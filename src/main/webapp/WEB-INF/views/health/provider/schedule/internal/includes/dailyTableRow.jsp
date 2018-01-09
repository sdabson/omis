<%--
 - Displays daily schedule for provider in a table row.
 -
 - Author: Stephen Abson
 --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<fmt:setBundle basename="omis.health.msgs.health" var="healthBundle"/>
<c:choose>
		<c:when test="${not empty internalProviderScheduleDayItem.startTime && not empty internalProviderScheduleDayItem.endTime}">
<tr>

	<td class="weekRange">
	
		<c:choose>
			<c:when test="${internalProviderScheduleDayItem.date eq scheduleDate}">
				<input type="radio" name="scheduleDate" class="scheduleDateSelector" id="scheduleDate${internalProviderScheduleDayItem.date.time}" value="<fmt:formatDate value='${internalProviderScheduleDayItem.date}' pattern='MM/dd/yyyy'/>" checked="checked">
			</c:when>
			<c:otherwise>
				<input type="radio" name="scheduleDate" class="scheduleDateSelector" id="scheduleDate${internalProviderScheduleDayItem.date.time}" value="<fmt:formatDate value='${internalProviderScheduleDayItem.date}' pattern='MM/dd/yyyy'/>">
			</c:otherwise>
		</c:choose>
		<label for="scheduleDate${internalProviderScheduleDayItem.date.time}">
			<fmt:formatDate value="${internalProviderScheduleDayItem.date}" pattern="E MM/dd/yyyy"/></label>
		
	</td>
	<td>
		 <c:if test="${internalProviderScheduleDayItem.scheduledApppointments > 0}">
			<c:out value="${internalProviderScheduleDayItem.scheduledApppointments}"/>
		 </c:if> 
	</td>
	<td>
		<fmt:formatDate value="${internalProviderScheduleDayItem.startTime}" pattern="h:mm a"/>
	</td>
	<td>
		<fmt:formatDate value="${internalProviderScheduleDayItem.endTime}" pattern="h:mm a"/>
	</td>
	
</tr>
</c:when>
	<c:otherwise>
		<tr class="disabled">
			<td>
			<input type="radio" name="scheduleDate" class="scheduleDateSelector" id="scheduleDate${internalProviderScheduleDayItem.date.time}" value="<fmt:formatDate value='${internalProviderScheduleDayItem.date}' pattern='MM/dd/yyyy'/>"  disabled="disabled">
			<label for="scheduleDate${internalProviderScheduleDayItem.date.time}">
			<fmt:formatDate value="${internalProviderScheduleDayItem.date}" pattern="E MM/dd/yyyy"/></label>
			</td>
			<td colspan="3"></td>
		</tr>
	</c:otherwise>
	</c:choose>