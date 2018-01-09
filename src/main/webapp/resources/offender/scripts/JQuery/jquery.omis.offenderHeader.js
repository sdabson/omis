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
					
					if($(navContentTarget).find(".profileActionMenuItem")[0]){
						var actionMenu = $(navContentTarget).find(".profileActionMenuItem")[0];
						applyActionMenu(actionMenu);
					}
				},
				error: function(jqXHR, textStatus, errorThrown) {
					alert("Error - status: " + textStatus + "; error: "
						+ errorThrown);
					$(navContentTarget).html(jqXHR.responseText );
				}
		});
	}
}