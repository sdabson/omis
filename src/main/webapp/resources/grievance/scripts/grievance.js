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
 * Behavior to edit grievance.
 * 
 * @author Stephen Abson
 */
window.onload = function() {
	var grievanceEditToggleLink = document.getElementById("grievanceEditToggleLink");
	if (grievanceEditToggleLink != null) {
		grievanceEditToggleLink.onclick = function() {
			var edit = document.getElementById("edit");
			var grievanceFields = document.getElementById("grievanceFields");
			var grievanceSummary = document.getElementById("grievanceSummary");
			if (edit.value == "true") {
				grievanceEditToggleLink.setAttribute("class", "viewEditLink");
				grievanceFields.setAttribute("class", "hidden");
				grievanceSummary.setAttribute("class", "");
				edit.value = "false";
			} else {
				grievanceEditToggleLink.setAttribute("class", "viewLink");
				grievanceFields.setAttribute("class", "");
				grievanceSummary.setAttribute("class", "hidden");
				edit.value = "true";
			}
			return false;
		};
	}
	var closed = document.getElementById("closed");
	var coordinatorLevelDispositionToggleLink = document.getElementById("coordinatorLevelDispositionEditToggleLink");
	if (coordinatorLevelDispositionToggleLink != null) {
		coordinatorLevelDispositionToggleLink.onclick = function() {
			var coordinatorLevelDispositionEdit = document.getElementById("coordinatorLevelDispositionItem.edit");
			var coordinatorLevelDispositionFields = document.getElementById("coordinatorLevelDispositionFields");
			var coordinatorLevelDispositionSummary = document.getElementById("coordinatorLevelDispositionSummary");
			var coordinatorLevelDisposition = document.getElementById("coordinatorLevelDispositionItem.disposition").value;
			if (coordinatorLevelDispositionEdit.value == "true") {
				if (coordinatorLevelDisposition != null && coordinatorLevelDisposition != "") {
					coordinatorLevelDispositionToggleLink.setAttribute("class", "viewEditLink");
				} else {
					coordinatorLevelDispositionToggleLink.setAttribute("class", "createLink");
				}
				coordinatorLevelDispositionFields.setAttribute("class", "hidden");
				coordinatorLevelDispositionSummary.setAttribute("class", "");
				coordinatorLevelDispositionEdit.value = "false";
			} else {
				if (coordinatorLevelDisposition != null && coordinatorLevelDisposition != "") {
					coordinatorLevelDispositionToggleLink.setAttribute("class", "viewLink");
				} else {
					coordinatorLevelDispositionToggleLink.setAttribute("class", "removeLink");
				}
				coordinatorLevelDispositionFields.setAttribute("class", "");
				coordinatorLevelDispositionSummary.setAttribute("class", "hidden");
				coordinatorLevelDispositionEdit.value = "true";
			}
			return false;
		};
	}
	if (document.getElementById("coordinatorLevelDispositionFields") != null) {
		if (document.getElementById("coordinatorLevelDispositionItem.closedDate") != null) {
			applyDatePicker(document.getElementById("coordinatorLevelDispositionItem.closedDate"));
		}
		applyDatePicker(document.getElementById("coordinatorLevelDispositionItem.receivedDate"));
		applyDatePicker(document.getElementById("coordinatorLevelDispositionItem.responseDueDate"));
		applyDatePicker(document.getElementById("coordinatorLevelDispositionItem.responseToOffenderDate"));
		document.getElementById("coordinatorLevelDispositionItem.clearResponseByLink").onclick = function() {
			document.getElementById("coordinatorLevelDispositionItem.responseBy").value = "";
			document.getElementById("coordinatorLevelDispositionItem.responseByQuery").value = "";
			return false;
		};
		document.getElementById("coordinatorLevelDispositionItem.useCurrentUserAccountLink").onclick = function() {
			document.getElementById("coordinatorLevelDispositionItem.responseBy").value = config.SessionConfig.getUserAccountId();
			document.getElementById("coordinatorLevelDispositionItem.responseByQuery").value = config.SessionConfig.getUserAccountLabel();
			return false;
		};
		applyValueLabelAutoComplete(document.getElementById("coordinatorLevelDispositionItem.responseByQuery"), document.getElementById("coordinatorLevelDispositionItem.responseBy"), config.ServerConfig.getContextPath() + "/grievance/searchUserAccounts.json");
		applyDatePicker(document.getElementById("coordinatorLevelDispositionItem.appealDate"));
		if (document.getElementById("coordinatorLevelDispositionItem.closedDate") != null) {
			onchangeDispositionStatus(document.getElementById("coordinatorLevelDispositionItem.status"), document.getElementById("coordinatorLevelDispositionItem.closedDate"), document.getElementById("coordinatorLevelDispositionItem.reason"));
		}
	}
	var wardenFhaLevelDispositionToggleLink = document.getElementById("wardenFhaLevelDispositionEditToggleLink");
	if (wardenFhaLevelDispositionToggleLink != null) {
		wardenFhaLevelDispositionToggleLink.onclick = function() {
			var wardenFhaLevelDispositionEdit = document.getElementById("wardenFhaLevelDispositionItem.edit");
			var wardenFhaLevelDispositionFields = document.getElementById("wardenFhaLevelDispositionFields");
			var wardenFhaLevelDispositionSummary = document.getElementById("wardenFhaLevelDispositionSummary");
			var wardenFhaLevelDisposition = document.getElementById("wardenFhaLevelDispositionItem.disposition").value;
			if (wardenFhaLevelDispositionEdit.value == "true") {
				if (wardenFhaLevelDisposition != null && wardenFhaLevelDisposition != "") {
					wardenFhaLevelDispositionToggleLink.setAttribute("class", "viewEditLink");
				} else {
					wardenFhaLevelDispositionToggleLink.setAttribute("class", "createLink");
				}
				wardenFhaLevelDispositionFields.setAttribute("class", "hidden");
				wardenFhaLevelDispositionSummary.setAttribute("class", "");
				wardenFhaLevelDispositionEdit.value = "false";
			} else {
				if (wardenFhaLevelDisposition != null && wardenFhaLevelDisposition != "") {
					wardenFhaLevelDispositionToggleLink.setAttribute("class", "viewLink");	
				} else {
					wardenFhaLevelDispositionToggleLink.setAttribute("class", "removeLink");
				}
				wardenFhaLevelDispositionFields.setAttribute("class", "");
				wardenFhaLevelDispositionSummary.setAttribute("class", "hidden");
				wardenFhaLevelDispositionEdit.value = "true";
			}
			return false;
		};
	}
	if (document.getElementById("wardenFhaLevelDispositionFields") != null) {
		if (document.getElementById("wardenFhaLevelDispositionItem.closedDate") != null) {
			applyDatePicker(document.getElementById("wardenFhaLevelDispositionItem.closedDate"));
		}
		applyDatePicker(document.getElementById("wardenFhaLevelDispositionItem.receivedDate"));
		applyDatePicker(document.getElementById("wardenFhaLevelDispositionItem.responseDueDate"));
		applyDatePicker(document.getElementById("wardenFhaLevelDispositionItem.responseToOffenderDate"));
		document.getElementById("wardenFhaLevelDispositionItem.clearResponseByLink").onclick = function() {
			document.getElementById("wardenFhaLevelDispositionItem.responseBy").value = "";
			document.getElementById("wardenFhaLevelDispositionItem.responseByQuery").value = "";
			return false;
		};
		document.getElementById("wardenFhaLevelDispositionItem.useCurrentUserAccountLink").onclick = function() {
			document.getElementById("wardenFhaLevelDispositionItem.responseBy").value = config.SessionConfig.getUserAccountId();
			document.getElementById("wardenFhaLevelDispositionItem.responseByQuery").value = config.SessionConfig.getUserAccountLabel();
			return false;
		};
		applyValueLabelAutoComplete(document.getElementById("wardenFhaLevelDispositionItem.responseByQuery"), document.getElementById("wardenFhaLevelDispositionItem.responseBy"), config.ServerConfig.getContextPath() + "/grievance/searchUserAccounts.json");
		applyDatePicker(document.getElementById("wardenFhaLevelDispositionItem.appealDate"));
		if (document.getElementById("wardenFhaLevelDispositionItem.closedDate") != null) {
			onchangeDispositionStatus(document.getElementById("wardenFhaLevelDispositionItem.status"), document.getElementById("wardenFhaLevelDispositionItem.closedDate"), document.getElementById("wardenFhaLevelDispositionItem.reason"));
		}
	}
	var directorLevelDispositionToggleLink = document.getElementById("directorLevelDispositionEditToggleLink");
	if (directorLevelDispositionToggleLink != null) {
		directorLevelDispositionToggleLink.onclick = function() {
			var directorLevelDispositionEdit = document.getElementById("directorLevelDispositionItem.edit");
			var directorLevelDispositionFields = document.getElementById("directorLevelDispositionFields");
			var directorLevelDispositionSummary = document.getElementById("directorLevelDispositionSummary");
			var directorLevelDisposition = document.getElementById("directorLevelDispositionItem.disposition").value;
			if (directorLevelDispositionEdit.value == "true") {
				if (directorLevelDisposition != null && directorLevelDisposition != "") {
					directorLevelDispositionToggleLink.setAttribute("class", "viewEditLink");
				} else {
					directorLevelDispositionToggleLink.setAttribute("class", "createLink");
				}
				directorLevelDispositionFields.setAttribute("class", "hidden");
				directorLevelDispositionSummary.setAttribute("class", "");
				directorLevelDispositionEdit.value = "false";
			} else {
				if (directorLevelDisposition != null && directorLevelDisposition != "") {
					directorLevelDispositionToggleLink.setAttribute("class", "viewLink");
				} else {
					directorLevelDispositionToggleLink.setAttribute("class", " removeLink");
				}
				directorLevelDispositionFields.setAttribute("class", "");
				directorLevelDispositionSummary.setAttribute("class", "hidden");
				directorLevelDispositionEdit.value = "true";
			}
			return false;
		};
	}
	if (document.getElementById("directorLevelDispositionFields") != null) {
		if (document.getElementById("directorLevelDispositionItem.closedDate") != null) {
			applyDatePicker(document.getElementById("directorLevelDispositionItem.closedDate"));
		}
		applyDatePicker(document.getElementById("directorLevelDispositionItem.receivedDate"));
		applyDatePicker(document.getElementById("directorLevelDispositionItem.responseDueDate"));
		applyDatePicker(document.getElementById("directorLevelDispositionItem.responseToOffenderDate"));
		document.getElementById("directorLevelDispositionItem.clearResponseByLink").onclick = function() {
			document.getElementById("directorLevelDispositionItem.responseBy").value = "";
			document.getElementById("directorLevelDispositionItem.responseByQuery").value = "";
			return false;
		};
		document.getElementById("directorLevelDispositionItem.useCurrentUserAccountLink").onclick = function() {
			document.getElementById("directorLevelDispositionItem.responseBy").value = config.SessionConfig.getUserAccountId();
			document.getElementById("directorLevelDispositionItem.responseByQuery").value = config.SessionConfig.getUserAccountLabel();
			return false;
		};
		applyValueLabelAutoComplete(document.getElementById("directorLevelDispositionItem.responseByQuery"), document.getElementById("directorLevelDispositionItem.responseBy"), config.ServerConfig.getContextPath() + "/grievance/searchUserAccounts.json");
		onchangeDispositionStatus(document.getElementById("directorLevelDispositionItem.status"), document.getElementById("directorLevelDispositionItem.closedDate"), document.getElementById("directorLevelDispositionItem.reason"));
	}
	var openedDate = document.getElementById("openedDate");
	openedDate.onchange = function() {
		var url = config.ServerConfig.getContextPath() + "/grievance/calculateResponseDueDate.json?openedDate=" + openedDate.value;
		var request = new XMLHttpRequest();
		request.open("GET", url, false);
		request.send(null);
		if (request.status == 200) {
		var responseDueDate = eval("(" + request.responseText + ")");
		var coordinatorLevelResponseDueDate = document.getElementById("coordinatorLevelDispositionItem.responseDueDate");
			if (coordinatorLevelResponseDueDate != null) {
				if (coordinatorLevelResponseDueDate.value == null || coordinatorLevelResponseDueDate.value == '') {
					coordinatorLevelResponseDueDate.value = responseDueDate;
				}
			} else {
				var wardenFhaLevelResponseDueDate = document.getElementById("wardenFhaLevelDispositionItem.responseDueDate");
				if (wardenFhaLevelResponseDueDate != null) {
					if (wardenFhaLevelResponseDueDate.value == null || wardenFhaLevelResponseDueDate.value == '') {
						wardenFhaLevelResponseDueDate.value = responseDueDate;
					}
				} else {
					var directorLevelResponseDueDate = document.getElementById("directorLevelDispositionItem.responseDueDate");
					if (directorLevelResponseDueDate != null) {
						if (directorLevelResponseDueDate.value == null || directorLevelResponseDueDate.value == '') {
							directorLevelResponseDueDate.value = responseDueDate;
						}
					} else {
						alert("Error: no dispositions exists");
					}
				}
			}
		} else {
			alert("Error calculating response due date: code - " + request.status + "; text - " + request.statusText);
		}
	};
	applyDatePicker(openedDate);
	applyDatePicker(document.getElementById("informalFileDate"));
	applyActionMenu(document.getElementById("actionMenuLink"));
};

