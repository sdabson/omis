<?xml version="1.0" encoding="UTF-8"?>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<fmt:bundle basename="omis.questionnaire.msgs.questionnaire">
	<fieldset id="questionAnswerSet[${questionAnswerEditItemIndex}]" class="questionAnswerSet">
	<c:choose>
		<c:when test="${questionAnswerEditItems[questionAnswerEditItemIndex].operation eq 'REMOVE'}">
			<c:set var="removeClass" value="removeSet" />
		</c:when>
		<c:otherwise>
			<c:set var="removeClass" value="" />
		</c:otherwise>
	</c:choose>
	<div id="questionAnswerSetDiv[${questionAnswerEditItemIndex}]" class="${removeClass}">
	<input type="hidden" name="questionAnswerEditItems[${questionAnswerEditItemIndex}].sortOrder" id="questionAnswerEditItems[${questionAnswerEditItemIndex}].sortOrder" class="sortOrder" value="${questionAnswerEditItems[questionAnswerEditItemIndex].sortOrder}" />
	<input type="hidden" name="questionAnswerEditItems[${questionAnswerEditItemIndex}].operation" 
		id="questionAnswerEditItems[${questionAnswerEditItemIndex}].operation" class="questionOperation" value="${questionAnswerEditItem.operation}" />	
	<input type="hidden" name="questionAnswerEditItems[${questionAnswerEditItemIndex}].allowedQuestion" 
		id="questionAnswerEditItems[${questionAnswerEditItemIndex}].allowedQuestion" value="${questionAnswerEditItems[questionAnswerEditItemIndex].allowedQuestion.id}" />	
		
		<span class="fieldGroup">
			<label class="fieldLabel">
				<a id="removeQuestionAnswerLink${questionAnswerEditItemIndex}" class="questionAddItemLink">
					<fmt:message key="removeQuestionLink"/>
				</a>
			</label>
		</span>
		
		<span class="fieldGroup">
			<label for="questionAnswerEditItems[${questionAnswerEditItemIndex}].questionNumber" class="fieldLabel">
				<fmt:message key="questionNumberLabel"/>
			</label>
			<input id="questionAnswerEditItems[${questionAnswerEditItemIndex}].questionNumber" name="questionAnswerEditItems[${questionAnswerEditItemIndex}].questionNumber" value="${questionAnswerEditItems[questionAnswerEditItemIndex].questionNumber}" />
			<form:errors path="questionAnswerEditItems[${questionAnswerEditItemIndex}].questionNumber" cssClass="error"/>
		</span>
		<span class="fieldGroup">
			<!-- Required Checkbox -->
			<label class="fieldLabel" for="questionAnswerEditItems[${questionAnswerEditItemIndex}].required">
				<fmt:message key="requiredLabel"/>
			</label>
			<input type="checkbox" value="true" name="questionAnswerEditItems[${questionAnswerEditItemIndex}].required" 
					id="questionAnswerEditItems[${questionAnswerEditItemIndex}].required" value="true" 
					${questionAnswerEditItems[questionAnswerEditItemIndex].required ? 'checked="checked"' : ''} />
		</span>
		<span class="fieldGroup">
				<!-- Valid Select -->
			<label class="fieldLabel" for="questionAnswerEditItems[${questionAnswerEditItemIndex}].valid">
				<fmt:message key="validLabel"/>
			</label>
			<input type="checkbox" value="true"
					id="questionAnswerEditItems[${questionAnswerEditItemIndex}].valid" name="questionAnswerEditItems[${questionAnswerEditItemIndex}].valid" 
					${questionAnswerEditItems[questionAnswerEditItemIndex].valid or questionAnswerEditItem.valid ? 'checked="checked"' : ''} />
		</span>
		<span class="fieldGroup">
			<label  class="fieldLabel">
				<fmt:message key="helpTextLabel"/>
			</label>
			<input id="questionAnswerEditItems[${questionAnswerEditItemIndex}].questionHelp" name="questionAnswerEditItems[${questionAnswerEditItemIndex}].questionHelp" value="${questionAnswerEditItems[questionAnswerEditItemIndex].questionHelp}"/>
		</span>
		<span class="fieldGroup">
			<!-- Use Existing Question Option -->
			<input type="radio" class="fieldOption" id="questionAnswerEditItems[${questionAnswerEditItemIndex}].existingQuestion"
				name="questionAnswerEditItems[${questionAnswerEditItemIndex}].existingQuestion" value="true" 
				${questionAnswerEditForm.questionAnswerEditItems[questionAnswerEditItemIndex].existingQuestion eq 'true' or not 
				questionAnswerEditForm.questionAnswerEditItems[questionAnswerEditItemIndex].existingQuestion 
					? 'checked="checked"' : ''} />
			<label for="questionAnswerEditItems[${questionAnswerEditItemIndex}].existingQuestion">
				<fmt:message key="useExistingLabel"/>
			</label>
		</span>	
		<span class="fieldGroup"> 
			<!-- Question Search -->
			<label class="fieldLabel">&nbsp;</label><!-- Here to properly format without having to fiddle with extra css -->
			<input type="text" class="large" name="questionAnswerEditItems[${questionAnswerEditItemIndex}].questionQuery"
				 id="questionAnswerEditItems[${questionAnswerEditItemIndex}].questionQuery" 
				 value="<c:if test="${not empty questionAnswerEditItems[questionAnswerEditItemIndex].question}">(<c:out value="${fn:toUpperCase(fn:substring(questionAnswerEditItems[questionAnswerEditItemIndex].questionCategory, 0, 1))}${fn:toLowerCase(fn:substring(fn:replace(questionAnswerEditItems[questionAnswerEditItemIndex].questionCategory, '_', ' '), 1, -1))}"/>) <c:out value="${questionAnswerEditItems[questionAnswerEditItemIndex].question.text}"/></c:if>"/>
			<input type="hidden" name="questionAnswerEditItems[${questionAnswerEditItemIndex}].question" id="questionAnswerEditItems[${questionAnswerEditItemIndex}].question"
				 value="<c:out value='${questionAnswerEditForm.questionAnswerEditItems[questionAnswerEditItemIndex].question.id}'/>"/>
			<form:errors path="questionAnswerEditItems[${questionAnswerEditItemIndex}].question" cssClass="error"/>
			<a id="clearQuestionLink[${questionAnswerEditItemIndex}]" class="clearLink" title="<fmt:message key='clearQuestionLink' />" ></a>
		</span>	
			
		
		
		
		
		
		
		<span class="fieldGroup">
			<!-- Create New Question Option -->
			<input type="radio" class="fieldOption" id="questionAnswerEditItems[${questionAnswerEditItemIndex}].newQuestion"
				name="questionAnswerEditItems[${questionAnswerEditItemIndex}].existingQuestion" value="false" 
				${questionAnswerEditForm.questionAnswerEditItems[questionAnswerEditItemIndex].existingQuestion eq'false'
					? 'checked="checked"' : ''} />
			<label for="questionAnswerEditItems[${questionAnswerEditItemIndex}].newQuestion">
				<fmt:message key="createNewLabel"/>
			</label>
		</span>
		
		<div id="questionCreate[${questionAnswerEditItemIndex}]" class="hidden">
			<span class="fieldGroup">
				<label for="questionAnswerEditItems[${questionAnswerEditItemIndex}].questionText" class="fieldLabel">
					<fmt:message key="questionTextLabel"/>
				</label>
				<textarea id="questionAnswerEditItems[${questionAnswerEditItemIndex}].questionText" name="questionAnswerEditItems[${questionAnswerEditItemIndex}].questionText"><c:out value="${questionAnswerEditItems[questionAnswerEditItemIndex].questionText}"/></textarea>
				<form:errors path="questionAnswerEditItems[${questionAnswerEditItemIndex}].questionText" cssClass="error"/>
			</span>
			
			<span class="fieldGroup">
				<!-- Category Select -->
				<label class="fieldLabel" for="questionAnswerEditItems[${questionAnswerEditItemIndex}].questionCategory">
					<fmt:message key="questionTypeLabel"/>
				</label>
				<select class="questionCategorySelect" id="questionAnswerEditItems[${questionAnswerEditItemIndex}].questionCategory" name="questionAnswerEditItems[${questionAnswerEditItemIndex}].questionCategory" onchange="showHideAnswerCreate(${questionAnswerEditItemIndex})">
					<jsp:include page="../../includes/nullOption.jsp"/>
					<c:forEach var="category" items="${questionTypes}"> 
						<option value="${category}"
							${questionAnswerEditItems[questionAnswerEditItemIndex].questionCategory
								eq category ? 'selected="selected"' : ''}>
							<c:out value="${fn:toUpperCase(fn:substring(category, 0, 1))}${fn:toLowerCase(fn:substring(fn:replace(category, '_', ' '), 1, -1))}"/>
						</option>
					</c:forEach>
				</select>
				<form:errors path="questionAnswerEditItems[${questionAnswerEditItemIndex}].questionCategory" cssClass="error"/>
			</span>
		</div>


		<div id="answerCreate[${questionAnswerEditItemIndex}]" class="answerCreate hidden">
			<div id="answerCardinality[${questionAnswerEditItemIndex}]">
				<span class="fieldGroup">
					<!-- Cardinality Select All -->
					<input type="radio" class="fieldOption" 
						name="questionAnswerEditItems[${questionAnswerEditItemIndex}].multipleCardinality" value="true" 
						${questionAnswerEditItems[questionAnswerEditItemIndex].multipleCardinality eq 'true'
						 or empty questionAnswerEditItems[questionAnswerEditItemIndex].multipleCardinality
							? 'checked="checked"' : ''} />
					<label for="questionAnswerEditItems[${questionAnswerEditItemIndex}].multipleCardinality">
						<fmt:message key="selectAllLabel"/>
					</label>
				</span>
				
				<span class="fieldGroup">
					<!-- Cardinality Select One -->
					<input type="radio" class="fieldOption"
						name="questionAnswerEditItems[${questionAnswerEditItemIndex}].multipleCardinality" value="false" 
						${questionAnswerEditItems[questionAnswerEditItemIndex].multipleCardinality eq 'false'
							? 'checked="checked"' : ''} />
					<label for="questionAnswerEditItems[${questionAnswerEditItemIndex}].multipleCardinality">
						<fmt:message key="selectOneLabel"/>
					</label>
				</span>
			</div>
			<span class="fieldGroup">
				<!-- Add Answer Button -->
				<label class="fieldLabel">
					<a id="answerAddItemLink[${questionAnswerEditItemIndex}]" class="answerAddItemLink">
						<fmt:message key="addAnswerLink"/>
					</a>
				</label>
			</span>
			
			
				<c:set var="allowedAnswerItems"
					value="${questionAnswerEditItems[questionAnswerEditItemIndex].allowedAnswerItems}"
					scope="request"/>
				<div id="allowedAnswerItemsBody[${questionAnswerEditItemIndex}]">
					<c:forEach var="allowedAnswerItem" 
						items="${allowedAnswerItems}" varStatus="aaStatus">
						<c:set var="allowedAnswerItem" value="${allowedAnswerItem}" scope="request"/>
						<c:set var="allowedAnswerItemIndex" value="${aaStatus.index}" scope="request"/>
						<c:if test="${not empty allowedAnswerItem.operation}">
							<jsp:include page="answerEditItemContent.jsp"/>
						</c:if>
					</c:forEach>
				
				</div>
			</div>
		
		
		<span class="fieldGroup">
			<label class="fieldLabel">
				<a id="questionAddItemLink[${questionAnswerEditItemIndex}]" class="questionAddItemLink">
					<fmt:message key="addQuestionLink"/>
				</a>
			</label>
		</span>
		
	</div>
	</fieldset>
</fmt:bundle>