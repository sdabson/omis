package omis.questionnaire.web.profile;

import java.util.Date;
import java.util.Map;
import java.util.Set;

import omis.offender.domain.Offender;
import omis.questionnaire.report.AdministeredQuestionnaireProfileSummaryReportService;
import omis.user.domain.UserAccount;
import omis.web.profile.AbstractProfileItem;
import omis.web.profile.ProfileItem;
import omis.web.profile.ProfileItemRegistry;

/**
 * AdministeredQuestionnaireProfileItem.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Feb 23, 2017)
 *@since OMIS 3.0
 *
 */
public class AdministeredQuestionnaireProfileItem
	extends AbstractProfileItem
	implements ProfileItem {
	
	private static final long serialVersionUID = 1L;

	private static final String ADMINISTERED_QUESTIONNAIRE_COUNT_MODEL_KEY =
			"administeredQuestionnaireCount";
	
	private final AdministeredQuestionnaireProfileSummaryReportService
		administeredQuestionnaireProfileSummaryReportService;
	
	/**
	 * @param requiredAuthorizations
	 * @param includePage
	 * @param name
	 * @param profileItemRegistry
	 * @param enabled whether enabled
	 */
	public AdministeredQuestionnaireProfileItem(
			final Set<String> requiredAuthorizations,
			final String includePage, final String name,
			final ProfileItemRegistry profileItemRegistry,
			final AdministeredQuestionnaireProfileSummaryReportService
			administeredQuestionnaireProfileSummaryReportService,
			final Boolean enabled) {
		super(requiredAuthorizations, includePage, name, profileItemRegistry,
				enabled);
		this.administeredQuestionnaireProfileSummaryReportService =
				administeredQuestionnaireProfileSummaryReportService;
	}

	/**{@inheritDoc} */
	@Override
	public void build(final Map<String, Object> modelMap,
			final Offender offender, final UserAccount userAccount,
			final Date date) {
		modelMap.put(ADMINISTERED_QUESTIONNAIRE_COUNT_MODEL_KEY,
				this.administeredQuestionnaireProfileSummaryReportService
				.findCountByAnswerer(offender));
		
	}

}
