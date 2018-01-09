<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<fmt:bundle basename="omis.education.msgs.education">
	<tr id="achievementItemRow${achievementItemIndex}" class="achievementItemRow">
		<td>
			<a class="removeLink" id="removeAchievementLink${achievementItemIndex}" href="${pageContext.request.contextPath}/education/removeAchievement.html?educationTerm=${educationSummary.educationTermId}"><span class="linkLabel"><fmt:message key="removeLink" bundle="${commonBundle}"/></span></a>
			<input type="hidden" id="achievementId${achievementItemIndex}" name="achievementItems[${achievementItemIndex}].achievement" value="${achievementItem.achievement.id}"/>
			<form:errors path="achievementItems[${achievementItemIndex}].achievement" cssClass="error"/>
			<input type="hidden" id="achievementOperation${achievementItemIndex}" name="achievementItems[${achievementItemIndex}].operation" value="${achievementItem.operation}"/>
			<form:errors path="achievementItems[${achievementItemIndex}].operation" cssClass="error"/>
		</td>
		<td>
			<fmt:formatDate var="achievementDate" value="${achievementItem.date}" pattern="MM/dd/yyyy"/>
			<input type="text" class="date" name="achievementItems[${achievementItemIndex}].date" id="achievementItemDate${achievementItemIndex}" value="${achievementDate}"/>
			<form:errors path="achievementItems[${achievementItemIndex}].date" cssClass="error"/>
		</td>
		<td>
			<select name="achievementItems[${achievementItemIndex}].category">
				<jsp:include page="../../includes/nullOption.jsp"/>
				<c:forEach items="${achievementCategories}" var="achievementCategory">
					<c:set var="achievementCategoryId" value="${achievementCategory.id}"/>
					<option value="${achievementCategoryId}" ${achievementCategoryId == achievementItems[achievementItemIndex].category.id ? 'selected="selected"' : ''}>
						<c:out value="${achievementCategory.name}"/>
					</option>
				</c:forEach>
			</select>
			<form:errors path="achievementItems[${achievementItemIndex}].category" cssClass="error"/>
		
		</td>
		<td>
			<textarea rows="4" name="achievementItems[${achievementItemIndex}].description" id="achievementItems[${achievementItemIndex}].description" style="width: 95%"><c:out value="${achievementItem.description}"/></textarea>
			<form:errors path="achievementItems[${achievementItemIndex}].description" cssClass="error"/>
		</td> 
	</tr>
</fmt:bundle> 