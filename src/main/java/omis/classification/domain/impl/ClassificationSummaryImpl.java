/**
 * 
 */
package omis.classification.domain.impl;

import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.classification.domain.ClassificationSummary;
import omis.datatype.DateRange;
import omis.offender.domain.Offender;

/**
 * Classification Summary Implementation.
 * 
 * @author Joel Norris (Feb, 07 2013)
 * @version 0.1.0
 * @since OMIS 3.0
 */
public class ClassificationSummaryImpl implements ClassificationSummary {

	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	private Integer totalScore;
	
	private Integer custodyLevel;
	
	private DateRange classificationPeriod;
	
	private Boolean override;
	
	private String comment;
	
	private Offender offender;

	private UpdateSignature updateSignature;
	
	private CreationSignature creationSignature;
	
	/** {@inheritDoc} */
	@Override
	public void setUpdateSignature(final UpdateSignature updateSignature) {
		this.updateSignature = updateSignature;
	}

	/** {@inheritDoc} */
	@Override
	public UpdateSignature getUpdateSignature() {
		return this.updateSignature;
	}

	/** {@inheritDoc} */
	@Override
	public void setCreationSignature(
			final CreationSignature creationSignature) {
		this.creationSignature = creationSignature;
	}

	/** {@inheritDoc} */
	@Override
	public CreationSignature getCreationSignature() {
		return this.creationSignature;
	}

	/** {@inheritDoc} */
	@Override
	public void setOffender(final Offender offender) {
		this.offender = offender;
	}

	/** {@inheritDoc} */
	@Override
	public Offender getOffender() {
		return this.offender;
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
	public Integer getTotalScore() {
		return this.totalScore;
	}

	/** {@inheritDoc} */
	@Override
	public void setTotalScore(final Integer totalScore) {
		this.totalScore = totalScore;
	}

	/** {@inheritDoc} */
	@Override
	public Integer getCustodyLevel() {
		return this.custodyLevel;
	}

	/** {@inheritDoc} */
	@Override
	public void setCustodyLevel(final Integer custodyLevel) {
		this.custodyLevel = custodyLevel;
	}

	/** {@inheritDoc} */
	@Override
	public DateRange getClassificationPeriod() {
		return this.classificationPeriod;
	}

	/** {@inheritDoc} */
	@Override
	public void setClassificationPeriod(final DateRange classificationPeriod) {
		this.classificationPeriod = classificationPeriod;
	}

	/** {@inheritDoc} */
	@Override
	public Boolean getOverride() {
		return this.override;
	}

	/** {@inheritDoc} */
	@Override
	public void setOverride(final Boolean override) {
		this.override = override;
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
}