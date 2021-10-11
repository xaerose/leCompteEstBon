import java.util.ArrayList;
import java.util.Collections;

public class LeCompteEstBon {

    private ArrayList<Integer> listeNombresAutorisees;
    private static int iterations = 0;

    public LeCompteEstBon(){
        listeNombresAutorisees = new ArrayList<Integer>();
        Collections.addAll(listeNombresAutorisees,1,2,3,4,5,6,7,8,9,10,25,50,75,100);

    }

    public boolean verifierNombresDonnes(ArrayList<Integer> liste){
        return listeNombresAutorisees.containsAll(liste);
    }

    public ArrayList<int[]> combinerComplets(ArrayList<Integer> liste){
        ArrayList<int[]> combi = new ArrayList<>();
        for(int i=0;i<liste.size();i++){
            for(int j=0;j<liste.size();j++){
                if(!liste.get(i).equals(liste.get(j))){
                    combi.add(new int[]{liste.get(i),liste.get(j)});
                }
            }
        }
        return combi;
    }

    public boolean compterBon(ArrayList<Integer> liste,int result){
        iterations++;
        boolean trouve=false;
        if(liste.size()>1){
                ArrayList<int[]> combi = combinerComplets(liste);
                int i = 0;
                while((!trouve) && (i<combi.size())){
                    int[] combinaison = combi.get(i);
                    int a = combinaison[0];
                    int b = combinaison[1];
                    if((a+b)==result){
                        trouve = true;
                    }
                    else{
                        trouve = this.compterBon(creerListe(liste,a,b,(a+b)),result);
                    }
                    if(!trouve){
                        if((a*b)==result){
                            trouve = true;
                        }
                        else{
                            trouve = this.compterBon(creerListe(liste,a,b,(a*b)),result);
                        }
                    }
                    if(!trouve){
                        if((a-b)>0){
                            if((a-b)==result){
                                trouve = true;
                            }
                            else{
                                trouve = this.compterBon(creerListe(liste,a,b,(a-b)),result);
                            }
                        }
                    }
                    if(!trouve){
                        if((a%b)==0){
                            if((a/b)==result){
                                trouve = true;
                            }
                            else{
                                trouve = this.compterBon(creerListe(liste,a,b,(a*b)),result);
                            }
                        }
                    }
                    i++;
                }
        }
        return trouve;
    }

    public int getIterations() {
        return iterations;
    }

    public ArrayList<Integer> creerListe(ArrayList<Integer> liste,int nb1,int nb2,int res){
        ArrayList<Integer> newliste = (ArrayList<Integer>) liste.clone();
        newliste.remove((Integer) nb1);
        newliste.remove((Integer) nb2);
        newliste.add(0,res);
        return newliste;
    }
}
