package omis.offenderrelationship.web.controller.delegate;

import java.util.List;
import java.util.Map;

import omis.offenderrelationship.report.OffenderRelationshipSummary;
import omis.offenderrelationship.report.OffenderRelationshipSummaryReportService;
import omis.person.domain.Person;

/**
 * Delegate to manage offender relationship summaries in a model.
 * 
 * @author Sheronda Vaughn
 * @since 0.1.0 (Jan 28, 2016)
 * @version OMIS 3.0
 */
public class OffenderRelationshipSummaryModelDelegate {
	
	/* Model keys. */
	
	private static final String OFFENDER_RELATIONSHIP_MODEL_KEY 
		= "offenderRelationshipSummary";
	
	/* Report services. */
	
	private final OffenderRelationshipSummaryReportService 
		offenderRelationshipSummaryReportService;
	
	/* Constructors. */

	/**
	 * Instantiates delegate to manage victim summaries in a model.
	 * 
	 * @param offenderRelationshipSummaryReportService offender relationship
	 * summary report service
	 */
	public OffenderRelationshipSummaryModelDelegate(
			final OffenderRelationshipSummaryReportService 
			offenderRelationshipSummaryReportService) {
		this.offenderRelationshipSummaryReportService 
			= offenderRelationshipSummaryReportService;
	}

	/**
	 * Adds offender relationship summary to model.
	 * 
	 * @param modelMap model map
	 * @param relation relation
	 */
	public void add(
			final Map<String, Object> modelMap, final Person relation) {
		List<OffenderRelationshipSummary> offenderRelationshipSummary 
			= this.offenderRelationshipSummaryReportService.summarizeByName(
				relation.getName().getLastName(), relation.getName()
				.getFirstName(), null);
				
		modelMap.put(OFFENDER_RELATIONSHIP_MODEL_KEY, 				
				offenderRelationshipSummary);
	}
}