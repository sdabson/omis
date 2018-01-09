package omis.offender.web.summary;

/** Abstract implementation of summary item.
 * @author Ryan Johns
 * @version 0.1.0 (Jul 27, 2015)
 * @since OMIS 3.0 */
public abstract class AbstractSummaryItem implements SummaryItem {
	private static final long serialVersionUID = 1L;
	private final String includedPageName;
	private final SummaryItemRegistry summaryItemRegistry;
	private final int order;
	private final boolean enabled;
	
	/** Constructor.
	 * @param includedPageName - page to be included.
	 * @param summaryItemRegistry - registry to register summary items. 
	 * @param order - order for comparison. */
	public AbstractSummaryItem(final String includedPageName, 
			final SummaryItemRegistry summaryItemRegistry, 
			final int order, final boolean enabled) {
		this.includedPageName = includedPageName;
		this.summaryItemRegistry = summaryItemRegistry;
		this.order = order;	
		this.enabled = enabled;
		this.summaryItemRegistry.register(this);
		
	}
	
	/** {@inheritDoc} */
	@Override
	public int getOrder() { 
		return this.order; 
	}
	
	/** {@inheritDoc} */
	@Override
	public String getIncludedPageName() { 
		return this.includedPageName; 
	}
	
	/** {@inheritDoc} */
	@Override
	public boolean getEnabled() {
		return this.enabled;
	}
	
	/** {@inheritDoc} */
	@Override
	public int compareTo(final SummaryItem that) {
		return this.getOrder() - that.getOrder();
	}
}