/**
 * Displays statement explaining that grievance will be reopened.
 */
function displayReopenStatement() {
	var resolver = new common.MessageResolver("omis.grievance.msgs.grievance");
	alert(resolver.getMessage("changingToOpenedStatusReopensGrievanceStatement"));
}

/**
 * Disable closed date if status is not closed.
 * 
 * @param statusElt status element
 * @param closedDateElt closed date element
 * @param reasonElt reason element
 */
function onchangeDispositionStatus(statusElt, closedDateElt, reasonElt) {
	statusElt.onchange = function() {
		var currentlyClosed;
		if (document.getElementById("closed") != null) {
			currentlyClosed = document.getElementById("closed").value == "true";
		} else {
			currentlyClosed = false;
		}
		var selectedStatusElt = statusElt.options[statusElt.selectedIndex];
		if (selectedStatusElt != null) {
			var request = new XMLHttpRequest();
			request.open("GET", config.ServerConfig.getContextPath() + "/grievance/isDispositionStatusClosed.html?status=" + selectedStatusElt.value, false);
			request.send(null);
			var closed = eval(request.responseText);
			closedDateElt.disabled = !closed;
		} else {
			closedDateElt.disabled = true;
		}
		if (currentlyClosed && !closed) {
			displayReopenStatement();
		}
		var url = config.ServerConfig.getContextPath() + "/grievance/findDispositionReasonsByStatus.html";
		if (selectedStatusElt != null) {
			url = url + "?status=" + selectedStatusElt.value;
		}
		var selectedReasonElt = reasonElt.options[reasonElt.selectedIndex];
		if (selectedReasonElt != null) {
			if (url.slice(-1) == "?") {
				url = url + "&";
			}
			url = url + "&defaultDispositionReason=" + reasonElt.value;
		}
		var request = new XMLHttpRequest();
		request.open("GET", url, false);
		request.send(null);
		reasonElt.innerHTML = request.responseText;
	};
}