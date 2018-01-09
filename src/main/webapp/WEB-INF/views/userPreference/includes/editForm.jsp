<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<fmt:bundle basename="omis.userpreference.msgs.userPreference">
	<form:form commandName="userPreferenceForm" class="editForm">
		<fieldset>
			<legend><fmt:message key="backgroundPreferencesLabel"/></legend>
			<form:label path="backgroundHue"><fmt:message key="hueLabel"/></form:label>
			<form:input class="preferenceSlider spectrum" type="range" path="backgroundHue" min="0" max="359"/>
			<form:errors path="backgroundHue"/>
			<form:label path="backgroundSaturation"><fmt:message key="saturationLabel"/></form:label>
			<form:input class="preferenceSlider" type="range" path="backgroundSaturation" min="0" max="100"/>
			<form:errors path="backgroundSaturation"/>
			<form:label path="backgroundSaturation" class="fieldLabel"><fmt:message key="whiteBackgroundLabel"/></form:label>
			<form:checkbox path="whiteBackground" id="whiteBackground" value="true"/>
		</fieldset>
		<fieldset>
			<legend><fmt:message key="foregroundPreferencesLabel"/></legend>
			<form:label path="foregroundHue"><fmt:message key="hueLabel"/></form:label>
			<form:input class="preferenceSlider spectrum" type="range" path="foregroundHue" min="0" max="359"/>
			<form:errors path="foregroundHue"/>
			<form:label path="foregroundSaturation"><fmt:message key="saturationLabel"/></form:label>
			<form:input class="preferenceSlider" type="range" path="foregroundSaturation" min="0" max="100"/>
			<form:errors path="foregroundSaturation"/>
		</fieldset>
		<fieldset>
			<legend><fmt:message key="accentPreferencesLabel"/></legend>
			<form:label path="accentHue"><fmt:message key="hueLabel"/></form:label>
			<form:input class="preferenceSlider spectrum" type="range" path="accentHue" min="0" max="359"/>
			<form:errors path="accentHue"/>
			<form:label path="accentSaturation"><fmt:message key="saturationLabel"/></form:label>
			<form:input class="preferenceSlider" type="range" path="accentSaturation" min="0" max="100"/>
			<form:errors path="accentSaturation"/>
		</fieldset>
		<fieldset>
			<legend><fmt:message key="previewLabel"/></legend>
			<div id="previewContainer">
				<div id="previewBackground">
					<h1><fmt:message key="previewHeaderLabel"/></h1>
					<fieldset id="previewFieldSet">
						<legend id="previewFieldLegend"><fmt:message key="previewFieldSetLegendLabel"/></legend>
						<label><fmt:message key="previewInputLabel"/></label>
						<input id="previewInput" type="text"/>
						<table id="previewTable">
							<tr>
							<th id="previewTableHeader"><fmt:message key="previewTableHeaderLabel"/></th>
							</tr>
						</table>						
					</fieldset>
					<p class="buttons">
						<input type="button" value="<fmt:message key='previewButtonLabel'/>"/>
					</p>
				</div>
			</div>
		</fieldset>
		<p class="buttons">
			<input type="submit" value="<fmt:message key='saveLabel' bundle='${commonBundle}'/>"/>
		</p>
	</form:form>
</fmt:bundle>