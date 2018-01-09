function assignCityOnClick() {
var state = $("#state");
var city = $("#city");
	state.change(function() {
		$.ajax({			
			type: "GET",
			async: false,
			url: "showSearchCityOptions.html",
			data: {state: state.val()},
			success: function(response) {
				$(city).html(response);
			},
			error: function(jqXHR, textStatus, errorThrown) {
				alert("Error - status: " + textStatus + "; error: "
						+ errorThrown);
				$(city).html(jqXHR.responseText );
			}
		});
})};