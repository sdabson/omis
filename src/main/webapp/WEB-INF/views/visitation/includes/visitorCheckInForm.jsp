<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:bundle basename="omis.visitation.msgs.visitation">
	<span class="accentRegular titleBar">
		<label class="modalDialogueTitleLabel">
			<fmt:message key="visitorCheckInFormTitle"/>
		</label>
	</span>
	<form:form commandName="visitorCheckInForm" class="modalForm" action="${pageContext.request.contextPath}/visitation/visit/checkIn.html" method="POST">
		<fieldset style="border:0px;">
				<span class="fieldGroup">
					<form:label path="badgeNumber" class="fieldLabel"><fmt:message key="badgeNumberLabel"/></form:label>
					<form:input type ="text" path="badgeNumber"/>
					<form:errors cssClass="error" path="badgeNumber"/>
					<input type="hidden" id="visitationAssociation" name="visitationAssociation" value="${visitationAssociation.id}"/>
				</span>
<!-- 				Remove the hidden span when multiple visitation methods are allowed -->
				<span class="removeThisSpan hidden">
					<span class="fieldGroup">
						<form:label path="method" class="fieldLabel"><fmt:message key="visitMethodLabel"/></form:label>
						<form:select class="" path="method">
							<c:forEach items="${visitMethods}" var="method" varStatus="status">
								<form:option value="${method}"><fmt:message key="${method}.optionLabel"/></form:option>
							</c:forEach>
						</form:select>
						<form:errors cssClass="error" path="method"/>
					</span>
				</span>
		</fieldset>
		<p class="buttons">
			<button id="modalFormCancelButton" value="cancel" type="reset"><fmt:message key='cancelVisitLabel'/></button>
			<input type="submit" value="<fmt:message key='saveVisitLabel'/>"/>
		</p>
	</form:form>
</fmt:bundle>