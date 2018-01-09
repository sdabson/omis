<%--
 - Author: Stephen Abson
 - Version: 0.1.0 (Oct 30, 2013)
 - Since: OMIS 3.0
 --%>
<div id="showProfilePrompt">
	<form action="${pageContext.request.contextPath}/offender/showProfile.html" method="get">
		<p>
		<input type="hidden" name="redirectUrl" value="${redirectUrl}"/>
		</p>
		<p>
		<input type="text" name="${param.offenderNumberFieldName}" id="${param.offenderNumberFieldName}"/>
		<input type="submit" value="OK"/>
		</p>
	</form>
</div>