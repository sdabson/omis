<!-- 
 - Author: Yidong Li
 - Version: 0.1.0 (Nov 23, 2015)
 - Since: OMIS 3.0
 -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<fmt:bundle basename="omis.family.msgs.family">
	<ul>
		<sec:authorize access="hasRole('FAMILY_ASSOCIATION_CREATE') or hasRole('ADMIN')">
			<li>
				<a class="createLink" href="${pageContext.request.contextPath}/family/addFamilyAssociationTelephoneNumberItem.html" id="addFamilyAssociationTelephoneNumberItemLink">
					<span class="visibleLinkLabel">
						<fmt:message key="createTelephoneNumbersLabel"/>
					</span>
				</a>
			</li>
		</sec:authorize>
	</ul>
	</body>
</fmt:bundle>