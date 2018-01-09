<%--
 -
 - Form to edit addresses.
 -
 - Author: Stephen Abson, Joel Norris
 --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<fmt:bundle basename="omis.address.msgs.address">
	<form:form commandName="addressForm" class="editForm">
		<fieldset>
			<legend><fmt:message key="addressLabel"/></legend>
			<span class="fieldGroup">
				<form:label path="value" class="fieldLabel">
					<fmt:message key="valueLabel"/></form:label>
				<form:input path="value" />
				<form:errors path="value" cssClass="error"/>
			</span>
			<span class="fieldGroup">
				<form:label path="country" class="fieldLabel">
					<fmt:message key="countryLabel"/></form:label>
				<form:select path="country">
					<form:option value=""><fmt:message key="nullLabel" bundle="${commonBundle}"/></form:option>
					<form:options itemValue="id" itemLabel="name" items="${countries}"/>
				</form:select>
				<form:errors path="country" cssClass="error"/>
			</span>
			<span class="fieldGroup">
				<form:label path="state" class="fieldLabel">
					<fmt:message key="stateLabel"/></form:label>
				<form:select path="state">
					<form:option value=""><fmt:message key="nullLabel" bundle="${commonBundle}"/></form:option>
					<form:options itemValue="id" itemLabel="name" items="${states}"/>
				</form:select>
				<form:errors path="state" cssClass="error"/>
			</span>
			<span class="fieldGroup">
				<form:label path="city" class="fieldLabel">
					<fmt:message key="cityLabel"/></form:label>
				<form:select path="city">
					<form:option value=""><fmt:message key="nullLabel" bundle="${commonBundle}"/></form:option>
					<form:options itemValue="id" itemLabel="name" items="${cities}"/>
				</form:select>
				<form:errors path="city" cssClass="error"/>
			</span>
			<span class="fieldGroup">
				<form:label path="zipCode" class="fieldLabel">
					<fmt:message key="zipCodeLabel"/></form:label>
				<form:select path="zipCode">
					<form:option value=""><fmt:message key="nullLabel" bundle="${commonBundle}"/></form:option>
					<c:forEach var="zipCode" items="${zipCodes}">
						<c:choose>
							<c:when test="${addressForm.zipCode eq zipCode}">
								<form:option value="${zipCode.id}" selected="selected">
									<c:out value="${zipCode.city.name}"/>,
									<c:out value="${zipCode.city.state.abbreviation}"/>
									<c:out value="${zipCode.value}"/>
									<c:out value="${zipCode.extension}"/>
								</form:option>
							</c:when>
							<c:otherwise>
								<form:option value="${zipCode.id}">
									<c:out value="${zipCode.city.name}"/>,
									<c:out value="${zipCode.city.state.abbreviation}"/>
									<c:out value="${zipCode.value}"/>
									<c:out value="${zipCode.extension}"/>
								</form:option>
							</c:otherwise>
						</c:choose>
					</c:forEach>
				</form:select>
				<form:errors path="zipCode" cssClass="error"/>
			</span>
		</fieldset>
		<c:if test="${not empty address}">
		<c:set var="updatable" value="${address}" scope="request"/>
			<jsp:include page="/WEB-INF/views/audit/includes/updateSignature.jsp"/>
		</c:if>
		<p class="buttons">
			<input type="submit" value="<fmt:message key='saveLabel' bundle='${commonBundle}'/>"/>
		</p>
	</form:form>
</fmt:bundle>