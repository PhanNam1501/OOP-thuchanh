package Selenium;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.io.FileWriter;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws InterruptedException, IOException {
        // Khởi tạo WebDriver
        WebDriver driver = new ChromeDriver();

        // Mở trang web
        driver.get("https://cryptonews.com/?s=blockchain");

        try {
            // Tìm phần tử nút "Read More"
        	JavascriptExecutor js = (JavascriptExecutor) driver;
        	for (int i = 0 ; i < 20 ;i++) {
	            js.executeScript("document.querySelector('.button-more').click();");
	            Thread.sleep(5000);
        	}

            // Chờ cho đến khi một phần tử mới xuất hiện trên trang web (ví dụ: phần tử có class là "new-element")
            // Code chờ ở đây

            // Lấy HTML của trang web sau khi đã bấm nút "Read More" và trang web đã được cập nhật
            String html = driver.getPageSource();

            // Ghi HTML vào tệp tin văn bản (txt)
            FileWriter writer = new FileWriter("output.html");
            writer.write(html);
            System.out.println("Done");
            

        } finally {
            driver.quit();
        }
    }
}
