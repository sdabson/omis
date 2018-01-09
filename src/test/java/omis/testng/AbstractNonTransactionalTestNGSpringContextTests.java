package omis.testng;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.support.GenericXmlContextLoader;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;

/**
 * Provides access to application context for non-transactional tests.
 * 
 * @author Stephen Abson
 * @version 0.0.1 (Nov 29, 2017)
 * @since OMIS 3.0
 */
@ContextConfiguration(
		locations = { "file:src/test/config/applicationContext-test.xml" },
		loader = GenericXmlContextLoader.class
)
public abstract class AbstractNonTransactionalTestNGSpringContextTests
		extends AbstractTestNGSpringContextTests {
	
	// No additional functionality
}
