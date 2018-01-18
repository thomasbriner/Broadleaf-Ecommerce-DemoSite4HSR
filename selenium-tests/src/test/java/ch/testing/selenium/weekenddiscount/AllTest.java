package ch.testing.selenium.weekenddiscount;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import ch.testing.selenium.weekenddiscount.tests.WeekendDiscountAcceptanceTests;
import ch.testing.selenium.weekenddiscount.tests.WeekendDiscountSmokeTests;

@RunWith(Suite.class)
@Suite.SuiteClasses({WeekendDiscountSmokeTests.class, WeekendDiscountAcceptanceTests.class})

public class AllTest {

	public AllTest(){
		System.out.println("Alle Tests ausfuehren");
	}
}
