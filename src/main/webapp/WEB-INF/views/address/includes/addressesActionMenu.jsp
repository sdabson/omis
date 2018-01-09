<%--
  - Action menu for addresses.
  -
  - Author: Stephen Abson
  --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<fmt:bundle basename="omis.address.msgs.address">
<ul>
	<c:if test="${empty address}">
		<sec:authorize access="hasRole('ADMIN') or hasRole('ADDRESS_CREATE')">
			<li><a class="createLink" href="${pageContext.request.contextPath}/address/create.html"><span class="visibleLinkLabel"><fmt:message key="createAddressTitle"/></span></a></li>
		</sec:authorize>
	</c:if>
	<c:if test="${not empty address}">
		<sec:authorize access="hasRole('ADMIN') or hasRole('ADDRESS_VIEW')">
			<li><a class="viewEditLink" href="${pageContext.request.contextPath}/address/edit.html?address=${address.id}"><span class="visibleLinkLabel"><fmt:message key="viewEditAddressLink"/></span></a></li>
		</sec:authorize>
		<sec:authorize access="hasRole('ADMIN') or hasRole('ADDRESS_REMOVE')">
			<li><a class="removeLink" href="${pageContext.request.contextPath}/address/remove.html?address=${address.id}"><span class="visibleLinkLabel"><fmt:message key="removeAddressLink"/></span></a></li>
		</sec:authorize>
	</c:if>
</ul>
</fmt:bundle>