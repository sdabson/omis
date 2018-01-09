/**
 * Stores a list of events and allows them to be executed in the order in
 * which they were stored.
 * @author Stephen Abson
 * @version 0.1.0 (April 2, 2012)
 * @since OMIS 3.0
 */

EventRunner = function() {
	
	var events = new Array();
	
	/** Runs all the stored events in the order in which they were added. */
	this.runEvents = function() {
		for (var i = 0; i < events.length; i++) {
			events[i].call();
		}
	};
	
	/**
	 * Stores an event.
	 * @param event event to store
	 */
	this.addEvent = function(event) {
		events[events.length] = event;
	};
};

var eventRunner = new EventRunner();
$(document).ready(function() { eventRunner.runEvents(); });