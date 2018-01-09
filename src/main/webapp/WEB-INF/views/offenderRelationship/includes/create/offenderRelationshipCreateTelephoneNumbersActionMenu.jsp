<!-- 
 - Author: Yidong Li
 - Version: 0.1.0 (May 6, 2016)
 - Since: OMIS 3.0
 -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<fmt:bundle basename="omis.offenderrelationship.msgs.createOffenderRelationship">
	<ul>
		<li>
			<a class="createLink" href="${pageContext.request.contextPath}/offenderRelationship/addCreateOffenderRelationshipTelephoneNumberItem.html" id="addCreateOffenderRelationTelephoneNumberItemLink">
				<span class="visibleLinkLabel">
					<fmt:message key="createTelephoneNumbersLabel"/>
				</span>
			</a>
		</li>
	</ul>
	</body>
</fmt:bundle>