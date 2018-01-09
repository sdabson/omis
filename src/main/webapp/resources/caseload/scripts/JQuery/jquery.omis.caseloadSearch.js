/**
 * @author Sheronda Vaughn
 * @version 0.1.0 (Nov 7, 2016)
 * @since OMIS 3.0
 */

/** Caseload autocomplete search.
 * @param input input control.
 * @param target target element.
 * @param options options. 
 */
ui.search.Autocomplete.CASELOAD_SEARCH = function(input, target, clear, targetLabel, options) {	
	if (!$(input).hasClass("lookup")) {
		var settings = options;
		settings = $.extend({ 
			onSelect: function(event, ui) {
				$(target).val(ui.item.value);
				displaySelection(ui.item.label, input, targetLabel);
				return false;
			}}, options);
		
		$(input).autocomplete({
			autoFocus: true,
			minLength: 4,
			source: function(request, response) {
			$.ajax({
				url: config.ServerConfig.getContextPath() + "/caseload/reassignOffender.json?caseloadSearchQuery="+request.term,
				dataType: "json",
				cache:false,
				success: function(data) {
					 response($.map( data, function( item ) {
							 return {
								 label: item.caseloadName + " - " + item.caseWorkerLastName + ", " + item.caseWorkerFirstName,
									value: item.caseloadId
								 	};}));}});}, select: settings.onSelect });
		$(input).addClass("lookup");
		
		if (typeof clear != 'undefined') {
			$(clear).on("click", function() {
				clearFields($(target), $(input), $(targetLabel));
				return false;
		});}
		return this;
}};