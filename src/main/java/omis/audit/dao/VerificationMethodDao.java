package omis.audit.dao;

import omis.audit.domain.VerificationMethod;
import omis.dao.GenericDao;

/**
 * Data access object for verification methods.
 * 
 * @author Stephen Abson
 * @version 0.1.1 (Feb 7, 2013)
 * @since OMIS 3.0
 */
public interface VerificationMethodDao
		extends GenericDao<VerificationMethod> {

	VerificationMethod find(String name, Boolean valid);

}