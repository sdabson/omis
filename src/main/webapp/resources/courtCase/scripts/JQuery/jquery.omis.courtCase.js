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
 * Jquery implementation of functions for courtCase.js
 * 
 * @author: Joel Norris
 * @author: Ryan Johns
 * @author: Josh Divine
 * @version: 0.1.3 (Jan 31, 2018)
 * @since: OMIS 3.0
 */
function applyCourtCaseBehavior() {
	for (var index = 0; index < currentChargeIndex; index++) {
		chargeRowOnClick(index);
	}
	for (var index = 0; index < currentCourtCaseNoteIndex; index++) {
		courtCaseNoteRowOnClick(index);
	}
}

/**
 * Assigns on click functionality for the charges action menu.
 */
function chargeActionMenuOnClick() {
	$("#createChargeLink").click(function() {
		$.ajax(config.ServerConfig.getContextPath() + "/courtCase/addCharge.html",
			   {
				   type: "GET",
				   async: false,
				   data: { chargeIndex: currentChargeIndex },
				   success: function(data) {
				   		$("#charges").append(data);
						chargeRowOnClick(currentChargeIndex);
				   },
				   error: function(jqXHR, textStatus, errorThrown) {
						alert("Error - status: " + textStatus + "; error: "
								+ errorThrown);
							$("#charges").html(jqXHR.responseText);
				   }
				   
			   }
			);
   			currentChargeIndex++;
   			return false;
	});
}

/**
 * Assigns on click functionality for the court case note items action menu.
 */
function courtCaseNoteActionMenuOnClick() {
	$("#createCourtCaseNoteLink").click(function() {
		$.ajax(config.ServerConfig.getContextPath() + "/courtCase/addCourtCaseNote.html",
			   {
				   type: "GET",
				   async: false,
				   data: { courtCaseNoteIndex: currentCourtCaseNoteIndex },
				   success: function(data) {
				   		$("#courtCaseNotes").append(data);
				   		courtCaseNoteRowOnClick(currentCourtCaseNoteIndex);
				   },
				   error: function(jqXHR, textStatus, errorThrown) {
						alert("Error - status: " + textStatus + "; error: "
								+ errorThrown);
							$("#courtCaseNotes").html(jqXHR.responseText);
				   }
				   
			   }
			);
   			currentCourtCaseNoteIndex++;
   			return false;
	});
}

/** Updates the judges drop down content. */
function updateJudges() {
	var court = $("#court");
	var judge = $("#judge");
	if (court.val() != null && court.val() != "") {
		$.get(config.ServerConfig.getContextPath()
				+ "/courtCase/findJudgesByCourt.html",
				{ court: court.val(),
				  pronouncementDate: $("pronouncementDate").val(),
				  judge: judge.val() },
			function(data) {
				judge.html(data);
			}
		);
		judge.removeAttr("disabled");
	} else {
		judge.html("");
		judge.attr("disabled", "disabled");
	}
}

/**
 * Assigns on click functionality for the charge item row with the
 * specified charge index.
 * 
 * @param chargeIndex charge index
 */
function chargeRowOnClick(chargeIndex) {
	applyDatePicker("#charge" + chargeIndex + "Date");
	applyDatePicker("#charge" + chargeIndex + "FileDate");
	$("#removeLink" + chargeIndex).click(function() {
		if ($("#chargeOperation" + chargeIndex).val() == "EDIT") {
			$("#chargeOperation" + chargeIndex).val("REMOVE");
			$("#chargeRow" + chargeIndex).addClass("removeRow");
		} else if($("#chargeOperation" + chargeIndex).val() == "REMOVE") {
			$("#chargeOperation" + chargeIndex).val("EDIT");
			$("#chargeRow" + chargeIndex).removeClass("removeRow");
		} else {
			$("#chargeRow" + chargeIndex).remove();
		}
		return false;
	});
	if($("#chargeOperation" + chargeIndex).val() == "REMOVE") {
		$("#chargeRow" + chargeIndex).addClass("removeRow");
	}
	applyValueLabelAutoComplete(document.getElementById("charges[" + chargeIndex + "].offenseQuery"), document.getElementById("charges[" + chargeIndex + "].offense"), config.ServerConfig.getContextPath() + "/offenseTerm/searchOffenses.json");
	applyClear(document.getElementById("charges[" + chargeIndex + "].clearOffenseLink"), document.getElementById("charges[" + chargeIndex + "].offenseQuery"), document.getElementById("charges[" + chargeIndex + "].offense"));
	document.getElementById("charges[" + chargeIndex + "].offenseUrlLink").onclick = function() {
		var offense = document.getElementById("charges[" + chargeIndex + "].offense");
		if (offense != null && offense.value != "") {
			var url = config.ServerConfig.getContextPath() + "/courtCase/findOffense.json";
			var params = "offense=" + offense.value;
			var request = new XMLHttpRequest();
			request.open("get", url + "?" + params, false);
			request.send(null);
			if (request.status == 200) {
				var offenseUrl = eval("(" + request.responseText + ")").url;
				if (offenseUrl != "") {
					window.open(offenseUrl, "_blank");
				} else {
					var offenseResolver = new common.MessageResolver("omis.offense.msgs.offense");
					alert(offenseResolver.getMessage("noUrlFoundForOffenseMessage"));
				}
			} else {
				alert("Error - status: " + request.status + "; url: " + url + "?" + params);
			}
		}
		return false;
	};
}

/**
 * Assigns on click functionality for the court case note item row with the
 * specified court case note index.
 * 
 * @param courtCaseNoteIndex court case note index
 */
function courtCaseNoteRowOnClick(courtCaseNoteIndex) {
	applyDatePicker("#courtCaseNoteDate" + courtCaseNoteIndex);
	$("#removeNoteLink" + courtCaseNoteIndex).click(function() {
		if ($("#courtCaseNoteOperation" + courtCaseNoteIndex).val() == "EDIT") {
			$("#courtCaseNoteOperation" + courtCaseNoteIndex).val("REMOVE");
			$("#courtCaseNoteRow" + courtCaseNoteIndex).addClass("removeRow");
		} else if($("#courtCaseNoteOperation" + courtCaseNoteIndex).val() == "REMOVE") {
			$("#courtCaseNoteOperation" + courtCaseNoteIndex).val("EDIT");
			$("#courtCaseNoteRow" + courtCaseNoteIndex).removeClass("removeRow");
		} else {
			$("#courtCaseNoteRow" + courtCaseNoteIndex).remove();
		}
		return false;
	});
	if($("#courtCaseNoteOperation" + courtCaseNoteIndex).val() == "REMOVE") {
		$("#courtCaseNoteRow" + courtCaseNoteIndex).addClass("removeRow");
	}
}