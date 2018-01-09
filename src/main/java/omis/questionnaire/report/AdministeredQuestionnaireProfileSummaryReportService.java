package omis.questionnaire.report;

import omis.person.domain.Person;

/**
 * AdministeredQuestionnaireProfileSummaryReportService.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Feb 23, 2017)
 *@since OMIS 3.0
 *
 */
public interface AdministeredQuestionnaireProfileSummaryReportService {
	
	/** Find administered questionnaire count by answerer.
	 * @param answerer - Person
	 * @return administered questionnaire count. */
	Integer findCountByAnswerer(final Person answerer);
	
}
