package AllyTest;
import java.net.URL;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.deque.axe.AXE;

import io.github.bonigarcia.wdm.WebDriverManager;

public class AmazonAllyTest {
	WebDriver driver;
	private static final URL ScriptURL=AmazonAllyTest.class.getResource("/axe.min.js");
	
	@Before
	public void setup() {
		WebDriverManager.chromedriver().setup();
		driver=new ChromeDriver();
		driver.get("https://www.airbnb.co.in/");
	}
	
	@Test
	public void amazonAllytest() {
		JSONObject responceJS=new AXE.Builder(driver, ScriptURL).analyze();
		JSONArray voilations=responceJS.getJSONArray("violations");
		if(voilations.length()==0) {
			System.out.println("no violation");
		}
		else {
			AXE.writeResults("amazonAllytest", responceJS);
			Assert.assertEquals(false,AXE.report(voilations));
		}
	}
	
	@After
	public void teardown() {
		driver.quit();
	}
}
