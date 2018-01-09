/** Referral Center.
 * @author: Ryan Johns
 * @version: 0.1.0 (Feb 23, 2015)
 * @since OMIS 3.0 */

/* Checks element has classname. */
function hasClass(element, classname) {
	return $(element).hasClass(classname);
}

/* Gets parent given selector. */
function getParent(element, selector) {
	return $(element).parents(selector)[0];
}

/* Gets previous sibling. */
function prev(element) {
	return $(element).prev()[0];
}

/* Gets next sibling. */
function nxt(element) {
	return $(element).next()[0];
}

/* Evaluates if row is a prerelease. */
function isPrereleaseRow(tr) {
	return ($(tr).parents("table").attr('id') == 'preReleaseList');
}

/* Marks row for removal. */
function removeRow(tr, itemOperationClass) {
	if (!$(tr).hasClass("removed")) {
		$(tr).addClass("removed");
		var operation = $(tr).find("."+itemOperationClass)[0];
		operation.value = "REMOVE";
	}
}

/* Marks row for inclusion. */
function replaceRow(tr,operationClass, referralScreeningClass) {
	$(tr).removeClass("removed");
	var operation = $(tr).find("."+operationClass)[0];
	var referralScreening = $(tr).find("."+referralScreeningClass)[0];
	if ($(referralScreening).val() == '') {
		operation.value = "CREATE";
	} else {
		operation.value = "UPDATE";
	}
}

/* Removes classname from element given classname. */
function removeClass(element, classname) {
	$(element).removeClass(classname);
}

/* Creates dom element from html. */
function domFromHtml(html) {
	var element = $(html);
	
	if (element.length > 1) { 
		return $(html).get();
	} else { return [element.get(0)]; }
}

/* finds if table has edited records. */
function edited(tbody, operationClass) {
	var result = false;
	var itemOperations = $(tbody).find("."+operationClass);
	
	itemOperations.each(function(index, value) {
		var itemOperation = $(value);
		if (itemOperation.val() != 'CREATE') {
			result = true;
			return false;
		}
	});
	return result;
}