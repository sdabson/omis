package omis.incident.domain.impl;

import java.util.Date;

import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.incident.domain.IncidentStatementNote;
import omis.incident.domain.IncidentStatement;

/**
* Implementation of incident note
* @author Yidong Li
* @author Joel Norris
* @version 0.1.0 (June 20, 2015)
* @since OMIS 3.0
*/
public class IncidentStatementNoteImpl implements IncidentStatementNote {
	private static final long serialVersionUID = 1L;
	private Long id;
	private Date date;
	private String note;
	private IncidentStatement statement;
	private CreationSignature creationSignature;
	private UpdateSignature updateSignature;
	
	/* Constructor */
	public IncidentStatementNoteImpl(){
		// Do nothing
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
	public Date getDate(){
		return this.date;
	}
	
	/** {@inherDoc} */
	@Override
	public void setDate(final Date date){
		this.date = date;
	}
	
	/** {@inherDoc} */
	@Override
	public String getNote(){
		return this.note;
	}
	
	/** {@inherDoc} */
	@Override
	public void setNote(final String note){
		this.note = note;
	}
	
	/** {@inherDoc} */
	@Override
	public IncidentStatement getStatement(){
		return this.statement;
	}
	
	/** {@inherDoc} */
	@Override
	public void setStatement(final IncidentStatement statement){
		this.statement = statement;
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
		if(!(obj instanceof IncidentStatementNote)){
			return false;
		}
		IncidentStatementNote that = (IncidentStatementNote)obj;
		if(this.getDate()==null){
			throw new IllegalStateException("Date required");
		}
		if(!(this.getDate().equals(that.getDate()))){
			return false;
		}
		if(this.getNote()==null){
			throw new IllegalStateException("Note required");
		}
		if(!(this.getNote().equals(that.getNote()))){
			return false;
		}
		if(this.getStatement()==null){
			throw new IllegalStateException("Statement required");
		}
		if(!(this.getStatement().equals(that.getStatement()))){
			return false;
		}
		return true;
	}
	
	/** {@ihheriDoc} */
	@Override
	public int hashCode(){
		int hashCode = 7;
		if(this.getDate()==null){
			throw new IllegalStateException("Date required");
		}
		if(this.getNote()==null){
			throw new IllegalStateException("Note required");
		}
		if(this.getStatement()==null){
			throw new IllegalStateException("Statement required");
		}
		
		hashCode += 14*this.getDate().hashCode();
		hashCode += 14*this.getNote().hashCode();
		hashCode += 14*this.getStatement().hashCode();
		return hashCode;
	}
}