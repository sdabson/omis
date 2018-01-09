package omis.hearing.dao.impl.hibernate;

import java.util.List;

import org.hibernate.SessionFactory;

import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.hearing.dao.StaffAttendanceDao;
import omis.hearing.domain.Hearing;
import omis.hearing.domain.StaffAttendance;
import omis.staff.domain.StaffAssignment;

/**
 * StaffAttendanceDaoHibernateImpl.java
 * 
 *@author Annie Jacques 
 *@version 0.1.1 (Apr 17, 2017)
 *@since OMIS 3.0
 *
 */
public class StaffAttendanceDaoHibernateImpl
	extends GenericHibernateDaoImpl<StaffAttendance>
		implements StaffAttendanceDao {
	
	/* Query Names */
	
	private static final String FIND_STAFF_ATTENDANCE_QUERY_NAME =
			"findStaffAttendance";
	
	private static final String FIND_STAFF_ATTENDANCE_EXCLUDING_QUERY_NAME =
			"findStaffAttendanceExcluding";
	
	private static final String FIND_ALL_STAFF_ATTENDANCE_BY_HEARING_QUERY_NAME =
			"findStaffAttendanceByHearing";
	
	/* Param Names */
	
	private static final String STAFF_PARAM_NAME = "staff";
	
	private static final String HEARING_PARAM_NAME = "hearing";
	
	private static final String STAFF_ATTENDANCE_PARAM_NAME = "staffAttendance";
	
	/**
	 * @param sessionFactory
	 * @param entityName
	 */
	protected StaffAttendanceDaoHibernateImpl(
			final SessionFactory sessionFactory, final String entityName) {
		super(sessionFactory, entityName);
	}

	/**{@inheritDoc} */
	@Override
	public StaffAttendance find(final Hearing hearing,
			final StaffAssignment staff) {
		StaffAttendance staffAttendance =
				(StaffAttendance) this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(FIND_STAFF_ATTENDANCE_QUERY_NAME)
				.setParameter(HEARING_PARAM_NAME, hearing)
				.setParameter(STAFF_PARAM_NAME, staff)
				.uniqueResult();
		return staffAttendance;
	}

	/**{@inheritDoc} */
	@Override
	public StaffAttendance findExcluding(final Hearing hearing,
			final StaffAssignment staff, final StaffAttendance staffAttendance){
		StaffAttendance staffAttendanceExcluding =
				(StaffAttendance) this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(FIND_STAFF_ATTENDANCE_EXCLUDING_QUERY_NAME)
				.setParameter(HEARING_PARAM_NAME, hearing)
				.setParameter(STAFF_PARAM_NAME, staff)
				.setParameter(STAFF_ATTENDANCE_PARAM_NAME, staffAttendance)
				.uniqueResult();
		return staffAttendanceExcluding;
	}

	/**{@inheritDoc} */
	@Override
	public List<StaffAttendance> findByHearing(final Hearing hearing) {
		@SuppressWarnings("unchecked")
		List<StaffAttendance> staffAttendances = this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(FIND_ALL_STAFF_ATTENDANCE_BY_HEARING_QUERY_NAME)
				.setParameter(HEARING_PARAM_NAME, hearing)
				.list();
		return staffAttendances;
	}

}
