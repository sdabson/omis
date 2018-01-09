package omis.identificationnumber.service.delegate;

import java.util.List;

import omis.exception.DuplicateEntityFoundException;
import omis.identificationnumber.dao.IdentificationNumberIssuerDao;
import omis.identificationnumber.domain.IdentificationNumberIssuer;
import omis.instance.factory.InstanceFactory;

/**
 * Delegate for identification number issuer.
 *
 * @author Stephen Abson
 * @version 0.0.1
 * @since OMIS 3.0
 */
public class IdentificationNumberIssuerDelegate {
	
	/* Instance factories. */
	
	private final InstanceFactory<IdentificationNumberIssuer>
	identificationNumberIssuerInstanceFactory;

	/* Data access objects. */
	
	private final IdentificationNumberIssuerDao identificationNumberIssuerDao;
	
	/* Constructors. */
	
	/**
	 * Delegate for identification number issuer.
	 * 
	 * @param identificationNumberIssuerInstanceFactory instance factory
	 * for identification number issuer 
	 * @param identificationNumberIssuerDao data access object for
	 * identification number issuer
	 */
	public IdentificationNumberIssuerDelegate(
			final InstanceFactory<IdentificationNumberIssuer>
				identificationNumberIssuerInstanceFactory,
			final IdentificationNumberIssuerDao identificationNumberIssuerDao) {
		this.identificationNumberIssuerInstanceFactory
			= identificationNumberIssuerInstanceFactory;
		this.identificationNumberIssuerDao = identificationNumberIssuerDao;
	}
	
	/* Methods. */
	
	/**
	 * Returns identification number issuers.
	 * 
	 * @return identification number issuers
	 */
	public List<IdentificationNumberIssuer> findAll() {
		return this.identificationNumberIssuerDao.findAll();
	}

	/**
	 * Creates identification number issuer.
	 * 
	 * @param name name
	 * @param valid whether valid
	 * @return created identification number issuer
	 * @throws DuplicateEntityFoundException if issuer exists
	 */
	public IdentificationNumberIssuer create(
			final String name, final Boolean valid)
					throws DuplicateEntityFoundException {
		if (this.identificationNumberIssuerDao.find(name) != null) {
			throw new DuplicateEntityFoundException("Issuer exists");
		}
		IdentificationNumberIssuer issuer
			= this.identificationNumberIssuerInstanceFactory.createInstance();
		
		issuer.setName(name);
		issuer.setValid(valid);
		return this.identificationNumberIssuerDao.makePersistent(issuer);
	}
}