package com.example.demo.controllers;

import com.example.demo.models.Produto;
import com.example.demo.services.ProdutoService;

import java.util.Objects;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ProdutoController {

    private final ProdutoService service;

    public ProdutoController(ProdutoService service){
        this.service = service;
    }
    @GetMapping("/produtos")
    public String listar(Model model){
        model.addAttribute("produtos",  service.listarProduto());
        return "produtos";
    }

    @GetMapping("/produtos/novo")
    public String showFormForAdd(Model model) {
        model.addAttribute("produto", new Produto());
        return "produto-form";
    }

    @PostMapping("/produtos")
    public String saveProduto(Produto produto) {
        if (Objects.isNull(produto.getId()) || produto.getId() == 0) {
            service.adicionar(produto);
        }else{
            service.atualizar(produto.getId(), produto).orElseThrow(() -> new IllegalArgumentException("Produto inválido:" + produto.getId()));
        }
        return "redirect:/produtos";
    }

    @GetMapping("/produtos/editar/{id}")
    public String showFormForUpdate(@PathVariable Long id, Model model) {
        Produto produto = service.buscarProduto(id)
            .orElseThrow(() -> new IllegalArgumentException("Produto inválido:" + id));
        model.addAttribute("produto", produto);
        return "produto-form";
    }

    @GetMapping("/produtos/excluir/{id}")
    public String deleteProduto(@PathVariable Long id) {
        if (!service.excluir(id)){
            throw new IllegalArgumentException("Produto Inválido: " + id);
        }
        return "redirect:/produtos";
    }
}
