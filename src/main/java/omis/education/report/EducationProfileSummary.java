package omis.education.report;

import omis.education.domain.AchievementCategoryLevel;

/** Educational profile summary.
 * @author Ryan Johns
 * @version 0.1.0 (Aug 31, 2016)
 * @since OMIS 3.0 */
public class EducationProfileSummary {
	private final AchievementCategoryLevel level;
	private final Long count;
	
	/** Constructor.
	 * @param level - achievement category level.
	 * @param count - count. */
	public EducationProfileSummary(final AchievementCategoryLevel level, 
			final Long count) {
		this.level = level;
		this.count = count;
	}
	
	/** Gets level.
	 * @return achievement category level. */
	public AchievementCategoryLevel getLevel() {
		return this.level;
	}
	
	/** Gets count.
	 * @return count - count. */
	public Long getCount() {
		return this.count;
	}
}
