import java.util.ArrayList;
import java.util.Collections;

public class LeCompteEstBon {

    private int solution;
    private ArrayList<Integer> listeNombresAutorisees;
    private int iterations;

    public LeCompteEstBon(int but){
        listeNombresAutorisees = new ArrayList<Integer>();
        Collections.addAll(listeNombresAutorisees,1,2,3,4,5,6,7,8,9,10,25,50,75,100);
        solution = but;
    }

    public boolean verifierNombresDonnes(ArrayList<Integer> liste){
        return listeNombresAutorisees.containsAll(liste);
    }

    public boolean compterBon(ArrayList<Integer> liste){
        iterations++;
        boolean trouve=false;
        if(liste.contains(solution)){
            return true;
        }
        else{
            trouve = false;
            while(!trouve && (liste.size()>1)){
                int a = liste.get(0);
                int b = liste.get(1);
                if(calculer(a,b,Calcul.ADDITION)==solution){
                    trouve = true;
                }
                else{
                    ArrayList<Integer> liste1 = (ArrayList<Integer>) liste.clone();
                    liste1.add(0,calculer(a,b,Calcul.ADDITION));
                    liste1.remove((Integer) a);
                    liste1.remove((Integer) b);
                    trouve = this.compterBon(liste1);
                }
                if(calculer(a,b,Calcul.MULTIPLICATION)==solution){
                    trouve = true;
                }
                else{
                    ArrayList<Integer> liste1 = (ArrayList<Integer>) liste.clone();
                    liste1.add(0,calculer(a,b,Calcul.MULTIPLICATION));
                    liste1.remove((Integer) a);
                    liste1.remove((Integer) b);
                    trouve = this.compterBon(liste1);
                }
                if(calculer(a,b,Calcul.SOUSTRACTION)!=-1){
                    if(calculer(a,b,Calcul.SOUSTRACTION)==solution){
                        trouve = true;
                    }
                    else{
                        ArrayList<Integer> liste1 = (ArrayList<Integer>) liste.clone();
                        liste1.add(0,calculer(a,b,Calcul.SOUSTRACTION));
                        liste1.remove((Integer) a);
                        liste1.remove((Integer) b);
                        trouve = this.compterBon(liste1);
                    }
                }
                if(calculer(a,b,Calcul.DIVISION)!=-1){
                    if(calculer(a,b,Calcul.DIVISION)==solution){
                        trouve = true;
                    }
                    else{
                        ArrayList<Integer> liste1 = (ArrayList<Integer>) liste.clone();
                        liste1.add(0,calculer(a,b,Calcul.DIVISION));
                        liste1.remove((Integer) a);
                        liste1.remove((Integer) b);
                        trouve = this.compterBon(liste1);
                    }
                }
            }
        }
        System.out.println(iterations);
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

    public int getIterations() {
        return iterations;
    }
}
