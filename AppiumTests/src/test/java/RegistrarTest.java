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
public class RegistrarTest {

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
    public void navegarARegistro() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement menuButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.id("com.example.proy_mobile2024:id/buttonDrawerToggle")));
        menuButton.click();
        System.out.println("Menú de navegación abierto.");

        WebElement registrarOption = wait.until(ExpectedConditions.elementToBeClickable(
                By.id("com.example.proy_mobile2024:id/nav_registro")));
        registrarOption.click();
        System.out.println("Pantalla de registro abierta.");
    }

    @Test
    @Order(2)
    public void completarFormularioRegistro() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.id("com.example.proy_mobile2024:id/et_name"))).sendKeys("Juan");
        ocultarTeclado();

        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.id("com.example.proy_mobile2024:id/et_lastname"))).sendKeys("Pérez");
        ocultarTeclado();

        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.id("com.example.proy_mobile2024:id/et_email"))).sendKeys("juan.perez@example.com");
        ocultarTeclado();

        WebElement spinnerDni = wait.until(ExpectedConditions.elementToBeClickable(
                By.id("com.example.proy_mobile2024:id/spinner_tipo_DNI")));
        spinnerDni.click();
        System.out.println("Spinner de tipo DNI abierto.");

        WebElement opcionDNI = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//android.widget.CheckedTextView[@text='DNI']")));
        opcionDNI.click();
        System.out.println("Opción DNI seleccionada.");

        WebElement campoDni = wait.until(ExpectedConditions.elementToBeClickable(
                By.id("com.example.proy_mobile2024:id/dni")));
        campoDni.click();
        campoDni.sendKeys("12345678");
        ocultarTeclado();
        System.out.println("Número de DNI ingresado.");

        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.id("com.example.proy_mobile2024:id/et_username"))).sendKeys("juanperez");
        ocultarTeclado();

        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.id("com.example.proy_mobile2024:id/et_password"))).sendKeys("password123");
        ocultarTeclado();

        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.id("com.example.proy_mobile2024:id/et_confirm_password"))).sendKeys("password123");
        ocultarTeclado();

        WebElement btnRegistro = wait.until(ExpectedConditions.elementToBeClickable(
                By.id("com.example.proy_mobile2024:id/btn_Registro")));
        btnRegistro.click();
        System.out.println("Formulario de registro completado.");

        // Espera de 3 segundos antes de continuar
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

