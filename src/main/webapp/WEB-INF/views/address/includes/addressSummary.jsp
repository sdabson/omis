<%--
  - Summary of address.
  -
  - Author: Stephen Abson
  --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="address" uri="/WEB-INF/tld/address.tld" %>
<span class="subSectionLine">
	<address:formatSummary value="${addressSummarizable}" format="LINE1"/>
</span>
<span class="subSectionLine">
	<address:formatSummary value="${addressSummarizable}" format="LINE2"/>
</span>