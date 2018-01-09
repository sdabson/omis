/** Schedule Provider JQuery implementation functions.
 * @author: Ryan Johns
 * @version: 0.1.0 (Jun 05, 2014)
 * @since OMIS 3 */

function applyAddCreateIrregularScheduleDay(createIrregularScheduleLink) {
	$(createIrregularScheduleLink).click(function() {
		var index = document.getElementsByClassName("irregularScheduleDayListItem").length;
		var url = $(createIrregularScheduleLink).attr('href') + "&index="+index;
		$.ajax({
			type: "GET",
			async: false,
			url: url,
			 success: function(data) {
				 $("#irregularScheduleDayList > tbody").append(data);
				 
				applyTimePickers("time");
				applyDatePickers("date");
				applyRemoveBehavior("removeLink");
			 },
			error: function(jqXHR, textStatus, errorThrown) {
			 alert("Error - status: " + textStatus + "; error: " + errorThrown);
			 $(document).html(jqXHR.responseText );
		}});
		
		return false;
	});
}

function applyAddCreateIrregularScheduleDays(createIrregularScheduleLink) {
	$(createIrregularScheduleLink).click(function() {
		var index = document.getElementsByClassName("irregularScheduleDayListItem").length;
		var startDay = document.getElementById("multipleIrregularScheduleStart").value;
		var endDay = document.getElementById("multipleIrregularScheduleEnd").value;
		var msg = new common.MessageResolver("omis.health.msgs.form");
		var url = $(createIrregularScheduleLink).attr('href') + "&index="+index;
		if ((startDay && !/^\s*$/.test(startDay)) && (endDay && !/^\s*$/.test(endDay))) {
			url += "&startDate="+startDay+"&endDate="+endDay;
		
		if(dayDiff(new Date(startDay), new Date(endDay)) <=14) {
			
		$.ajax({
			type: "GET",
			async: false,
			url: url,
			 success: function(data) {
				 $("#irregularScheduleDayList > tbody").append(data);
				 
				applyTimePickers("time");
				applyDatePickers("date");
				applyRemoveBehavior("removeLink");
			 },
			error: function(jqXHR, textStatus, errorThrown) {
			 alert("Error - status: " + textStatus + "; error: " + errorThrown);
			 $(document).html(jqXHR.responseText );
		}});
		} else {
			alert(msg.getMessage("providerIrregularSchedule.dateRange.numberOfDaysExceeded"));
		}
		} else {
			alert(msg.getMessage("providerIrregularSchedule.dateRange.empty"));
		}
		return false;
	});
}

function dayDiff(start, end) {
    return (end-start)/(1000*60*60*24);
}
 
function applyTimePickers(className) {
	$("."+className).each(function() {
		$(this).ptTimeSelect();
	});
}

function applyDatePickers(className) {
	$("."+className).each(function() {
		$(this).datepicker($.extend({
			changeMonth:true,
			changeYear: true
		}));
	});
}

function applyRemoveBehavior(className) {
	$("."+className).each(function() {
		var element = $(this);
		var id = element.attr("id");
		
		$(this).off("click");
		element.on('click', function(e) {
			 var msg = new common.MessageResolver("omis.health.msgs.health");
			if (confirm(msg.getMessage("confirmRemoveSchedule"))) {
			if ($("#"+id+"_irregularScheduleDaysItem").hasClass("new")) {
				$("#"+id+"_irregularScheduleDaysItem").closest("tr").remove();
			} else {
				$("#"+id).closest("tr").hide();
				element.remove();
				$("#"+id+"_day").remove();
				$("#"+id+"_startTime").remove();
				$("#"+id+"_endTime").remove();
			}
		}
	});});
}

function applyRefreshBehavior(id, url) {
	$("#"+id).on("click", function() {
		var startDate = $("#dateRangeStart").val();
		var endDate = $("#dateRangeEnd").val();
		var isStartDateEmpty = (!startDate || 0 === startDate.length); 
		var isEndDateEmpty = (!endDate || 0 === endDate.length);
	
		if (new Date(startDate) > new Date(endDate)) {
			var msg = new common.MessageResolver("omis.msgs.form");
			alert(msg.getMessage("dateRange.startDateGreaterThanEndDate"));
		} else {
			if  (!(isStartDateEmpty) && !(isEndDateEmpty)) {
				window.location.href = url+"&startDate="+startDate+"&endDate="+endDate;
			} else if(!isStartDateEmpty && isEndDateEmpty) {
				window.location.href = url +"&startDate="+startDate;
			} else {
				var msg = new common.MessageResolver("omis.health.msgs.health");
				alert(msg.getMessage("refreshStartDateRequired"));
			}
		}
	});	
}
