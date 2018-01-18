/*
 * HSR Hochschule fuer Technik Rapperswil
 * Master of Advanced Studies in Software Engineering
 * Module Software Testing
 * 
 * Thomas Briner, thomas.briner@gmail.com
 */
package ch.testing.selenium.weekenddiscount.executionrule;

import org.junit.rules.MethodRule;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.Statement;

/**
 * The Class ScreenshotOnFailureRule. Used for adding steps at execution time of
 * the junit framework in order to write log output and create snapshot of html
 * and a screenshot in case of failure.
 */
public class ScreenshotOnFailureRule implements MethodRule {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.junit.rules.MethodRule#apply(org.junit.runners.model.Statement,
	 * org.junit.runners.model.FrameworkMethod, java.lang.Object)
	 */
	public Statement apply(Statement base, FrameworkMethod method, Object target) {
		String className = method.getMethod().getDeclaringClass()
				.getSimpleName();
		String methodName = method.getName();
		ScreenshotOnFailureStatement statement = new ScreenshotOnFailureStatement(
				base, className, methodName);
		return statement;
	}
}