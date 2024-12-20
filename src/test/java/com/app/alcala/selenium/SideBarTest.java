package com.app.alcala.selenium;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.app.alcala.AlcalaApplication;
import com.app.alcala.entities.Employee;
import com.app.alcala.entities.Team;
import com.app.alcala.entities.User;
import com.app.alcala.repositories.UserRepository;
import com.app.alcala.service.EmployeeService;
import com.app.alcala.service.TeamService;

@SpringBootTest(classes = AlcalaApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SideBarTest {

	@LocalServerPort
	int port;

	private WebDriver driver;
	private WebDriverWait wait;

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private EmployeeService employeeService;
	@Autowired
	private TeamService teamService;
	
	
	@BeforeEach
	public void setUpTest() {
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--headless");
		options.addArguments("--allow-insecure-localhost");
		options.addArguments("--disable-gpu");
		options.addArguments("--window-size=1920,1080"); 

		driver = new ChromeDriver(options);
		wait = new WebDriverWait(driver, Duration.ofSeconds(100));

	}

	@AfterEach
	public void tearDown() {
		if (driver != null) {
			driver.quit();
		}
	}

	@Test
	public void setUp() {

 
		userRepository.save(new User("testSideBar", passwordEncoder.encode("test"), "ADMIN"));
		Employee employeeTest = new Employee();
		employeeTest.setUserEmployee("testSideBar");
		employeeService.save(employeeTest);
		Team teamTest = new Team();
		teamTest.setNameTeam("teamTestSideBar");
		Map<Long, Employee> employeeMap = new HashMap<Long, Employee>();
		teamTest.setEmployeeMap(employeeMap);
		teamService.save(teamTest);
		teamTest.getEmployeeMap().put(employeeTest.getEmployeeId(), employeeTest);
		employeeTest.setNameTeam("teamTestSideBar");
		employeeTest.setTeam(teamTest);
		employeeService.save(employeeTest);
		teamService.save(teamTest);
		
		driver.get("https://localhost:" + this.port + "/login");
		WebElement usernameField = driver.findElement(By.id("username"));
		WebElement passwordField = driver.findElement(By.id("password"));
		WebElement loginButton = driver.findElement(By.id("loginButton"));

		usernameField.sendKeys("testSideBar");
		passwordField.sendKeys("test");
		loginButton.click();

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("dailywork")));

		assertTrue(driver.getCurrentUrl().endsWith("/dailywork"));
		
		navigateAndVerify("dailywork-link", "/dailywork");
		navigateAndVerify("mywork-link", "/mywork");
		navigateAndVerify("releases-link", "/releases");
		navigateAndVerify("projects-link", "/projects");
		navigateAndVerify("tickets-link", "/tickets");
		
		Optional<User> user = userRepository.findByName("testSideBar");
		if (user != null) {
			Employee employeeDelete = employeeService.findByUserEmployee(user.get().getName());
			if (employeeDelete != null) {
				Team teamDelete = teamService.findByNameTeam(employeeDelete.getNameTeam());

				if (teamDelete != null) {
					teamDelete.getEmployeeMap().remove(employeeDelete.getEmployeeId());
					teamService.save(teamDelete);
				}
				employeeDelete.setTeam(null);
				employeeService.save(employeeDelete);
				
				if (teamTest != null) {
					teamService.delete(teamDelete);
				}
				employeeService.delete(employeeDelete);
			}
			userRepository.delete(user.get());
		}

	}

    private void navigateAndVerify(String linkId, String expectedUrlSuffix) {
        try {
            WebElement link = driver.findElement(By.id(linkId));
            
            // Asegurar que el elemento esté visible antes de hacer clic
            wait.until(ExpectedConditions.visibilityOf(link));
            
            // Desplazar el elemento a la vista antes de hacer clic
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", link);
            
            // Hacer clic con JavaScript si es necesario
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", link);
            
            wait.until(ExpectedConditions.urlContains(expectedUrlSuffix));
            assertTrue(driver.getCurrentUrl().endsWith(expectedUrlSuffix));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
