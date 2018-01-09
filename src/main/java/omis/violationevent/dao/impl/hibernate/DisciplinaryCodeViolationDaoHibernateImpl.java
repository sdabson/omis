package omis.violationevent.dao.impl.hibernate;

import java.util.List;

import org.hibernate.SessionFactory;

import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.disciplinaryCode.domain.DisciplinaryCode;
import omis.violationevent.dao.DisciplinaryCodeViolationDao;
import omis.violationevent.domain.DisciplinaryCodeViolation;
import omis.violationevent.domain.ViolationEvent;

/**
 * DisciplinaryCodeViolationDaoHibernateImpl.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Jan 17, 2017)
 *@since OMIS 3.0
 *
 */
public class DisciplinaryCodeViolationDaoHibernateImpl
	extends GenericHibernateDaoImpl<DisciplinaryCodeViolation>
		implements DisciplinaryCodeViolationDao {
	
	
	
	
	private static final String FIND_DISCIPLINARY_CODE_VIOLATION_QUERY_NAME =
			"findDisciplinaryCodeViolation";
	
	private static final String
		FIND_DISCIPLINARY_CODE_VIOLATION_EXCLUDING_QUERY_NAME =
			"findDisciplinaryCodeViolationExcluding";
	
	private static final String
		FIND_DISCIPLINARY_CODE_VIOLATIONS_BY_VIOLATION_EVENT_QUERY_NAME =
			"findDisciplinaryCodeViolationsByViolationEvent";
	
	private static final String
	FIND_UNRESOLVED_DISCIPLINARY_CODE_VIOLATIONS_BY_VIOLATION_EVENT_QUERY_NAME =
		"findUnresolvedDisciplinaryCodeViolationsByViolationEvent";
	
	
	
	private static final String DISCIPLINARY_CODE_PARAM_NAME =
			"disciplinaryCode";
	
	private static final String VIOLATION_EVENT_PARAM_NAME = "violationEvent";
	
	private static final String DISCIPLINARY_CODE_VIOLATION_PARAM_NAME =
			"disciplinaryCodeViolation";
	
	
	
	/**
	 * @param sessionFactory
	 * @param entityName
	 */
	protected DisciplinaryCodeViolationDaoHibernateImpl(
			final SessionFactory sessionFactory, final String entityName) {
		super(sessionFactory, entityName);
	}

	/**{@inheritDoc} */
	@Override
	public DisciplinaryCodeViolation find(
			final DisciplinaryCode disciplinaryCode,
			final ViolationEvent violationEvent) {
		DisciplinaryCodeViolation disciplinaryCodeViolation =
				(DisciplinaryCodeViolation) this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(FIND_DISCIPLINARY_CODE_VIOLATION_QUERY_NAME)
				.setParameter(DISCIPLINARY_CODE_PARAM_NAME, disciplinaryCode)
				.setParameter(VIOLATION_EVENT_PARAM_NAME, violationEvent)
				.uniqueResult();
		
		return disciplinaryCodeViolation;
	}

	/**{@inheritDoc} */
	@Override
	public DisciplinaryCodeViolation findExcluding(
			final DisciplinaryCodeViolation excludedDisciplinaryCodeViolation,
			final DisciplinaryCode disciplinaryCode,
			final ViolationEvent violationEvent) {
		DisciplinaryCodeViolation disciplinaryCodeViolation =
				(DisciplinaryCodeViolation) this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(
						FIND_DISCIPLINARY_CODE_VIOLATION_EXCLUDING_QUERY_NAME)
				.setParameter(DISCIPLINARY_CODE_PARAM_NAME, disciplinaryCode)
				.setParameter(VIOLATION_EVENT_PARAM_NAME, violationEvent)
				.setParameter(DISCIPLINARY_CODE_VIOLATION_PARAM_NAME,
						excludedDisciplinaryCodeViolation)
				.uniqueResult();
		
		return disciplinaryCodeViolation;
	}

	/**{@inheritDoc} */
	@Override
	public List<DisciplinaryCodeViolation> findByViolationEvent(
			final ViolationEvent violationEvent) {
		@SuppressWarnings("unchecked")
		List<DisciplinaryCodeViolation> disciplinaryCodeViolations =
			this.getSessionFactory()
			.getCurrentSession()
			.getNamedQuery(
				FIND_DISCIPLINARY_CODE_VIOLATIONS_BY_VIOLATION_EVENT_QUERY_NAME)
			.setParameter(VIOLATION_EVENT_PARAM_NAME, violationEvent)
			.list();
		
		return disciplinaryCodeViolations;
	}

	/**{@inheritDoc} */
	@Override
	public List<DisciplinaryCodeViolation> findUnresolvedByViolationEvent(
			final ViolationEvent violationEvent) {
		@SuppressWarnings("unchecked")
		List<DisciplinaryCodeViolation> disciplinaryCodeViolations =
			this.getSessionFactory()
			.getCurrentSession()
			.getNamedQuery(
		FIND_UNRESOLVED_DISCIPLINARY_CODE_VIOLATIONS_BY_VIOLATION_EVENT_QUERY_NAME)
			.setParameter(VIOLATION_EVENT_PARAM_NAME, violationEvent)
			.list();
		
		return disciplinaryCodeViolations;
	}

}
