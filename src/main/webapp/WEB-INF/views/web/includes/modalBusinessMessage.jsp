<%--
 - Author: Stephen Abson
 --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setBundle basename="${modalBusinessMessage.basename}" var="modalBusinessMessageBundle"/>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<div id="modalArea">
	<div id="modalLayer"></div>
	<div id="modalMessage">
	  <p id="modalMessageText"><fmt:message key="${modalBusinessMessage.key}" bundle="${modalBusinessMessageBundle}"/></p>
	  <p id="modalMessageButtons">
	    <a id="modalMessageCloseLink" href="${pageContext.request.contextPath}/closeMessage.html">
	    	<fmt:message key="iUnderstandLabel" bundle="${commonBundle}"/>
	    </a>
	  </p>
	</div>
</div>