package pl.polsl.bd2.helpers;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/*
 * http://stackoverflow.com/questions/5355082/spring-beanfactory-as-a-singleton-in-a-swing-application
 * 
 */

public final class SpringUtil {

	private static ApplicationContext context = null;
	private static Set<String> alreadyLoaded = new HashSet<String>();

	/**
	 * Sets the spring context based off multiple wiring files. The files must
	 * exist on the classpath to be found. Consider using
	 * "import resource="wiring.xml" in a single wiring file to reference other
	 * wiring files instead. Note that this will destroy all previous existing
	 * wiring contexts.
	 * 
	 * @param wiringFiles
	 *            an array of spring wiring files
	 */
	public static void setContext(String... wiringFiles) {
		alreadyLoaded.clear();
		alreadyLoaded.addAll(Arrays.asList(wiringFiles));
		context = new ClassPathXmlApplicationContext(wiringFiles);
		System.out.println(context.getBeanDefinitionCount());
		for (String s: context.getBeanDefinitionNames())
			System.out.println(s);
	}

	/**
	 * Adds more beans to the spring context givin an array of wiring files. The
	 * files must exist on the classpath to be found.
	 * 
	 * @param addFiles
	 *            an array of spring wiring files
	 */
	public static void addContext(String... addFiles) {
		if (context == null) {
			setContext(addFiles);
			return;
		}

		Set<String> notAlreadyLoaded = new HashSet<String>();
		for (String target : addFiles) {
			if (!alreadyLoaded.contains(target)) {
				notAlreadyLoaded.add(target);
			}
		}

		if (notAlreadyLoaded.size() > 0) {
			alreadyLoaded.addAll(notAlreadyLoaded);
			context = new ClassPathXmlApplicationContext(
					notAlreadyLoaded.toArray(new String[] {}), context);
		}
	}

	/**
	 * Gets the current spring context for direct access.
	 * 
	 * @return the current spring context
	 */
	public static ApplicationContext getContext() {
		return context;
	}

	/**
	 * Gets a bean from the current spring context.
	 * 
	 * @param beanName
	 *            the name of the bean to be returned
	 * @return the bean, or throws a RuntimeException if not found.
	 */
	public static Object getBean(final String beanName) {
		if (context == null) {
			throw new RuntimeException("Context has not been loaded.");
		}
		return getContext().getBean(beanName);
	}

	/**
	 * Sets this singleton back to an uninitialized state, meaning it does not
	 * have any spring context and {@link #getContext()} will return null. Note:
	 * this is for unit testing only and may be removed at any time.
	 */
	public static void reset() {
		alreadyLoaded.clear();
		context = null;
	}
}
