import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.junit.jupiter.api.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class) // Ejecutar los tests en orden
public class PetBoutiqueTest {

    static AppiumDriver driver; // Driver declarado aquí

    @BeforeAll // Configuración inicial antes de todos los tests
    public static void setUp() throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("deviceName", "A14 de Esparto"); // Nombre de celu
        capabilities.setCapability("udid", "R58W41VKW9D"); // Número de serie
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("platformVersion", "14"); // Versión del Android
        capabilities.setCapability("automationName", "uiAutomator2");
        capabilities.setCapability("appPackage", "com.example.proy_mobile2024");
        capabilities.setCapability("appActivity", "com.example.proy_mobile2024.LandingActivity");

        URL url = new URL("http://127.0.0.1:4723/"); // El servidor de Appium
        driver = new AppiumDriver(url, capabilities); // Inicializar driver

        System.out.println("Application Started successfully");
    }

    @Test
    @Order(1) // Primera prueba: Verificar apertura de la app
    public void testOpenApp() {
        Assertions.assertNotNull(driver, "El driver no se inicializó correctamente.");
        System.out.println("Aplicación abierta correctamente.");
    }


    @Test
    @Order(2)
    public void testStartButton() {
        WebElement startButton = driver.findElement(By.id("com.example.proy_mobile2024:id/button_start"));
        startButton.click();
        System.out.println("Clicked on Start button");

        // Espera explícita para asegurar que el siguiente elemento esté disponible
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement siguientePantalla = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.id("android:id/content"))
        );

        Assertions.assertNotNull(siguientePantalla, "No se encontró el siguiente elemento.");
        System.out.println("Navegación correcta tras hacer click en el botón.");
    }




    @AfterAll // Cerrar el driver después de todas las pruebas
    public static void tearDown() {
        if (driver != null) {
            driver.quit();
            System.out.println("Driver cerrado correctamente.");
        }
    }
}
