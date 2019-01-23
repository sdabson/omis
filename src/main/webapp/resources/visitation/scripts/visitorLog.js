/**
 * visitorLog.js
 * 
 * author: Joel Norris
 * version: 0.1.0 (October 21, 2013)
 * since: OMIS 3.0
 */

/*
 * Assign on click functionality for visitor log table elements
 */
function assignVisitorLogOnClick() {
	$(".visitorLogSummary").each(function() {
		var element = $(this);
		var id = element.attr("id");
		var visit = id.replace("visitorLogSummary", "");
		var endVisitLink = $("#" + id + "EndVisitLink");
		var removeVisitLink = $("#" + id + "RemoveVisitLink");
		removeVisitLink.click(function(){
			removeVisitFromOffenderVisitorLog(visit);
			return false;
		});
		endVisitLink.click(function(){
			endVisitorLogVisit(visit);
			return false;
		});
	});
}

/*
 * Assign on click functionality for visitor log elements.
 */
function oneTimeAssignmentFunctions() {
	$("#changeDates").click(function() {
		var form = $(this);
		refreshVisitorLog(form);
		return false;
	});
	$(".chooseDate").attr("autocomplete", "off");
	$(".chooseDate").datepicker({
		changeMonth: true,
		changeYear: true
	});
	
	$(".singleDate").click(function() {
		var singleDate = $('.singleDate[name="singleDate"]:checked').val();
		$("#dateFieldArea").hide(200);
		$.ajax({
			type: "GET",
			async: false,
			data: {singleDate: singleDate},
			url: config.ServerConfig.getContextPath() + "/visitation/dateFieldArea.html",
			beforeSend: function() {
				$("#dateFieldArea").html("<img src='"
					+ config.ServerConfig.getContextPath()
					+ "/resources/common/images/ajaxLoader.gif'/>");
			},
			success: function(data){
				$("#dateFieldArea").html(data);
				$(".chooseDate").attr("autocomplete", "off");
				$(".chooseDate").datepicker({
					changeMonth: true,
					changeYear: true
				});
			},
			error: function(jqXHR, textStatus, errorThrown) {
				alert("Error - status: " +  textStatus + "; error: "
					+ errorThrown);
				$("#dateFieldArea").html(jqXHR.responseText);
			}
		});
		$("#dateFieldArea").show(200);
	});
	
}

/*
 * Function to run upon clicking the "End Visit" option on a record in the
 * visitation log table. 
 */
function endVisitorLogVisit(visit){
	$.ajax({
		type: "GET",
		async: false,
		url: config.ServerConfig.getContextPath() + "/visitation/endVisitorLogVisit.html",
		data: {firstDate: $("#firstDate").val(), secondDate: $("#secondDate").val(), 
			singleDate: $('.singleDate[name="singleDate"]:checked').val(), visit: visit,
			offender: $("#offender").val(), facility: $("#facility").val()
			},
		beforeSend: function() {
			$("#visitLogSummaries").html("<img src='"
				+ config.ServerConfig.getContextPath()
				+ "/resources/common/images/ajaxLoader.gif'/>");
		},
		success: function(data) {
			$("#visitLogSummaries").html(data);
			assignVisitorLogOnClick();
		},
		error: function(jqXHR, textStatus, errorThrown) {
			alert("Error - status: " + textStatus + "; error: "
				+ errorThrown);
			$("#visitLogSummaries").html(jqXHR.responseText );
		}
	});
}

/*
 * Function to run when removing a visit from the visitation log.
 */
function removeVisitFromOffenderVisitorLog(visit) {
	$.ajax({
		type: "GET",
		async: false,
		url: config.ServerConfig.getContextPath() + "/visitation/removeVisit.html",
		data: {firstDate: $("#firstDate").val(), secondDate: $("#secondDate").val(), 
			singleDate: $('.singleDate[name="singleDate"]:checked').val(), visit: visit,
			offender: $("#offender").val(), facility: $("#facility").val()
			},
		beforeSend: function() {
			$("#visitLogSummaries").html("<img src='"
				+ config.ServerConfig.getContextPath()
				+ "/resources/common/images/ajaxLoader.gif'/>");
		},
		success: function(response) {
			$("#visitLogSummaries").html(response);
			assignVisitorLogOnClick();
		},
		error: function(jqXHR, textStatus, errorThrown) {
			alert("Error - status: " + textStatus + "; error: "
				+ errorThrown);
			$("#visitLogSummaries").html(jqXHR.responseText );
		}
	});
}

/*
 * Function to run when submitting the change visitation log dates form.
 */
function refreshVisitorLog(form) {
	$.ajax({
		type: "GET",
		async: false,
		url: config.ServerConfig.getContextPath() + "/visitation/refreshLog.html",
		data: {firstDate: $("#firstDate").val(), secondDate: $("#secondDate").val(), 
			singleDate: $('.singleDate[name="singleDate"]:checked').val(),
			offender: $("#offender").val(), facility: $("#facility").val()
			},
		beforeSend: function() {
			$("#visitLogSummaries").html("<img src='"
				+ config.ServerConfig.getContextPath()
				+ "/resources/common/images/ajaxLoader.gif'/>");
		},
		success: function(response) {
			$("#visitLogSummaries").html(response);
			assignVisitorLogOnClick();
		},
		error: function(jqXHR, textStatus, errorThrown) {
			alert("Error - status: " + textStatus + "; error: "
				+ errorThrown);
			$("#visitLogSummaries").html(jqXHR.responseText );
		}
	});
}

$(document).ready(function() {
	assignVisitorLogOnClick();
	oneTimeAssignmentFunctions();
});