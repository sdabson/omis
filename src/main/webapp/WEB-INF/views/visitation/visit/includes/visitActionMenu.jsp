<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<fmt:bundle basename="omis.visitation.msgs.visitation">
	<ul>
		<c:choose>
			<c:when test="${not empty offender}">
				<li>
					<a class="listLink" href="${pageContext.request.contextPath}/visitation/list.html?offender=${offender.id}"><span class="visibleLinkLabel"><fmt:message key="offenderVisitationListLinkLabel"/></span></a>
				</li>
				<input type="hidden" id="offender" value="${offender.id}"/>
			</c:when>
			<c:otherwise>
				<li>
					<a class="listLink" href="${pageContext.request.contextPath}/visitation/index.html"><span class="visibleLinkLabel">
						<fmt:message key="FacilitiesVisitationIndexLink"/></span></a>
				</li>
				<input type="hidden" id="faciltiy" value="${facility.id}"/>
			</c:otherwise>
		</c:choose>
	</ul>
</fmt:bundle>