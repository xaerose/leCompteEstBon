import java.util.ArrayList;
import java.util.Collections;

public class LeCompteEstBon {

    private int solution;
    private ArrayList<Integer> nombresDonnes;
    private ArrayList<Integer> listeNombresAutorisees;

    public LeCompteEstBon(Integer[] nombres,int but){

        listeNombresAutorisees = new ArrayList<Integer>();
        Collections.addAll(listeNombresAutorisees,1,2,3,4,5,6,7,8,9,10,25,50,75,100);
        nombresDonnes = new ArrayList<>();
        Collections.addAll(nombresDonnes,nombres);
        solution = but;
    }

    public boolean verifierNombresDonnes(){
        return listeNombresAutorisees.containsAll(nombresDonnes);
    }
}
