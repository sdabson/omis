/** Referral js.
 * @author Ryan Johns
 * @version 0.1.0 (Feb 5, 2015)
 * @since OMIS 3.0 */
$(document).ready(function() {

	/* applies form update checker. */
	applyFormUpdateChecker(document.getElementById("form"));
	
	/* applies date picker. */
	applyDatePicker(document.getElementById("referralDate"));
	
	/* applies user search for referral source. */
	applyUserSearch(document.getElementById("referringUserInput"),
			document.getElementById("referringUserId"),
			document.getElementById("userLabel"),
			document.getElementById("currentUser"),
			document.getElementById("clearUser"));
	
	/* applies action menu */
	applyActionMenu(document.getElementById("actionMenuLink"));
	applyActionMenu(document.getElementById("prereleaseActionMenuLink"));
	document.getElementById("prereleaseActionMenuLink").onclick = function() {
		var addPrereleaseLink = document.getElementById("addPrerelease");
		if (!hasClass(addPrereleaseLink, "addPrerelease")) {
				addPrereleaseLink.className = addPrereleaseLink.className + " addPrerelease";
				addPrereleaseLink.onclick = function() {
					var prereleaseTable = document.getElementById("preReleaseList");
					ajax(this.href+"?offender="+offenderId+"&index="+(prereleaseTable.rows.length-1), prereleaseTable, function(html) {
						var prereleaseTable = document.getElementById("preReleaseList");
						var trs = domFromHtml(html);
						for(x = 0; x < trs.length; x++) {
							var tr = trs[x];
							prereleaseTable.tBodies[0].appendChild(tr);
							var tr1 = prereleaseTable.tBodies[0].getElementsByTagName("tr")[prereleaseTable.tBodies[0].getElementsByTagName("tr").length-1];
							var tbody = getParent(tr1, "tbody");
							var previous = prev(tr1);
							while (previous != undefined && previous != null && hasClass(previous, "removed")) {
								swapOrder(tr1, previous);
								tbody.insertBefore(tr1, previous);
								previous = prev(tr1);
							}
						
							applyOnClick();
						}
					});
					
					return false;
				};
			}	
	};
	
	applyActionMenu(document.getElementById("treatmentActionMenuLink"));
	document.getElementById("treatmentActionMenuLink").onclick = function() {
		var addTreatmentLinks = document.getElementsByClassName("addTreatment");
		for (var x = 0; x < addTreatmentLinks.length; x++) {
			var addTreatmentLink = addTreatmentLinks[x];
			if (!hasClass(addTreatmentLink, "addTreatmentLink")) {
				addTreatmentLink.className = addTreatmentLink.className + " addTreatmentLink";
				addTreatmentLink.onclick = function() {
					var treatmentTable = document.getElementById("treatmentList");
					var index = treatmentTable.rows.length-1;
					ajax(this.href+'&offender='+offenderId+'&index='+index, treatmentTable, function(html) {
						var trS = domFromHtml(html);
						for (var x =0; x < trS.length; x++) {
							var tr = trS[x];
							treatmentTable.tBodies[0].appendChild(tr);
							var tr1 = treatmentTable.tBodies[0].getElementsByTagName("tr")[treatmentTable.tBodies[0].getElementsByTagName("tr").length-1];
							var tbody = getParent(tr1, "tbody");
							var previous = prev(tr1);
							while (previous != undefined && previous != null && hasClass(previous, "removed")) {
								swapOrder(tr1, previous);
								tbody.insertBefore(tr1, previous);
								previous = prev(tr1);
							}
							applyOnClick();
						}
					});
					

			}	
		}
		return false;
	};
	
	
	/* applies on change to program categories to populate appropriate facilities. */
	document.getElementById("programCategory").onchange = setFacilities;
	
	
	/* Queues up screening on facility selection. */
	document.getElementById("facility").onchange = setScreening;
	
	if (document.getElementById("facility").value = "") {
		setFacilities();
		setScreening();
	}
	
	
	
	applyOnClick();
	}});

/* Sets screening either prerelease or treatment. */
function setScreening() {
	var facilityValue = document.getElementById("facility").value;
	var programValue = document.getElementById("programCategory").value;
	var target;
	
	if (programValue != "") {
		if (facilityValue != "") {
			ajax(config.ServerConfig.getContextPath() + "/placementScreening/referral/queueScreening.html?referralScreeningCenter="+facilityValue+"&category="+programValue+"&offender="+offenderId,
				target, screeningSuccess);		
		} 
	}
}


