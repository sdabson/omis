package omis.need.web.form;

import java.io.Serializable;
import java.util.Date;

import omis.need.domain.NeedDomain;
import omis.need.domain.ObjectivePriority;
import omis.need.domain.ObjectiveSource;
import omis.person.domain.Person;

/**
 * Case plan objective form.
 * 
 * @author Joel Norris
 * @version 0.1.0 (Jul 14, 2015)
 * @since OMIS 3.0
 */
public class CasePlanObjectiveForm implements Serializable {

	private static final long serialVersionUID = 1L;

	private String name;
	
	private NeedDomain domain;
	
	private ObjectivePriority priority;
	
	private ObjectiveSource source;
	
	private Date identifiedDate;
	
	private Person staffMember;
	
	private String comment;
	
	private Boolean offenderAgrees;
	
	/**
	 * Instantiates a default instance of case 
	 */
	public CasePlanObjectiveForm() {
		//Default constructor.
	}

	/**
	 * Returns the name.
	 * 
	 * @return name
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Sets the name.
	 * 
	 * @param name name
	 */
	public void setName(final String name) {
		this.name = name;
	}

	/**
	 * Returns the domain.
	 * 
	 * @return need domain
	 */
	public NeedDomain getDomain() {
		return this.domain;
	}

	/**
	 * Sets the domain.
	 * 
	 * @param domain need domain
	 */
	public void setDomain(final NeedDomain domain) {
		this.domain = domain;
	}

	/**
	 * Returns the objective priority.
	 * 
	 * @return objective priority
	 */
	public ObjectivePriority getPriority() {
		return this.priority;
	}

	/**
	 * Sets the objective priority.
	 * 
	 * @param priority objective priority
	 */
	public void setPriority(final ObjectivePriority priority) {
		this.priority = priority;
	}

	/**
	 * Returns the objective source.
	 * 
	 * @return objective source
	 */
	public ObjectiveSource getSource() {
		return this.source;
	}

	public void setSource(ObjectiveSource source) {
		this.source = source;
	}

	/**
	 * Returns the identified date.
	 * 
	 * @return identified date
	 */
	public Date getIdentifiedDate() {
		return this.identifiedDate;
	}

	/**
	 * Sets the identified date.
	 * 
	 * @param identifiedDate identified date
	 */
	public void setIdentifiedDate(final Date identifiedDate) {
		this.identifiedDate = identifiedDate;
	}

	/**
	 * Returns the staff member.
	 * 
	 * @return staff member
	 */
	public Person getStaffMember() {
		return this.staffMember;
	}

	/**
	 * Sets the staff member.
	 * 
	 * @param staffMember staff member
	 */
	public void setStaffMember(final Person staffMember) {
		this.staffMember = staffMember;
	}

	/**
	 * Returns the comment.
	 * 
	 * @return comment
	 */
	public String getComment() {
		return this.comment;
	}

	/**
	 * Sets the comment.
	 * 
	 * @param comment comment
	 */
	public void setComment(final String comment) {
		this.comment = comment;
	}
	
	/**
	 * Gets whether offender agrees applies.
	 * 
	 * @return offender agrees
	 */
	public Boolean getOffenderAgrees() {
		return this.offenderAgrees;
	}

	/**
	 * Sets whether offender agrees applies.
	 * 
	 * @param offenderAgrees offender agrees
	 */
	public void setOffenderAgrees(final Boolean offenderAgrees) {
		this.offenderAgrees = offenderAgrees;
	}
}