/*
 * OMIS - Offender Management Information System
 * Copyright (C) 2011 - 2017 State of Montana
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

/**
 * Behavior for changing correctional statuses.
 *
 * Author: Stephen Abson
 * Version: 0.1.0 (Oct 24, 2013)
 * Since: OMIS 3.0
 */
$(document).ready(function() {
	
	// Common messages resolver
	var commonResolver = new common.MessageResolver("omis.msgs.common");
	
	// Empties and disables select drop down
	function emptyAndDisableSelect(selectId) {
		var elt = $("#" + selectId);
		elt.html('<option value="">' + commonResolver.getMessage("nullLabel") + '</option>');
		elt.attr("disabled", "disabled");
	}
	
	// Changes correctional status
	function changeCorrectionalStatus() {
		var correctionalStatus = $("#correctionalStatus").val();
		var effectiveDate = $("#effectiveDate").val();
		var defaultStartChangeReason = $("#startChangeReason").val();
		var defaultEndChangeReason = $("#endChangeReason").val();
		if (correctionalStatus != null && correctionalStatus != '') {
			findAllowedStartChangeReasons(offender, correctionalStatus, effectiveDate, defaultStartChangeReason);
			findAllowedEndChangeReasons(offender, correctionalStatus, effectiveDate, defaultEndChangeReason);
		} else {
			emptyAndDisableSelect("startChangeReason");
			emptyAndDisableSelect("endChangeReason");
		}
	}
	
	$("#effectiveDate").attr("autocomplete", "off");
	$("#effectiveDate").datepicker({
		changeMonth: true,
		changeYear: true
	});
	$("#effectiveDate").change(function() {
		var correctionalStatus = $("#correctionalStatus").val();
		var effectiveDate = $("#effectiveDate").val();
		$.ajax(config.ServerConfig.getContextPath() + "/supervision/placementTerm/findAllowedCorrectionalStatuses.html",
			{
				async: false,
				data: { offender: offender, date: effectiveDate, defaultCorrectionalStatus: correctionalStatus },
				success: function(data) {
					$("#correctionalStatus").html(data);
					$("#correctionalStatus").focus();
					changeCorrectionalStatus();
				},
				type: "GET"
			}
		);
		$("correctionalStatus").focus();
	});
	$("#correctionalStatus").change(function() {
		changeCorrectionalStatus();
	});
}); 

/**
 * Populates start change reasons.
 * 
 * @param offender offender
 * @param correctionalStatus correctional status
 * @param effectiveDate effective date
 * @param defaultStartChangeReason default change reason
 */
function findAllowedStartChangeReasons(offender, correctionalStatus, effectiveDate, defaultStartChangeReason) {
	findAllowedChangeReasons("/supervision/placementTerm/findAllowedStartChangeReasonsForCorrectionalStatus.html",
			offender, correctionalStatus, effectiveDate, defaultStartChangeReason, "startChangeReason");
}

/**
 * Populate end change reasons.
 * 
 * @param offender offender
 * @param correctionalStatus correctional status
 * @param effectiveDate effective date
 * @param defaultEndChangeReason default change reason
 */
function findAllowedEndChangeReasons(offender, correctionalStatus, effectiveDate, defaultEndChangeReason) {
	findAllowedChangeReasons("/supervision/placementTerm/findAllowedEndChangeReasonsForCorrectionalStatus.html",
			offender, correctionalStatus, effectiveDate, defaultEndChangeReason, "endChangeReason");
}

/**
 * Populates change reason drop down with allowed change reasons.
 * 
 * @param url URL to invoke for change reasons
 * @param offender offender
 * @param correctionalStatus correctional status
 * @param effectiveDate effective date
 * @param defaultChangeReason default change reason
 * @param changeReasonId ID of change reason drop down element
 */
function findAllowedChangeReasons(url, offender, correctionalStatus,
		effectiveDate, defaultChangeReason, changeReasonId) {
	$.ajax(config.ServerConfig.getContextPath() + url,
		{
			async: false,
			data: {
				offender: offender,
				correctionalStatus: correctionalStatus,
				date: effectiveDate,
				defaultChangeReason: defaultChangeReason
			},
			success: function(data) {
				elt = $("#" + changeReasonId);
				elt.html(data);
				elt.removeAttr("disabled");
			},
			type: "GET"
		}
	);
}