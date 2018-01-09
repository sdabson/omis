/**
 * Court case detail form behavior.
 * 
 * @author Stephen Abson
 * @author Joel Norris
 * @author Ryan Johns
 * @author Josh Divine
 * @version 0.1.3 (Sept 15, 2017)
 * @since OMIS 3.0
 */

/** Assign initial element behavior. */
window.addEventListener("load", function() {
	applyActionMenu(document.getElementById("actionMenuLink"));
	applyFormUpdateChecker(document.getElementById("courtCaseForm"));
	applyValueLabelAutoComplete(document.getElementById("judgeQuery"), document.getElementById("judge"), config.ServerConfig.getContextPath() + "/courtCase/searchJudges.json");
	applyClear(document.getElementById("judgeClear"), document.getElementById("judgeQuery"), document.getElementById("judge"), document.getElementById("judgeDisplay"));
	applyDatePicker(document.getElementById("pronouncementDate"));
	applyDatePicker(document.getElementById("sentenceReviewDate"));
	applyCourtCaseBehavior();
	applyActionMenu(document.getElementById("chargesActionMenuLink"), chargeActionMenuOnClick);
	applyActionMenu(document.getElementById("courtCaseNotesActionMenuLink"), courtCaseNoteActionMenuOnClick);
});

/**
 * Clears the data associated with the specified controls
 * @param clear button that on click will be added to
 * @param query query input control
 * @param value hidden value control
 * @param display display control
 */
function applyClear(clear, query, value, display) {
	clear.onclick = function() {
		query.value = "";
		value.value = "";
		display.innerHTML = "";
		return false;
	};
};

/**
 * Clears the data associated with the specified controls
 * @param clear button that on click will be added to
 * @param query query input control
 * @param value hidden value control
 */
function applyClear(clear, query, value) {
	clear.onclick = function() {
		query.value = "";
		value.value = "";
		return false;
	};
};