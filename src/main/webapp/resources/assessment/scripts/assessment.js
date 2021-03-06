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
 * Assessments detail screen behavior.
 * 
 * Author: Josh Divine
 * Author: Annie Wahl
 * Version: 0.1.1 Nov 20, 2018
 * Since: OMIS 3.0
 */
window.onload = function() {	
	applyActionMenu(document.getElementById("actionMenuLink"));
	applyFormUpdateChecker(document.getElementById("assessmentForm"));
	applyDatePicker(document.getElementById("assessDate"));
	applyTimePicker(document.getElementById("assessTime"));
	applyPersonSearch(document.getElementById("assessorInput"), 
			document.getElementById("assessor"),
			document.getElementById("assessorDisplay"),
			document.getElementById("assessorClear"));
};