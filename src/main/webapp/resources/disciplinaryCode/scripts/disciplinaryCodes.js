/*
 * Disciplinary Codes List java script.
 * 
 * Author: Annie Jacques
 * Version: 0.1.0 (Aug 10, 2016)
 * Since: OMIS 3.0
 */
window.onload = function() {
	applyActionMenu(document.getElementById("actionMenuLink"));
	var rows = document.getElementsByClassName('rowActionMenuItem');
	for(var i = 0; i < rows.length; i++){
		applyActionMenu(rows[i], function() {
			applyRemoveLinkConfirmation();
		});
		
	}
	applyDatePicker(document.getElementById("toDate"));
	applyDatePicker(document.getElementById("fromDate"));
	applyDatePicker(document.getElementById("effectiveDate"));
	checkDateUsage();
	
	$('#useEffectiveDate').click(function(){
		checkDateUsage();
	})
	$('#useDateRange').click(function(){
		checkDateUsage();
	})
	
	function checkDateUsage(){
		if(document.getElementById('useEffectiveDate').checked){
			document.getElementById('effectiveDate').removeAttribute("disabled");
			document.getElementById('fromDate').disabled = "true";
			document.getElementById('toDate').disabled = "true";
		}
		
		if(document.getElementById('useDateRange').checked){
			document.getElementById('fromDate').removeAttribute("disabled");
			document.getElementById('toDate').removeAttribute("disabled");
			document.getElementById('effectiveDate').disabled = "true";
		}
	}
}