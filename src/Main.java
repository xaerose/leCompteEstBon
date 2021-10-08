public class Main {

    public static void main(String[] args) {

        Integer[] nombres = new Integer[6];
        for(int i=0;i<6;i++){
            nombres[i]=Integer.parseInt(args[i]);
        }
        int but = Integer.parseInt(args[6]);

        LeCompteEstBon lceb = new LeCompteEstBon(nombres,but);

        if(!lceb.verifierNombresDonnes()){
            System.out.println("Les nombres donnees ne sont pas bons!");
            System.exit(1);
        }
    }
}
