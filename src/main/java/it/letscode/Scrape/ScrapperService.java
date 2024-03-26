package it.letscode.Scrape;

import io.github.sudharsan_selvaraj.wowxhr.WowXHR;
import io.github.sudharsan_selvaraj.wowxhr.exceptions.DriverNotSupportedException;
import io.github.sudharsan_selvaraj.wowxhr.log.XHRLog;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.springframework.stereotype.Component;

import org.openqa.selenium.devtools.v122.network.Network;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Component
public class ScrapperService {

    public void run() throws DriverNotSupportedException, InterruptedException, MalformedURLException {

//        WowXHR wowXhr = new WowXHR(new ChromeDriver()); //any selenium webdriver or RemoteWebDriver
//        WebDriver driver = wowXhr.getMockDriver();


        ChromeOptions options = new ChromeOptions();

        // Tworzenie instancji RemoteWebDriver, która wskazuje na Selenium Hub
        WebDriver driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), options);

        // Tworzenie instancji WowXHR z RemoteWebDriver
        WowXHR wowXhr = new WowXHR(driver);
        WebDriver mockDriver = wowXhr.getMockDriver();

        // Przechodzenie do strony internetowej
        mockDriver.get("https://l.messenger.com/l.php?u=https%3A%2F%2Fwww.laczynaspilka.pl%2Frozgrywki%3Fseason%3D2023%252F2024%26leagueGroup%3D63e0b91e-f2cc-4149-813b-ea9a77919385%26leagueId%3D20505afb-3cb6-4e59-9bb1-ed56e8201bb8%26subLeague%3D733f5b9c-9ade-4011-84c4-b08d35d170b3%26enumType%3DZpnAndLeagueAndPlay%26group%3Dda03855e-6763-4671-b8ed-9b4aa7b10f0f%26voivodeship%3Dcd81a30b-c8a3-44e0-abd6-8b5772d3137c%26isAdvanceMode%3Dfalse%26genderType%3DMale&h=AT0H8lNGVuT-hELSQw_Oo4aAyoY8hIJagBJaywox6XK8u6iXSHDcxdAK23JbPXn2a0s9mXRGES-HdNrGciRKN1kDqzfoCzHDHokIvJYjAcONYqnHA5zgTiR4kfqrRbeAMXpMig");

        Thread.sleep(10000);

        List<XHRLog> logs = wowXhr.log().getXHRLogs();
        logs.forEach(log -> {
            Date initiatedTime =  log.getInitiatedTime();
            Date completedTime =  log.getCompletedTime();

//            String method = log.getRequest().getMethod().name(); // GET or POST or PUT etc
            String url = log.getRequest().getUrl();
            Integer status = log.getResponse().getStatus();
            String requestBody = (String) log.getRequest().getBody();
            String responseBody = (String) log.getResponse().getBody();
            Map<String, String> requestHeaders = log.getRequest().getHeaders();
            Map<String, String> responseHeaders = log.getResponse().getHeaders();

            System.out.println("URL ----------------------------------------------> " + url);

            if(url != null && url.endsWith("tables")) {
                System.out.println(responseBody);
            }
        });

        System.out.println("Tytuł strony: " + mockDriver.getTitle());
    }
}
