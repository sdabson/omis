/**
 * Author: Yidong Li.
 * 
 * Date: Dec 15, 2017
 */

window.onload = function() {
	for(var x=0; x<itemIndex; x++){
		var trackedDocumentItemRemoveLink = document.getElementById("trackedDocumentItemRemoveLink[" + x + "]");
		trackedDocumentItemRemoveLink.onclick = function() {
			alert("3");
			var operation = document.getElementById(this.getAttribute("id").replace("trackedDocumentItemRemoveLink", "trackedDocumentItemOperation"));
			trackedDocumentTableRow = document.getElementById(this.getAttribute("id").replace("trackedDocumentItemRemoveLink", "trackedDocumentItemTableRow"));
			trackedDocumentTableRow.parentNode.removeChild(trackedDocumentTableRow);
			return false;
		};
	}
	
	function applyTrackedDocumentRowBehavior(trackedDocumentItemIndex) {
		var trackedDocumentItemRemoveLink = document.getElementById("trackedDocumentItemRemoveLink[" + trackedDocumentItemIndex + "]");
		trackedDocumentItemRemoveLink.onclick = function() {
			var operation = document.getElementById(this.getAttribute("id").replace("trackedDocumentItemRemoveLink", "trackedDocumentItemOperation"));
			trackedDocumentTableRow = document.getElementById(this.getAttribute("id").replace("trackedDocumentItemRemoveLink", "trackedDocumentItemTableRow"));
			trackedDocumentTableRow.parentNode.removeChild(trackedDocumentTableRow);
			return false;
		};
		applyDatePicker(document.getElementById("trackedDocumentItemsDate["+trackedDocumentItemIndex + "]"));
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