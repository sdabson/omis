<%--
  - Offense item.
  -
  - Author: Stephen Abson
  --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<fmt:setBundle var="offenseTermBundle" basename="omis.offenseterm.msgs.offenseTerm"/>
<fmt:setBundle var="commonBundle" basename="omis.msgs.common"/>
<c:choose>
	<c:when test="${offenseItem.operation eq 'REMOVE'}">
		<c:set var="headerContentClass" value="headerContent removeSection"/>
	</c:when>
	<c:otherwise>
		<c:set var="headerContentClass" value="headerContent backgroundLight"/>
	</c:otherwise>
</c:choose>
<div id="offenseItems[${offenseItemIndex}]" class="fieldsetSection">
	<div id="offenseItems[${offenseItemIndex}].headerContent" class="${headerContentClass}">
		<c:choose>
			<c:when test="${offenseItem.expanded}">
				<c:set var="expandCollapseLinkClass" value="collapseLink"/>
			</c:when>
			<c:otherwise>
				<c:set var="expandCollapseLinkClass" value="expandLink"/>
			</c:otherwise>
		</c:choose>
		<a href="${pageContext.request.contextPath}/offenseTerm/toggleOffense.html?itemIndex=${offenseItemIndex}" id="offenseItems[${offenseItemIndex}].expandCollapseLink" class="${expandCollapseLinkClass}"></a>
		<a href="${pageContext.request.contextPath}/offenseTerm/removeOffense.html?itemIndex=${offenseItemIndex}" id="offenseItems[${offenseItemIndex}].removeLink" class="deleteLink"></a>
		<c:choose>
			<c:when test="${offenseItem.expanded}">
				<c:set var="offenseSummaryClass" value="hidden"/>
				<c:set var="editOffenseHeaderClass" value=""/>
			</c:when>
			<c:otherwise>
				<c:set var="offenseSummaryClass" value=""/>
				<c:set var="editOffenseHeaderClass" value="hidden"/>
			</c:otherwise>
		</c:choose>
		<span id="offenseItems[${offenseItemIndex}].editOffenseHeader" class="header ${editOffenseHeaderClass}"><fmt:message key="editOffenseHeader" bundle="${offenseTermBundle}"/></span>
		<span id="offenseItems[${offenseItemIndex}].offenseSummary" class="${offenseSummaryClass}">
			<span class="fieldLabel"><fmt:message key="offenseLabel" bundle="${offenseTermBundle}"/></span>
			<span id="offenseItems[${offenseItemIndex}].summaryOffenseValue" class="fieldValue"><c:out value="${offenseItem.convictionFields.offense.name}"/></span>
			<span class="fieldLabel"><fmt:message key="termLabel" bundle="${offenseTermBundle}"/></span>
			<span id="offenseItems[${offenseItemIndex}].summaryTermValue" class="fieldValue">
				<c:set var="showPrisonTerm" value="${offenseItem.sentenceFields.category.prisonRequirement.name eq 'REQUIRED' or offenseItem.sentenceFields.category.prisonRequirement.name eq 'OPTIONAL'}" scope="request"/>
				<c:set var="prisonDays" value="${offenseItem.sentenceFields.prisonDays}" scope="request"/>
				<c:set var="prisonMonths" value="${offenseItem.sentenceFields.prisonMonths}" scope="request"/>
				<c:set var="prisonYears" value="${offenseItem.sentenceFields.prisonYears}" scope="request"/>
				<c:set var="showProbationTerm" value="${offenseItem.sentenceFields.category.probationRequirement.name eq 'REQUIRED' or offenseItem.sentenceFields.category.probationRequirement.name eq 'OPTIONAL'}" scope="request"/>
				<c:set var="probationDays" value="${offenseItem.sentenceFields.probationDays}" scope="request"/>
				<c:set var="probationMonths" value="${offenseItem.sentenceFields.probationMonths}" scope="request"/>
				<c:set var="probationYears" value="${offenseItem.sentenceFields.probationYears}" scope="request"/>
				<c:set var="showDeferredTerm" value="${offenseItem.sentenceFields.category.deferredRequirement.name eq 'REQUIRED' or offenseItem.sentenceFields.category.deferredRequirement.name eq 'OPTIONAL'}" scope="request"/>
				<c:set var="deferredDays" value="${offenseItem.sentenceFields.deferredDays}" scope="request"/>
				<c:set var="deferredMonths" value="${offenseItem.sentenceFields.deferredMonths}" scope="request"/>
				<c:set var="deferredYears" value="${offenseItem.sentenceFields.deferredYears}" scope="request"/>
				<jsp:include page="/WEB-INF/views/sentence/includes/sentenceTerms.jsp"/>
			</span>
			<span class="fieldLabel"><fmt:message key="legalDispositionCategoryLabel" bundle="${offenseTermBundle}"/></span>
			<span id="offenseItems[${offenseItemIndex}].summaryLegalDispositionCategoryValue" class="fieldValue"><c:out value="${offenseItem.sentenceFields.legalDispositionCategory.name}"/></span>
			<span class="fieldLabel"><fmt:message key="sentenceCategoryLabel" bundle="${offenseTermBundle}"/></span>
			<span id="offenseItems[${offenseItemIndex}].summarySentenceCategoryValue" class="fieldValue"><c:out value="${offenseItem.sentenceFields.category.name}"/></span>
		</span>
	</div>
	<c:choose>
		<c:when test="${offenseItem.expanded}">
			<c:set var="fieldsClass" value=""/>
		</c:when>
		<c:otherwise>
			<c:set var="fieldsClass" value="hidden"/>
		</c:otherwise>
	</c:choose>
	<div id="offenseItems[${offenseItemIndex}].fields" class="${fieldsClass}">
		<input type="hidden" id="offenseItems[${offenseItemIndex}].operation" name="offenseItems[${offenseItemIndex}].operation" value="${offenseItem.operation.name}"/>
		<input type="hidden" id="offenseItems[${offenseItemIndex}].expanded" name="offenseItems[${offenseItemIndex}].expanded" value="${offenseItem.expanded}"/>
		<div id="convictionContainer[${offenseItemIndex}]">
			<input type="hidden" name="offenseItems[${offenseItemIndex}].conviction" value="${offenseItem.conviction.id}"/>
			<c:set var="fieldsPropertyName" value="offenseItems[${offenseItemIndex}].convictionFields" scope="request"/>
			<c:set var="convictionFields" value="${offenseItem.convictionFields}" scope="request"/>
			<jsp:include page="/WEB-INF/views/conviction/includes/convictionFields.jsp"/>
		</div>
		<c:choose>
			<c:when test="${not empty offenseItem.sentence}">
				<c:set var="sentenceOperationsId" value="sentenceOperations[${offenseItem.sentence.id}]"/>
			</c:when>
			<c:otherwise>
				<c:set var="sentenceOperationsId" value="sentenceOperations[null]"/>
			</c:otherwise>
		</c:choose>
		<span class="fieldGroup" id="${sentenceOperationsId}">
			<label class="fieldLabel">
				<fmt:message key="sentenceOperationLabel" bundle="${offenseTermBundle}"/></label>
			<c:choose>
				<c:when test="${not empty offenseItem.sentence}">
					<c:choose>
						<c:when test="${offenseItem.sentenceOperation.name eq 'UPDATE'}">
							<input type="radio" name="offenseItems[${offenseItemIndex}].sentenceOperation" id="offenseItems[${offenseItemIndex}].sentenceOperation.UPDATE" value="UPDATE" checked="checked"/>
						</c:when>
						<c:otherwise>
							<input type="radio" name="offenseItems[${offenseItemIndex}].sentenceOperation" id="offenseItems[${offenseItemIndex}].sentenceOperation.UPDATE" value="UPDATE"/>
						</c:otherwise>
					</c:choose>
					<label for="offenseItems[${offenseItemIndex}].sentenceOperation.UPDATE" title="<fmt:message key='sentenceOperationMessage.UPDATE' bundle='${offenseTermBundle}'/>" class="fieldValueLabel">
						<fmt:message key="sentenceOperationLabel.UPDATE" bundle="${offenseTermBundle}"/></label>
					<c:choose>
						<c:when test="${offenseItem.sentenceOperation.name eq 'AMEND'}">
							<input type="radio" name="offenseItems[${offenseItemIndex}].sentenceOperation" id="offenseItems[${offenseItemIndex}].sentenceOperation.AMEND" value="AMEND" checked="checked"/>
						</c:when>
						<c:otherwise>
							<input type="radio" name="offenseItems[${offenseItemIndex}].sentenceOperation" id="offenseItems[${offenseItemIndex}].sentenceOperation.AMEND" value="AMEND"/>
						</c:otherwise>
					</c:choose>
					<label for="offenseItems[${offenseItemIndex}].sentenceOperation.AMEND" title="<fmt:message key='sentenceOperationMessage.AMEND' bundle='${offenseTermBundle}'/>" class="fieldValueLabel">
						<fmt:message key="sentenceOperationLabel.AMEND" bundle="${offenseTermBundle}"/></label>
					<c:choose>
						<c:when test="${offenseItem.sentenceOperation.name eq 'REMOVE'}">
							<input type="radio" name="offenseItems[${offenseItemIndex}].sentenceOperation" id="offenseItems[${offenseItemIndex}].sentenceOperation.REMOVE" value="REMOVE" checked="checked"/>
						</c:when>
						<c:otherwise>
							<input type="radio" name="offenseItems[${offenseItemIndex}].sentenceOperation" id="offenseItems[${offenseItemIndex}].sentenceOperation.REMOVE" value="REMOVE"/>
						</c:otherwise>
					</c:choose>
					<label for="offenseItems[${offenseItemIndex}].sentenceOperation.REMOVE" title="<fmt:message key='sentenceOperationMessage.REMOVE' bundle='${offenseTermBundle}'/>" class="fieldValueLabel">
						<fmt:message key="sentenceOperationLabel.REMOVE" bundle="${offenseTermBundle}"/></label>					
				</c:when>
				<c:otherwise>
					<c:choose>
						<c:when test="${empty offenseItem.sentenceOperation}">
							<input type="radio" name="offenseItems[${offenseItemIndex}].sentenceOperation" id="offenseItems[${offenseItemIndex}].emptySentenceOperation" value="" checked="checked"/>
						</c:when>
						<c:otherwise>
							<input type="radio" name="offenseItems[${offenseItemIndex}].sentenceOperation" id="offenseItems[${offenseItemIndex}].emptySentenceOperation" value=""/>
						</c:otherwise>
					</c:choose>
					<label for="offenseItems[${offenseItemIndex}].emptySentenceOperation" title="<fmt:message key='emptySentenceOperationMessage' bundle='${offenseTermBundle}'/>" class="fieldValueLabel">
						<fmt:message key="emptySentenceOperationLabel" bundle="${offenseTermBundle}"/></label>
					<c:choose>
						<c:when test="${offenseItem.sentenceOperation.name eq 'CREATE'}">
							<input type="radio" name="offenseItems[${offenseItemIndex}].sentenceOperation" id="offenseItems[${offenseItemIndex}].sentenceOperation.CREATE" value="CREATE" checked="checked"/> 
						</c:when>
						<c:otherwise>
							<input type="radio" name="offenseItems[${offenseItemIndex}].sentenceOperation" id="offenseItems[${offenseItemIndex}].sentenceOperation.CREATE" value="CREATE"/>
						</c:otherwise>
					</c:choose>
					<label for="offenseItems[${offenseItemIndex}].sentenceOperation.CREATE" title="<fmt:message key='sentenceOperationMessage.CREATE' bundle='${offenseTermBundle}'/>" class="fieldValueLabel">
						<fmt:message key="sentenceOperationLabel.CREATE" bundle="${offenseTermBundle}"/></label>						
				</c:otherwise>
			</c:choose>
			<form:errors path="offenseItems[${offenseItemIndex}].sentenceOperation" cssClass="error"/>
			<span id="offenseItems[${offenseItemIndex}].sentenceOperationMessage" class="message">
				<c:choose>
					<c:when test="${not empty offenseItem.sentenceOperation}">
						<fmt:message key="sentenceOperationMessage.${offenseItem.sentenceOperation.name}" bundle="${offenseTermBundle}"/>
					</c:when>
					<c:otherwise>
						<fmt:message key="emptySentenceOperationMessage" bundle="${offenseTermBundle}"/>			
					</c:otherwise>
				</c:choose>
			</span>
		</span>
		<c:choose>
			<c:when test="${offenseItem.sentenceOperation.name eq 'CREATE' or offenseItem.sentenceOperation.name eq 'UPDATE' or offenseItem.sentenceOperation.name eq 'AMEND'}">
				<c:set var="sentenceContainerClass" value=""/>
			</c:when>
			<c:otherwise>
				<c:set var="sentenceContainerClass" value="hidden"/>
			</c:otherwise>
		</c:choose>
		<div id="sentenceContainer[${offenseItemIndex}]" class="${sentenceContainerClass}">
			<span class="fieldGroup">
				<label for="offenseItems[${offenseItemIndex}].connection" class="fieldLabel">
					<fmt:message key="offenseItemConnectionLabel" bundle="${offenseTermBundle}"/></label>
				<select name="offenseItems[${offenseItemIndex}].connection" id="offenseItems[${offenseItemIndex}].connection">
					<option value=""><fmt:message key="nullLabel" bundle="${commonBundle}"/></option>
					<c:choose>
						<c:when test="${offenseItem.connection.classification.name eq 'INITIAL'}">
							<option value="INITIAL" selected="selected"><fmt:message key="offenseItemConnectionLabel.INITIAL" bundle="${offenseTermBundle}"/></option>
						</c:when>
						<c:otherwise>
							<option value="INITIAL"><fmt:message key="offenseItemConnectionLabel.INITIAL" bundle="${offenseTermBundle}"/></option>
						</c:otherwise>
					</c:choose>
					<c:choose>
						<c:when test="${offenseItem.connection.classification.name eq 'CONCURRENT'}">
							<option value="CONCURRENT" selected="selected"><fmt:message key="offenseItemConnectionLabel.CONCURRENT" bundle="${offenseTermBundle}"/></option>
						</c:when>
						<c:otherwise>
							<option value="CONCURRENT"><fmt:message key="offenseItemConnectionLabel.CONCURRENT" bundle="${offenseTermBundle}"/></option>
						</c:otherwise>
					</c:choose>
					<c:forEach var="otherSentence" items="${otherSentences}">
						<fmt:message var="consecutiveOtherDocketOffenseItemSummaryLabel" key="consecutiveToOtherDocketSentenceLabel" bundle="${offenseTermBundle}">
							<fmt:param>${otherSentence.conviction.offense.name}</fmt:param>
							<fmt:param>${otherSentence.conviction.counts}</fmt:param>
							<fmt:param>${otherSentence.conviction.courtCase.docket.value}</fmt:param>
						</fmt:message>
						<c:choose>
							<c:when test="${offenseItem.connection.classification.name eq 'CONSECUTIVE_OTHER_DOCKET' and otherSentence.id eq offenseItem.connection.index}">
								<option value="CONSECUTIVE_OTHER_DOCKET[${otherSentence.id}]" selected="selected"><c:out value="${consecutiveOtherDocketOffenseItemSummaryLabel}"/></option>
							</c:when>
							<c:otherwise>
								<option value="CONSECUTIVE_OTHER_DOCKET[${otherSentence.id}]"><c:out value="${consecutiveOtherDocketOffenseItemSummaryLabel}"/></option>
							</c:otherwise>
						</c:choose>
					</c:forEach>
					<c:forEach var="innerOffenseItem" items="${offenseItems}" varStatus="status">
						<c:if test="${innerOffenseItem ne offenseItem and not empty innerOffenseItem.operation}">
							<c:choose>
								<c:when test="${not empty innerOffenseItem.convictionFields.offense}">
									<fmt:message var="consecutiveOffenseItemSummaryLabel" key="consecutiveToSentenceLabel" bundle="${offenseTermBundle}">
										<fmt:param>${innerOffenseItem.convictionFields.offense.name}</fmt:param>
										<fmt:param>${innerOffenseItem.convictionFields.counts}</fmt:param>
									</fmt:message>
								</c:when>
								<c:otherwise>
									<fmt:message var="consecutiveOffenseItemSummaryLabel" key="noOffenseSelectedMessage" bundle="${offenseTermBundle}"/>
								</c:otherwise>
							</c:choose>
							<c:choose>
								<c:when test="${not empty innerOffenseItem.convictionFields.offense}">
									<c:set var="innerOffenseItemClass" value=""/>
								</c:when>
								<c:otherwise>
									<c:set var="innerOffenseItemClass" value="hidden"/>
								</c:otherwise>
							</c:choose>
							<c:choose>
								<c:when test="${offenseItem.connection.classification.name eq 'CONSECUTIVE' and offenseItem.connection.index eq status.index}">
									<c:choose>
										<c:when test="${innerOffenseItem.operation.name eq 'REMOVE'}">
											<option value="CONSECUTIVE[${status.index}]" class="${innerOffenseItemClass}" selected="selected" disabled="disabled"><c:out value="${consecutiveOffenseItemSummaryLabel}"/></option>
										</c:when>
										<c:otherwise>
											<option value="CONSECUTIVE[${status.index}]" class="${innerOffenseItemClass}" selected="selected"><c:out value="${consecutiveOffenseItemSummaryLabel}"/></option>
										</c:otherwise>
									</c:choose>
								</c:when>
								<c:otherwise>
									<c:choose>
										<c:when test="${innerOffenseItem.operation.name eq 'REMOVE'}">
											<option value="CONSECUTIVE[${status.index}]" class="${innerOffenseItemClass}" disabled="disabled"><c:out value="${consecutiveOffenseItemSummaryLabel}"/></option>
										</c:when>
										<c:otherwise>
											<option value="CONSECUTIVE[${status.index}]" class="${innerOffenseItemClass}"><c:out value="${consecutiveOffenseItemSummaryLabel}"/></option>
										</c:otherwise>
									</c:choose>
								</c:otherwise>
							</c:choose>
						</c:if>
					</c:forEach>
				</select>
				<form:errors path="offenseItems[${offenseItemIndex}].connection" cssClass="error"/>
			</span>
			<input type="hidden" id="offenseItems[${offenseItemIndex}].sentence" name="offenseItems[${offenseItemIndex}].sentence" value="${offenseItem.sentence.id}"/>
			<c:set var="fieldsPropertyName" value="offenseItems[${offenseItemIndex}].sentenceFields" scope="request"/>
			<c:set var="sentenceFields" value="${offenseItem.sentenceFields}" scope="request"/>
			<jsp:include page="/WEB-INF/views/sentence/includes/sentenceFields.jsp"/>
			<c:if test="${not empty offenseItem.sentence}">
				<c:choose>
					<c:when test="${offenseItem.sentenceOperation.name eq 'AMEND'}">
						<c:set var="hisoricalOffenseTermsClass" value=""/>
					</c:when>
					<c:otherwise>
						<c:set var="hisoricalOffenseTermsClass" value="hidden"/>
					</c:otherwise>
				</c:choose>
				<div id="offenseItems[${offenseItemIndex}].hisoricalOffenseTerms" class="${hisoricalOffenseTermsClass}">
					<h4>
						<a href="${pageContext.request.contextPath}/offenseTerm/listHistoricalOffenseTerms.html?conviction=${offenseItem.conviction.id}" class="listLink" id="historicalOffenseTermsListLink[${offenseItem.sentence.id}]"></a>
						<fmt:message key="historicalOffenseTermsHeader" bundle="${offenseTermBundle}"/>
					</h4>
					<c:set var="inactiveSentenceSummaries" value="${inactiveSentenceSummariesForConviction[offenseItem.conviction]}" scope="request"/>
					<jsp:include page="historicalOffenseTermListTable.jsp"/>
				</div>
			</c:if>
		</div>
	</div>
</div>