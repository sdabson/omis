package omis.web.dao.impl.hibernate;

import java.util.List;

import org.hibernate.SessionFactory;

import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.web.dao.FormFieldTipDao;
import omis.web.domain.FormFieldTip;

/**
 * Hibernate implementation of form field tip.
 *
 * @author Stephen Abson
 * @version 0.0.1 (Sep 17, 2015)
 * @since OMIS 3.0
 */
public class FormFieldTipDaoHibernateImpl
		extends GenericHibernateDaoImpl<FormFieldTip>
		implements FormFieldTipDao {

	/* Query names. */
	
	private static final String FIND_BY_FORM_QUERY_NAME
		= "findFormFieldTipsByForm";

	/* Parameter names. */
	
	private static final String FORM_PARAM_NAME = "form";
	
	/* Property names. */
	
	private static final String FORM_PROPERTY_NAME = "form";
	
	private static final String FIELD_NAME_PROPERTY_NAME = "fieldName";

	/* Constructors. */
	
	/**
	 * Instantiates Hibernate implementation of form field tip.
	 * 
	 * @param sessionFactory session factory
	 * @param entityName entity name
	 */
	public FormFieldTipDaoHibernateImpl(
			final SessionFactory sessionFactory,
			final String entityName) {
		super(sessionFactory, entityName);
	}

	/* Method implementations. */
	
	/** {@inheritDoc} */
	@Override
	public List<FormFieldTip> findByForm(
			final String form) {
		@SuppressWarnings("unchecked")
		List<FormFieldTip> tips = this.getSessionFactory().getCurrentSession()
				.getNamedQuery(FIND_BY_FORM_QUERY_NAME)
				.setParameter(FORM_PARAM_NAME, form)
				.list();
		return tips;
	}

	/** {@inheritDoc} */
	@Override
	public FormFieldTip find(
			final String form,
			final String fieldName) {
		FormFieldTip tip = (FormFieldTip)
				this.getSessionFactory().getCurrentSession()
				.byNaturalId(this.getEntityName())
				.using(FORM_PROPERTY_NAME, form)
				.using(FIELD_NAME_PROPERTY_NAME, fieldName)
				.load();
		return tip;
	}
}