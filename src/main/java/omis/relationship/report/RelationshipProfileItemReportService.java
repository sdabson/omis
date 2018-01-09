package omis.relationship.report;

import omis.offender.domain.Offender;

/** Relationship profile item report service.
 * @author Ryan Johns
 * @version 0.1.0 (Mar 17, 2016)
 * @since OMIS 3.0 */
public interface RelationshipProfileItemReportService {
	/** Finds relationship count by offender.
	 * @author offender - offender.
	 * @return count. */
	Integer findRelationshipCountByOffender(Offender offender);
}
