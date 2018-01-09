<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<sec:authorize var="editPresentenceInvestigationRequest" access="hasRole('PRESENTENCE_INVESTIGATION_VIEW') or hasRole('ADMIN')"/>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<fmt:setBundle basename="omis.offendercontact.msgs.offendercontact" var="offenderContactBundle"/>
<fmt:bundle basename="omis.presentenceinvestigation.msgs.presentenceInvestigationHome">
		
		<ul id="psiLinks">
		<c:choose>
			<c:when test="${empty offender}">
				<li class="psiLink">
					<a class="button" href="${pageContext.request.contextPath}/offender/create.html?person=${summary.offenderId}">
					<fmt:message key="createOffenderLink">
						<fmt:param value="${summary.offenderLastName}, ${summary.offenderFirstName}" />
					</fmt:message>
					
					</a>
				</li>
				
			</c:when>
			<c:when test="${not empty offender}">
				<li class="psiLink">
					<a class="newTab tabs2 button" href="${pageContext.request.contextPath}/offenderContact/edit.html?offender=${summary.offenderId}">
						<fmt:message key='offenderContactInformationLink' />
					</a>
					<input class="moduleCheckBox" type="checkbox" disabled="disabled" />
				</li>
				<li class="psiLink">
					<a class="newTab tabs2 button" href="${pageContext.request.contextPath}/offender/demographics/edit.html?offender=${summary.offenderId}">
						<fmt:message key='demographicsLink'/>
					</a>
					<input class="moduleCheckBox" type="checkbox" disabled="disabled" />
				</li>
				<li class="psiLink">
					<a class="newTab tabs2 button" href="${pageContext.request.contextPath}/citation/list.html?offender=${summary.offenderId}">
						<fmt:message key='misdemeanorCitationsLink'/>
					</a>
					<input class="moduleCheckBox" type="checkbox" disabled="disabled" />
				</li>
				<li class="psiLink">
					<a class="newTab tabs2 button" href="${pageContext.request.contextPath}/employment/list.html?offender=${summary.offenderId}">
						<fmt:message key='employmentLink'/>
					</a>
					<input class="moduleCheckBox" type="checkbox" disabled="disabled" />
				</li>
				<li class="psiLink">
					<a class="newTab tabs2 button" href="${pageContext.request.contextPath}/offender/personalDetails/edit.html?offender=${summary.offenderId}">
						<fmt:message key='identityLink'/>
					</a>
					<input class="moduleCheckBox" type="checkbox" disabled="disabled" />
				</li>
				<li class="psiLink">
					<a class="newTab tabs2 button" href="${pageContext.request.contextPath}/education/list.html?offender=${summary.offenderId}">
						<fmt:message key='educationLink'/>
					</a>
					<input class="moduleCheckBox" type="checkbox" disabled="disabled" />
				</li>
				<li class="psiLink">
					<a class="newTab tabs2 button" href="${pageContext.request.contextPath}/military/list.html?offender=${summary.offenderId}">
						<fmt:message key='militaryLink'/>
					</a>
					<input class="moduleCheckBox" type="checkbox" disabled="disabled" />
				</li>
				<li class="psiLink">
					<a class="newTab tabs2 button" href="${pageContext.request.contextPath}/financial/edit.html?offender=${summary.offenderId}">
						<fmt:message key='financialProfileLink'/>
					</a>
					<input class="moduleCheckBox" type="checkbox" disabled="disabled" />
				</li>
				<li class="psiLink">
					<a class="newTab tabs2 button" href="${pageContext.request.contextPath}/detainerNotification/list.html?offender=${summary.offenderId}">
						<fmt:message key='detainersAndNotificationsLink'/>
					</a>
					<input class="moduleCheckBox" type="checkbox" disabled="disabled" />
				</li>
				<li class="psiLink">
					<a class="newTab tabs2 button" href="${pageContext.request.contextPath}/criminalAssociation/list.html?offender=${summary.offenderId}">
						<fmt:message key='criminalAssociatesLink'/>
					</a>
					<input class="moduleCheckBox" type="checkbox" disabled="disabled" />
				</li>

				<li class="psiLink">
					<a class="newTab tabs2 button" href="${pageContext.request.contextPath}/offenderPhoto/list.html?offender=${summary.offenderId}">
						<fmt:message key='mugshotsLink'/>
					</a>
					<input class="moduleCheckBox" type="checkbox" disabled="disabled" />
				</li>
				<li class="psiLink">
					<a class="newTab tabs2 button" href="${pageContext.request.contextPath}/family/list.html?offender=${summary.offenderId}">
						<fmt:message key='familyLink'/>
					</a>
					<input class="moduleCheckBox" type="checkbox" disabled="disabled" />
				</li>
				<li class="psiLink">
					<a class="newTab tabs2 button" href="${pageContext.request.contextPath}/victim/association/listByOffender.html?offender=${summary.offenderId}">
						<fmt:message key='victimsLink'/>
					</a>
					<input class="moduleCheckBox" type="checkbox" disabled="disabled" />
				</li>
				<li class="psiLink">
					<a class="newTab tabs2 button" href="${pageContext.request.contextPath}/residence/list.html?offender=${summary.offenderId}">
						<fmt:message key='residencesLink'/>
					</a>
					<input class="moduleCheckBox" type="checkbox" disabled="disabled" />
				</li>

				<li class="psiLink">
					<a class="newTab tabs2 button" href="${pageContext.request.contextPath}/offender/identity/alternative/list.html?offender=${summary.offenderId}">
						<fmt:message key='alternativeNamesLink'/>
					</a>
					<input class="moduleCheckBox" type="checkbox" disabled="disabled" />
				</li>
				<li class="psiLink">
					<a class="newTab tabs2 button" href="${pageContext.request.contextPath}/identificationNumber/list.html?offender=${summary.offenderId}">
						<fmt:message key='identificationNumbersLink'/>
					</a>
					<input class="moduleCheckBox" type="checkbox" disabled="disabled" />
				</li>
				<%--<li class="moduleLink">
					<input type="button" style="width:225px"
					onclick="location.href='${pageContext.request.contextPath}/questionnaire/administeredQuestionnaireList.html?offender=${summary.offenderId}&questionnaireType='"
						value="<fmt:message key='questionnaireLink'/>">
					<input class="moduleCheckBox" type="checkbox" title="test" />
				</li>--%>
			</c:when>
		</c:choose>
		</ul>
</fmt:bundle>