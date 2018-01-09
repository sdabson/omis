<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<fmt:bundle basename="omis.physicalfeature.msgs.physicalfeature">
		<select id="feature" name="feature">
		<option value="">...</option>
		<c:forEach var="physicalFeature" items="${physicalFeatures}" varStatus="status">
			<c:choose>
				<c:when test="${empty featureClassificationName}">
					<c:set value="${featureClassificationName} ${physicalFeature.name}" var="physicalFeatureName"/>
				</c:when>
				<c:otherwise>
					<c:set value="${physicalFeature.name}" var="physicalFeatureName"/>
				</c:otherwise>
			</c:choose>
			<c:choose>
				<c:when test="${not empty physicalFeatureAssociationForm.feature and physicalFeatureAssociationForm.feature eq physicalFeature}">
					<option value="${physicalFeature.id}" selected="selected"><c:out value="${physicalFeatureName}"/></option>
				</c:when>
				<c:otherwise>
					<option value="${physicalFeature.id}"><c:out value="${physicalFeatureName}"/></option>
				</c:otherwise>
			</c:choose>
		</c:forEach>
		</select>
</fmt:bundle>
