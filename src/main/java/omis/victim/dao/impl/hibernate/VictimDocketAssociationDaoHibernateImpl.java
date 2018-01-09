package omis.victim.dao.impl.hibernate;

import java.util.List;

import org.hibernate.SessionFactory;

import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.docket.domain.Docket;
import omis.person.domain.Person;
import omis.victim.dao.VictimDocketAssociationDao;
import omis.victim.domain.VictimDocketAssociation;

/**
 * Victim docket association data access object hibernate implementation.
 * 
 * @author Joel Norris
 * @version 0.1.0 (Aug 08, 2017)
 * @since OMIS 3.0
 */
public class VictimDocketAssociationDaoHibernateImpl
	extends GenericHibernateDaoImpl<VictimDocketAssociation>
	implements VictimDocketAssociationDao {
	
	/* Query names. */
	
	private final String FIND_QUERY_NAME = "findVictimDocketAssociation";
	private final String FIND_EXCLUDING_QUERY_NAME = "findVictimDocketAssociationExcluding";
	private final String FIND_BY_VICTIM_QUERY_NAME = "findVictimDocketAssociationByVictim";
	private final String FIND_BY_DOCKET_QUERY_NAME = "findVictimDocketAssociationByDocket";
	
	/* Parameter names. */
	
	private final String DOCKET_PARAM_NAME = "docket";
	private final String VICTIM_PARAM_NAME = "victim";
	private final String ASSOCIATION_PARAM_NAME = "victimDocketAssociation";

	/* Constructor. */
	
	public VictimDocketAssociationDaoHibernateImpl(final SessionFactory sessionFactory,
			final String entityName) {
		super(sessionFactory, entityName);
	}

	/** {@inheritDoc} */
	@Override
	public VictimDocketAssociation find(final Person victim, final Docket docket) {
		return (VictimDocketAssociation) this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(FIND_QUERY_NAME)
				.setParameter(VICTIM_PARAM_NAME, victim)
				.setParameter(DOCKET_PARAM_NAME, docket).uniqueResult();
	}

	/** {@inheritDoc} */
	@Override
	public VictimDocketAssociation findExcluding(final VictimDocketAssociation victimDocketAssociation,
			final Person victim, final Docket docket) {
		return (VictimDocketAssociation) this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(FIND_EXCLUDING_QUERY_NAME)
				.setParameter(ASSOCIATION_PARAM_NAME, victimDocketAssociation)
				.setParameter(VICTIM_PARAM_NAME, victim)
				.setParameter(DOCKET_PARAM_NAME, docket)
				.uniqueResult();
	}

	/** {@inheritDoc} */
	@Override
	public List<VictimDocketAssociation> findByVictim(final Person victim) {
		@SuppressWarnings("unchecked")
		List<VictimDocketAssociation> associations = this.getSessionFactory().getCurrentSession()
				.getNamedQuery(FIND_BY_VICTIM_QUERY_NAME)
				.setParameter(VICTIM_PARAM_NAME, victim)
				.list();
		return associations;
	}

	/** {@inheritDoc} */
	@Override
	public List<VictimDocketAssociation> findByDocket(final Docket docket) {
		@SuppressWarnings("unchecked")
		List<VictimDocketAssociation> associations = this.getSessionFactory().getCurrentSession()
				.getNamedQuery(FIND_BY_DOCKET_QUERY_NAME)
				.setParameter(DOCKET_PARAM_NAME, docket)
				.list();
		return associations;
	}
}