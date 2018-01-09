package omis.substancetest.domain.impl;

import java.util.Date;

import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.person.domain.Person;
import omis.substance.domain.SubstanceLab;
import omis.substancetest.domain.SubstanceTest;
import omis.substancetest.domain.SubstanceTestSample;

/**
 * Substance Test Impl.
 * 
 * @author Joel Norris
 * @version 0.1.0 (Jun 4, 2013)
 * @since OMIS 3.0
 */
public class SubstanceTestImpl implements SubstanceTest {

	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	private String testComment;
	
	private SubstanceTestSample substanceTestSample;
	
	private Boolean labInvolved;
	
	private Date resultDate;
	
	private Date labResultDate;
	
	private Date labRequestDate;
	
	private SubstanceLab lab;
	
	private String privateLabJustification;
	
	private Person authorizingStaff;
	
	private CreationSignature creationSignature;
	
	private UpdateSignature updateSignature;
	
	/**
	 * Instantiates a default instance of substance test implementation.
	 */
	public SubstanceTestImpl() {
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
	public String getTestComment() {
		return this.testComment;
	}

	/** {@inheritDoc} */
	@Override
	public void setTestComment(final String testComment) {
		this.testComment = testComment;
	}

	/** {@inheritDoc} */
	@Override
	public SubstanceTestSample getSubstanceTestSample() {
		return this.substanceTestSample;
	}

	/** {@inheritDoc} */
	@Override
	public void setSubstanceTestSample(
			final SubstanceTestSample substanceTestSample) {
		this.substanceTestSample = substanceTestSample;
	}

	/** {@inheritDoc} */
	@Override
	public Boolean getLabInvolved() {
		return labInvolved;
	}

	/** {@inheritDoc} */
	@Override
	public void setLabInvolved(Boolean labInvolved) {
		this.labInvolved = labInvolved;
	}

	/** {@inheritDoc} */
	@Override
	public Date getLabResultDate() {
		return labResultDate;
	}

	/** {@inheritDoc} */
	@Override
	public void setLabResultDate(Date labResultDate) {
		this.labResultDate = labResultDate;
	}

	/** {@inheritDoc} */
	@Override
	public Date getLabRequestDate() {
		return labRequestDate;
	}

	/** {@inheritDoc} */
	@Override
	public void setLabRequestDate(Date labRequestDate) {
		this.labRequestDate = labRequestDate;
	}

	/** {@inheritDoc} */
	@Override
	public Date getResultDate() {
		return this.resultDate;
	}

	/** {@inheritDoc} */
	@Override
	public void setResultDate(final Date resultDate) {
		this.resultDate = resultDate;
	}

	/** {@inheritDoc} */
	@Override
	public SubstanceLab getLab() {
		return this.lab;
	}

	/** {@inheritDoc} */
	@Override
	public void setLab(final SubstanceLab lab) {
		this.lab = lab;
	}

	/** {@inheritDoc} */
	@Override
	public String getPrivateLabJustification() {
		return this.privateLabJustification;
	}

	/** {@inheritDoc} */
	@Override
	public void setPrivateLabJustification(
			final String privateLabJustification) {
		this.privateLabJustification = privateLabJustification;
	}
	
	/** {@inheritDoc} */
	@Override
	public Person getAuthorizingStaff() {
		return this.authorizingStaff;
	}

	/** {@inheritDoc} */
	@Override
	public void setAuthorizingStaff(final Person authorizingStaff) {
		this.authorizingStaff = authorizingStaff;
	}

	/** {@inheritDoc} */
	@Override
	public CreationSignature getCreationSignature() {
		return this.creationSignature;
	}

	/** {@inheritDoc} */
	@Override
	public void setCreationSignature(
			final CreationSignature creationSignature) {
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
	
	/** {@inheritDoc} */
	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof SubstanceTest)) {
			return false;
		}
		
		SubstanceTest that = (SubstanceTest) obj;
		
		if (this.getSubstanceTestSample() == null) {
			throw new IllegalStateException("Sample required.");
		}
		if (!this.getSubstanceTestSample().equals(
				that.getSubstanceTestSample())) {
			return false;
		}
		return true;
	}
	
	/** {@inheritDoc} */
	@Override
	public int hashCode() {
		if (this.getSubstanceTestSample() == null) {
			throw new IllegalStateException("Sample required.");
		}
		int hashCode = 14;
		hashCode = hashCode * 29 + this.getSubstanceTestSample().hashCode();
		return hashCode;
	}
}