/*
 * OMIS - Offender Management Information System
 * Copyright (C) 2011 - 2017 State of Montana
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/
package omis.task.web.controller;

import java.beans.PropertyEditor;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Date;
import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.mapping.PersistentClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import omis.beans.factory.PropertyEditorFactory;
import omis.beans.factory.spring.CustomDateEditorFactory;
import omis.task.domain.Task;
import omis.task.domain.TaskParameterValue;
import omis.task.service.TaskInvocationService;

/**
 * Controller to invoke tasks.
 * 
 * <p>This controller invokes systems components directly to perform systems
 * operations. The controller reads Spring and Hibernate configuration and
 * invokes the Hibernate persistence context. This should not be done normally
 * in controllers that perform business operations.
 * 
 * @author Stephen Abson
 * @author Josh Divine
 * @version 0.1.1
 * @since OMIS 3.0
 */
@Controller
@PreAuthorize("hasRole('USER')")
@RequestMapping("/task")
public class InvokeTaskController {
	
	/* System components */
	
	// Wires application context - this should not be done in controllers
	// that perform business operations
	@Autowired
	private ApplicationContext context;
	
	// Wires Hibernate configuration - this should not be done in controllers
	// that perform business operations
	@Autowired
	private Configuration configuration;
	
	// Wires Hibernate session factory - this should not be done in controllers
	// that perform business operations
	@Autowired
	private SessionFactory sessionFactory;
	
	/* Services. */
	
	@Autowired
	@Qualifier("taskInvocationService")
	private TaskInvocationService taskInvocationService;
	
	/* Property editor factories. */
	
	@Autowired
	@Qualifier("taskPropertyEditorFactory")
	private PropertyEditorFactory taskPropertyEditorFactory;
	
	@Autowired
	@Qualifier("datePropertyEditorFactory")
	private CustomDateEditorFactory customDateEditorFactory;
	
	/* Constructor. */
	
	/** Instantiates controller to invoke task. */
	public InvokeTaskController() {
		// Default instantiation
	}
	
	/* URL invokable methods. */
	
	/**
	 * Invokes task.
	 * 
	 * @return invoked task
	 */
	@RequestMapping(value = "/invoke.html", method = RequestMethod.GET)
	public ModelAndView invokeTask(
			@RequestParam(value = "task", required = true) final Task task) {
		// Iterates through parameter values adding type and value to arrays
		List<TaskParameterValue> parameterValues
			= this.taskInvocationService.findParameterValues(task);
		int parameterIndex = 0;
		int parameterCount = parameterValues.size();
		Class<?>[] parameterTypes = new Class<?>[parameterCount];
		Object[] entityInstances = new Serializable[parameterCount];
		for (TaskParameterValue parameterValue : parameterValues) {
			Serializable entityInstance;
			Class<?> proxyClass;
			
			PersistentClass persistentClass = this.configuration
					.getClassMapping(parameterValue.getTypeName());
			if (persistentClass != null) {
				entityInstance = (Serializable)
						this.sessionFactory.getCurrentSession()
							.load(parameterValue.getTypeName(),
									parameterValue.getInstanceValue());
				proxyClass = persistentClass.getProxyInterface();
			} else {
				try {
					proxyClass = Class.forName(parameterValue.getTypeName());
				} catch (ClassNotFoundException e) {
					throw new RuntimeException("Class not found exception", e);
				}
				if (proxyClass.equals(Date.class)) {
					entityInstance = convertStringToDate(parameterValue
							.getInstanceValue());
				} else if (proxyClass.equals(Boolean.class)) {
					entityInstance = Boolean.valueOf(parameterValue
							.getInstanceValue());
				} else if (proxyClass.equals(String.class)) {
					entityInstance = parameterValue.getInstanceValue();
				} else if (proxyClass.equals(Integer.class)) {
					entityInstance = Integer.valueOf(parameterValue
							.getInstanceValue());
				} else if (proxyClass.equals(Long.class)) {
					entityInstance = Long.valueOf(parameterValue
							.getInstanceValue());
				} else if (proxyClass.isEnum()) {
					try {
						entityInstance = (Serializable) proxyClass.getMethod(
								"valueOf", String.class).invoke(null, 
										parameterValue.getInstanceValue());
					} catch (NoSuchMethodException e) {
						throw new RuntimeException("No such method exception", 
								e);
					} catch (SecurityException e) {
						throw new RuntimeException("Security exception", e);
					} catch (IllegalAccessException e) {
						throw new RuntimeException("Illegal access exception", 
								e);
					} catch (IllegalArgumentException e) {
						throw new RuntimeException("Illegal argument exception", 
								e);
					} catch (InvocationTargetException e) {
						throw new RuntimeException("Invocation target exception", 
								e);
					}
				} else {
					throw new UnsupportedOperationException(
							"Supported type not found: " +
									parameterValue.getTypeName());
				}
			}
			
			// Adds parameter type and instance to arrays
			parameterTypes[parameterIndex] = proxyClass;
			entityInstances[parameterIndex] = entityInstance;
			parameterIndex++;
		}
		
		// Finds and invokes method, returns results
		Object controller = context.getBean(task.getControllerName());
		Method method;
		try {
			method = controller.getClass().getMethod(
					task.getMethodName(), parameterTypes);
		} catch (NoSuchMethodException e) {
			throw new RuntimeException("No such method exception", e);
		} catch (SecurityException e) {
			throw new RuntimeException("Security exception", e);
		}
		ModelAndView mav;
		try {
			mav = (ModelAndView) method.invoke(controller, entityInstances);
		} catch (IllegalAccessException e) {
			throw new RuntimeException("Illegal access exception", e);
		} catch (IllegalArgumentException e) {
			throw new RuntimeException("Illegal argument exception", e);
		} catch (InvocationTargetException e) {
			throw new RuntimeException("Invocation target exception", e);
		}
		return mav;
	}
	
