<%--
  - Summary of offender.
  - 
  - This page requires an object named "offenderSummary" with the properties
  - "offenderLastName", "offenderFirstName", "offenderMiddleName",
  - "offenderSuffix", "offenderNumber".
  -
  - Author: Stephen Abson
  --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
${offenderSummary.offenderLastName}, ${offenderSummary.offenderFirstName}<c:if test="${not empty offenderSummary.offenderMiddleName}"> ${fn:substring(offenderSummary.offenderMiddleName, 0, 1)}</c:if> #${offenderSummary.offenderNumber}