package test;

import java.io.File;
import java.util.Set;

import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

public class Browser_Settings {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setBrowserName("chrome");
		
		ChromeOptions options = new ChromeOptions();
		//Don't use a proxy server, always make direct connections. Overrides any other proxy server flags that are passed.
		options.addArguments("no-proxy-server");
		//Does not automatically open a browser window on startup 
		options.addArguments("no-startup-window");
		//Starts the browser maximized, regardless of any previous settings.
		options.addArguments("start-maximized");
		//Launches URL in new browser window
		options.addArguments("new-window");
		//Ignores certificate-related errors.
		options.addArguments("ignore-certificate-errors");
		//WebDriver driver = new ChromeDriver();
		options.addArguments("window-size=800,480");
		
		
		// Add the WebDriver proxy capability.
		Proxy proxy = new Proxy();
		proxy.setHttpProxy("myhttpproxy:3337");
		capabilities.setCapability("proxy", proxy);
		//capabilities.setCapability(ChromeOptions.CAPABILITY, options);

		// Add ChromeDriver-specific capabilities through ChromeOptions.
		
		options.addExtensions(new File("/path/to/extension.crx"));
		capabilities.setCapability(ChromeOptions.CAPABILITY, options);
		
		ChromeDriver driver = new ChromeDriver(capabilities);
		
		// For IE
		
		//Allows the user to specify the initial URL loaded when IE starts. Intended to be used with ignoreProtectedModeSettings to allow the user to initialize IE in the proper Protected Mode zone. Using this capability may cause browser instability or flaky and unresponsive code. Only "best effort" support is provided when using this capability.
		capabilities.setCapability(InternetExplorerDriver.INITIAL_BROWSER_URL, true);
		//Indicates whether to skip the check that the browser's zoom level is set to 100%. Value is set to false by default.
		capabilities.setCapability(InternetExplorerDriver.IGNORE_ZOOM_SETTING, true);
		//Determines whether to require that the IE window have focus before performing any user interaction operations (mouse or keyboard events). This capability is false by default, but delivers much more accurate native events interactions.
		capabilities.setCapability(InternetExplorerDriver.REQUIRE_WINDOW_FOCUS, true);
		capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,true);
		
		
		
		

	}

}

