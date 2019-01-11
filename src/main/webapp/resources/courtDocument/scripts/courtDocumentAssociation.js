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
 * Court document association detail screen behavior.
 * 
 * Author: Ryan Johns
 * Author: Josh Divine
 * Version: 0.1.1 (Aug 9, 2018)
 * Since: OMIS 3.0
 */
window.onload = function() {	
	applyActionMenu(document.getElementById("actionMenuLink"));
	applyDatePicker(document.getElementById("date"));
	if (document.getElementById("documentData")) {
		applyFileExtensionNamer();
	}
	applyFormBehavior();
	applyRemoveTagLinks();
	applyAddTagLink();
	applyDownloadLink();
	
	
	function applyFileExtensionNamer() {
		var documentData = document.getElementById("documentData");
		var fileExtension = document.getElementById("dataFileExtension");
		documentData.onchange = function() {
			var fileExtensionValue = documentData.value.substr(documentData.value.lastIndexOf('.')+1);
			fileExtension.value = fileExtensionValue;
		}
	}
	function applyFormBehavior() {
		var form = document.getElementById("form");
		form.onsubmit = function() {
			reindexDocumentTags();
		};
		applyFormUpdateChecker(form);
	}
	
	function applyDownloadLink() {
		var link = document.getElementById("courtDocumentDownloadLink");
		link.onclick = function(event) {
			var iframe = document.getElementById("downloadFrame");
			if (iframe == null) {
				iframe = document.createElement("iframe");
				iframe.id="downloadFrame";
				iframe.className += " hide";
				document.getElementsByTagName("body")[0].appendChild(iframe);
			}
			iframe.src = event.target.href;
			return false;
		};
	}
}