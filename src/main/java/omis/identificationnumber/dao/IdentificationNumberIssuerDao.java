package omis.identificationnumber.dao;

import omis.dao.GenericDao;
import omis.identificationnumber.domain.IdentificationNumberIssuer;

/**
 * Data access object for identification number issuers.
 *
 * @author Stephen Abson
 * @version 0.0.1
 * @since OMIS 3.0
 */
public interface IdentificationNumberIssuerDao
		extends GenericDao<IdentificationNumberIssuer> {

	/**
	 * Returns identification number issuer.
	 * 
	 * @param name name
	 * @return identification number issuer
	 */
	IdentificationNumberIssuer find(String name);
}