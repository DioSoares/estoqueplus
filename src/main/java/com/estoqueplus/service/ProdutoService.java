package com.estoqueplus.service;

import com.estoqueplus.dto.ProdutoDTO;
import com.estoqueplus.exception.ResourceNotFoundException;
import com.estoqueplus.model.Produto;
import com.estoqueplus.repository.CategoriaRepository;
import com.estoqueplus.repository.ProdutoRepository;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProdutoService {

    private final ProdutoRepository produtoRepository;
    private final CategoriaRepository categoriaRepository;

    public ProdutoService(ProdutoRepository produtoRepository, CategoriaRepository categoriaRepository) {
        this.produtoRepository = produtoRepository;
        this.categoriaRepository = categoriaRepository;
    }

    public List<ProdutoDTO> listarProdutos() {
        return produtoRepository.findAll()
                .stream()
                .map(this::converterParaDTO)
                .collect(Collectors.toList());
    }

    public ProdutoDTO buscarProdutoPorId(Long id) {
        Produto produto = produtoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Produto não encontrado com ID: " + id));
        return converterParaDTO(produto);
    }

    public ProdutoDTO salvarProduto(Produto produto) {
        if (produto.getCategoria() == null || !categoriaRepository.existsById(produto.getCategoria().getId())) {
            throw new ResourceNotFoundException("Categoria não encontrada");
        }
        Produto produtoSalvo = produtoRepository.save(produto);
        return converterParaDTO(produtoSalvo);
    }

    public ProdutoDTO atualizarProduto(Long id, Produto produtoAtualizado) {
        Produto produtoExistente = produtoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Produto não encontrado com ID: " + id));

        if (produtoAtualizado.getCategoria() == null ||
                !categoriaRepository.existsById(produtoAtualizado.getCategoria().getId())) {
            throw new ResourceNotFoundException("Categoria não encontrada");
        }

        produtoExistente.setNome(produtoAtualizado.getNome());
        produtoExistente.setPreco(produtoAtualizado.getPreco());
        produtoExistente.setQuantidade(produtoAtualizado.getQuantidade());
        produtoExistente.setCategoria(produtoAtualizado.getCategoria());

        Produto produtoSalvo = produtoRepository.save(produtoExistente);
        return converterParaDTO(produtoSalvo);
    }


    public void excluirProduto(Long id) {
        if (!produtoRepository.existsById(id)) {
            throw new ResourceNotFoundException("Produto não encontrado com ID: " + id);
        }
        produtoRepository.deleteById(id);
    }

    private ProdutoDTO converterParaDTO(Produto produto) {
        return new ProdutoDTO(
                produto.getId(),
                produto.getNome(),
                produto.getPreco(),
                produto.getQuantidade(),
                produto.getCategoria().getNome()
        );
    }
}