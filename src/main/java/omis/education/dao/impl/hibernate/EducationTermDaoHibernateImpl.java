package omis.education.dao.impl.hibernate;

import java.util.List;

import org.hibernate.SessionFactory;

import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.education.dao.EducationTermDao;
import omis.education.domain.EducationTerm;
import omis.education.domain.InstituteCategory;
import omis.offender.domain.Offender;

/**
 * EducationTermDaoHibernateImpl.java
 * 
 *@author Annie Jacques 
 *@author Ryan Johns
 *@version 0.1.1 (Nov 21, 2016)
 *@since OMIS 3.0
 *
 */
public class EducationTermDaoHibernateImpl 
	extends GenericHibernateDaoImpl<EducationTerm> 
	implements EducationTermDao {
	
	/* Query Names */
	
	private static final String FIND_EDUCATION_TERM_QUERY_NAME 
		= "findEducationTerm";
	
	private static final String FIND_EDUCATION_TERMS_BY_OFFENDER_QUERY_NAME 
		= "findEducationTermsByOffender";
	
	private static final String FIND_EDUCATION_TERM_EXCLUDING_QUERY_NAME 
		= "findEducationTermExcluding";
	
	/* Parameter names */
	
	private static final String OFFENDER_PARAM_NAME = "offender";
	
	private static final String DESCRIPTION_PARAM_NAME = "description";
	
	private static final String EDUCATION_TERM_PARAM_NAME = "educationTerm";
	
	private static final String INSTITUTE_CATEGORY_PARAM_NAME = "instituteCategory";
	
	private static final String INSTITUTE_NAME_PARAM_NAME = "instituteName";
	
	/**
	 * @param sessionFactory
	 * @param entityName
	 */
	protected EducationTermDaoHibernateImpl(SessionFactory sessionFactory, 
			String entityName) {
		super(sessionFactory, entityName);
	}
	
	
	/**{@inheritDoc} */
	@Override
	public EducationTerm find(final Offender offender, 
			final String description) {
		EducationTerm educationTerm = (EducationTerm) this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(FIND_EDUCATION_TERM_QUERY_NAME)
				.setParameter(OFFENDER_PARAM_NAME, offender)
				.setParameter(DESCRIPTION_PARAM_NAME, description)
				.uniqueResult();
		return educationTerm;
	}

	/**{@inheritDoc} */
	@Override
	public EducationTerm findExcluding(final Offender offender, 
			final String description, 
			final InstituteCategory instituteCategory, 
			final String instituteName,  final EducationTerm educationTerm) {
		EducationTerm foundEducationTerm = (EducationTerm) this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(FIND_EDUCATION_TERM_EXCLUDING_QUERY_NAME)
				.setParameter(OFFENDER_PARAM_NAME, offender)
				.setParameter(DESCRIPTION_PARAM_NAME, description)
				.setEntity(INSTITUTE_CATEGORY_PARAM_NAME, instituteCategory)
				.setString(INSTITUTE_NAME_PARAM_NAME, instituteName)
				.setParameter(EDUCATION_TERM_PARAM_NAME, educationTerm)
				.uniqueResult();
		return foundEducationTerm;
	}

	/**{@inheritDoc} */
	@Override
	public List<EducationTerm> findByOffender(final Offender offender) {
		@SuppressWarnings("unchecked")
		List<EducationTerm> educationTerms = this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(FIND_EDUCATION_TERMS_BY_OFFENDER_QUERY_NAME)
				.setParameter(OFFENDER_PARAM_NAME, offender).list();
		return educationTerms;
	}

}
