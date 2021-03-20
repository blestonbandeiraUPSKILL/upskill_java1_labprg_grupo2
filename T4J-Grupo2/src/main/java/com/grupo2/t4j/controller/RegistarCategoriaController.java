package com.grupo2.t4j.controller;


import com.grupo2.t4j.domain.Categoria;
import com.grupo2.t4j.dto.CategoriaDTO;
import com.grupo2.t4j.persistence.FabricaRepositorios;
import com.grupo2.t4j.persistence.RepositorioAreaActividade;
import com.grupo2.t4j.persistence.RepositorioCategoriaTarefa;
import com.grupo2.t4j.persistence.database.FabricaRepositoriosDatabase;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RegistarCategoriaController {

    private FabricaRepositorios fabricaRepositorios = new FabricaRepositoriosDatabase();
    private RepositorioCategoriaTarefa repositorioCategoriaTarefa = fabricaRepositorios.getRepositorioCategoriaTarefa();
    private RepositorioAreaActividade repositorioAreaActividade = fabricaRepositorios.getRepositorioAreaActividade();


    /**
    * Registar categoria boolean
    *
    * @param codigoCategoria as código da categoria
    * @param descBreve as descrição breve da categoria
    * @param descDetalhada as descrição detalhada da categoria
    * @param codigoAreaActividade as código da área da actividade
    * @return boolean
    */
    public boolean registarCategoria(String codigoCategoria, String descBreve,
                                     String descDetalhada, String codigoAreaActividade
                                     ) throws SQLException {

        Categoria categoria = new Categoria(codigoCategoria, descBreve, descDetalhada, codigoAreaActividade);

        return repositorioCategoriaTarefa.save(categoria);
    }

    /**
     * Devolve uma lista de todas as categorias
     * @return
     * @throws SQLException 
     */
    public List<CategoriaDTO> getAll() throws SQLException {
        List<Categoria> categorias = repositorioCategoriaTarefa.getAll();
        List<CategoriaDTO> categoriasDTO = new ArrayList<>();

        for(Categoria categoria : categorias) {
            categoriasDTO.add((CategoriaDTO) categoria.toDTO());
        }
        return categoriasDTO;
    }

    /**
     * Devolve todas as categorias de uma area de actividade
     * @param codigoAreaActividade
     * @return
     * @throws SQLException 
     */
    public List<CategoriaDTO> findByAreaActividade(String codigoAreaActividade) throws SQLException{
        List<Categoria> categorias = repositorioCategoriaTarefa.findByAreaActividade(codigoAreaActividade);
        List<CategoriaDTO> categoriasDTO = new ArrayList<>();

        for(Categoria categoria : categorias) {
            categoriasDTO.add((CategoriaDTO) categoria.toDTO());
        }
        return categoriasDTO;
    }

    /**
     * Devolve uma categoria a partir do seu id
     * @param codigoCategoria
     * @return
     * @throws SQLException 
     */
    public CategoriaDTO findByCodigo(String codigoCategoria) throws SQLException {
        Categoria categoria = repositorioCategoriaTarefa.findByCodigo(codigoCategoria);
        return (CategoriaDTO) categoria.toDTO();
    }




}

