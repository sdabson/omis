/**
 * Click functionality for substance test sample.
 * 
 * Author: Joel Norris
 * Since: OMIS 3.0 (May 04, 2015)
 * Version: 0.1.0
 */
$(document).ready(function() {
	assignOnClick();
	applyUserSearch(document.getElementById("collectionEmployeeInput"),
			document.getElementById("collectionEmployee"),
			document.getElementById("collectionEmployeeDisplay"),
			document.getElementById("collectionEmployeeCurrent"),
			document.getElementById("collectionEmployeeClear"));
	applyActionMenu(document.getElementById("actionMenuLink"));
	applyFormUpdateChecker(document.getElementById("substanceTestSampleForm"));
});