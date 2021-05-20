/**
 * 
 */
package hrcassignment3;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.imageio.ImageIO;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

public class FlipkartAutomation {

		private static WebDriver firefoxDriver;
		private static DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");

		/* 1. Navigate to https://www.flipkart.com/ */
		public static void launchWebsite() throws InterruptedException {
				WebDriverManager.firefoxdriver().setup();
				firefoxDriver = new FirefoxDriver();
				firefoxDriver.manage().window().maximize();
				firefoxDriver.manage().deleteAllCookies();

				firefoxDriver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
				firefoxDriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

				firefoxDriver.navigate().to("https://www.flipkart.com/");
		}

		/* 2. As soon as flipkart is up, a pop up box will appear Asking to login. */
		/* 3. Fill username and password and hit login button */
		/* 4. Wait for your profile name to get populated (i..e.. Success login) ! */
		@SuppressWarnings({ "unused", "deprecation" })
		public static void loginAccount() throws InterruptedException {
				/** email or mobile number field */
				firefoxDriver.findElement(By.xpath("(//input[@type='text'])[2]")).sendKeys("9647100133");
				WebDriverWait wait = new WebDriverWait(firefoxDriver, 20);
				// Thread.sleep(1000);
				/** password field */
				firefoxDriver.findElement(By.xpath("//input[@type='password']")).sendKeys("abcdefgh");
				WebDriverWait wait2 = new WebDriverWait(firefoxDriver, 20);
				// Thread.sleep(1000);
				/** login button */
				firefoxDriver.findElement(By.xpath("(//button[@type='submit'])[2]")).click();
				Thread.sleep(1000);

				/** Account Verification, profileName and "Padmanabha" Matches or not */
				String confirmation = firefoxDriver.findElement(By.xpath("//div[@class='exehdJ']")).getText();
				String name = "Padmanabha";
				System.out.println(confirmation + " - " + dateTimeFormatter.format(LocalDateTime.now()));
				if (confirmation.equals(name))
						System.out.println("Verification Successful");
				else {
						System.out.println("Verification Unsuccessful");
						return;
				}
		}

		/* 5. Search with String “smartphone” from the Search Box */
		public static void searchSmartphone() throws InterruptedException {
				/** Search Bar */
				firefoxDriver.findElement(By.name("q")).sendKeys("smartphone");
				System.out.println("smartphone " + dateTimeFormatter.format(LocalDateTime.now()));
				/** Search icon */
				firefoxDriver.findElement(By.className("L0Z3Pu")).click();
				System.out.println("Search " + dateTimeFormatter.format(LocalDateTime.now()));
		}

		/*
		 * 6. There will be Filters available on the left menu panel. Filter by
		 * selecting the following criteria : a. Min price - 7000 ( select ) b. RAM - 6
		 * GB & Above ( click ) c. BRAND - ASUS - ( type and select ) d. Rating - 3 %
		 * Above (click) 7. Click the “Price Low to High” button visible on the top of
		 * the results
		 */
		@SuppressWarnings("unused")
		public static void filter() throws InterruptedException {
				/** Min Price */
				Select select = new Select(firefoxDriver.findElement(By.className("_2YxCDZ")));
				select.selectByValue("7000");
				System.out.println("Minimum Price selected to 7000/- " + dateTimeFormatter.format(LocalDateTime.now()));
				// WebDriverWait wait4=new WebDriverWait(driver, 40);
				Thread.sleep(1500);
				/** RAM */
				firefoxDriver.findElement(By.xpath("//div[@class='_24_Dny']")).click();
				System.out.println("RAM selected to 6GB & Above " + dateTimeFormatter.format(LocalDateTime.now()));
				Thread.sleep(1500);
				// WebDriverWait wait5=new WebDriverWait(driver, 20);
				/** BRAND */
				firefoxDriver.findElement(By.xpath("//input[@placeholder='Search Brand']")).sendKeys("ASUS");
				System.out.println("Search Brand " + dateTimeFormatter.format(LocalDateTime.now()));
				// WebDriverWait wait6=new WebDriverWait(driver, 60);
				Thread.sleep(1500);
				firefoxDriver.findElement(By.xpath("//div[contains(text(), 'ASUS')]")).click();
				System.out.println("ASUS " + dateTimeFormatter.format(LocalDateTime.now()));
				// WebDriverWait wait7=new WebDriverWait(driver, 20);
				Thread.sleep(1500);
				/** Rating */
				firefoxDriver.findElement(By.xpath("//div[contains(text(), '3★ & above')]")).click();
				Thread.sleep(1500);
				System.out.println("Rating set to be 3★ & above " + dateTimeFormatter.format(LocalDateTime.now()));
				/** Sort by Price Low to High */
				firefoxDriver.findElement(By.xpath("//div[contains(text(), 'Price -- Low to High')]")).click();
				System.out.println("Sorted by Price Low to High " + dateTimeFormatter.format(LocalDateTime.now()));
				Thread.sleep(1500);
		}

