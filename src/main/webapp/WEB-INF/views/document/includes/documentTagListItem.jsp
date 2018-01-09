<%-- Author: Ryan Johns
 - Version: 0.1.0 (Dec 22, 2015)
 - Since: OMIS 3.0 --%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:bundle basename="omis.document.msgs.document">
<li id="documentTagItems${index}_row" class="documentTagRow">
	<span>
		<a id="documentTagItems${index}" class="tagRemoveLink removeLink"></a>
	</span>
	<span>
		<input type="hidden" id="documentTagItems${index}_documentTag" name="documentTagItems[${index}].documentTag" value="${documentTagItem.documentTag.id}"/>
		<input type="text" id="documentTagItems${index}_name" name="documentTagItems[${index}].name" value="${documentTagItem.name}"/>
		<input type="hidden" id="documentTagItems${index}_operation" name="documentTagItems[${index}].documentTagOperation" value="${documentTagItem.documentTagOperation}"/>
		<form:errors path="documentTagItems[${index}].name" cssClass="error"/>
	</span>
</li>
</fmt:bundle>