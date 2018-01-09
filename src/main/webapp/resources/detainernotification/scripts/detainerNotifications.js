/*
 * Detainer Notifications java script.
 * 
 * Author: Annie Jacques
 * Version: 0.1.0 (July 14, 2016)
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
}