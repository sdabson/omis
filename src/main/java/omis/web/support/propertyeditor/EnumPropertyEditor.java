package omis.web.support.propertyeditor;

import java.beans.PropertyEditorSupport;

/** Property Editor for Enum Types.
 * @author Ryan Johns
 * @version 0.1.0 (July 3, 2012)
 * @since OMIS 3.0
 * @param <T> enumerated type. */
public class EnumPropertyEditor<T extends Enum<T>>
	extends PropertyEditorSupport {

	private final Class<T> clazz;

	/** Constructor.
	 * @param o class. */
	public EnumPropertyEditor(final Class<T> o) { this.clazz = o; }

	/* Binds a String to a Enum Type <T>
	 * @param text String. */
	@Override
	public void setAsText(final String text) {

		if (text != null && text.length() > 0) {
			this.setValue(Enum.valueOf(this.clazz, text));
		} else {
			this.setValue(null);
		}
	}

	/* Binds an Enum Type <T> to a String.
	 * @return String.*/
	@Override
	public String getAsText() {
		final Enum<?> e = (Enum<?>) this.getValue();
		String result = "";

		if (e != null) {
			result = e.name();
		}

		return result;
	}
}
