package omis.document.web.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/** Registry for document association view items.
 * @author Ryan Johns
 * @version 0.1.0 (Nov 27, 2015)
 * @since OMIS 3.0 */
public class OffenderDocumentAssociationItemRegistry {
	private final Map<String, OffenderDocumentAssociationItem> registry;
	
	/** Constructor. */
	public OffenderDocumentAssociationItemRegistry() {
		this.registry = 
				new HashMap<String, OffenderDocumentAssociationItem>();
	}
	
	/** register.
	 * @param associationName - association name.
	 * @param offenderDocumentAssociationItem - 
	 * 	offender document association view item. 
	 * @return true if successful.*/
	public boolean register(final String associationName,
			final OffenderDocumentAssociationItem 
			offenderDocumentAssociationItem) {
		boolean result = (this.registry.put(associationName, 
				offenderDocumentAssociationItem) != null);
		return result;
	}
	
	/** Gets list of document association view items.
	 * @return list of document association view items. */
	public List<OffenderDocumentAssociationItem> 
		getDocumentAssociationItems() {
		return new ArrayList<OffenderDocumentAssociationItem>(
				this.registry.values());
	}
		
}
