/**
 * Probation term detail screen behavior.
 * 
 * Author: Josh Divine
 * Author: Stephen Abson
 * Version: 0.1.1 Nov 21, 2017
 * Since: OMIS 3.0
 */
window.onload = function() {	
	applyActionMenu(document.getElementById("actionMenuLink"));
	
	applyDatePicker(document.getElementById("startDate"));
	applyDatePicker(document.getElementById("expirationDate"));
	applyDatePicker(document.getElementById("terminationDate"));
	
	applyFormUpdateChecker(document.getElementById("probationTermForm"));

	// Action to fire when term fields are changed
	function onchangeTermDay(yearsElt, monthsElt, daysElt, totalDaysElt, totalDaysLabelElt) {
		var url = config.ServerConfig.getContextPath() + "/probationTerm/calculateTotalDays.json";
		var params = "years=" + yearsElt.value + "&months=" + monthsElt.value + "&days=" + daysElt.value;
		var request = new XMLHttpRequest();
		request.open("get", url + "?" + params, false);
		request.send(null);
		if (request.status == 200) {
			var totalDays = eval("(" + request.responseText + ")");
			totalDaysElt.value = totalDays;
			totalDaysLabelElt.innerHTML = totalDays;
		} else {
			alert("Error - status: " + request.status + "; url: " + url + "?" + params);
		}
	};
	
	function onChangeTotalDays(totalDaysElt, jailCreditElt, sentenceDaysElt, sentenceDaysDisplayElt) {
		if (totalDaysElt != null) {
			var totalDays = totalDaysElt.value;
			var jailCredit = "0";
			if (jailCredit != null && jailCreditElt.value != "") {
				jailCredit = jailCreditElt.value;
			}
			var sentenceDays = eval("(" + totalDays + " - " + jailCredit + ")");
			sentenceDaysElt.value = sentenceDays;
			sentenceDaysDisplayElt.innerHTML = sentenceDays;
		}
	};
	
	function onChangeExpirationDate(startDateElt, sentenceDaysElt, expirationDateElt) {
		if (startDateElt.value != null && startDateElt.value != "" && new Date(startDateElt.value) !== "Invalid Date" && !isNaN(new Date(startDateElt.value))) {
			var url = config.ServerConfig.getContextPath() + "/probationTerm/calculateProbationExpirationDate.json";
			var params = "startDate=" + startDateElt.value + "&sentenceDays=" + sentenceDaysElt.value;
			var request = new XMLHttpRequest();
			request.open("get", url + "?" + params, false);
			request.send(null);
			if (request.status == 200) {
				expirationDateElt.value = eval("(" + request.responseText + ")");
			} else {
				alert("Error - status: " + request.status + "; URL: " + url + "; params: " + params);
			}
		}
	};
	
	// Applies change behavior to years field
	document.getElementById("years").onchange = function() {
		var monthsElt = document.getElementById("months");
		var daysElt = document.getElementById("days");
		var totalDaysElt = document.getElementById("totalDays");
		var totalDaysLabelElt = document.getElementById("totalDaysLabel");
		onchangeTermDay(this, monthsElt, daysElt, totalDaysElt, totalDaysLabelElt);
		var jailCreditElt = document.getElementById("jailCredit");
		var sentenceDaysElt = document.getElementById("sentenceDays");
		var sentenceDaysDisplayElt = document.getElementById("sentenceDaysDisplay");
		onChangeTotalDays(totalDaysElt, jailCreditElt, sentenceDaysElt, sentenceDaysDisplayElt);
		var startDateElt = document.getElementById("startDate");
		var expirationDateElt = document.getElementById("expirationDate");
		onChangeExpirationDate(startDateElt, sentenceDaysElt, expirationDateElt);
		return false;
	};
	
	// Applies change behavior to months field
	document.getElementById("months").onchange = function() {
		var yearsElt = document.getElementById("years");
		var daysElt = document.getElementById("days");
		var totalDaysElt = document.getElementById("totalDays");
		var totalDaysLabelElt = document.getElementById("totalDaysLabel");
		onchangeTermDay(yearsElt, this, daysElt, totalDaysElt, totalDaysLabelElt);
		var jailCreditElt = document.getElementById("jailCredit");
		var sentenceDaysElt = document.getElementById("sentenceDays");
		var sentenceDaysDisplayElt = document.getElementById("sentenceDaysDisplay");
		onChangeTotalDays(totalDaysElt, jailCreditElt, sentenceDaysElt, sentenceDaysDisplayElt);
		var startDateElt = document.getElementById("startDate");
		var expirationDateElt = document.getElementById("expirationDate");
		onChangeExpirationDate(startDateElt, sentenceDaysElt, expirationDateElt);
		return false;
	};
	
	// Applies change behavior to days field
	document.getElementById("days").onchange = function() {
		var yearsElt = document.getElementById("years");
		var monthsElt = document.getElementById("months");
		var totalDaysElt = document.getElementById("totalDays");
		var totalDaysLabelElt = document.getElementById("totalDaysLabel");
		onchangeTermDay(yearsElt, monthsElt, this, totalDaysElt, totalDaysLabelElt);
		var jailCreditElt = document.getElementById("jailCredit");
		var sentenceDaysElt = document.getElementById("sentenceDays");
		var sentenceDaysDisplayElt = document.getElementById("sentenceDaysDisplay");
		onChangeTotalDays(totalDaysElt, jailCreditElt, sentenceDaysElt, sentenceDaysDisplayElt);
		var startDateElt = document.getElementById("startDate");
		var expirationDateElt = document.getElementById("expirationDate");
		onChangeExpirationDate(startDateElt, sentenceDaysElt, expirationDateElt);
		return false;
	};
	
	document.getElementById("jailCredit").onchange = function() {
		var totalDaysElt = document.getElementById("totalDays");
		var sentenceDaysElt = document.getElementById("sentenceDays");
		var sentenceDaysDisplayElt = document.getElementById("sentenceDaysDisplay");
		onChangeTotalDays(totalDaysElt, this, sentenceDaysElt, sentenceDaysDisplayElt);
		var startDateElt = document.getElementById("startDate");
		var expirationDateElt = document.getElementById("expirationDate");
		onChangeExpirationDate(startDateElt, sentenceDaysElt, expirationDateElt);
		return false;
	};
	
	document.getElementById("startDate").onchange = function() {
		var sentenceDaysElt = document.getElementById("sentenceDays");
		var expirationDateElt = document.getElementById("expirationDate");
		if (sentenceDaysElt.value != null && sentenceDaysElt.value != "") {
			onChangeExpirationDate(this, sentenceDaysElt, expirationDateElt);
		}
		return false;
	};
}