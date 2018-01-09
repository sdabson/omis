<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<fmt:bundle basename="omis.identificationnumber.msgs.identificationNumberCategory">
<c:forEach var="summary" items="${identificationNumberCategorySummaries}">
	<tr>
		<td>
			<a href="${pageContext.request.contextPath}/identificationNumber/category/identificationNumberCategoriesRowActionMenu.html?identificationNumberCategory=${summary.identificationNumberCategoryId}" class="rowActionMenuItem actionMenuItem" id="identificationNumber${summary.identificationNumberCategoryId}Link"></a>
		</td>
		<td>
			<c:out value="${summary.name}"/>
		</td>
		<td>
			<fmt:message key="${summary.valid}Label"/>
		</td>
	</tr>
</c:forEach>
</fmt:bundle>