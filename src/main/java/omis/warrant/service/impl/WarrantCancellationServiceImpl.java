package omis.warrant.service.impl;

import java.util.Date;

import omis.exception.DuplicateEntityFoundException;
import omis.person.domain.Person;
import omis.warrant.domain.Warrant;
import omis.warrant.domain.WarrantCancellation;
import omis.warrant.service.WarrantCancellationService;
import omis.warrant.service.delegate.WarrantCancellationDelegate;

/**
 * WarrantCancellationServiceImpl.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (May 9, 2017)
 *@since OMIS 3.0
 *
 */
public class WarrantCancellationServiceImpl implements WarrantCancellationService {
	
	private final WarrantCancellationDelegate warrantCancellationDelegate;
	
	/**
	 * @param warrantCancellationDelegate
	 */
	public WarrantCancellationServiceImpl(
			final WarrantCancellationDelegate warrantCancellationDelegate) {
		this.warrantCancellationDelegate = warrantCancellationDelegate;
	}

	/**{@inheritDoc} */
	@Override
	public WarrantCancellation create(
			final Warrant warrant, final Date date, final String comment,
			final Person clearedBy, final Date clearedByDate)
					throws DuplicateEntityFoundException {
		return this.warrantCancellationDelegate.create(
				warrant, date, comment, clearedBy, clearedByDate);
	}

	/**{@inheritDoc} */
	@Override
	public WarrantCancellation update(
			final WarrantCancellation warrantCancellation, final Date date,
			final String comment, final Person clearedBy, final Date clearedByDate)
					throws DuplicateEntityFoundException {
		return this.warrantCancellationDelegate.update(
				warrantCancellation, date, comment, clearedBy, clearedByDate);
	}

	/**{@inheritDoc} */
	@Override
	public void remove(final WarrantCancellation warrantCancellation) {
		this.warrantCancellationDelegate.remove(warrantCancellation);
	}

	/**{@inheritDoc} */
	@Override
	public WarrantCancellation findByWarrant(final Warrant warrant) {
		return this.warrantCancellationDelegate.findByWarrant(warrant);
	}

}
