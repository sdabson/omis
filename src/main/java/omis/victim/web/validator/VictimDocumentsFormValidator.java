package omis.victim.web.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import omis.document.web.form.DocumentTagItem;
import omis.document.web.form.DocumentTagOperation;
import omis.victim.web.form.VictimDocumentItem;
import omis.victim.web.form.VictimDocumentItemOperation;
import omis.victim.web.form.VictimDocumentsForm;

/**
 * Victim documents form validator.
 * 
 * @author Joel Norris
 * @version 0.1.0 (Aug 25, 2017)
 * @since OMIS 3.0
 */
public class VictimDocumentsFormValidator implements Validator {

	/**
	 * Instantiates an instance of victim documents form validator.
	 */
	public VictimDocumentsFormValidator() {
		//Default constructor.
	}
	
	/** {@inheritDoc} */
	@Override
	public boolean supports(final Class<?> clazz) {
		return VictimDocumentsForm.class.isAssignableFrom(clazz);
	}

	/** {@inheritDoc} */
	@Override
	public void validate(final Object target, final Errors errors) {
		VictimDocumentsForm form = (VictimDocumentsForm) target;
		
		int documentItemIndex =  0;
		for (VictimDocumentItem item : form.getDocumentItems()) {
			if (VictimDocumentItemOperation.CREATE.equals(item.getOperation())) {
				if (item.getTitle().isEmpty()) {
					errors.rejectValue("documentItems[" + documentItemIndex + "].title", "documentItem.title.empty");
				}
				if (item.getDate() == null) {
					errors.rejectValue("documentItems[" + documentItemIndex + "].date", "documentItem.date.empty");
				}
				ValidationUtils.rejectIfEmpty(errors, "documentItems[" + documentItemIndex + "].documentData", "documentItem.documentData.empty");
			}
			int tagItemIndex = 0;
			for(DocumentTagItem tagItem : item.getTagItems()) {
				if ((DocumentTagOperation.CREATE.equals(tagItem.getDocumentTagOperation())
						|| DocumentTagOperation.UPDATE.equals(tagItem.getDocumentTagOperation()))
						&& tagItem.getName().isEmpty()) {
					errors.rejectValue("documentItems[" + documentItemIndex + "].tagItems["+ tagItemIndex +"].name", "tagItem.name.empty");
				}
				tagItemIndex++;
			}
			documentItemIndex++;
		}	
	}
}
