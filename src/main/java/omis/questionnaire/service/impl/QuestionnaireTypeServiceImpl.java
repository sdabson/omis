package omis.questionnaire.service.impl;

import java.util.Date;
import java.util.List;

import omis.exception.DuplicateEntityFoundException;
import omis.questionnaire.domain.QuestionnaireCategory;
import omis.questionnaire.domain.QuestionnaireType;
import omis.questionnaire.service.QuestionnaireTypeService;
import omis.questionnaire.service.delegate.QuestionnaireCategoryDelegate;
import omis.questionnaire.service.delegate.QuestionnaireTypeDelegate;

/**
 * QuestionnaireTypeServiceImpl.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Nov 16, 2016)
 *@since OMIS 3.0
 *
 */
public class QuestionnaireTypeServiceImpl implements QuestionnaireTypeService {
	
	private final QuestionnaireTypeDelegate questionnaireTypeDelegate;

	private final QuestionnaireCategoryDelegate questionnaireCategoryDelegate;
	
	
	/**
	 * @param questionnaireTypeDelegate
	 * @param questionnaireCategoryDelegate
	 */
	public QuestionnaireTypeServiceImpl(
			final QuestionnaireTypeDelegate questionnaireTypeDelegate,
			final QuestionnaireCategoryDelegate questionnaireCategoryDelegate) {
		this.questionnaireTypeDelegate = questionnaireTypeDelegate;
		this.questionnaireCategoryDelegate = questionnaireCategoryDelegate;
	}

	/**{@inheritDoc} */
	@Override
	public QuestionnaireType create(final String title, final String shortTitle,
			final Integer cycle, final Boolean valid, final Date startDate,
			final Date endDate, final String questionnaireHelp,
			final QuestionnaireCategory questionnaireCategory)
					throws DuplicateEntityFoundException {
		return this.questionnaireTypeDelegate.create(shortTitle, title, cycle,
				questionnaireCategory, valid, startDate, endDate,
				questionnaireHelp);
	}

	/**{@inheritDoc} */
	@Override
	public QuestionnaireType update(final QuestionnaireType questionnaireType,
			final String title, final String shortTitle, final Integer cycle,
			final Boolean valid, final Date startDate, final Date endDate,
			final String questionnaireHelp,
			final QuestionnaireCategory questionnaireCategory)
					throws DuplicateEntityFoundException {
		return this.questionnaireTypeDelegate.update(questionnaireType,
				shortTitle, title, cycle, questionnaireCategory, valid,
				startDate, endDate, questionnaireHelp);
	}

	/**{@inheritDoc} */
	@Override
	public void remove(final QuestionnaireType questionnaireType) {
		this.questionnaireTypeDelegate.remove(questionnaireType);
	}

	/**{@inheritDoc} */
	@Override
	public List<QuestionnaireCategory> findAllQuestionnaireCategories() {
		return this.questionnaireCategoryDelegate.findAll();
	}

	/**{@inheritDoc} */
	@Override
	public QuestionnaireType copy(QuestionnaireType questionnaireType)
			throws DuplicateEntityFoundException {
		return this.questionnaireTypeDelegate.copy(questionnaireType);
	}

	
	
	
	
	
	
	
}
