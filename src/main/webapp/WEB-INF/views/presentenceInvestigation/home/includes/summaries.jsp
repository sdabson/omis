<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<sec:authorize var="editPresentenceInvestigationRequest" access="hasRole('PRESENTENCE_INVESTIGATION_VIEW') or hasRole('ADMIN')"/>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<fmt:bundle basename="omis.presentenceinvestigation.msgs.presentenceInvestigationHome">

	<ul id="psiLinks">
		
		<c:forEach var="summaryTaskItem" items="${summaryTaskItems}" varStatus="status">
			<li class="psiLink">
				<a class="newTab tabs2 button" href="${pageContext.request.contextPath}/presentenceInvestigation/offenseSummary/edit.html?presentenceInvestigationRequest=${presentenceInvestigationRequest.id}">
				<fmt:message key="summaryLink">
					<fmt:param value="${summaryTaskItem.taskAssociation.task.description}" />
				</fmt:message>
				</a>
				<c:choose>
					<c:when test="${summaryTaskItem.itemOperation eq 'COMPLETE'}">
						<input disabled="disabled" type="checkbox" class="moduleCheckBox"
						checked="checked"/>
						<form:hidden path="summaryTaskItems[${status.index}].itemOperation" />
					</c:when>
					<c:otherwise>
						<input type="checkbox" id="summaryTaskItems[${status.index}]"
						name="summaryTaskItems[${status.index}]" class="moduleCheckBox"
						value="${summaryTaskItem.itemOperation}"/>
					</c:otherwise>
				</c:choose>
				<a href="${pageContext.request.contextPath}/presentenceInvestigation/task/edit.html?presentenceInvestigationTaskAssociation=${summaryTaskItem.taskAssociation.id}" class="newTab tabs2 viewEditLink"></a>
			</li>
		</c:forEach>
		
		
		<%--
		<c:choose>
			<c:when test="${not empty offender}">
				<sec:authorize access="hasRole('OFFENSE_SECTION_SUMMARY_VIEW') or hasRole('OFFENSE_SECTION_SUMMARY_EDIT') or hasRole('ADMIN')">
					<li class="psiLink">
						<a class="newTab tabs2 button" href="${pageContext.request.contextPath}/presentenceInvestigation/offenseSummary/edit.html?presentenceInvestigationRequest=${presentenceInvestigationRequest.id}">
						<fmt:message key="offenseCircumstanceLink"/></a>
						<input class="moduleCheckBox" type="checkbox" disabled="disabled" />
					</li>
				</sec:authorize>
				<sec:authorize access="hasRole('JAIL_ADJUSTMENT_SECTION_SUMMARY_VIEW') or hasRole('JAIL_ADJUSTMENT_SECTION_SUMMARY_EDIT') or hasRole('ADMIN')">
					<li class="psiLink">
						<a class="newTab tabs2 button" href="${pageContext.request.contextPath}/presentenceInvestigation/jailAdjustmentSummary/edit.html?presentenceInvestigationRequest=${presentenceInvestigationRequest.id}">
						<fmt:message key="jailAdjustmentSummaryLink"/></a>
						<input class="moduleCheckBox" type="checkbox" disabled="disabled" />
					</li>
				</sec:authorize>
				<sec:authorize access="hasRole('VICTIM_SECTION_SUMMARY_VIEW') or hasRole('VICTIM_SECTION_SUMMARY_EDIT') or hasRole('ADMIN')">
					<li class="psiLink">
						<a class="newTab tabs2 button" href="${pageContext.request.contextPath}/presentenceInvestigation/victimSummary/edit.html?presentenceInvestigationRequest=${presentenceInvestigationRequest.id}">
						<fmt:message key="victimSummaryLink"/></a>
						<input class="moduleCheckBox" type="checkbox" disabled="disabled" />
					</li>
				</sec:authorize>
				<sec:authorize access="hasRole('PSYCHOLOGICAL_SECTION_SUMMARY_VIEW') or hasRole('PSYCHOLOGICAL_SECTION_SUMMARY_EDIT') or hasRole('ADMIN')">
					<li class="psiLink">
						<a class="newTab tabs2 button" href="${pageContext.request.contextPath}/presentenceInvestigation/psychologicalSummary/edit.html?presentenceInvestigationRequest=${presentenceInvestigationRequest.id}">
						<fmt:message key="psychologicalSummaryLink"/></a>
						<input class="moduleCheckBox" type="checkbox" disabled="disabled" />
					</li>
				</sec:authorize>
				<sec:authorize access="hasRole('EDUCATION_SECTION_SUMMARY_VIEW') or hasRole('EDUCATION_SECTION_SUMMARY_EDIT') or hasRole('ADMIN')">
					<li class="psiLink">
						<a class="newTab tabs2 button" href="${pageContext.request.contextPath}/presentenceInvestigation/educationSummary/edit.html?presentenceInvestigationRequest=${presentenceInvestigationRequest.id}">
						<fmt:message key="educationSummaryLink"/></a>
						<input class="moduleCheckBox" type="checkbox" disabled="disabled" />
					</li>
				</sec:authorize>
				<sec:authorize access="hasRole('PLEA_AGREEMENT_SECTION_SUMMARY_VIEW') or hasRole('PLEA_AGREEMENT_SECTION_SUMMARY_EDIT') or hasRole('ADMIN')">
					<li class="psiLink">
						<a class="newTab tabs2 button" href="${pageContext.request.contextPath}/presentenceInvestigation/pleaAgreementSummary/edit.html?presentenceInvestigationRequest=${presentenceInvestigationRequest.id}">
						<fmt:message key="pleaAgreementSummaryLink"/></a>
						<input class="moduleCheckBox" type="checkbox" disabled="disabled" />
					</li>
				</sec:authorize>
				<sec:authorize access="hasRole('CHEMICAL_USE_SECTION_SUMMARY_VIEW') or hasRole('CHEMICAL_USE_SECTION_SUMMARY_EDIT') or hasRole('ADMIN')">
					<li class="psiLink">
						<a class="newTab tabs2 button" href="${pageContext.request.contextPath}/presentenceInvestigation/chemicalUseSummary/edit.html?presentenceInvestigationRequest=${presentenceInvestigationRequest.id}">
						<fmt:message key="chemicalUseSummaryLink"/></a>
						<input class="moduleCheckBox" type="checkbox" disabled="disabled" />
					</li>
				</sec:authorize>
				<sec:authorize access="hasRole('OTHER_PERTINENT_INFORMATION_SECTION_SUMMARY_VIEW') or hasRole('OTHER_PERTINENT_INFORMATION_SECTION_SUMMARY_EDIT') or hasRole('ADMIN')">
					<li class="psiLink">
						<a class="newTab tabs2 button" href="${pageContext.request.contextPath}/presentenceInvestigation/otherPertinentInformationSummary/edit.html?presentenceInvestigationRequest=${presentenceInvestigationRequest.id}">
						<fmt:message key="otherPertinentInformationSummaryLink"/></a>
						<input class="moduleCheckBox" type="checkbox" disabled="disabled" />
					</li>
				</sec:authorize>
				<sec:authorize access="hasRole('HEALTH_SECTION_SUMMARY_VIEW') or hasRole('HEALTH_SECTION_SUMMARY_EDIT') or hasRole('ADMIN')">
					<li class="psiLink">
						<a class="newTab tabs2 button" href="${pageContext.request.contextPath}/presentenceInvestigation/healthSummary/edit.html?presentenceInvestigationRequest=${presentenceInvestigationRequest.id}">
						<fmt:message key="healthSectionSummaryLink"/></a>
						<input class="moduleCheckBox" type="checkbox" disabled="disabled" />
					</li>
				</sec:authorize>
			</c:when>
		</c:choose>
		 --%>
	</ul>
</fmt:bundle>