package omis.supervisionfee.domain.impl;

import omis.datatype.DateRange;
import omis.offender.domain.Offender;
import omis.supervisionfee.domain.SupervisionFeeTransaction;

/**
 * Supervision fee transaction implementation.
 * 
 * @author Sheronda Vaughn
 * @version 0.1.0 (Aug 27, 2014)
 * @since OMIS 3.0
 */
public class SupervisionFeeTransactionImpl 
		implements SupervisionFeeTransaction {

	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	private DateRange date;
	
	private Integer amount;
	
	private Offender offender;

	/**{@inheritDoc} */
	@Override
	public Long getId() {
		return this.id;
	}

	/**{@inheritDoc} */
	@Override
	public void setId(Long id) {
		this.id = id;
	}

	/**{@inheritDoc} */
	@Override
	public DateRange getDate() {
		return this.date;
	}

	/**{@inheritDoc} */
	@Override
	public void setDate(DateRange date) {
		this.date = date;
	}

	/**{@inheritDoc} */
	@Override
	public Integer getAmount() {
		return this.amount;
	}

	/**{@inheritDoc} */
	@Override
	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	/**{@inheritDoc} */
	@Override
	public Offender getOffender() {
		return this.offender;
	}

	/**{@inheritDoc} */
	@Override
	public void setOffender(Offender offender) {
		this.offender = offender;
	}	
	/** {@inheritDoc} */
	@Override
	public boolean equals(final Object obj) {
		//TODO unimplemented method
		throw new UnsupportedOperationException(
				"Operation not supported...Method not yet completed.");
	}

	/** {@inheritDoc} */
	@Override
	public int hashCode() {
		//TODO unimplemented method
		throw new UnsupportedOperationException(
				"Operation not supported...Method not yet completed.");
	}
}