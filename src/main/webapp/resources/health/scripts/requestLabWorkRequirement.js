/**
 * Behavior of screen to request lab work requirement for a health request.
 *
 * Author: Stephen Abson
 */
window.onload = function() {
	var requestLabWorkRequirementLink = document.getElementById("requestLabWorkRequirementLink");
	for (var itemIndex = 0; itemIndex < currentIndex; itemIndex++) {
		var sampleDate = document.getElementById("labWorkRequirementRequestItems[" + itemIndex + "].sampleDate");
		applyDatePicker(sampleDate);
		var sampleOrderedDate = document.getElementById("labWorkRequirementRequestItems[" + itemIndex + "].sampleOrderedDate");
		applyDatePicker(sampleOrderedDate);
		var removeLink = document.getElementById("labWorkRequirementRequestItems[" + itemIndex + "].removeLink");
		removeLink.onclick = function() {
			var index = this.getAttribute("id").replace("labWorkRequirementRequestItems[", "").replace("].removeLink", "");
			var row = document.getElementById("labWorkRequirementRequestItems[" + index + "].row");
			var operation = document.getElementById("labWorkRequirementRequestItems[" + index + "].operation");
			row.setAttribute("class", (row.getAttribute("class") != null ? row.getAttribute("class").replace("removedRequestRow", "") + "removedRequestRow" : "removedRequestRow"));
			operation.setAttribute("value", "REMOVE");
			return false;
		};
	}
	requestLabWorkRequirementLink.onclick = function() {
		var url = this.getAttribute("href") + "&currentIndex=" + currentIndex;
		$.ajax(url,
				   {
					   async: false,
					   success: function(data) {
					   		$("#labWorkRequestBody").append(data);
					   		var sampleDate = document.getElementById("labWorkRequirementRequestItems[" + currentIndex + "].sampleDate");
					   		applyDatePicker(sampleDate);
							var sampleOrderedDate = document.getElementById("labWorkRequirementRequestItems[" + currentIndex + "].sampleOrderedDate");
							applyDatePicker(sampleOrderedDate);
					   		var removeLink = document.getElementById("labWorkRequirementRequestItems[" + currentIndex + "].removeLink");
					   		var row = document.getElementById("labWorkRequirementRequestItems[" + currentIndex + "].row");
					   		removeLink.onclick = function() {
					   			$(row).remove();
					   			return false;
					   		};
					   		currentIndex = currentIndex + 1;
					   },
					   error: function(jqXHR, textStatus, errorThrown) {
						   alert("Error: " + textStatus + "; " + errorThrown);
					   },
					   type: "GET"
				   }
			);
		return false;
	};
};