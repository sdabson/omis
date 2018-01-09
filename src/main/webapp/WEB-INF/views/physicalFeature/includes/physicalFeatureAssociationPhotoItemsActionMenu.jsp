<!-- 
 - Author: Joel Norris
 - Version: 0.1.0 (Aug  10, 2015)
 - Since: OMIS 3.0
 -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<fmt:bundle basename="omis.physicalfeature.msgs.physicalfeature">
	<ul>
		<sec:authorize access="hasRole('PHYSICAL_FEATURE_VIEW') or hasRole('ADMIN')">
			<li>
				<a href="${pageContext.request.contextPath}/physicalFeature/addPhysicalFeaturePhoto.html" class="createLink" id="addPhotoItem"><span class="visibleLinkLabel"><fmt:message key="offenderPhysicalFeatureAddPhotoLabel"/></span></a>
			</li>
		</sec:authorize>
		<li></li>
		<li></li>
	</ul>
</fmt:bundle>