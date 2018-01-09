<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<fmt:bundle basename="omis.identificationnumber.msgs.identificationNumberCategory">
<form:form commandName="identificationNumberCategoryForm" class="editForm">
	<fieldset>
		<span class="fieldGroup">
			<form:label path="name" class="fieldLabel">
				<fmt:message key="nameLabel"/>
			</form:label>
			<form:input path="name"/>
			<form:errors path="name" cssClass="error"/>
		</span>
		<span class="fieldGroup">
			<form:label path="valid" class="fieldLabel">
				<fmt:message key="validLabel"/>
			</form:label>
			<form:checkbox path="valid" />
			<form:errors path="valid" cssClass="error"/>
		</span>
		<span class="fieldGroup">
			<form:label path="multitude" class="fieldLabel">
				<fmt:message key="multitudeLabel"/>
			</form:label>
			<form:select path="multitude">
				<jsp:include page="../../../includes/nullOption.jsp"/>
				<c:forEach items="${multitudeValues}" var="mult">
					<option value="${mult}" ${mult == identificationNumberCategoryForm.multitude ? 'selected="selected"' : ''}>
						<fmt:message key="multitudeValue${mult}Label"/>
					</option>
				</c:forEach>
			</form:select>
			<form:errors path="multitude" cssClass="error"/>
		</span>
	</fieldset>
	
	<p class="buttons">
		<input type="submit" value="<fmt:message key='saveLabel' bundle='${commonBundle}'/>"/>
	</p>
</form:form>
</fmt:bundle>