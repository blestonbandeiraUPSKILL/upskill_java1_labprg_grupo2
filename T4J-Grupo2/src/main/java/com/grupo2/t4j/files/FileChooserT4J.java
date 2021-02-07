/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.grupo2.t4j.files;

import javafx.stage.FileChooser;

/**
 *
 * @author acris
 */
public class FileChooserT4J {
    private FileChooser fileChooser;

    private FileChooserT4J(String descricao, String extensao) {
        fileChooser = new FileChooser();

        associarFiltro(descricao, extensao);
    }

    public static FileChooser criarFileChooserT4J(String descricao, String extensao) {
        FileChooserT4J fcT4J = new FileChooserT4J(descricao, extensao);

        return fcT4J.fileChooser;
    }

    private void associarFiltro(String descricao, String extensao) {
        FileChooser.ExtensionFilter filtro = new FileChooser.ExtensionFilter(descricao, extensao);
        fileChooser.getExtensionFilters().add(filtro);
    }
    
}
