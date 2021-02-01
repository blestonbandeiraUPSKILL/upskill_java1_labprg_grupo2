import ui.UIFuncionario;
import ui.UIGeral;
import ui.UIPessoa;

public class AutarquiaCliApplication {

    public static void main(String[] args) {
        int op;
        System.out.println("AutarquiaCliApplication iniciou.");
        do {
            op = menu();
            switch (op) {
                case 0:
                    System.out.println("AutarquiaCliApplication terminou. Adeus.");
                    break;
                case 1:
                    UIPessoa.mainPessoa();
                    break;
                case 2:
                    UIFuncionario.mainFuncionario();
                    break;
                default:
                    System.out.println("Opc~ao Errada");
                    break;
            }
        } while (op != 0);
    }

    private static int menu() {
        int op;
        do {
            System.out.println("\nMenu Principal");
            System.out.println("1 - Gerir pessoas");
            System.out.println("2 - Gerir funcionarios");
            System.out.println("\n0 - Sair");
            op = (int) UIGeral.getNumber("--->");
        } while (op < 0 || op > 2);
        return op;
    }
}