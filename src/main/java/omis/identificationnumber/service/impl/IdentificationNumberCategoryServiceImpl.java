package omis.identificationnumber.service.impl;

import omis.exception.DuplicateEntityFoundException;
import omis.identificationnumber.domain.IdentificationNumberCategory;
import omis.identificationnumber.domain.Multitude;
import omis.identificationnumber.service.IdentificationNumberCategoryService;
import omis.identificationnumber.service.delegate.IdentificationNumberCategoryDelegate;

/**
 * IdentificationNumberCategoryServiceImpl.java
 * 
 *@author Annie Jacques 
 *@version 0.1.1 (Nov 1, 2017)
 *@since OMIS 3.0
 *
 */
public class IdentificationNumberCategoryServiceImpl
		implements IdentificationNumberCategoryService {
	
	private final IdentificationNumberCategoryDelegate
			identificationNumberCategoryDelegate;
	
	/**
	 * @param identificationNumberCategoryDelegate
	 */
	public IdentificationNumberCategoryServiceImpl(
			final IdentificationNumberCategoryDelegate
				identificationNumberCategoryDelegate) {
		this.identificationNumberCategoryDelegate =
				identificationNumberCategoryDelegate;
	}

	/**{@inheritDoc} */
	@Override
	public IdentificationNumberCategory create(
			final String name, final Multitude multitude, final Boolean valid)
					throws DuplicateEntityFoundException {
		return this.identificationNumberCategoryDelegate.create(
				name, multitude, valid);
	}

	/**{@inheritDoc} */
	@Override
	public IdentificationNumberCategory update(
			final IdentificationNumberCategory category,
			final String name, final Multitude multitude, final Boolean valid)
					throws DuplicateEntityFoundException {
		return this.identificationNumberCategoryDelegate
				.update(category, name, multitude, valid);
	}

	/**{@inheritDoc} */
	@Override
	public void remove(final IdentificationNumberCategory category) {
		this.identificationNumberCategoryDelegate.remove(category);
	}

}
