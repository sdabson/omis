<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
	<fmt:bundle basename="omis.substance.msgs.substance">
	<head>
		<jsp:include page="/WEB-INF/views/common/includes/headerOffenderFormResources.jsp"/>
		<jsp:include page="/WEB-INF/views/common/includes/toolsResources.jsp"/>
		<script type="text/javascript" src="${pageContext.request.contextPath}/resources/common/scripts/JQuery/jquery.omis.search.js"> </script>
    	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/common/scripts/search.js"> </script>
    	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/substanceTest/scripts/jQuery/jquery.omis.substanceTest.js?VERSION=1"> </script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/resources/substanceTest/scripts/substanceTest.js?VERSION=1"> </script>
		<script type="text/javascript">
	  /* <![CDATA[ */
			//Track current substance test and crime lab result index
			var currentSubstanceTestResultIndex = ${currentSubstanceTestResultIndex};
			var currentLabResultIndex = ${currentLabResultIndex};
	  /* ]]> */
	  </script>
		<title>
			<c:choose>
				<c:when test="${not empty substanceTest}">
					<fmt:message key="substanceTestEditTitle">
					</fmt:message>
					<jsp:include page="/WEB-INF/views/offender/includes/offenderNameSummary.jsp"/>
				</c:when>
				<c:otherwise>
					<fmt:message key="substanceTestCreateTitle">
					</fmt:message>
					<jsp:include page="/WEB-INF/views/offender/includes/offenderNameSummary.jsp"/>
				</c:otherwise>
			</c:choose>
		</title>
	</head>
	<body>
		<jsp:include page="/WEB-INF/views/offender/includes/offenderHeader.jsp"/>
		<h1>
			<a class="actionMenuItem" id="actionMenuLink" href="${pageContext.request.contextPath}/substanceTest/substanceTestActionMenu.html?offender=${offender.id}"></a>
			<c:choose>
					<c:when test="${not empty substanceTest}">
						<fmt:message key="substanceTestEditTitle">
						</fmt:message>
					</c:when>
					<c:otherwise>
						<fmt:message key="substanceTestCreateTitle">
						</fmt:message>
					</c:otherwise>
				</c:choose>
		</h1>
		<jsp:include page="includes/editForm.jsp"/>
		</body>
	</fmt:bundle>
</html>