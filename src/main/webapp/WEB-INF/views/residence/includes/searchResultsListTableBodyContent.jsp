<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="address" uri="/WEB-INF/tld/address.tld" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<fmt:setBundle basename="omis.residence.msgs.residence" var="residenceBundle"/>
<c:forEach var="residenceSearchSummary" items="${searchSummaries}">
<%-- 	<c:if test="${not empty residenceSearchSummary.personId}"> --%>
		<tr> 
			<td></td><td id="displayPersonName">
				<fmt:message key="fullNameLabel" bundle="${residenceBundle}">
					<c:choose>
						<c:when test="${not empty residenceSearchSummary.personMiddleName}">
							<c:set value="${residenceSearchSummary.personMiddleName}" var="middleName"/>
						</c:when>
						<c:otherwise>
							<c:set value="" var="middleName"/>
						</c:otherwise>
					</c:choose>			
					<fmt:param value="${residenceSearchSummary.personLastName}"/>
					<fmt:param value="${residenceSearchSummary.personFirstName}"/>
					<fmt:param value="${middleName}"/>			
				</fmt:message>
			</td>
			<td><c:if test="${residenceSearchSummary.address == true}">
					<address:formatSummary value="${residenceSearchSummary}" format="LINE1"/> <address:formatSummary value="${residenceSearchSummary}" format="LINE2"/>
				</c:if>
			</td>
			<td>
			<fmt:message key="termDateLabel" bundle="${residenceBundle}">
					<c:choose>
						<c:when test="${residenceSearchSummary.termStartDate ne null}">
							<fmt:formatDate value="${residenceSearchSummary.termStartDate}" var="termStartDate" pattern="MM/dd/yyyy"/>
						</c:when>
						<c:otherwise>
							<c:set value="" var="termStartDate"/>
						</c:otherwise>
					</c:choose>	
					<c:choose>
						<c:when test="${residenceSearchSummary.termEndDate ne null}">
							<fmt:formatDate value="${residenceSearchSummary.termEndDate}" var="termEndDate" pattern="MM/dd/yyyy"/>
						</c:when>
						<c:otherwise>
							<c:set value="" var="termEndDate"/>
						</c:otherwise>
					</c:choose>	
					<fmt:param value="${termStartDate}"/>		
					<fmt:param value="${termEndDate}"/>		
				</fmt:message>
			</td>
			<td>
				<c:out value="${residenceSearchSummary.confirmed}"/>
			</td>
		</tr>
<%-- 	</c:if> --%>
</c:forEach>