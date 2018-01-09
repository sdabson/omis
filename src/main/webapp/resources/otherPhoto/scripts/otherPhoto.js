function otherPhotoOnClick() {
	var $radioButtons = $('.physicalFeatureAssociation');
	$(".physicalFeature").each(function() {
		if ($(this).find(".physicalFeatureAssociation").is(':checked')) {
			$(this).closest(".physicalFeature").addClass("associate");
		}
	});
	$radioButtons.click(function() {
		$radioButtons.each(function() {
	        $(this).closest(".physicalFeature").toggleClass('associate', this.checked);
	    });
	});
	$(".orphanedPhoto").each(function() {
		var operations = jQuery(this).find(".operations");
		var numberId = operations.attr("id").replace("operations", "");
		var processStatus = $("#processStatus" + numberId).val().toLowerCase();
		$(this).addClass(processStatus);
		jQuery(operations).find(".operationButton").each(function() {
			var buttonOperation = $(this).attr("id").replace(numberId, "");
			if (buttonOperation == "associate") {
				$("#associate" + numberId).click(function() {
					if ($("#associate" + numberId).attr("alt") == "off") {
						this.src = config.ServerConfig.getContextPath() + "/resources/otherPhoto/images/acceptPressed.png";
						$("#processStatus" + numberId).val("ASSOCIATE");
						$("#associate" + numberId).attr("alt", "on");
						$("#remove" + numberId).attr("src", config.ServerConfig.getContextPath() + "/resources/otherPhoto/images/delete.png");
						$("#remove" + numberId).attr("alt", "off");
						$(this).closest(".orphanedPhoto").removeClass("remove");
						$(this).closest(".orphanedPhoto").addClass("associate");
					} else {
						this.src = config.ServerConfig.getContextPath() + "/resources/otherPhoto/images/accept.png";
						$("#processStatus" + numberId).val("");
						$("#associate" + numberId).attr("alt", "off");
						$(this).closest(".orphanedPhoto").removeClass("associate");
					}
					return false;
				});
			} else {
				$("#remove" + numberId).click(function() {
					if ($("#remove" + numberId).attr("alt") == "off") {
						this.src = config.ServerConfig.getContextPath() + "/resources/otherPhoto/images/deletePressed.png";
						$("#processStatus" + numberId).val("REMOVE");
						$("#remove" + numberId).attr("alt", "on");
						$("#associate" + numberId).attr("src", config.ServerConfig.getContextPath() + "/resources/otherPhoto/images/accept.png");
						$("#associate" + numberId).attr("alt", "off");
						$(this).closest(".orphanedPhoto").removeClass("associate");
						$(this).closest(".orphanedPhoto").addClass("remove");
					} else {
						this.src = config.ServerConfig.getContextPath() + "/resources/otherPhoto/images/delete.png";
						$("#processStatus" + numberId).val("");
						$("#remove" + numberId).attr("alt", "off");
						$(this).closest(".orphanedPhoto").removeClass("remove");
					}
					return false;
				});
			}
		});
	});
}

/*
 * Function to run when the screen is first loaded.
 */
$(document).ready(function() {
	otherPhotoOnClick();
	applyFormUpdateChecker(document.getElementById("otherPhotosForm"));
	applyActionMenu(document.getElementById("actionMenuLink"));
});