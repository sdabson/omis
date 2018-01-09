<!-- 
 - Author: Yidong Li
 - Version: 0.1.0 (April 17, 2015)
 - Since: OMIS 3.0
 -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<fmt:bundle basename="omis.criminalassociation.msgs.criminalAssociation">
	<ul style = 'display: none'>
		<li>
			<a class="printLink"><span class="visibleLinkLabel"><fmt:message key="printAssociationLabel"/></span>
			</a>
		</li>
	</ul>
	<ul style = 'display: none'>
		<li>
			<a class="helpLink"><span class="visibleLinkLabel"><fmt:message key="associationHelpLabel"/></span>
			</a>
		</li>
	</ul>
	<ul>
		<li>
			<a class="listLink" href="${pageContext.request.contextPath}/criminalAssociation/list.html?offender=${offender.id}"><span class="visibleLinkLabel"><fmt:message key="listAssociationsLabel"/></span>
			</a>
		</li>
	</ul>
</fmt:bundle>