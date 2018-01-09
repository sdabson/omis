/**
 * Behavior for screen to change correctional statuses.
 * 
 * Author: Stephen Abson
 */
window.onload = function() {
	applyDatePicker(document.getElementById("effectiveDate"));
	applyTimePicker(document.getElementById("effectiveTime"));
	applyDatePicker(document.getElementById("endDate"));
	applyTimePicker(document.getElementById("endTime"));
	var supervisoryOrganization = document.getElementById("supervisoryOrganization");
	if (supervisoryOrganization != null) {
		var location = document.getElementById("location");
		supervisoryOrganization.onchange = function() {
			var supervisoryOrganizationOption = supervisoryOrganization.options[supervisoryOrganization.selectedIndex];
			var supervisoryOrganizationValue;
			if (supervisoryOrganizationOption != null) {
				supervisoryOrganizationValue = supervisoryOrganization.options[supervisoryOrganization.selectedIndex].getAttribute("value");	
			} else {
				supervisoryOrganizationValue = null;
			}
			var effectiveDateValue = getInputValue("effectiveDate");
			var effectiveTimeValue = getInputValue("effectiveTime");
			var endDateValue = getInputValue("endDate");
			var endTimeValue = getInputValue("endTime");
			var locationValue;
			if (location != null) {
				populateLocations(supervisoryOrganizationValue, correctionalStatus);
				location = document.getElementById("location");
				applyLocationOnchange();
				var locationOption = location.options[location.selectedIndex];
				if (locationOption != null) {
					locationValue = locationOption.getAttribute("value");
				} else {
					locationValue = null;
				}
			} else {
				locationValue = null;
			}
			populatePrograms(supervisoryOrganizationValue, locationValue, effectiveDateValue, effectiveTimeValue, endDateValue, endTimeValue);
		};
		if (location != null) {
			applyLocationOnchange();
		}
	}
	applyActionMenu(document.getElementById("actionMenuLink"));
};

/**
 * Retrieves locations, populates location drop down.
 * 
 * @param supervisoryOrganizationValue supervisory organization
 */
function populateLocations(supervisoryOrganizationValue, correctionalStatus) {
	
	var url = config.ServerConfig.getContextPath();
	url = url + "/placement/locationSelect.html"
	url = url + "?supervisoryOrganization=" + supervisoryOrganizationValue;
	url = addParam(url, "correctionalStatus", correctionalStatus, false);
	url = url + "&nocache=" + new Date().getTime();
	
	// Request location drop down and replace
	var request = new XMLHttpRequest();
	request.open("get", url, false);
	request.send(null);
	if (request.status == 200) {
		var location = document.getElementById("location");
		location.parentNode.innerHTML = request.responseText;
	} else {
		alert("Error - " + request.status + " - " + request.statusText);
	}
}

/**
 * Retrieves programs, populates programs drop down.
 * 
 * @param supervisoryOrganizationValue supervisory organization
 * @param locationValue location
 * @param effectiveDateValue effective date
 * @param effectiveTimeValue effective time
 * @param endDateValue end date
 * @param endTimeValue end time
 */
function populatePrograms(supervisoryOrganizationValue, locationValue, effectiveDateValue, effectiveTimeValue, endDateValue, endTimeValue) {
	
	// Build URL. Note, the following should also be added:
	//
	//   url = addParam(url, "effectiveTime", effectiveTimeValue, true);
	//   url = addParam(url, "endTime", endTimeValue, true);
	//
	// However, the values must be escaped - SA
	var url = config.ServerConfig.getContextPath();
	url = url + "/placement/programSelect.html";
	url = url + "?supervisoryOrganization=" + supervisoryOrganizationValue;
	url = addParam(url, "location", locationValue, true);
	url = addParam(url, "effectiveDate", effectiveDateValue, true);
	url = addParam(url, "endDate", endDateValue, true);
	url = url + "&nocache=" + new Date().getTime();
	
	// Request program drop down and replace
	var request = new XMLHttpRequest();
	request.open("get", url, false);
	request.send(null);
	if (request.status == 200) {
		var location = document.getElementById("program");
		location.parentNode.innerHTML = request.responseText;
	} else {
		alert("Error - " + request.status + " - " + request.statusText);
	}
}

/**
 * Returns input value of element with name.
 * 
 * @param eltName element name
 * @return input value of element with name
 */
function getInputValue(eltName) {
	return document.getElementById(eltName).value;
}

/**
 * Adds parameter to a URL.
 * 
 * @param url url
 * @param paramName parameter name
 * @param paramValue parameter value
 * @param optional whether parameter is optional
 * @returns url with parameter added
 */
function addParam(url, paramName, paramValue, optional) {
	if (!optional || (paramValue != null && paramValue != "")) {
		return url + "&" + paramName + "=" + paramValue;
	} else {
		return url;
	}
}

/** Applies location onchange event. */
function applyLocationOnchange() {
	var location = document.getElementById("location");
	location.onchange = function() {
		var programAllowed = document.getElementById("programAllowed").value;
		if (programAllowed) {
			var supervisoryOrganization = document.getElementById("supervisoryOrganization");
			var supervisoryOrganizationOption = supervisoryOrganization.options[supervisoryOrganization.selectedIndex];
			var supervisoryOrganizationValue;
			if (supervisoryOrganizationOption != null) {
				supervisoryOrganizationValue = supervisoryOrganization.options[supervisoryOrganization.selectedIndex].getAttribute("value");	
			} else {
				supervisoryOrganizationValue = null;
			}
			var locationOption = location.options[location.selectedIndex];
			var locationValue;
			if (locationOption != null) {
				locationValue = locationOption.getAttribute("value");
			} else {
				locationValue = null;
			}
			var effectiveDateValue = getInputValue("effectiveDate");
			var effectiveTimeValue = getInputValue("effectiveTime");
			var endDateValue = getInputValue("endDate");
			var endTimeValue = getInputValue("endTime");
			populatePrograms(supervisoryOrganizationValue, locationValue, effectiveDateValue, effectiveTimeValue, endDateValue, endTimeValue);
		}
	};
}