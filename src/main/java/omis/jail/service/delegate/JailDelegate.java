package omis.jail.service.delegate;

import java.util.List;

import omis.instance.factory.InstanceFactory;
import omis.jail.dao.JailDao;
import omis.jail.domain.Jail;
import omis.jail.domain.JailCategory;
import omis.location.domain.Location;
import omis.organization.domain.Organization;

/**
 * Delegate for jails.
 *
 * @author Stephen Abson
 * @author Annie Jacques
 * @version 0.0.2 (May 15, 2017)
 * @since OMIS 3.0
 */
public class JailDelegate {

	/* Resources. */
	
	private final JailDao jailDao;
	
	private final InstanceFactory<Jail> jailInstanceFactory;
	
	/* Constructors. */
	
	/**
	 * Instantiates delegate for jails.
	 * 
	 * @param jailDao data access object for jails
	 * @param jailInstanceFactory - InstanceFactory for Jails
	 */
	public JailDelegate(final JailDao jailDao,
			final InstanceFactory<Jail> jailInstanceFactory) {
		this.jailDao = jailDao;
		this.jailInstanceFactory = jailInstanceFactory;
	}
	
	/* Methods. */
	
	/**
	 * Returns jails.
	 * 
	 * @return jails
	 */
	public List<Jail> findAll() {
		return this.jailDao.findAll();
	}

	/**
	 * Returns jails by organization.
	 * 
	 * @param organization organization
	 * @return jails by organization
	 */
	public List<Jail> findByOrganization(Organization organization) {
		return this.jailDao.findByOrganization(organization);
	}
	
	/**
	 * Creates a Jail (for Unit Testing)
	 * @param name - String
	 * @param location - Location
	 * @param category - JailCategory
	 * @param active - Boolean
	 * @return Created Jail
	 */
	public Jail create(final String name, final Location location,
			final JailCategory category, final Boolean active){
		Jail jail = this.jailInstanceFactory.createInstance();
		
		jail.setCategory(category);
		jail.setLocation(location);
		jail.setName(name);
		jail.setActive(active);
		
		return this.jailDao.makePersistent(jail);
	}
}