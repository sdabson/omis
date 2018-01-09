<?xml version="1.0" encoding="UTF-8"?>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<sec:authorize var="editQuestionnaire" access="hasRole('QUESTIONNAIRE_CREATE') or hasRole('ADMIN')"/>
<fmt:bundle basename="omis.questionnaire.msgs.questionnaire">
<form:form commandName="questionAnswerEditItem" class="editForm">
	<fieldset>
		<form:input type="hidden" path="operation" />
		<form:input type="hidden" path="sortOrder" />
		<span class="fieldGroup">
			<label for="questionNumber" class="fieldLabel">
				<fmt:message key="questionNumberLabel"/>
			</label>
			<form:input path="questionNumber" />
			<form:errors path="questionNumber" cssClass="error"/>
		</span>
		<span class="fieldGroup">
			<!-- Required Checkbox -->
			<label class="fieldLabel" for="required">
				<fmt:message key="requiredLabel"/>
			</label>
			<form:checkbox value="true" path="required" />
		</span>
		<span class="fieldGroup">
				<!-- Valid Select -->
			<label class="fieldLabel" for="valid">
				<fmt:message key="validLabel"/>
			</label>
			<form:checkbox path="valid" value="true"/>
		</span>
		<span class="fieldGroup">
			<label  class="fieldLabel">
				<fmt:message key="helpTextLabel"/>
			</label>
			<form:input path="questionHelp" />
		</span>
		<span class="fieldGroup">
			<!-- Use Existing Question Option -->
			<form:radiobutton class="fieldOption" path="existingQuestion" value="true" />
			<label for="existingQuestion">
				<fmt:message key="useExistingLabel"/>
			</label>
		</span>	
		<span class="fieldGroup"> 
			<!-- Question Search -->
			<label class="fieldLabel">&nbsp;</label><!-- Here to properly format without having to fiddle with extra css -->
			<input type="text" class="large" name="questionQuery"
				 id="questionQuery" 
				 value="<c:if test="${not empty questionAnswerEditItem.question}">(<c:out 
				 value="${fn:toUpperCase(fn:substring(questionAnswerEditItem.question.questionCategory, 0, 1))}${fn:toLowerCase(fn:substring(fn:replace(questionAnswerEditItem.question.questionCategory, '_', ' '), 1, -1))}"/>) <c:out 
				 value="${questionAnswerEditItem.question.text}"/></c:if>"/>
			<form:input type="hidden" path="question" />
			<form:errors path="question" cssClass="error"/>
			<a id="clearQuestionLink" class="clearLink" title="<fmt:message key='clearQuestionLink' />" ></a>
		</span>
		
		<span class="fieldGroup">
			<!-- Create New Question Option -->
			<form:radiobutton class="fieldOption" path="existingQuestion" value="false" />
			<label for="newQuestion">
				<fmt:message key="createNewLabel"/>
			</label>
		</span>
		
		<div id="questionCreate" class="">
			<span class="fieldGroup">
				<label for="questionText" class="fieldLabel">
					<fmt:message key="questionTextLabel"/>
				</label>
				<form:textarea path="questionText" value="${questionText}"/>
				<form:errors path="questionText" cssClass="error"/>
			</span>
			
			<span class="fieldGroup">
				<!-- Category Select -->
				<label class="fieldLabel" for="questionCategory">
					<fmt:message key="questionTypeLabel"/>
				</label>
				<form:select class="questionCategorySelect" path="questionCategory" onchange="singleShowHideAnswerCreate()">
					<jsp:include page="../../includes/nullOption.jsp"/>
					<c:forEach var="category" items="${questionTypes}"> 
						<option value="${category}"
							${questionAnswerEditItem.questionCategory
								eq category ? 'selected="selected"' : ''}>
							<c:out value="${fn:toUpperCase(fn:substring(category, 0, 1))}${fn:toLowerCase(fn:substring(fn:replace(category, '_', ' '), 1, -1))}"/>
						</option>
					</c:forEach>
				</form:select>
				<form:errors path="questionCategory" cssClass="error"/>
			</span>
		</div>


		<div id="answerCreate" class="answerCreate">
			<div id="answerCardinality">
				<span class="fieldGroup">
					<!-- Cardinality Select All -->
					<form:radiobutton class="fieldOption" path="multipleCardinality" value="true" />
					<label for="multipleCardinality">
						<fmt:message key="selectAllLabel"/>
					</label>
				</span>
				
				<span class="fieldGroup">
					<!-- Cardinality Select One -->
					<form:radiobutton class="fieldOption" path="multipleCardinality" value="false" />
					<label for="multipleCardinality">
						<fmt:message key="selectOneLabel"/>
					</label>
				</span>
			</div>
			<span class="fieldGroup">
				<!-- Add Answer Button -->
				<label class="fieldLabel">
					<a id="answerAddItemLink" class="answerAddItemLink">
						<fmt:message key="addAnswerLink"/>
					</a>
				</label>
			</span>
			
			
			<c:set var="allowedAnswerItems"
				value="${questionAnswerEditItem.allowedAnswerItems}"
				scope="request"/>
			<div id="allowedAnswerItemsBody">
				<c:forEach var="allowedAnswerItem" 
					items="${allowedAnswerItems}" varStatus="aaStatus">
					<c:set var="allowedAnswerItem" value="${allowedAnswerItem}" scope="request"/>
					<c:set var="allowedAnswerItemIndex" value="${aaStatus.index}" scope="request"/>
					<c:if test="${not empty allowedAnswerItem.operation}">
						<jsp:include page="singleAnswerEditItemContent.jsp"/>
					</c:if>
				</c:forEach>
			</div>
		</div>
	</fieldset>
	<c:if test="${not empty allowedQuestion}">
		<c:set var="updatable" value="${allowedQuestion}" scope="request"/>
		<jsp:include page="/WEB-INF/views/audit/includes/updateSignature.jsp"/>
	</c:if>
	<c:if test="${editQuestionnaire}">
		<p class="buttons">
			<input type="submit" value="<fmt:message key='saveLabel' bundle='${commonBundle}'/>"/>
		</p>
	</c:if>
</form:form>
</fmt:bundle>