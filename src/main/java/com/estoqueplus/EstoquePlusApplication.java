package com.estoqueplus;

import com.estoqueplus.model.Produto;
import com.estoqueplus.repository.ProdutoRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class EstoquePlusApplication {

	public static void main(String[] args) {


		SpringApplication.run(EstoquePlusApplication.class, args);
	}

	/*@Bean
	public CommandLineRunner initData(ProdutoRepository repository) {
		return args -> {
			Produto produto1 = new Produto();
			produto1.setNome("Caneta");
			produto1.setQuantidade(100);
			produto1.setPreco(1.50);
			repository.save(produto1);
		};
	}*/
}