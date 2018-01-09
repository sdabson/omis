<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<fmt:bundle basename="omis.substance.msgs.substance">
	<c:forEach var="substanceSummary" items="${summaries}" varStatus="status">
		<c:set var="substanceSummary" value="${substanceSummary}" scope="request"/>
		<tr class="sample">
			<td>
				<a class="actionMenuItem rowActionMenuLink" id="summaryActionMenuLink${status.index}" href="${pageContext.request.contextPath}/substanceTest/substanceTestsRowActionMenu.html?offender=${offender.id}&&sample=${substanceSummary.sampleId}&&request=${substanceSummary.substanceTestSampleRequestId}&&substanceTest=${substanceSummary.substanceTestId}"></a>
			</td>
			<c:choose>
				<c:when test="${not empty substanceSummary.substanceTestSampleRequestId and empty substanceSummary.sampleId and empty substanceSummary.sampleRequestStatusReason}">
					<fmt:formatDate value="${substanceSummary.substanceTestSampleRequestDate}" pattern="MM/dd/YYYY" var="requestDate"/>
					<td colspan="6">
						<fmt:message key="offenderSelectedForRandomLabel">
							<fmt:param><c:out value="${requestDate}"/></fmt:param>
						</fmt:message>
					</td>
				</c:when>
				<c:when test="${not empty substanceSummary.substanceTestSampleRequestId and not empty substanceSummary.sampleRequestStatusReason}">
					<td colspan="6">
						<fmt:formatDate value="${substanceSummary.substanceTestSampleRequestDate}" pattern="MM/dd/YYYY" var="requestDate"/>
						<fmt:message key="offenderSelectedForRandomLabel">
								<fmt:param><c:out value="${requestDate}"/></fmt:param>
							</fmt:message>
						<fmt:message key="requestResolutionReasonLabel">
							<fmt:param><c:out value="${substanceSummary.sampleRequestStatusReason}"/></fmt:param>
						</fmt:message>
					</td>
				</c:when>
				<c:otherwise>
					<td>
						<fmt:formatDate value="${substanceSummary.sampleDate}" pattern="MM/dd/YYYY"/>
					</td>
					<td>
						<c:out value="${substanceSummary.collectionMethodName}"/>
					</td>
					<c:choose>
						<c:when test="${empty substanceSummary.substanceTestId}">
							<td colspan="2">
							</td>
						</c:when>
						<c:otherwise>
							<td>
								<c:choose>
									<c:when test="${not empty substanceSummary.crimeLabResultName}">
										<c:out value="${substanceSummary.crimeLabResultName}"/>
									</c:when>
									<c:otherwise>
										<c:out value="${substanceSummary.testResultName}"/>
									</c:otherwise>
								</c:choose>
							</td>
							<td>
								<fmt:formatDate value="${substanceSummary.testDate}" pattern="MM/dd/YYYY"/>
							</td>
						</c:otherwise>
					</c:choose>
					<td>
						<c:out value="${substanceSummary.substanceTestReason}"/>
					</td>
					<td>
						<c:choose>
							<c:when test="${not empty substanceSummary.crimeLabResultDate}">
								<fmt:formatDate var="verificationLabResultDate" value="${substanceSummary.crimeLabResultDate}" pattern="MM/dd/YYYY"/>
								<fmt:message key="verificationLabResultWithDateLabel">
									<fmt:param>
										<c:out value="${verificationLabResultDate}"/>
									</fmt:param>
								</fmt:message>
							</c:when>
							<c:otherwise>
								<c:choose>
									<c:when test="${not empty substanceSummary.crimeLabRequestedDate}">
										<fmt:formatDate var="verificationLabRequestDate" value="${substanceSummary.crimeLabRequestedDate}" pattern="MM/dd/YYYY"/>
										<fmt:message key="verificationLabRequestWithDateLabel">
											<fmt:param>
												<c:out value="${verificationLabRequestDate}"/>
											</fmt:param>
										</fmt:message>
									</c:when>
									<c:otherwise>
										<fmt:message key="verificationLabNotApplicable"/>
									</c:otherwise>
								</c:choose>
							</c:otherwise>
						</c:choose>
					</td>
				</c:otherwise>
			</c:choose>
		</tr>
	</c:forEach>
</fmt:bundle>