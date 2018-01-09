<?xml version="1.0" encoding="UTF-8"?>
<%--
 - Non Offender-Centric Report screen.
 -
 - Author: Josh Divine
 --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<fmt:bundle basename="omis.nocr.msgs.nocr">
<head>
	<title>
		<fmt:message key="nonOffenderCentricReportingHeader"/>
	</title>
	<jsp:include page="/WEB-INF/views/common/includes/headerMetas.jsp"/>
	<jsp:include page="/WEB-INF/views/common/includes/headerGeneralResources.jsp"/>
	<jsp:include page="/WEB-INF/views/common/includes/headerToolbarResources.jsp"/>
	<jsp:include page="/WEB-INF/views/common/includes/jQueryResources.jsp"/>
	<jsp:include page="/WEB-INF/views/common/includes/toolsResources.jsp"/>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/nocr/scripts/nocr.js?VERSION=1"> </script>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/nocr/style/nocr.css?VERSION=1"/>
</head>
<body>
	<h1><fmt:message key="nonOffenderCentricReportingHeader"/></h1>
	<div class="reportContainer">
		<h1>
			<sec:authorize access="hasRole('NOCR_VIEW') or hasRole('ADMIN')">
				<a class="actionMenuItem" id="basicInformationActionMenuLink" href="${pageContext.request.contextPath}/nocr/basicInformationActionMenu.html"></a>
			</sec:authorize>
			<fmt:message key="basicInformationLabel"/>
		</h1>
		<div id="reportItems">
			<div class="reportItem">
				<sec:authorize access="hasRole('OFFENDER_ALT_IDENTITY_VIEW') or hasRole('ADMIN')">
					<a class="actionMenuItem" id="alternativeIdentitiesActionMenuLink" href="${pageContext.request.contextPath}/nocr/alternativeIdentitiesActionMenu.html"></a>
				</sec:authorize>
				<fmt:message key="alternativeIdentitiesLabel"/>
			</div>
			<div class="reportItem">
				<sec:authorize access="hasRole('OFFENDER_DEMOGRAPHICS_VIEW') or hasRole('ADMIN')">
					<a class="actionMenuItem" id="demographicsActionMenuLink" href="${pageContext.request.contextPath}/nocr/demographicsActionMenu.html"></a>
				</sec:authorize>
				<fmt:message key="demographicsLabel"/>
			</div>
			<div class="reportItem">
				<sec:authorize access="hasRole('DNA_VIEW') or hasRole('ADMIN')">
					<a class="actionMenuItem" id="dnaActionMenuLink" href="${pageContext.request.contextPath}/nocr/dnaActionMenu.html"></a>
				</sec:authorize>
				<fmt:message key="dnaLabel"/>
			</div>
			<div class="reportItem">
				<sec:authorize access="hasRole('OFFENDER_FLAG_VIEW') or hasRole('ADMIN')">
					<a class="actionMenuItem" id="flagsActionMenuLink" href="${pageContext.request.contextPath}/nocr/flagsActionMenu.html"></a>
				</sec:authorize>
				<fmt:message key="flagsLabel"/>
			</div>
			<div class="reportItem">
				<sec:authorize access="hasRole('OFFENDER_VIEW') or hasRole('ADMIN')">
					<a class="actionMenuItem" id="identificationNumbersActionMenuLink" href="${pageContext.request.contextPath}/nocr/identificationNumbersActionMenu.html"></a>
				</sec:authorize>
				<fmt:message key="identificationNumbersLabel"/>
			</div>
			<div class="reportItem">
				<sec:authorize access="hasRole('OFFENDER_VIEW') or hasRole('ADMIN')">
					<a class="actionMenuItem" id="legalNameAndIdentityActionMenuLink" href="${pageContext.request.contextPath}/nocr/legalNameAndIdentityActionMenu.html"></a>
				</sec:authorize>
				<fmt:message key="legalNameAndIdentityLabel"/>
			</div>
			<div class="reportItem">
				<sec:authorize access="hasRole('MILITARY_LIST') or hasRole('ADMIN')">
					<a class="actionMenuItem" id="militaryActionMenuLink" href="${pageContext.request.contextPath}/nocr/militaryActionMenu.html"></a>
				</sec:authorize>
				<fmt:message key="militaryLabel"/>
			</div>
			<div class="reportItem">
				<sec:authorize access="hasRole('OFFENDER_VIEW') or hasRole('ADMIN')">
					<a class="actionMenuItem" id="mugshotsActionMenuLink" href="${pageContext.request.contextPath}/nocr/mugshotsActionMenu.html"></a>
				</sec:authorize>
				<fmt:message key="mugshotsLabel"/>
			</div>
			<div class="reportItem">
				<sec:authorize access="hasRole('RELIGIOUS_PREFERENCE_VIEW') or hasRole('ADMIN')">
					<a class="actionMenuItem" id="religiousPreferenceActionMenuLink" href="${pageContext.request.contextPath}/nocr/religiousPreferenceActionMenu.html"></a>
				</sec:authorize>
				<fmt:message key="religiousPreferenceLabel"/>
			</div>
			<div class="reportItem">
				<sec:authorize access="hasRole('PHYSICAL_FEATURE_VIEW') or hasRole('ADMIN')">
					<a class="actionMenuItem" id="scarsMarksAndTattoosActionMenuLink" href="${pageContext.request.contextPath}/nocr/scarsMarksAndTattoosActionMenu.html"></a>
				</sec:authorize>
				<fmt:message key="scarsMarksAndTattosLabel"/>
			</div>
		</div>
	</div>
	<div class="reportContainer">
		<h1>
			<sec:authorize access="hasRole('NOCR_VIEW') or hasRole('ADMIN')">
				<a class="actionMenuItem" id="caseManagementActionMenuLink" href="${pageContext.request.contextPath}/nocr/caseManagementActionMenu.html"></a>
			</sec:authorize>
			<fmt:message key="caseManagementLabel"/>
		</h1>
		<div id="reportItems">
			<div class="reportItem">
				<sec:authorize access="hasRole('NOCR_VIEW') or hasRole('ADMIN')">
					<a class="actionMenuItem" id="assessmentsActionMenuLink" href="${pageContext.request.contextPath}/nocr/assessmentsActionMenu.html"></a>
				</sec:authorize>
				<fmt:message key="assessmentsLabel"/>
			</div>
			<div class="reportItem">
				<sec:authorize access="hasRole('NOCR_VIEW') or hasRole('ADMIN')">
					<a class="actionMenuItem" id="boardHearingsActionMenuLink" href="${pageContext.request.contextPath}/nocr/boardHearingsActionMenu.html"></a>
				</sec:authorize>
				<fmt:message key="boardHearingsLabel"/>
			</div>
			<div class="reportItem">
				<sec:authorize access="hasRole('NOCR_VIEW') or hasRole('ADMIN')">
					<a class="actionMenuItem" id="caseManagerAndOfficerActionMenuLink" href="${pageContext.request.contextPath}/nocr/caseManagerAndOfficerActionMenu.html"></a>
				</sec:authorize>
				<fmt:message key="caseManagerAndOfficerLabel"/>
			</div>
			<div class="reportItem">
				<sec:authorize access="hasRole('NOCR_VIEW') or hasRole('ADMIN')">
					<a class="actionMenuItem" id="caseNotesActionMenuLink" href="${pageContext.request.contextPath}/nocr/caseNotesActionMenu.html"></a>
				</sec:authorize>
				<fmt:message key="caseNotesLabel"/>
			</div>
			<div class="reportItem">
				<sec:authorize access="hasRole('NOCR_VIEW') or hasRole('ADMIN')">
					<a class="actionMenuItem" id="conditionsOfSupervisionActionMenuLink" href="${pageContext.request.contextPath}/nocr/conditionsOfSupervisionActionMenu.html"></a>
				</sec:authorize>
				<fmt:message key="conditionsOfSupervisionLabel"/>
			</div>
			<div class="reportItem">
				<sec:authorize access="hasRole('CUSTODY_REVIEW_VIEW') or hasRole('ADMIN')">
					<a class="actionMenuItem" id="custodyReviewActionMenuLink" href="${pageContext.request.contextPath}/nocr/custodyReviewActionMenu.html"></a>
				</sec:authorize>
				<fmt:message key="custodyReviewLabel"/>
			</div>
			<div class="reportItem">
				<sec:authorize access="hasRole('EDUCATION_VIEW') or hasRole('ADMIN')">
					<a class="actionMenuItem" id="educationActionMenuLink" href="${pageContext.request.contextPath}/nocr/educationActionMenu.html"></a>
				</sec:authorize>
				<fmt:message key="educationLabel"/>
			</div>
			<div class="reportItem">
				<sec:authorize access="hasRole('EMPLOYMENT_TERM_VIEW') or hasRole('ADMIN')">
					<a class="actionMenuItem" id="employmentActionMenuLink" href="${pageContext.request.contextPath}/nocr/employmentActionMenu.html"></a>
				</sec:authorize>
				<fmt:message key="employmentLabel"/>
			</div>
			<div class="reportItem">
				<sec:authorize access="hasRole('NOCR_VIEW') or hasRole('ADMIN')">
					<a class="actionMenuItem" id="goalsAndObjectivesActionMenuLink" href="${pageContext.request.contextPath}/nocr/goalsAndObjectivesActionMenu.html"></a>
				</sec:authorize>
				<fmt:message key="goalsAndObjectivesLabel"/>
			</div>
			<div class="reportItem">
				<sec:authorize access="hasRole('USER') or hasRole('ADMIN')">
					<a class="actionMenuItem" id="grievanceActionMenuLink" href="${pageContext.request.contextPath}/nocr/grievanceActionMenu.html"></a>
				</sec:authorize>
				<fmt:message key="grievanceLabel"/>
			</div>
			<div class="reportItem">
				<sec:authorize access="hasRole('NEED_MODULE') or hasRole('ADMIN')">
					<a class="actionMenuItem" id="needsActionMenuLink" href="${pageContext.request.contextPath}/nocr/needsActionMenu.html"></a>
				</sec:authorize>
				<fmt:message key="needsLabel"/>
			</div>
			<div class="reportItem">
				<sec:authorize access="hasRole('NOCR_VIEW') or hasRole('ADMIN')">
					<a class="actionMenuItem" id="referralsActionMenuLink" href="${pageContext.request.contextPath}/nocr/referralsActionMenu.html"></a>
				</sec:authorize>
				<fmt:message key="referralsLabel"/>
			</div>
			<div class="reportItem">
				<sec:authorize access="hasRole('RESIDENCE_TERM_LIST') or hasRole('NON_RESIDENCE_TERM_LIST') or hasRole('ADMIN')">
					<a class="actionMenuItem" id="residenceActionMenuLink" href="${pageContext.request.contextPath}/nocr/residenceActionMenu.html"></a>
				</sec:authorize>
				<fmt:message key="residenceLabel"/>
			</div>
			<div class="reportItem">
				<sec:authorize access="hasRole('NOCR_VIEW') or hasRole('ADMIN')">
					<a class="actionMenuItem" id="servicesActionMenuLink" href="${pageContext.request.contextPath}/nocr/servicesActionMenu.html"></a>
				</sec:authorize>
				<fmt:message key="servicesLabel"/>
			</div>
			<div class="reportItem">
				<sec:authorize access="hasRole('NOCR_VIEW') or hasRole('ADMIN')">
					<a class="actionMenuItem" id="strengthsActionMenuLink" href="${pageContext.request.contextPath}/nocr/strengthsActionMenu.html"></a>
				</sec:authorize>
				<fmt:message key="strengthsLabel"/>
			</div>
			<div class="reportItem">
				<sec:authorize access="hasRole('NOCR_VIEW') or hasRole('ADMIN')">
					<a class="actionMenuItem" id="treatmentActionMenuLink" href="${pageContext.request.contextPath}/nocr/treatmentActionMenu.html"></a>
				</sec:authorize>
				<fmt:message key="treatmentLabel"/>
			</div>
			<div class="reportItem">
				<sec:authorize access="hasRole('VEHICLE_VIEW') or hasRole('ADMIN')">
					<a class="actionMenuItem" id="vehiclesActionMenuLink" href="${pageContext.request.contextPath}/nocr/vehiclesActionMenu.html"></a>
				</sec:authorize>
				<fmt:message key="vehiclesLabel"/>
			</div>
		</div>
	</div>
	<div class="reportContainer">
		<h1>
			<sec:authorize access="hasRole('NOCR_VIEW') or hasRole('ADMIN')">
				<a class="actionMenuItem" id="complianceActionMenuLink" href="${pageContext.request.contextPath}/nocr/complianceActionMenu.html"></a>
			</sec:authorize>
			<fmt:message key="complianceLabel"/>
		</h1>
		<div id="reportItems">
			<div class="reportItem">
				<sec:authorize access="hasRole('NOCR_VIEW') or hasRole('ADMIN')">
					<a class="actionMenuItem" id="disciplinaryHearingActionMenuLink" href="${pageContext.request.contextPath}/nocr/disciplinaryHearingActionMenu.html"></a>
				</sec:authorize>
				<fmt:message key="disciplinaryHearingLabel"/>
			</div>
			<div class="reportItem">
				<sec:authorize access="hasRole('NOCR_VIEW') or hasRole('ADMIN')">
					<a class="actionMenuItem" id="reportsOfViolationActionMenuLink" href="${pageContext.request.contextPath}/nocr/reportsOfViolationActionMenu.html"></a>
				</sec:authorize>
				<fmt:message key="reportsOfViolationLabel"/>
			</div>
			<div class="reportItem">
				<sec:authorize access="hasRole('NOCR_VIEW') or hasRole('ADMIN')">
					<a class="actionMenuItem" id="requirementsToRegisterActionMenuLink" href="${pageContext.request.contextPath}/nocr/requirementsToRegisterActionMenu.html"></a>
				</sec:authorize>
				<fmt:message key="requirementsToRegisterLabel"/>
			</div>
			<div class="reportItem">
				<sec:authorize access="hasRole('NOCR_VIEW') or hasRole('ADMIN')">
					<a class="actionMenuItem" id="restitutionActionMenuLink" href="${pageContext.request.contextPath}/nocr/restitutionActionMenu.html"></a>
				</sec:authorize>
				<fmt:message key="restitutionLabel"/>
			</div>
			<div class="reportItem">
				<sec:authorize access="hasRole('NOCR_VIEW') or hasRole('ADMIN')">
					<a class="actionMenuItem" id="sanctionsAndInterventionsActionMenuLink" href="${pageContext.request.contextPath}/nocr/sanctionsAndInterventionsActionMenu.html"></a>
				</sec:authorize>
				<fmt:message key="sanctionsAndInterventionsLabel"/>
			</div>
			<div class="reportItem">
				<sec:authorize access="hasRole('NOCR_VIEW') or hasRole('ADMIN')">
					<a class="actionMenuItem" id="substanceHistoryActionMenuLink" href="${pageContext.request.contextPath}/nocr/substanceHistoryActionMenu.html"></a>
				</sec:authorize>
				<fmt:message key="substanceHistoryLabel"/>
			</div>
			<div class="reportItem">
				<sec:authorize access="hasRole('SUBSTANCE_TEST_VIEW') or hasRole('ADMIN')">
					<a class="actionMenuItem" id="substanceTestsActionMenuLink" href="${pageContext.request.contextPath}/nocr/substanceTestsActionMenu.html"></a>
				</sec:authorize>
				<fmt:message key="substanceTestsLabel"/>
			</div>
		</div>
	</div>
	<div class="reportContainer">
		<h1>
			<sec:authorize access="hasRole('NOCR_VIEW') or hasRole('ADMIN')">
				<a class="actionMenuItem" id="healthActionMenuLink" href="${pageContext.request.contextPath}/nocr/healthActionMenu.html"></a>
			</sec:authorize>
			<fmt:message key="healthLabel"/>
		</h1>
		<div id="reportItems">
			<div class="reportItem">
				<sec:authorize access="hasRole('NOCR_VIEW') or hasRole('ADMIN')">
					<a class="actionMenuItem" id="allergiesActionMenuLink" href="${pageContext.request.contextPath}/nocr/allergiesActionMenu.html"></a>
				</sec:authorize>
				<fmt:message key="allergiesLabel"/>
			</div>
			<div class="reportItem">
				<sec:authorize access="hasRole('NOCR_VIEW') or hasRole('ADMIN')">
					<a class="actionMenuItem" id="labWorkActionMenuLink" href="${pageContext.request.contextPath}/nocr/labWorkActionMenu.html"></a>
				</sec:authorize>
				<fmt:message key="labWorkLabel"/>
			</div>
			<div class="reportItem">
				<sec:authorize access="hasRole('NOCR_VIEW') or hasRole('ADMIN')">
					<a class="actionMenuItem" id="externalAppointmentsActionMenuLink" href="${pageContext.request.contextPath}/nocr/externalAppointmentsActionMenu.html"></a>
				</sec:authorize>
				<fmt:message key="externalAppointmentsLabel"/>
			</div>
			<div class="reportItem">
				<sec:authorize access="hasRole('NOCR_VIEW') or hasRole('ADMIN')">
					<a class="actionMenuItem" id="internalAppointmentsActionMenuLink" href="${pageContext.request.contextPath}/nocr/internalAppointmentsActionMenu.html"></a>
				</sec:authorize>
				<fmt:message key="internalAppointmentsLabel"/>
			</div>
		</div>
	</div>
	<div class="reportContainer">
		<h1>
			<sec:authorize access="hasRole('NOCR_VIEW') or hasRole('ADMIN')">
				<a class="actionMenuItem" id="legalActionMenuLink" href="${pageContext.request.contextPath}/nocr/legalActionMenu.html"></a>
			</sec:authorize>
			<fmt:message key="legalLabel"/>
		</h1>
		<div id="reportItems">
			<div class="reportItem">
				<sec:authorize access="hasRole('NOCR_VIEW') or hasRole('ADMIN')">
					<a class="actionMenuItem" id="courtCasesActionMenuLink" href="${pageContext.request.contextPath}/nocr/courtCasesActionMenu.html"></a>
				</sec:authorize>
				<fmt:message key="courtCasesLabel"/>
			</div>
			<div class="reportItem">
				<sec:authorize access="hasRole('NOCR_VIEW') or hasRole('ADMIN')">
					<a class="actionMenuItem" id="courtDocumentsActionMenuLink" href="${pageContext.request.contextPath}/nocr/courtDocumentsActionMenu.html"></a>
				</sec:authorize>
				<fmt:message key="courtDocumentsLabel"/>
			</div>
			<div class="reportItem">
				<sec:authorize access="hasRole('NOCR_VIEW') or hasRole('ADMIN')">
					<a class="actionMenuItem" id="courtOrderedConditionsActionMenuLink" href="${pageContext.request.contextPath}/nocr/courtOrderedConditionsActionMenu.html"></a>
				</sec:authorize>
				<fmt:message key="courtOrderedConditionsLabel"/>
			</div>
			<div class="reportItem">
				<sec:authorize access="hasRole('DETAINER_NOTIFICATION_VIEW') or hasRole('ADMIN')">
					<a class="actionMenuItem" id="detainersAndNotificationsActionMenuLink" href="${pageContext.request.contextPath}/nocr/detainersAndNotificationsActionMenu.html"></a>
				</sec:authorize>
				<fmt:message key="detainersAndNotificationsLabel"/>
			</div>
			<div class="reportItem">
				<sec:authorize access="hasRole('MISDEMEANOR_CITATION_VIEW') or hasRole('ADMIN')">
					<a class="actionMenuItem" id="misdemeanorCitationsActionMenuLink" href="${pageContext.request.contextPath}/nocr/misdemeanorCitationsActionMenu.html"></a>
				</sec:authorize>
				<fmt:message key="misdemeanorCitationsLabel"/>
			</div>
			<div class="reportItem">
				<sec:authorize access="hasRole('NOCR_VIEW') or hasRole('ADMIN')">
					<a class="actionMenuItem" id="offensesActionMenuLink" href="${pageContext.request.contextPath}/nocr/offensesActionMenu.html"></a>
				</sec:authorize>
				<fmt:message key="offensesLabel"/>
			</div>
			<div class="reportItem">
				<sec:authorize access="hasRole('NOCR_VIEW') or hasRole('ADMIN')">
					<a class="actionMenuItem" id="pendingChargesActionMenuLink" href="${pageContext.request.contextPath}/nocr/pendingChargesActionMenu.html"></a>
				</sec:authorize>
				<fmt:message key="pendingChargesLabel"/>
			</div>
			<div class="reportItem">
				<sec:authorize access="hasRole('PRESENTENCE_INVESTIGATION_REQUEST_VIEW') or hasRole('ADMIN')">
					<a class="actionMenuItem" id="presentenceInvestigationsActionMenuLink" href="${pageContext.request.contextPath}/nocr/presentenceInvestigationsActionMenu.html"></a>
				</sec:authorize>
				<fmt:message key="presentenceInvestigationsLabel"/>
			</div>
			<div class="reportItem">
				<sec:authorize access="hasRole('NOCR_VIEW') or hasRole('ADMIN')">
					<a class="actionMenuItem" id="sentenceCalculationsActionMenuLink" href="${pageContext.request.contextPath}/nocr/sentenceCalculationsActionMenu.html"></a>
				</sec:authorize>
				<fmt:message key="sentenceCalculationsLabel"/>
			</div>
		</div>
	</div>
	<div class="reportContainer">
		<h1>
			<sec:authorize access="hasRole('PLACEMENT_PROFILE_VIEW') or hasRole('ADMIN')">
				<a class="actionMenuItem" id="placementActionMenuLink" href="${pageContext.request.contextPath}/nocr/placementActionMenu.html"></a>
			</sec:authorize>
			<fmt:message key="placementLabel"/>
		</h1>
		<div id="reportItems">
			<div class="reportItem">
				<sec:authorize access="hasRole('BED_PLACEMENT_VIEW') or hasRole('ADMIN')">
					<a class="actionMenuItem" id="bedAssignmentActionMenuLink" href="${pageContext.request.contextPath}/nocr/bedAssignmentActionMenu.html"></a>
				</sec:authorize>
				<fmt:message key="bedAssignmentLabel"/>
			</div>
			<div class="reportItem">
				<sec:authorize access="hasRole('NOCR_VIEW') or hasRole('ADMIN')">
					<a class="actionMenuItem" id="commitStatusActionMenuLink" href="${pageContext.request.contextPath}/nocr/commitStatusActionMenu.html"></a>
				</sec:authorize>
				<fmt:message key="commitStatusLabel"/>
			</div>
			<div class="reportItem">
				<sec:authorize access="hasRole('CORRECTIONAL_STATUS_TERM_VIEW') or hasRole('ADMIN')">
					<a class="actionMenuItem" id="correctionalStatusActionMenuLink" href="${pageContext.request.contextPath}/nocr/correctionalStatusActionMenu.html"></a>
				</sec:authorize>
				<fmt:message key="correctionalStatusLabel"/>
			</div>
			<div class="reportItem">
				<sec:authorize access="hasRole('LOCATION_TERM_VIEW') or hasRole('ADMIN')">
					<a class="actionMenuItem" id="locationActionMenuLink" href="${pageContext.request.contextPath}/nocr/locationActionMenu.html"></a>
				</sec:authorize>
				<fmt:message key="locationLabel"/>
			</div>
			<div class="reportItem">
				<sec:authorize access="hasRole('PLACEMENT_TERM_VIEW') or hasRole('ADMIN')">
					<a class="actionMenuItem" id="placementSubActionMenuLink" href="${pageContext.request.contextPath}/nocr/placementSubActionMenu.html"></a>
				</sec:authorize>
				<fmt:message key="placementLabel"/>
			</div>
			<div class="reportItem">
				<sec:authorize access="hasRole('PROGRAM_PLACEMENT_CHANGE_VIEW') or hasRole('ADMIN')">
					<a class="actionMenuItem" id="programPlacementActionMenuLink" href="${pageContext.request.contextPath}/nocr/programPlacementActionMenu.html"></a>
				</sec:authorize>
				<fmt:message key="programPlacementLabel"/>
			</div>
			<div class="reportItem">
				<sec:authorize access="hasRole('NOCR_VIEW') or hasRole('ADMIN')">
					<a class="actionMenuItem" id="screeningActionMenuLink" href="${pageContext.request.contextPath}/nocr/screeningActionMenu.html"></a>
				</sec:authorize>
				<fmt:message key="screeningLabel"/>
			</div>
			<div class="reportItem">
				<sec:authorize access="hasRole('WORK_ASSIGNMENT_VIEW') or hasRole('ADMIN')">
					<a class="actionMenuItem" id="workAssignmentsActionMenuLink" href="${pageContext.request.contextPath}/nocr/workAssignmentsActionMenu.html"></a>
				</sec:authorize>
				<fmt:message key="workAssignmentsLabel"/>
			</div>
		</div>
	</div>
	<div class="reportContainer">
		<h1>
			<sec:authorize access="hasRole('OFFENDER_VIEW') or hasRole('ADMIN')">
				<a class="actionMenuItem" id="relationshipsActionMenuLink" href="${pageContext.request.contextPath}/nocr/relationshipsActionMenu.html"></a>
			</sec:authorize>
			<fmt:message key="relationshipsLabel"/>
		</h1>
		<div id="reportItems">
			<div class="reportItem">
				<sec:authorize access="hasRole('CRIMINAL_ASSOCIATION_VIEW') or hasRole('ADMIN')">
					<a class="actionMenuItem" id="criminalAssociatesActionMenuLink" href="${pageContext.request.contextPath}/nocr/criminalAssociatesActionMenu.html"></a>
				</sec:authorize>
				<fmt:message key="criminalAssociatesLabel"/>
			</div>
			<div class="reportItem">
				<sec:authorize access="hasRole('FAMILY_ASSOCIATION_VIEW') or hasRole('ADMIN')">
					<a class="actionMenuItem" id="familyActionMenuLink" href="${pageContext.request.contextPath}/nocr/familyActionMenu.html"></a>
				</sec:authorize>
				<fmt:message key="familyLabel"/>
			</div>
			<div class="reportItem">
				<sec:authorize access="hasRole('VICTIM_ASSOCIATION_VIEW') or hasRole('ADMIN')">
					<a class="actionMenuItem" id="victimsActionMenuLink" href="${pageContext.request.contextPath}/nocr/victimsActionMenu.html"></a>
				</sec:authorize>
				<fmt:message key="victimsLabel"/>
			</div>
			<div class="reportItem">
				<sec:authorize access="hasRole('VISIT_LIST') or hasRole('ADMIN')">
					<a class="actionMenuItem" id="visitationActionMenuLink" href="${pageContext.request.contextPath}/nocr/visitationActionMenu.html"></a>
				</sec:authorize>
				<fmt:message key="visitationLabel"/>
			</div>
		</div>
	</div>
	<div class="reportContainer">
		<h1>
			<sec:authorize access="hasRole('OFFENDER_VIEW') or hasRole('ADMIN')">
				<a class="actionMenuItem" id="safetyActionMenuLink" href="${pageContext.request.contextPath}/nocr/safetyActionMenu.html"></a>
			</sec:authorize>
			<fmt:message key="safetyLabel"/>
		</h1>
		<div id="reportItems">
			<div class="reportItem">
				<sec:authorize access="hasRole('ADA_ACCOMMODATION_VIEW') or hasRole('ADA_ACCOMMODATION_ISSUANCE_VIEW') or hasRole('ADMIN')">
					<a class="actionMenuItem" id="adaAccommodationsActionMenuLink" href="${pageContext.request.contextPath}/nocr/adaAccommodationsActionMenu.html"></a>
				</sec:authorize>
				<fmt:message key="adaAccommodationsLabel"/>
			</div>
			<div class="reportItem">
				<sec:authorize access="hasRole('ALERT_VIEW') or hasRole('ADMIN')">
					<a class="actionMenuItem" id="alertsActionMenuLink" href="${pageContext.request.contextPath}/nocr/alertsActionMenu.html"></a>
				</sec:authorize>
				<fmt:message key="alertsLabel"/>
			</div>
			<div class="reportItem">
				<sec:authorize access="hasRole('CAUTION_VIEW') or hasRole('ADMIN')">
					<a class="actionMenuItem" id="cautionsActionMenuLink" href="${pageContext.request.contextPath}/nocr/cautionsActionMenu.html"></a>
				</sec:authorize>
				<fmt:message key="cautionsLabel"/>
			</div>
			<div class="reportItem">
				<sec:authorize access="hasRole('INCIDENT_STATEMENT_VIEW') or hasRole('ADMIN')">
					<a class="actionMenuItem" id="incidentsActionMenuLink" href="${pageContext.request.contextPath}/nocr/incidentsActionMenu.html"></a>
				</sec:authorize>
				<fmt:message key="incidentsLabel"/>
			</div>
			<div class="reportItem">
				<sec:authorize access="hasRole('STG_AFFILIATION_VIEW') or hasRole('ADMIN')">
					<a class="actionMenuItem" id="securityThreatGroupsActionMenuLink" href="${pageContext.request.contextPath}/nocr/securityThreatGroupsActionMenu.html"></a>
				</sec:authorize>
				<fmt:message key="securityThreatGroupsLabel"/>
			</div>
			<div class="reportItem">
				<sec:authorize access="hasRole('SEPARATION_NEED_VIEW') or hasRole('ADMIN')">
					<a class="actionMenuItem" id="separationNeedsActionMenuLink" href="${pageContext.request.contextPath}/nocr/separationNeedsActionMenu.html"></a>
				</sec:authorize>
				<fmt:message key="separationNeedsLabel"/>
			</div>
			<div class="reportItem">
				<sec:authorize access="hasRole('SPECIAL_NEED_VIEW') or hasRole('ADMIN')">
					<a class="actionMenuItem" id="specialManagementDesignationsActionMenuLink" href="${pageContext.request.contextPath}/nocr/specialManagementDesignationsActionMenu.html"></a>
				</sec:authorize>
				<fmt:message key="specialManagementDesignationsLabel"/>
			</div>
			<div class="reportItem">
				<sec:authorize access="hasRole('TIER_DESIGNATION_VIEW') or hasRole('ADMIN')">
					<a class="actionMenuItem" id="tierDesignationsActionMenuLink" href="${pageContext.request.contextPath}/nocr/tierDesignationsActionMenu.html"></a>
				</sec:authorize>
				<fmt:message key="tierDesignationsLabel"/>
			</div>
			<div class="reportItem">
				<sec:authorize access="hasRole('WORK_RESTRICTION_VIEW') or hasRole('ADMIN')">
					<a class="actionMenuItem" id="workRestrictionsActionMenuLink" href="${pageContext.request.contextPath}/nocr/workRestrictionsActionMenu.html"></a>
				</sec:authorize>
				<fmt:message key="workRestrictionsLabel"/>
			</div>
		</div>
	</div>
</body>
</fmt:bundle>
</html>