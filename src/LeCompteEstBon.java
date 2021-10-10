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

    public boolean compterBon(){
        boolean trouve;
        if(nombresDonnes.contains(solution)){
            trouve = true;
        }
        else{
            trouve = false;
            while(!trouve && nombresDonnes.size()>1){

            }
        }
        return trouve;
    }

    public int calculer(int a, int b, Calcul c) {
        int res = -1;
        switch (c) {
            case ADDITION -> res= a + b;
            case DIVISION -> {
                if (a % b == 0) {
                    res= a/b;
                }
            }
            case SOUSTRACTION -> {
                if(a-b>1){
                    res= a-b;
                }
            }
            case MULTIPLICATION -> res= a*b;
        }
        return res;
    }
}
