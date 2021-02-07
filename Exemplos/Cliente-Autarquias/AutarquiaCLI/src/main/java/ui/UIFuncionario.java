package ui;

import controller.FuncionariosController;
import dto.FuncionarioDTO;
import dto.Mapper;
import exception.NumeroFuncionarioInvalidoException;
import model.Funcionario;
import model.Pessoa;
import network.HttpStatusCode;
import utils.Constants;
import utils.Response;

import java.util.ArrayList;
import java.util.List;

public class UIFuncionario {
    public static void mainFuncionario() {
        Funcionario funcionario = null;
        Response response = null;
        FuncionarioDTO funcionarioDTO = null;
        int numero;
        int op;
        do {
            op = menuFuncionario();
            switch (op) {
                case 0:
                    System.out.println("Volta para o menu anterior.");
                    break;
                case 1:
                    System.out.println("\nInserir\n");
                    funcionario = getFuncionario();
                    funcionarioDTO = Mapper.funcionario2FuncionarioDTO(funcionario);
                    response = FuncionariosController.addFuncionario(Constants.HOST + "api/funcionarios",
                            funcionarioDTO);
                    if (response != null) {
                        Object object = response.getBody();
                        switch (response.getStatus()) {
                            case HttpStatusCode.Created:
                                System.out.println(HttpStatusCode.Created + "- Created");
                                break;
                            case HttpStatusCode.Conflict:
                                if (object instanceof String) {
                                    String message = (String) object;
                                    System.out.println(HttpStatusCode.Conflict + "- Conflict:" + message);
                                }
                            default:
                                break;
                        }
                    }
                    break;
                case 2:
                    System.out.println("\nPesquisar\n");
                    numero = (int) UIGeral.getNumber("Numero");
                    response = FuncionariosController.getFuncionario(Constants.HOST + "api/funcionarios/" + numero);
                    if (response != null) {
                        Object object = response.getBody();
                        switch (response.getStatus()) {
                            case HttpStatusCode.OK:
                                if (object instanceof Funcionario) {
                                    funcionario = (Funcionario) object;
                                    printFuncionario(funcionario);
                                }
                                break;
                            case HttpStatusCode.Conflict:
                                if (object instanceof String) {
                                    String message = (String) object;
                                    System.out.println(HttpStatusCode.Conflict + "- Conflict:" + message);
                                }
                            default:
                                break;
                        }
                    }
                    break;
                case 3:
                    System.out.println("\nRemover\n");
                    numero = (int) UIGeral.getNumber("Numero");
                    response = FuncionariosController.deleteFuncionario(Constants.HOST + "api/funcionarios/" + numero);
                    if (response != null) {
                        Object object = response.getBody();
                        switch (response.getStatus()) {
                            case HttpStatusCode.OK:
                                System.out.println("Deleted");
                                break;
                            case HttpStatusCode.Conflict:
                                if (object instanceof String) {
                                    String message = (String) object;
                                    System.out.println(HttpStatusCode.Conflict + "- Conflict:" + message);
                                }
                            default:
                                break;
                        }
                    }
                    break;
                case 4:
                    System.out.println("\nAlterar\n");
                    funcionario = getFuncionario();
                    funcionarioDTO = Mapper.funcionario2FuncionarioDTO(funcionario);
                    numero = funcionario.getNumeroFuncionario();
                    response = FuncionariosController.updateFuncionario(Constants.HOST + "api/funcionarios/" + numero,
                            funcionarioDTO);
                    if (response != null) {
                        Object object = response.getBody();
                        switch (response.getStatus()) {
                            case HttpStatusCode.OK:
                                System.out.println("Updated");
                                break;
                            case HttpStatusCode.Conflict:
                                if (object instanceof String) {
                                    String message = (String) object;
                                    System.out.println(HttpStatusCode.Conflict + "- Conflict:" + message);
                                }
                            default:
                                break;
                        }
                    }
                    break;
                case 5:
                    System.out.println("\nListar (Todas)\n");
                    response = FuncionariosController.getFuncionarios(Constants.HOST + "api/funcionarios/");
                    if (response != null) {
                        Object object = response.getBody();
                        switch (response.getStatus()) {
                            case HttpStatusCode.OK:
                                if (object instanceof List) {
                                    ArrayList<Funcionario> funcionarios = (ArrayList<Funcionario>) object;
                                    printFuncionarios(funcionarios);
                                }
                                break;
                            case HttpStatusCode.Conflict:
                                if (object instanceof String) {
                                    String message = (String) object;
                                    System.out.println(HttpStatusCode.Conflict + "- Conflict:" + message);
                                }
                            default:
                                break;
                        }
                    }
                    break;
                default:
                    System.out.println("Opção Errada");
                    break;
            }
        } while (op != 0);
    }
    private static int menuFuncionario() {
        int op;
        do {
            System.out.println("\nMenu Funcionario");
            System.out.println("1 - Inserir ");
            System.out.println("2 - Listar ");
            System.out.println("3 - Eliminar ");
            System.out.println("4 - Alterar ");
            System.out.println("5 - Listar (Todos) ");
            System.out.println("\n0 - Voltar");
            op = (int) UIGeral.getNumber("--->");
        } while (op < 0 || op > 5);
        return op;
    }
    public static Funcionario getFuncionario() {
        Pessoa pessoa = UIPessoa.getPessoa();
        Funcionario funcionario = new Funcionario(pessoa.getNif(), pessoa.getNome(), pessoa.getNascimento());
        boolean flag;
        do {
            flag = false;
            try {
                int numeroFuncionario = (int) UIGeral.getNumber("Numero");
                funcionario.setNumeroFuncionario(numeroFuncionario);
            } catch (NumeroFuncionarioInvalidoException e) {
                flag = true;
                System.out.println("Atenção: " + e.getMessage());
            }
        } while (flag);
        String cargo = UIGeral.getText("Cargo");
        funcionario.setCargo(cargo);
        return funcionario;
    }
    public static void printFuncionario(Funcionario funcionario) {
        System.out.print("[" + funcionario.getNumeroFuncionario() + "]" + funcionario.getNome() + ", " +
                funcionario.getCargo() + ", " + funcionario.getNif() + "," + funcionario.getNascimento().getDia
                () + "/" + funcionario.getNascimento().getMes() + "/" + funcionario.getNascimento().getAno());
    }
    public static void printFuncionarios(ArrayList<Funcionario> funcionarios) {
        Funcionario funcionario = null;
        for (int i = 0; i < funcionarios.size(); i++) {
            funcionario = funcionarios.get(i);
            printFuncionario(funcionario);
            System.out.print("\n");
        }
    }
}
