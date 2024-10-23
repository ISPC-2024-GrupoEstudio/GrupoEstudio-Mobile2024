import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.junit.jupiter.api.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class) // Para ejecutar los tests en orden
public class ContactoTest {

    static AndroidDriver driver;

    @BeforeAll
    public static void setUp() throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("deviceName", "A14 de Esparto");
        capabilities.setCapability("udid", "R58W41VKW9D");
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("platformVersion", "14");
        capabilities.setCapability("automationName", "UiAutomator2");
        capabilities.setCapability("appPackage", "com.example.proy_mobile2024");
        capabilities.setCapability("appActivity", "com.example.proy_mobile2024.MainActivity");

        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/"), capabilities);
        System.out.println("App iniciada correctamente.");
    }

    // Método para ocultar el teclado
    public void ocultarTeclado() {
        try {
            driver.hideKeyboard();
            System.out.println("Teclado oculto correctamente.");
        } catch (Exception e) {
            System.out.println("El teclado ya estaba oculto o no se pudo ocultar.");
        }
    }

    @Test
    @Order(1)
    public void navegarAContactanos() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement menuButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.id("com.example.proy_mobile2024:id/buttonDrawerToggle")));
        menuButton.click();
        System.out.println("Menú de navegación abierto.");

        WebElement contactanosOption = wait.until(ExpectedConditions.elementToBeClickable(
                By.id("com.example.proy_mobile2024:id/nav_contactus")));
        contactanosOption.click();
        System.out.println("Pantalla de contacto abierta.");
    }

    @Test
    @Order(2)
    public void completarFormularioContacto() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.id("com.example.proy_mobile2024:id/et_name"))).sendKeys("Juan Pérez");
        ocultarTeclado();

        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.id("com.example.proy_mobile2024:id/et_tel"))).sendKeys("3518542789");
        ocultarTeclado();

        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.id("com.example.proy_mobile2024:id/et_email"))).sendKeys("juan.perez@example.com");
        ocultarTeclado();

        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.id("com.example.proy_mobile2024:id/et_msj"))).sendKeys("Estoy interesado en sus productos.");
        ocultarTeclado();

        WebElement btnEnviar = wait.until(ExpectedConditions.elementToBeClickable(
                By.id("com.example.proy_mobile2024:id/btn_enviar")));
        btnEnviar.click();
        System.out.println("Formulario de contacto enviado.");

        // Esperar 3 segundos después de enviar el formulario
        Thread.sleep(3000);
    }

    @AfterAll
    public static void tearDown() {
        if (driver != null) {
            driver.quit();
            System.out.println("Driver cerrado correctamente.");
        }
    }
}
