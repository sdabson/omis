<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<fmt:bundle basename="omis.supervisionfee.msgs.supervisionFee">
<tr id="feeRequirementRow${feeRequirementIndex}" class="requirement">
<td>
<input type="hidden" name="feeRequirements[${feeRequirementIndex}].operation" id="feeRequirementOperation${feeRequirementIndex}" value="${feeRequirement.operation.name}"/>
<input type="hidden" name="feeRequirements[${feeRequirementIndex}].feeRequirementId" id="feeRequirementId${feeRequirementIndex}" value="${feeRequirement.feeRequirementId}"/>
<input type="hidden" name="feeRequirements[${feeRequirementIndex}].itemAuthority" id="feeRequirementItemAuthority${feeRequirementIndex}" value="${feeRequirement.itemAuthority.name}"/>
<input type="hidden" name="feeRequirements[${feeRequirementIndex}].feeRequirement" id="feeRequirement${feeRequirementIndex}FeeRequirement" value="${feeRequirement.feeRequirement.id}"/>

<a href="${pageContext.request.contextPath}/supervisionFee/removeFeeRequirement.html" id="removeFeeRequirement${feeRequirementIndex}" class="removeLink">
<span class="linkLabel"><fmt:message key="removeFeeRequirementLink"/></span></a>
</td>
<td>
	<input name="feeRequirements[${feeRequirementIndex}].startDate" id="feeRequirement${feeRequirementIndex}StartDate" type="text"
			class="date" value="<fmt:formatDate pattern='MM/dd/yyyy' value='${feeRequirement.startDate}'/>"/>
	<form:errors path="feeRequirements[${feeRequirementIndex}].startDate" cssClass="error"/>
</td>
<td>
	<input name="feeRequirements[${feeRequirementIndex}].endDate" id="feeRequirement${feeRequirementIndex}EndDate" type="text"
			class="date" value="<fmt:formatDate pattern='MM/dd/yyyy' value='${feeRequirement.endDate}'/>"/>
	<form:errors path="feeRequirements[${feeRequirementIndex}].endDate" cssClass="error"/>
</td>
<td>
	<input name="feeRequirements[${feeRequirementIndex}].fee" id="feeRequirement${feeRequirementIndex}AdjustedFee" 
		value="<fmt:formatNumber value='${feeRequirement.fee}'/>"/>
	<form:errors path="feeRequirements[${feeRequirementIndex}].fee" cssClass="error"/>
</td>
<td>
	<select name="feeRequirements[${feeRequirementIndex}].reason" id="feeRequirement${feeRequirementIndex}Reason">
	<option value="">...</option>
		<c:forEach var="reason" items="${reasons}">
			<c:choose>
				<c:when test="${not empty feeRequirement.reason and feeRequirement.reason eq reason}">					
					<option value="${reason.id}" selected="selected"><c:out value="${reason.name}"/></option>
				</c:when>
				<c:otherwise>
					<option value="${reason.id}"><c:out value="${reason.name}"/></option>
				</c:otherwise>
			</c:choose>
		</c:forEach>
	</select>
	<form:errors path="feeRequirements[${feeRequirementIndex}].reason" cssClass="error"/>
</td>
<td>
	<c:choose>
		<c:when test="${feeRequirement.operation.name eq 'CREATE'}">
			<c:choose>
				<c:when test="${feeRequirement.itemAuthority.name eq 'OFFICER'}">
					<input id="feeRequirement${feeRequirementIndex}Authority"/>
					<input type="hidden" id="feeRequirement${feeRequirementIndex}Officer" name="feeRequirements[${feeRequirementIndex}].officer"/>
					<a id="feeRequirement${feeRequirementIndex}OfficerClear" class="clearLink"></a>
					<span id="feeRequirement${feeRequirementIndex}OfficerCurrentLabel">
					<c:if test="${not empty supervisionFeeForm.feeRequirements[feeRequirementIndex].officer and supervisionFeeForm.feeRequirements[feeRequirementIndex].officer eq officer}">
						<c:out value="feeRequirements[${feeRequirementIndex}].officer.lastName"/>
						<c:out value="feeRequirements[${feeRequirementIndex}].officer.firstName"/>
					</c:if>
					</span>	
					<form:errors path="feeRequirements[${feeRequirementIndex}].officer" cssClass="error"/>
				</c:when>
				<c:otherwise>
			<c:choose>
				<c:when test="${feeRequirement.itemAuthority.name eq 'COURT'}">
					<select id="feeRequirement${feeRequirementIndex}Court" name="feeRequirements[${feeRequirementIndex}].court">
					<option value="">...</option>
						<c:forEach var="court" items="${courts}">
							<c:choose>
								<c:when test="${not empty supervisionFeeForm.feeRequirements[feeRequirementIndex].court and supervisionFeeForm.feeRequirements[feeRequirementIndex].court eq court}">
									<option value="${court.id}" selected="selected"><c:out value="${court.name}"/></option>
								</c:when>
								<c:otherwise>								
									<option value="${court.id}"><c:out value="${court.name}"/></option>
								</c:otherwise>
							</c:choose>
						</c:forEach>
					</select>
				<form:errors path="feeRequirements[${feeRequirementIndex}].court" cssClass="error"/>
				</c:when>					
			</c:choose>
				</c:otherwise>
			</c:choose>		
		</c:when>
	</c:choose>
	<c:choose>
		<c:when test="${feeRequirement.operation.name eq 'UPDATE' || feeRequirement.operation.name eq 'REMOVE'}">
		<c:choose>
			<c:when test="${feeRequirement.itemAuthority.name eq 'OFFICER'}">
				<c:choose>
					<c:when test="${not empty supervisionFeeForm.feeRequirements[feeRequirementIndex].officer}">
						<input type="hidden" id="feeRequirement${feeRequirementIndex}Officer" name="feeRequirements[${feeRequirementIndex}].officer" value="${feeRequirement.officer.id}"/>
						<c:out value="${feeRequirement.officer.name.lastName}, ${feeRequirement.officer.name.firstName}"/>
					</c:when>
					<c:otherwise>
						<form:errors path="feeRequirements[${feeRequirementIndex}].officer" cssClass="error"/>
					</c:otherwise>
				</c:choose>
			</c:when>
			<c:otherwise>
			<c:choose>						
				<c:when test="${feeRequirement.itemAuthority.name eq 'COURT'}">
					<c:choose>
						<c:when test="${not empty supervisionFeeForm.feeRequirements[feeRequirementIndex].court}">
							<input type="hidden" id="feeRequirement${feeRequirementIndex}Court" name="feeRequirements[${feeRequirementIndex}].court" value="${feeRequirement.court.id}"/>
							<c:out value="${feeRequirement.court.name}"/>
						</c:when>
						<c:otherwise>
						<form:errors path="feeRequirements[${feeRequirementIndex}].court" cssClass="error"/>
					</c:otherwise>	
				</c:choose>
				</c:when>
			</c:choose>
			</c:otherwise>
		</c:choose>	
		</c:when>		
	</c:choose>
</td>
<td>
	<textarea name="feeRequirements[${feeRequirementIndex}].comment" id="feeRequirement${feeRequirementIndex}Comment" rows="3" cols="40"><c:out value="${feeRequirement.comment}"/></textarea>
	<form:errors path="feeRequirements[${feeRequirementIndex}].comment" cssClass="error"/>
</td>
</tr>
</fmt:bundle>