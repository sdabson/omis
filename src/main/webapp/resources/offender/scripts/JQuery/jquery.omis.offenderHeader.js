function applyShowProfile(profileId, navContentTarget){
	var offenderId = $("#headerOffenderId").val();
	if(!$(navContentTarget).html().trim()){
		$.ajax(config.ServerConfig.getContextPath() + "/offender/" + profileId + ".html",
		{
				type: "GET",
				async: false,
				data: {
					offender: offenderId
					},
				success: function(data) {
					$(navContentTarget).append(data);
					assignNewTabLinks();
				},
				error: function(jqXHR, textStatus, errorThrown) {
					alert("Error - status: " + textStatus + "; error: "
						+ errorThrown);
					$(navContentTarget).html(jqXHR.responseText );
				}
		});
	}
}