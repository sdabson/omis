<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<fmt:bundle basename="omis.offenderrelationship.msgs.offenderRelationship">
<head>
	<jsp:include page="/WEB-INF/views/common/includes/headerOffenderListResources.jsp"/>
	<jsp:include page="/WEB-INF/views/common/includes/toolsResources.jsp"/>
	<jsp:include page="/WEB-INF/views/common/includes/messageResolverResources.jsp"/>
	<script type="text/javascript">
		/* <![CDATA[ */
			//offender id for use with action menu display
			var offender = ${offender.id};
		/* ]]> */
	</script>
  	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/offenderRelationship/scripts/listOffenderRelationships.js"> </script>
	<title>
		<fmt:message key="offenderRelationshipListHeader"/>
		<jsp:include page="/WEB-INF/views/offender/includes/offenderNameSummary.jsp"/>
	</title>
</head>
 <body>
 	<c:if test="${not empty offenderSummary}">
 	<jsp:include page="/WEB-INF/views/offender/includes/offenderHeader.jsp"/>
 	</c:if>
 	<h1>
		<a class="actionMenuItem" id="offenderRelationshipListActionMenuLink" href="${pageContext.request.contextPath}/offenderRelationship/offenderRelationshipsListActionMenu.html?offender=${offender.id}"></a><span class="visibleLinkLabel"/>
		<fmt:message key="offenderRelationshipListHeader"/>	
	</h1>
	<jsp:include page="includes/listTable.jsp"/>
</body>
</fmt:bundle>
</html>