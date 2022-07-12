package org.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Set;
import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {
	static WebDriver driver;

	public void browserlaunch(String Url) {
		WebDriverManager.chromedriver().setup();
		driver.manage().window().maximize();
		driver = new ChromeDriver();
		driver.get(Url);
	}

	public static WebElement Locators(String locatorsType, String Value) {
		if (locatorsType.equals("id")) {
			WebElement findElement = driver.findElement(By.id(Value));
			return findElement;
		} else if (locatorsType.equals("name")) {
			WebElement findElement = driver.findElement(By.name(Value));
			return findElement;
		} else {
			WebElement findElement = driver.findElement(By.xpath(Value));
			return findElement;
		}
	}

	public static String getTile() {
		String title = driver.getTitle();
		return title;

	}

	public static String getUrl() {
		String url = driver.getCurrentUrl();
		return url;
	}

	public void enterText(WebElement element, String Text) {
		element.sendKeys(Text);
	}

	public static void clear() {
		driver.close();
	}

	public static void quit() {
		driver.quit();
	}

	public static void clear(WebElement element) {
		element.clear();
	}

	public void SimpleAlert() {
		Alert a = driver.switchTo().alert();
		a.accept();
	}

	public void ConfirmAlert() {
		Alert a = driver.switchTo().alert();
		a.dismiss();
	}

	public void Actions(WebElement element) {
		org.openqa.selenium.interactions.Actions act = new org.openqa.selenium.interactions.Actions(driver);
		act.moveToElement(element).perform();

	}

	public void ContextAction(WebElement element) {
		org.openqa.selenium.interactions.Actions act = new org.openqa.selenium.interactions.Actions(driver);
		act.contextClick(element).perform();
	}

	public void doubleclick(WebElement element) {
		org.openqa.selenium.interactions.Actions act = new org.openqa.selenium.interactions.Actions(driver);
		act.doubleClick(element).perform();
	}

	public void Select(WebElement element, String Value) {
		org.openqa.selenium.support.ui.Select s = new org.openqa.selenium.support.ui.Select(element);
		s.selectByValue(Value);

	}

	public void selectbyindex(WebElement element, String Text) {
		org.openqa.selenium.support.ui.Select s = new org.openqa.selenium.support.ui.Select(element);
		s.selectByVisibleText(Text);
	}

	public void selectbyindex(WebElement element, int index) {
		org.openqa.selenium.support.ui.Select s = new org.openqa.selenium.support.ui.Select(element);
		s.selectByIndex(index);
	}

	public static void TakeScreentShot(String Path) throws IOException {
		TakesScreenshot tk = (TakesScreenshot) driver;
		File src = tk.getScreenshotAs(OutputType.FILE);
		File des = new File(Path);
		FileUtils.copyDirectory(src, des);
	}

	public void javascript(WebElement element, String Text, String Value) {
		JavascriptExecutor jk = (JavascriptExecutor) driver;
		jk.executeScript("arguments[0].setAttribute('element','Value')", Text);

	}

	public void javascript(WebElement element, String Text) {
		JavascriptExecutor jk = (JavascriptExecutor) driver;
		jk.executeScript("arguments[0].scrollIntoView(element)", Text);

	}

public String excelRead(String path, String SheetName ,  int Rowindex, int cellindex ) throws IOException {
		
		File f = new File(path);
		
		FileInputStream s = new FileInputStream(f);
		
		Workbook w = new XSSFWorkbook(s);
		
		Sheet sheet = w.getSheet(SheetName);
		Row row = sheet.getRow(Rowindex);
		Cell cell = row.getCell(cellindex);
		
		int cellType = cell.getCellType();
		if (cellType == 1) {
			String stringcellvalve = cell.getStringCellValue();
			return stringcellvalve;
		}
		else if(DateUtil.isCellDateFormatted(cell)) {
			Date datecellvalue = cell.getDateCellValue();
			SimpleDateFormat s1 = new SimpleDateFormat("dd/mm/yyyy");
			
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
	}
