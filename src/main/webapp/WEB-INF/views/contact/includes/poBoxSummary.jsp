<%--
  - Summary of PO box.
  -
  - Author: Joel Norris
  --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="contact" uri="/WEB-INF/tld/contact.tld" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setBundle basename="omis.contact.msgs.poBox" var="poBox"/>
<%@ taglib prefix="contact" uri="/WEB-INF/tld/contact.tld" %>
<span class="subSectionLine">
	<contact:poBoxSummary value="${poBoxSummarizable}" format="LINE1"/>
</span>
<span class="subSectionLine">
	<contact:poBoxSummary value="${poBoxSummarizable}" format="LINE2"/>
</span>