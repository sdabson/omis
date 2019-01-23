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
 * Behavior for changing supervisory organizations.
 *
 * Author: Stephen Abson
 * Version: 0.1.0 (Oct 18, 2013)
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
	
	// Changes supervisory organization
	function changeSupervisoryOrganization() {
		var supervisoryOrganization = $("#supervisoryOrganization").val();
		var effectiveDate = $("#effectiveDate").val();
		var defaultStartChangeReason = $("#startChangeReason").val();
		var defaultEndChangeReason = $("#endChangeReason").val();
		if (supervisoryOrganization != null && supervisoryOrganization != '') {
			findAllowedStartChangeReasons(offender, supervisoryOrganization, effectiveDate, defaultStartChangeReason);
			findAllowedEndChangeReasons(offender, supervisoryOrganization, effectiveDate, defaultEndChangeReason);
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
		var supervisoryOrganization = $("#supervisoryOrganization").val();
		var effectiveDate = $("#effectiveDate").val();
		$.ajax(config.ServerConfig.getContextPath() + "/supervision/placementTerm/findAllowedSupervisoryOrganizations.html",
			{
				async: false,
				data: { offender: offender, date: effectiveDate, defaultSupervisoryOrganization: supervisoryOrganization },
				success: function(data) {
					$("#supervisoryOrganization").html(data);
					$("#supervisoryOrganization").focus();
					changeSupervisoryOrganization();
				},
				type: "GET"
			}	
		);
	});
	$("#supervisoryOrganization").change(function() {
		changeSupervisoryOrganization();
	});
});

/**
 * Populates start change reasons.
 * 
 * @param offender offender
 * @param supervisoryOrganization supervisory organization
 * @param effectiveDate effective date
 * @param defaultStartChangeReason default change reason
 */
function findAllowedStartChangeReasons(offender, supervisoryOrganization, effectiveDate, defaultStartChangeReason) {
	findAllowedChangeReasons("/supervision/placementTerm/findAllowedStartChangeReasonsForSupervisoryOrganization.html",
			offender, supervisoryOrganization, effectiveDate, defaultStartChangeReason, "startChangeReason");
}

/**
 * Populates end change reasons.
 * 
 * @param offender offender
 * @param supervisoryOrganization supervisory organization
 * @param effectiveDate effective date
 * @param defaultEndChangeReason default end change reason
 */
function findAllowedEndChangeReasons(offender, supervisoryOrganization, effectiveDate, defaultEndChangeReason) {
	findAllowedChangeReasons("/supervision/placementTerm/findAllowedEndChangeReasonsForSupervisoryOrganization.html",
			offender, supervisoryOrganization, effectiveDate, defaultEndChangeReason, "endChangeReason");
}

/**
 * Populates change reason drop down with allowed change reasons.
 * 
 * @param url URL to invoke for change reasons
 * @param offender offender
 * @param supervisoryOrganization supervisory organization
 * @param effectiveDate effective date
 * @param defaultChangeReason default change reason
 * @param changeReasonsId ID of change reasons drop down element
 */
function findAllowedChangeReasons(url, offender, supervisoryOrganization,
		effectiveDate, defaultChangeReason, changeReasonsId) {
	$.ajax(config.ServerConfig.getContextPath() + url,
		{
			async: false,
			data: {
				offender: offender,
				supervisoryOrganization: supervisoryOrganization,
				date: effectiveDate,
				defaultChangeReason: defaultChangeReason
			},
			success: function(data) {
				elt = $("#" + changeReasonsId);
				elt.html(data);
				elt.removeAttr("disabled");
			},
			type: "GET"
		}
	);
}