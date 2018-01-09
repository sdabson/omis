<!-- 
 - Author: Josh Divine
 - Version: 0.1.0 (Dec 8, 2016)
 - Since: OMIS 3.0
 -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="/WEB-INF/tld/omis.tld" prefix="omis" %>
<fmt:setBundle basename="omis.nocr.msgs.nocr" var="nocr"/>
<fmt:bundle basename="omis.nocr.msgs.caseManagement">
	<ul>
		<li>
			<span><fmt:message key="facilityLabel" bundle="${nocr}"/></span>
			<ul>
				<li><span><fmt:message key="noneAtThisTimeLabel" bundle="${nocr}"/></span></li>
			</ul>
		</li>
		<li>
			<span><fmt:message key="communityLabel" bundle="${nocr}"/></span>
			<ul>
				<li>
					<span><fmt:message key="blankFormsLabel"/></span>
					<ul>
						<li><span><fmt:message key="noneAtThisTimeLabel" bundle="${nocr}"/></span></li>
						<%--<li><omis:report reportPath="" decorate="no" title="" className="newTab reportLink"><fmt:message key="biologicalSampleFormLinkLabel"/></omis:report></li>
						<li><omis:report reportPath="" decorate="no" title="" className="newTab reportLink"><fmt:message key="conditionsOfSupervisionFormLinkLabel"/></omis:report></li>
						<li><omis:report reportPath="" decorate="no" title="" className="newTab reportLink"><fmt:message key="contactLetterLinkLabel"/></omis:report></li>
						<li><omis:report reportPath="" decorate="no" title="" className="newTab reportLink"><fmt:message key="firearmFormLinkLabel"/></omis:report></li>
						<li><omis:report reportPath="" decorate="no" title="" className="newTab reportLink"><fmt:message key="noContactFormLinkLabel"/></omis:report></li>
						<li><omis:report reportPath="" decorate="no" title="" className="newTab reportLink"><fmt:message key="notificationOfSearchFormLinkLabel"/></omis:report></li>
						<li><omis:report reportPath="" decorate="no" title="" className="newTab reportLink"><fmt:message key="restitutionReportLinkLabel"/></omis:report></li>
						<li><omis:report reportPath="" decorate="no" title="" className="newTab reportLink"><fmt:message key="supervisionFeesFormLinkLabel"/></omis:report></li>
						<li><omis:report reportPath="" decorate="no" title="" className="newTab reportLink"><fmt:message key="signupPackageFormLinkLabel"/></omis:report></li>
						<li><omis:report reportPath="" decorate="no" title="" className="newTab reportLink"><fmt:message key="travelPermitFormLinkLabel"/></omis:report></li>--%>
					</ul>
				</li>
			</ul>
		</li>
		<li>
			<span><fmt:message key="combinedLabel" bundle="${nocr}"/></span>
			<ul>
				<li><span><fmt:message key="noneAtThisTimeLabel" bundle="${nocr}"/></span></li>
				<%--<li><omis:report reportPath="" decorate="no" title="" className="newTab reportLink"><fmt:message key="authorizationForReleaseOfInformationFormLinkLabel"/></omis:report></li>--%>
			</ul>
		</li>
	</ul>
</fmt:bundle>