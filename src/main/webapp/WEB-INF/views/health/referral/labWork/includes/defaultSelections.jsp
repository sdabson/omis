<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<fmt:bundle basename="omis.health.msgs.health">
	<div id="defaultSelectionArea">
		<fieldset>
			<legend><fmt:message key="defaultLabWorkSelectionAreaLabel"/></legend>
			<p>
			<label for="defaultOrderDate" class="fieldLabel"><fmt:message key="defaultOrderDateLabel"/></label>
			<input type="text" class="date" id="defaultOrderDate" name="defaultOrderDate"/>
			<label for="defaultOrderedBy" class="fieldLabel"><fmt:message key="defaultOrderedByLabel"/></label>
			<select id="defaultOrderedBy" name="defaultOrderedBy">
				<jsp:include page="/WEB-INF/views/health/referral/labWork/includes/defaultByProviderOptions.jsp"/>
			</select>
			</p>
			<p>
			<label for="defaultSampleDate" class="fieldLabel"><fmt:message key="defaultSampleDateLabel"/></label>
			<input type="text" class="date" id="defaultSampleDate"/>
			<label for="defaultSampleLab" class="fieldLabel"><fmt:message key="defaultSampleLabLabel"/></label>
			<select id="defaultSampleLab">
				<option value=""><fmt:message key="nullLabel" bundle="${commonBundle}"/></option>
				<c:forEach var="lab" items="${labs}">
					<c:choose>
						<c:when test="${defaultSampleLab eq lab}">
							<option value="${lab.id}" selected="selected"><c:out value="${lab.name}"/></option>
						</c:when>
						<c:otherwise>
							<option value="${lab.id}"><c:out value="${lab.name}"/></option>
						</c:otherwise>
					</c:choose>
				</c:forEach>
			</select>
			<label class="fieldLabel"><fmt:message key="defaultLabWorkSampleRestrictionsShortLabel"/></label>
			<label for="defaultNothingPerOral" class="fieldValueLabel"><fmt:message key="nothingPerOralSampleRestriction"/></label>
			<c:choose>
				<c:when test="${defaultNothingPerOral}">
					<input id="defaultNothingPerOral" name="defaultNothingPerOral" class="fieldValue" type="checkbox" value="true" checked="checked"/>
				</c:when>
				<c:otherwise>
					<input id="defaultNothingPerOral" name="defaultNothingPerOral" class="fieldValue" type="checkbox" value="true"/>
				</c:otherwise>
			</c:choose>
			<label for="defaultNoLeaky" class="fieldValueLabel"><fmt:message key="noLeakySampleRestrictionLabel"/></label>
			<c:choose>
				<c:when test="${defaultNoLeaky}">
					<input id="defaultNoLeaky" name="defaultNoLeaky" class="fieldValue" type="checkbox" value="true" checked="checked"/>
				</c:when>
				<c:otherwise>
					<input id="defaultNoLeaky" name="defaultNoLeaky" class="fieldValue" type="checkbox" value="true"/>
				</c:otherwise>
			</c:choose>
			<label for="defaultNoMeds" class="fieldValueLabel"><fmt:message key="noMedsSampleRestrictionLabel"/></label>
			<c:choose>
				<c:when test="${defaultNoMeds}">
					<input id="defaultNoMeds" name="defaultNoMeds" class="fieldValue" type="checkbox" value="true" checked="checked"/>
				</c:when>
				<c:otherwise>
					<input id="defaultNoMeds" name="defaultNoMeds" class="fieldValue" type="checkbox" value="true"/>
				</c:otherwise>
			</c:choose>
			</p>
		</fieldset>
	</div>
</fmt:bundle>