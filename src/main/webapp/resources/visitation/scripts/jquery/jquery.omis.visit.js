function assignVisitFormOnClick() {
	$("#date").attr("autocomplete", "off");
	$("#date").datepicker({
		changeMonth: true,
		changeYear: true
	});
	$("#startTime").attr("autocomplete", "off");
	$("#startTime").ptTimeSelect();
	$("#endTime").attr("autocomplete", "off");
	$("#endTime").ptTimeSelect();
	$("#date").change(function () {
		$.ajax({
			type: "GET",	
			async: false,
			url: "visitationAssociationSelect.html",
			data: {offender: offenderId,
				date: $("#date").val(),
				visitationAssociation: $("#visitationAssociation").val()},
			success: function(response) {
				$("#visitationAssociation").html(response);
			},
			error: function(jqXHR, textStatus, errorThrown) {
				alert("Error - status: " + textStatus + "; error: "
						+ errorThrown);
				$("#visitationAssociation").html(jqXHR.responseText );
			}
		});
	});
}