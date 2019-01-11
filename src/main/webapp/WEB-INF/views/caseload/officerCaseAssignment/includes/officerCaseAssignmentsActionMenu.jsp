<%--
 - OMIS - Offender Management Information System
 - Copyright (C) 2011 - 2017 State of Montana
 -
 - This program is free software: you can redistribute it and/or modify
 - it under the terms of the GNU General Public License as published by
 - the Free Software Foundation, either version 3 of the License, or
 - (at your option) any later version.
 -
 - This program is distributed in the hope that it will be useful,
 - but WITHOUT ANY WARRANTY; without even the implied warranty of
 - MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 - GNU General Public License for more details.
 -
 - You should have received a copy of the GNU General Public License
 - along with this program.  If not, see <http://www.gnu.org/licenses/>.
--%>
<%--
 - Author: Josh Divine
 - Author: Sierra Haynes
 - Author: Trevor Isles
 - Version: 0.1.6 (Nov 28, 2018)
 - Since: OMIS 3.0
 --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="/WEB-INF/tld/omis.tld" prefix="omis" %>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<fmt:bundle basename="omis.caseload.msgs.officerCaseAssignment">
	<ul>
		<c:if test="${empty officerCaseAssignment}">
			<sec:authorize access="hasRole('OFFICER_CASE_ASSIGNMENT_CREATE') or hasRole('ADMIN')">
				<li>
					<a class="createLink" href="${pageContext.request.contextPath}/caseload/officerCaseAssignment/create.html?offender=${offender.id}&userAccount=${userAccount.id}"><span class="visibleLinkLabel"><fmt:message key="createOfficerCaseAssignmentsLink"/></span></a>
				</li>
			</sec:authorize>
		</c:if>
		<c:if test="${empty officerCaseAssignment and not empty offender}">
			<sec:authorize access="hasRole('OFFICER_CASE_ASSIGNMENT_LIST') or hasRole('ADMIN')">
				<li>
				<a href="${pageContext.request.contextPath}/caseload/officerCaseAssignment/officerCaseListingReport.html?offender=${offender.id}&reportFormat=PDF" class="newTab printLink"><fmt:message key="officerCaseListingReportLinkLabel"/></a>
				</li>
			</sec:authorize>
		</c:if>
		<c:if test="${empty officerCaseAssignment and empty offender}">
			<sec:authorize access="hasRole('OFFICER_CASE_ASSIGNMENT_LIST') or hasRole('ADMIN')">
				<li>
				<a href="${pageContext.request.contextPath}/caseload/officerCaseAssignment/officerCaseloadActiveListingReport.html?userAccount=${userAccount.id}&reportFormat=PDF" class="newTab printLink"><fmt:message key="officerCaseloadActiveListingReportLinkLabel"/></a>
				</li>
			</sec:authorize>
		</c:if>
		<c:if test="${empty officerCaseAssignment and empty offender}">
			<sec:authorize access="hasRole('OFFICER_CASE_ASSIGNMENT_LIST') or hasRole('ADMIN')">
				<li>
				<a href="${pageContext.request.contextPath}/caseload/officerCaseAssignment/officerCaseloadDetailedReport.html?userAccount=${userAccount.id}&reportFormat=PDF" class="newTab printLink"><fmt:message key="officerCaseloadActiveDetailedReportLinkLabel"/></a>
				</li>
			</sec:authorize>
		</c:if>
		<c:if test="${empty officerCaseAssignment and empty offender}">
			<sec:authorize access="hasRole('OFFICER_CASE_ASSIGNMENT_LIST') or hasRole('ADMIN')">
				<li>
				<a href="${pageContext.request.contextPath}/caseload/officerCaseAssignment/officerCaseloadDetailedPhotoReport.html?userAccount=${userAccount.id}&reportFormat=PDF" class="newTab printLink"><fmt:message key="officerCaseloadActiveDetailedPhotoReportLinkLabel"/></a>
				</li>
			</sec:authorize>
		</c:if>				
		<c:if test="${empty officerCaseAssignment and empty offender}">
			<sec:authorize access="hasRole('OFFICER_CASE_ASSIGNMENT_LIST') or hasRole('ADMIN')">
				<li>
				<a href="${pageContext.request.contextPath}/caseload/officerCaseAssignment/officerCaseloadListingReport.html?userAccount=${userAccount.id}&reportFormat=PDF" class="newTab printLink"><fmt:message key="officerCaseloadListingReportLinkLabel"/></a>
				</li>
			</sec:authorize>
		</c:if>		
		<c:if test="${empty officerCaseAssignment and empty offender}">
			<sec:authorize access="hasRole('OFFICER_CASE_ASSIGNMENT_LIST') or hasRole('ADMIN')">
				<li>
				 <omis:reportPro reportPath="/CaseManagement/OfficerCaseAssignment/Officer_Case_Assignment_by_Region_Supervising_Office" decorate="no" title="" className="newTab reportLink"><fmt:message key="officerCaseAssignmentsRegionReportLinkLabel"/></omis:reportPro>
				</li>
			</sec:authorize>
		</c:if>					
		<c:if test="${not empty officerCaseAssignment}">
			<sec:authorize access="hasRole('OFFICER_CASE_ASSIGNMENT_VIEW') or hasRole('ADMIN')">
				<li>
					<a class="viewEditLink" href="${pageContext.request.contextPath}/caseload/officerCaseAssignment/edit.html?officerCaseAssignment=${officerCaseAssignment.id}&offender=${offender.id}&userAccount=${userAccount.id}"><span class="visibleLinkLabel"><fmt:message key="viewEditLink" bundle="${commonBundle}"/></span></a>
				</li>
			</sec:authorize>
			<sec:authorize access="hasRole('OFFICER_CASE_ASSIGNMENT_REMOVE') or hasRole('ADMIN')">
				<li>
					<a class="removeLink" href="${pageContext.request.contextPath}/caseload/officerCaseAssignment/remove.html?officerCaseAssignment=${officerCaseAssignment.id}&offender=${offender.id}&userAccount=${userAccount.id}"><span class="visibleLinkLabel"><fmt:message key="removeLink" bundle="${commonBundle}"/></span></a>
				</li>
			</sec:authorize>
			<c:if test="${empty offender and not empty userAccount}">
				<li>
					<a class="newTab viewEditLink" href="${pageContext.request.contextPath}/offender/profile.html?offender=${officerCaseAssignment.offender.id}"><span class="visibleLinkLabel"><fmt:message key="loadOffenderLink"/></span></a>
				</li>
				<li>
					<a class="showHideOffenderAssignmentsLink" href="${pageContext.request.contextPath}/caseload/officerCaseAssignment/showOffenderAssignments.html?officerCaseAssignment=${officerCaseAssignment.id}"><span class="visibleLinkLabel"><fmt:message key="showHideOffenderAssignmentsLink"/></span></a>
				</li>
			</c:if>
		</c:if>
		<c:if test="${not empty officerCaseAssignment}">
			<sec:authorize access="hasRole('OFFICER_CASE_ASSIGNMENT_VIEW') or hasRole('ADMIN')">
				<li>
					<a href="${pageContext.request.contextPath}/caseload/officerCaseAssignment/officerCaseDetailsReport.html?officerCaseAssignment=${officerCaseAssignment.id}&reportFormat=PDF" class="newTab printLink"><fmt:message key="officerCaseDetailsReportLinkLabel"/></a>
				</li>
			</sec:authorize>
		</c:if>		
	</ul>
</fmt:bundle>