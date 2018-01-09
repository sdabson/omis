package omis.workrestriction.dao.impl.hibernate;

import java.util.Date;
import java.util.List;

import org.hibernate.SessionFactory;

import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.workrestriction.dao.WorkRestrictionNoteDao;
import omis.workrestriction.domain.WorkRestriction;
import omis.workrestriction.domain.WorkRestrictionNote;

/**
 * WorkRestrictionNoteDaoHibernateImpl.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Sep 25, 2016)
 *@since OMIS 3.0
 *
 */
public class WorkRestrictionNoteDaoHibernateImpl
	extends GenericHibernateDaoImpl<WorkRestrictionNote>
		implements WorkRestrictionNoteDao {
	
	/* Query Names */
	
	private static final String FIND_WORK_RESTRICTION_NOTE_QUERY_NAME 
		= "findWorkRestrictionNote";
	
	private static final String FIND_WORK_RESTRICTION_NOTE_EXCLUDING_QUERY_NAME
		= "findWorkRestrictionNoteExcluding";
	
	private static final String FIND_NOTES_BY_WORK_RESTRICTION_QUERY_NAME
		= "findNotesByWorkRestriction";
	
	/* Parameter Names */
	
	private static final String WORK_RESTRICTION_PARAM_NAME = "workRestriction";
	
	private static final String DATE_PARAM_NAME = "date";
	
	private static final String VALUE_PARAM_NAME = "value";
	
	private static final String WORK_RESTRICTION_NOTE_PARAM_NAME 
		= "workRestrictionNote";
	
	
	/**
	 * @param sessionFactory
	 * @param entityName
	 */
	protected WorkRestrictionNoteDaoHibernateImpl(
			SessionFactory sessionFactory, String entityName) {
		super(sessionFactory, entityName);
	}

	
	/**{@inheritDoc} */
	@Override
	public WorkRestrictionNote find(WorkRestriction workRestriction, Date date, 
			String value) {
		WorkRestrictionNote workRestrictionNote = (WorkRestrictionNote) 
				this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(FIND_WORK_RESTRICTION_NOTE_QUERY_NAME)
				.setParameter(WORK_RESTRICTION_PARAM_NAME, workRestriction)
				.setTimestamp(DATE_PARAM_NAME, date)
				.setParameter(VALUE_PARAM_NAME, value)
				.uniqueResult();
		
		return workRestrictionNote;
	}

	/**{@inheritDoc} */
	@Override
	public WorkRestrictionNote findExcluding(
			WorkRestrictionNote excludedWorkRestrictionNote,
			WorkRestriction workRestriction, Date date, String value) {
		WorkRestrictionNote workRestrictionNote = (WorkRestrictionNote) 
				this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(FIND_WORK_RESTRICTION_NOTE_EXCLUDING_QUERY_NAME)
				.setParameter(WORK_RESTRICTION_PARAM_NAME, workRestriction)
				.setTimestamp(DATE_PARAM_NAME, date)
				.setParameter(VALUE_PARAM_NAME, value)
				.setParameter(WORK_RESTRICTION_NOTE_PARAM_NAME,
						excludedWorkRestrictionNote)
				.uniqueResult();
		
		return workRestrictionNote;
	}

	/**{@inheritDoc} */
	@Override
	public List<WorkRestrictionNote> findAllByWorkRestriction(
			WorkRestriction workRestriction) {
		@SuppressWarnings("unchecked")
		List<WorkRestrictionNote> workRestrictionNotes =
			this.getSessionFactory()
			.getCurrentSession()
			.getNamedQuery(FIND_NOTES_BY_WORK_RESTRICTION_QUERY_NAME)
			.setParameter(WORK_RESTRICTION_PARAM_NAME, workRestriction)
			.list();
		
		return workRestrictionNotes;
	}

}
