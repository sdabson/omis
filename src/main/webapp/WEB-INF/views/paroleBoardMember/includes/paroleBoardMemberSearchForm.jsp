<%--
 - Search form for parole board members.
 -
 - Author: Josh Divine
 --%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<fmt:bundle basename="omis.paroleboardmember.msgs.paroleBoardMember">
	<form:form commandName="paroleBoardMemberSearchForm" class="filterForm">
		<fieldset class="foregroundUltraLight">
			<div>
				<span class="fieldGroup relevantOptionFieldGroup">
					<form:radiobutton path="singleDate" id="singleDateSearch" value="true"/>
					<label for="singleDateSearch" class="radioButtonLabel"><fmt:message key="effectiveDateLabel"/></label>
				</span>
				<span class="fieldGroup">
					<form:input id="date" path="date" class="date"/>
					<form:errors cssClass="error" path="date"/>
				</span>
			</div>
			<div>
				<span class="fieldGroup relevantOptionFieldGroup">
					<form:radiobutton id="dateRangeSearch" path="singleDate" value="false"/>
					<label for="dateRangeSearch" class="radioButtonLabel"><fmt:message key="effectiveDatesLabel"/></label>
				</span>
				<span class="fieldGroup">
					<form:input class="date" id="startDate" path="startDate"/>
					<form:errors cssClass="error" path="startDate"/>
				</span>
				<span class="fieldGroup">
					<label for="endDate"><fmt:message key="toDateLabel"/></label>
					<form:input class="date" id="endDate" path="endDate"/>
					<form:errors cssClass="error" path="endDate"/>
				</span>
			</div>
			<p class="buttons">
				<input type="submit" value="<fmt:message key='searchLabel' bundle='${commonBundle}'/>"/>
			</p>
		</fieldset>
	</form:form>
</fmt:bundle>