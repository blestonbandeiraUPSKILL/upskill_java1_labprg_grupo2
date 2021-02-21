package com.grupo2.t4j.persistence.inmemory;


import com.grupo2.t4j.exception.CategoriaDuplicadaException;
import com.grupo2.t4j.model.CaracterizacaoCT;
import com.grupo2.t4j.model.Categoria;
import com.grupo2.t4j.persistence.RepositorioCategoriaTarefa;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class RepositorioCategoriaTarefaInMemory implements Serializable, RepositorioCategoriaTarefa {

    private static RepositorioCategoriaTarefaInMemory repositorioCategoriaTarefaInMemory;
    private List<Categoria> listaCategorias;
    
    /**
     * Construtor do repositorio
     */
    RepositorioCategoriaTarefaInMemory(){
        listaCategorias = new ArrayList<>();
    }
    /**
     * Garante que existe apenas um repositorio para categorias
     * @return 
     */
    public static RepositorioCategoriaTarefaInMemory getInstance(){
        if (repositorioCategoriaTarefaInMemory == null){
            repositorioCategoriaTarefaInMemory = new RepositorioCategoriaTarefaInMemory();
        }
        return repositorioCategoriaTarefaInMemory;
    }

    @Override
    public void save(String codigoCategoria, String descBreve, String descDetalhada,
                     String codigoAreaActividade, List<CaracterizacaoCT> caracterizacaoCTS) {


    }

    @Override
    public boolean save(Categoria categoria) {
        Categoria c = findByCodigo(categoria.getCodigoCategoria());
        if (c == null) {
            this.listaCategorias.add(categoria);
            return true;
        } else {
            throw new CategoriaDuplicadaException(c.getCodigoCategoria() + ": Categoria já existe");
        }

    }

    @Override
    public Categoria findByCodigo(String codigoCategoria) {
        for (int i = 0; i < this.listaCategorias.size(); i++) {
            Categoria categoria = this.listaCategorias.get(i);
            if (categoria.getCodigoCategoria().equals(codigoCategoria)) {
                return categoria;
            }
        }
        return null;
    }

    @Override
    public ArrayList<Categoria> findByAreaActividade(String codigoAreaActividade) {
        ArrayList<Categoria> categoriaPorAt = new ArrayList<>();

        for (Categoria cat : listaCategorias) {
            if (cat.getCodigoAreaActividade().equals(codigoAreaActividade)) {
                categoriaPorAt.add(cat);
            }
        }
        return categoriaPorAt;
    }

    @Override
    public ArrayList<Categoria> getAll() {
        return new ArrayList<Categoria>(listaCategorias);
    }

    public int adicionarListaCategorias(RepositorioCategoriaTarefaInMemory outraListaCategorias) {
        int totalCategoriasAdicionadas = 0;

        for (Categoria categoria : outraListaCategorias.listaCategorias) {
            boolean categoriaAdicionada = save(categoria);
            if (categoriaAdicionada) {
                totalCategoriasAdicionadas++;
            }
        }
        return totalCategoriasAdicionadas;
    }
}
