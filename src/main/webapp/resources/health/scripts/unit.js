/*
 * Unit libraries - to be replaced with unit module libraries when converted.
 *
 * @author Stephen Abson
 */

/**
 * Applies behavior to search offenders and look up and display unit name.
 * 
 * @param offenderElt offender hidden element
 * @param offenderNameElt offender name input element
 * @param offenderUnitSpanElt offender unit span element
 * @param offenderUnitLabelElt label element to display unit
 * @param offenderUnitGroupElt element grouping offender unit elements
 */
function applyOffenderAutocompleteWithUnit(offenderElt, offenderNameElt, offenderUnitSpanElt, offenderUnitLabelElt, offenderUnitGroupElt) {

	//Display unit when offender input element is changed
	function onchangeOffender(offenderElt, offenderUnitSpanElt, offenderUnitLabelElt) {
		
		// Hides unit span
		function hideUnitSpan() {
			var offenderUnitSpanClass = offenderUnitSpanElt.getAttribute("class");
			if (offenderUnitSpanClass.indexOf("hidden") == -1) {
				offenderUnitSpanElt.setAttribute("class", offenderUnitSpanClass + "hidden");
			}
		}
		
		// Shows unit span
		function showUnitSpan() {
			var offenderUnitSpanClass = offenderUnitSpanElt.getAttribute("class");
			if (offenderUnitSpanClass.indexOf("hidden") != -1) {
				offenderUnitSpanElt.setAttribute("class", offenderUnitSpanClass.replace("hidden", ""));
			}
		}
		
		offenderValue = offenderElt.getAttribute("value");
		if (offenderValue != null && offenderValue.length > 0) {
			var url = config.ServerConfig.getContextPath() + "/health/unit/findByOffender.json?offender=" + offenderValue;
			ajax(url, offenderUnitLabelElt, function(result) {
				var unitAbbreviation = eval("(" + result + ")").unitAbbreviation;
				if (unitAbbreviation != null && unitAbbreviation.length > 0) {
					offenderUnitLabelElt.setAttribute("value", unitAbbreviation);
					showUnitSpan();	
				} else {
					hideUnitSpan();
				}
			});
		} else {
			hideUnitSpan();
		}
	}
	applySearch(offenderNameElt, offenderElt, ui.search.Autocomplete.OFFENDER_SEARCH, function() {
		onchangeOffender(offenderElt, offenderUnitGroupElt, offenderUnitLabelElt);
	});
}