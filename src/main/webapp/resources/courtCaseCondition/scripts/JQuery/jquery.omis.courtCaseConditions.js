function applyCourtCaseConditionsActionMenuOnClick(event, targetElement) {
	applyRemoveLinkConfirmation();
	var viewConditions = $(targetElement).find(".viewConditionLink")[0];
	$(viewConditions).click(function() {
		var courtCaseAgreementId = this.getElementsByTagName('input')[0].value;
		if(!$("#conditions" + courtCaseAgreementId).html().trim()){
			$.ajax(config.ServerConfig.getContextPath() + "/courtCaseCondition/showConditions.html",
			{
					type: "GET",
					async: false,
					data: {
						courtCaseAgreement: courtCaseAgreementId
						},
					success: function(data) {
						$("#conditions" + courtCaseAgreementId).append(data);
						var rows = $(".rowActionMenuItem" + courtCaseAgreementId);
						for(var i = 0, row; row = rows[i]; i++) {
							applyActionMenu(row);
						}
					},
					error: function(jqXHR, textStatus, errorThrown) {
						alert("Error - status: " + textStatus + "; error: "
							+ errorThrown);
						$("#conditions" + courtCaseAgreementId).html(jqXHR.responseText );
					}
			});
		}
		else{
			$("#conditions" + courtCaseAgreementId).html('')
		}
		return false;
	});
}

function assignDatePicker(elementId) {
	if ( $("#" + elementId).prop('type') != 'date' ) {
		$("#" + elementId).datepicker({
			changeMonth: true,
			changeYear: true
		});
	}
};

