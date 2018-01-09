package omis.condition.service.delegate;

import java.util.List;

import omis.condition.dao.ConditionTitleDao;
import omis.condition.domain.ConditionTitle;
import omis.instance.factory.InstanceFactory;

/**
 * ConditionTitleDelegate.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (May 15, 2017)
 *@since OMIS 3.0
 *
 */
public class ConditionTitleDelegate {
	
	public final ConditionTitleDao conditionTitleDao;
	
	public final InstanceFactory<ConditionTitle> conditionTitleInstanceFactory;

	/**
	 * Contructor for ConditionTitleDelegate
	 * @param conditionTitleDao
	 */
	public ConditionTitleDelegate(final ConditionTitleDao conditionTitleDao,
			final InstanceFactory<ConditionTitle> conditionTitleInstanceFactory) {
		this.conditionTitleDao = conditionTitleDao;
		this.conditionTitleInstanceFactory = conditionTitleInstanceFactory;
	}
	
	/**
	 * Returns a list of all ConditionTitles
	 * @return List of all ConditionTitles
	 */
	public List<ConditionTitle> findAll() {
		return this.conditionTitleDao.findAll();
	}
	
	/**
	 * Creates a ConditionTitle (for use in Unit Testing)
	 * @param title - String
	 * @return Created ConditionTitle
	 */
	public ConditionTitle create(final String title){
		ConditionTitle conditionTitle = this.conditionTitleInstanceFactory
				.createInstance();
		
		conditionTitle.setTitle(title);
		
		return this.conditionTitleDao.makePersistent(conditionTitle);
	}
	
}
