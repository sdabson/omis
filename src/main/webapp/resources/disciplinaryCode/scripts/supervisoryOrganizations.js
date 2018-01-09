/*
 * Supervisory Organizations Selection List java script.
 * 
 * Author: Annie Jacques
 * Version: 0.1.0 (Aug 10, 2016)
 * Since: OMIS 3.0
 */
window.onload = function() {
	var rows = document.getElementsByClassName('rowActionMenuItem');
	for(var i = 0; i < rows.length; i++){
		applyActionMenu(rows[i]);
	}
}