/* Sets facilities given program category. */
function setFacilities() {
	var programValue = document.getElementById("programCategory").value;
	if (programValue != "") {
	ajax(config.ServerConfig.getContextPath()+"/placementScreening/referral/facilitiesByProgramCategory.html?programCategory="+programValue+"&offender="+offenderId,
	document.getElementById("facility"), facilitySuccess);
	} else {
		document.getElementById("facility").innerHTML = "";
	}
}

/* Sets facilities and queues up screening. */
function facilitySuccess(html) {
	document.getElementById("facility").innerHTML = html;
	
	setScreening();
}

/* Ajax success function on screening facilities. */
function screeningSuccess(html) {
	var programValue = document.getElementById("programCategory").value;
	var target;
	if (programValue === "PRERELEASE") { 
		target = document.getElementById("preReleaseList");
	} else {
		target = document.getElementById("treatmentList");
	}
	
	if (!edited(target.tBodies[0], 'itemOperation')) {
		target.innerHTML = html;
	}
	
	applyOnClick();
}

/* Applies on click functionality. */
function applyOnClick() {
	
	
	var nodes = document.getElementsByClassName("upLink");
	for (var x = 0; x < nodes.length; x++) {
		if (!hasClass(this, 'shiftUp')) {
			nodes[x].onclick = shiftUpOnClick;
			nodes[x].className = nodes[x].className + " shiftUp";
		}
	}
	
	nodes = document.getElementsByClassName("downLink");
	for (var x = 0; x < nodes.length; x++) {
		if (!hasClass(this, 'shiftDown')) {
			nodes[x].onclick = shiftDownOnClick;
			nodes[x].className = nodes[x].className + " shiftDown";
		}
	}
	
	nodes = document.getElementsByClassName("removeLink");
	for (var x = 0; x < nodes.length; x++) {
		if (!hasClass(this, 'remove')) {
			nodes[x].onclick = removeRowClick;
			nodes[x].className = nodes[x].className + " remove";
		}
			
	}
}

/* Shift row up event. */
function shiftUpOnClick() {
		var tr = getParent(this, "tr");
		var previous = prev(tr);
		if (previous != undefined && previous != null && !hasClass(tr, "removed") && !hasClass(previous, "removed")) {
			swapOrder(tr, previous);
			getParent(tr, "tbody").insertBefore(tr,previous);
		}
}

/* Shift row down event. */
function shiftDownOnClick() {
		var tr = getParent(this, "tr");
		var next = nxt(tr);
		if (next != undefined && next != null && !hasClass(tr,"removed") && !hasClass(next,"removed")) {
			swapOrder(tr, next);
			getParent(tr, "tbody").insertBefore(next,tr);
		}
}

/* Mark or un-mark row for removal. */
function removeRowClick() {
	var tr = getParent(this, "tr");
	var tbody = getParent(tr, "tbody");
	if (!hasClass(tr, "removed")) {
		var next = nxt(tr);
		while(next != undefined && next != null) {
			swapOrder(tr, next);
			tbody.insertBefore(next, tr);
			next = nxt(tr);
		}
		removeRow(tr, "itemOperation");
	} else {
		replaceRow(tr, "itemOperation", "referralScreening");
		var previous = prev(tr);
		while (previous != undefined && previous != null && hasClass(previous, "removed")) {
			swapOrder(tr, previous);
			tbody.insertBefore(tr, previous);
			previous = prev(tr);
			
		}
	}
}

/* Swaps rows and adjusts orders respectively. */
function swapOrder(tr, tr2) {
	var trIndex =  tr.getElementsByClassName("upLink")[0].getAttribute('id').replace(/upLink\[/, '').replace(/\][pt]/,'');
	var tr2Index = tr2.getElementsByClassName("upLink")[0].getAttribute('id').replace(/upLink\[/, '').replace(/\][pt]/,'');
	var trOrder;
	var tr2Order;
	
	if (isPrereleaseRow(tr)) {
		trOrder = document.getElementById("preReleaseOrder["+trIndex+"]");
		tr2Order = document.getElementById("preReleaseOrder["+tr2Index+"]");
	} else {
		trOrder = document.getElementById("treatmentOrder["+trIndex+"]");
		tr2Order = document.getElementById("treatmentOrder["+tr2Index+"]");
	}
	
	var tempVal = tr2Order.value;
	tr2Order.value = trOrder.value;
	trOrder.value = tempVal;	
}