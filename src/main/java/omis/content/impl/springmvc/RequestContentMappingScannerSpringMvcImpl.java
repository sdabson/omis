package omis.content.impl.springmvc;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import omis.content.RequestContent;
import omis.content.RequestContentMapping;
import omis.content.RequestContentMappingScanner;
import omis.content.RequestContentType;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Spring MVC implementation of a screen scanner.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (June 15, 2012)
 * @since OMIS 3.0
 */
public class RequestContentMappingScannerSpringMvcImpl
		implements RequestContentMappingScanner {
	
	/**
	 * Instantiates a default request content mapping scanner from Spring MVC.
	 */
	public RequestContentMappingScannerSpringMvcImpl() {
		// Default instantiation
	}

	/** {@inheritDoc} */
	@Override
	public List<RequestContent> getRequestContents() {
		ClassPathScanningCandidateComponentProvider scanner =
				new ClassPathScanningCandidateComponentProvider(false);
		scanner.addIncludeFilter(new AnnotationTypeFilter(Controller.class));
		List<RequestContent> screens = new ArrayList<RequestContent>();
		for (BeanDefinition beanDef : scanner.findCandidateComponents("omis")) {
			Class<? extends Object> clazz;
			try {
				clazz = Class.forName(beanDef.getBeanClassName());
			} catch (ClassNotFoundException cnfe) {
				// Is this possible?
				throw new AssertionError(cnfe);
			}
			RequestMapping controllerRequestMapping = clazz
					.getAnnotation(RequestMapping.class);
			PreAuthorize controllerPreAuthorize =
					clazz.getAnnotation(PreAuthorize.class);
			for (Method method : clazz.getDeclaredMethods()) {
				RequestContentMapping screenMapping = method.getAnnotation(
						RequestContentMapping.class);
				if (screenMapping != null) {
					RequestMapping methodRequestMapping = method
							.getAnnotation(RequestMapping.class);
					if (!(controllerRequestMapping == null
							&& methodRequestMapping == null)) {
						String[] controllerValues;
						if (controllerRequestMapping == null) {
							controllerValues = new String[] { "" };
						} else {
							controllerValues =
									controllerRequestMapping.value();
						}
						for (String controllerValue : controllerValues) {
							for (String methodValue
									: methodRequestMapping.value()) {
								String url = controllerValue + methodValue;
								PreAuthorize methodPreAuthorize = 
										method.getAnnotation(
												PreAuthorize.class);
								String authorizationRequired =
										getRequiredAuthorization(
												controllerPreAuthorize,
												methodPreAuthorize);
								screens.add(createScreen(url,
										screenMapping.nameKey(),
										screenMapping.descriptionKey(),
										screenMapping.messageBundle(),
										screenMapping.screenType(),
										authorizationRequired,
										clazz.getName(),
										method.getName()));
							}
						}
					} else {
						throw new RuntimeException("Misconfiguration "
							+ "- mapped screens require mapped requests");
					}
				}
			}
		}
		return screens;
	}
	
	// Returns a string describing authorization requirements
	private String getRequiredAuthorization(
			final PreAuthorize controllerPreAuthorize,
			final PreAuthorize methodPreAuthorize) {
		String authorizationRequired;
		if (controllerPreAuthorize != null
				|| methodPreAuthorize != null) {
			authorizationRequired =
					(controllerPreAuthorize != null
							? controllerPreAuthorize.value() : "")	
				+ (controllerPreAuthorize != null
						&& methodPreAuthorize != null ? " and " : "") +
					(methodPreAuthorize != null
							? methodPreAuthorize.value() : "");
		} else {
			authorizationRequired = "";
		}
		return authorizationRequired;
	}
	
	// Returns a newly instantiated screen detail with the specified properties
	private RequestContent createScreen(final String url, final String nameKey,
			final String descriptionKey, final String messageBundle,
			final RequestContentType screenType,
			final String requiredAuthorization, final String className,
			final String methodName) {
		return new RequestContent() {

			/** {@inheritDoc} */
			@Override
			public String getUrl() {
				return url;
			}
			
			/** {@inheritDoc} */
			@Override
			public String getMessageBundle() {
				return messageBundle;
			}
			
			/** {@inheritDoc} */
			@Override
			public String getNameKey() {
				return nameKey;
			}
			
			/** {@inheritDoc} */
			@Override
			public String getDescriptionKey() {
				return descriptionKey;
			}

			/** {@inheritDoc} */
			@Override
			public RequestContentType getType() {
				return screenType;
			}

			/** {@inheritDoc} */
			@Override
			public String getRequiredAuthorization() {
				return requiredAuthorization;
			}

			/** {@inheritDoc} */
			@Override
			public String getClassName() {
				return className;
			}

			/** {@inheritDoc} */
			@Override
			public String getMethodName() {
				return methodName;
			}
		};
	}
}