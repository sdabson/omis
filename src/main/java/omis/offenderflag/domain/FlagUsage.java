package omis.offenderflag.domain;

/**
 * FlagUsage.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Dec 15, 2016)
 *@since OMIS 3.0
 *
 */
public enum FlagUsage {
	
	REQUIREMENT,
	
	SECURITY;
	
	
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
