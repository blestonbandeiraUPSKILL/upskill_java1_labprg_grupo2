package ui;

import controller.PessoasController;
import dto.Mapper;
import dto.PessoaDTO;
import exception.NifInvalidoException;
import exception.NomePessoaInvalidoException;
import model.Data;
import model.Pessoa;
import network.HttpStatusCode;
import utils.Constants;
import utils.Response;

import java.util.ArrayList;
import java.util.List;

public class UIPessoa {
    public static void mainPessoa() {
        Pessoa pessoa = null;
        Response response = null;
        PessoaDTO pessoaDTO = null;
        long nif;
        int op;
        do {
            op = menuPessoa();
            switch (op) {
                case 0:
                    System.out.println("Volta para o menu anterior.");
                    break;
                case 1:
                    System.out.println("\nInserir\n");
                    pessoa = getPessoa();
                    pessoaDTO = Mapper.pessoa2PessoaDTO(pessoa);
                    response = PessoasController.addPessoa(Constants.HOST + "api/pessoas", pessoaDTO);
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
                    nif = UIGeral.getNumber("NIF");
                    response = PessoasController.getPessoa(Constants.HOST + "api/pessoas/" + nif);
                    if (response != null) {
                        Object object = response.getBody();
                        switch (response.getStatus()) {
                            case HttpStatusCode.OK:
                                if (object instanceof Pessoa) {
                                    pessoa = (Pessoa) object;
                                    printPessoa(pessoa);
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
                    nif = UIGeral.getNumber("NIF");
                    response = PessoasController.deletePessoa(Constants.HOST + "api/pessoas/" + nif);
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
                    pessoa = getPessoa();
                    pessoaDTO = Mapper.pessoa2PessoaDTO(pessoa);
                    nif = pessoa.getNif();
                    response = PessoasController.updatePessoa(Constants.HOST + "api/pessoas/" + nif, pessoaDTO);
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
                    response = PessoasController.getPesssoas(Constants.HOST + "api/pessoas/");
                    if (response != null) {
                        Object object = response.getBody();
                        switch (response.getStatus()) {
                            case HttpStatusCode.OK:
                                if (object instanceof List) {
                                    ArrayList<Pessoa> pessoas = (ArrayList) object;
                                    printPessoas(pessoas);
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
                    System.out.println("Opc~ao Errada");
                    break;
            }
        } while (op != 0);
    }
    private static int menuPessoa() {
        int op;
        do {
            System.out.println("\nMenu Pessoas");
            System.out.println("1 - Inserir");
            System.out.println("2 - Listar");
            System.out.println("3 - Eliminar");
            System.out.println("4 - Alterar");
            System.out.println("5 - Listar (Todas)");
            System.out.println("\n0 - Voltar");
            op = (int) UIGeral.getNumber("--->");
        } while (op < 0 || op > 5);
        return op;
    }
    public static Pessoa getPessoa() {
        Pessoa pessoa = new Pessoa();
        boolean flag;
        do {
            try {
                flag = false;
                long nif = UIGeral.getNumber("NIF");
                pessoa.setNif(nif);
            } catch (NifInvalidoException e) {
                flag = true;
                System.out.println("Atenc~ao: " + e.getMessage());
            }
        } while (flag);
        do {
            try {
                flag = false;
                String nome = UIGeral.getText("Nome");
                pessoa.setNome(nome);
            } catch (NomePessoaInvalidoException e) {
                flag = true;
                System.out.println("Atenc~ao: " + e.getMessage());
            }
        } while (flag);
        Data data = UIData.getData();
        pessoa.setNascimento(data);
        return pessoa;
    }
    public static void printPessoa(Pessoa pessoa) {
        System.out.print("[" + pessoa.getNif() + "] " + pessoa.getNome() + ", " + pessoa.getNascimento().
                getDia() + "/" + pessoa.getNascimento().getMes() + "/" + pessoa.getNascimento().getAno());
    }
    public static void printPessoas(ArrayList<Pessoa> pessoas) {
        Pessoa pessoa = null;
        for (int i = 0; i < pessoas.size(); i++) {
            pessoa = pessoas.get(i);
            printPessoa(pessoa);
            System.out.print("\n");
        }
    }
}