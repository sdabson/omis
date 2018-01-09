<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:bundle basename="omis.dna.msgs.dna">
	<c:forEach var="dnaSample" items="${dnaSamples}" varStatus="status">
		<tr>
			<td>
				<a class="actionMenuItem" href="${pageContext.request.contextPath}/dna/dnaSamplesActionMenu.html?dnaSample=${dnaSample.id}"></a>
			</td>
			<td>
				<fmt:formatDate value="${dnaSample.date}" pattern="MM/dd/yyyy"/> 
			</td>
			<td>
				<fmt:formatDate type="time" timeStyle="short" value="${dnaSample.time}"/> 
			</td>
			<td>
				<c:out value="${dnaSample.collectionEmployee}"/>
			</td>
			<td>
				<c:out value="${dnaSample.witness}"/>
			</td>
			<td>
				<c:out value="${dnaSample.location}"/>
			</td>
			<td>
				<c:out value="${dnaSample.comment}"/>
			</td>
		</tr>
	</c:forEach>
</fmt:bundle>