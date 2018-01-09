package omis.family.dao.impl.hibernate;

import java.util.Date;
import java.util.List;

import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.family.dao.FamilyAssociationNoteDao;
import omis.family.domain.FamilyAssociation;
import omis.family.domain.FamilyAssociationNote;

import org.hibernate.SessionFactory;

/**
 * Implementation of data access object for family association note.
 * 
 * @author Yidong Li
 * @author Sheronda Vaughn
 * @version 0.1.0 (June 3, 2015)
 * @since OMIS 3.0
 */
public class FamilyAssociationNoteDaoHibernateImpl 
	extends GenericHibernateDaoImpl<FamilyAssociationNote>
	implements FamilyAssociationNoteDao {

	/* Query names. */
	
	private static final String FIND_FAMILY_ASSOCIATION_NOTE_QUERY_NAME
		= "findFamilyAssociationNote";
	
	private static final String 
		FIND_FAMILY_ASSOCIATION_NOTE_EXCLUDING_QUERY_NAME
		= "findFamilyAssociationNoteExcluding";
	
	private static final String 
		FIND_FAMILY_ASSOCIATION_NOTE_BY_ASSOCIATION_QUERY_NAME
		= "findFamilyAssociationNoteByAssociation";
	
	private static final String FIND_ALL_FAMILY_ASSOCIATION_NOTES_QUERY_NAME
		= "findAllFamilyAssociationNotes";
	
	/* Parameter names. */
	private static final String FAMILY_ASSOCIATION_PARAM_NAME = "association";
	
	private static final String DATE_PARAM_NAME = "date";
	
	private static final String NOTE_PARAM_NAME = "note";
	
	/**
	 * Instantiates a default instance of family association data access object
	 * hibernate entity implementation.
	 * 
	 * @param sessionFactory session factory
	 * @param entityName entity name
	 */
	public FamilyAssociationNoteDaoHibernateImpl(
			final SessionFactory sessionFactory,
			final String entityName) {
		super(sessionFactory, entityName);
	}
	
	/** {@inheritDoc} */
	@Override
	public FamilyAssociationNote find(final FamilyAssociation association, 
		final Date date) {
		FamilyAssociationNote associationNote = (FamilyAssociationNote) 
			this.getSessionFactory().getCurrentSession()
			.getNamedQuery(FIND_FAMILY_ASSOCIATION_NOTE_QUERY_NAME)
			.setParameter(FAMILY_ASSOCIATION_PARAM_NAME, association) 
			.setParameter(DATE_PARAM_NAME, date)
			.uniqueResult();
		return associationNote;
	}
	
	/** {@inheritDoc} */
	@Override
	public FamilyAssociationNote findExcluding(
			final FamilyAssociation association, final Date date, 
			final FamilyAssociationNote note) {
		FamilyAssociationNote associationNote = (FamilyAssociationNote) 
			this.getSessionFactory().getCurrentSession()
			.getNamedQuery(FIND_FAMILY_ASSOCIATION_NOTE_EXCLUDING_QUERY_NAME)
			.setParameter(FAMILY_ASSOCIATION_PARAM_NAME, association) 
			.setParameter(DATE_PARAM_NAME, date)
			.setParameter(NOTE_PARAM_NAME, note)
			.uniqueResult();
		return associationNote;
	}
	
	/** {@inheritDoc} */
	@Override
	public List<FamilyAssociationNote> findByAssociation(
		final FamilyAssociation familyAssociation) {
		@SuppressWarnings("unchecked")
		List<FamilyAssociationNote> associationNotes 
			= (List<FamilyAssociationNote>)	
			this.getSessionFactory().getCurrentSession()
			.getNamedQuery(
					FIND_FAMILY_ASSOCIATION_NOTE_BY_ASSOCIATION_QUERY_NAME)
			.setParameter(FAMILY_ASSOCIATION_PARAM_NAME, familyAssociation) 
			.list();
		return associationNotes;
	}
	
	/** {@inheritDoc} */
	@Override	
	public List<FamilyAssociationNote> findAll() {
		@SuppressWarnings("unchecked")
		List<FamilyAssociationNote> associationNotes 
			= (List<FamilyAssociationNote>) 
			this.getSessionFactory().getCurrentSession()
			.getNamedQuery(FIND_ALL_FAMILY_ASSOCIATION_NOTES_QUERY_NAME)
			.list();
		return associationNotes;
	}
}