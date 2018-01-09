/*
 * Author: Ryan Johns
 * Version: 0.1.0 (Feb 11, 2014)
 * Since: OMIS 3.0 */
eventRunner.addEvent(function($) {


	/** Prepares caseload case assignment listing. */	 
function ajaxcaseLoadCaseLink() {
	var caseLoadCases = document.getElementById("caseLoadCases");
	var dialogElement = document.getElementById("caseLoadDialog");
	
	scrollTable(document.getElementById("caseLoadCase"),
			document.getElementById("CaseLoadCaseFilter"));
	var links = [].slice.call(caseLoadCases.getElementsByClassName("createLink"));
	    links = links.concat([].slice.call(caseLoadCases.getElementsByClassName("viewEditLink")));
	
	for (var x = 0; x < links.length; x++) {
		ajaxLink(links[x], dialogElement,
		function(html) {
			dialogElement.innerHTML=html;
			dialog(dialogElement);
			ajaxcaseLoadCaseForm();
	});}	
	links = [].slice.call(caseLoadCases.getElementsByClassName("removeLink"));
	for (var x = 0; x < links.length; x++) {
		alert(links[x].className);
		ajaxLink(links[x], caseLoadCases, 
			function(html) {
				caseLoadCases.innerHTML=html;
				ajaxcaseLoadCaseLink();
			}, {precondition:function() {
					return confirm(new commonMessageResolver("omis.msgs.common").getMessage("removeConfirmMessage"));
}});}}

/** Prepares caseload case assignment form. */
function ajaxcaseLoadCaseForm() {
	var caseLoadCases = document.getElementById("caseLoadCases");
	
	ajaxSubmit(document.getElementById("caseLoadCaseAssignment"),
			caseLoadCases,
			function() {
				ajaxcaseLoadCaseLink();
			},
			function() {
				ajaxcaseLoadCaseForm();
				caseLoadAssignmentDatePickers();	
			});
	caseLoadAssignmentDatePickers();
	applySearch(document.getElementById("caseLoadSubjectTitle"),
				document.getElementById("caseLoadAssigned"), 
				ui.search.Autocomplete.CASELOAD_SEARCH);
}

if (window.caseLoadId) {
	var caseLoadCases = document.getElementById("caseLoadCases");
	
	var urlCaseLoads = config.ServerConfig.getContextPath() + '/caseLoad/caseLoadCaseAssignment/list.html?caseLoad='+caseLoadId;
	ajax(urlCaseLoads + appendDateRange(), caseLoadCases, 
		function(html) {
			caseLoadCases.innerHTML = html;
			ajaxcaseLoadCaseLink();
});}

/** Apply caseload case assignment date pickers. */
function caseLoadAssignmentDatePickers() {
	var caseLoadCaseForm = document.getElementsByClassName("editCaseLoadCaseAssignmentForm")[0];
	var dates = caseLoadCaseForm.getElementsByClassName("date");
	for (var x = 0; x < dates.length; x++) {
		if (!dates[x].className.match(/b\hasDatePicker\b/)) {
			applyDatePicker(dates[x]);
}}}});