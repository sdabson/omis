package omis.education.report.impl.hibernate;

import java.util.Arrays;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;

import omis.education.domain.AchievementCategoryLevel;
import omis.education.report.EducationProfileItemReportService;
import omis.education.report.EducationProfileSummary;
import omis.offender.domain.Offender;

/** Hibernate implementation of education profile item report service.
 * @author Ryan Johns
 * @version 0.1.0 (Aug 30, 2016)
 * @since OMIS 3.0 */
public class EducationProfileItemReportServiceHibernateImpl 
	implements EducationProfileItemReportService {
	private static final String FIND_HIGH_SCHOOL_EDUCATION_BY_OFFENDER_QUERY_NAME =
			"findHighSchoolEducationByOffender";
	private static final String OFFENDER_PARAM_NAME = "offender";
	private static final String LEVELS_PARAM_NAME = "levels";
	
	private final SessionFactory sessionFactory;
	
	/** Constructor.
	 * @param sessionFactory - session factory. */
	public EducationProfileItemReportServiceHibernateImpl(
			final SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	/** [@inheritDoc} */
	@Override
	public List<EducationProfileSummary> findEducationSummaryByOffender(
			final Offender offender) {
		Query q = this.sessionFactory.getCurrentSession().getNamedQuery(
				FIND_HIGH_SCHOOL_EDUCATION_BY_OFFENDER_QUERY_NAME);
		q.setEntity(OFFENDER_PARAM_NAME, offender);
		q.setParameterList(LEVELS_PARAM_NAME,
				Arrays.asList(AchievementCategoryLevel.HIGHSCHOOL,
				AchievementCategoryLevel.MASTERS, 
				AchievementCategoryLevel.ASSOCIATE,
				AchievementCategoryLevel.DOCTORAL, 
				AchievementCategoryLevel.BACHELORS));
		return this.cast(q.list());
	}
	
	/* Casts to list of education summaries. */
	@SuppressWarnings("unchecked")
	private List<EducationProfileSummary> cast(List<?> objs) {
		return (List<EducationProfileSummary>) objs;
	}
}
