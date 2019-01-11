/*
 * OMIS - Offender Management Information System
 * Copyright (C) 2011 - 2017 State of Montana
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/
package omis.assessment.dao.impl.hibernate;

import java.util.List;
import org.hibernate.SessionFactory;
import omis.assessment.dao.AnswerRatingDao;
import omis.assessment.domain.AnswerRating;
import omis.assessment.domain.RatingCategory;
import omis.assessment.domain.RatingScaleGroup;
import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.questionnaire.domain.AdministeredQuestionnaire;
import omis.questionnaire.domain.AllowedAnswer;

/**
 * Hibernate implementation of the answer rating data access object.
 * 
 * @author Josh Divine
 * @author Annie Wahl
 * @version 0.1.2 (Aug 14, 2018)
 * @since OMIS 3.0
 */
public class AnswerRatingDaoHibernateImpl 
		extends GenericHibernateDaoImpl<AnswerRating> 
		implements AnswerRatingDao {

	/* Queries. */
	
	private static final String FIND_QUERY_NAME = "findAnswerRating";
	
	private static final String FIND_EXCLUDING_QUERY_NAME = 
			"findAnswerRatingExcluding";
	
	private static final String 
			FIND_BY_RATING_CATEGORY_AND_ADMINISTERED_QUESTIONNAIRE_QUERY_NAME = 
				"findAnswerRatingsByRatingCategoryAndAdministeredQuestionnaire";
	
	private static final String 
			FIND_BY_RATING_SCALE_GROUP_AND_ADMINISTERED_QUESTIONNAIRE_QUERY_NAME 
			= "findAnswerRatingsByRatingScaleGroupAndAdministeredQuestionnaire";
	
	private static final String 
			FIND_BY_RATING_CATEGORY_AND_ADMINISTERED_QUESTIONNAIRE_EXCLUDING_SCALED_GROUPS_QUERY_NAME 
			= "findAnswerRatingsByRatingCategoryAndAdministeredQuestionnaireExcludingScaledGroups";
	
	/* Parameters. */
	
	private static final String ALLOWED_ANSWER_PARAM_NAME = "allowedAnswer";
	
	private static final String RATING_CATEGORY_PARAM_NAME = "ratingCategory";
	
	private static final String ADMINISTERED_QUESTIONNAIRE_PARAM_NAME = 
			"administeredQuestionnaire";
	
	private static final String RATING_SCALE_GROUP_PARAM_NAME = 
			"ratingScaleGroup";
	
	private static final String EXCLUDED_ANSWER_RATING_PARAM_NAME = 
			"excludedAnswerRating";
	
	/**
	 * Instantiates an Hibernate implementation of data access object for
	 * answer rating.
	 * 
	 * @param sessionFactory session factory
	 * @param entityName entity name
	 */
	public AnswerRatingDaoHibernateImpl(final SessionFactory sessionFactory, 
			final String entityName) {
		super(sessionFactory, entityName);
	}

	/** {@inheritDoc} */
	@Override
	public AnswerRating find(final AllowedAnswer allowedAnswer, 
			final RatingCategory ratingCategory) {
		AnswerRating answerRating = (AnswerRating) this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(FIND_QUERY_NAME)
				.setParameter(ALLOWED_ANSWER_PARAM_NAME, allowedAnswer)
				.setParameter(RATING_CATEGORY_PARAM_NAME, ratingCategory)
				.uniqueResult();
		return answerRating;
	}

	/** {@inheritDoc} */
	@Override
	public AnswerRating findExcluding(final AllowedAnswer allowedAnswer, 
			final RatingCategory ratingCategory,
			final AnswerRating excludedAnswerRating) {
		AnswerRating answerRating = (AnswerRating) this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(FIND_EXCLUDING_QUERY_NAME)
				.setParameter(ALLOWED_ANSWER_PARAM_NAME, allowedAnswer)
				.setParameter(RATING_CATEGORY_PARAM_NAME, ratingCategory)
				.setParameter(EXCLUDED_ANSWER_RATING_PARAM_NAME, 
						excludedAnswerRating)
				.uniqueResult();
		return answerRating;
	}

	/** {@inheritDoc} */
	@Override
	public List<AnswerRating> findByRatingCategoryAndAdministeredQuestionnaire(
			final RatingCategory ratingCategory,
			final AdministeredQuestionnaire administeredQuestionnaire) {
		@SuppressWarnings("unchecked")
		List<AnswerRating> answerRatings = this.getSessionFactory()
		.getCurrentSession()
				.getNamedQuery(
						FIND_BY_RATING_CATEGORY_AND_ADMINISTERED_QUESTIONNAIRE_QUERY_NAME)
				.setParameter(RATING_CATEGORY_PARAM_NAME, ratingCategory)
				.setParameter(ADMINISTERED_QUESTIONNAIRE_PARAM_NAME, 
						administeredQuestionnaire)
				.list();
		return answerRatings;
	}

	/** {@inheritDoc} */
	@Override
	public List<AnswerRating> findByRatingScaleGroupAndAdministeredQuestionnaire(
			final RatingScaleGroup ratingScaleGroup,
			final AdministeredQuestionnaire administeredQuestionnaire) {
		@SuppressWarnings("unchecked")
		List<AnswerRating> answerRatings = this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(
						FIND_BY_RATING_SCALE_GROUP_AND_ADMINISTERED_QUESTIONNAIRE_QUERY_NAME)
				.setParameter(RATING_SCALE_GROUP_PARAM_NAME, ratingScaleGroup)
				.setParameter(ADMINISTERED_QUESTIONNAIRE_PARAM_NAME, 
						administeredQuestionnaire)
				.list();
		return answerRatings;
	}

	/** {@inheritDoc} */
	@Override
	public List<AnswerRating> 
			findByRatingCategoryAndAdministeredQuestionnaireExcludingScaledGroups(
					final RatingCategory ratingCategory, 
					final AdministeredQuestionnaire administeredQuestionnaire) {
		@SuppressWarnings("unchecked")
		List<AnswerRating> answerRatings = this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(
						FIND_BY_RATING_CATEGORY_AND_ADMINISTERED_QUESTIONNAIRE_EXCLUDING_SCALED_GROUPS_QUERY_NAME)
				.setParameter(RATING_CATEGORY_PARAM_NAME, ratingCategory)
				.setParameter(ADMINISTERED_QUESTIONNAIRE_PARAM_NAME, 
						administeredQuestionnaire)
				.list();
		return answerRatings;
	}
}