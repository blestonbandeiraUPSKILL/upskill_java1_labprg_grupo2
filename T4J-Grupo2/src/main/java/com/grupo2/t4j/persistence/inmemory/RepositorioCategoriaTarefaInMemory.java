package com.grupo2.t4j.persistence.inmemory;


import com.grupo2.t4j.exception.CategoriaDuplicadaException;
import com.grupo2.t4j.model.AreaActividade;
import com.grupo2.t4j.model.CaracterizacaoCT;
import com.grupo2.t4j.model.Categoria;
import com.grupo2.t4j.persistence.RepositorioCategoriaTarefa;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class RepositorioCategoriaTarefaInMemory implements Serializable, RepositorioCategoriaTarefa {

    private static RepositorioCategoriaTarefaInMemory instance;
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
        if (instance == null){
            instance = new RepositorioCategoriaTarefaInMemory();
        }
        return instance;
    }

   


    
    public int adicionarListaCategorias(RepositorioCategoriaTarefaInMemory outraListaCategorias) {
        int totalCategoriasAdicionadas = 0;
        
        for (Categoria categoria : outraListaCategorias.listaCategorias) {
            boolean categoriaAdicionada = addCategoria(categoria);
            if (categoriaAdicionada) {
                totalCategoriasAdicionadas++;
            }
        }
        return totalCategoriasAdicionadas;
    }

    @Override
    public void save(String descBreve, String descDetalhada, AreaActividade areaActividade, List<CaracterizacaoCT> caracterizacaoCTS) {

    }

    /**
     * Adiciona uma categoria a  lista de categorias
     * @param categoria
     * @throws CategoriaDuplicadaException
     * @return
     */
    public boolean addCategoria(Categoria categoria) throws CategoriaDuplicadaException {
        Categoria c = findById(categoria.getId());
        if (c == null) {
            this.listaCategorias.add(categoria);
            return true;
        } else {
            throw new CategoriaDuplicadaException(c.getId() + ": Categoria ja existe");
        }

    }

    @Override
    public Categoria findById(String id) {
        for (int i = 0; i < this.listaCategorias.size(); i++) {
            Categoria categoria = this.listaCategorias.get(i);
            if (categoria.getId().equals(id)) {
                return categoria;
            }
        }
        return null;
    }

    @Override
    public ArrayList<Categoria> findByAreaActividade(AreaActividade at) {
        ArrayList<Categoria> categoriaPorAt = new ArrayList<>();

        for (Categoria cat : listaCategorias) {
            if (cat.getAt().equals(at)) {
                categoriaPorAt.add(cat);
            }
        }
        return categoriaPorAt;
    }

    @Override
    public ArrayList<Categoria> getAll() {
        return new ArrayList<Categoria>(listaCategorias);
    }
}
