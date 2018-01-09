/*
 * OMIS - Offender Management Information System
 * Copyright (C) 2011 - 2017 State of Montana
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package omis.supervision.web.summary;

import java.util.Date;
import java.util.Map;

import omis.offender.domain.Offender;
import omis.offender.web.summary.AbstractSummaryItem;
import omis.offender.web.summary.SummaryItem;
import omis.offender.web.summary.SummaryItemRegistry;
import omis.supervision.report.SupervisionSummaryReportService;

/** Summary item for supervision.
 * @author Ryan Johns
 * @author Stephen Abson
 * @version 0.1.0 (Jul 29, 2015)
 * @since OMIS 3.0 */
public class SupervisionSummaryItem 
	extends AbstractSummaryItem 
	implements SummaryItem {
	private static final long serialVersionUID = 1L;
	private static final String SUPERVISION_SUMMARY_MODEL_KEY =
			"supervisionSummary";
	private final SupervisionSummaryReportService 
		supervisionSummaryReportService;
	
	/** Constructor.
	 * @param supervisionSummaryReportService - supervision summary report 
	 * service.
	 * @param summaryItemRegistry - summary item registry.
	 * @param includedPageName - included page name. 
	 * @param order - order.
	 * @param enabled - enabled*/
	public SupervisionSummaryItem(
			final SupervisionSummaryReportService 
				supervisionSummaryReportService, 
				final SummaryItemRegistry summaryItemRegistry, 
				final String includedPageName, final int order,
				final boolean enabled) {
		super(includedPageName, summaryItemRegistry, order, enabled);
		this.supervisionSummaryReportService = supervisionSummaryReportService;
	}
	
	/** {@inheritDoc} */
	@Override
	public void build(final Map<String, Object> map, final Offender offender, 
			final Date date) {
		map.put(SUPERVISION_SUMMARY_MODEL_KEY,
				this.supervisionSummaryReportService.findByOffenderAndDate(
							offender, date));
	}
}
