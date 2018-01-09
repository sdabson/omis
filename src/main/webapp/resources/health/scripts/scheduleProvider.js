/** Schedule Provider behavior.
 * @author: Ryan Johns
 * @version: 0.1.0 (Jun 05, 2014)
 * @since OMIS 3.0 */
$(document).ready(function($) {
	var createIrregularScheduleLink = document.getElementById("createIrregularScheduleDay");
	var createIrregularScheduleDaysLink = document.getElementById("createIrregularScheduleDateRange");
	applyAddCreateIrregularScheduleDay(createIrregularScheduleLink);
	applyAddCreateIrregularScheduleDays(createIrregularScheduleDaysLink);
	applyTimePickers("time");
	applyDatePickers("date");
	
	applyRemoveBehavior("removeLink");
	applyRefreshBehavior("dateRangeRefresh", document.URL.replace(/&startDate=.*/, ""));
});