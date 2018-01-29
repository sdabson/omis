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
 * Tracked document edit screen java script file
 * 
 * Author: Yidong Li.
 * 
 * Date: Dec 15, 2017
 */

window.onload = function() {
	function applyExistingReceivalItemBehavior(x){
		var existingItemRemoveLink = document.getElementById("trackedDocumentItemRemoveLink[" + x + "]");
		existingItemRemoveLink.onclick = function() {
			var existingItemOperation = document.getElementById(this.getAttribute("id").replace("trackedDocumentItemRemoveLink", "trackedDocumentItemOperation"));
			var existingItemRow = document.getElementById("trackedDocumentItemTableRow[" + x + "]");
			if(existingItemOperation.value == "EDIT"){
				if (!ui.hasClass(existingItemRow, "removeRow")) {
					ui.addClass(existingItemRow, "removeRow");
					existingItemOperation.value = "REMOVE";
				} 
			} else if (existingItemOperation.value == "REMOVE"){
				if(ui.hasClass(existingItemRow, "removeRow")) {
					ui.removeClass(existingItemRow, "removeRow");
					existingItemOperation.value = "EDIT";
				}
			}
			return false;
		}
	}

	function applyTrackedDocumentRowBehavior(trackedDocumentItemIndex) {
		var newTrackedDocumentItemRemoveLink = document.getElementById("trackedDocumentItemRemoveLink[" + trackedDocumentItemIndex + "]");
		newTrackedDocumentItemRemoveLink.onclick = function() {
			var operation = document.getElementById(this.getAttribute("id").replace("trackedDocumentItemRemoveLink", "trackedDocumentItemOperation"));
			trackedDocumentTableRow = document.getElementById(this.getAttribute("id").replace("trackedDocumentItemRemoveLink", "trackedDocumentItemTableRow"));
			trackedDocumentTableRow.parentNode.removeChild(trackedDocumentTableRow);
			return false;
		};
		applyDatePicker(document.getElementById("trackedDocumentItemsDate["+trackedDocumentItemIndex + "]"));
	}

	for(var x=0; x<itemIndex; x++){
		var existingTrackedDocumentItemRemoveLink = document.getElementById("trackedDocumentItemRemoveLink[" + x + "]");
		var operation = document.getElementById("trackedDocumentItemOperation[" + x + "]");
		var row = document.getElementById("trackedDocumentItemTableRow[" + x + "]");
		if(operation.value == "REMOVE"){
			if(!ui.hasClass(row, "removeRow")) {
				ui.addClass(row, "removeRow");
			}
		}
		if(operation.value == "REMOVE" || operation.value == "EDIT"){
			applyExistingReceivalItemBehavior(x)
		}
		if(operation.value == "CREATE"){
			applyTrackedDocumentRowBehavior(x);
		}
	}
	
	applyActionMenu(document.getElementById("trackedDocumentEditTableActionMenuLink"), function() {
		var createTrackDocumentLink = document.getElementById("createTrackedDocumentLink");
		createTrackDocumentLink.onclick = function() {
		var url = createTrackDocumentLink.getAttribute("href") + "&trackedDocumentItemIndex=" + itemIndex;
		var request = new XMLHttpRequest();
		request.open("GET", url, false);
		request.send();
		if (request.status == 200) {
			ui.appendHtml(document.getElementById("trackedDocumentReceivalBody"), request.responseText);
			applyTrackedDocumentRowBehavior(itemIndex);
			itemIndex++;
		} else {
			alert("Error - status: " + request.status + "; URL: " + url);
		}
		return false;
		};
	});
	applyActionMenu(document.getElementById("trackedDocumentEditActionMenuLink"));
}