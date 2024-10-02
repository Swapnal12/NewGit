package Swapnal.testComponents;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import Swapnal.pageObjects.landingPage;

public class BaseTest {

	public WebDriver driver;

	public WebDriver initializeDriver() throws IOException {

		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(
				System.getProperty("user.dir") + "/src/main/java/Swapnal/resources/GlobalData.properties");
		prop.load(fis);
		String browserName=System.getProperty("browser")!=null ? System.getProperty("browser"):prop.getProperty("browser");
		prop.getProperty("browser");

		if (browserName.equalsIgnoreCase("Edge")) {
			driver = new EdgeDriver();

		}

		else if (browserName.equalsIgnoreCase("Firefox")) {
			//
		} else if (browserName.equalsIgnoreCase("Chrome")) {
			//
		}

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		return driver;

	}
	
	@BeforeMethod

	public landingPage launchApplication() throws IOException {
		driver = initializeDriver();
		landingPage landingpage = new landingPage(driver);
		landingpage.goTo();
		return landingpage;
	}
	
	public List<HashMap<String, String>> getJsonDataToMap(String filePath) throws IOException
	{
		
		String jsonContent=FileUtils.readFileToString(new File(filePath),StandardCharsets.UTF_8);
		//String to HashMap JacksonDatabind
		
		ObjectMapper mapper = new ObjectMapper();
		  List<HashMap<String, String>> data = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String, String>>>() {
	      });
		  return data;
	}
	
	public String getScreenshot(String testCaseName, WebDriver driver) throws IOException
	{
		TakesScreenshot ts=(TakesScreenshot)driver;
		File source=ts.getScreenshotAs(OutputType.FILE);
		File file=new File(System.getProperty("user.dir")+"//reports//"+testCaseName+".png");
		FileUtils.copyFile(source,file);
		return System.getProperty("user.dir")+"//reports//"+testCaseName+".png";
	}

}
