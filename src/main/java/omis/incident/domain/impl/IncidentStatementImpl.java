package omis.incident.domain.impl;

import java.util.Date;

import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.incident.domain.IncidentStatement;
import omis.incident.domain.IncidentStatementCategory;
import omis.incident.domain.IncidentStatementSubmissionCategory;
import omis.incident.domain.InformationSource;
import omis.incident.domain.Jurisdiction;
import omis.incident.domain.component.IncidentScene;
import omis.person.domain.Person;
import omis.user.domain.UserAccount;


/**
* Implementation of incident report.
* 
* @author Yidong Li
* @author Joel Norris
* @version 0.1.0 (June 20, 2015)
* @since OMIS 3.0
*/
public class IncidentStatementImpl implements IncidentStatement {
	
	private static final long serialVersionUID = 1L;
	private Long id;
	private UserAccount documenter;
	private String number;
	private String title;
	private Date incidentDate;
	private Date statementDate;
	private String summary;
	private IncidentScene scene;
	private Jurisdiction jurisdiction;
	private IncidentStatementCategory category;
	private IncidentStatementSubmissionCategory submissionCategory;
	private Boolean confidentialInformant;
	private Person reporter;
	private InformationSource informationSource;
	private CreationSignature creationSignature;
	private UpdateSignature updateSignature;
	
	/* Constructor */
	
	/**
	 * Instantiates a default instance of incident report.
	 */
	public IncidentStatementImpl(){
		// Default constructor.
	}
	
	/** {@inherDoc} */
	@Override
	public Long getId(){
		return this.id;
	}
	
	/** {@inherDoc} */
	@Override
	public void setId(final Long id){
		this.id = id;
	}
	
	/** {@inherDoc} */
	@Override
	public UserAccount getDocumenter() {
		return this.documenter;
	}

	/** {@inherDoc} */
	@Override
	public void setDocumenter(final UserAccount documenter) {
		this.documenter = documenter;
	}

	/** {@inherDoc} */
	@Override
	public String getNumber(){
		return this.number;
	}
	
	/** {@inherDoc} */
	@Override
	public void setNumber(final String number){
		this.number = number;
	}
	
	/** {@inherDoc} */
	@Override
	public String getTitle(){
		return this.title;
	}
	
	/** {@inherDoc} */
	@Override
	public void setTitle(final String title){
		this.title = title;
	}
	
	/** {@inherDoc} */
	@Override
	public Date getStatementDate(){
		return this.statementDate;
	}
	
	/** {@inherDoc} */
	@Override
	public void setStatementDate(final Date statementDate){
		this.statementDate = statementDate;
	}
	
	/** {@inherDoc} */
	@Override
	public Date getIncidentDate(){
		return this.incidentDate;
	}
	
	/** {@inherDoc} */
	@Override
	public void setIncidentDate(final Date incidentDate){
		this.incidentDate = incidentDate;
	}
	
	/** {@inherDoc} */
	@Override
	public String getSummary(){
		return this.summary;
	}
	
	/** {@inherDoc} */
	@Override
	public void setSummary(final String summary){
		this.summary = summary;
	}
	
	/** {@inherDoc} */
	@Override
	public IncidentScene getScene(){
		return this.scene;
	}
	
	/** {@inherDoc} */
	@Override
	public void setScene(final IncidentScene scene){
		this.scene = scene;
	}
	
	/** {@inherDoc} */
	@Override
	public Jurisdiction getJurisdiction() {
		return this.jurisdiction;
	}
	
	/** {@inherDoc} */
	@Override
	public void setJurisdiction(final Jurisdiction jurisdiction) {
		this.jurisdiction = jurisdiction;
	}
	
	/** {@inherDoc} */
	@Override
	public IncidentStatementCategory getCategory() {
		return this.category;
	}

	/** {@inherDoc} */
	@Override
	public void setCategory(final IncidentStatementCategory category) {
		this.category = category;
	}

