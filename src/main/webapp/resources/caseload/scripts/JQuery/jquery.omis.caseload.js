$(document).ready(function() {
	$("#dateRangeStart.date, #dateRangeEnd.date").datepicker();
	
function refreshOnClick(personID) {
	alert("here js");
	$("#dateRangeRefresh").click(function() {
		var url = config.ServerConfig.getContextPath()+'/caseload/refreshList.html?searchCriteria='+personId;
		if ($(dateRangeStart).val()) { 			
			url +='&dateRangeStart='+$(dateRangeStart).val();
			

			if ($(dateRangeEnd).val()) {
				url += '&dateRangeEnd='+$(dateRangeEnd).val(); 
			}
		}
		$.ajax({
			type:'GET',
			dataType:"html",
			url: url,
			success: function(html) {	
				$("#caseloadListTableBody>tbody:last").html(html);
			},
			error: function(xhr, ajaxOptions, thrownError) {
				alert(xhr.status);
				alert(thrownError);
			}});
	})};
});