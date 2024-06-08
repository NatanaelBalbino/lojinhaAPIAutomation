package modulos.produto;

import dataFactory.ProdutoDataFactory;
import dataFactory.UsuarioDataFactory;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;

@DisplayName("Testes de API REST do módulo de Produto")
public class ProdutoTest {
    private String token;

    @BeforeEach
    public void beforeEach(){
        baseURI = "http://165.227.93.41";
        basePath = "/lojinha";

        this.token= given()
                .contentType(ContentType.JSON)
                .body(UsuarioDataFactory.gerarToken())
            .when()
                .post("/v2/login")
            .then()
                .extract()
                .path("data.token");
    }

    @Test
    @DisplayName("Validar os limites de valor proibidos do Produto: 0.00 Não Permitido")
    public void testValidarLimiteProibidoValorZERADOProduto(){
        String request = given()
                .contentType(ContentType.JSON)
                .header("token", this.token)
                .body(ProdutoDataFactory.criarProdutoComumComOValorIgualA(0.00))
            .when()
                .post("/v2/produtos")
            .then()
                .assertThat()
                    .body("error", equalTo("O valor do produto deve estar entre R$ 0,01 e R$ 7.000,00"))
                .extract()
                    .statusLine();
        System.out.println(request);
    }@Test
    @DisplayName("Validar os limites de valor proibidos do Produto: 7000.01 Não Permitido")
    public void testValidarLimiteProibidoValorSeteMilEUmProduto(){

        String request = given()
                .contentType(ContentType.JSON)
                .header("token", this.token)
                .body(ProdutoDataFactory.criarProdutoComumComOValorIgualA(7000.01))
            .when()
                .post("/v2/produtos")
            .then()
                .assertThat()
                    .body("error", equalTo("O valor do produto deve estar entre R$ 0,01 e R$ 7.000,00"))
                .extract()
                    .statusLine();
        System.out.println(request);
    }
}
