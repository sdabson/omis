/**
 * Behavior to display lab works.
 *
 * Author: Yidong Li.
 * 
 * Date: Dec.4, 2014
 */

$(document).ready(function($) {
	applyDatePicker(document.getElementById("filterByStartDate"));
	applyDatePicker(document.getElementById("filterByEndDate"));
	resolveLabWorkDatePicker();
	applyPersonSearch(document.getElementById("offenderInput"), 
			document.getElementById("offender"),
			document.getElementById("offenderDisplay"), 
			document.getElementById("clear"));
	applyFormUpdateChecker(document.getElementById("searchLabWorkForm"));

	function resolveLabWorkDatePicker() {
	for (var x = 0; x < total; x++) {
		applyDatePicker(document.getElementById("resolveLabWorkItems[" + x + "].resultsDate"));
		var removeLink = document.getElementById("resolveLabWorkItems[" + x + "].removeLink");
		var sampleTaken = document.getElementById("resolveLabWorkItems[" + x + "].sampleTaken");
		var originalSampleTaken = sampleTaken;
		
		removeLink.onclick = function() {
			var index = this.getAttribute("id").replace("resolveLabWorkItems[", "").replace("].removeLink", "");
			var operation = document.getElementById("resolveLabWorkForm.labWorkItems[" + index + "].operation");
			var labWorkItem = document.getElementById("labWorkItems[" + index + "]");
			switch(operation.getAttribute("value")){                                                      // Toggle the operation
				case "REMOVE":
					operation.setAttribute("value", "UPDATE");
					removeClass(labWorkItem);
					break;
				case "UPDATE": 
					operation.setAttribute("value", "REMOVE");
					addClass(labWorkItem);
					break;
				default:
			}
		};
		
		if(sampleTaken.getAttribute("disabled") != "disabled")
		{
			sampleTaken.onclick = function() 
			{
				var index = this.getAttribute("id").replace("resolveLabWorkItems[", "").replace("].sampleTaken", "");
				var sampleTaken = document.getElementById("resolveLabWorkItems[" + index + "].sampleTaken");
				if (sampleTaken.checked) {
					var sampleNotes = document.getElementById("labWorkItems[" + index + "].sampleNotes");
					sampleNotes.removeAttribute("disabled"); 
					var resultsLab = document.getElementById("labWorkItems[" + index + "].resultsLab");
					resultsLab.removeAttribute("disabled"); 
					var resultNotes = document.getElementById("resolveLabWorkItems[" + index + "].resultNotes");
					resultNotes.removeAttribute("disabled"); 
					var resultDate = document.getElementById("resolveLabWorkItems[" + index + "].resultsDate");
					resultDate.removeAttribute("disabled"); 
				} else {
					var sampleNotes = document.getElementById("labWorkItems[" + index + "].sampleNotes");
					sampleNotes.setAttribute("disabled","disabled"); 
					var resultsLab = document.getElementById("labWorkItems[" + index + "].resultsLab");
					resultsLab.setAttribute("disabled", "disabled"); 
					var resultNotes = document.getElementById("resolveLabWorkItems[" + index + "].resultNotes");
					resultNotes.setAttribute("disabled", "disabled"); 
					var resultDate = document.getElementById("resolveLabWorkItems[" + index + "].resultsDate");
					resultDate.setAttribute("disabled", "disabled"); 	
				}
			}
		}
	}
	
	return false;
}});

