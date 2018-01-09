package omis.offenderflag.service.impl;

import java.util.Date;
import java.util.List;

import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.exception.DuplicateEntityFoundException;
import omis.instance.factory.InstanceFactory;
import omis.offender.domain.Offender;
import omis.offenderflag.dao.OffenderFlagCategoryDao;
import omis.offenderflag.dao.OffenderFlagDao;
import omis.offenderflag.domain.FlagUsage;
import omis.offenderflag.domain.OffenderFlag;
import omis.offenderflag.domain.OffenderFlagCategory;
import omis.offenderflag.service.OffenderFlagService;
import omis.offenderflag.service.delegate.OffenderFlagCategoryDelegate;
import omis.user.domain.UserAccount;

/**
 * Implementation of service for offender flags.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (Feb 10, 2014)
 * @since OMIS 3.0
 */
public class OffenderFlagServiceImpl
		implements OffenderFlagService {	

	private final OffenderFlagDao offenderFlagDao;
	
	private final InstanceFactory<OffenderFlag> offenderFlagInstanceFactory;
	
	private final OffenderFlagCategoryDao offenderFlagCategoryDao;
	
	/* Delegates. */
	
	private final OffenderFlagCategoryDelegate offenderFlagCategoryDelegate;
	
	/**
	 * Instantiates an implementation of service for offender flags.
	 * 
	 * @param offenderFlagDao data access object for offender flags
	 * @param offenderFlagInstanceFactory instance factory for offender flags
	 * @param offenderFlagCategoryDao data access object for offender flag
	 * categories
	 */
	public OffenderFlagServiceImpl(
			final OffenderFlagDao offenderFlagDao,
			final InstanceFactory<OffenderFlag> offenderFlagInstanceFactory,
			final OffenderFlagCategoryDao offenderFlagCategoryDao,
			final OffenderFlagCategoryDelegate offenderFlagCategoryDelegate) {
		this.offenderFlagDao = offenderFlagDao;
		this.offenderFlagInstanceFactory = offenderFlagInstanceFactory;
		this.offenderFlagCategoryDao = offenderFlagCategoryDao;
		this.offenderFlagCategoryDelegate = offenderFlagCategoryDelegate;
	}
	
	/** {@inheritDoc} */
	@Override
	public OffenderFlag set(final Offender offender,
			final OffenderFlagCategory category, final Boolean value,
			final UserAccount userAccount, final Date date) {
		OffenderFlag flag = this.offenderFlagDao.find(offender, category);
		if (flag == null) {
			flag = this.offenderFlagInstanceFactory.createInstance();
			flag.setCreationSignature(new CreationSignature(userAccount, date));
			flag.setCategory(category);
			flag.setOffender(offender);
		}
		flag.setUpdateSignature(new UpdateSignature(userAccount, date));
		flag.setValue(value);
		return this.offenderFlagDao.makePersistent(flag);
	}
	
	/** {@inheritDoc} */
	@Override
	public void unset(final Offender offender,
			final OffenderFlagCategory category) {
		OffenderFlag flag = this.offenderFlagDao.find(offender, category);
		if (flag != null) {
			this.offenderFlagDao.makeTransient(flag);
		}
	}
	
	/** {@inheritDoc} */
	@Override
	public List<OffenderFlagCategory> findRequiredCategories() {
		return this.offenderFlagCategoryDao.findRequired();
	}
	
	/** {@inheritDoc} */
	@Override
	public List<OffenderFlagCategory> findCategories() {
		return this.offenderFlagCategoryDao.findAll();
	}

	/** {@inheritDoc} */
	@Override
	public boolean hasRequiredFlags(final Offender offender) {
		List<OffenderFlagCategory> categories = this.offenderFlagCategoryDao
				.findRequired();
		return this.offenderFlagDao.countMissingFlags(offender,
				categories.toArray(new OffenderFlagCategory[] { })) == 0;
	}

	/** {@inheritDoc} */
	@Override
	public OffenderFlag find(
			final Offender offender, final OffenderFlagCategory category) {
		return this.offenderFlagDao.find(offender, category);
	}

	/** {@inheritDoc} */
	@Override
	public OffenderFlagCategory createOffenderFlagCategory(
			final String name, final String description, final Boolean requried,
			final Short sortOrder, final FlagUsage usage) 
					throws DuplicateEntityFoundException {
		return this.offenderFlagCategoryDelegate.createOffenderFlagCategory(
				name, description, requried, sortOrder, usage);
	}

	/** {@inheritDoc} */
	@Override
	public OffenderFlagCategory updateOffenderFlagCategory(
			final OffenderFlagCategory offenderFlagCategory, final String name,
			final Boolean requried, final Short sortOrder, 
			final FlagUsage usage) throws DuplicateEntityFoundException {
		return this.offenderFlagCategoryDelegate.updateOffenderFlagCategory(
				offenderFlagCategory, name, requried, sortOrder, usage);
	}

	/** {@inheritDoc} */
	@Override
	public void removeOffenderFlagCategory(
			final OffenderFlagCategory offenderFlagCategory) {
		this.offenderFlagCategoryDelegate.removeOffenderFlagCategory
				(offenderFlagCategory);
	}
}