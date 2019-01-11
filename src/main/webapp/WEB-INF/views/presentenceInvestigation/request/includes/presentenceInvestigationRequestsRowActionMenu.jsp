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
  - Author: Annie Wahl
  - Author: Kelly Churchill
  - Author: Sierra Haynes
  - Author: Josh Divine
  - Version: 0.1.9 (Nov 28, 2018)
  - Since: OMIS 3.0 
  --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="/WEB-INF/tld/omis.tld" prefix="omis" %>
<fmt:bundle basename="omis.presentenceinvestigation.msgs.presentenceInvestigationRequest">
	<ul>
		<sec:authorize access="hasRole('PRESENTENCE_INVESTIGATION_REQUEST_VIEW') or hasRole('ADMIN')">
			<li>
				<a class="viewEditLink"
				 href="${pageContext.request.contextPath}/presentenceInvestigation/request/edit.html?presentenceInvestigationRequest=${presentenceInvestigationRequest.id}&onReturn=${onReturn}"><span class="visibleLinkLabel"><fmt:message key="editPresentenceInvestigationRequestLink"/></span></a>
			</li>
		</sec:authorize>
		<sec:authorize access="hasRole('PRESENTENCE_INVESTIGATION_REQUEST_VIEW') or hasRole('ADMIN')">
			<li>
				<a class="homeLink" href="${pageContext.request.contextPath}/presentenceInvestigation/home.html?presentenceInvestigationRequest=${presentenceInvestigationRequest.id}"><span class="visibleLinkLabel"><fmt:message key="presentenceInvestigationHomeLink"/></span></a>
			</li>
		</sec:authorize>
		<sec:authorize access="hasRole('PRESENTENCE_INVESTIGATION_REQUEST_REMOVE') or hasRole('ADMIN')">
			<li>
				<a class="removeLink" href="${pageContext.request.contextPath}/presentenceInvestigation/request/remove.html?presentenceInvestigationRequest=${presentenceInvestigationRequest.id}&onReturn=${onReturn}"><span class="visibleLinkLabel"><fmt:message key="removePresentenceInvestigationRequestLink"/></span></a>
			</li>
		</sec:authorize>
		<sec:authorize access="hasRole('PRESENTENCE_INVESTIGATION_REQUEST_VIEW') or hasRole('ADMIN')">
			<li>
				<a href="${pageContext.request.contextPath}/presentenceInvestigation/request/affidavitOfPecuniaryLossReport.html?presentenceInvestigationRequest=${presentenceInvestigationRequest.id}&reportFormat=PDF" class="newTab adobeReportLink"><fmt:message key="affidavitOfPecuniaryLossReportLinkLabel"/></a>
			</li>
		</sec:authorize>		
		<sec:authorize access="hasRole('PRESENTENCE_INVESTIGATION_REQUEST_VIEW') or hasRole('ADMIN')">
			<li>
				<omis:reportPro reportPath="/Legal/PSI/Letter_of_Noncompliance&PSI_REQ_ID=${presentenceInvestigationRequest.id}" decorate="no" title="" className="newTab reportLink"><fmt:message key="psiNoncomplianceReportLinkLabel"/></omis:reportPro>
			</li>
		</sec:authorize>
		<sec:authorize access="hasRole('PRESENTENCE_INVESTIGATION_REQUEST_VIEW') or hasRole('ADMIN')">
			<li>
				<a href="${pageContext.request.contextPath}/presentenceInvestigation/request/psiQuestionnaireReport.html?person=${presentenceInvestigationRequest.person.id}&reportFormat=PDF" class="newTab adobeReportLink"><fmt:message key="pSIQuestionnaireReportLinkLabel"/></a>
			</li>
		</sec:authorize>
		 <%--<sec:authorize access="hasRole('PRESENTENCE_INVESTIGATION_REQUEST_VIEW') or hasRole('ADMIN')">
			<li>
				<a href="${pageContext.request.contextPath}/presentenceInvestigation/request/investigationRequestDetailsReport.html?presentenceInvestigationRequest=${presentenceInvestigationRequest.id}&reportFormat=PDF" class="newTab adobeReportLink"><fmt:message key="investigationRequestDetailsReportLinkLabel"/></a>
			</li>
		</sec:authorize>--%>	
		<sec:authorize access="hasRole('PRESENTENCE_INVESTIGATION_REQUEST_VIEW') or hasRole('ADMIN')">
			<li>
				<a href="${pageContext.request.contextPath}/presentenceInvestigation/request/psiRequestDetailsReport.html?presentenceInvestigationRequest=${presentenceInvestigationRequest.id}&reportFormat=PDF" class="newTab printLink"><fmt:message key="psiRequestDetailsReportLinkLabel"/></a>
			</li>
		</sec:authorize>					
	</ul>
</fmt:bundle>