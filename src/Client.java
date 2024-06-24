import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

class Client {
    private String nom;
    private String prenom;
    private String numeroCIN;
    // Constructeur de la classe Client
    public Client(String nom, String prenom, String numeroCIN) {
        this.nom = nom;
        this.prenom = prenom;
        this.numeroCIN = numeroCIN;
    }
    //Methode pour écrire en CSV file une ligne client.
    public String toCSVLine() {
        return nom + "," + prenom + "," + numeroCIN;
    }
    // Méthode pour écrire les informations du client dans un fichier CSV
    public void writeToCSVFile(String fileName) {
        String clientData = toCSVLine();
        try (FileWriter fileWriter = new FileWriter(fileName, true);  //le writer est suceptible de lancer des exception IO par defaut donc pas besoin de lancer des exceptions.
             BufferedWriter writer = new BufferedWriter(fileWriter)) {   
            writer.write(clientData);
            writer.newLine();
            System.out.println("Client ajouté avec succès !");
        } catch (IOException e) {
            System.out.println("Une erreur est survenue lors de l'écriture dans le fichier " + fileName);
        }
    }
}