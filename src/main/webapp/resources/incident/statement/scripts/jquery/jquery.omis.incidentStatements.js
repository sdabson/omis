/**
 * Jquery implementation of functions for incidentReport.js
 * 
 * Author: Joel Norris
 * Version: 0.1.0 (Feb 26, 2016)
 * Since: OMIS 3.0
 */

function applyIncidentReportRowOnClick() {
	$(".incidentStatementRowActionMenu").each(function () {
		applyActionMenu(document.getElementById($(this).attr("id")));
	});
}