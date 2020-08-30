package br.com.rruizdasilva;

import io.restassured.http.ContentType;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import static br.com.rruizdasilva.ReusableMethods.obterNumeroRelatorio;
import static br.com.rruizdasilva.TestData.obterCaminhoArquivoDadosExternos;
import static br.com.rruizdasilva.TestData.obterEnderecoAPI;
import static br.com.rruizdasilva.TestData.obterJsonComDadosVazios;
import static br.com.rruizdasilva.TestData.obterJsonComRegraDeDataDiferente;
import static br.com.rruizdasilva.TestData.obterJsonComRegraDeNomeDiferente;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

public class RelatorioTest {

  private static final String END_API = obterEnderecoAPI();
  private static String TOKEN;
  private static final String REGRA_DADOS_VAZIOS = obterJsonComDadosVazios();
  private static final String REGRA_DATA_INCONSCISTENTE = obterJsonComRegraDeDataDiferente();
  private static final String REGRA_NOME_INCONSCISTENTE = obterJsonComRegraDeNomeDiferente();
  Properties prop = new Properties();

  @BeforeTest
  public void getData() throws IOException {
    File file = new File(obterCaminhoArquivoDadosExternos());
    FileInputStream fis = new FileInputStream(file.getAbsolutePath());
    prop.load(fis);
    TOKEN=System.getProperty("tokencode");
  }

  @Test
  public void enviarRelatorioComDadosVazios(){
    given()
        .log().all()
        .contentType(ContentType.JSON)
        .header("Authorization", TOKEN)
        .body(REGRA_DADOS_VAZIOS)
        .when()
        .post(END_API)
        .then()
        .log().all()
        .statusCode(400)
        .body("message", is("É necessário enviar ao menos um parâmetro para criação do relatório."));
  }

  @Test
  public void enviarRelatorioComDataInconscistente(){
    String numero = obterNumeroRelatorio(TOKEN, REGRA_DATA_INCONSCISTENTE, END_API);
    given()
        .log().all()
        .contentType(ContentType.JSON)
        .header("Authorization", TOKEN)
        .when()
        .get(END_API + "/" + numero)
        .then()
        .log().all()
        .statusCode(200)
        .body("result.mensagem", notNullValue())
        .body("result.status", notNullValue());
  }

  @Test
  public void enviarRelatorioComNomeInconscistente(){
    String numero = obterNumeroRelatorio(TOKEN, REGRA_NOME_INCONSCISTENTE, END_API);
    given()
        .log().all()
        .contentType(ContentType.JSON)
        .header("Authorization", TOKEN)
        .when()
        .get(END_API + "/" + numero)
        .then()
        .log().all()
        .statusCode(200)
        .body("result.mensagem", notNullValue())
        .body("result.status", notNullValue());
  }

  @Test(dataProvider = "obterDadosExternos" )
  public void enviarRelatorioDadosExternos(String data, String nome, String cpf){
    final String regraDadosExternos = TestData.obterJsonComOsSeguintesDados(data, nome, cpf);
    System.out.println(regraDadosExternos);
    String numero = obterNumeroRelatorio(TOKEN, regraDadosExternos, END_API);
    given()
        .log().all()
        .contentType(ContentType.JSON)
        .header("Authorization", TOKEN)
        .when()
        .get(END_API + "/" + numero)
        .then()
        .log().all()
        .statusCode(200)
        .body("result.mensagem", is("Válido."))
        .body("result.resultado", is("VALID"));
    System.out.println(numero);
  }

  @DataProvider
  Object[][] obterDadosExternos() {
    return new Object[][]{
        {
            prop.getProperty("DATA1"),
            prop.getProperty("NOME1"),
            prop.getProperty("CPF1")
        }
    };
  }
}
