<?xml version="1.0" encoding="UTF-8"?>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<fmt:bundle basename="omis.questionnaire.msgs.questionnaire">
	<c:choose>
		<c:when test="${allowedAnswerItems[allowedAnswerItemIndex].operation eq 'REMOVE'}">
			<c:set var="removeClass" value="removeSet" />
		</c:when>
		<c:otherwise>
			<c:set var="removeClass" value="" />
		</c:otherwise>
	</c:choose>
	<div class="answerSet ${removeClass}" id="answerSet[${allowedAnswerItemIndex}]">
	<input type="hidden" id="allowedAnswerOperation[${allowedAnswerItemIndex}]" class="allowedAnswerOperation"
		 value="${allowedAnswerItem.operation}"
		 name="allowedAnswerItems[${allowedAnswerItemIndex}].operation"/>
	<input type="hidden" id="allowedAnswerItems[${allowedAnswerItemIndex}].allowedAnswer"
		 value="${allowedAnswerItems[allowedAnswerItemIndex].allowedAnswer.id}"
		 name="allowedAnswerItems[${allowedAnswerItemIndex}].allowedAnswer"/>	
		<span class="fieldGroup">
			<label class="fieldLabel">
				<a id="removeAnswerLink[${allowedAnswerItemIndex}]" class="questionAddItemLink">
					<fmt:message key="removeAnswerLink"/>
				</a>
			</label>
		</span>
		
		
		<span class="fieldGroup">
			<!-- Use Existing Answer Option -->
			<input type="radio" class="fieldOption" 
			id="allowedAnswerItems[${allowedAnswerItemIndex}].existingAnswer"
				name="allowedAnswerItems[${allowedAnswerItemIndex}].existingAnswer" value="true" 
				${allowedAnswerItems[allowedAnswerItemIndex].existingAnswer eq 'true' or not 
				allowedAnswerItems[allowedAnswerItemIndex].existingAnswer 
					? 'checked="checked"' : ''} />
			<label for="allowedAnswerItems[${allowedAnswerItemIndex}].existingAnswer">
				<fmt:message key="useExistingLabel"/>
			</label>
		</span>
			
		<span class="fieldGroup">	
			<!-- Answer Search -->
			<label class="fieldLabel">&nbsp;</label><!-- Here to properly format without having to fiddle with extra css -->
			<input type="text" class="medium" name="allowedAnswerItems[${allowedAnswerItemIndex}].answerQuery"
				 id="allowedAnswerItems[${allowedAnswerItemIndex}].answerQuery" 
				 value="<c:out value='${allowedAnswerItems[allowedAnswerItemIndex].answerValue.description}'/>"/>
			<input type="hidden" name="allowedAnswerItems[${allowedAnswerItemIndex}].answerValue" id="allowedAnswerItems[${allowedAnswerItemIndex}].answerValue"
				 value="${allowedAnswerItems[allowedAnswerItemIndex].answerValue.id}"/>
			<form:errors path="allowedAnswerItems[${allowedAnswerItemIndex}].answerValue" cssClass="error"/>
			<a id="clearAnswerLink[${allowedAnswerItemIndex}]" class="clearLink" title="<fmt:message key='clearAnswerLink' />"></a>	
		</span>
		
		<span class="fieldGroup">
			<!-- Create New Answer Option -->
			<input type="radio" class="fieldOption" 
			id="allowedAnswerItems[${allowedAnswerItemIndex}].newAnswer"
				name="allowedAnswerItems[${allowedAnswerItemIndex}].existingAnswer" value="false" 
				${allowedAnswerItems[allowedAnswerItemIndex].existingAnswer eq'false'
					? 'checked="checked"' : ''} />
			<label for="allowedAnswerItems[${allowedAnswerItemIndex}].newAnswer">
				<fmt:message key="createNewLabel"/>
			</label>
		</span>
		
		<div id="createNewAnswer[${allowedAnswerItemIndex}]">
			<span class="fieldGroup">
				<label for="allowedAnswerItems[${allowedAnswerItemIndex}].description" class="fieldLabel">
					<fmt:message key="descriptionLabel" />
				</label>
				<input type="text" 
						 id="allowedAnswerItems[${allowedAnswerItemIndex}].description" 
						 name="allowedAnswerItems[${allowedAnswerItemIndex}].description" 
						 value="<c:out value='${allowedAnswerItems[allowedAnswerItemIndex].description}'/>"/>
				<form:errors path="allowedAnswerItems[${allowedAnswerItemIndex}].description" cssClass="error"/>
			</span>
		</div>
	</div>
</fmt:bundle>