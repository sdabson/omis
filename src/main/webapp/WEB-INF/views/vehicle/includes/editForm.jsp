<%--
 - Author: Ryan Johns
 - Author: Yidong Li
 - Version: 0.1.1 (Jun 1, 2016)
 - Since: OMIS 3.0
 --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<fmt:setBundle basename="omis.vehicle.msgs.vehicle" var="vehicleBundle"/>

<form:form commandName="vehicleAssociationForm" class="editForm">
	<fieldset>
		<span class="fieldGroup">
			<form:label path="startDate" class="fieldLabel">
				<fmt:message key="startDateLabel" bundle="${vehicleBundle}"/>
			</form:label>
			<form:input path="startDate" class="date"/>
			<form:errors path="startDate" cssClass="error"/>
		</span>
		<span class="fieldGroup">
			<form:label path="endDate" class="fieldLabel">
				<fmt:message key="endDateLabel" bundle="${vehicleBundle}"/>
			</form:label>
			<form:input path="endDate" class="date"/>
			<form:errors path="endDate" cssClass="error"/>
		</span>
		<span class="fieldGroup">
			<form:label class="fieldLabel" path="vehicleMake">
				<fmt:message key="vehicleMakeLabel" bundle="${vehicleBundle}"/>
			</form:label>
			<form:select path = "vehicleMake">
				<form:option value=""><fmt:message key="nullLabel" bundle="${commonBundle}"/></form:option>
				<form:options items="${vehicleMakes}" itemValue="id" itemLabel="name"/>
			</form:select>
			<form:errors path="vehicleMake" cssClass="error"/>
		</span>	
		<span class="fieldGroup">
			<form:label path="vehicleModel" class="fieldLabel">
				<fmt:message key="vehicleModelLabel" bundle="${vehicleBundle}"></fmt:message>
			</form:label>
			<form:select path="vehicleModel">
				<jsp:include page="/WEB-INF/views/vehicle/includes/vehicleModelOptions.jsp"/>
			</form:select>				
			<form:errors path="vehicleModel" cssClass="error"/>
		</span>
		<span class="fieldGroup">
			<form:label path="vehicleColor" class="fieldLabel">
				<fmt:message key="vehicleColorLabel" bundle="${vehicleBundle}"/>
			</form:label>
			<form:select path="vehicleColor">
				<form:option value=""><fmt:message key="nullLabel" bundle="${commonBundle}"/></form:option>
				<form:options items="${vehicleColors}" itemValue="id" itemLabel="name"/>
			</form:select>
			<form:errors path="vehicleColor" cssClass="error"/>
		</span>
		<span class="fieldGroup">
			<form:label path="year" class="fieldLabel">
				<fmt:message key="yearLabel" bundle="${vehicleBundle}"/>
			</form:label>
			
			<form:select path = "year">
				<form:option value=""><fmt:message key="nullLabel" bundle="${commonBundle}"/></form:option>
				<c:forEach var = "y" begin="${startYear}" end="${currentYear}" step='1'>
					<form:option value='${y}'/>
				</c:forEach>
			</form:select>
		</span>	
		<span class="fieldGroup">
			<form:label path="" class="fieldLabel">
				<fmt:message key="stateLabel" bundle="${vehicleBundle}"/></form:label>
			<form:select path="state">
				<form:option value=""><fmt:message key="nullLabel" bundle="${commonBundle}"/></form:option>
				<form:options items="${vehicleStates}" itemValue="id" itemLabel="name"/>
			</form:select>
			<form:errors path="state" cssClass="error"/>
		</span>
		<span class="fieldGroup">
			<form:label path="plateNumber" class="fieldLabel">
				<fmt:message key="plateNumberLabel" bundle="${vehicleBundle}"/>
			</form:label>
			<form:input path="plateNumber"/>
			<form:errors path="plateNumber" cssClass="error"/>
		</span>
		<span class="fieldGroup">
			<form:label path="ownerDescription" class="fieldLabel">
				<fmt:message key="ownerDescriptionLabel" bundle="${vehicleBundle}"/></form:label>
			<form:input path="ownerDescription"/>
			<form:errors path="ownerDescription" cssClass="error"/>
		</span>
		
		<span class="fieldGroup">
			<form:label path="comments" class="fieldLabel">
				<fmt:message key="commentsLabel" bundle="${vehicleBundle}"/>
			</form:label>
			<%-- 
			<form:input path="comments"/>
			--%>
			<form:textarea path="comments" row="4"/>
			<form:errors path="comments" cssClass="error"/>
		</span>
		<p class="buttons">
			<input type="submit" value="<fmt:message key='saveLabel' bundle="${commonBundle}"/>"/>
		</p>
	</fieldset>
		<c:if test="${not empty vehicleAssociation}">
		<c:set var="updatable" value="${vehicleAssociation}" scope="request"/>
		<jsp:include page="/WEB-INF/views/audit/includes/updateSignature.jsp"/>
	</c:if>
</form:form>