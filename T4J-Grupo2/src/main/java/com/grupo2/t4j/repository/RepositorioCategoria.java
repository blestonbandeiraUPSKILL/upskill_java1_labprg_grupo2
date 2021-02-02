package com.grupo2.t4j.repository;

import com.grupo2.t4j.exception.CategoriaDuplicadaException;
import com.grupo2.t4j.model.Categoria;
import java.util.ArrayList;
import java.util.List;

public class RepositorioCategoria {
    private static RepositorioCategoria instance;
    private List<Categoria> listaCategorias = new ArrayList<>();
    
    public static RepositorioCategoria getInstance(){
        if (instance == null){
            instance = new RepositorioCompetenciaTecnica();
        }
        return instance;
    }
   /**
    * Adiciona uma categoria Ã  lista de categorias
    * @param categoria
    * @throws CategoriaDuplicadaException 
    */
    public void addCategoria(Categoria categoria) throws CategoriaDuplicadaException {
        Categoria c = getCategoriaById(categoria.getId());
        if (c == null) {
            this.listaCategorias.add(categoria);
        } else {
            throw new CategoriaDuplicadaException(c.getId() + ": Categoria jÃ¡ existe");
        }
    }
    /**
     * Retorina uma cÃ³pida da categoria referente a um determinado id
     * @param id
     * @return copia
     */
    private Categoria getCategoriaById(String id) {
        Categoria categoria = null;
        for (int i = 0; i < this.listaCategorias.size(); i++) {
            categoria = this.listaCategorias.get(i);
            if (categoria.getId().equals(id)) {
                Categoria copia = new Categoria(categoria);
                return copia;
            }
        }
        return null;
    }
    
    
    
}
