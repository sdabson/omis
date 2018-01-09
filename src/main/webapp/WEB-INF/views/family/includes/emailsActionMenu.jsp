<!-- 
 - Author: Yidong Li
 - Version: 0.1.0 (Dec 1, 2015)
 - Since: OMIS 3.0
 -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<fmt:bundle basename="omis.family.msgs.family">
	<ul>
		<sec:authorize access="hasRole('FAMILY_ASSOCIATION_CREATE') or hasRole('ADMIN')">
			<li>
				<a class="createLink" href="${pageContext.request.contextPath}/family/addFamilyAssociationEmailItem.html" id="addFamilyAssociationEmailItemLink">
					<span class="visibleLinkLabel">
						<fmt:message key="createEmailsLabel"/>
					</span>
				</a>
			</li>
		</sec:authorize>
	</ul>
	</body>
</fmt:bundle>