package omis.datatype;


/**
 * Represents a week of a month. The week can be the first through fourth
 * or can be the last. The last week of the month could be the fourth or fifth.
 * 
 * @author Stephen Abson
 * @version 0.1.2 (April 8, 2014)
 * @since OMIS 3.0
 */
public enum WeekOfMonth {

	/** First week of month. */
	FIRST("1st", "First", "First week of month"),
	
	/** Second week of month. */
	SECOND("2nd", "Second", "Second week of month"),
	
	/** Third week of month. */
	THIRD("3rd", "Third", "Third week of month"),
	
	/** Fourth week of month. */
	FOURTH("4th", "Fourth", "Fourth week of month"),
	
	/** Fifth week of month. */
	FIFTH("5th", "Fifth", "Fifth week of month"),
	
	/** Last week of month (fourth, fifth or sixth week depending on month). */
	LAST("Last", "Fourth, fifth or sixth",
			"Fourth, fifth or sixth week of month");
	
	private final String shortName;
	
	private final String name;
	
	private final String description;
	
	// Instantiates with the specified properties
	private WeekOfMonth(final String shortName, final String name,
			final String description) {
		this.shortName = shortName;
		this.name = name;
		this.description = description;
	}
	
	/**
	 * Returns the short name of the week of the month.
	 * 
	 * @return short name
	 */
	public String getShortName() {
		return this.shortName;
	}
	
	/**
	 * Returns the name of the week of the month.
	 * 
	 * @return name
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * Returns the description of the day of the month.
	 * 
	 * <p>Such a description might provide a useful tooltip in the UI of a event
	 * schedule builder tool.
	 * 
	 * @return description
	 */
	public String getDescription() {
		return this.description;
	}
	
	/**
	 * Returns the week of month with the specified short name.
	 * 
	 * @param shortName short name of week of month to find
	 * @return week of month with specified short name; {@code null} if a week
	 * of month with specified short name was not found or if specified
	 * short name is {@code null} or equal to {@code ""}
	 */
	public static WeekOfMonth findByShortName(final String shortName) {
		if (shortName == null || shortName.length() == 0) {
			return null;
		}
		for (WeekOfMonth weekOfMonth : WeekOfMonth.values()) {
			if (weekOfMonth.getShortName().equals(shortName)) {
				return weekOfMonth;
			}
		}
		return null;
	}
	
	/**
	 * Returns the week of the month with the specified name.
	 * 
	 * @param name name of week of month to find
	 * @return week of month with specified name; {@code null} if a week
	 * of month with the specified name was not found or if the specified
	 * name is {@code null} or is equal to {@code ""}
	 */
	public static WeekOfMonth findByName(final String name) {
		if (name == null || name.length() == 0) {
			return null;
		}
		for (WeekOfMonth weekOfMonth : WeekOfMonth.values()) {
			if (weekOfMonth.getName().equals(name)) {
				return weekOfMonth;
			}
		}
		return null;
	}
}