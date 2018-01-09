package omis.staff.service.impl;

import java.util.List;

import omis.exception.DuplicateEntityFoundException;
import omis.staff.domain.StaffTitle;
import omis.staff.service.StaffTitleService;
import omis.staff.service.delegate.StaffTitleDelegate;

/**
 * Implementation of service for staff titles.
 * 
 * @author Stephen Abson
 * @author Josh Divine
 * @version 0.1.1 (Nov 9, 2017)
 * @since OMIS 3.0
 */
public class StaffTitleServiceImpl
		implements StaffTitleService {
	
	private final StaffTitleDelegate staffTitleDelegate;
	
	/**
	 * Instantiates an implementation of service for staff titles.
	 * 
	 * @param staffTitleDelegate delegate for staff titles
	 */
	public StaffTitleServiceImpl(final StaffTitleDelegate staffTitleDelegate) {
		this.staffTitleDelegate = staffTitleDelegate;
	}
	
	/** {@inheritDoc} */
	@Override
	public List<StaffTitle> findAll() {
		return this.staffTitleDelegate.findAll();
	}

	/** {@inheritDoc} */
	@Override
	public StaffTitle create(final String name, final Short sortOrder,
			final Boolean valid) throws DuplicateEntityFoundException {
		return this.staffTitleDelegate.create(name, sortOrder, valid);
	}

	/** {@inheritDoc} */
	@Override
	public StaffTitle update(final StaffTitle staffTitle, final String name,
			final Short sortOrder, final Boolean valid)
					throws DuplicateEntityFoundException{
		return this.staffTitleDelegate.update(staffTitle, name, sortOrder, 
				valid);
	}
	
	/** {@inheritDoc} */
	@Override
	public void remove(final StaffTitle staffTitle) {
		this.staffTitleDelegate.remove(staffTitle);
	}

	/** {@inheritDoc} */
	@Override
	public short findHighestSortOrder() {
		return this.staffTitleDelegate.findHighestSortOrder();
	}
}