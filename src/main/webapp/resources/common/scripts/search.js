/** Provides offender selection UI.
 * author Ryan Johns
 * author Sheronda Vaughn
 * version 0.1.0 (Feb 24, 2014) */
/** Applies offender search.
 * @param input input element.
 * @param target target element.
 * @param searchImplementation search implementation.
 * @param options additional options. */
function applySearch(input, target, searchImplementation,onselect, options) {
	var x;
	searchImplementation(input, target,x,x,onselect, options);
}

/** Applies person search and clear functionality.
 * 
 * usage: applyPersonSearch(document.getElementById("test"),
 *			document.getElementById("hidden"), 
 *			document.getElementById("clear")); 
 * 
 * @param input input element.
 * @param target target element.
 * @param clear clear control element. */
function applyPersonSearch(input, target, targetLabel,  clear) {
	var mClear = clear;
	var mTargetLabel = targetLabel;
	if (clear === undefined) {
		mClear = targetLabel;
		mTargetLabel = undefined;
	}
	ui.search.Autocomplete.PERSON_SEARCH(input, target,mTargetLabel, mClear);
}

/** Applies user search and clear functionality.
 * 
 * usage: applyUserSearch(document.getElementById("test"),
 *			document.getElementById("hidden"), 
 *			document.getElementById("current"),
 *			document.getElementById("clear")); 
 * 
 * @param input input element.
 * @param target target element.
 * @param current current control element.
 * @param clear clear control element. */
function applyUserIDSearch(input, target, targetLabel, current, clear) {
	var mClear = clear;
	var mTargetLabel = targetLabel;
	if (clear === undefined) {
		mClear = targetLabel;
		mTargetLabel = undefined;
	}
	ui.search.Autocomplete.USERID_SEARCH(input, target, mTargetLabel, current, mClear);
}

/** Applies user search and clear functionality.
 * 
 * usage: applyUserSearch(document.getElementById("test"),
 *			document.getElementById("hidden"), 
 *			document.getElementById("current"),
 *			document.getElementById("clear")); 
 * 
 * @param input input element.
 * @param target target element.
 * @param current current control element.
 * @param clear clear control element. */
function applyUserSearch(input, target, targetLabel, current, clear) {
	var mClear = clear;
	var mTargetLabel = targetLabel;
	if (clear === undefined) {
		mClear = targetLabel;
		mTargetLabel = undefined;
	}
	ui.search.Autocomplete.USER_SEARCH(input, target, mTargetLabel, current, mClear);
}

/** Applies Staff search and clear functionality.
 * 
 * usage: applyStaffSearch(document.getElementById("test"),
 *			document.getElementById("hidden"), 
 *			document.getElementById("current"),
 *			document.getElementById("clear")); 
 * 
 * @param input input element.
 * @param target target element.
 * @param current current control element.
 * @param clear clear control element. */
function applyStaffSearch(input, target,targetLabel, current, clear) {
	var mClear = clear;
	var mTargetLabel = targetLabel;
	if (clear === undefined) {
		mClear = targetLabel;
		mTargetLabel = undefined;
	}
	ui.search.Autocomplete.STAFF_SEARCH(input, target, mTargetLabel, current, mClear);
}

/** Applies offender search and clear functionality.
 * 
 * usage: applyOffenderSearch(document.getElementById("test"),
 *			document.getElementById("hidden"), 
 *			document.getElementById("clear")); 
 * 
 * @param input input element.
 * @param target target element.
 * @param clear clear control element. */
function applyOffenderSearch(input, target, targetLabel, clear) {
	var mClear = clear;
	var mTargetLabel = targetLabel;
	if (clear === undefined) {
		mClear = targetLabel;
		mTargetLabel = undefined;
	}
	ui.search.Autocomplete.OFFENDER_SEARCH(input, target, mTargetLabel, mClear);
}

/** Applies employer search and clear functionality.
 * 
 * usage: applyEmployerSearch(document.getElementById("test"),
 *			document.getElementById("hidden"), 
 *			document.getElementById("clear")); 
 * 
 * @param input input element.
 * @param target target element.
 * @param clear clear control element. */
function applyEmployerSearch(input, target, targetLabel, clear) {
	var mClear = clear;
	var mTargetLabel = targetLabel;
	if (clear === undefined) {
		mClear = targetLabel;
		mTargetLabel = undefined;
	}
	ui.search.Autocomplete.EMPLOYER_SEARCH(input, target, mTargetLabel, mClear);
}

/** Applies location search and clear functionality.
 * usage: applyLocationSearch(document.getElementById("test"),
 *          document.getElementById("hidden"),
 *          document.getElementById("clear"));
 * @param input input element.
 * @param target target element.
 * @param clear clear control element. */
function applyLocationSearch(input, target, targetLabel, clear) {
	var mClear = clear;
	var mTargetLabel = targetLabel;
	if (clear === undefined) {
		mClear = targetLabel;
		mTargetLabel = undefined;
	}
	ui.search.Autocomplete.LOCATION_SEARCH(input, target, mTargetLabel, mClear);
}

/** Applies Staff assignment search and clear functionality.
 * 
 * usage: applyStaffAssignmentSearch(document.getElementById("test"),
 *			document.getElementById("hidden"), 
 *			document.getElementById("current"),
 *			document.getElementById("clear")); 
 * 
 * @param input input element.
 * @param target target element.
 * @param current current control element.
 * @param clear clear control element. */
function applyStaffAssignmentSearch(input, target,targetLabel, current, clear) {
	var mClear = clear;
	var mTargetLabel = targetLabel;
	if (clear === undefined) {
		mClear = targetLabel;
		mTargetLabel = undefined;
	}
	ui.search.Autocomplete.STAFF_ASSIGNMENT_SEARCH(input, target, mTargetLabel, current, mClear);
}