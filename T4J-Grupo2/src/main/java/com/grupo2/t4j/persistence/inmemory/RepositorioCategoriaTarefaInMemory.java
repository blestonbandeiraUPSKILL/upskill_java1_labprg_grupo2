package com.grupo2.t4j.persistence.inmemory;


import com.grupo2.t4j.exception.CategoriaDuplicadaException;
import com.grupo2.t4j.model.AreaActividade;
import com.grupo2.t4j.model.CaracterizacaoCT;
import com.grupo2.t4j.model.Categoria;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class RepositorioCategoriaTarefaInMemory implements Serializable{

    private static RepositorioCategoriaTarefaInMemory instance;
    private List<Categoria> listaCategorias;
    
    /**
     * Construtor do repositorio
     */
    private RepositorioCategoriaTarefaInMemory(){
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

    public Categoria novaCategoriaTarefa (String descBreve, String descDetalhada, AreaActividade areaActividade,
                                List<CaracterizacaoCT> caracterizacaoCTS) {
        return new Categoria(descBreve, descDetalhada, areaActividade, caracterizacaoCTS);
    }
   
   /**
    * Adiciona uma categoria a  lista de categorias
    * @param categoria
    * @throws CategoriaDuplicadaException
    * @return
    */
    public boolean addCategoria(Categoria categoria) throws CategoriaDuplicadaException {
        Categoria c = getCategoriaById(categoria.getId());
        if (c == null) {
            this.listaCategorias.add(categoria);
            return true;
        } else {
            throw new CategoriaDuplicadaException(c.getId() + ": Categoria ja existe");
        }

    }

    /**
     * Retorna uma copida da categoria referente a um determinado id
     * @param id
     * @return copia
     */
    public Categoria getCategoriaById(String id) {
        Categoria categoria = null;
        for (int i = 0; i < this.listaCategorias.size(); i++) {
            categoria = this.listaCategorias.get(i);
            if (categoria.getId().equals(id)) {
               // Categoria copia = new Categoria(categoria);
                return categoria;
            }
        }
        return null;
    }
    
    /**
     * Retorna uma lista de categorias referentes a uma determinada Area de Actividade
     * @param at
     * @return 
     */
    public ArrayList<Categoria> getCategoriasByAreaActividade(AreaActividade at) {
        ArrayList<Categoria> categoriaPorAt = new ArrayList<>();

        for (Categoria cat : listaCategorias) {
            if (cat.getAt().equals(at)) {
                categoriaPorAt.add(cat);
            }
        }

        return categoriaPorAt;
    }
    
    /**
     * Retorna a lista de todas as categorias registadas
     * @return 
     */
    public ArrayList<Categoria> getCategorias() {

        return new ArrayList<Categoria>(listaCategorias);
    }

    public boolean addCategoria(String descBreve,
                                String descDetalhada,
                                AreaActividade areaActividade,
                                List<CaracterizacaoCT> ccts) {

       return listaCategorias.add(new Categoria(descBreve, descDetalhada, areaActividade, ccts));

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
}
