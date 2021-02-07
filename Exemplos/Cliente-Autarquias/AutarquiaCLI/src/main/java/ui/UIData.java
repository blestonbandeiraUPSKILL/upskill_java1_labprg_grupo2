package ui;

import exception.DataInvalidaException;
import model.Data;

public class UIData {
    public static Data getData() {
        boolean flag;
        Data data = null;
        do {
            try {
                flag = false;
                System.out.println("Data");
                int dia = (int) UIGeral.getNumber("Dia");
                int mes = (int) UIGeral.getNumber("Mes");
                int ano = (int) UIGeral.getNumber("Ano");
                data = new Data(dia, mes, ano);
            } catch (DataInvalidaException e) {
                flag = true;
                System.out.println("Atenc~ao: "+ e.getMessage());
            }
        } while (flag);
        return data;
    }
}