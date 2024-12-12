package com.estoqueplus.service;

import com.estoqueplus.exception.ResourceNotFoundException;
import com.estoqueplus.model.Produto;
import com.estoqueplus.repository.ProdutoRepository;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoService {

    private final ProdutoRepository produtoRepository;

    public ProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    public List<Produto> listarProdutos() {
        return produtoRepository.findAll();
    }

    public Produto buscarProdutoPorId(Long id ) {
        return produtoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Produto não encontrado com ID: " + id));
    }

    public Produto salvarProduto(Produto produto) {
        return produtoRepository.save(produto);
    }

    public Produto atualizarProduto(Long id, Produto produtoAtualizado) {
        Produto produtoExistente = produtoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Produto não encontrado com ID: " + id));

                produtoExistente.setNome(produtoAtualizado.getNome());
                produtoExistente.setQuantidade(produtoAtualizado.getQuantidade());
                produtoExistente.setPreco(produtoAtualizado.getPreco());

                return produtoRepository.save(produtoExistente);
    }
}