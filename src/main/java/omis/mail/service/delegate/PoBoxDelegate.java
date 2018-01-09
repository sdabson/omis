package omis.mail.service.delegate;

import java.util.List;

import omis.address.domain.ZipCode;
import omis.exception.DuplicateEntityFoundException;
import omis.instance.factory.InstanceFactory;
import omis.mail.dao.PoBoxDao;
import omis.mail.domain.PoBox;

/**
 * Delegate for P.O.Box
 *
 * @author Yidong Li
 * @version 0.0.1 (June 27, 2015)
 * @since OMIS 3.0
 * @deprecated use {@code omis.contact.domain.component.PoBox} instead
 */
@Deprecated
public class PoBoxDelegate {
	/* Resources. */
	private final InstanceFactory<PoBox> poBoxInstanceFactory;
	private final PoBoxDao poBoxDao;
	
	/* Constructors. */
	/**
	 * Instantiates delegate for managing P.O.Box.
	 * 
	 * @param poBoxInstanceFactory instance factory for po box
	 */
	public PoBoxDelegate(final InstanceFactory<PoBox> poBoxInstanceFactory,
		final PoBoxDao poBoxDao) {
		this.poBoxInstanceFactory = poBoxInstanceFactory;
		this.poBoxDao = poBoxDao;
	}

	/* Methods. */
	
	/**
	 * Creates P.O.Box.
	 * 
	 * @param value po box number
	 * @param zipCode ZIP code
	 * @return created po box
	 */
	public PoBox create(final String value, final ZipCode zipCode) 
		throws DuplicateEntityFoundException{
		PoBox poBox = this.poBoxInstanceFactory.createInstance();
		if(this.poBoxDao.find(value, zipCode)!=null){
			throw new DuplicateEntityFoundException("PO Box Already Exist.");
		}
		poBox.setValue(value);
		poBox.setZipCode(zipCode);
		return poBoxDao.makePersistent(poBox);
	}
	
	/**
	 * Updates po box.
	 * 
	 * @param poBox po box to be updated
	 * @param value po box number
	 * @param zipCode ZIP code
	 * @return updated po box
	 */
	public PoBox update(final PoBox poBox, final String value,
		final ZipCode zipCode) throws DuplicateEntityFoundException{
		if(this.poBoxDao.findExcluding(poBox, value, zipCode)!=null){
			throw new DuplicateEntityFoundException("PO Box Already Exist.");
		}
		poBox.setValue(value);
		poBox.setZipCode(zipCode);
		return poBoxDao.makePersistent(poBox);
	}
	
	/**
	 * Removes po box.
	 * 
	 * @param po box to remove
	 */
	public void remove(final PoBox poBox) {
		this.poBoxDao.makeTransient(poBox);
	}
	
	/**
	 * Find po box by zip code.
	 * 
	 * @param zipCode zipCode
	 */
	public List<PoBox> findPoBoxByZipCode(final ZipCode zipCode) {
		return this.poBoxDao.findPoBoxByZipCode(zipCode);
	}
}