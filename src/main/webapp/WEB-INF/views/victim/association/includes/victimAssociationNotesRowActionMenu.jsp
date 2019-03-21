<%--
  - Action menu for victim association notes.
  -
  - Author: Sheronda Vaughn
  --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<fmt:setBundle var="commonBundle" basename="omis.msgs.common"/>
	<ul>		
		<li>		
		<a class="removeLink associationRemoveLink" id="associationRemoveLink${victimNoteItemIndex}" href="${pageContext.request.contextPath}/victim/association/removeNote.html"><span class="linkLabel"><fmt:message key="removeLink" bundle="${commonBundle}"/></span></a>
  		</li>
		<li>
		<c:if test="${'UPDATE' eq operation.name}">
			<a class="linkLink" id="disassociateLink${victimNoteItemIndex}" href="${pageContext.request.contextPath}/victim/association/disassociateNote.html"><span class="linkLabel"><fmt:message key="disassociateLink" bundle="${commonBundle}"/></span></a>
		</c:if>		
		</li>
	</ul>
	