package omis.victim.dao.impl.hibernate;

import java.util.List;

import org.hibernate.SessionFactory;

import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.docket.domain.Docket;
import omis.document.domain.Document;
import omis.person.domain.Person;
import omis.victim.dao.VictimDocumentAssociationDao;
import omis.victim.domain.VictimDocumentAssociation;

/**
 * Victim document association data access object hibernate implementation.
 * 
 * @author Joel Norris
 * @version 0.1.0 (Aug 22, 2017)
 * @since OMIS 3.0
 */
public class VictimDocumentAssociationDaoHibernateImpl 
	extends GenericHibernateDaoImpl<VictimDocumentAssociation>
	implements VictimDocumentAssociationDao {

	/* Query names. */
	
	private static final String FIND_QUERY_NAME = "findVictimDocumentAssociation";
	private static final String FIND_EXCLUDING_QUERY_NAME = "findVictimDocumentAssociationExcluding";
	private static final String FIND_BY_DOCKET_QUERY_NAME = "findVictimDocumentAssociationsByDocket";
	private static final String FIND_BY_VICTIM_QUERY_NAME = "findVictimDocumentAssociationsByVictim";
	private static final String FIND_BY_DOCKET_AND_VICTIM_QUERY_NAME = "findVictimDocumentAssociationsByDocketAndVictim";
	
	/* Parameter names. */
	
	private static final String DOCUMENT_ASSOCIATION_PARAM_NAME = "documentAssociation";
	private static final String DOCKET_PARAM_NAME = "docket";
	private static final String VICTIM_PARAM_NAME = "victim";
	private static final String DOCUMENT_PARAM_NAME = "document";
	
	/**
	 * Instantiates a default instance of victim document association data access object.
	 * 
	 * @param sessionFactory session factory
	 * @param entityName entity name
	 */
	public  VictimDocumentAssociationDaoHibernateImpl(final SessionFactory sessionFactory,
			final String entityName) {
		super(sessionFactory, entityName);
	}

	/** {@inheritDoc} */
	@Override
	public VictimDocumentAssociation find(final Person victim, final Document document) {
		VictimDocumentAssociation documentAssociation = (VictimDocumentAssociation) this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(FIND_QUERY_NAME)
				.setParameter(VICTIM_PARAM_NAME, victim)
				.setParameter(DOCUMENT_PARAM_NAME, document)
				.uniqueResult();
		return documentAssociation;
	}

	/** {@inheritDoc} */
	@Override
	public VictimDocumentAssociation findExcluding(final Person victim, final Document document,
			final VictimDocumentAssociation documentAssociation) {
		VictimDocumentAssociation victimDocumentAssociation = (VictimDocumentAssociation) this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(FIND_EXCLUDING_QUERY_NAME)
				.setParameter(VICTIM_PARAM_NAME, victim)
				.setParameter(DOCUMENT_PARAM_NAME, document)
				.setParameter(DOCUMENT_ASSOCIATION_PARAM_NAME, documentAssociation)
				.uniqueResult();
		return victimDocumentAssociation;
	}

	/** {@inheritDoc} */
	@Override
	public List<VictimDocumentAssociation> findByDocket(final Docket docket) {
		@SuppressWarnings("unchecked")
		List<VictimDocumentAssociation> documentAssociations =  this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(FIND_BY_DOCKET_QUERY_NAME)
				.setParameter(DOCKET_PARAM_NAME, docket)
				.list();
		return documentAssociations;
	}

	/** {@inheritDoc} */
	@Override
	public List<VictimDocumentAssociation> findByVictim(final Person victim) {
		@SuppressWarnings("unchecked")
		List<VictimDocumentAssociation> documentAssociations = this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(FIND_BY_VICTIM_QUERY_NAME)
				.setParameter(VICTIM_PARAM_NAME, victim)
				.list();
		return documentAssociations;
	}

	/** {@inheritDoc} */
	@Override
	public List<VictimDocumentAssociation> findByDocketAndVictim(final Docket docket, final Person victim) {
		@SuppressWarnings("unchecked")
		List<VictimDocumentAssociation> documentAssociations = this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(FIND_BY_DOCKET_AND_VICTIM_QUERY_NAME)
				.setParameter(DOCKET_PARAM_NAME, docket)
				.setParameter(VICTIM_PARAM_NAME, victim)
				.list();
		return documentAssociations;
	}
}
