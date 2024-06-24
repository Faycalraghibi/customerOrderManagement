import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;


class Commande {
    private int IDfournisseur;
    private int numero;
    private String dateCommande;
    private String dateLivraison;
    private String produits;   // Il s'agit d'un string aulieu d'une liste de produits pour faciliter la manipulation dans les fichier CSV.
    private double prixTotal;

    // Constructeur par défaut de la classe Commande
    public Commande() {
        this.numero = 0;
        this.dateCommande = "";
        this.dateLivraison = "";
        this.produits = "";
        this.prixTotal = 0.0;
    }
        // Constructeur de la classe Commande
    public Commande(int IDfournisseur, int numero, String dateCommande, String dateLivraison, String produits, double prixTotal) {
        this.IDfournisseur= IDfournisseur;
        this.numero = numero;
        this.dateCommande = dateCommande;
        this.dateLivraison = dateLivraison;
        this.produits = produits;
        this.prixTotal = prixTotal; 
    }
        // Méthode pour écrire un enregestriment de commande dans un fichier CSV. 
    public void writeToCSVFile(String fileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
            String line = this.IDfournisseur + "," + this.numero + "," + this.dateCommande + "," + this.dateLivraison + "," + "\"" + this.produits + "\"" + "," +
                    this.prixTotal;
            writer.write(line);
            writer.newLine();
        } catch (IOException e) {
            System.out.println("Une erreur est survenue lors de l'écriture dans le fichier " + fileName);
        }
    }
    

    
}