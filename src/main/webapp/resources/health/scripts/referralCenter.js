/** Referral Center.
 * @author: Ryan Johns
 * @author: Stephen Abson
 * @version: 0.1.0 (Apr 01, 2014)
 * @since OMIS 3.0 */
$(document).ready(function($) {
	var messageResolver = new common.MessageResolver("omis.health.msgs.health");
	function prepActionMenus() {
		var actionMenus = document.getElementsByClassName("actionMenuItem");
		
		for (var x = 0, actionMenu; actionMenu = actionMenus[x]; x++) {
			applyActionMenu(actionMenu);
		}
	}
	
	
	var requestLabel = messageResolver.getMessage("healthRequestLabel"); 
	
	applyCancelLinkConfirmation("cancelRequestLink", requestLabel);
	
	prepActionMenus();
	var pendingRequestsListTable = document.getElementById("pendingRequestsListTable");
	if (pendingRequestsListTable != null) {
		applyCommentBehavior(pendingRequestsListTable, "commentLink","notes");
	}
	var pendingAuthorizationListTable = document.getElementById("pendingAuthorizationListTable");
	if (pendingAuthorizationListTable != null) {
		applyCommentBehavior(pendingAuthorizationListTable, "commentLink","notes");
	}
	var authorizedReferralsListTable = document.getElementById("authorizedReferralsListTable");
	if (authorizedReferralsListTable != null) {
		applyCommentBehavior(authorizedReferralsListTable,"commentLink", "notes");
	}
	var scheduledReferralsListTable = document.getElementById("scheduledReferralsListTable");
	if (scheduledReferralsListTable != null) {
		applyCommentBehavior(scheduledReferralsListTable,"commentLink", "notes");
	}
	var resovledReferralsListTable = document.getElementById("resovledReferralsListTable");
	if (resovledReferralsListTable != null) {
		applyCommentBehavior(resovledReferralsListTable,"commentLink", "notes");
	}
	applyToggleReferralCenterFilter();
	applyToggleReferral();
	
	applyDatePicker(document.getElementById("filterByStartDate"));
	applyDatePicker(document.getElementById("filterByEndDate"));
	applySearch(document.getElementById("filterByOffenderSearchText"),document.getElementById("filterByOffender"), ui.search.Autocomplete.OFFENDER_SEARCH);
	var filterByOffenderSearchText = document.getElementById("filterByOffenderSearchText");
	if (filterByOffenderSearchText != null) {
		filterByOffenderSearchText.focus();
		filterByOffenderSearchText.onfocus = function() {
			this.select();
		};
	}
});