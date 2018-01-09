package omis.victim.dao.impl.hibernate;

import java.util.List;

import org.hibernate.SessionFactory;

import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.document.domain.Document;
import omis.person.domain.Person;
import omis.victim.dao.VictimDocketDocumentAssociationDao;
import omis.victim.domain.VictimDocketAssociation;
import omis.victim.domain.VictimDocketDocumentAssociation;

/**
 * Victim docket document association data access object hibernate implementation.
 * 
 * @author Joel Norris
 * @version 0.1.0 (Aug 08, 2017)
 * @since OMIS 3.0
 */
public class VictimDocketDocumentAssociationDaoHibernateImpl 
	extends GenericHibernateDaoImpl<VictimDocketDocumentAssociation>
	implements VictimDocketDocumentAssociationDao {
	
	/* Query names. */
	
	private static final String FIND_QUERY_NAME = "findVictimDocketDocumentAssociation";
	private static final String FIND_EXCLUDING_QUERY_NAME = "findVictimDocketDocumentAssociationExcluding";
	private static final String FIND_BY_VICTIM_QUERY_NAME = "findVictimDocketDocumentAssociationByVictim";
	private static final String FIND_BY_VICTIM_DOCKET_ASSOCIATION_QUERY_NAME
		= "findVictimDocketDocumentAssociationByVictimDocketAssociation";
	
	/* Parameter names. */
	
	private static final String VICTIM_DOCKET_DOCUMENT_ASSOCIATION_PARAM_NAME = "victimDocketDocumentAssociation";
	private static final String VICTIM_DOCKET_ASSOCIATION_PARAM_NAME = "victimDocketAssociation";
	private static final String DOCUMENT_PARAM_NAME = "document";
	private static final String VICTIM_PARAM_NAME = "victim";

	/**
	 * Instantiates an instance of victim docket document association data access object with the specified
	 * session factory and entity name.
	 * 
	 * @param sessionFactory session factory
	 * @param entityName entity name
	 */
	public VictimDocketDocumentAssociationDaoHibernateImpl(final SessionFactory sessionFactory,
			final String entityName) {
		super(sessionFactory, entityName);
	}

	/** {@inheritDoc} */
	@Override
	public VictimDocketDocumentAssociation find(final VictimDocketAssociation victimDocketAssociation,
			final Document document) {
		return (VictimDocketDocumentAssociation) this.getSessionFactory().getCurrentSession()
				.getNamedQuery(FIND_QUERY_NAME)
				.setParameter(VICTIM_DOCKET_ASSOCIATION_PARAM_NAME, victimDocketAssociation)
				.setParameter(DOCUMENT_PARAM_NAME, document)
				.uniqueResult();
	}

	/** {@inheritDoc} */
	@Override
	public VictimDocketDocumentAssociation findExcluding(
			final VictimDocketDocumentAssociation victimDocketDocumentAssociation,
			final VictimDocketAssociation victimDocketAssociation, final Document document) {
		return (VictimDocketDocumentAssociation) this.getSessionFactory().getCurrentSession()
				.getNamedQuery(FIND_EXCLUDING_QUERY_NAME)
				.setParameter(VICTIM_DOCKET_DOCUMENT_ASSOCIATION_PARAM_NAME, victimDocketDocumentAssociation)
				.setParameter(VICTIM_DOCKET_ASSOCIATION_PARAM_NAME, victimDocketAssociation)
				.setParameter(DOCUMENT_PARAM_NAME, document)
				.uniqueResult();
	}

	/** {@inheritDoc} */
	@Override
	public List<VictimDocketDocumentAssociation> findByVictim(final Person victim) {
		@SuppressWarnings("unchecked")
		List<VictimDocketDocumentAssociation> associations = this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(FIND_BY_VICTIM_QUERY_NAME)
				.setParameter(VICTIM_PARAM_NAME, victim)
				.list();
		return associations;
	}

	/** {@inheritDoc} */
	@Override
	public List<VictimDocketDocumentAssociation> findByVictimDocketAssociation(
			VictimDocketAssociation victimDocketAssociation) {
		@SuppressWarnings("unchecked")
		List<VictimDocketDocumentAssociation> associations = this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(FIND_BY_VICTIM_DOCKET_ASSOCIATION_QUERY_NAME)
				.setParameter(VICTIM_DOCKET_ASSOCIATION_PARAM_NAME, victimDocketAssociation)
				.list();
		return associations;
	}
}