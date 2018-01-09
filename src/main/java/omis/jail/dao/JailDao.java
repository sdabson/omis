package omis.jail.dao;

import java.util.List;

import omis.dao.GenericDao;
import omis.jail.domain.Jail;
import omis.organization.domain.Organization;

/**
 * Data access object for jails.
 *
 * @author Stephen Abson
 * @version 0.0.1 (Apr 18, 2016)
 * @since OMIS 3.0
 */
public interface JailDao
		extends GenericDao<Jail> {

	/**
	 * Returns jails by organization.
	 * 
	 * @param organization organization
	 * @return jails by organization
	 */
	List<Jail> findByOrganization(Organization organization);
}