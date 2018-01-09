package omis.beans.form;

/** Form option bean.
 * @author Ryan Johns
 * @version 0.1.0 (Apr 29, 2014)
 * @since OMIS 3.0 */
public class Option {
	private final String value;
	private final String name;

	/** Constructor. */
	public Option(final String name, final Object value) {
		this.name = name;
		this.value = String.valueOf(value);
	}


	/** Gets name.
	 * @return name. */
	public String getName() {
		return this.name;
	}

	/** Gets value.
	 * @return value. */
	public String getValue() {
		return this.value;
	}
}
