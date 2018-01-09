package omis.detainernotification.domain;

/**
 * Detainer warrant cancellation reason.
 *
 * @author Sheronda Vaughn
 * @version 0.1.0 (Jul 7, 2016)
 * @since OMIS 3.0
 */
public enum DetainerWarrantCancellationReason {
	
	/** Dropped By Agency */
	DROPPED_BY_AGENCY,
	
	/** Released To Agency */
	RELEASED_TO_AGENCY,
	
	/** Released To Other */
	RELEASED_TO_OTHER,
	
	/** Sentence Imposed */
	SENTENCE_IMPOSED;
	
	/**
	 * Returns the instance name.
	 * 
	 * @return instance name
	 */
	public String getName() {
		return this.name();
	}
	
	/**
	 * Returns the name.
	 * 
	 * @return name
	 */
	
	@Override
	public String toString() {
		return this.name();
	}
}