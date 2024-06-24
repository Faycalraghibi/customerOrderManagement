import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class UtilCSV {  
        // Méthode pour écrire des lignes dans un fichier
     static void writeLinesToFile(String fileName, List<String> lines) {
        try (FileWriter fileWriter = new FileWriter(fileName);
             BufferedWriter writer = new BufferedWriter(fileWriter)) {
            for (String line : lines) {
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Une erreur est survenue lors de l'écriture dans le fichier " + fileName);
        }
    } 
         // Méthode pour lire les lignes d'un fichier
    static List<String> readFromFile(String fileName) {
        List<String> lines = new ArrayList<>(); //utilisation de liste pour faciliter la manipulation des informations qu'on lit à partir du fichier CSV.
        try (Scanner fileScanner = new Scanner(new File(fileName))) {
            while (fileScanner.hasNextLine()) { 
                lines.add(fileScanner.nextLine());  //ajouter toutes les lignes.
            }
        } catch (FileNotFoundException e) {
            System.out.println("Le fichier " + fileName + " n'existe pas.");
        }
        return lines;//retourne une liste de chaine contenant des enregistrements( peut être des enregistrement produits,fournisseurS....)
    }



}