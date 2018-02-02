<%--
  - Employer search form.
  -
  - Author: Yidong Li
  --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<fmt:bundle basename="omis.employment.msgs.employment">
<form:form commandName = "chronologicalNoteFilterOptionsForm"  id="chronologicalNoteFilterOptionsForm" method="POST">
	<fieldset>
		<p>
			<label><fmt:message key="chronologicalNoteFilterOptionsLabel"/></label>
		</p>
		<p>
			<c:forEach var="category" items="${categories}" varStatus="status">
				<form:checkbox path="endActiveBedPlacement" id="endActiveBedPlacement" value="true"/>
				<form:label path="endActiveBedPlacement" class="fieldLabel"><fmt:message key="endActiveBedPlacementLabel"/></form:label>
				<form:input type="hidden" path="activeBedPlacement"/>
			</c:forEach>
		</p>
		<p class="buttons">
			<input type="submit" value="<fmt:message key="RefreshLabel"/>"/>
		</p>
	</fieldset>
</form:form>
</fmt:bundle>