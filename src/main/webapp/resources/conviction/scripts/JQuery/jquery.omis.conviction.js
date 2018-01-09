/**
 * Jquery implementation of functions for conviction.js
 * 
 * @author: Josh Divine
 * @version: 0.1.0 (May 15, 2017)
 * @since: OMIS 3.0
 */

/**
 * Assigns on click functionality for the court case note items action menu.
 */
function convictionActionMenuOnClick() {
	$("#createConvictionLink").click(function() {
		$.ajax(config.ServerConfig.getContextPath() + "/conviction/addConviction.html",
			   {
				   type: "GET",
				   async: false,
				   data: { convictionIndex: currentConvictionIndex },
				   success: function(data) {
				   		$("#convictionsBody").append(data);
				   		convictionRowOnClick(currentConvictionIndex);
				   },
				   error: function(jqXHR, textStatus, errorThrown) {
						alert("Error - status: " + textStatus + "; error: "
								+ errorThrown);
							$("#convictionsBody").html(jqXHR.responseText);
				   }
				   
			   }
			);
			currentConvictionIndex++;
   			return false;
	});
};

/**
 * Assigns on click functionality for the conviction item row with the
 * specified conviction index.
 * 
 * @param convictionIndex conviction index
 */
function convictionRowOnClick(convictionIndex) {
	applyDatePicker(document.getElementById("convictionItems[" + convictionIndex + "].date"));
	var removeLink = document.getElementById("convictionItems[" + convictionIndex + "].removeLink");
	$(removeLink).click(function() {
		var operation = document.getElementById("convictionItems[" + convictionIndex + "].operation");
		var row = document.getElementById("convictionItems[" + convictionIndex + "].row");
		if ($(operation).val() == "EDIT") {
			$(operation).val("REMOVE");
			$(row).addClass("removeRow");
		} else if($(operation).val() == "REMOVE") {
			$(operation).val("EDIT");
			$(row).removeClass("removeRow");
		} else {
			$(row).remove();
		}
		return false;
	});
	document.getElementById("convictionItems[" + convictionIndex + "].offenseUrlLink").onclick = function() {
		var offense = document.getElementById("convictionItems[" + convictionIndex + "].offense");
		if (offense.selectedIndex > -1 && offense.options[offense.selectedIndex].value != "" && offense.options[offense.selectedIndex].value != null) {
			var url = config.ServerConfig.getContextPath() + "/conviction/findOffense.json";
			var params = "offense=" + offense.options[offense.selectedIndex].value;
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
};