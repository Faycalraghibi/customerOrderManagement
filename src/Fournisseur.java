import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

class Fournisseur {
    private int idFournisseur;
    private String nom;
    private String prenom;
    private String numeroCIN;
    private String raisonSociale;
    private String domaineActivite;
    private boolean estSociete;

            // Constructeur pour un fournisseur personne physique
    public Fournisseur(int idFournisseur, String nom, String prenom, String numeroCIN) {
        this(idFournisseur,nom,prenom,numeroCIN,"","",false);
        
    }
            // Constructeur pour un fournisseur société
    public Fournisseur(int idFournisseur, String raisonSociale, String domaineActivite) {
        this(idFournisseur,"","","",raisonSociale,domaineActivite,true);
    }

        // Constructeur général de la classe Fournisseur
    public Fournisseur(int idFournisseur, String nom, String prenom, String numeroCIN,  String raisonSociale, String domaineActivite,boolean estSociete) {
        this.idFournisseur = idFournisseur;
        this.nom = nom;
        this.prenom = prenom;
        this.numeroCIN = numeroCIN;
        this.raisonSociale = raisonSociale;
        this.domaineActivite = domaineActivite;
        this.estSociete = estSociete;
    }
        // Méthode redéfinition de toString pour  obtenir une représentation textuelle d'un fournisseur(elle va être utilisé pour la consultation de la liste des fournisseurs)
    public String toString() {
        String s;
        if (estSociete) {
            s= "ID: " + idFournisseur +", Raison sociale: " + raisonSociale
                    + ", Domaine d'activité: " + domaineActivite + ", Est une société";
        } else {
            s= "ID: " + idFournisseur + ", Nom: " + nom + ", Prénom: " + prenom
                    + ", Numéro CIN: " + numeroCIN + ", N'est pas une société";
        }
        return s;
        }
       
    // Méthode pour écrire 
    public void writeToCSVFile(String fileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {    //le writer lance des exceptions de type I/O. 
            String line = this.idFournisseur + "," + this.nom + "," + this.prenom + "," + this.numeroCIN + "," +
                    this.raisonSociale + "," + this.domaineActivite + "," + this.estSociete;
            writer.write(line);
            writer.newLine();
        } catch (IOException e) {
            System.out.println("Une erreur est survenue lors de l'écriture dans le fichier " + fileName);
        }
    }
}