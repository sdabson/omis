package omis.trackeddocument.web.validator;

import omis.trackeddocument.web.form.TrackedDocumentForm;
import omis.trackeddocument.web.form.TrackedDocumentReceivalItem;
import omis.trackeddocument.web.form.TrackedDocumentReceivalItemOperation;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * Validator for form for tracked document.
 * 
 * @author Yidong
 * @version 0.1.0 (Jan 4, 2018)
 * @since OMIS 3.0
 */
public class TrackedDocumentFormValidator
	implements Validator {

	/** Instantiates a validator for form for tracked document. */
	public TrackedDocumentFormValidator() {
		// Default instantiation
	}
	
	/** {@inheritDoc} */
	@Override
	public boolean supports(final Class<?> clazz) {
		return TrackedDocumentForm.class.isAssignableFrom(clazz);
	}

	/** {@inheritDoc} */
	@Override
	public void validate(final Object target, final Errors errors) {
		TrackedDocumentForm form = (TrackedDocumentForm) target;
		
		if (form.getTrackedDocumentReceivalItems()!= null) {
			for (int index = 0; index < form.getTrackedDocumentReceivalItems()
				.size(); index++) {
				if(TrackedDocumentReceivalItemOperation.CREATE.equals(
					form.getTrackedDocumentReceivalItems().get(index)
					.getOperation())){
					TrackedDocumentReceivalItem item
					= form.getTrackedDocumentReceivalItems().get(index);
					if(form.getDocket()==null){
						errors.rejectValue("docket","docket.empty");
					}
					
//					if (TrackedDocumentReceivalItemOperation.CREATE.equals(
//							item.getOperation())) {
						if (item.getCategory() == null) {
							errors.rejectValue("trackedDocumentReceivalItems[" 
									+ index + "].category", "category.empty");
						}
						if (item.getReceivedByUserAccount() == null) {
							errors.rejectValue("trackedDocumentReceivalItems[" 
							+ index + "].receivedByUserAccount",
							"receivedByUserAccount.empty");
						}
//					}
				
					for (int innerIndex = index+1;
						innerIndex < form.getTrackedDocumentReceivalItems()
						.size(); innerIndex++) {
						if((form.getTrackedDocumentReceivalItems().get(index)
							.getCategory().equals(
							form.getTrackedDocumentReceivalItems()
							.get(innerIndex).getCategory()))&&(
							form.getTrackedDocumentReceivalItems().get(index)
							.getReceivedDate().equals(
							form.getTrackedDocumentReceivalItems()
							.get(innerIndex).getReceivedDate()))){
							errors.rejectValue(
							"trackedDocumentReceivalItems["	+ innerIndex
								+ "].category", 
								"trackedDocumentReceivalItem.duplicate");
							break;
						}
					}
				}
			}
		}
	}
}