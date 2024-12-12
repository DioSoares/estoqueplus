package com.estoqueplus.controller;

import com.estoqueplus.model.Produto;
import com.estoqueplus.service.ProdutoService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/almo-sys/produtos")
public class ProdutoController {

    private final ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @GetMapping
    public List<Produto> listarProdutos() {
        return produtoService.listarProdutos();
    }

    @PostMapping
    public Produto cadastrarProduto(@RequestBody @Valid Produto produto) {
        return produtoService.salvarProduto(produto);
    }
}
