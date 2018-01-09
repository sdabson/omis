<%-- Author: Ryan Johns
   - Version: 0.1.0 (Jul 28, 2015)
   - Since: OMIS 3.0 --%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<fmt:bundle basename="omis.offender.msgs.offenderHeader">
<div id="offenderHeaderPhoto" class="offenderHeaderPhoto">
	<img src="${pageContext.request.contextPath}/offenderPhoto/displayProfilePhoto.html?offender=${offenderPhotoAssociationSummary.offenderId}&amp;contentType=image/jpg"
					class="viewableImage" alt="<fmt:message key='noPhotoFoundLabel'/>"/>
	<c:if test="${not empty offenderPhotoAssociationSummary.photoDate}">
		<div id="offenderHeaderPhotoDetails">
			<span id="offenderHeaderPhotoDate" class="offenderHeaderFieldLabel"><fmt:message key="photoDateLabel"/></span>
			<a href="${pageContext.request.contextPath}/offenderPhoto/list.html?offender=${offenderSummary.id}">
				<span class="offenderHeaderFieldValue"><fmt:formatDate value="${offenderPhotoAssociationSummary.photoDate}" pattern="MM/dd/yyyy"/></span>
			</a>
		</div>
	</c:if>
</div>
</fmt:bundle>
