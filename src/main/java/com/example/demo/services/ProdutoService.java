package com.example.demo.services;

import com.example.demo.models.Produto;
import com.example.demo.repositories.ProdutoRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService  {

    private final ProdutoRepository repository;
 
    public ProdutoService(ProdutoRepository repository){
        this.repository = repository;
    }

 
    public List<Produto> listarProduto(){
        return repository.findAll();
    }

    public Optional<Produto> buscarProduto(Long id){
        return repository.findById(id);
    }

    public Produto adicionar(Produto produto){
        return repository.save(produto);
    }

    public Optional<Produto> atualizar(Long id, Produto novoProduto){
        return buscarProduto(id).map(p -> {
            p.setNome(novoProduto.getNome());
            p.setPreco(novoProduto.getPreco());
            return repository.save(p);
        });
    }

    public boolean excluir(Long id){
        return buscarProduto(id).map(produto -> {
            repository.delete(produto);
            return true;
        }).orElse(false);
    }
}
