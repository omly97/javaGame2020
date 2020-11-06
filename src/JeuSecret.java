import java.util.Scanner;

public class JeuSecret {

    public static void main(String[] args) {
        JeuSecret.app();
    }

    /**------------------------------------------------------------------ */
    //    AFFICHAGE CONSOLE
    /**------------------------------------------------------------------ */

    public static void app() {
        int jeu = JeuSecret.menu();
        if (jeu == 1) {
            JeuSecret.computerMind();
            JeuSecret.app();
        } else if (jeu == 2) {
            JeuSecret.userMind();
            JeuSecret.app();
        } else if (jeu == 0) {
            System.out.println("\n\t ---> Merci d'avoir jouer");
        } else {
            System.out.println("\n\t ---> Menu Indisponible");
            JeuSecret.app();
        }
    }

    public static int menu() {
        System.out.println("\n\t\t **** LE GRAND JEU SECRET ****");
        System.out.println("\n\t 1- JEU 1: C'est la machine qui devine");
        System.out.println("\n\t 2- JEU 2: C'est moi qui devine");
        System.out.println("\n\t 0- Quitter");
        return saisirEntier("Choissez un jeu : ");
    }

    public static int saisirEntier(String texte) {
        Scanner sc = new Scanner(System.in);
        System.out.print("\n\t -->  " + texte);
        return sc.nextInt();
    }

    /**------------------------------------------------------------------ */
    //    PREMIER JEU: LA MACHINE DEVINE
    /**------------------------------------------------------------------ */

    public static int dicho(int x, int y) {
        return (x+y) / 2;
    }

    public static void computerMind() {
        int a = saisirEntier("Donnez la valeur de a : ");
        int b = saisirEntier("Donnez la valeur de b : ");
        int essais = 0, secret = 0;
        int inf = a, sup = b;
        boolean isOK = false;
        do {
            secret = dicho(inf, sup);
            essais++;
            System.out.print("\n\t -->  Le nombre secret est " + secret );
            int reponse = saisirEntier("Est-ce le nombre secret ou pas ? [-1/0/1]  ");
            if (reponse == 1)
                inf = secret;
            else if (reponse == -1)
                sup = secret;
            else if (reponse == 0)
                isOK = true;
            else {
                System.out.print("\n\t -->  Le nombre secret est " + secret );
                reponse = saisirEntier("Est-ce le nombre secret ou pas ? [-1/0/1]  ");
            }
        } while(isOK == false);
        System.out.println("\n\t Le nombre secret est " + secret + ". Je l'ai devine avec " + essais + " coup(s).");
    }

    /**------------------------------------------------------------------ */
    //    SECOND JEU: LE USER DEVINE
    /**------------------------------------------------------------------ */

    public static int gaming(int secret) {
        int coups = 0;
        boolean rejouer = false;
        do {
            int nbr = saisirEntier("Devinez le nombre secret: ");
            coups++;
            if (nbr == secret) {
                System.out.println("\n\t --> FELICITATIONS VOUS AVEZ DEVINEZ LE NOMBRE SECRET");
                rejouer = false;
            } else if (nbr > secret) {
                System.out.println("\n\t --> Plus petit !!!");
                rejouer = true;
            } else {
                System.out.println("\n\t --> Plus grand !!!");
                rejouer = true;
            }
        } while(rejouer == true);
        return coups;
    }

    public static void userMind() {
        int[] resultats = new int[3];
        int joueur = 0;
        boolean rejouer = false;
        int secret =  (int) (Math.random() * 100);
        int inf = secret - (int) (Math.random() * 100);
        int sup = secret + (int) (Math.random() * 100);

        System.out.println("\n\t Le nombre secret est compris entre " + inf + " et " + sup);
        do {
            if (joueur < resultats.length) {
                resultats[joueur++] = gaming(secret);
                int reponse = saisirEntier("Voulez vous rejouer ? [1/0] : ");
                if (reponse == 0) rejouer = false;
                else rejouer = true;
            } else {
                System.out.println("\n\t --> Nombre de joueur maximal depasse");
                rejouer = false;
            }
        } while(rejouer == true);

        System.out.println("\n\t\t !!! RESULTATS DU JEU !!!");
        for (int i = 0; i < resultats.length; i++) {
            System.out.println("\n\t ----> Joueur " + (i+1) + " : " + resultats[i] + " coups");
        }
    }
}