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
window.onload = function() {
	applyActionMenu(document.getElementById("actionMenuLink"));
	applyActionMenu(document.getElementById("earlyReleaseRequestNoteAssociationItemsActionMenuLink"), earlyReleaseRequestNoteAssociationItemsCreateOnClick);
	applyActionMenu(document.getElementById("earlyReleaseRequestInternalApprovalItemsActionMenuLink"), earlyReleaseRequestInternalApprovalItemsCreateOnClick);
	applyActionMenu(document.getElementById("earlyReleaseRequestExternalOppositionItemsActionMenuLink"), earlyReleaseRequestExternalOppositionItemsCreateOnClick);
	applyDatePicker(document.getElementById("requestDate"));
	applyDatePicker(document.getElementById("approvalDate"));
	applySearchUserAccountsAutocomplete(
			document.getElementById("requestByInput"),
			document.getElementById("requestByDisplay"),
			document.getElementById("requestBy"),
			document.getElementById("clearRequestBy"),
			document.getElementById("currentRequestBy"));
	
	earlyReleaseRequestDocumentAssociationItemsCreateOnClick();
	for (var index = 0; index < currentEarlyReleaseRequestNoteAssociationItemIndex; index++) {
		earlyReleaseRequestNoteAssociationItemRowOnClick(index);
	}
	for (var index = 0; index < currentEarlyReleaseRequestDocumentAssociationItemIndex; index++) {
		earlyReleaseRequestDocumentAssociationItemRowOnClick(index);
		for(var j = 0; j < currentDocumentTagItemIndexes[index]; j++){
			documentTagItemRowOnClick(index,j);
		}
	}
	for (var index = 0; index < currentEarlyReleaseRequestInternalApprovalItemIndex; index++) {
		earlyReleaseRequestInternalApprovalItemRowOnClick(index);
	}
	for (var index = 0; index < currentEarlyReleaseRequestExternalOppositionItemIndex; index++) {
		earlyReleaseRequestExternalOppositionItemRowOnClick(index);
	}
}