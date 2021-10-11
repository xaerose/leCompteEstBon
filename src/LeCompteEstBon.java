import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;

public class LeCompteEstBon {

    private ArrayList<Integer> listeNombresAutorisees;
    private static int iterations = 0;
    private int plusproche = 0;
    private ArrayList<String> lopeplusproche;

    public LeCompteEstBon(){
        listeNombresAutorisees = new ArrayList<>();
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

    public void lireListe(ArrayList<String> liste){
        System.out.println("Liste de calculs:");
        for (String s : liste) {
            System.out.println(s);
        }
    }

    public void trierListe(ArrayList<Integer> liste){
        for (int i = 0; i < liste.size() - 1; i++) {
            int index = i;
            for (int j = i + 1; j < liste.size(); j++)
            {
                if (liste.get(j) >liste.get(index)){
                    index = j;
                }
            }
            int min = liste.get(index);
            liste.remove(index);
            liste.add(index,liste.get(i));
            liste.remove(i);
            liste.add(i,min);
        }
    }

    public void verifierPlusProche(int res,ArrayList<String> lope,int result){
        if(Math.abs(result-plusproche) > Math.abs(result-res)) {
            plusproche = res;
            lopeplusproche = (ArrayList<String>) lope.clone();
        }

    }

    public boolean compterBon(ArrayList<Integer> liste,int result,ArrayList<String> lope){
        iterations++;
        boolean trouve=false;
        if(liste.size()>1){
                ArrayList<int[]> combi = combinerComplets(liste);
                int i = 0;
                while((!trouve) && (i<combi.size())){
                    int[] combinaison = combi.get(i);
                    int a = combinaison[0];
                    int b = combinaison[1];
                    if(a!=1 && b!=1){
                        if((a*b)==result){
                            trouve = true;
                            lope.add(a+" * "+b+" = "+(a*b));
                            lireListe(lope);
                        }
                        else{
                            ArrayList<String> lope1 = (ArrayList<String>) lope.clone();
                            lope1.add(a+" * "+b+" = "+(a*b));
                            verifierPlusProche((a*b),lope1,result);
                            trouve = this.compterBon(creerListe(liste,a,b,(a*b)),result,lope1);
                        }
                    }
                    if(!trouve){
                        if((a+b)==result){
                            trouve = true;
                            lope.add(a+" + "+b+" = "+(a+b));
                            lireListe(lope);
                        }
                        else{
                            ArrayList<String> lope1 = (ArrayList<String>) lope.clone();
                            lope1.add(a+" + "+b+" = "+(a+b));
                            verifierPlusProche((a+b),lope1,result);
                            trouve = this.compterBon(creerListe(liste,a,b,(a+b)),result,lope1);
                        }
                    }
                    if(!trouve){
                        if((a-b)>0){
                            if((a-b)==result){
                                trouve = true;
                                lope.add(a+" - "+b+" = "+(a-b));
                                lireListe(lope);
                            }
                            else{
                                ArrayList<String> lope1 = (ArrayList<String>) lope.clone();
                                lope1.add(a+" - "+b+" = "+(a-b));
                                verifierPlusProche((a-b),lope1,result);
                                trouve = this.compterBon(creerListe(liste,a,b,(a-b)),result,lope1);
                            }
                        }
                    }
                    if(!trouve){
                        if(b!=1){
                            if((a%b)==0){
                                if((a/b)==result){
                                    trouve = true;
                                    lope.add(a+" / "+b+" = "+(a/b));
                                    lireListe(lope);
                                }
                                else{
                                    ArrayList<String> lope1 = (ArrayList<String>) lope.clone();
                                    lope1.add(a+" / "+b+" = "+(a/b));
                                    verifierPlusProche((a/b),lope1,result);
                                    trouve = this.compterBon(creerListe(liste,a,b,(a*b)),result,lope1);
                                }
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

    public int getPlusproche() {
        return plusproche;
    }

    public ArrayList<String> getLopeplusproche() {
        return lopeplusproche;
    }
//------------------------------------------------------------------------------------------------------------------------------------------

    public static void main(String[] args) {

        Integer[] nombres = new Integer[6];
        for(int i=0;i<6;i++){
            nombres[i]=Integer.parseInt(args[i]);
        }
        int but = Integer.parseInt(args[6]);
        ArrayList<Integer> nombresDonnes = new ArrayList<>();
        ArrayList<String> lope = new ArrayList<>();
        Collections.addAll(nombresDonnes,nombres);

        LeCompteEstBon lceb = new LeCompteEstBon();
        lceb.trierListe(nombresDonnes);
        if(!lceb.verifierNombresDonnes(nombresDonnes)){
            System.out.println("Les nombres donnees ne sont pas bons!");
            System.exit(1);
        }
        if(but<101 || but>999){
            System.out.println("L'objectif n'est pas  compris entre 101 et 999!");
            System.exit(4);
        }
        if(lceb.compterBon(nombresDonnes,but,lope)){
            System.out.println("Trouvé");
            System.out.println("Nombre d'itérations pour trouver");
            System.out.println(lceb.getIterations());
            System.exit(2);
        }else{
            System.out.println("Pas de solution exacte");
            System.out.println("Le plus proche:");
            System.out.println(lceb.getPlusproche());
            lceb.lireListe(lceb.getLopeplusproche());
            System.out.println("Nombre d'itérations pour trouver");
            System.out.println(lceb.getIterations());
            System.exit(3);
        }

    }
}
