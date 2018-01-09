package omis.need.domain.impl;

import java.util.Date;

import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.need.domain.CasePlanObjective;
import omis.need.domain.NeedDomain;
import omis.need.domain.ObjectivePriority;
import omis.need.domain.ObjectiveSource;
import omis.offender.domain.Offender;
import omis.person.domain.Person;

/**
 * Case plan objective implementation.
 * 
 * @author Joel Norris
 * @version 0.1.0 (Jun 23, 2015)
 * @since OMIS 3.0
 */
public class CasePlanObjectiveImpl implements CasePlanObjective {

	private static final long serialVersionUID = 1L;

	private Long id;
	
	private Offender offender;
	
	private Date identificationDate;
	
	private String name;
	
	private String comment;
	
	private ObjectiveSource source;
	
	private ObjectivePriority priority;
	
	private Person staffMember;
	
	private NeedDomain domain;
	
	private Boolean offenderAgrees;
	
	private CreationSignature creationSignature;
	
	private UpdateSignature updateSignature;
	
	/**
	 * Instantiates a default instance of case plan objective.
	 */
	public CasePlanObjectiveImpl() {
		//Default constructor.
	}

	/** {@inheritDoc} */
	@Override
	public Long getId() {
		return this.id;
	}

	/** {@inheritDoc} */
	@Override
	public void setId(final Long id) {
		this.id = id;
	}

	/** {@inheritDoc} */
	@Override
	public Offender getOffender() {
		return this.offender;
	}

	/** {@inheritDoc} */
	@Override
	public void setOffender(final Offender offender) {
		this.offender = offender;
	}

	/** {@inheritDoc} */
	@Override
	public Date getIdentificationDate() {
		return this.identificationDate;
	}

	/** {@inheritDoc} */
	@Override
	public void setIdentificationDate(final Date identificationDate) {
		this.identificationDate = identificationDate;
	}

	/** {@inheritDoc} */
	@Override
	public String getName() {
		return this.name;
	}

	/** {@inheritDoc} */
	@Override
	public void setName(final String name) {
		this.name = name;
	}

	/** {@inheritDoc} */
	@Override
	public String getComment() {
		return this.comment;
	}

	/** {@inheritDoc} */
	@Override
	public void setComment(final String comment) {
		this.comment = comment;
	}

	/** {@inheritDoc} */
	@Override
	public ObjectiveSource getSource() {
		return this.source;
	}

	/** {@inheritDoc} */
	@Override
	public void setSource(final ObjectiveSource source) {
		this.source = source;
	}

	/** {@inheritDoc} */
	@Override
	public ObjectivePriority getPriority() {
		return this.priority;
	}

	/** {@inheritDoc} */
	@Override
	public void setPriority(final ObjectivePriority priority) {
		this.priority = priority;
	}

	/** {@inheritDoc} */
	@Override
	public Person getStaffMember() {
		return this.staffMember;
	}

	/** {@inheritDoc} */
	@Override
	public void setStaffMember(final Person staffMember) {
		this.staffMember = staffMember;
	}

	/** {@inheritDoc} */
	@Override
	public NeedDomain getDomain() {
		return this.domain;
	}

	/** {@inheritDoc} */
	@Override
	public void setDomain(final NeedDomain domain) {
		this.domain = domain;
	}

	/** {@inheritDoc} */
	@Override
	public Boolean getOffenderAgrees() {
		return this.offenderAgrees;
	}

	/** {@inheritDoc} */
	@Override
	public void setOffenderAgrees(final Boolean offenderAgrees) {
		this.offenderAgrees = offenderAgrees;
	}

	/** {@inheritDoc} */
	@Override
	public CreationSignature getCreationSignature() {
		return this.creationSignature;
	}

	/** {@inheritDoc} */
	@Override
	public void setCreationSignature(final CreationSignature creationSignature) {
		this.creationSignature = creationSignature;
	}

	/** {@inheritDoc} */
	@Override
	public UpdateSignature getUpdateSignature() {
		return this.updateSignature;
	}

	/** {@inheritDoc} */
	@Override
	public void setUpdateSignature(final UpdateSignature updateSignature) {
		this.updateSignature = updateSignature;
	}
}