<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:bundle basename="omis.physicalfeature.msgs.physicalfeature">
	<c:forEach var="physicalFeatureAssociation" items="${physicalFeatureSummaries}" varStatus="status">
	
		<tr class="offenderPhysicalFeatureRow" id="offenderPhysicalFeatureRow${offenderPhysicalFeature.id}">
			<td>
				<a class="actionMenuItem physicalFeatureAssociationsRowActionMenuLink" id="physicalFeatureAssociationsRowActionMenuLink${status.index}" href="${pageContext.request.contextPath}/physicalFeature/physicalFeatureAssociaitonsRowItemActionMenu.html?physicalFeatureAssociation=${physicalFeatureAssociation.id}"></a>
			</td>
			<td>
				<c:out value="${physicalFeatureAssociation.featureClassificationName}"/>
			</td>
			<td>
				<c:out value="${physicalFeatureAssociation.physicalFeatureName}"/>
			</td>
			<td>
				<c:out value="${physicalFeatureAssociation.description}"/>
			</td>
			<td>
				<fmt:formatDate value="${physicalFeatureAssociation.date}" pattern="MM/dd/yyyy"/>
			</td>
		</tr>
	</c:forEach>
</fmt:bundle>