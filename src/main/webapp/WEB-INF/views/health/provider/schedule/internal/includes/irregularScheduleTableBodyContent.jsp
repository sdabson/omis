<%-- Author: Ryan Johns
   - Version: 0.1.0 (Jun 3, 2014)
   - Since OMIS 3.0 --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<fmt:bundle basename="omis.health.msgs.health">
	<tr class="irregularScheduleDayListItem <c:if test="${irregularScheduleDayItem.isRemoved}">hidden</c:if>"><td>
		<a class="removeLink" id="irregularScheduleDays${mindex}"></a>
		<input id="irregularScheduleDays${mindex}_irregularScheduleDaysItem" type="hidden" name="irregularScheduleDays[${mindex}].irregularScheduleDayItem" class='<c:if test="${empty irregularScheduleDayItem.irregularScheduleDayItem}">new</c:if>' value="${irregularScheduleDayItem.irregularScheduleDayItem.id}"/>
	</td><td>
		<input id="irregularScheduleDays${mindex}_day" name="irregularScheduleDays[${mindex}].day" type="text" value='<fmt:formatDate value="${irregularScheduleDayItem.day}" pattern="MM/dd/yyyy"/>' class="date"/>
		<form:errors path="irregularScheduleDays[${mindex}].day" class="error"/>
	</td><td>
		<input id="irregularScheduleDays${mindex}_startTime" name="irregularScheduleDays[${mindex}].startTime" type="text" value='<fmt:formatDate value="${irregularScheduleDayItem.startTime}" pattern="h:mm a"/>'  class="time" />
		<form:errors path="irregularScheduleDays[${mindex}].startTime" class="error"/>
	</td><td>
		<input id="irregularScheduleDays${mindex}_endTime" name="irregularScheduleDays[${mindex}].endTime" type="text" value='<fmt:formatDate value="${irregularScheduleDayItem.endTime}" pattern="h:mm a"/>' class="time"/>
		<form:errors path="irregularScheduleDays[${mindex}].endTime" class="error"/>
	</td></tr>
</fmt:bundle>