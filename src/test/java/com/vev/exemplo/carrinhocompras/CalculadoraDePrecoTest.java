package com.vev.exemplo.carrinhocompras;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.*;

public class CalculadoraDePrecoTest {
    @Test
    void teste_carrinhoVazio() {
        Carrinho carrinho = new Carrinho();
        CalculadoraDePreco calculadoraPreco = CalculadoraDePrecoFactory.freteSimplesDolarBuild();
        
        assertEquals(0, calculadoraPreco.calculaCustoBasico(carrinho));
        assertEquals(0, calculadoraPreco.calculaCustoAdicional(carrinho));
        assertEquals(0, calculadoraPreco.calculaCustoFinal(carrinho));
    }

    
    //100,Banana,2.5,Real
    @Test
    void teste_CarrinhoUmItem() {
        Carrinho carrinho = new Carrinho();
        CadastroProduto catalogoProduto = new CadastroProduto(new LeitorDeProdutos("produtos.dat"));
        CalculadoraDePreco calculadoraPreco = CalculadoraDePrecoFactory.freteSimplesDolarBuild();
        ItemDeCarrinho item = new ItemDeCarrinho(catalogoProduto.recuperaPorCodigo(100), 2);
        
        carrinho.novoItem(item);
        assertEquals(5.0, calculadoraPreco.calculaCustoBasico(carrinho), 0.001);
        assertEquals(5.0, calculadoraPreco.calculaCustoAdicional(carrinho), 0.001);
        assertEquals(10.0, calculadoraPreco.calculaCustoFinal(carrinho), 0.001);
    }

    //120,Maca,4.5,Peso
    //105,Laranja,1.2,Dolar
    //135,Limao,0.8,Real
    @Test
    void teste_CarrinhoMultiplosItem() {
        Carrinho carrinho = new Carrinho();
        CadastroProduto catalogoProduto = new CadastroProduto(new LeitorDeProdutos("produtos.dat"));
        CalculadoraDePreco calculadoraPreco = CalculadoraDePrecoFactory.freteSimplesDolarBuild();
        
        carrinho.novoItem(new ItemDeCarrinho(catalogoProduto.recuperaPorCodigo(120), 1));
        carrinho.novoItem(new ItemDeCarrinho(catalogoProduto.recuperaPorCodigo(105), 2));
        carrinho.novoItem(new ItemDeCarrinho(catalogoProduto.recuperaPorCodigo(135), 3));
        
        assertEquals(6.5, calculadoraPreco.calculaCustoBasico(carrinho), 0.001);
        assertEquals(12.5, calculadoraPreco.calculaCustoAdicional(carrinho), 0.001);
        assertEquals(19.0, calculadoraPreco.calculaCustoFinal(carrinho), 0.001);
    }
}
