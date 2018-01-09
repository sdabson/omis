package omis.education.domain;

/** Achievement category level.
 * @author Ryan Johns
 * @version 0.1.0 (Aug 30, 2016)
 * @since OMIS 3.0 */
public enum AchievementCategoryLevel {
	HIGHSCHOOL,
	ASSOCIATE,
	BACHELORS,
	MASTERS,
	DOCTORAL;

	/** Returns {@code this.name()}
	 * <p> See {@link Enum#name()}
	 * @return {@code this.name()} */
	public String getName() {
		return this.name();
	}
}
