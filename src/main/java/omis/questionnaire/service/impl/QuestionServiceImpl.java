package omis.questionnaire.service.impl;

import java.util.List;

import omis.exception.DuplicateEntityFoundException;
import omis.questionnaire.domain.AllowedAnswer;
import omis.questionnaire.domain.AllowedQuestion;
import omis.questionnaire.domain.AnswerCardinality;
import omis.questionnaire.domain.AnswerValue;
import omis.questionnaire.domain.Question;
import omis.questionnaire.domain.QuestionCategory;
import omis.questionnaire.domain.QuestionConditionality;
import omis.questionnaire.domain.QuestionnaireSection;
import omis.questionnaire.service.QuestionService;
import omis.questionnaire.service.delegate.AllowedAnswerDelegate;
import omis.questionnaire.service.delegate.AllowedQuestionDelegate;
import omis.questionnaire.service.delegate.AnswerValueDelegate;
import omis.questionnaire.service.delegate.QuestionDelegate;

/**
 * QuestionServiceImpl.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Nov 16, 2016)
 *@since OMIS 3.0
 *
 */
public class QuestionServiceImpl implements QuestionService {
	
	private final QuestionDelegate questionDelegate;
	
	private final AllowedQuestionDelegate allowedQuestionDelegate;
	
	private final AllowedAnswerDelegate allowedAnswerDelegate;
	
	private final AnswerValueDelegate answerValueDelegate;
	
	/**
	 * @param questionDelegate
	 * @param allowedQuestionDelegate
	 * @param allowedAnswerDelegate
	 * @param answerValueDelegate
	 */
	public QuestionServiceImpl(final QuestionDelegate questionDelegate, 
			final AllowedQuestionDelegate allowedQuestionDelegate,
			final AllowedAnswerDelegate allowedAnswerDelegate, 
			final AnswerValueDelegate answerValueDelegate) {
		this.questionDelegate = questionDelegate;
		this.allowedQuestionDelegate = allowedQuestionDelegate;
		this.allowedAnswerDelegate = allowedAnswerDelegate;
		this.answerValueDelegate = answerValueDelegate;
	}

	/**{@inheritDoc} */
	@Override
	public AllowedQuestion createAllowedQuestion(
			final QuestionnaireSection questionnaireSection,
			final Question question, 
			final QuestionConditionality questionConditionality,
			final AnswerCardinality answerCardinality,
			final String questionNumber, final Short sortOrder,
			final Boolean valid, final String questionHelp)
					throws DuplicateEntityFoundException {
		return this.allowedQuestionDelegate.create(questionnaireSection,
				question, valid, sortOrder, questionNumber,
				questionConditionality, answerCardinality, questionHelp);
	}
	
	/**{@inheritDoc} */
	@Override
	public AllowedQuestion updateAllowedQuestion(
			final AllowedQuestion allowedQuestion,
			final QuestionnaireSection questionnaireSection,
			final Question question, 
			final QuestionConditionality questionConditionality,
			final AnswerCardinality answerCardinality,
			final String questionNumber, final Short sortOrder,
			final Boolean valid, final String questionHelp)
					throws DuplicateEntityFoundException {
		return this.allowedQuestionDelegate.update(allowedQuestion,
				questionnaireSection, question, valid, sortOrder,
				questionNumber, questionConditionality, answerCardinality,
				questionHelp);
	}
	
	/**{@inheritDoc} */
	@Override
	public Question createQuestion(final QuestionCategory questionCategory,
			final String text, final Boolean statik, final Boolean valid)
					throws DuplicateEntityFoundException {
		return this.questionDelegate.create(questionCategory, text, statik,
				valid);
	}
	
	/**{@inheritDoc} */
	@Override
	public Question updateQuestion(final Question question,
			final QuestionCategory questionCategory, final String text,
			final Boolean statik, final Boolean valid)
					throws DuplicateEntityFoundException {
		return this.questionDelegate.update(question, questionCategory, text,
				statik, valid);
	}
	
	

	/**{@inheritDoc} */
	@Override
	public List<AllowedQuestion> findAllowedQuestionsByQuestionnaireSection(
			final QuestionnaireSection questionnaireSection) {
		return this.allowedQuestionDelegate
				.findAllByQuestionnaireSection(questionnaireSection);
	}

