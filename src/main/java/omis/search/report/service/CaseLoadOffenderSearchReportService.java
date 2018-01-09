package omis.search.report.service;

import java.util.List;

import omis.person.domain.Person;
import omis.search.report.OffenderSearchResult;
/** Case Load search summary service.
 * @author Ryan Johns
 * @version 0.1.0 (Oct 10, 2013)
 * @since OMIS 3.0 */
public interface CaseLoadOffenderSearchReportService {

    /** List Offenders by person.
     * @param person Person case worker.
     * @return list of offender search results. */
    List<OffenderSearchResult> findCaseLoadOffenderSearchByPerson(
    		Person person);
}
