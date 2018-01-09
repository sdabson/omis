package omis.questionnaire.domain;

/**
 * QuestionCategory.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Sep 7, 2016)
 *@since OMIS 3.0
 *
 */
public enum QuestionCategory {
	
	TRUE_FALSE,
	
	MULTIPLE_CHOICE,
	
	MULTIPLE_CHOICE_ESSAY,
	
	ESSAY,
	
	SELECT_FROM_LIST,
	
	DATE,
	
	PHONE_NUMBER,
	
	CURRENCY,
	
	WHOLE_NUMBER,
	
	DECIMAL_NUMBER,
	
	SHORT_ANSWER;
	
	/**
	 * Returns the instance name.
	 * 
	 * @return instance name
	 */
	public String getName() {
		return this.name();
	}
	
	/**
	 * Returns the name.
	 * 
	 * @return name
	 */
	
	@Override
	public String toString() {
		return this.name();
	}
	
	
}
