/**
 * @author Sheronda Vaughn
 * @version 0.1.0 (Nov 10, 2016)
 * @since OMIS 3.0
 */

/** Applies caseload search and clear functionality.
 * 
 * usage: applyCaseloadSearch(document.getElementById("test"),
 *			document.getElementById("hidden"), 
 *			document.getElementById("clear")); 
 * 
 * @param input input element.
 * @param target target element.
 * @param clear clear control element. */
function applyCaseloadSearch(input, target, targetLabel, clear) {
	var mClear = clear;
	var mTargetLabel = targetLabel;
	if (clear === undefined) {
		mClear = targetLabel;
		mTargetLabel = undefined;
	}
	ui.search.Autocomplete.CASELOAD_SEARCH(input, target, mTargetLabel, mClear);
}