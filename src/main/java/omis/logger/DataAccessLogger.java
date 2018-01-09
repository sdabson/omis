package omis.logger;

import java.lang.reflect.Method;
import java.util.logging.Logger;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

/**
 * Logs data access.
 * 
 * <p>It is recommended that this logger not be used in production or with
 * production data as it may log entity instance values.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (31 Dec, 2011)
 * @since OMIS 3.0
 */
@Component
@Aspect
public class DataAccessLogger {
	
	private final Logger logger = Logger.getLogger(
			DataAccessLogger.class.getName());
	
	/** Instantiates a default data access logger. */
	public DataAccessLogger() {
		// Default instantiation
	}
	
	/**
	 * Logs a method of a data access object implementation.
	 * 
	 * <p>Log includes the method signature details and parameters passed
	 * (if any). If the parameter passed is a persistable entity (has an
	 * {@code id} property, the ID will be displayed as the value of the
	 * parameter, otherwise the {@code toString()} method of the
	 * parameter value will be invoked. The latter case may prove costly
	 * and inefficient.
	 * 
	 * <p>Logs public DAO and report service methods. Aside for the generic DAO,
	 * DAOs must be placed in {@code omis.<module>.dao} and their
	 * implementations in {@code omis.<module>.dao.impl.<implemetation_type>};
	 * report services must be placed in {@code omis.<module>.report} with their
	 * implementations placed in
	 * {@code omis.<module>.report.impl.<implementation_type>}.
	 * 
	 * @param joinPoint join point
	 * @return result of join point method invocation
	 * @throws Throwable if an exception is thrown by the method invocation
	 */
	@Around("execution (public * omis.*.dao.impl..*.*(..))"
			+ " or execution (public * omis.dao.impl..*.*(..))"
			+ " or execution (public * omis.*.report.impl..*.*(..))")
	public Object logMethod(final ProceedingJoinPoint joinPoint)
			throws Throwable {
		String username;
		if (SecurityContextHolder.getContext().getAuthentication() != null) {
			username = SecurityContextHolder.getContext().getAuthentication()
					.getName();
		} else {
			username = "No user";
		}
		Object result;
		Object[] args = joinPoint.getArgs();
		if (args.length > 0) {
			StringBuilder message = new StringBuilder();
			message.append(String.format(
					"%s: Beginning %s with parameters [", username,
					this.getMethodSignature(joinPoint)));
			for (int i = 0; i < args.length; i++) {
				Object arg = args[i];
				String paramValue;
				if (arg != null) {
					try {
						Method method = arg.getClass().getMethod("getId");
						Object idValue = method.invoke(arg);
						if (idValue == null) {
							idValue = "new entity";
						}
						paramValue = "id: " +  idValue.toString();
					} catch (NoSuchMethodException nme) {
						paramValue = arg.toString();
					}
				} else {
					paramValue = "null";
				}
				message.append(paramValue);
				if (i + 1 != args.length) {
					message.append(", ");
				}
			}
			message.append("]");
			this.logger.info(message.toString());
		} else {
			this.logger.info(String.format(
					"%s: Beginning %s with no parameters",
					username,
					this.getMethodSignature(joinPoint)));
		}
		result = joinPoint.proceed();
		this.logger.info(String.format("%s: Finished %s",
				username, this.getMethodSignature(joinPoint)));
		return result;
	}
	
	// Returns a meaningful method signature
	private String getMethodSignature(final ProceedingJoinPoint joinPoint) {
		return String.format("%s in class %s", joinPoint.getSignature(),
				joinPoint.getTarget().getClass().getCanonicalName());
	}
}
