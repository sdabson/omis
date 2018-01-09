package omis.education.dao.impl.hibernate;

import java.util.Date;
import java.util.List;

import org.hibernate.SessionFactory;

import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.education.dao.EducationalAchievementDao;
import omis.education.domain.EducationTerm;
import omis.education.domain.EducationalAchievement;

/**
 * EducationalAchievementDaoHibernateImpl.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Jul 26, 2016)
 *@since OMIS 3.0
 *
 */
public class EducationalAchievementDaoHibernateImpl 
		extends GenericHibernateDaoImpl<EducationalAchievement>
		implements EducationalAchievementDao {

	/* Query Names */
	
	private static final String FIND_EDUCATIONAL_ACHIEVEMENT_QUERY_NAME 
		= "findEducationalAchievement";
	
	private static final String 
		FIND_EDUCATIONAL_ACHIEVEMENTS_BY_EDUCATION_TERM_QUERY_NAME 
			= "findEducationalAchievementsByEducationTerm";
	
	private static final String
	FIND_EDUCATIONAL_ACHIEVEMENTS_EXCLUDING_DEGREE_BY_EDUCATION_TERM_QUERY_NAME
			= "findEducationalAchievementsExcludingDegreeByEducationTerm";
	
	private static final String FIND_EDUCATIONAL_ACHIEVEMENT_EXCLUDING_QUERY_NAME 
		= "findEducationalAchievementExcluding";
	
	/* Parameter names */
	
	private static final String DATE_PARAM_NAME = "date";
	
	private static final String DESCRIPTION_PARAM_NAME = "description";
	
	private static final String EDUCATION_TERM_PARAM_NAME = "educationTerm";
	
	private static final String EDUCATIONAL_ACHIEVEMENT_PARAM_NAME 
		= "educationalAchievement";
	
	/**
	 * @param sessionFactory
	 * @param entityName
	 */
	protected EducationalAchievementDaoHibernateImpl(
			SessionFactory sessionFactory, String entityName) {
		super(sessionFactory, entityName);
	}
	
	/**{@inheritDoc} */
	@Override
	public EducationalAchievement find(final Date date, final String description,
			final EducationTerm educationTerm) {
		EducationalAchievement educationalAchievement 
			= (EducationalAchievement) this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(FIND_EDUCATIONAL_ACHIEVEMENT_QUERY_NAME)
				.setParameter(DATE_PARAM_NAME, date)
				.setParameter(DESCRIPTION_PARAM_NAME, description)
				.setParameter(EDUCATION_TERM_PARAM_NAME, educationTerm)
				.uniqueResult();
		return educationalAchievement;
	}

	/**{@inheritDoc} */
	@Override
	public EducationalAchievement findExcluding(final Date date, 
			final String description, final EducationTerm educationTerm,
			final EducationalAchievement educationalAchievement) {
		EducationalAchievement foundEducationalAchievement 
			= (EducationalAchievement) this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(FIND_EDUCATIONAL_ACHIEVEMENT_EXCLUDING_QUERY_NAME)
				.setParameter(DATE_PARAM_NAME, date)
				.setParameter(DESCRIPTION_PARAM_NAME, description)
				.setParameter(EDUCATION_TERM_PARAM_NAME, educationTerm)
				.setParameter(EDUCATIONAL_ACHIEVEMENT_PARAM_NAME, 
						educationalAchievement)
				.uniqueResult();
		return foundEducationalAchievement;
	}

	/**{@inheritDoc} */
	@Override
	public List<EducationalAchievement> findByEducationTerm(
			final EducationTerm educationTerm) {
		@SuppressWarnings("unchecked")
		List<EducationalAchievement> educationalAchievements 
			= this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(
					FIND_EDUCATIONAL_ACHIEVEMENTS_BY_EDUCATION_TERM_QUERY_NAME)
				.setParameter(EDUCATION_TERM_PARAM_NAME, educationTerm).list();
		return educationalAchievements;
	}
	
	/**{@inheritDoc} */
	@Override
	public List<EducationalAchievement> findAllExcludingDegreeByEducationTerm(
			final EducationTerm educationTerm) {
		
		@SuppressWarnings("unchecked")
		List<EducationalAchievement> educationalAchievements 
			= this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(
	FIND_EDUCATIONAL_ACHIEVEMENTS_EXCLUDING_DEGREE_BY_EDUCATION_TERM_QUERY_NAME)
				.setParameter(EDUCATION_TERM_PARAM_NAME, educationTerm).list();
		return educationalAchievements;
		
	}

}
