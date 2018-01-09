<%-- Author: Ryan Johns
 - Version: 0.1.0 (Feb 10, 2015)
 - Since: OMIS 3.0 --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<fmt:setBundle basename="omis.msgs.common" var="common"/>
<fmt:bundle basename="omis.placementscreening.msts.form">
<c:set var="isRemoved" value="${treatmentScreeningItem.itemOperation == 'REMOVE' ? 'removed' : '' }"/>
<tr class="${isRemoved}">
	<td>
		<a id="removeTreatmentLink${index}" class="removeLink"></a>
		<input type="hidden" name="treatmentScreeningItems[${index}].itemOperation" class="itemOperation" value="<c:out value="${treatmentScreeningItem.itemOperation}"/>"/>
		<form:errors path="treatmentScreeningItems[${index}].itemOperation" cssClass="error"/>
		<input type="hidden" class="referralScreening" name="treatmentScreeningItems[${index}].referralScreening" value="<c:out value="${treatmentScreeningItem.referralScreening}"/>"/>
		<form:errors path="treatmentScreeningItems[${index}].referralScreening" cssClass="error"/>
	</td>
	<td>
		<span class="fieldGroup">
			<a id="upLink[${index}]t" class="upLink"></a>
			<a id="downLink[${index}]t" class="downLink"></a>
			<input id="treatmentOrder[${index}]" name="treatmentScreeningItems[${index}].order" size="1" value="<c:out value="${treatmentScreeningItem.order}"/>" readonly="readonly"/>
			<form:errors path="treatmentScreeningItems[${index}].order" cssClass="error"/>
		</span>
	</td>
	<td>
		<span class="fieldGroup">
			<c:choose>
			<c:when test="${empty treatmentScreeningItem.referralScreeningCenter}">
				<select name="treatmentScreeningItems[${index}].referralScreeningCenter">
					<option value=""><fmt:message key="nullLabel" bundle="${common}"/></option>
					<c:forEach items="${treatmentScreeningCenters}" var="treatmentScreeningCenter">
						<c:choose>
							<c:when test="${treatmentScreeningCenter eq treatmentScreeningItem.referralScreeningCenter}" >
								<option value="${treatmentScreeningCenter.id}" selected>
								<c:out value="${treatmentScreeningCenter.facility.name}"/>
							</option>
							</c:when>
							<c:otherwise>
								<option value="${treatmentScreeningCenter.id}">
								<c:out value="${treatmentScreeningCenter.facility.name}"/>
							</option>
							</c:otherwise>
						</c:choose>
					</c:forEach>
				</select>
			</c:when>
			<c:otherwise>
				<input type="hidden" name="treatmentScreeningItems[${index}].referralScreeningCenter" value="<c:out value="${treatmentScreeningItem.referralScreeningCenter.id}"/>"/>
				<span><c:out value="${treatmentScreeningItem.referralScreeningCenter.facility.name}"/></span>
			</c:otherwise>
			</c:choose>
			<form:errors path="treatmentScreeningItems[${index}].referralScreeningCenter" cssClass="error"/>
		</span>
	</td>
</tr>	
</fmt:bundle>