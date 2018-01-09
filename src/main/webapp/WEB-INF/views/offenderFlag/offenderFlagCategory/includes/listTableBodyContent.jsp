<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<fmt:bundle basename="omis.offenderflag.msgs.offenderFlag">
	<c:forEach var="offenderFlagCategory" items="${categories}" varStatus="status">
		<tr>
			<td>
				<a class="actionMenuItem rowActionMenuLinks"
					id="summaryActionMenuLink${status.index}"
					href="${pageContext.request.contextPath}/offenderFlagCategory/offenderFlagCategoriesRowActionMenu.html?offenderFlagCategory=${offenderFlagCategory.offenderFlagCategoryId}"></a>
				</td>
			<td>
				<c:out value="${offenderFlagCategory.name}"/>
			</td>
			<td>
				<c:out value="${offenderFlagCategory.description}"/>
			</td>
			<td>
				<c:out value="${offenderFlagCategory.required}"/>
			</td>
		</tr>
	</c:forEach> 
</fmt:bundle>