import java.util.ArrayList;
import java.util.Collections;

public class CompteEstBon {

    // ATTRIBUTS
    private ArrayList<Integer> listeNombresAutorisees;
    /**
     * nombre d'iteration realise pour trouver le resultat ou
     */
    private static int iterations = 0;
    /**
     * entier plus proche du resultat souhaite
     */
    private int plusproche = 0;
    /**
     * liste des operations pour un resultat au plus porhce de celui souhaite
     */
    private ArrayList<String> lopeplusproche;


    /**
     * constructeur du compte est bon
     * une liste des nombres autorises est initalisee et remplie
     */
    public CompteEstBon(){
        listeNombresAutorisees = new ArrayList<>();
        Collections.addAll(listeNombresAutorisees,1,2,3,4,5,6,7,8,9,10,25,50,75,100);

    }

    /**
     * @param liste la liste d entier pour effectuer les operations
     * @return un boolean prenant la valeur true si les nombres choisis respectent les convetions etablies
     */
    public boolean verifierNombresDonnes(ArrayList<Integer> liste){
        return listeNombresAutorisees.containsAll(liste);
    }


    /**
     * @param liste la liste d entier pour effectuer les operations
     * @return une nouvelle arraylist de tableaux permettant d etablir les combinaisons possibles
     */
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

    /**
     * methode permettant de lire la liste des operations effectuees
     * @param liste la liste a lire
     */
    public void lireListe(ArrayList<String> liste){
        System.out.println("Liste de calculs:");
        for (String s : liste) {
            System.out.println(s);
        }
    }


    /**
     * methode triant la liste dans l ordre decroissant pour optimiser le programme
     * @param liste la liste a trier
     */
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


    /**
     * @param res
     * @param lope la liste d operation a cloner
     * @param result
     */
    public void verifierPlusProche(int res,ArrayList<String> lope,int result){
        if(Math.abs(result-plusproche) > Math.abs(result-res)) {
            plusproche = res;
            lopeplusproche = (ArrayList<String>) lope.clone();
        }
    }


    /**
     * methode recursive visant a trouver un nombre compris entre 101 et 999 avec une liste de nombres choisis parmi ceux conseilles
     * @param liste la liste d entiers permettant de mener au resultat
     * @param result le resultat final suppose trouve a la fin du programme
     * @param lope la liste d operations effectuees pour mener au result
     * @return un boolean prenant la valeur true dans le cas ou le result a ete trouve apres un nombre n d iterations recursives
     */
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


    /**
     * getter du nb d iterations
     * @return le nombre d iterations effectuees+
     */
    public int getIterations() {
        return iterations;
    }


    /**
     * methode creant une liste a partir de la precedente, supprimant deux nombres pour inserer le res de l operation de ces derniers
     * @param liste la liste de nombres
     * @param nb1 opérateur 1
     * @param nb2 opérateur 2
     * @param res resultat de l operation entre nb1 et nb2
     * @return une nouvelle liste de taille n-1 (ou n est la taille de la precedente)
     */
    public ArrayList<Integer> creerListe(ArrayList<Integer> liste,int nb1,int nb2,int res){
        ArrayList<Integer> newliste = (ArrayList<Integer>) liste.clone();
        newliste.remove((Integer) nb1);
        newliste.remove((Integer) nb2);
        newliste.add(0,res);
        return newliste;
    }


    /**
     * @return le nb le plus proche
     */
    public int getPlusproche() {
        return plusproche;
    }

    /**
     * @return la liste d operations du nb le plus proche
     */
    public ArrayList<String> getLopeplusproche() {
        return lopeplusproche;
    }
//------------------------------------------------------------------------------------------------------------------------------------------


    /**
     * @param args arguments rentres en parametre pour run le programme
     */
    public static void main(String[] args) {

        Integer[] nombres = new Integer[6];
        for(int i=0;i<6;i++){
            nombres[i]=Integer.parseInt(args[i]);
        }
        int but = Integer.parseInt(args[6]);
        ArrayList<Integer> nombresDonnes = new ArrayList<>();
        ArrayList<String> lope = new ArrayList<>();
        Collections.addAll(nombresDonnes,nombres);

        CompteEstBon lceb = new CompteEstBon();
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
