/*
 * Author: Ryan Johns
 * Version: 0.1.0 (Feb 11, 2014)
 * Since: OMIS 3.0 */
eventRunner.addEvent(function() {
	alert("offenderAssignments");
var caseOffenders = document.getElementById("caseOffenders");
var dialogElement = document.getElementById("caseLoadDialog");
function ajaxcaseOffenderLink() {
	 scrollTable(document.getElementById("caseOffenderWorker"),
			 document.getElementById("caseOffenderAssignmentFilter"));
	 var links = [].splice.call(caseOffenders.getElementsByClassName("createLink"));
	 links = links.concat([].splice.call(caseOffenders.getElementsByClassName("viewEditLink")));
	 
	 for (var x = 0; x < links.length; x++) {
		 ajaxLink(links[x], dialogElement,
		 function(html) {
			 dialogElement.innerHTML=html;
			 dialog(dialogElement);
			 ajaxcaseOffenderForm();
		 });}
	
	links = [].slice.call(caseOffenders.getElementsByClassName("removeLink"));
	for (var x = 0; x < links.length; x++) {
		ajaxLink(links[x], caseOffenders,
			function(html) {
				caseOffenders.innerHTML=html;
				ajaxcaseOffenderLink();
		},{precondition: function() {
			 confirm(new commonMessageResolver("omis.msgs.common").getMessage("removeConfirmMessage"));
}});}}
 
alert("ajaxcaseOffenderLink");
function ajaxcaseOffenderForm() {
	ajaxSubmit(document.getElementById("caseLoadOffenderAssignment"),
			caseOffenders,
			function() {
				ajaxcaseOffenderLink();
			},
			function() {
				ajaxcaseOffenderForm();
				caseLoadOffenderDatePickers();
			});
	
	caseLoadOffenderDatePickers();
	applySearch(document.getElementById("firstLastName"), document.getElementById("caseOffender"), ui.search.Autocomplete.OFFENDER_SEARCH);
}

alert("ajaxCaseOffenderForm");
if (window.caseLoadId) {
	var urlOffenders = config.ServerConfig.getContextPath() + '/caseLoad/caseLoadOffenderAssignment/list.html?caseLoad='+caseLoadId;
	ajax(urlOffenders + appendDateRange(),caseOffenders,
		function(html) {
			caseOffenders.innerHTML = html;
			ajaxcaseOffenderLink();
});}
 
function caseLoadOffenderDatePickers() {
	var caseOffenderForm = document.getElementsByClassName("editCaseOffenderAssignmentForm")[0];
	var elements = caseOffenderForm.getElementsByClassName("date");
	for(var x = 0; x < elements.length; x++) {
		var thisPicker = elements[x];
		if (!thisPicker.className.match(/b\hasDatePicker\b/)) {
			applyDatePicker(thisPicker);
}}}

alert("offenderAssignmentsEnd");

});