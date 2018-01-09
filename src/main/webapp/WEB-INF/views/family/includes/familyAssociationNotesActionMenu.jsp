<!-- 
 - Author: Yidong Li
 - Version: 0.1.0 (June 12, 2015)
 - Since: OMIS 3.0
 -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<fmt:bundle basename="omis.family.msgs.family">
	<ul>
		<li>
			<a class="createLink" href="${pageContext.request.contextPath}/family/addFamilyAssociationNoteItem.html" id="addFamilyAssociationNoteItemLink">
				<span class="visibleLinkLabel">
					<fmt:message key="createFamilyAssociationNotesLabel"/>
				</span>
			</a>
		</li>
	</ul>
	</body>
</fmt:bundle>