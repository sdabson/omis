/**
 * Behavior for searching user accounts.
 *
 * @author Stephen Abson
 */

/**
 * Applies functionality to search for user accounts by entering a query
 * into the input.
 * 
 * @param inputElt input to enter query string
 * @param labelElt result label
 * @param hiddenElt result value
 * @param clearElt element to click to clear verification account
 * @param userCurrentElt element to click to user current user account for
 * verification
 */
function applySearchUserAccountsAutocomplete(inputElt, labelElt, hiddenElt, clearElt,
		useCurrentElt) {
	$(inputElt).autocomplete({
		source: config.ServerConfig.getContextPath() + "/user/userAccount/search.json",
		minLength: 3,
		select: function(event, ui) {
			$(hiddenElt).val(ui.item.value);
			$(labelElt).html(ui.item.label);
			return false;
		}
	});
	$(clearElt).click(function() {
		$(hiddenElt).val("");
		$(inputElt).val("");
		$(labelElt).html("");
		return false;
	});
	$(useCurrentElt).click(function() {
		$(hiddenElt).val(config.SessionConfig.getUserAccountId());
		$(labelElt).html(config.SessionConfig.getUserAccountLabel());
		return false;
	});
}