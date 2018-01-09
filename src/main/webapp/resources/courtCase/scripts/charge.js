/**
 * Charge detail form behavior.
 * 
 * @author Josh Divine
 * @version 0.1.0 (Aug 15, 2017)
 * @since OMIS 3.0
 */

/** Assign initial element behavior. */
window.onload = function() {
	applyActionMenu(document.getElementById("actionMenuLink"));
	applyFormUpdateChecker(document.getElementById("chargeForm"));
	applyDatePicker(document.getElementById("date"));
	applyDatePicker(document.getElementById("fileDate"));
	document.getElementById("offenseUrlLink").onclick = function() {
		var offense = document.getElementById("offense");
		if (offense.selectedIndex > -1 && offense.options[offense.selectedIndex].value != "" && offense.options[offense.selectedIndex].value != null) {
			var url = config.ServerConfig.getContextPath() + "/courtCase/findOffense.json";
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
