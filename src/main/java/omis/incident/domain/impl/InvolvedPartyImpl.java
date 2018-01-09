package omis.incident.domain.impl;

import omis.audit.domain.VerificationSignature;
import omis.incident.domain.IncidentStatement;
import omis.incident.domain.InvolvedParty;
import omis.incident.domain.InvolvedPartyCategory;
import omis.person.domain.Person;

/**
 * Involved party implementation.
 * 
 * @author Yidong Li
 * @author Joel Norris
 * @version 0.1.0 (Oct 1, 2015)
 * @since OMIS 3.0
 */
public class InvolvedPartyImpl implements InvolvedParty {
	private static final long serialVersionUID = 1L;
	private Long id;
	private Person person;
	private VerificationSignature verification;
	private IncidentStatement statement;
	private InvolvedPartyCategory category;
	private String narrative;
	private String name;
	
	/* Constructor */
	public InvolvedPartyImpl(){
		// Do nothing
	}
	
	/** {@inheritDoc} */
	@Override
	public Long getId(){
		return this.id;
	}
	
	/** {@inherDoc} */
	@Override
	public void setId(final Long id){
		this.id = id;
	}
	
	/** {@inheritDoc} */
	@Override
	public Person getPerson(){
		return this.person;
	}
	
	/** {@inherDoc} */
	@Override
	public void setPerson(final Person person){
		this.person = person;
	}
	
	/** {@inheritDoc} */
	@Override
	public  VerificationSignature getVerificationSignature(){
		return this.verification;
	}
	
	/** {@inherDoc} */
	@Override
	public void setVerificationSignature(final VerificationSignature verification){
		this.verification = verification;
	}
	
	/** {@inheritDoc} */
	@Override
	public IncidentStatement getStatement(){
		return this.statement;
	}
	
	/** {@inherDoc} */
	@Override
	public void setStatement(final IncidentStatement statement){
		this.statement = statement;
	}
	
	/** {@inherDoc} */
	@Override
	public String getNarrative() {
		return this.narrative;
	}

	/** {@inherDoc} */
	@Override
	public void setNarrative(final String narrative) {
		this.narrative = narrative;
	}

	/** {@inheritDoc} */
	@Override
	public InvolvedPartyCategory getCategory(){
		return this.category;
	}
	
	/** {@inherDoc} */
	@Override
	public void setCategory(final InvolvedPartyCategory category){
		this.category = category;
	}
	
	/** {@inherDoc} */
	@Override
	public String getName() {
		return this.name;
	}

	/** {@inherDoc} */
	@Override
	public void setName(final String name) {
		this.name = name;
	}

	/** {@inherDoc} */
	@Override
	public boolean equals(Object obj){
		if(this==obj){
			return true;
		}
		if(!(obj instanceof InvolvedParty)){
			return false;
		}
		
		InvolvedParty that = (InvolvedParty)obj;
		if (this.getPerson() != null) {
			if (!this.getPerson().equals(that.getPerson())) {
				return false;
			}
		} else if (that.getPerson() != null) {
			return false;
		}
		if(this.getStatement()==null){
			throw new IllegalStateException("Statement required");
		}
		if(!(this.getStatement().equals(that.getStatement()))){
			return false;
		}
		if(this.getCategory()==null){
			throw new IllegalStateException("Category required");
		}
		if(!(this.getCategory().equals(that.getCategory()))){
			return false;
		}
		if(this.getVerificationSignature().getResult()==null){
			throw new IllegalStateException("Verification result required");
		}
		if(this.getVerificationSignature().getMethod()==null){
			throw new IllegalStateException("Verification method required");
		}
		if (this.getName() != null) {
			if (!this.getName().equals(that.getName())) {
				return false;
			}
		} else if (that.getName() != null) {
			return false;
		}
		return true;
	}
	
	/** {@inherDoc} */
	@Override
	public int hashCode(){
		int hashCode = 7;
		if(this.getPerson()==null){
			throw new IllegalStateException("Person required");
		}
		if(this.getStatement()==null){
			throw new IllegalStateException("Statement required");
		}
		if(this.getCategory()==null){
			throw new IllegalStateException("Category required");
		}
		if(this.getVerificationSignature().getResult()==null){
			throw new IllegalStateException("Result required");
		}
		if(this.getVerificationSignature().getMethod()==null){
			throw new IllegalStateException("Method required");
		}
		
		hashCode += 14*this.getPerson().hashCode();
		hashCode += 14*this.getStatement().hashCode();
		hashCode += 14*this.getCategory().hashCode();
		return hashCode;
	}
}