	/**{@inheritDoc} */
	@Override
	public List<AllowedAnswer> findAllowedAnswersByAllowedQuestion(
			final AllowedQuestion allowedQuestion) {
		return this.allowedAnswerDelegate
				.findAllByAllowedQuestion(allowedQuestion);
	}

	/**{@inheritDoc} */
	@Override
	public List<Question> findQuestionsByText(final String text) {
		return this.questionDelegate.findQuestionsByText(text);
	}

	/**{@inheritDoc} */
	@Override
	public List<AnswerValue> findAnswerValuesByDescription(
			final String description) {
		return this.answerValueDelegate
				.findAnswerValuesByDescription(description);
	}

	/**{@inheritDoc} */
	@Override
	public AnswerValue createAnswerValue(final String description,
			final String value) throws DuplicateEntityFoundException {
		return this.answerValueDelegate.create(description, value);
	}

	/**{@inheritDoc} */
	@Override
	public AnswerValue updateAnswerValue(final AnswerValue answerValue,
			final String description, final String value)
			throws DuplicateEntityFoundException {
		return this.answerValueDelegate.update(answerValue, description, value);
	}

	/**{@inheritDoc} */
	@Override
	public AllowedAnswer createAllowedAnswer(
			final AllowedQuestion allowedQuestion,
			final AnswerValue answerValue, final Short sortOrder)
			throws DuplicateEntityFoundException {
		return this.allowedAnswerDelegate.create(allowedQuestion, sortOrder,
				answerValue);
	}

	/**{@inheritDoc} */
	@Override
	public AllowedAnswer updateAllowedAnswer(final AllowedAnswer allowedAnswer,
			final AllowedQuestion allowedQuestion,
			final AnswerValue answerValue, final Short sortOrder)
					throws DuplicateEntityFoundException {
		return this.allowedAnswerDelegate.update(allowedAnswer,
				allowedQuestion, sortOrder, answerValue);
	}

	/**{@inheritDoc} */
	@Override
	public void removeQuestion(final Question question) {
		this.questionDelegate.remove(question);
	}

	/**{@inheritDoc} */
	@Override
	public void removeAllowedQuestion(final AllowedQuestion allowedQuestion) {
		this.allowedQuestionDelegate.remove(allowedQuestion);
	}

	/**{@inheritDoc} */
	@Override
	public void removeAnswerValue(final AnswerValue answerValue) {
		this.answerValueDelegate.remove(answerValue);
	}

	/**{@inheritDoc} */
	@Override
	public void removeAllowedAnswer(final AllowedAnswer allowedAnswer) {
		this.allowedAnswerDelegate.remove(allowedAnswer);
	}
	
	/**{@inheritDoc} 
	 * @throws DuplicateEntityFoundException */
	@Override
	public AllowedQuestion setTrueFalseAllowedAnswersToAllowedQuestion(
			final AllowedQuestion allowedQuestion)
					throws DuplicateEntityFoundException{
		List<AllowedAnswer> currentAllowedAnswers =
				this.allowedAnswerDelegate.findAllByAllowedQuestion(
						allowedQuestion);
		int countBoth = 0;
		for(AllowedAnswer aa : currentAllowedAnswers){
			if(!(aa.getAnswerValue().equals(this.answerValueDelegate.findTrue()))
			|| (!aa.getAnswerValue().equals(this.answerValueDelegate
					.findFalse()))){
				this.allowedAnswerDelegate.remove(aa);
			}
			else{
				countBoth++;
			}
		}
		if(countBoth != 2){
			for(AllowedAnswer aa : currentAllowedAnswers){
				this.allowedAnswerDelegate.remove(aa);
			}
			this.allowedAnswerDelegate.create(allowedQuestion, (short) 0,
					this.answerValueDelegate.findTrue());
			this.allowedAnswerDelegate.create(allowedQuestion, (short) 1,
					this.answerValueDelegate.findFalse());
		}
		
		return allowedQuestion;
	}

	/**{@inheritDoc} */
	@Override
	public List<AllowedQuestion> findAllAllowedQuestionsByQuestionnaireSection(
			QuestionnaireSection questionnaireSection) {
		return this.allowedQuestionDelegate
				.findAllByQuestionnaireSectionIncludingInvalid(
						questionnaireSection);
	}
}
