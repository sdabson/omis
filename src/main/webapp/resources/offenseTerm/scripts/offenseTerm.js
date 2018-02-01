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
 * Behavior for offense term edit screen.
 * 
 * <p>Offense term edit screen manages court cases, convictions and sentences.
 * 
 * @author: Stephen Abson
 * @author: Josh Divine
 */
window.onload = function() {
	
	// Shows edit offense header
	function showEditOffenseHeader(offenseItemIndex) {
		var editOffenseHeader = document.getElementById("offenseItems[" + offenseItemIndex + "].editOffenseHeader");
		if (ui.hasClass(editOffenseHeader, "hidden")) {
			ui.removeClass(editOffenseHeader, "hidden");
		}
	}
	
	// Shows offense summary
	function showOffenseSummary(offenseItemIndex) {
		var offenseSummary = document.getElementById("offenseItems[" + offenseItemIndex + "].offenseSummary");
		if (ui.hasClass(offenseSummary, "hidden")) {
			ui.removeClass(offenseSummary, "hidden");
		}
	}
	
	// Hides edit offense header
	function hideEditOffenseHeader(offenseItemIndex) {
		var editOffenseHeader = document.getElementById("offenseItems[" + offenseItemIndex + "].editOffenseHeader");
		if (!ui.hasClass(editOffenseHeader, "hidden")) {
			ui.addClass(editOffenseHeader, "hidden");
		}
	}
	
	// Hides offense summary
	function hideOffenseSummary(offenseItemIndex) {
		var offenseSummary = document.getElementById("offenseItems[" + offenseItemIndex + "].offenseSummary");
		if (!ui.hasClass(offenseSummary, "hidden")) {
			ui.addClass(offenseSummary, "hidden");
		}
	}
	
	// Shows sentence term (prison, probation, deferred)
	function showSentenceTermFields(termName, offenseItemIndex) {
		var offenseTerm = document.getElementById("offenseItems[" + offenseItemIndex + "].sentenceFields." + termName + "Term");
		if (ui.hasClass(offenseTerm, "hidden")) {
			ui.removeClass(offenseTerm, "hidden");
		}
	}
	
	// Hides sentence term (prison, probation, deferred)
	function hideSentenceTermFields(termName, offenseItemIndex) {
		var offenseTerm = document.getElementById("offenseItems[" + offenseItemIndex + "].sentenceFields." + termName + "Term");
		if (!ui.hasClass(offenseTerm, "hidden")) {
			ui.addClass(offenseTerm, "hidden");
		}
	}
	
	// Shows historical offense terms
	function showHistoricalOffenseTerms(offenseItemIndex) {
		var historicalOffenseTerms = document.getElementById("offenseItems[" + offenseItemIndex + "].hisoricalOffenseTerms");
		if (ui.hasClass(historicalOffenseTerms, "hidden")) {
			ui.removeClass(historicalOffenseTerms, "hidden");
		}
	}
	
	// Hides historical offense terms
	function hideHistoricalOffenseTerms(offenseItemIndex) {
		var historicalOffenseTerms = document.getElementById("offenseItems[" +offenseItemIndex + "].hisoricalOffenseTerms");
		if (!ui.hasClass(historicalOffenseTerms, "hidden")) {
			ui.addClass(historicalOffenseTerms, "hidden");
		}
	}
	
	// Updates message for sentence operation
	function updateSentenceOperationMessage(offenseItemIndex, operationName) {
		var operationMessage = document.getElementById("offenseItems[" + offenseItemIndex + "].sentenceOperationMessage");
		var messageResolver = new common.MessageResolver("omis.offenseterm.msgs.offenseTerm");
		var message;
		if (operationName != null && operationName != "") {
			message = messageResolver.getMessage("sentenceOperationMessage." + operationName);
		} else {
			message = messageResolver.getMessage("emptySentenceOperationMessage");
		}
		operationMessage.innerHTML = message;
	}
	
	// Returns "no offense selected" message
	function getNoOffenseSelectedMessage() {
		var messageResolver = new common.MessageResolver("omis.offenseterm.msgs.offenseTerm");
		return messageResolver.getMessage("noOffenseSelectedMessage");
	}
	
	// Expands offense item with index
	function expandOffenseItem(offenseItemIndex) {
		var expandCollapseLink = document.getElementById("offenseItems[" + offenseItemIndex + "].expandCollapseLink");
		ui.addOrReplaceClass(expandCollapseLink, "expandLink", "collapseLink");
		var expanded = document.getElementById("offenseItems[" + offenseItemIndex + "].expanded");
		expanded.value = true;
		var fields = document.getElementById("offenseItems[" + offenseItemIndex + "].fields");
		if (ui.hasClass(fields, "hidden")) {
			ui.removeClass(fields, "hidden");
		}
	}
	
	// Collapses offense item with index
	function collapseOffenseItem(offenseItemIndex) {
		var expandCollapseLink = document.getElementById("offenseItems[" + offenseItemIndex + "].expandCollapseLink");
		ui.addOrReplaceClass(expandCollapseLink, "collapseLink", "expandLink");
		var expanded = document.getElementById("offenseItems[" + offenseItemIndex + "].expanded");
		expanded.value = false;
		var fields = document.getElementById("offenseItems[" + offenseItemIndex + "].fields");
		if (!ui.hasClass(fields, "hidden")) {
			ui.addClass(fields, "hidden");
		}
		var summaryOffenseValue = document.getElementById("offenseItems[" + offenseItemIndex + "].summaryOffenseValue");
		var offenseQuery = document.getElementById("offenseItems[" + offenseItemIndex + "].convictionFields.offenseQuery");
		if (offenseQuery != null && offenseQuery.value != "") {
			summaryOffenseValue.innerHTML = offenseQuery.value;
		} else {
			summaryOffenseValue.innerHTML = "";
		}
		var url = config.ServerConfig.getContextPath() + "/offenseTerm/showSentenceTerms.html";
		var category = document.getElementById("offenseItems[" + offenseItemIndex + "].sentenceFields.category");
		var params = "category=";
		if (category.selectedIndex > -1) {
			params = params + category.options[category.selectedIndex].value;
		} else {
			params = params + null;
		}
		params = params + "&prisonYears=" + document.getElementById("offenseItems[" + offenseItemIndex + "].sentenceFields.prisonYears").value;
		params = params + "&prisonMonths=" + document.getElementById("offenseItems[" + offenseItemIndex + "].sentenceFields.prisonMonths").value;
		params = params + "&prisonDays=" + document.getElementById("offenseItems[" + offenseItemIndex + "].sentenceFields.prisonDays").value;
		params = params + "&probationYears=" + document.getElementById("offenseItems[" + offenseItemIndex + "].sentenceFields.probationYears").value;
		params = params + "&probationMonths=" + document.getElementById("offenseItems[" + offenseItemIndex + "].sentenceFields.probationMonths").value;
		params = params + "&probationDays=" + document.getElementById("offenseItems[" + offenseItemIndex + "].sentenceFields.probationDays").value;
		params = params + "&deferredYears=" + document.getElementById("offenseItems[" + offenseItemIndex + "].sentenceFields.deferredYears").value;
		params = params + "&deferredMonths=" + document.getElementById("offenseItems[" + offenseItemIndex + "].sentenceFields.deferredMonths").value;
		params = params + "&deferredDays=" + document.getElementById("offenseItems[" + offenseItemIndex + "].sentenceFields.deferredDays").value;
		var request = new XMLHttpRequest();
		request.open("get", url + "?" + params, false); 
		request.send(null);
		var summaryTermValue = document.getElementById("offenseItems[" + offenseItemIndex + "].summaryTermValue");
		if (request.status == 200) {
			summaryTermValue.innerHTML = request.responseText;
		} else {
			summaryTermValue.innerHTML = "Error - status code: " + request.status + "; url: " + url + "; params: " + params;
		}
		var summaryLegalDispositionCategoryValue = document.getElementById("offenseItems[" + offenseItemIndex + "].summaryLegalDispositionCategoryValue");
		var legalDispositionCategory = document.getElementById("offenseItems[" + offenseItemIndex + "].sentenceFields.legalDispositionCategory");
		if (legalDispositionCategory.selectedIndex > -1) {
			summaryLegalDispositionCategoryValue.innerHTML = legalDispositionCategory.options[legalDispositionCategory.selectedIndex].label;
		} else {
			summaryLegalDispositionCategoryValue.innerHTML = "";
		}
		var summarySentenceCategoryValue = document.getElementById("offenseItems[" + offenseItemIndex + "].summarySentenceCategoryValue");
		var category = document.getElementById("offenseItems[" + offenseItemIndex + "].sentenceFields.category");
		if (category.selectedIndex > -1) {
			summarySentenceCategoryValue.innerHTML = category.options[category.selectedIndex].label;
		} else {
			summarySentenceCategoryValue.innerHTML = "";
		}
	}
	
	// Convicts without amendment to original charge
	function convictOffenseItem(offenseItemIndex) {
		var offenseLabelContent = document.getElementById("offenseItems[" + offenseItemIndex + "].convictionFields.offenseLabelContent");
		if (ui.hasClass(offenseLabelContent, "hidden")) {
			ui.removeClass(offenseLabelContent, "hidden");
		}
		showEditOffenseHeader(offenseItemIndex);
	}
	
	// Updates connections of each offense item; index is that of connection that is to be updated in other connections 
	function updateOffenseItemConnections(offenseItemIndex) {
		var offense = document.getElementById("offenseItems[" + offenseItemIndex + "].convictionFields.offense");
		var offenseName;
		if (offense.value != null && offense.value != "") {
			var messageResolver = new common.MessageResolver("omis.offenseterm.msgs.offenseTerm");
			var offenseQuery = document.getElementById("offenseItems[" + offenseItemIndex + "].convictionFields.offenseQuery");
			var counts = document.getElementById("offenseItems[" + offenseItemIndex + "].convictionFields.counts");
			offenseName = offenseItemConnectionLabel = messageResolver.getMessage("consecutiveToSentenceLabel", {param0: offenseQuery.value, param1: counts.value});
		} else {
			offenseName = getNoOffenseSelectedMessage();
		}
		for (var innerOffenseItemIndex = 0; innerOffenseItemIndex < currentOffenseItemIndex; innerOffenseItemIndex++) {
			if (innerOffenseItemIndex != offenseItemIndex) {
				var connection = document.getElementById("offenseItems[" + innerOffenseItemIndex + "].connection");
				if (connection != null) {
					for (var connectionOptionIndex = 0; connectionOptionIndex < connection.options.length; connectionOptionIndex++) {
						var connectionOption = connection.options[connectionOptionIndex];
						var classificationIndexText = "CONSECUTIVE[" + offenseItemIndex + "]";
						if (connectionOption.value.indexOf(classificationIndexText) > -1) {
							connectionOption.value = classificationIndexText;
							connectionOption.label = offenseName;
							if (offense.value != null && offense.value != "") {
								ui.removeClass(connectionOption, "hidden");
							} else {
								ui.addClass(connectionOption, "hidden");
							}
						}
					}
				}
			}
		}
	}
	
	// Removes connection from those of each offense item; index is that of connection to remove 
	function removeOffenseItemConnections(offenseItemIndex) {
		for (var innerOffenseItemIndex = 0; innerOffenseItemIndex < currentOffenseItemIndex; innerOffenseItemIndex++) {
			if (innerOffenseItemIndex != offenseItemIndex) {
				
				// Only remove offense item from connections of other existing offense items
				if (document.getElementById("offenseItems[" + innerOffenseItemIndex + "]") != null) {
					var connection = document.getElementById("offenseItems[" + innerOffenseItemIndex + "].connection");
					for (var connectionOptionIndex = 0; connectionOptionIndex < connection.options.length; connectionOptionIndex++) {
						var connectionOption = connection.options[connectionOptionIndex];
						var classificationIndexText = "CONSECUTIVE[" + offenseItemIndex + "]";
						if (connectionOption.value.indexOf(classificationIndexText) > -1) {
							connectionOption.parentNode.removeChild(connectionOption);
						}
					}
				}
			}
		}
	}
	
	// Shows term (sentence) of offense item
	function showOffenseTerm(offenseItemIndex) {
		var sentenceContainer = document.getElementById("sentenceContainer[" +  offenseItemIndex + "]");
		if (ui.hasClass(sentenceContainer, "hidden")) {
			ui.removeClass(sentenceContainer, "hidden");
		}
	}
	
	// Hides term (sentence) of offense item
	function hideOffenseTerm(offenseItemIndex) {
		var sentenceContainer = document.getElementById("sentenceContainer[" + offenseItemIndex + "]");
		if (!ui.hasClass(sentenceContainer, "hidden")) {
			ui.addClass(sentenceContainer, "hidden");
		}
	}
	
	// Enables consecutive connection option in offense item
	function enableConsecutiveConnectionOption(offenseItemIndex, consecutiveConnectionIndex) {
		var connection = document.getElementById("offenseItems[" + offenseItemIndex + "].connection");
		for (var counter = 0; counter < connection.options.length; counter++) {
			var connectionOption = connection.options[counter];
			if (connectionOption.value == "CONSECUTIVE[" + consecutiveConnectionIndex + "]" && connectionOption.getAttribute("disabled") == "disabled") {
				connectionOption.removeAttribute("disabled");
			}
		}
	}
	
	// Disables consecutive connection option in offense item
	function disableConsecutiveConnectionOption(offenseItemIndex, consecutiveConnectionIndex) {
		var connection = document.getElementById("offenseItems[" + offenseItemIndex + "].connection");
		for (var counter = 0; counter < connection.options.length; counter++) {
			var connectionOption = connection.options[counter];
			if (connectionOption.value == "CONSECUTIVE[" + consecutiveConnectionIndex + "]" && (connectionOption.getAttribute("disabled") == "" || connectionOption.getAttribute("disabled") == null)) {
				connectionOption.setAttribute("disabled", "disabled");
			}
		}
	}
	
	// Action to fire when term fields are changed
	function onchangeTermDay(yearsElt, monthsElt, daysElt, totalDaysElt, totalDaysLabelElt) {
		var url = config.ServerConfig.getContextPath() + "/offenseTerm/calculateTotalDays.json";
		var params = "years=" + yearsElt.value + "&months=" + monthsElt.value + "&days=" + daysElt.value;
		var request = new XMLHttpRequest();
		request.open("get", url + "?" + params, false);
		request.send(null);
		if (request.status == 200) {
			var totalDays = eval("(" + request.responseText + ")");
			totalDaysElt.value = totalDays;
			var messageResolver = new common.MessageResolver("omis.sentence.msgs.sentence");
			totalDaysLabelElt.innerHTML = messageResolver.getMessage("totalDaysLabel", {param0: totalDays});
		} else {
			alert("Error - status: " + request.status + "; url: " + url + "?" + params);
		}
	}
	
	// Applies term fields behavior
	function applyTermFieldsBehavior(sentenceFields, termName) {
		
		// Returns offense item index from ID
		function getOffenseItemIndexFromId(id) {
			return id.substring(0, id.indexOf("]")).replace("offenseItems[", "");
		}
		
		// Applies change behavior to years field
		document.getElementById(sentenceFields + "." + termName + "Years").onchange = function() {
			var offenseItemIndex = getOffenseItemIndexFromId(this.getAttribute("id"));
			var termName = this.getAttribute("id").replace("offenseItems[" + offenseItemIndex + "].sentenceFields.", "").replace("Years", "");
			var monthsElt = document.getElementById("offenseItems[" + offenseItemIndex + "].sentenceFields." + termName + "Months");
			var daysElt = document.getElementById("offenseItems[" + offenseItemIndex + "].sentenceFields." + termName + "Days");
			var totalDaysElt = document.getElementById("offenseItems[" + offenseItemIndex + "].sentenceFields." + termName + "TotalDays");
			var totalDaysLabelElt = document.getElementById("offenseItems[" + offenseItemIndex + "].sentenceFields." + termName + "TotalDaysLabel");
			onchangeTermDay(this, monthsElt, daysElt, totalDaysElt, totalDaysLabelElt);
			return false;
		};
		
		// Applies change behavior to months field
		document.getElementById(sentenceFields + "." + termName + "Months").onchange = function() {
			var offenseItemIndex = getOffenseItemIndexFromId(this.getAttribute("id"));
			var termName = this.getAttribute("id").replace("offenseItems[" + offenseItemIndex + "].sentenceFields.", "").replace("Months", "");
			var yearsElt = document.getElementById("offenseItems[" + offenseItemIndex + "].sentenceFields." + termName + "Years");
			var daysElt = document.getElementById("offenseItems[" + offenseItemIndex + "].sentenceFields." + termName + "Days");
			var totalDaysElt = document.getElementById("offenseItems[" + offenseItemIndex + "].sentenceFields." + termName + "TotalDays");
			var totalDaysLabelElt = document.getElementById("offenseItems[" + offenseItemIndex + "].sentenceFields." + termName + "TotalDaysLabel");
			onchangeTermDay(yearsElt, this, daysElt, totalDaysElt, totalDaysLabelElt);
			return false;
		};
		
		// Applies change behavior to days field
		document.getElementById(sentenceFields + "." + termName + "Days").onchange = function() {
			var offenseItemIndex = getOffenseItemIndexFromId(this.getAttribute("id"));
			var termName = this.getAttribute("id").replace("offenseItems[" + offenseItemIndex + "].sentenceFields.", "").replace("Days", "");
			var yearsElt = document.getElementById("offenseItems[" + offenseItemIndex + "].sentenceFields." + termName + "Years");
			var monthsElt = document.getElementById("offenseItems[" + offenseItemIndex + "].sentenceFields." + termName + "Months");
			var totalDaysElt = document.getElementById("offenseItems[" + offenseItemIndex + "].sentenceFields." + termName + "TotalDays");
			var totalDaysLabelElt = document.getElementById("offenseItems[" + offenseItemIndex + "].sentenceFields." + termName + "TotalDaysLabel");
			onchangeTermDay(yearsElt, monthsElt, this, totalDaysElt, totalDaysLabelElt);
			return false;
		};
	}
	
	/*
	 * Clears the data associated with the specified controls
	 * @param clear button that on click will be added to
	 * @param query query input control
	 * @param value hidden value control
	 * @param display display control
	 */
	function applyClear(clear, query, value, display) {
		clear.onclick = function() {
			query.value = "";
			value.value = "";
			display.innerHTML = "";
			return false;
		};
	}
	
	/*
	 * Clears the data associated with the specified controls
	 * @param clear button that on click will be added to
	 * @param query query input control
	 * @param value hidden value control
	 */
	function applyClear(clear, query, value) {
		clear.onclick = function() {
			query.value = "";
			value.value = "";
			return false;
		};
	}
	
	// Applies offense behavior for offense fields with index
	// Behavior is for date pickers, icons and calculate link
	function applyOffenseBehavior(offenseItemIndex) {
		var offenseItem = "offenseItems[" + offenseItemIndex + "]";
		document.getElementById(offenseItem + ".expandCollapseLink").onclick = function() {
			var expanded = document.getElementById("offenseItems[" + offenseItemIndex + "].expanded");
			if (expanded.value == "true" || expanded.value == true) {
				collapseOffenseItem(offenseItemIndex);
				hideEditOffenseHeader(offenseItemIndex);
				showOffenseSummary(offenseItemIndex);
			} else {
				expandOffenseItem(offenseItemIndex);
				showEditOffenseHeader(offenseItemIndex);
				hideOffenseSummary(offenseItemIndex);
			}
			return false;
		};
		document.getElementById(offenseItem + ".removeLink").onclick = function() {
			var thisItemIndex = this.getAttribute("id").replace("offenseItems[", "").replace("].removeLink", "");
			var operation = document.getElementById("offenseItems[" + thisItemIndex + "].operation");
			var offenseItem = document.getElementById("offenseItems[" + thisItemIndex + "]");
			var offenseItemHeaderContent = document.getElementById("offenseItems[" + thisItemIndex + "].headerContent");
			if (operation.value == "CREATE") {
				removeOffenseItemConnections(offenseItemIndex);
				offenseItem.parentNode.removeChild(offenseItem);
			} else if (operation.value == "UPDATE") {
				operation.value = "REMOVE";
				ui.addOrReplaceClass(offenseItemHeaderContent, "backgroundLight", "removeSection");
				for (var innerItemIndex = 0; innerItemIndex < currentOffenseItemIndex; innerItemIndex++) {
					disableConsecutiveConnectionOption(innerItemIndex, thisItemIndex);
				}
			} else if (operation.value == "REMOVE") {
				operation.value = "UPDATE";
				ui.addOrReplaceClass(offenseItemHeaderContent, "removeSection", "backgroundLight");
				for (var innerItemIndex = 0; innerItemIndex < currentOffenseItemIndex; innerItemIndex++) {
					enableConsecutiveConnectionOption(innerItemIndex, thisItemIndex);
				}
			} else {
				alert("Unsupported operation: " + operation.value);
			}
			return false;
		};
		var convictionFields = offenseItem + ".convictionFields";
		applyValueLabelAutoComplete(document.getElementById(convictionFields + ".offenseQuery"), document.getElementById(convictionFields + ".offense"), config.ServerConfig.getContextPath() + "/offenseTerm/searchOffenses.json", function(event) {
			var target = event.target;
			var offenseItemIndex = target.getAttribute("id").replace("offenseItems[", "").replace("].convictionFields.offenseQuery", "");
			updateOffenseItemConnections(offenseItemIndex);
		});
		var counts = document.getElementById(offenseItem + ".convictionFields.counts");
		counts.onchange = function() {
			var offenseItemIndex = this.getAttribute("id").replace("offenseItems[", "").replace("].convictionFields.counts", "");
			updateOffenseItemConnections(offenseItemIndex);			
		};
		applyClear(document.getElementById(convictionFields + ".clearOffenseLink"), document.getElementById(convictionFields + ".offenseQuery"), document.getElementById(convictionFields + ".offense"));
		document.getElementById(convictionFields + ".offenseUrlLink").onclick = function() {
			var offenseItemIndex = this.getAttribute("id").replace("offenseItems[", "").replace("].convictionFields.offenseUrlLink", "");
			var offense = document.getElementById("offenseItems[" + offenseItemIndex + "].convictionFields.offense");
			if (offense != null && offense.value != "") {
				var url = config.ServerConfig.getContextPath() + "/offenseTerm/findOffense.json";
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
		applyDatePicker(document.getElementById(convictionFields + ".date"));
		var sentence = document.getElementById(offenseItem + ".sentence");
		if (sentence.value != null && sentence.value != "") {
			document.getElementById(offenseItem + ".sentenceOperation.UPDATE").onclick = function() {
				var offenseItemIndex = this.getAttribute("id").replace("offenseItems[", "").replace("].sentenceOperation.UPDATE", "");
				if (this.checked) {
					showOffenseTerm(offenseItemIndex);
					updateSentenceOperationMessage(offenseItemIndex, "UPDATE");
					hideHistoricalOffenseTerms(offenseItemIndex);
				} else {
					hideOffenseTerm(offenseItemIndex);
				}
			};
			document.getElementById(offenseItem + ".sentenceOperation.AMEND").onclick = function() {
				var offenseItemIndex = this.getAttribute("id").replace("offenseItems[", "").replace("].sentenceOperation.AMEND", "");
				if (this.checked) {
					showOffenseTerm(offenseItemIndex);
					updateSentenceOperationMessage(offenseItemIndex, "AMEND");
					showHistoricalOffenseTerms(offenseItemIndex);
				} else {
					hideOffenseTerm(offenseItemIndex);
				}
			};
			document.getElementById(offenseItem + ".sentenceOperation.REMOVE").onclick = function() {
				var offenseItemIndex = this.getAttribute("id").replace("offenseItems[", "").replace("].sentenceOperation.REMOVE", "");
				if (this.checked) {
					hideOffenseTerm(offenseItemIndex);
					updateSentenceOperationMessage(offenseItemIndex, "REMOVE");
					hideHistoricalOffenseTerms(offenseItemIndex);
				} else {
					showOffenseTerm(offenseItemIndex);
				}
			};
		} else {
			document.getElementById(offenseItem + ".emptySentenceOperation").onclick = function() {
				var offenseItemIndex = this.getAttribute("id").replace("offenseItems[", ""). replace("].emptySentenceOperation", "");
				if (this.checked) {
					hideOffenseTerm(offenseItemIndex);
					updateSentenceOperationMessage(offenseItemIndex, null);
				} else {
					showOffenseTerm(offenseItemIndex);
				}
			};
			document.getElementById(offenseItem + ".sentenceOperation.CREATE").onclick = function() {
				var offenseItemIndex = this.getAttribute("id").replace("offenseItems[", "").replace("].sentenceOperation.CREATE", "");
				if (this.checked) {
					showOffenseTerm(offenseItemIndex);
					updateSentenceOperationMessage(offenseItemIndex, "CREATE");
				} else {
					hideOffenseTerm(offenseItemIndex);
				}
			};
		}
		var sentenceFields = offenseItem + ".sentenceFields";
		applyDatePicker(document.getElementById(sentenceFields + ".pronouncementDate"));
		applyDatePicker(document.getElementById(sentenceFields + ".effectiveDate"));
		applyDatePicker(document.getElementById(sentenceFields + ".turnSelfInDate"));
		applyTermFieldsBehavior(sentenceFields, "prison");
		applyTermFieldsBehavior(sentenceFields, "probation");
		applyTermFieldsBehavior(sentenceFields, "deferred");
		document.getElementById(sentenceFields + ".calculateEffectiveDateLink").onclick = function() {
			var thisItemIndex = this.getAttribute("id").replace("offenseItems[", "").replace("].sentenceFields.calculateEffectiveDateLink", "");
			var sentenceFields = "offenseItems[" + thisItemIndex + "].sentenceFields";
			var pronouncementDate = document.getElementById(sentenceFields + ".pronouncementDate").value;
			var jailTimeCredit = document.getElementById(sentenceFields + ".jailTimeCredit").value;
			var streetTimeCredit = document.getElementById(sentenceFields + ".streetTimeCredit").value;
			if (pronouncementDate != null && pronouncementDate != "" && jailTimeCredit != null && jailTimeCredit != "" && streetTimeCredit != null && streetTimeCredit != "") {
				var url = config.ServerConfig.getContextPath() + "/offenseTerm/calculateSentenceEffectiveDate.json";
				var params = "pronouncementDate=" + pronouncementDate + "&jailTimeCredit=" + jailTimeCredit + "&streetTimeCredit=" + streetTimeCredit;
				var request = new XMLHttpRequest();
				request.open("get", url + "?" + params, false);
				request.send(null);
				if (request.status == 200) {
					document.getElementById(sentenceFields + ".effectiveDate").value = eval("(" + request.responseText + ")");
				} else {
					alert("Error - status: " + request.status + "; URL: " + url + "; params: " + params);
				}
			} else {
				var messageResolver = new common.MessageResolver("omis.offenseterm.msgs.offenseTerm");
				alert(messageResolver.getMessage("sentenceEffectiveDateCalculationRequirementsMessage"));
			}
			return false;
		};
		document.getElementById(sentenceFields + ".category").onchange = function() {
			var offenseItemIndex = this.getAttribute("id").replace("offenseItems[", "").replace("].sentenceFields.category", "");
			if (this.selectedIndex > -1 && this.options[this.selectedIndex].value != null && this.options[this.selectedIndex].value != "") {
				var categoryId = this.options[this.selectedIndex].value;
				var request = new XMLHttpRequest();
				request.open("get", config.ServerConfig.getContextPath() + "/offenseTerm/findSentenceCategory.json?sentenceCategory=" + categoryId, false);
				request.send(null);
				var category = eval("(" + request.responseText + ")");
				if (category.prisonRequirement == "REQUIRED" || category.prisonRequirement == "OPTIONAL") {
					showSentenceTermFields("prison", offenseItemIndex);
				} else {
					hideSentenceTermFields("prison", offenseItemIndex);	
				}
				if (category.probationRequirement == "REQUIRED" || category.probationRequirement == "OPTIONAL") {
					showSentenceTermFields("probation", offenseItemIndex);
				} else {
					hideSentenceTermFields("probation", offenseItemIndex);
				}
				if (category.deferredRequirement == "REQUIRED" || category.deferredRequirement == "OPTIONAL") {
					showSentenceTermFields("deferred", offenseItemIndex);
				} else {
					hideSentenceTermFields("deferred", offenseItemIndex);
				}
			} else {
				hideSentenceTermFields("prison", offenseItemIndex);
				hideSentenceTermFields("probation", offenseItemIndex);
				hideSentenceTermFields("deferred", offenseItemIndex);
			}
		};
	}
	
	// Applies form behavior
	var allowExistingDocket = document.getElementById("allowExistingDocket");
	var allowDocketFields = document.getElementById("allowDocketFields");
	if (allowExistingDocket.value && allowDocketFields.value) {
		document.getElementById("existingDocket").onchange = function() {
			var existingDocketValue = this.options[this.selectedIndex].value;
			var docketValue = document.getElementById("docketFields.value");
			var court = document.getElementById("docketFields.court");
			if (existingDocketValue != null && existingDocketValue != "") {
				docketValue.disabled = true;
				court.disabled = true;
			} else {
				docketValue.disabled = false;
				court.disabled = false;
			}
		};
	}
	applyDatePicker(document.getElementById("fields.pronouncementDate"));
	applyDatePicker(document.getElementById("fields.sentenceReviewDate"));
	applyValueLabelAutoComplete(document.getElementById("fields.judgeQuery"), document.getElementById("fields.judge"), config.ServerConfig.getContextPath() + "/offenseTerm/searchJudges.json");
	applyClear(document.getElementById("fields.clearJudgeLink"), document.getElementById("fields.judgeQuery"), document.getElementById("fields.judge"), document.getElementById("fields.judgeDisplay"));
	applyActionMenu(document.getElementById("actionMenuLink"));
	applyActionMenu(document.getElementById("offensesActionMenuLink"), function() {
		
		// Applies behavior to create offense link
		var createOffenseLink = document.getElementById("createOffenseLink");
		createOffenseLink.onclick = function() {
			var url = createOffenseLink.getAttribute("href") + "&itemIndex=" + currentOffenseItemIndex;
			var request = new XMLHttpRequest();
			request.open("GET", url + "&timestamp=" + new Date().getTime(), false);
			request.send();
			if (request.status == 200) {
				var offenseContainer = document.getElementById("offensesContainer");
				var emptyOffensesMessage = document.getElementById("emptyOffensesMessage");
				if (emptyOffensesMessage != null) {
					offenseContainer.removeChild(emptyOffensesMessage);
				}
				ui.appendHtml(offenseContainer, request.responseText);
				applyOffenseBehavior(currentOffenseItemIndex);
			} else {
				alert("Error - status: " + request.status + "; URL: " + url);
				return false;
			}
			
			// Adds a hidden representation of the new offense item to which  other offenses can run consecutive
			// The option becomes visible when an offense is specified
			var offenseItemConnectionLabel = getNoOffenseSelectedMessage();
			var offenseItemConnectionValue = "CONSECUTIVE[" + currentOffenseItemIndex + "]";
			for (var offenseItemIndex = 0; offenseItemIndex < currentOffenseItemIndex; offenseItemIndex++) {
				
				// Only add new offense item to connections of other existing offense items
				if (document.getElementById("offenseItems[" + offenseItemIndex + "]") != null) {
					var connection = document.getElementById("offenseItems[" + offenseItemIndex + "].connection");
					var option = document.createElement("option");
					option.value = offenseItemConnectionValue;
					option.label = offenseItemConnectionLabel;
					ui.addClass(option, "hidden");
					connection.add(option);
				}
			}
			
			// Adds existing offense terms to connection of new offense term
			// Skips the first offense item as no other items exist on creation
			if (currentOffenseItemIndex > 0) {
				var connection = document.getElementById("offenseItems[" + currentOffenseItemIndex + "].connection");
				for (var offenseItemIndex = 0; offenseItemIndex < currentOffenseItemIndex; offenseItemIndex++) {
					
					// Only add offenses of items that exist to connections of new offense item 
					if (document.getElementById("offenseItems[" + offenseItemIndex + "]") != null) {
						var offense = document.getElementById("offenseItems[" + offenseItemIndex + "].convictionFields.offense");
						var offenseQuery = document.getElementById("offenseItems[" + offenseItemIndex + "].convictionFields.offenseQuery");
						var counts = document.getElementById("offenseItems[" + offenseItemIndex + "].convictionFields.counts");
						var option = document.createElement("option");
						var offenseItemConnectionLabel;
						var offenseItemConnectionValue;
						if (offense != null && offense.value != "") {
							var messageResolver = new common.MessageResolver("omis.offenseterm.msgs.offenseTerm");
							offenseItemConnectionLabel = messageResolver.getMessage("consecutiveToSentenceLabel", {param0: offenseQuery.value, param1: counts.value});
						} else {
							offenseItemConnectionLabel = getNoOffenseSelectedMessage();
							ui.addClass(option, "hidden");
						}
						option.value = "CONSECUTIVE[" + offenseItemIndex + "]";
						option.label = offenseItemConnectionLabel;
						connection.add(option);
					}
				}
			}
			
			// Increases current offense item index and prevents link from being followed
			currentOffenseItemIndex = currentOffenseItemIndex + 1;
			return false;
		};
	});
	
	// Applies offense behavior to offenses initially displayed with page
	for (var offenseItemIndex = 0; offenseItemIndex < currentOffenseItemIndex; offenseItemIndex++) {
		var offenseItem = document.getElementById("offenseItems[" + offenseItemIndex + "]");
		if (offenseItem != null) {
			applyOffenseBehavior(offenseItemIndex);
		}
	}
};