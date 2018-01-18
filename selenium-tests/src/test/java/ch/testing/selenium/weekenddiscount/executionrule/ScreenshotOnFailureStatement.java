/*
 * HSR Hochschule fuer Technik Rapperswil
 * Master of Advanced Studies in Software Engineering
 * Module Software Testing
 * 
 * Thomas Briner, thomas.briner@gmail.com
 */
package ch.testing.selenium.weekenddiscount.executionrule;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.runners.model.Statement;
import org.openqa.selenium.WebDriver;

import ch.testing.selenium.weekenddiscount.util.FileUtil;

/**
 * The Class ScreenshotOnFailureStatement. The statement belonging to the rule
 * for the junit execution. The statement adds log output and takes screenshot
 * and stores the html in case of failure.
 */
public class ScreenshotOnFailureStatement extends Statement {

	/** The Constant LOG. */
	private static final Log LOG = LogFactory
			.getLog(ScreenshotOnFailureStatement.class);

	private final Statement base;

	private final String className;

	private final String methodName;

	public ScreenshotOnFailureStatement(Statement base, String className,
			String methodName) {
		this.base = base;
		this.className = className;
		this.methodName = methodName;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.junit.runners.model.Statement#evaluate()
	 */
	@Override
	public void evaluate() throws Throwable {
		try {
			LOG.info("Starting Test " + className + "." + methodName);
			base.evaluate();
			LOG.info("Test was successful: " + className + "." + methodName);
			WebDriver driver = WebDriverKeeper.getInstance().getDriver();
			driver.close();

		} catch (Throwable e) {
			LOG.info("Test failed: " + className + "." + methodName);
			WebDriver driver = WebDriverKeeper.getInstance().getDriver();

			FileUtil.saveScreenshotToFile(className + "." + methodName, driver);
			FileUtil.writePageSourceToFile(className + "." + methodName, driver);

			throw e;
		}
	}
}