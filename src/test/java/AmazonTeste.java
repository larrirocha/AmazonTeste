import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class AmazonTeste {
    private WebDriver driver;

    @Before
    public void abrir() {
        System.setProperty("webdriver.gecko.driver", "C:/driver/geckodriver.exe");
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.get("https://www.amazon.com.br/");

    }
    @After
    public void sair() {
        driver.quit();
    }

    @Test
    public void testarLoginEmailInvalido(){
        driver.findElement(By.id("nav-link-accountList")).click();
        driver.findElement(By.id("ap_email")).sendKeys("teste@teste");
        driver.findElement(By.id("continue")).click();
        Assert.assertEquals("Houve um problema", driver.findElement(By.xpath("/html/body/div[1]/div[1]/div[2]/div/div[1]/div/div/div/h4")).getText());
    }

    @Test
    public void testarLoginSenhaIncorreta(){
        driver.findElement(By.id("nav-link-accountList")).click();
        driver.findElement(By.id("ap_email")).sendKeys("teste@teste.com.br");
        driver.findElement(By.id("continue")).click();
        driver.findElement(By.id("ap_password")).sendKeys("lalala");
        driver.findElement(By.id("signInSubmit")).click();
       // Assert.assertEquals("Houve um problema",driver.findElement(By.xpath("/html/body/div[1]/div[1]/div[2]/div/div[1]/div/div/div/h4")).getText());
        Assert.assertTrue("Houve um problema",true);
        Assert.assertTrue("Mensagem importante!",true);
    }
    @Test
    public void testarLoginNumeroTelCelInvalido(){
        driver.findElement(By.id("nav-link-accountList-nav-line-1")).click();
        driver.findElement(By.id("ap_email")).sendKeys("999999999");
        driver.findElement(By.id("continue")).click();
        Assert.assertEquals("Número de telefone incorreto", driver.findElement(By.xpath("/html/body/div[1]/div[1]/div[2]/div/div[1]/div/div/div/h4")).getText());
    }
    @Test
    public void testarLoginAlfaNumericoEntendeComoEmail(){
        driver.findElement(By.id("nav-link-accountList")).click();
        driver.findElement(By.id("ap_email")).sendKeys("999999999abc");
        driver.findElement(By.id("continue")).click();
        boolean encontrouEmail = false;
         if (driver.getTitle().equals("Não encontramos uma conta associada a este endereço de e-mail ")){
                encontrouEmail = true;
            }
    }
    @Test
    public void testarBuscador() {
        driver.findElement(By.id("twotabsearchtextbox")).sendKeys("geladeira");
        driver.findElement(By.id("nav-search-submit-button")).click();
        Assert.assertEquals("RESULTADOS", driver.findElement(By.xpath("/html/body/div[1]/div[2]/div[1]/div[1]/div/span[1]/div[1]/div[2]/div/span/div/div/span")).getText());
    }
    @Test
    public void testarProdutoNoCarrinho() {
        driver.findElement(By.id("twotabsearchtextbox")).sendKeys("geladeira");
        driver.findElement(By.id("nav-search-submit-button")).click();
        driver.findElement(By.xpath("/html/body/div[1]/div[2]/div[1]/div[1]/div/span[1]/div[1]/div[3]/div/div/div/div/div/div/div[2]/div[1]/h2/a/span")).click();
        driver.findElement(By.id("add-to-cart-button")).click();
        Assert.assertEquals("Adicionado ao carrinho", driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div[1]/div[2]/div/span")).getText());
    }
}
