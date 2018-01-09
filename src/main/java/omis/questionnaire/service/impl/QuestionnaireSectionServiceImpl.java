package omis.questionnaire.service.impl;

import java.util.List;

import omis.exception.DuplicateEntityFoundException;
import omis.questionnaire.domain.QuestionnaireSection;
import omis.questionnaire.domain.QuestionnaireType;
import omis.questionnaire.domain.SectionType;
import omis.questionnaire.service.QuestionnaireSectionService;
import omis.questionnaire.service.delegate.QuestionnaireSectionDelegate;
import omis.questionnaire.service.delegate.SectionTypeDelegate;

/**
 * QuestionnaireSectionServiceImpl.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Nov 16, 2016)
 *@since OMIS 3.0
 *
 */
public class QuestionnaireSectionServiceImpl implements QuestionnaireSectionService {
	
	private final QuestionnaireSectionDelegate questionnaireSectionDelegate;
	
	private final SectionTypeDelegate sectionTypeDelegate;

	/**
	 * @param questionnaireSectionDelegate
	 */
	public QuestionnaireSectionServiceImpl(
			final QuestionnaireSectionDelegate questionnaireSectionDelegate,
			final SectionTypeDelegate sectionTypeDelegate) {
		this.questionnaireSectionDelegate = questionnaireSectionDelegate;
		this.sectionTypeDelegate = sectionTypeDelegate;
	}

	/**{@inheritDoc} */
	@Override
	public QuestionnaireSection create(
			final String title, final Short sortOrder,
			final Integer sectionNumber, final SectionType sectionType, 
			final String sectionHelp,
			final QuestionnaireType questionnaireType)
					throws DuplicateEntityFoundException {
		return this.questionnaireSectionDelegate.create(
				title, sortOrder, questionnaireType, sectionType, sectionNumber,
				sectionHelp);
	}

	/**{@inheritDoc} */
	@Override
	public QuestionnaireSection update(
			final QuestionnaireSection questionnaireSection,
			final String title, final Short sortOrder,
			final Integer sectionNumber, final SectionType sectionType,
			final String sectionHelp,
			final QuestionnaireType questionnaireType)
					throws DuplicateEntityFoundException {
		return this.questionnaireSectionDelegate.update(questionnaireSection,
				title, sortOrder, questionnaireType, sectionType, sectionNumber,
				sectionHelp);
	}

	/**{@inheritDoc} */
	@Override
	public List<SectionType> findAllSectionTypes() {
		return this.sectionTypeDelegate.findAll();
	}

	/**{@inheritDoc} */
	@Override
	public List<QuestionnaireSection> findAllByQuestionnaireType(
			final QuestionnaireType questionnaireType) {
		return this.questionnaireSectionDelegate
				.findByQuestionnaireType(questionnaireType);
	}

	/**{@inheritDoc} */
	@Override
	public Integer countSectionsByQuestionnaireType(
			final QuestionnaireType questionnaireType) {
		Integer count = this.questionnaireSectionDelegate
				.findByQuestionnaireType(questionnaireType).size();
		
		return count;
	}

	/**{@inheritDoc} */
	@Override
	public void remove(final QuestionnaireSection questionnaireSection) {
		this.questionnaireSectionDelegate.remove(questionnaireSection);
	}
	
	
}
