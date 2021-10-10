import java.util.ArrayList;
import java.util.Collections;

public class Main {

    public static void main(String[] args) {

        Integer[] nombres = new Integer[6];
        for(int i=0;i<6;i++){
            nombres[i]=Integer.parseInt(args[i]);
        }
        int but = Integer.parseInt(args[6]);
        ArrayList<Integer> nombresDonnes = new ArrayList<>();
        Collections.addAll(nombresDonnes,nombres);

        LeCompteEstBon lceb = new LeCompteEstBon();

        if(!lceb.verifierNombresDonnes(nombresDonnes)){
            System.out.println("Les nombres donnees ne sont pas bons!");
            System.exit(1);
        }
        if(lceb.compterBon(nombresDonnes,but)){
           System.out.println("Trouve");
           System.out.println(lceb.getIterations());
           System.exit(2);
        }

    }
}
