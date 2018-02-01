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
 * Court cases list behavior.
 * 
 * @author Joel Norris
 * @author Josh Divine
 * @version 0.1.1 (Jan 31, 2017)
 * @since OMIS 3.0
 */

/** Assign initial element behavior. */
$(document).ready(function() {
	applyActionMenu(document.getElementById("actionMenuLink"));
	
	var courtCasesTableBody = document.getElementById("courtCases");
	var rowLinks = courtCasesTableBody.getElementsByTagName("a");
	for(var x = 0; x < rowLinks.length; x++) {
		var rowLink = rowLinks[x];
		if (rowLink.getAttribute("class") != null && rowLink.getAttribute("class").indexOf("actionMenuItem") > -1) {
			applyActionMenu(rowLink, function() {
				var dismissDocket = document.getElementsByClassName("dismissDocketLink");
				for (var y = 0; y < dismissDocket.length; y++) {
					dismissDocket[y].onclick = function() {
						var resolver = new common.MessageResolver("omis.courtcase.msgs.courtCase");
						var message = resolver.getMessage("confirmDocketDismiss", null);
						if (confirm(message)) {
							return true;
						} else {
							return false;
						}
					};
				}
			});
		}
	}
});
