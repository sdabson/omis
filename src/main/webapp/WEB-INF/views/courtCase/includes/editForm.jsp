<%--
 - Form to edit court cases.
 -
 - Author: Stephen Abson
 - Author: Joel Norris
 --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<fmt:bundle basename="omis.courtcase.msgs.courtCase">
<form:form commandName="courtCaseForm" class="editForm">
	<fieldset>
		<legend><fmt:message key="courtCaseLegendLabel"/></legend>
		<form:hidden path="allowCourt"/>
		<span class="fieldGroup">
			<form:label path="court" class="fieldLabel">
				<fmt:message key="courtLabel"/></form:label>
			<c:choose>
			<c:when test="${empty docket}">
				<form:select path="court">
					<form:option value="">...</form:option>
					<form:options items="${courts}" itemLabel="name" itemValue="id"/>
				</form:select>
				<form:errors path="court" cssClass="error"/>
			</c:when>
			<c:otherwise>
				<span id="courtDisplay"><c:out value="${docket.court.name}"/></span>
			</c:otherwise>
			</c:choose>	
		</span>
		<form:hidden path="allowDocket"/>
		<span class="fieldGroup">
			<form:label path="docketValue" class="fieldLabel">
				<fmt:message key="docketLabel"/></form:label>
			<c:choose>
				<c:when test="${empty docket}">
					<form:input path="docketValue"/>
					<form:errors path="docketValue" cssClass="error"/>
				</c:when>
				<c:otherwise>
					<span id="docketDisplay"><c:out value="${docket.value}"/></span>
				</c:otherwise>
			</c:choose>
		</span>
		<span class="fieldGroup">
			<form:label path="interStateNumber" class="fieldLabel">
				<fmt:message key="interStateNumberLabel"/></form:label>
			<form:input path="interStateNumber"/>
			<form:errors path="interStateNumber" cssClass="error"/>
		</span>
		<span class="fieldGroup">
			<form:label path="interState" class="fieldLabel">
				<fmt:message key="interStateLabel"/></form:label>
			<form:select path="interState">
				<form:option value="">...</form:option>
				<form:options items="${states}" itemLabel="name" itemValue="id"/>
			</form:select>
			<form:errors path="interState" cssClass="error"/>
		</span>
		<span class="fieldGroup">
			<form:label path="pronouncementDate" class="fieldLabel">
				<fmt:message key="pronouncementDateLabel"/></form:label>
			<form:input path="pronouncementDate" class="date"/>
			<form:errors path="pronouncementDate" cssClass="error"/>
		</span>
		<span class="fieldGroup">
			<form:label path="jurisdictionAuthority" class="fieldLabel">
				<fmt:message key="jurisdictionAuthorityLabel"/></form:label>
			<form:select path="jurisdictionAuthority">
				<form:option value="">...</form:option>
				<c:forEach items="${jurisdictionAuthorities}" var="jurisdictionAuthority">
					<c:choose>
						<c:when test="${not empty courtCaseForm.jurisdictionAuthority and courtCaseForm.jurisdictionAuthority eq jurisdictionAuthority}">
							<form:option selected="selected" value="${jurisdictionAuthority}"><fmt:message key="jurisdictionAuthorityLabel.${jurisdictionAuthority}" /></form:option>
						</c:when>
						<c:otherwise>
							<form:option value="${jurisdictionAuthority}"><fmt:message key="jurisdictionAuthorityLabel.${jurisdictionAuthority}" /></form:option>
						</c:otherwise>
					</c:choose>
				</c:forEach>
			</form:select>
			<form:errors path="jurisdictionAuthority" cssClass="error"/>
		</span>
		<span class="fieldGroup">
			<form:label path="dangerDesignator" class="fieldLabel">
				<fmt:message key="dangerDesignatorLabel"/></form:label>
			<form:select path="dangerDesignator">
				<form:option value="">...</form:option>
				<c:forEach items="${dangerDesignators}" var="dangerDesignator">
					<c:choose>
						<c:when test="${not empty courtCaseForm.dangerDesignator and courtCaseForm.dangerDesignator eq dangerDesignator}">
							<form:option selected="selected" value="${dangerDesignator}"><fmt:message key="dangerDesignatorLabel.${dangerDesignator}" /></form:option>
						</c:when>
						<c:otherwise>
							<form:option value="${dangerDesignator}"><fmt:message key="dangerDesignatorLabel.${dangerDesignator}" /></form:option>
						</c:otherwise>
					</c:choose>
				</c:forEach>
			</form:select>
			<form:errors path="dangerDesignator" cssClass="error"/>
		</span>
		<span class="fieldGroup">
			<form:label path="sentenceReviewDate" class="fieldLabel">
				<fmt:message key="sentenceReviewDateLabel"/></form:label>
			<form:input path="sentenceReviewDate" class="date"/>
			<form:errors path="sentenceReviewDate" cssClass="error"/>
		</span>
		<span class="fieldGroup">
			<form:label path="convictionOverturned" class="fieldLabel"><fmt:message key="convictionsOverturnedLabel"/></form:label>
			<form:checkbox path="convictionOverturned" value="true"/>
		</span>
		<span class="fieldGroup">
			<form:label path="criminallyConvictedYouth" class="fieldLabel"><fmt:message key="criminallyConvictedYouthLabel"/></form:label>
			<form:checkbox path="criminallyConvictedYouth" value="true"/>
		</span>
		<span class="fieldGroup">
			<form:label path="dismissed" class="fieldLabel"><fmt:message key="dismissedLabel"/></form:label>
			<form:checkbox path="dismissed" value="true"/>
		</span>
		<span class="fieldGroup">
			<form:label path="youthTransfer" class="fieldLabel"><fmt:message key="youthTransferLabel"/></form:label>
			<form:checkbox path="youthTransfer" value="true"/>
		</span>
		<span class="fieldGroup">
			<form:label path="comments" class="fieldLabel">
				<fmt:message key="commentsLabel"/>
			</form:label>
			<form:textarea path="comments"/>
		</span>
	</fieldset>
	<fieldset>
		<legend><fmt:message key="courtPersonnelLabel"/></legend>
		<span class="fieldGroup">
		  <form:label path="judge" class="fieldLabel">
					<fmt:message key="judgeLabel"/>
			</form:label>
			<input id="judgeQuery"/>
			<form:hidden path="judge"/>
			<a id="judgeClear" class="clearLink"></a>
			<span id="judgeDisplay">
			<c:if test="${not empty courtCaseForm.judge}">
				<c:out value="${courtCaseForm.judge.name.lastName}"/>,
				<c:out value="${courtCaseForm.judge.name.firstName}"/>
			</c:if>
			</span>
			<form:errors path="judge" cssClass="error"/>
		</span>
		<span class="fieldGroup">
		  	<form:label path="defenseAttorneyName" class="fieldLabel">
				<fmt:message key="defenseAttorneyLabel"/></form:label>
			<form:input path="defenseAttorneyName"/>
			<form:errors path="defenseAttorneyName" cssClass="error"/>
		</span>
		<span class="fieldGroup">
			<form:label path="prosecutingAttorneyName" class="fieldLabel">
				<fmt:message key="prosecutingAttorneyLabel"/></form:label>
			<form:input path="prosecutingAttorneyName"/>
			<form:errors path="prosecutingAttorneyName" cssClass="error"/>
		</span>
	</fieldset>
	<fieldset id="chargesHolder">
		<legend><fmt:message key="chargesTitle"/></legend>
		<%-- <a href="${pageContext.request.contextPath}/courtCase/addCharge.html" class="createLink" id="addChargeLink"><span class="visibleLinkLabel"><fmt:message key="addChargeLink"/></span></a>--%>
		<jsp:include page="chargesContent.jsp"/>
		<form:errors path="charges" cssClass="error"/>
	</fieldset>
	<fieldset id="notesHolder">
		<legend><fmt:message key="courtCaseNotesTitle"/></legend>
		<jsp:include page="courtCaseNotesTable.jsp"/>
		<form:errors path="noteItems" cssClass="error"/>
	</fieldset>
	<c:if test="${not empty courtCase}">
		<c:set var="updatable" value="${courtCase}" scope="request"/>
		<jsp:include page="/WEB-INF/views/audit/includes/updateSignature.jsp"/>
	</c:if>
	<p class="buttons">
		<button type="submit" name="operation" value="SAVE">
			<fmt:message key="saveLabel" bundle="${commonBundle}"/></button>
		<%-- <button type="submit" name="operation" value="SAVE_AND_CONTINUE">
			<fmt:message key="saveAndContinueLabel" bundle="${commonBundle}"/></button> --%>
	</p>
</form:form>
</fmt:bundle>