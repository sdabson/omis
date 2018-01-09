package omis.courtcasecondition.dao;

import java.util.List;

import omis.condition.domain.Agreement;
import omis.courtcasecondition.domain.CourtCaseAgreement;
import omis.courtcasecondition.domain.CourtCaseAgreementCategory;
import omis.dao.GenericDao;
import omis.docket.domain.Docket;
import omis.offender.domain.Offender;

/**
 * Data access object for CourtCaseAgreement.
 * 
 * @author Jonny Santy
 * @author Annie Jacques
 * @version 0.1.1 (Aug 29, 2017)
 * @since OMIS 3.0
 */
public interface CourtCaseAgreementDao  extends 
GenericDao<CourtCaseAgreement> {

	/**
	 * Returns a CourtCaseAgreement by it's composite primary business key.
	 * @param agreement agreement we want Agreements from
	 * @param category CourtCaseAgreementCategory we want Agreements from
	 * @param Docket docket
	 * @return CourtCaseAgreement relevant to key.
	 */
	CourtCaseAgreement find(Agreement agreement, CourtCaseAgreementCategory 
			category, Docket docket);



	/**
	 * Returns CourtCaseAgreements excluding the passed in CourtCaseAgreement
	 *  by it's composite primary business key.
	 * @param agreement agreement we want Agreements from
	 * @param category CourtCaseAgreementCategory we want Agreements from
	 * @param Docket docket
	 * @return CourtCaseAgreement relevant to key.
	 */
	CourtCaseAgreement findExcluding(CourtCaseAgreement courtCaseAgreement,
			Agreement agreement, CourtCaseAgreementCategory category, 
			Docket docket);

	/**
	 * Returns a list of CourtCaseAgreements by specified Offender
	 * @param offender - Offender
	 * @return List of CourtCaseAgreements by specified Offender
	 */
	List<CourtCaseAgreement> findByOffender(Offender offender);

	
}
