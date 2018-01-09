<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<fmt:setBundle basename="omis.health.msgs.provider" var="providerBundle"/>
<fmt:bundle basename="omis.msgs.homeLinks">
<form:form commandName="providerSearchForm" method="post" id="searchForm" class="editForm">
	<fieldSet>
		<legend><fmt:message key="searchCriteria" bundle="${providerBundle}"/></legend>
		<div class="searching">
		<span class="fieldGroup">
			<label for="searchByProviderName" class="fieldLabel"><fmt:message key="searchByproviderNameLabel" bundle="${providerBundle}"/></label>
			<input id="searchByProviderName" name="providerName" value="${providerSearchForm.provider}" class="fieldValue"/>			
		</span>
		<span class="fieldGroup">
			<label for="searchByServiceCategory" class="fieldLabel"><fmt:message key="searchByServiceCategoryLabel" bundle="${providerBundle}"/></label>
			<select class="fieldValue"><option id="searchByServiceCategory" value="${providerSearchForm.serviceCategory.name}"/></select>			
		</span>
		<span class="fieldGroup">
			<label for="searchByCity" class="fieldLabel"><fmt:message key="searchByCityLabel" bundle="${providerBundle}"/></label>
			<select class="fieldValue"><option id="searchByCity" value="${providerSearchForm.city.name}"/></select>			
		</span>
		<span class="fieldGroup">
			<label for="searchByCounty" class="fieldLabel"><fmt:message key="searchByCountyLabel" bundle="${providerBundle}"/></label>
			<select class="fieldValue"><option id="searchByCounty" value="${providerSearchForm.county.name}"/></select>			
		</span>
		
		<%-- <span class="fieldGroup">
			<label for="populationGroup" class="fieldLabel">
				<fmt:message key="searchByPopulationLabel" bundle="${providerBundle}"/>
			</label> --%>
			<span id="populationGroup" class="fieldGroup">
				<span class="checkbox">
					<span>
						<label for="populationGroup" class="fieldLabel">
							<fmt:message key="searchByPopulationLabel" bundle="${providerBundle}"/>
						</label>
					</span>
					<span>
						<input id="convictedFelons" type="checkBox" name="convictedFelons" />
						<label for="convictedFelons">Convicted Felons</label>
					</span>
					
				
					<span>
						<input id="veterans" type="checkBox" name="Veterans"/>
						<label for="veterans">Veterans</label>
					</span>
					<span>
						<input id="sexOffenders" type="checkBox" name="sexOffenders"/>
						<label for="sexOffenders">Sex Offenders</label>
					</span>
						
					<span>
						<input id="violentOffenders" type="checkBox" name="violentOffenders"/>
						<label for="violentOffenders">Violent Offenders</label>
					</span>
				</span>
			</span>		
		<!-- </span> -->
	<p class="buttons">
		<input type="submit" value="<fmt:message key='searchButton' bundle='${providerBundle}'/>"/>
	</p>
	</div>
	</fieldSet>
</form:form>
</fmt:bundle>