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
public class FileChooserCategoria {
    private FileChooser fileChooser;

    private FileChooserCategoria(String descricao, String extensao) {
        fileChooser = new FileChooser();

        associarFiltro(descricao, extensao);
    }

    public static FileChooser criarFileChooserCategoria(String descricao, String extensao) {
        FileChooserCategoria fcCategoria = new FileChooserCategoria(descricao, extensao);

        return fcCategoria.fileChooser;
    }

    private void associarFiltro(String descricao, String extensao) {
        FileChooser.ExtensionFilter filtro = new FileChooser.ExtensionFilter(descricao, extensao);
        fileChooser.getExtensionFilters().add(filtro);
    }
}
