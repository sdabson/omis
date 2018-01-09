<%--
  - Table body content of identification numbers.
  -
  - Author: Stephen Abson
  --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:forEach var="identificationNumberSummary" items="${identificationNumberSummaries}">
	<tr>
		<td>
			<a href="${pageContext.request.contextPath}/identificationNumber/identificationNumbersActionMenu.html?identificationNumber=${identificationNumberSummary.id}" class="actionMenuItem" id="identificationNumber${identificationNumberSummary.id}Link"></a>
		</td>
		<td>
			<c:out value="${identificationNumberSummary.issuerName}"/>
		</td>
		<td>
			<c:out value="${identificationNumberSummary.categoryName}"/>
		</td>
		<td>
			<c:out value="${identificationNumberSummary.value}"/>
		</td>
		<td>
			<fmt:formatDate value="${identificationNumberSummary.issueDate}" pattern="MM/dd/yyyy"/>
		</td>
		<td>
			<fmt:formatDate value="${identificationNumberSummary.expireDate}" pattern="MM/dd/yyyy"/>
		</td>
	</tr>
</c:forEach>