	/**
	 * Invokes task, builds and invokes redirect URL.
	 * 
	 * @param task task
	 * @return redirect URL
	 */
	@RequestMapping(
			value = "/invokeAndRedirect.html", method = RequestMethod.GET)
	public ModelAndView invokeTaskAndRedirect(
			@RequestParam(value = "task", required = true) final Task task) {
		
		// Iterates through parameter values adding type and value to arrays
		List<TaskParameterValue> parameterValues
			= this.taskInvocationService.findParameterValues(task);
		int parameterIndex = 0;
		int parameterCount = parameterValues.size();
		Class<?>[] parameterTypes = new Class<?>[parameterCount];
		for (TaskParameterValue parameterValue : parameterValues) {
			Class<?> proxyClass;
			
			// Checks for entity types first
			PersistentClass persistentClass = this.configuration
					.getClassMapping(parameterValue.getTypeName());
			if (persistentClass != null) {
				proxyClass = persistentClass.getProxyInterface();
			} else {
				try {
					proxyClass = Class.forName(parameterValue.getTypeName());
				} catch (ClassNotFoundException e) {
					throw new RuntimeException("Class not found exception", e);
				}
			}
				
			// Adds parameter type and instance to arrays
			parameterTypes[parameterIndex] = proxyClass;
			parameterIndex++;
		}
		
		// Finds method
		Class<?> controllerClass = context.getType(task.getControllerName())
				.getSuperclass();
		Method method;
		try {
			method = controllerClass.getMethod(
					task.getMethodName(), parameterTypes);
		} catch (NoSuchMethodException e) {
			throw new RuntimeException("No such method exception", e);
		} catch (SecurityException e) {
			throw new RuntimeException("Security exception", e);
		}
		
		// Builds redirect URL
		StringBuilder redirectUrl = new StringBuilder();
		RequestMapping controllerMapping = controllerClass
				.getAnnotation(RequestMapping.class);
		for (String value : controllerMapping.value()) {
			redirectUrl.append(value);
		}
		RequestMapping methodMapping
			= method.getAnnotation(RequestMapping.class);
		for (String value : methodMapping.value()) {
			redirectUrl.append(value);
		}
		int requestParamIndex = 0;
		boolean firstParam = true;
		for (Parameter parameter : method.getParameters()) {
			if (parameter.isAnnotationPresent(RequestParam.class)) {
				if (firstParam) {
					redirectUrl.append("?");
					firstParam = false;
				} else {
					redirectUrl.append("&");
				}
				RequestParam requestParam
					= parameter.getAnnotation(RequestParam.class);
				TaskParameterValue paramValue = this.taskInvocationService
						.findParameterValueByOrder(task,
								(short) requestParamIndex);
				redirectUrl.append(requestParam.value());
				if (paramValue.getInstanceValue() == null) {
					redirectUrl.append("=");
				} else {
					redirectUrl.append("=" + paramValue.getInstanceValue());
				}
				requestParamIndex++;
			}
		}
		return new ModelAndView("redirect:" + redirectUrl.toString());
	}
	
	/**
	 * Registers property editors.
	 * 
	 * @param binder binder
	 */
	@InitBinder
	protected void registerPropertyEditors(final WebDataBinder binder) {
		binder.registerCustomEditor(Task.class,
				this.taskPropertyEditorFactory.createPropertyEditor());
	}
	
	// Converts string to a date
	private Date convertStringToDate(final String dateText) {
		PropertyEditor editor = this.customDateEditorFactory
				.createCustomDateOnlyEditor(true);
		editor.setAsText(dateText);
		return (Date) editor.getValue();
	}
}