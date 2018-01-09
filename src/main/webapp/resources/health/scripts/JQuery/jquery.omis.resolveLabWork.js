/**
 * Jquery implementation of functions for resolveLabWork.js
 * 
 * @author: Yidong Li
 * @version: 0.1.0 (Dec. 24, 2014)
 * @since: OMIS 3.0
 */

/**
 * Remove css class from the selected lab work item.
 * 
 * @param labWorkItem lab work item
 */
function removeClass(labWorkItem) {
	$(labWorkItem).removeClass('remove');
};

/**
 * Add css class to the selected lab work item.
 * 
 * @param labWorkItem lab work item
 */
function addClass(labWorkItem) {
	$(labWorkItem).addClass('remove');
};