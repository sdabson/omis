package omis.paroleboardmember.dao.impl.hibernate;

import java.util.Date;
import java.util.List;

import org.hibernate.SessionFactory;

import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.paroleboardmember.dao.ParoleBoardMemberDao;
import omis.paroleboardmember.domain.ParoleBoardMember;
import omis.staff.domain.StaffAssignment;

/**
 * Hibernate implementation of the parole board member data access object.
 *
 * @author Josh Divine
 * @version 0.1.0 (Nov 8, 2017)
 * @since OMIS 3.0
 */
public class ParoleBoardMemberDaoHibernateImpl 
		extends GenericHibernateDaoImpl<ParoleBoardMember>
		implements ParoleBoardMemberDao  {


	/* Queries. */
	
	private static final String FIND_QUERY_NAME = 
			"findParoleBoardMember";
	
	private static final String FIND_EXCLUDING_QUERY_NAME = 
			"findParoleBoardMemberExcluding";
	
	private static final String FIND_BY_DATE_QUERY_NAME = 
			"findParoleBoardMemberByDate";
	
	/* Parameters. */
	
	private static final String STAFF_ASSIGNMENT_PARAM_NAME = "staffAssignment";
	
	private static final String START_DATE_PARAM_NAME = "startDate";
	
	private static final String EXCLUDED_MEMBER_PARAM_NAME = 
			"excludedParoleBoardMember";
	
	private static final String EFFECTIVE_DATE_PARAM_NAME = "effectiveDate";

	/** Instantiates a hibernate implementation of the data access object for 
	 *  parole board member.
	 * 
	 * @param sessionFactory session factory
	 * @param entityName entity name
	 */
	public ParoleBoardMemberDaoHibernateImpl(
			final SessionFactory sessionFactory, final String entityName) {
		super(sessionFactory, entityName);
	}

	/** {@inheritDoc} */
	@Override
	public ParoleBoardMember find(final StaffAssignment staffAssignment, 
			final Date startDate) {
		ParoleBoardMember paroleBoardMember = (ParoleBoardMember) this
				.getSessionFactory().getCurrentSession()
				.getNamedQuery(FIND_QUERY_NAME)
				.setParameter(STAFF_ASSIGNMENT_PARAM_NAME, staffAssignment)
				.setParameter(START_DATE_PARAM_NAME, startDate)
				.uniqueResult();
		return paroleBoardMember;
	}

	/** {@inheritDoc} */
	@Override
	public ParoleBoardMember findExcluding(
			final StaffAssignment staffAssignment, final Date startDate,
			final ParoleBoardMember excludedParoleBoardMember) {
		ParoleBoardMember paroleBoardMember = (ParoleBoardMember) this
				.getSessionFactory().getCurrentSession()
				.getNamedQuery(FIND_EXCLUDING_QUERY_NAME)
				.setParameter(STAFF_ASSIGNMENT_PARAM_NAME, staffAssignment)
				.setParameter(START_DATE_PARAM_NAME, startDate)
				.setParameter(EXCLUDED_MEMBER_PARAM_NAME, 
						excludedParoleBoardMember)
				.uniqueResult();
		return paroleBoardMember;
	}

	/** {@inheritDoc} */
	@Override
	public List<ParoleBoardMember> findBoardMembersByDate(
			final Date effectiveDate) {
		@SuppressWarnings("unchecked")
		List<ParoleBoardMember> members = (List<ParoleBoardMember>) this
				.getSessionFactory().getCurrentSession()
				.getNamedQuery(FIND_BY_DATE_QUERY_NAME)
				.setTimestamp(EFFECTIVE_DATE_PARAM_NAME, effectiveDate)
				.list();
		return members;
	}

}
