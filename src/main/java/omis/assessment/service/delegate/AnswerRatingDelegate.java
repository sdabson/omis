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
package omis.assessment.service.delegate;

import java.math.BigDecimal;
import java.util.List;
import omis.assessment.dao.AnswerRatingDao;
import omis.assessment.domain.AnswerRating;
import omis.assessment.domain.RatingCategory;
import omis.assessment.domain.RatingScaleGroup;
import omis.exception.DuplicateEntityFoundException;
import omis.instance.factory.InstanceFactory;
import omis.questionnaire.domain.AdministeredQuestionnaire;
import omis.questionnaire.domain.AllowedAnswer;

/**
 * Answer rating delegate.
 * 
 * @author Josh Divine
 * @author Annie Wahl
 * @version 0.1.2 (Aug 14, 2018)
 * @since OMIS 3.0
 */
public class AnswerRatingDelegate {

	/* Data access objects. */
	
	private final AnswerRatingDao answerRatingDao;

	/* Instance factories. */
	
	private final InstanceFactory<AnswerRating> answerRatingInstanceFactory;
	
	/**
	 * Instantiates an implementation of answer rating delegate with the 
	 * specified date access object and instance factory.
	 * 
	 * @param answerRatingDao answer rating data access object
	 * @param answerRatingInstanceFactory answer rating instance factory
	 */
	public AnswerRatingDelegate(final AnswerRatingDao answerRatingDao,
			final InstanceFactory<AnswerRating> answerRatingInstanceFactory) {
		this.answerRatingDao = answerRatingDao;
		this.answerRatingInstanceFactory = answerRatingInstanceFactory;
	}
	
	/**
	 * Creates a new allowed answer.
	 * 
	 * @param allowedAnswer allowed answer
	 * @param ratingCategory rating category
	 * @param value value
	 * @return allowed answer
	 * @throws DuplicateEntityFoundException if duplicate entity exists
	 */
	public AnswerRating create(final AllowedAnswer allowedAnswer, 
			final RatingCategory ratingCategory, final BigDecimal value) 
					throws DuplicateEntityFoundException {
		if (this.answerRatingDao.find(allowedAnswer, ratingCategory) != null) {
			throw new DuplicateEntityFoundException(
					"Answer rating already exists.");
		}
		AnswerRating answerRating = this.answerRatingInstanceFactory
				.createInstance();
		populateAnswerRating(answerRating, allowedAnswer,
				ratingCategory, value);
		return this.answerRatingDao.makePersistent(answerRating);
	}
	
	/**
	 * Updates an existing allowed answer.
	 * 
	 * @param answerRating answer rating
	 * @param allowedAnswer allowed answer
	 * @param ratingCategory rating category
	 * @param value value
	 * @return allowed answer
	 * @throws DuplicateEntityFoundException if duplicate entity exists
	 */
	public AnswerRating update(final AnswerRating answerRating, 
			final AllowedAnswer allowedAnswer,
			final RatingCategory ratingCategory, 
			final BigDecimal value) 
					throws DuplicateEntityFoundException {
		if (this.answerRatingDao.findExcluding(allowedAnswer, ratingCategory, 
				answerRating) != null) {
			throw new DuplicateEntityFoundException(
					"Answer rating already exists.");
		}
		populateAnswerRating(answerRating, allowedAnswer,
				ratingCategory, value);
		return this.answerRatingDao.makePersistent(answerRating);
	}

	/**
	 * Removes the specified answer rating.
	 * 
	 * @param answerRating answer rating
	 */
	public void remove(final AnswerRating answerRating) {
		this.answerRatingDao.makeTransient(answerRating);
	}
	
	/**
	 * Returns a list of answer ratings for the specified rating category and 
	 * administered questionnaire.
	 * 
	 * @param ratingCategory rating category
	 * @param administeredQuestionnaire administered questionnaire
	 * @return list of answer ratings
	 */
	public List<AnswerRating> findByRatingCategoryAndAdministeredQuestionnaire(
			final RatingCategory ratingCategory,
			final AdministeredQuestionnaire administeredQuestionnaire) {
		return this.answerRatingDao
				.findByRatingCategoryAndAdministeredQuestionnaire(
						ratingCategory, administeredQuestionnaire);
	}
	
	// Populates an answer rating
	private void populateAnswerRating(final AnswerRating answerRating, 
			final AllowedAnswer allowedAnswer,
			final RatingCategory ratingCategory, 
			final BigDecimal value) {
		answerRating.setAllowedAnswer(allowedAnswer);
		answerRating.setRatingCategory(ratingCategory);
		answerRating.setValue(value);
	}

	/**
	 * Returns a list of answer ratings for the specified scaled group and 
	 * administered questionnaire.
	 * 
	 * @param ratingScaleGroup rating scale group
	 * @param administeredQuestionnaire administered questionnaire
	 * @return list of answer ratings
	 */
	public List<AnswerRating> 
			findByRatingScaleGroupAndAdministeredQuestionnaire(
					final RatingScaleGroup ratingScaleGroup,
					final AdministeredQuestionnaire administeredQuestionnaire) {
		return this.answerRatingDao
				.findByRatingScaleGroupAndAdministeredQuestionnaire(
						ratingScaleGroup, administeredQuestionnaire);
	}

	/**
	 * Returns a list of answer ratings for the specified rating category and 
	 * administered questionnaire excluding already scaled groups.
	 * 
	 * @param ratingCategory rating category
	 * @param administeredQuestionnaire administered questionnaire
	 * @return list of answer ratings
	 */
	public List<AnswerRating> 
			findByRatingCategoryAndAdministeredQuestionnaireExcludingScaledGroups(
					final RatingCategory ratingCategory, 
					final AdministeredQuestionnaire administeredQuestionnaire) {
		return this.answerRatingDao
				.findByRatingCategoryAndAdministeredQuestionnaireExcludingScaledGroups(
						ratingCategory, administeredQuestionnaire);
	}
}