function applyParoleBoardConditionsActionMenuOnClick(event, targetElement) {
	applyRemoveLinkConfirmation();
	var viewConditions = $(targetElement).find(".viewConditionLink")[0];
	$(viewConditions).click(function() {
		var paroleBoardAgreementId = this.getElementsByTagName('input')[0].value;
		if(!$("#conditions" + paroleBoardAgreementId).html().trim()){
			$.ajax(config.ServerConfig.getContextPath() + "/paroleBoardCondition/showConditions.html",
			{
					type: "GET",
					async: false,
					data: {
						paroleBoardAgreement: paroleBoardAgreementId
						},
					success: function(data) {
						$("#conditions" + paroleBoardAgreementId).append(data);
						var rows = $(".rowActionMenuItem" + paroleBoardAgreementId);
						for(var i = 0, row; row = rows[i]; i++) {
							applyActionMenu(row);
						}
					},
					error: function(jqXHR, textStatus, errorThrown) {
						alert("Error - status: " + textStatus + "; error: "
							+ errorThrown);
						$("#conditions" + paroleBoardAgreementId).html(jqXHR.responseText );
					}
			});
		}
		else{
			$("#conditions" + paroleBoardAgreementId).html('')
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

