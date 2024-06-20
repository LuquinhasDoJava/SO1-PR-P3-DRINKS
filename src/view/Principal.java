package view;
import controller.ControllerDrinks;
import java.io.IOException;
import java.util.Scanner;
public class Principal {
    public static void main(String[] args) throws IOException {
        ControllerDrinks cd = new ControllerDrinks();
        Scanner sn = new Scanner(System.in);
        int x;
        do {
            System.out.println("1- Gravar arquivo\n2- Mostrar drinks e instruções");
            x = sn.nextInt();
            switch (x){
                case 1:
                    cd.gravarArq();
                    break;
                case 2:
                    cd.exibirInstr();
                    break;
                default:
                    System.out.println("Digite um valor valido!!");
            }
        }while(x!=9);
    }
}
