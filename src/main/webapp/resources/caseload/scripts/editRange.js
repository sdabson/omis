$(document).ready(function($) {
	$("#dateRangeRefresh").on("click", function() {
		var url = 'edit.html?caseLoad='+caseLoadId;
		
		if ($("#dateRangeStart").val()) { 
			url +='&dateRangeStart='+$("#dateRangeStart").val();
		
			if ($("#dateRangeEnd").val()) {
				url += '&dateRangeEnd='+$("#dateRangeEnd").val(); 
			}
		}
		$.ajax({
			type:"GET",
			dataType:"html",
			url: url,
			success: function(html) {
				$("body").html(html);
			},
			error: function(xhr, ajaxOptions, thrownError) {
				alert(xhr.status);
				alert(thrownError);
			}});
	});
	
	$(".dateRange .date").each(function() {
		var datePicker = $(this);
		if (!datePicker.hasClass("hasDatePicker")) {
			datePicker.datepicker();
		}
	});
	
	
});

function appendDateRange() {
	var url = '';
	if (document.getElementById("dateRangeStart").value) { 
		url +='&dateRangeStart='+document.getElementById("dateRangeStart").value;
		if (document.getElementById("dateRangeEnd").value) {
			url +='&dateRangeEnd='+document.getElementById("dateRangeEnd").value;
		}
	} 
	return url;
}