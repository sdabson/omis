package omis.presentenceinvestigation.dao.impl.hibernate;

import java.util.Date;
import java.util.List;

import org.hibernate.SessionFactory;

import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.presentenceinvestigation.dao.EvaluationRecommendationSectionSummaryNoteDao;
import omis.presentenceinvestigation.domain.EvaluationRecommendationSectionSummary;
import omis.presentenceinvestigation.domain.EvaluationRecommendationSectionSummaryNote;

/**
 * Evaluation Recommendation Section Summary Note Dao Hibernate Impl
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Sep 12, 2017)
 *@since OMIS 3.0
 *
 */
public class EvaluationRecommendationSectionSummaryNoteDaoHibernateImpl extends
		GenericHibernateDaoImpl<EvaluationRecommendationSectionSummaryNote>
		implements EvaluationRecommendationSectionSummaryNoteDao {
	
	private static final String FIND_SECTION_SUMMARY_NOTE_QUERY_NAME =
			"findEvaluationRecommendationSectionSummaryNote";
	
	private static final String FIND_SECTION_SUMMARY_NOTE_EXCLUDING_QUERY_NAME =
			"findEvaluationRecommendationSectionSummaryNoteExcluding";
	
	private static final String
			FIND_SECTION_SUMMARY_NOTES_BY_SECTION_SUMMARY_QUERY_NAME =
				"findEvaluationRecommendationSectionSummaryNotesBy" +
				"EvaluationRecommendationSectionSummary";
	
	private static final String DESCRIPTION_PARAM_NAME = "description";
	
	private static final String DATE_PARAM_NAME = "date";
	
	private static final String
			EVALUATION_RECOMMENDATION_SECTION_SUMMARY_PARAM_NAME =
				"evaluationRecommendationSectionSummary";
	
	private static final String
			EVALUATION_RECOMMENDATION_SECTION_SUMMARY_NOTE_PARAM_NAME =
				"evaluationRecommendationSectionSummaryNote";
	
	/**
	 * @param sessionFactory
	 * @param entityName
	 */
	protected EvaluationRecommendationSectionSummaryNoteDaoHibernateImpl(
			final SessionFactory sessionFactory, final String entityName) {
		super(sessionFactory, entityName);
	}
	
	/**{@inheritDoc} */
	@Override
	public EvaluationRecommendationSectionSummaryNote find(
			final String description, final Date date,
			final EvaluationRecommendationSectionSummary
				evaluationRecommendationSectionSummary) {
		EvaluationRecommendationSectionSummaryNote note =
				(EvaluationRecommendationSectionSummaryNote)
				this.getSessionFactory().getCurrentSession().getNamedQuery(
						FIND_SECTION_SUMMARY_NOTE_QUERY_NAME)
				.setParameter(DESCRIPTION_PARAM_NAME, description)
				.setDate(DATE_PARAM_NAME, date)
				.setParameter(
						EVALUATION_RECOMMENDATION_SECTION_SUMMARY_PARAM_NAME,
						evaluationRecommendationSectionSummary)
				.uniqueResult();
		
		return note;
	}

	/**{@inheritDoc} */
	@Override
	public EvaluationRecommendationSectionSummaryNote findExcluding(
			final String description, final Date date,
			final EvaluationRecommendationSectionSummary
				evaluationRecommendationSectionSummary,
			final EvaluationRecommendationSectionSummaryNote
				evaluationRecommendationSectionSummaryNoteExcluded) {
		EvaluationRecommendationSectionSummaryNote note =
				(EvaluationRecommendationSectionSummaryNote)
				this.getSessionFactory().getCurrentSession().getNamedQuery(
						FIND_SECTION_SUMMARY_NOTE_EXCLUDING_QUERY_NAME)
				.setParameter(DESCRIPTION_PARAM_NAME, description)
				.setDate(DATE_PARAM_NAME, date)
				.setParameter(
						EVALUATION_RECOMMENDATION_SECTION_SUMMARY_PARAM_NAME,
						evaluationRecommendationSectionSummary)
				.setParameter(
						EVALUATION_RECOMMENDATION_SECTION_SUMMARY_NOTE_PARAM_NAME,
						evaluationRecommendationSectionSummaryNoteExcluded)
				.uniqueResult();
		
		return note;
	}

	/**{@inheritDoc} */
	@Override
	public List<EvaluationRecommendationSectionSummaryNote> findBySectionSummary(
				final EvaluationRecommendationSectionSummary
					evaluationRecommendationSectionSummary) {
		@SuppressWarnings("unchecked")
		List<EvaluationRecommendationSectionSummaryNote> notes = 
				this.getSessionFactory().getCurrentSession().getNamedQuery(
						FIND_SECTION_SUMMARY_NOTES_BY_SECTION_SUMMARY_QUERY_NAME)
				.setParameter(
						EVALUATION_RECOMMENDATION_SECTION_SUMMARY_PARAM_NAME,
						evaluationRecommendationSectionSummary)
				.list();
		
		return notes;
	}

}
