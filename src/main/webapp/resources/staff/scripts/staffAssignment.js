/*
 * OMIS - Offender Management Information System
 * Copyright (C) 2011 - 2017 State of Montana
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

/**
 * Applies behavior to screen to create/edit staff assignments.
 * 
 * @author Stephen Abson
 * @author Yidong Li
 */
window.onload = function() {
	
	// Updates locations and divisions
	function onchangeSupervisoryOrganization(supervisoryOrganization) {
		
		// Updates locations
		function updateLocations(supervisoryOrganization) {
			var request = new XMLHttpRequest();
			var url = config.ServerConfig.getContextPath() + "/staffAssignment/findLocationsBySupervisoryOrganization.html";
			var params = "supervisoryOrganization=" + supervisoryOrganization;
			request.open("get", url + "?" + params, false);
			request.send(null);
			if (request.status == 200) {
				var location = document.getElementById("location");
				location.innerHTML = request.responseText;
			} else {
				alert("Error - code: " + request.status + "; text: " + request.statusText);
			}
		}
		
		// Updates divisions
		function updateOrganizationDivisions(supervisoryOrganization) {
			var request = new XMLHttpRequest();
			var url = config.ServerConfig.getContextPath() + "/staffAssignment/findDivisionsBySupervisoryOrganization.html";
			var params = "supervisoryOrganization=" + supervisoryOrganization;
			request.open("get", url + "?" + params, false);
			request.send(null);
			if (request.status == 200) {
				var organizationDivision = document.getElementById("organizationDivision");
				organizationDivision.innerHTML = request.responseText;
			} else {
				alert("Error - code: " + request.status + "; text: " + request.statusText);
			}
		}
		
		// Invokes update methods
		updateLocations(supervisoryOrganization);
		updateOrganizationDivisions(supervisoryOrganization);
	}
	
	// Applies behavior
	var birthDate = document.getElementById("birthDate");
	if (birthDate != null) {
		applyDatePicker(document.getElementById("birthDate"));
	}
	
	//If new photo flag is true, assign image editing
	if(allowEnhancedImageEditor) {
		if(document.getElementById("newPhoto").value) {
			assignSingleImageEdit(document.getElementById("photoPreview"), refreshPhoto, 640, 480);
		}
	}
	
	//Show preview area on edit, and 
	applyOnImageFileChange(
		document.getElementById("photoPreviewFieldGroup"), document.getElementById("photoPreview"),
		document.getElementById("photoFile"), document.getElementById("photoData"),
		refreshPhoto, function() { 
			if(allowEnhancedImageEditor) {
				assignSingleImageEdit(document.getElementById("photoPreview"), refreshPhoto, 640, 480);
			}
			document.getElementById("newPhoto").value = true;
		},
		640, 480);
	
	//apply date pickers
	applyDatePicker(document.getElementById("photoDate"));
	applyDatePicker(document.getElementById("startDate"));
	applyDatePicker(document.getElementById("endDate"));
	
	
	var supervisoryOrganization = document.getElementById("supervisoryOrganization");
	supervisoryOrganization.onchange = function() {
		var supervisoryOrganization;
		if (this.selectedIndex > -1) {
			supervisoryOrganization = this.options[this.selectedIndex].value;
		} else {
			supervisoryOrganization = null;
		}
		onchangeSupervisoryOrganization(supervisoryOrganization);
	};
	applyActionMenu(document.getElementById("staffAssignmentCreateEditScreenActionMenuLink"));
};

function refreshPhoto(data) {
	document.getElementById("photoPreview").setAttribute("src", data);
	document.getElementById("photoData").value = data.replace("data:image/jpeg;base64,", "");
}