package omis.search.report.service.impl.hibernate;

import java.util.ArrayList;
import java.util.List;

import omis.search.report.PersonSearchResult;
import omis.search.util.PersonRegexUtility;

import org.hibernate.Query;
import org.hibernate.SessionFactory;

/** provides search operations for persons features.
 * @author Ryan Johns
 * @version 0.1.0 (Apr 24, 2013)
 * @since OMIS 3.0
 * @param <T> personSearchResult */
public abstract class AbstractPersonSearchReportServiceHibernateImpl
	<T extends PersonSearchResult> {

	private SessionFactory sessionFactory;

	/** constructor.
	 * @param sessionFactory session factory. */
	protected AbstractPersonSearchReportServiceHibernateImpl(
			final SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	/** gets find by first last name Query name.
	 * @return name. */
	public abstract String getFindByFirstLastNameSearchQuery();

	/** gets find by first last name param names.
	 * @return params */
	public abstract String[] getFindByFirstLastNameSearchParams();

	/** gets find by first middle last name query name.
	 * @return name. */
	public abstract String getFindByFirstMiddleLastNameSearchQuery();

	/** gets find by first middle last name param names.
	 * @return params. */
	public abstract String[] getFindByFirstMiddleLastNameSearchParams();

	/** find by name search query name.
	 * @return name. */
	public abstract String getFindPersonNameByNameSearchQuery();

	/** find by name search param names.
	 * @return params. */
	public abstract String getFindPersonNameByNameSearchParam();

	/** sets the session factory.
	 * @param sessionFactory session factory. */
	public void setSessionFactory(final SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	/** gets the session factory.
	 * @return sessionFactory session factory. */
	protected SessionFactory getSessionFactory() { return this.sessionFactory; }

	/** find person names by unspecified search criteria.
	 * @param searchCriteria search criteria string.
	 * @return collection of persons. */
	public List<T> findPersonNamesByUnspecified(
			final String searchCriteria) {
		List<T> result = new ArrayList<T>();

		if (PersonRegexUtility.isFirstLast(searchCriteria)) {
			final String[] firstLast = searchCriteria.split("[\\s,]+");

			result = this.findPersonNamesByNameSearch(firstLast[0],
					firstLast[1]);
		} else if (PersonRegexUtility.isFirstMiddleLast(searchCriteria)) {
			final String[] firstMiddleLast = searchCriteria.split("[\\s,]+");

			result = this.findPersonNamesByNameSearch(firstMiddleLast[0],
					firstMiddleLast[1], firstMiddleLast[2]);
		} else if (PersonRegexUtility.isName(searchCriteria)) {
			return this.findPersonNamesByLastName(searchCriteria);
		}

		return result;
	}

	/** Returns person names with specified name search criteria.
	 * @param name1 first or last
	 * @param name2 first or last
	 * @return list of person names. */
	public List<T> findPersonNamesByNameSearch(final String name1,
			final String name2) {
		final Query q = this.sessionFactory.getCurrentSession()
				.getNamedQuery(this.getFindByFirstLastNameSearchQuery());

		q.setParameter(this.getFindByFirstLastNameSearchParams()[0], name1);
		q.setParameter(this.getFindByFirstLastNameSearchParams()[1], name2);

		@SuppressWarnings("unchecked")
		final
		List<T> result = q.list();

		return result;
	}

	/** Returns person with specified search criteria.
	 * @param first name string.
	 * @param middle name string.
	 * @param last name string.
	 * @return list of person names. */
	public List<T> findPersonNamesByNameSearch(final String first,
			final String middle, final String last) {
		final Query q = this.sessionFactory.getCurrentSession()
				.getNamedQuery(this.getFindByFirstMiddleLastNameSearchQuery());

		q.setParameter(this.getFindByFirstMiddleLastNameSearchParams()[0],
				first);
		q.setParameter(this.getFindByFirstMiddleLastNameSearchParams()[1],
				middle);
		q.setParameter(this.getFindByFirstMiddleLastNameSearchParams()[2],
				last);

		@SuppressWarnings("unchecked")
		final
		List<T> result = q.list();

		return result;
	}

	/** Returns person names with last name equaling searchcriteria.
	 * @param name name.
	 * @return list of person names. */
	public List<T> findPersonNamesByLastName(final String name) {
		final Query q = this.sessionFactory.getCurrentSession()
				.getNamedQuery(this.getFindPersonNameByNameSearchQuery());

		q.setString(this.getFindPersonNameByNameSearchParam(), name);

		@SuppressWarnings("unchecked")
		final
		List<T> result = q.list();

		return result;
	}
} //PersonSearchService
