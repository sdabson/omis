<%--
  - Snippet for total days calculation.
  -
  - Author: Stephen Abson
  --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setBundle basename="omis.sentence.msgs.sentence" var="sentenceBundle"/>
<fmt:message key="totalDaysLabel" bundle="${sentenceBundle}">
	<fmt:param>${totalDays}</fmt:param>
</fmt:message>