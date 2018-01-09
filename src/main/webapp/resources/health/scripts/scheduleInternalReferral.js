/*
 * Author: Ryan Johns
 * Author: Stephen Abson
 * Version: 0.1.0 (Apr 16, 2014)
 * Since : OMIS 3.0 */
eventRunner.addEvent(function() {
	
	// Apply if an offender is required to be captured by form
	var offenderRequired = document.getElementById("offenderRequired");
	if (offenderRequired != null && offenderRequired.getAttribute("value") == "true") {
		var offenderNameElt = document.getElementById("offenderName");
		applyOffenderAutocompleteWithUnit(
				document.getElementById("offender"),
				offenderNameElt,
				document.getElementById("offenderUnitSpan"),
				document.getElementById("offenderUnitLabel"),
				document.getElementById("offenderUnitGroup"));
		offenderNameElt.focus();
	}
	
	// Call when changing schedule date or provider
	function onchangeProviderScheduleCriteria(providerAssignment, scheduleDate) {
		
		// Selects date
		function onclickScheduleDateSelector(input) {
			if (input.checked) {
				var scheduleDate = document.getElementById("scheduleDateRange");
				scheduleDate.value = input.value;
			}
		}
		
		if (providerAssignment != null && providerAssignment != "" && scheduleDate != null && scheduleDate != "") {
			var internalWeeklyProviderScheduleContainer = document.getElementById("internalWeeklyProviderScheduleContainer");
			var url = config.ServerConfig.getContextPath() + "/health/provider/schedule/internal/showWeekly.html?providerAssignment=" + providerAssignment + "&scheduleDate=" + scheduleDate;
			ajax(url, internalWeeklyProviderScheduleContainer, function(html) {
				internalWeeklyProviderScheduleContainer.innerHTML = html;
				var internalWeeklyProviderSchedule = document.getElementById("internalWeeklyProviderSchedule");
				var inputs = internalWeeklyProviderSchedule.getElementsByTagName("input");
				for (var inputIndex = 0; inputIndex < inputs.length; inputIndex++) {
					var input = inputs[inputIndex];
					if (input.getAttribute("class").indexOf("scheduleDate") != -1) {
						input.onclick = function() {
							onclickScheduleDateSelector(this);
						};
					}
				}
				
				applyOnChangeEvents();
			});
		}
	}
	
	applyOnChangeEvents();
	addLabWorkItem();
	onchangeProviderScheduleCriteria(document.getElementById("providerAssignment").value, document.getElementById("scheduleDateRange").value);
	
	function applyOnChangeEvents() {
		var providerAssignment = document.getElementById("providerAssignment");
		var scheduleDate = document.getElementById("scheduleDateRange");
		
		if (scheduleDate != null) {
			applyDatePicker(scheduleDate, { showOn: "button",
				buttonImage: config.ServerConfig.getContextPath()+"/resources/common/images/calendar.png",
				buttonImageOnly: true});
		}
		
		if (providerAssignment != null && scheduleDate != null) {
			
			providerAssignment.onchange = null;
			providerAssignment.onchange = function() {
				onchangeProviderScheduleCriteria(providerAssignment.value, scheduleDate.value);
			};
			
			scheduleDate.onchange = null;
			scheduleDate.onchange = function() {
				onchangeProviderScheduleCriteria(providerAssignment.value, scheduleDate.value);
			};
		}
		
	}
});