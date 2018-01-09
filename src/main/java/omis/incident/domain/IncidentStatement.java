package omis.incident.domain;

import java.util.Date;

import omis.audit.domain.Creatable;
import omis.audit.domain.Updatable;
import omis.incident.domain.component.IncidentScene;
import omis.person.domain.Person;
import omis.user.domain.UserAccount;

/**
 * Incident report
 * 
 * @author: Yidong Li
 * @author: Joel Norris
 * @version: 0.1.0 (June 20, 2015)
 * @since: OMIS 3.0
 */

public interface IncidentStatement extends Creatable, Updatable{
	
	/**
	 * Returns the incident report id.
	 * 
	 * @return incident report id
	 */
	Long getId();
		
	/** 
	 * Sets the incident report id.
	 * 
	 * @param incident id
	 */
	void setId(Long id);
	
	/**
	 * Returns the documenter.
	 * 
	 * @return documenter
	 */
	UserAccount getDocumenter();
	
	/**
	 * Sets the documenter.
	 * 
	 * @param documenter documenter
	 */
	void setDocumenter(UserAccount documenter);
	
	/**
	 * Returns the number
	 * @return the reporter
	 */
	String getNumber();
		
	/** 
	 * Sets the number
	 * @param number
	 */
	void setNumber(String number);
	
	/**
	 * Returns the title
	 * @return the title
	 */
	String getTitle();
		
	/** 
	 * Sets the title
	 * @param title
	 */
	void setTitle(String title);
	
	/**
	 * Returns the incident date
	 * @return the incident date
	 */
	Date getIncidentDate();
	
	/** 
	 * Sets the incident date
	 * @param incident date
	 */
	void setIncidentDate(final Date incidentDate);
	
	/**
	 * Returns the statement date
	 * @return the statement date
	 */
	Date getStatementDate();
		
	/** 
	 * Sets the statement date
	 * @param statement date
	 */
	void setStatementDate(Date statementDate);
	
	/**
	 * Returns the summary
	 * @return the summary
	 */
	String getSummary();
		
	/** 
	 * Sets the summary
	 * @param summary
	 */
	void setSummary(String summary);
	
	/**
	 * Returns the incident scene 
	 * @return the incident scene
	 */
	IncidentScene getScene();
		
	/** 
	 * Sets the incident scene
	 * @param incident scene
	 */
	void setScene(IncidentScene scene);
	
	/**
	 * Returns the jurisdiction.
	 * 
	 * @return jurisdiction
	 */
	Jurisdiction getJurisdiction();
	
	/**
	 * Sets the jurisdiction.
	 * 
	 * @param jurisdiction jurisdiction
	 */
	void setJurisdiction(Jurisdiction jurisdiction);
	
	/**
	 * Returns the incident statement category.
	 * 
	 * @return incident statement category
	 */
	IncidentStatementCategory getCategory();
	
	/**
	 * Sets the incident statement category.
	 * 
	 * @param category incident statmenet category
	 */
	void setCategory(IncidentStatementCategory category);
	
	/**
	 * Returns incident statement submission category.
	 * @return incident statement submission category
	 */
	IncidentStatementSubmissionCategory getSubmissionCategory();

	/**
	 * Sets incident statement submission category.
	 * 
	 * @param submissionCategory incident statement submission category
	 */
	void setSubmissionCategory(
			IncidentStatementSubmissionCategory submissionCategory);
	
	/**
	 * Returns whether confidential informant applies.
	 * 
	 * @return confidential informant
	 */
	Boolean getConfidentialInformant();
	
	/**
	 * Sets whether confidential informant applies.
	 * 
	 * @param confidentialInformant confidential informant
	 */
	void setConfidentialInformant(Boolean confidentialInformant);
	
	/**
	 * Returns the reporter.
	 * 
	 * @return reporter
	 */
	Person getReporter();
	
	/**
	 * Sets the reporter.
	 * 
	 * @param reporter reporter
	 */
	void setReporter(Person reporter);
	
	/**
	 * Returns the information source.
	 * 
	 * @return information source
	 */
	InformationSource getInformationSource();
	
	/**
	 * Sets the information source.
	 * 
	 * @param informationSource information source
	 */
	void setInformationSource(final InformationSource informationSource);
	
	/**
	 * Compares {@code this} and {@code obj} for equality.
	 * 
	 * <p>Any mandatory property may be used in the comparison. If a  mandatory
	 * property of {@code this} that is used in the comparison is {@code null}
	 * an {@code IllegalStateException} will be thrown.
	 * 
	 * @param obj reference object with which to compare {@code this}
	 * @return {@code true} if {@code this} and {@code obj} are equal;
	 * {@code false} otherwise
	 * @throws IllegalStateException if a mandatory property of {@code this}
	 * that is used in the comparison is {@code null} 
	 */
	@Override
	boolean equals(Object obj);
	
	/**
	 * Returns a hash code for {@code this}.
	 * 
	 * <p>Any mandatory property of {@code this} may be used in the hash code.
	 * If a mandatory property that is used in the hash code is {@code null} an
	 * {@code IllegalStateException} will be thrown.
	 * 
	 * @return hash code
	 * @throws IllegalStateException if a mandatory property of {@code this}
	 * that is used in the hash code is {@code null}
	 */
	@Override
	int hashCode();
}