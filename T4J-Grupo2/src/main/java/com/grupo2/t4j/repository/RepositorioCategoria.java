package com.grupo2.t4j.repository;


import com.grupo2.t4j.exception.CategoriaDuplicadaException;
import com.grupo2.t4j.model.AreaActividade;
import com.grupo2.t4j.model.Categoria;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class RepositorioCategoria implements Serializable{

    private static RepositorioCategoria instance;
    private List<Categoria> listaCategorias;
    
    /**
     * Construtor do repositorio
     */
    private RepositorioCategoria(){
        listaCategorias = new ArrayList<>();
    }
    /**
     * Garante que existe apenas um repositorio para categorias
     * @return 
     */
    public static RepositorioCategoria getInstance(){
        if (instance == null){
            instance = new RepositorioCategoria();
        }
        return instance;
    }
    
   
   /**
    * Adiciona uma categoria a  lista de categorias
    * @param categoria
    * @throws CategoriaDuplicadaException 
    */
    public void addCategoria(Categoria categoria) throws CategoriaDuplicadaException {
        Categoria c = getCategoriaById(categoria.getId());
        if (c == null) {
            this.listaCategorias.add(categoria);
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
    
}
