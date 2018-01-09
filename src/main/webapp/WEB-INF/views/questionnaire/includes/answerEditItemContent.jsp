<?xml version="1.0" encoding="UTF-8"?>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<fmt:bundle basename="omis.questionnaire.msgs.questionnaire">
	<c:choose>
		<c:when test="${questionAnswerEditItems[questionAnswerEditItemIndex].allowedAnswerItems[allowedAnswerItemIndex].operation eq 'REMOVE'}">
			<c:set var="removeClass" value="removeSet" />
		</c:when>
		<c:otherwise>
			<c:set var="removeClass" value="" />
		</c:otherwise>
	</c:choose>
	<div class="answerSet ${removeClass}" id="answerSet[${questionAnswerEditItemIndex}][${allowedAnswerItemIndex}]">
	<input type="hidden" id="allowedAnswerOperation[${questionAnswerEditItemIndex}][${allowedAnswerItemIndex}]" class="allowedAnswerOperation"
		 value="${allowedAnswerItem.operation}"
		 name="questionAnswerEditItems[${questionAnswerEditItemIndex}].allowedAnswerItems[${allowedAnswerItemIndex}].operation"/>
	<input type="hidden" id="questionAnswerEditItems[${questionAnswerEditItemIndex}].allowedAnswerItems[${allowedAnswerItemIndex}].allowedAnswer"
		 value="${questionAnswerEditItems[questionAnswerEditItemIndex].allowedAnswerItems[allowedAnswerItemIndex].allowedAnswer.id}"
		 name="questionAnswerEditItems[${questionAnswerEditItemIndex}].allowedAnswerItems[${allowedAnswerItemIndex}].allowedAnswer"/>	
		<span class="fieldGroup">
			<label class="fieldLabel">
				<a id="removeAnswerLink[${questionAnswerEditItemIndex}][${allowedAnswerItemIndex}]" class="questionAddItemLink">
					<fmt:message key="removeAnswerLink"/>
				</a>
			</label>
		</span>
		
		
		<span class="fieldGroup">
			<!-- Use Existing Answer Option -->
			<input type="radio" class="fieldOption" 
			id="questionAnswerEditItems[${questionAnswerEditItemIndex}].allowedAnswerItems[${allowedAnswerItemIndex}].existingAnswer"
				name="questionAnswerEditItems[${questionAnswerEditItemIndex}].allowedAnswerItems[${allowedAnswerItemIndex}].existingAnswer" value="true" 
				${questionAnswerEditItems[questionAnswerEditItemIndex].allowedAnswerItems[allowedAnswerItemIndex].existingAnswer eq 'true' or not 
				questionAnswerEditItems[questionAnswerEditItemIndex].allowedAnswerItems[allowedAnswerItemIndex].existingAnswer 
					? 'checked="checked"' : ''} />
			<label for="questionAnswerEditItems[${questionAnswerEditItemIndex}].allowedAnswerItems[${allowedAnswerItemIndex}].existingAnswer">
				<fmt:message key="useExistingLabel"/>
			</label>
		</span>
			
		<span class="fieldGroup">	
			<!-- Answer Search -->
			<label class="fieldLabel">&nbsp;</label><!-- Here to properly format without having to fiddle with extra css -->
			<input type="text" class="medium" name="questionAnswerEditItems[${questionAnswerEditItemIndex}].allowedAnswerItems[${allowedAnswerItemIndex}].answerQuery"
				 id="questionAnswerEditItems[${questionAnswerEditItemIndex}].allowedAnswerItems[${allowedAnswerItemIndex}].answerQuery" 
				 value="<c:out value='${questionAnswerEditItems[questionAnswerEditItemIndex].allowedAnswerItems[allowedAnswerItemIndex].answerValue.description}'/>"/>
			<input type="hidden" name="questionAnswerEditItems[${questionAnswerEditItemIndex}].allowedAnswerItems[${allowedAnswerItemIndex}].answerValue" id="questionAnswerEditItems[${questionAnswerEditItemIndex}].allowedAnswerItems[${allowedAnswerItemIndex}].answerValue"
				 value="${questionAnswerEditItems[questionAnswerEditItemIndex].allowedAnswerItems[allowedAnswerItemIndex].answerValue.id}"/>
			<form:errors path="questionAnswerEditItems[${questionAnswerEditItemIndex}].allowedAnswerItems[${allowedAnswerItemIndex}].answerValue" cssClass="error"/>
			<a id="clearAnswerLink[${questionAnswerEditItemIndex}][${allowedAnswerItemIndex}]" class="clearLink" title="<fmt:message key='clearAnswerLink' />"></a>	
		</span>
		
		<span class="fieldGroup">
			<!-- Create New Answer Option -->
			<input type="radio" class="fieldOption" 
			id="questionAnswerEditItems[${questionAnswerEditItemIndex}].allowedAnswerItems[${allowedAnswerItemIndex}].newAnswer"
				name="questionAnswerEditItems[${questionAnswerEditItemIndex}].allowedAnswerItems[${allowedAnswerItemIndex}].existingAnswer" value="false" 
				${questionAnswerEditItems[questionAnswerEditItemIndex].allowedAnswerItems[allowedAnswerItemIndex].existingAnswer eq'false'
					? 'checked="checked"' : ''} />
			<label for="questionAnswerEditItems[${questionAnswerEditItemIndex}].allowedAnswerItems[${allowedAnswerItemIndex}].newAnswer">
				<fmt:message key="createNewLabel"/>
			</label>
		</span>
		
		<div id="createNewAnswer[${questionAnswerEditItemIndex}][${allowedAnswerItemIndex}]">
			<span class="fieldGroup">
				<label for="questionAnswerEditItems[${questionAnswerEditItemIndex}].allowedAnswerItems[${allowedAnswerItemIndex}].description" class="fieldLabel">
					<fmt:message key="descriptionLabel" />
				</label>
				<input type="text" 
						 id="questionAnswerEditItems[${questionAnswerEditItemIndex}].allowedAnswerItems[${allowedAnswerItemIndex}].description" 
						 name="questionAnswerEditItems[${questionAnswerEditItemIndex}].allowedAnswerItems[${allowedAnswerItemIndex}].description" 
						 value="<c:out value='${questionAnswerEditItems[questionAnswerEditItemIndex].allowedAnswerItems[allowedAnswerItemIndex].description}'/>"/>
				<form:errors path="questionAnswerEditItems[${questionAnswerEditItemIndex}].allowedAnswerItems[${allowedAnswerItemIndex}].description" cssClass="error"/>
			</span>
		</div>
	</div>
</fmt:bundle>