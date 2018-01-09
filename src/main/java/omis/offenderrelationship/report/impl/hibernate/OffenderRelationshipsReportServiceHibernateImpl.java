package omis.offenderrelationship.report.impl.hibernate;

import java.util.List;

import omis.offender.domain.Offender;
import omis.offenderrelationship.report.OffenderRelationshipsReportService;
import omis.offenderrelationship.report.OffenderRelationshipsSummary;

import org.hibernate.SessionFactory;

/**
 * Offender relationships report service hibernate implementation.
 * 
 * @author Joel Norris
 * @version 0.1.0 (Jun 11, 2015)
 * @since OMIS 3.0
 */
public class OffenderRelationshipsReportServiceHibernateImpl 
	implements OffenderRelationshipsReportService {

	private SessionFactory sessionFactory;
	
	/* Query names. */
	
	private static final String 
		SUMMARIZE_CRIMINAL_ASSOCIATIONS_BY_OFFENDER_QUERY_NAME
		= "summarizeCriminalAssociationsByOffender";
	
	private static final String 
		SUMMARIZE_FAMILY_ASSOCIATIONS_BY_OFFENDER_QUERY_NAME
		= "summarizeFamilyAssociationsByOffender";
	
	private static final String 
		SUMMARIZE_VICTIM_ASSOCIATIONS_BY_OFFENDER_QUERY_NAME
		= "summarizeVictimAssociationsByOffender";
	
	private static final String 
		SUMMARIZE_VISITATION_ASSOCIATIONS_BY_OFFENDER_QUERY_NAME
		= "summarizeVisitationAssociationsByOffender";
	
	/* Parameter names. */
	
	private static final String OFFENDER_PARAM_NAME = "offender";
	
	/* Constructors. */
	
	/**
	 * Instantiates an offender relationships report service.
	 * @param sessionFactory session factory
	 */
	public OffenderRelationshipsReportServiceHibernateImpl(
			final SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	/* Getters and setters. */

	/**
	 * Returns the session factory.
	 * 
	 * @return session factory
	 */
	public SessionFactory getSessionFactory() {
		return this.sessionFactory;
	}

	/**
	 * Sets the session factory.
	 * 
	 * @param sessionFactory session factory
	 */
	public void setSessionFactory(final SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	/* Method implementations. */

	/** {@inheritDoc} */
	@Override
	public List<OffenderRelationshipsSummary> findByOffender(
			final Offender offender) {
		List <OffenderRelationshipsSummary> summaries
			= this.findAssocationsForSummarization(
					SUMMARIZE_CRIMINAL_ASSOCIATIONS_BY_OFFENDER_QUERY_NAME,
					offender);
		this.mergeRelationshipsSummaries(summaries, 
				this.findAssocationsForSummarization(
					SUMMARIZE_FAMILY_ASSOCIATIONS_BY_OFFENDER_QUERY_NAME,
					offender));
		this.mergeRelationshipsSummaries(summaries, 
				this.findAssocationsForSummarization(
					SUMMARIZE_VICTIM_ASSOCIATIONS_BY_OFFENDER_QUERY_NAME,
					offender));
		this.mergeRelationshipsSummaries(summaries, 
				this.findAssocationsForSummarization(
					SUMMARIZE_VISITATION_ASSOCIATIONS_BY_OFFENDER_QUERY_NAME,
					offender));
		return summaries;
	}
	
	/* Helper methods. */
	
	/*
	 * 
	 * @param queryName query name
	 * @param offender offender
	 * @return list of offender relationships summaries
	 */
	@SuppressWarnings("unchecked")
	private List<OffenderRelationshipsSummary> findAssocationsForSummarization(
			final String queryName, final Offender offender) {
		return this.getSessionFactory().getCurrentSession()
				.getNamedQuery(queryName)
				.setParameter(OFFENDER_PARAM_NAME, offender)
				.list();
	}
	
	/*
	 * Merges two separate offender relationships summaries lists into one by
	 * either adding a category name to an existing summary, or adding a new
	 * summary to the end of the summaries list.
	 * 
	 * @param summaries offender relationships summaries list
	 * @param summariesToCompare offender relationships summaries list 
	 * to compare
	 * @return updated offender relationships summaries list
	 */
	private List<OffenderRelationshipsSummary> mergeRelationshipsSummaries(
			final List<OffenderRelationshipsSummary> summaries, 
			final List<OffenderRelationshipsSummary> summariesToCompare) {
		for (OffenderRelationshipsSummary summary : summariesToCompare) {
			int index = summaries.indexOf(summary);
			if (index > -1) {
				summaries.set(index, replaceRelationshipsSummary(
						summaries.get(index), summary));
			} else {
				summaries.add(summary);
			}
		}
		return summaries;
	}
	
	/*
	 * Replaces the original offender relationships summary, with the specified
	 * replacement, while upholding original category names.
	 * 
	 * @param original original offender relationships summary
	 * @param replacement replacement offender relationships summary
	 * @return new replacement offender relationships summary
	 */
	private OffenderRelationshipsSummary replaceRelationshipsSummary(
			final OffenderRelationshipsSummary original,
			final OffenderRelationshipsSummary replacement) {
		String criminalAssociationCategoryName = 
				this.compareCategoryNames(
						original.getCriminalAssociationCategoryName(),
						replacement.getCriminalAssociationCategoryName());
		String familyAssociationCategoryName = 
				this.compareCategoryNames(
						original.getFamilyAssociationCategoryName(),
						replacement.getFamilyAssociationCategoryName());
		String visitationAssociationCategoryName = 
				this.compareCategoryNames(
						original.getVisitorAssociationCategoryName(),
						replacement.getVisitorAssociationCategoryName());
		return new OffenderRelationshipsSummary(
				replacement.getAssociationId(), replacement.getPersonId(), 
				replacement.getPersonLastName(), 
				replacement.getPersonFirstName(), 
				replacement.getPersonMiddleName(), 
				criminalAssociationCategoryName, familyAssociationCategoryName,
				visitationAssociationCategoryName);
	}
	
	/*
	 * Compares two names. If the original is null, or empty, use the specified
	 * potential name. Otherwise use the original name.
	 * 
	 * @param originalName original name
	 * @param potentialName potential name
	 * @return name
	 */
	private String compareCategoryNames(final String originalName,
			final String potentialName) {
		if (originalName == null || originalName.length() < 1) {
			return potentialName;
		} else {
			return originalName;
		}
	}
}