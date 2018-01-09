package omis.judge.dao;

import java.util.Date;
import java.util.List;

import omis.dao.GenericDao;
import omis.datatype.DateRange;
import omis.judge.domain.JudgeTerm;
import omis.person.domain.Person;

/**
 * Data access object for judge terms.
 *
 * @author Josh Divine
 * @version 0.0.1 (Apr 10, 2017)
 * @since OMIS 3.0
 */
public interface JudgeTermDao
	extends GenericDao<JudgeTerm> {
	
	/**
	 * Returns the judge term matching the specified judge and date range.
	 * 
	 * @param judge judge
	 * @param dateRange date range
	 * @return
	 */
	JudgeTerm find(Person judge, DateRange dateRange);
	
	/**
	 * Returns the judge term matching the specified judge and date range 
	 * excluding the specified judge term.
	 * 
	 * @param excludedJudgeTerm excluded judge term
	 * @param judge judge
	 * @param dateRange date range
	 * @return
	 */
	JudgeTerm findExcluding(JudgeTerm excludedJudgeTerm, Person judge, 
			DateRange dateRange);
	
	/**
	 * Returns judges that are active on the specified date with the specified 
	 * first and last name.
	 * 
	 * @param lastName last name
	 * @param firstName first name
	 * @param effectiveDate effective date
	 * @return
	 */
	List<Person> findJudgesByNameOnDate(String lastName, String firstName, 
			Date effectiveDate);
	
	/**
	 * Returns judges that are active on the specified date with the specified 
	 * name.
	 * 
	 * @param query judge name
	 * @param effectiveDate effective date
	 * @return
	 */
	List<Person> findJudgesQueryOnDate(String query, Date effectiveDate);

}
