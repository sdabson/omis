<%-- Author: Ryan Johns
 - Version: 0.1.0 (Feb 10, 2015)
 - Since: OMIS 3.0 --%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
 <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<fmt:setBundle basename="omis.msgs.common" var="common"/>
<fmt:bundle basename="omis.placementscreening.msgs.form">
 <tr <c:if test="${preReleaseScreeningItem.itemOperation == 'REMOVE'}">class="removed"</c:if>>
 	<td>
 		<a id="removepreReleaseLink${index}" class="removeLink"></a>
 		<input type="hidden" name="prereleaseScreeningItems[${index}].itemOperation" class="itemOperation" value="<c:out value="${preReleaseScreeningItem.itemOperation}"/>"/>
 		<form:errors path="prereleaseScreeningItems[${index}].itemOperation" cssClass="error"/>
 		<input type="hidden" name="prereleaseScreeningItems[${index}].referralScreening" class="referralScreening" value="<c:out value="${preReleaseScreeningItem.referralScreening.id}"/>"/>
 		<form:errors path="prereleaseScreeningItems[${index}].referralScreening" cssClass="error"/>
 	</td>
 	<td>
 		<a id="upLink[${index}]p" class="upLink"></a>
 		<a id="downLink[${index}]p" class="downLink"></a>
 		<input id="preReleaseOrder[${index}]" name="prereleaseScreeningItems[${index}].order" size="1" value="<c:out value="${preReleaseScreeningItem.order}"/>" readonly="readonly"/> 		
 		<form:errors path="prereleaseScreeningItems[${index}].order" cssClass="error"/>
 	</td>
 	<td>
 		<span class="fieldGroup">
	 		<c:choose>
	 		<c:when test="${empty preReleaseScreeningItem.referralScreeningCenter}">
	 		<select name="prereleaseScreeningItems[${index}].referralScreeningCenter">
	 			<option value=""><fmt:message key="nullLabel" bundle="${common}"/></option>
	 			<c:forEach items="${prereleaseScreeningCenters}" var="prereleaseScreeningCenter">
	 			<option value="${prereleaseScreeningCenter.id}">
	 				<c:out value="${prereleaseScreeningCenter.facility.name}"/>
	 			</option>
	 			</c:forEach>
	 		</select>
	 		</c:when>
	 		<c:otherwise>
	 		<input type="hidden" name="prereleaseScreeningItems[${index}].referralScreeningCenter" value="${preReleaseScreeningItem.referralScreeningCenter.id}"/>
	 		<c:out value="${preReleaseScreeningItem.referralScreeningCenter.facility.name}"/>
	 		</c:otherwise>
	 		</c:choose>
	 		<form:errors path="prereleaseScreeningItems[${index}].referralScreeningCenter" cssClass="error"/>
 		</span>
 	</td>
 </tr>
 </fmt:bundle>