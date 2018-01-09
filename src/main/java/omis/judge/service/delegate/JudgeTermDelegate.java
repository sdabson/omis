package omis.judge.service.delegate;

import java.util.Date;
import java.util.List;

import omis.datatype.DateRange;
import omis.exception.DuplicateEntityFoundException;
import omis.instance.factory.InstanceFactory;
import omis.judge.dao.JudgeTermDao;
import omis.judge.domain.JudgeTerm;
import omis.person.domain.Person;

/**
 * Delegate for judge terms.
 *
 * @author Josh Divine
 * @version 0.0.1 (Apr 10, 2017)
 * @since OMIS 3.0
 */
public class JudgeTermDelegate {
	
	private final JudgeTermDao judgeTermDao;
	
	private final InstanceFactory<JudgeTerm> judgeTermInstanceFactory;
	
	/**
	 * Instantiates a delegate for judge terms.
	 * 
	 * @param judgeTermDao data access object for judge terms
	 * @param judgeTermInstanceFactory instance factory for judge terms
	 */
	public JudgeTermDelegate(final JudgeTermDao judgeTermDao,	
			final InstanceFactory<JudgeTerm> judgeTermInstanceFactory) {
		this.judgeTermDao = judgeTermDao;
		this.judgeTermInstanceFactory = judgeTermInstanceFactory;
	}
	
	/**
	 * Creates a new judge term.
	 * 
	 * @param judge judge
	 * @param dateRange date range
	 * @return judge term
	 * @throws DuplicateEntityFoundException thrown if judge term already exists
	 */
	public JudgeTerm create(Person judge, DateRange dateRange) 
			throws DuplicateEntityFoundException {
		if (this.judgeTermDao.find(judge, dateRange) != null) {
			throw new DuplicateEntityFoundException(
					"Judge term already exists.");
		}
		JudgeTerm judgeTerm = this.judgeTermInstanceFactory.createInstance();
		judgeTerm.setJudge(judge);
		judgeTerm.setDateRange(dateRange);
		return this.judgeTermDao.makePersistent(judgeTerm);
	}
	
	/**
	 * Updates an existing judge term with the specified date range.
	 * 
	 * @param judgeTerm judge term
	 * @param dateRange date range
	 * @return judge term
	 * @throws DuplicateEntityFoundException thrown in judge term already exists
	 */
	public JudgeTerm update(JudgeTerm judgeTerm, DateRange dateRange) 
			throws DuplicateEntityFoundException {
		if (this.judgeTermDao.findExcluding(judgeTerm, judgeTerm.getJudge(), 
				dateRange) != null) {
			throw new DuplicateEntityFoundException(
					"Judge term already exists.");
		}
		judgeTerm.setDateRange(dateRange);
		return this.judgeTermDao.makePersistent(judgeTerm);
	}
	
	/**
	 * Returns judges that are active on the specified date.
	 * 
	 * @param effectiveDate effective date
	 * @return
	 */
	public List<Person> findJudgesOnDate(final String query, 
			final Date effectiveDate) {
		if (query != null && query.contains(",")) {
			String lastName = query.substring(0, query.indexOf(","));
			String firstName = query.substring(query.indexOf(",") + 1).trim();
			return this.judgeTermDao.findJudgesByNameOnDate(lastName, firstName, 
					effectiveDate);
		}
		return this.judgeTermDao.findJudgesQueryOnDate(query, effectiveDate);
	}
}
