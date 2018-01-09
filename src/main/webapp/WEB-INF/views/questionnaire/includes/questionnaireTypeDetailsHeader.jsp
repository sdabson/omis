<?xml version="1.0" encoding="UTF-8"?>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<fmt:bundle basename="omis.questionnaire.msgs.questionnaire">
<fieldset>
	<span class="details">
		<label class="fieldLabel">
			<fmt:message key="questionnaireLabel" />
		</label>
		<span class="detail"><c:out value="${questionnaireTypeSummary.questionnaireTypeTitle}" /></span>
	</span>
	<span class="details">
		<label class="fieldLabel">
			<fmt:message key="categoryLabel" />
		</label>
		<span class="detail"><c:out value="${questionnaireTypeSummary.categoryDescription}" /></span>
	</span>
	<span class="details">
		<label class="fieldLabel">
			<fmt:message key="cycleLabel" />
		</label>
		<span class="detail"><c:out value="${questionnaireTypeSummary.cycle}" /></span>
	</span>
	<span class="details">
		<label class="fieldLabel">
			<fmt:message key="startDateLabel" />
		</label>
		<span class="detail"><fmt:formatDate value="${questionnaireTypeSummary.startDate}" pattern="MM/dd/yyyy" /></span>
	</span>
	<span class="details">
		<label class="fieldLabel">
			<fmt:message key="endDateLabel" />
		</label>
		<span class="detail"><fmt:formatDate value="${questionnaireTypeSummary.endDate}" pattern="MM/dd/yyyy" /></span>
	</span>
</fieldset>
</fmt:bundle>
</html>