	/** {@inherDoc} */
	@Override
	public IncidentStatementSubmissionCategory getSubmissionCategory() {
		return submissionCategory;
	}

	/** {@inherDoc} */
	@Override
	public void setSubmissionCategory(
			IncidentStatementSubmissionCategory submissionCategory) {
		this.submissionCategory = submissionCategory;
	}

	/** {@inherDoc} */
	@Override
	public Boolean getConfidentialInformant() {
		return this.confidentialInformant;
	}
	
	/** {@inherDoc} */
	@Override
	public void setConfidentialInformant(final Boolean confidentialInformant) {
		this.confidentialInformant = confidentialInformant;
	}
	
	/** {@inherDoc} */
	@Override
	public Person getReporter() {
		return this.reporter;
	}

	/** {@inherDoc} */
	@Override
	public void setReporter(final Person reporter) {
		this.reporter = reporter;
	}

	/** {@inherDoc} */
	@Override
	public InformationSource getInformationSource() {
		return this.informationSource;
	}

	/** {@inherDoc} */
	@Override
	public void setInformationSource(
			final InformationSource informationSource) {
		this.informationSource = informationSource;
	}

	/**{@inheritDoc}*/
	@Override
	public CreationSignature getCreationSignature(){
		return this.creationSignature;
	}
	
	/**{@inheritDoc}*/
	@Override
	public void setCreationSignature(final CreationSignature creationSignature) {
		this.creationSignature = creationSignature;
	}

	/**{@inheritDoc}*/
	@Override
	public UpdateSignature getUpdateSignature() {
		return this.updateSignature;
	}

	/**{@inheritDoc}*/
	@Override
	public void setUpdateSignature(final UpdateSignature updateSignature) {
		this.updateSignature = updateSignature;
	}
	
	/** {@inheriDoc} */
	@Override
	public boolean equals(Object obj){
		if(this==obj){
			return true;
		}
		if(!(obj instanceof IncidentStatement)){
			return false;
		}
		IncidentStatement that = (IncidentStatement)obj;
		
		if(this.getDocumenter() == null) {
			throw new IllegalStateException("Documenter required");
		}
		if(this.getDocumenter().equals(that.getDocumenter())) {
			return false;
		}
		if(this.getIncidentDate()==null){
			throw new IllegalStateException("Incident date required");
		}
		if(!(this.getIncidentDate().equals(that.getIncidentDate()))){
			return false;
		}
		if(this.getNumber() == null) {
			throw new IllegalStateException("Incident report number required");
		}
		if(!(this.getNumber().equals(that.getNumber()))) {
			return false;
		}
		if(this.getJurisdiction() == null) {
			throw new IllegalStateException("Jurisdiction required");
		}
		if(!(this.getJurisdiction().equals(that.getJurisdiction()))) {
			return false;
		}
		if(this.getSummary()==null){
			throw new IllegalStateException("Summary required");
		}
		if(!(this.getSummary().equals(that.getSummary()))) {
			return false;
		}
		return true;
	}
	
	/** {@ihheriDoc} */
	@Override
	public int hashCode(){
		int hashCode = 7;
		if(this.getNumber() == null) {
			throw new IllegalStateException("Report number required");
		}
		if(this.getDocumenter() == null) {
			throw new IllegalStateException("Documenter required");
		}
		if(this.getIncidentDate()==null){
			throw new IllegalStateException("Incident date required");
		}
		if(this.getStatementDate()==null){
			throw new IllegalStateException("Statement date required");
		}
		if(this.getSummary()==null){
			throw new IllegalStateException("Summary required");
		}
		
		hashCode += 14*this.getNumber().hashCode();
		hashCode += 14*this.getDocumenter().hashCode();
		hashCode += 14*this.getStatementDate().hashCode();
		hashCode += 14*this.getIncidentDate().hashCode();
		
		return hashCode;
	}
}