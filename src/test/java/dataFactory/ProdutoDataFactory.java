package dataFactory;

import pojo.ComponentePojo;
import pojo.ProdutoPojo;

import java.util.ArrayList;
import java.util.List;

public class ProdutoDataFactory {
    public static ProdutoPojo criarProdutoComumComOValorIgualA(double valor){
        ProdutoPojo produto = new ProdutoPojo();

        produto.setProdutoNome("Garrafa PET");

        produto.setProdutoValor(valor);

        List<String> cores = new ArrayList<>();
        cores.add("Blue");
        produto.setProdutoCores(cores);
        produto.setProdutoUrlMock("");

        List<ComponentePojo> componentesList = new ArrayList<>();
        ComponentePojo componente = new ComponentePojo();
        componente.setComponenteNome("Copo");
        componente.setComponenteQuantidade(3);
        componentesList.add(componente);
        produto.setComponentes(componentesList);

        return produto;
    }
}