		/* 9. Select “Newest First” from the top */
		/* 10.Select “Add to compare” checkbox for the first 3 models */
		public static void sort() throws InterruptedException {
				Thread.sleep(4000);
				/** Sort by Newest */
				firefoxDriver.findElement(By.xpath("//div[contains(text(), 'Newest First')]")).click();
				System.out.println("Sorted by Newest " + dateTimeFormatter.format(LocalDateTime.now()));
				Thread.sleep(2000);
				/** Select the first three mobile to compare */
				firefoxDriver.findElement(By.xpath("(//span[contains(.,'Add to Compare')])[1]")).click();
				Thread.sleep(2000);
				firefoxDriver.findElement(By.xpath("(//span[contains(.,'Add to Compare')])[2]")).click();
				Thread.sleep(2000);
				firefoxDriver.findElement(By.xpath("(//span[contains(.,'Add to Compare')])[3]")).click();
				Thread.sleep(2000);
				System.out.println("3 phones selected to compare " + dateTimeFormatter.format(LocalDateTime.now()));
		}

		/* 11. Click on compare → a comparison page will open ! */
		/* 12.There, along with the 3 model selected, there will be a 4th empty slot */
		/*
		 * 13.Select from the that slot 
		 * a. Choose Brand → “Apple ” (can type and select)
		 * b. Choose a Product → “Apple iPhone XR (White, 128 GB) (Includes EarPods, Power Adapter)” (select from Dropdown)
		 */
		/* 14. take a screenshot */
		public static void comparison() throws InterruptedException, IOException {
				/** Click on Compare */
				firefoxDriver.findElement(By.xpath("(//span[contains(.,'COMPARE')])[2]")).click();
				/** Select APPLE */
				new WebDriverWait(firefoxDriver, 20)
				                .until(ExpectedConditions
				                                .elementToBeClickable(By.xpath("//div[@class='_1EDlbo _17qiPn']")))
				                .click();
				firefoxDriver.findElement(By.xpath("//input[@placeholder='Choose Brand']")).sendKeys("Apple");
				firefoxDriver.findElement(By.xpath("//div[@title='APPLE']")).click();
				new WebDriverWait(firefoxDriver, 20)
				                .until(ExpectedConditions.elementToBeClickable(By.xpath(
				                                "//div[@class='_18OcjB _1cpOwe'][2]//div[@class='_1EDlbo _17qiPn']")))
				                .click();
				/**
				 * Select Apple iPhone XR (White, 128 GB) (Includes EarPods, Power Adapter)']
				 */
				firefoxDriver.findElement(By.xpath(
				                "//div[@title='Apple iPhone XR (White, 128 GB) (Includes EarPods, Power Adapter)']"))
				                .click();
				System.out.println("Apple Selected " + dateTimeFormatter.format(LocalDateTime.now()));
				Thread.sleep(2000);
				/** Take screenshot */
				Screenshot sc = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(1000))
				                .takeScreenshot(firefoxDriver);
				ImageIO.write(sc.getImage(), "png", new File(
				                "D:\\6th Semester\\HRC TECH TRACK INTERNSHIP\\Eclipse\\HRC\\Assignment\\hrcassignment3\\12142_padmanabhadas_flipkartscreenshot.png"));
				System.out.println("Screenshot taken " + dateTimeFormatter.format(LocalDateTime.now()));
		}

		/* 15. Navigate back to the prev Page */
		/* 16. Clear All filters ( by clicking clear all in the Filter panel) */
		/* 17. Select Logout from Profile Dropdown */
		public static void backPage() throws InterruptedException, IOException {
				/** Navigate back */
				firefoxDriver.navigate().back();
				System.out.println("Navgating Back " + dateTimeFormatter.format(LocalDateTime.now()));
				Thread.sleep(2000);
				/** Clear All selected filters */
				firefoxDriver.findElement(By.xpath("(//span[contains(.,'Clear all')])[1]")).click();
				System.out.println("Clear All " + dateTimeFormatter.format(LocalDateTime.now()));
				Thread.sleep(2000);
				/** Logout */
				WebElement webElementDropdown = firefoxDriver.findElement(By.className("exehdJ"));
				String profileName = webElementDropdown.getText();
				Actions action = new Actions(firefoxDriver);
				action.moveToElement(webElementDropdown).perform();
				Thread.sleep(5000);
				firefoxDriver.findElement(By.xpath("//a[@href='#'][contains(.,'Logout')]")).click();
				System.out.println("Logout Successful " + dateTimeFormatter.format(LocalDateTime.now()));
		}

		public static class Capture {
				
				public static boolean availableProduct(WebElement webElement) throws NoSuchElementException {
						try {
								WebElement available = webElement.findElement(By.className("_192laR"));
								return false;
						} catch (Exception exception) {
								return true;
						}
				}

				public static void insertIntoDB(WebDriver firefoxDriver) {
						Connection connection = null;
						PreparedStatement preparedStatement = null;
						String url = "jdbc:mysql://localhost:3306/";
						String schema = "flipkart_selenium";
						String user = "root";
						String pass = "root";
						// query
						String query = "INSERT INTO Smartphone (model_name,rating,rating_count, price,ram, front_camera_mp, rear_camera_mp,capacity,available,paths)"
						                + " VALUES (?,?,?,?,?,?,?,?,?,?)";
						try {
								connection = DriverManager.getConnection(url + schema, user, pass);
								preparedStatement = connection.prepareStatement(query);
								/** Rating Count */
								Pattern p2 = Pattern.compile("([\\d]*)\\,?([\\d]+)\\,?([\\d]+)");
								/** RAM */
								Pattern p3 = Pattern.compile("[\\d]*.\\w*");
								/** Camera */
								Pattern p4 = Pattern.compile("([\\dMP+\\s]+)[\\s\\w]*\\|\\s([\\dMP]+)[\\s\\w]*");
								/** Battery Capacity */
								Pattern p5 = Pattern.compile("(\\d+\\smAh)\\s[\\s\\w]*");
								/** Price */
								Pattern p6 = Pattern.compile("₹([\\d]*),([\\d]*)");

								// creating list of FlipkartPojo
								ArrayList<FlipkartPojo> FlipkartPojoList = new ArrayList<>();
								// creating list of web elements in the page
								ArrayList<WebElement> liElements = (ArrayList<WebElement>) firefoxDriver.findElements(By.xpath("//div[@class='_13oc-S']"));

								// iterating through the list of web elements
								for (WebElement webElement : liElements) {
										FlipkartPojo flipkartPojo = new FlipkartPojo();
										/** Model Name */
										WebElement name = webElement.findElement(By.className("_4rR01T"));
										flipkartPojo.setModelName(name.getText());
										/** Rating */
										WebElement rating = webElement.findElement(By.className("_3LWZlK"));
										flipkartPojo.setRating(Float.parseFloat(rating.getText()));
										/** Rating Count */
										WebElement rating_count = webElement.findElement(By.className("_2_R_DZ"));
										Matcher m2 = p2.matcher(rating_count.getText());
										if (m2.find()) {
												// System.out.println(Integer.parseInt(m2.group(1)+m2.group(2)));
												// obj.setRatingCount(Integer.parseInt(m2.group(1)+m2.group(2)+m2.group(3)));
												flipkartPojo.setRatingCount(Integer
												                .parseInt(m2.group(1) + m2.group(2) + m2.group(3)));
										}
										/** Price */
										WebElement price = webElement.findElement(By.cssSelector("div[class='_30jeq3 _1_WHN1']"));
										Matcher m6 = p6.matcher(price.getText());
										if (m6.find()) {
												// System.out.println(Float.parseFloat(m6.group(1)+m6.group(2)));

												flipkartPojo.setPrice(Float.parseFloat(m6.group(1) + m6.group(2)));
										}
										/** Path */
										WebElement path = webElement.findElement(By.className("_1fQZEK"));
										flipkartPojo.setPathOfModel((path.getAttribute("href")).toString());
										/** Battery Capacity */
										WebElement bat = webElement.findElement(By.cssSelector("ul > li:nth-child(4)"));
										Matcher m5 = p5.matcher(bat.getText());
										if (m5.find()) {
												// System.out.println(m5.group(0));
												flipkartPojo.setBatteryCapacity(m5.group(1));
										}
										/** RAM */
										WebElement ram = webElement.findElement(By.cssSelector("ul > li:nth-child(1)"));
										Matcher m3 = p3.matcher(ram.getText());
										if (m3.find())
												flipkartPojo.setRam(m3.group(0));
										/** Camera */
										WebElement camera = webElement.findElement(By.cssSelector("ul > li:nth-child(3)"));
										Matcher m4 = p4.matcher(camera.getText());
										if (m4.find()) {
												flipkartPojo.setMp_ofRearCamera(m4.group(1));
												flipkartPojo.setMp_ofFrontCamera(m4.group(2));
										}
										/** Availability */
										flipkartPojo.setAvailability(availableProduct(webElement));
										
										FlipkartPojoList.add(flipkartPojo);
								}

								// iterating through the FlipkartPojolist
								for (FlipkartPojo obj1 : FlipkartPojoList) {
										preparedStatement.setString(1, obj1.getModelName());
										preparedStatement.setFloat(2, obj1.getRating());
										preparedStatement.setInt(3, obj1.getRatingCount());
										preparedStatement.setFloat(4, obj1.getPrice());
										preparedStatement.setString(5, obj1.getRam());
										preparedStatement.setString(6, obj1.getMp_ofFrontCamera());
										preparedStatement.setString(7, obj1.getMp_ofRearCamera());
										preparedStatement.setString(8, obj1.getBatteryCapacity());
										preparedStatement.setBoolean(9, obj1.isAvailability());
										preparedStatement.setString(10, obj1.getPathOfModel());
										preparedStatement.execute();
								}
						}
						catch (SQLException e) {
								e.printStackTrace();
						} finally {
								try {
										preparedStatement.close();
										connection.close();
								} catch (SQLException e) {
										e.printStackTrace();
								}
						}
				}
		}

		public static void pagination(WebDriver firefoxDriver) throws InterruptedException {
				Thread.sleep(4000);
				JavascriptExecutor js = (JavascriptExecutor) firefoxDriver;
				try {
						do {
								Capture.insertIntoDB(firefoxDriver);
								firefoxDriver.findElement(By.xpath("//a[@class='_1LKTO3']")).click();
								js.executeScript("window.scrollTo(0,document.body.scrollHeight)");
								System.out.println("do");
						} while (firefoxDriver.findElement(By.xpath("//span[contains(.,'Next')]")) != null);
				} catch (StaleElementReferenceException ex) {
						System.out.println("exception");
						js.executeScript("window.scrollTo(0,document.body.scrollHeight)");
						Capture.insertIntoDB(firefoxDriver);
				}
				Thread.sleep(3000);
		}

		public static void main(String[] args) throws InterruptedException, SQLException, IOException {

				launchWebsite();
				loginAccount();
				searchSmartphone();
				filter();
				pagination(firefoxDriver);
				firefoxDriver.navigate().back();
				sort();
				comparison();
				backPage();
				/* 18. Close Browser */
				firefoxDriver.close();
		}
}
