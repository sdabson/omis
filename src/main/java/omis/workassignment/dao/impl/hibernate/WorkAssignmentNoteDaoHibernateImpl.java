package omis.workassignment.dao.impl.hibernate;

import java.util.Date;
import java.util.List;

import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.workassignment.dao.WorkAssignmentNoteDao;
import omis.workassignment.domain.WorkAssignment;
import omis.workassignment.domain.WorkAssignmentNote;

import org.hibernate.SessionFactory;

/**
 * Hibernate implementation of data access object for work assignment note
 * 
 * @author Yidong Li
 * @version 0.1.0 (Aug 18, 2016)
 * @since OMIS 3.0
 */
public class WorkAssignmentNoteDaoHibernateImpl
		extends GenericHibernateDaoImpl<WorkAssignmentNote>
		implements WorkAssignmentNoteDao {
	/* Queries. */
	private static final String FIND_WORK_ASSIGNMENT_NOTES_QUERY_NAME 
		= "findWorkAssignmentNotes";
	private static final String FIND_EXISTING_WORK_ASSIGNMENT_NOTES_QUERY_NAME 
		= "findExistingWorkAssignmentNote";
	private static final String FIND_EXCLUDED_EXISTING_WORK_ASSIGNMENT_NOTES_QUERY_NAME
		="findExcludedExistingWorkAssignmentNote";
	
	/* Parameters */
	private static final String WORK_ASSIGNMENT_PARAM_NAME = "workAssignment";
	private static final String DATE_PARAM_NAME = "date";
	private static final String VALUE_PARAMETER_NAME = "value";
	private static final String EXCLUDED_WORK_ASSIGNMENT_NOTE_PARAM_NAME 
		= "excludedWorkAssignmentNote";

	/**
	 * Instantiates an Hibernate implementation of data access object for
	 * work assignment note
	 * 
	 * @param sessionFactory session factory
	 * @param entityName entity name
	 */
	public WorkAssignmentNoteDaoHibernateImpl(
			final SessionFactory sessionFactory,
			final String entityName) {
		super(sessionFactory, entityName);
	}

	/** {@inheritDoc} */
	@Override
	public List<WorkAssignmentNote> findNotes(final WorkAssignment workAssignment) {
		@SuppressWarnings("unchecked")
		List<WorkAssignmentNote> workAssignmentNotes = this.getSessionFactory()
			.getCurrentSession()
			.getNamedQuery(FIND_WORK_ASSIGNMENT_NOTES_QUERY_NAME)
			.setParameter(WORK_ASSIGNMENT_PARAM_NAME, workAssignment)
			.list();
		return workAssignmentNotes;
	}
	
	/** {@inheritDoc} */
	@Override
	public WorkAssignmentNote findExistingWorkAssignmentNote(
		final WorkAssignment workAssignment, final Date date, 
		final String value) {
		WorkAssignmentNote workAssignmentNote 
			= (WorkAssignmentNote) getSessionFactory()
			.getCurrentSession()
			.getNamedQuery(FIND_EXISTING_WORK_ASSIGNMENT_NOTES_QUERY_NAME)
			.setParameter(WORK_ASSIGNMENT_PARAM_NAME, workAssignment)
			.setParameter(DATE_PARAM_NAME, date)
			.setParameter(VALUE_PARAMETER_NAME, value)
			.uniqueResult();
		return workAssignmentNote;
	}
	
	/** {@inheritDoc} */
	@Override
	public WorkAssignmentNote findExcludedExistingWorkAssignmentNote(
		final WorkAssignmentNote workAssignmentNote,
		final WorkAssignment workAssignment,
		final Date date, 
		final String value) {
		WorkAssignmentNote excludedWorkAssignmentNote 
			= (WorkAssignmentNote) getSessionFactory()
			.getCurrentSession()
			.getNamedQuery(FIND_EXCLUDED_EXISTING_WORK_ASSIGNMENT_NOTES_QUERY_NAME)
			.setParameter(WORK_ASSIGNMENT_PARAM_NAME, workAssignment)
			.setParameter(DATE_PARAM_NAME, date)
			.setParameter(VALUE_PARAMETER_NAME, value)
			.setParameter(EXCLUDED_WORK_ASSIGNMENT_NOTE_PARAM_NAME, workAssignmentNote)
			.uniqueResult();
		return excludedWorkAssignmentNote;
	}
}