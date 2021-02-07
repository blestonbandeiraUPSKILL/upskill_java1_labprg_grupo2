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
public class FileChooserAreaActividade {
    private FileChooser fileChooser;

    private FileChooserAreaActividade(String descricao, String extensao) {
        fileChooser = new FileChooser();

        associarFiltro(descricao, extensao);
    }

    public static FileChooser criarFileChooserAreaActividade(String descricao, String extensao) {
        FileChooserAreaActividade fcAreaActividade = new FileChooserAreaActividade(descricao, extensao);

        return fcAreaActividade.fileChooser;
    }

    private void associarFiltro(String descricao, String extensao) {
        FileChooser.ExtensionFilter filtro = new FileChooser.ExtensionFilter(descricao, extensao);
        fileChooser.getExtensionFilters().add(filtro);
    }
}
