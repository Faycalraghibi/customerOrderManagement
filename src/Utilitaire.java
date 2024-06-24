import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;

// Classe utilitaire fournissant des méthodes pour l'interaction avec l'utilisateur et la gestion des fichiers CSV

public class Utilitaire {
    static Scanner scanner = new Scanner(System.in);
    private static final String DATA_ABSOLUTE_PATH = "\\data\\";
    private static final String CLIENTS_FILE = DATA_ABSOLUTE_PATH + "clients.csv";
    private static final String FOURNISSEURS_FILE = DATA_ABSOLUTE_PATH + "fournisseurs.csv";
    private static final String PRODUITS_FILE = DATA_ABSOLUTE_PATH + "produits.csv";
    private static final String COMMANDES_FILE = DATA_ABSOLUTE_PATH + "commandes.csv";


     
    

            // Méthode pour ajouter un client
            static void ajouterClient() {
                try {
                    // Boîte de dialogue pour saisir le nom du client
                    String nom = JOptionPane.showInputDialog(null, "Nom:", "Ajout d'un client", JOptionPane.QUESTION_MESSAGE);
                    if (nom == null || nom.isEmpty()) {
                        throw new Vide("Nom");
                    }
            
                    // Boîte de dialogue pour saisir le prénom du client
                    String prenom = JOptionPane.showInputDialog(null, "Prénom:", "Ajout d'un client", JOptionPane.QUESTION_MESSAGE);
                    if (prenom == null || prenom.isEmpty()) {
                        throw new Vide("Prénom");
                    }
            
                    // Boîte de dialogue pour saisir le numéro CIN du client
                    String numeroCIN = JOptionPane.showInputDialog(null, "Numéro CIN:", "Ajout d'un client", JOptionPane.QUESTION_MESSAGE);
                    if (numeroCIN == null || numeroCIN.isEmpty()) {
                        throw new Vide("Numéro CIN");
                    }
            
                    // Création d'un objet Client et écriture dans le fichier CSV
                    Client client = new Client(nom, prenom, numeroCIN);
                    client.writeToCSVFile(CLIENTS_FILE);
            
                    JOptionPane.showMessageDialog(null, "Client ajouté avec succès !", "Succès", JOptionPane.INFORMATION_MESSAGE);
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
                }
            }
        // Méthode pour supprimer un client
        static void supprimerClient() {
            try {
                // Boîte de dialogue pour saisir le numéro CIN du client à supprimer
                String cinToDelete = JOptionPane.showInputDialog(null, "Numéro CIN du client à supprimer:", "Suppression d'un client", JOptionPane.QUESTION_MESSAGE);
                if (cinToDelete == null || cinToDelete.isEmpty()) {
                    throw new Vide("Numéro CIN");
                }
        
                List<String> lines = UtilCSV.readFromFile(CLIENTS_FILE);
                List<String> newLines = new ArrayList<>();
        
                // Parcours des lignes pour trouver et exclure le client à supprimer
                for (String line : lines) {
                    String[] attributs = line.split(",");
                    if (!attributs[2].equals(cinToDelete)) {
                        newLines.add(line);
                    }
                }
        
                // Vérification si le client a été trouvé et supprimé
                if (lines.size() == newLines.size()) {
                    throw new ClientIntrouvableException(cinToDelete);
                }
        
                UtilCSV.writeLinesToFile(CLIENTS_FILE, newLines);
                JOptionPane.showMessageDialog(null, "Client supprimé avec succès !", "Succès", JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        }
        

   //Cette méthode est utilisé pour trouver le dernier ID des fournisseurs (incrémenté de 1 à ...) elle va être utile pour ajouter un fournisseur dans la méthode suivante
     private static int dernierIdFournisseur() {
         try {
             List<String> fournisseurLines = UtilCSV.readFromFile(FOURNISSEURS_FILE);
             if (!fournisseurLines.isEmpty()) {
                 String lastLine = fournisseurLines.get(fournisseurLines.size() - 1);
                 String[] Attributs = lastLine.split(",");
                 return Integer.parseInt(Attributs[0]); //parseInt pour convertir le String représentant le ID à un entier.
             }
             return 0;
         }catch (Exception e){
             System.out.println(e.getMessage());
         }
         return 0;
     }

                // Lecture du fichier clients.csv et suppression du client avec le numéro CIN spécifié
                static void ajouterFournisseur() {
                    try {
                        // Demander à l'utilisateur s'il s'agit d'une société
                        int choixSociete = JOptionPane.showOptionDialog(null,
                                "Est-ce une société ?", "Ajout d'un fournisseur",
                                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,
                                null, new String[]{"Oui", "Non"}, "Oui");
            
                        if (choixSociete == JOptionPane.CLOSED_OPTION) {
                            return; // Si l'utilisateur annule, sortir de la méthode
                        }
                        boolean isSociete = (choixSociete == JOptionPane.YES_OPTION);
            
                        int idFournisseur = dernierIdFournisseur() + 1; // Génération de l'ID du fournisseur
            
                        if (isSociete) {
                            // Saisie des informations pour une société
                            String raisonSociale = JOptionPane.showInputDialog(null,
                                    "Raison sociale:", "Ajout d'un fournisseur - Société",
                                    JOptionPane.QUESTION_MESSAGE);
                            if (raisonSociale == null || raisonSociale.isEmpty()) {
                                throw new Vide("raisonSociale");
                            }
                            String domaineActivite = JOptionPane.showInputDialog(null,
                                    "Domaine d'activité:", "Ajout d'un fournisseur - Société",
                                    JOptionPane.QUESTION_MESSAGE);
                            if (domaineActivite == null || domaineActivite.isEmpty()) {
                                throw new Vide("domaineActivite");
                            }
                            // Création d'un objet Fournisseur et écriture dans le fichier CSV
                            Fournisseur fournisseur = new Fournisseur(idFournisseur, raisonSociale, domaineActivite);
                            fournisseur.writeToCSVFile(FOURNISSEURS_FILE);
            
                        } else {
                            // Saisie des informations pour une personne
                            String nom = JOptionPane.showInputDialog(null,
                                    "Nom:", "Ajout d'un fournisseur - Personne",
                                    JOptionPane.QUESTION_MESSAGE);
                            if (nom == null || nom.isEmpty()) {
                                throw new Vide("nom");
                            }
                            String prenom = JOptionPane.showInputDialog(null,
                                    "Prénom:", "Ajout d'un fournisseur - Personne",
                                    JOptionPane.QUESTION_MESSAGE);
                            if (prenom == null || prenom.isEmpty()) {
                                throw new Vide("prenom");
                            }
                            String numeroCIN = JOptionPane.showInputDialog(null,
                                    "Numéro CIN:", "Ajout d'un fournisseur - Personne",
                                    JOptionPane.QUESTION_MESSAGE);
                            if (numeroCIN == null || numeroCIN.isEmpty()) {
                                throw new Vide("numeroCIN");
                            }
                            // Création d'un objet Fournisseur et écriture dans le fichier CSV
                            Fournisseur fournisseur = new Fournisseur(idFournisseur, nom, prenom, numeroCIN);
                            fournisseur.writeToCSVFile(FOURNISSEURS_FILE);
                        }
            
                        JOptionPane.showMessageDialog(null,
                                "Fournisseur ajouté avec succès !", "Succès",
                                JOptionPane.INFORMATION_MESSAGE);
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(null,
                                e.getMessage(), "Erreur",
                                JOptionPane.ERROR_MESSAGE);
                    }
                }
                
            // Méthode pour supprimer un fournisseur
            static void supprimerFournisseur() {
                try {
                    // Demander à l'utilisateur l'ID du fournisseur à supprimer
                    String idToDeleteStr = JOptionPane.showInputDialog(null, "ID du fournisseur à supprimer:", "Suppression d'un fournisseur", JOptionPane.QUESTION_MESSAGE);
                    if (idToDeleteStr == null) {
                        return; // Si l'utilisateur annule, sortir de la méthode
                    }
                    int idToDelete = Integer.parseInt(idToDeleteStr);
            
                    // Lecture du fichier fournisseurs.csv et suppression du fournisseur avec l'ID spécifié
                    List<String> lines = UtilCSV.readFromFile(FOURNISSEURS_FILE);
                    List<String> newLines = new ArrayList<>(); // Liste vide où on va mettre toutes les lignes sauf le fournisseur non désiré
            
                    // Ajouter la première ligne (en-tête) au nouveau fichier
                    newLines.add(lines.get(0));
            
                    // Parcourir les lignes du fichier CSV pour trouver et supprimer le fournisseur avec l'ID spécifié
                    boolean found = false;
                    for (int i = 1; i < lines.size(); i++) {
                        String[] attributs = lines.get(i).split(",");
                        int currentId = Integer.parseInt(attributs[0]);
                        if (currentId == idToDelete) {
                            found = true;
                        } else {
                            newLines.add(lines.get(i));
                        }
                    }
            
                    // Vérification si le fournisseur a été trouvé et supprimé
                    if (!found) {
                        throw new FournisseurIntrouvableException(idToDelete);
                    }
            
                    // Écrire les lignes mises à jour dans le fichier CSV
                    UtilCSV.writeLinesToFile(FOURNISSEURS_FILE, newLines);
                    JOptionPane.showMessageDialog(null, "Fournisseur supprimé avec succès !", "Succès", JOptionPane.INFORMATION_MESSAGE);
            
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "L'ID doit être un entier.", "Erreur", JOptionPane.ERROR_MESSAGE);
                } catch (FournisseurIntrouvableException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
                }
            }
            


    // Méthode pour ajouter un produit
    static void ajouterProduit() {
        try {
            // Saisie du libellé du produit
            String libelle = JOptionPane.showInputDialog(null, "Libellé du produit:", "Ajout d'un produit", JOptionPane.QUESTION_MESSAGE);
            if (libelle == null || libelle.isEmpty()) {
                throw new Vide("libelle");
            }
    
            // Saisie du prix TTC du produit avec validation > 0
            double prixTTC = 0;
            boolean prixValide = false;
            while (!prixValide) {
                String prixTTCStr = JOptionPane.showInputDialog(null, "Prix TTC du produit:", "Ajout d'un produit", JOptionPane.QUESTION_MESSAGE);
                if (prixTTCStr == null || prixTTCStr.isEmpty()) {
                    throw new Vide("prixTTC");
                }
                try {
                    prixTTC = Double.parseDouble(prixTTCStr);
                    if (prixTTC <= 0) {
                        throw new IllegalArgumentException("Le prix doit être supérieur à zéro.");
                    }
                    prixValide = true;
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Le prix doit être un nombre réel.", "Erreur", JOptionPane.ERROR_MESSAGE);
                } catch (IllegalArgumentException e) {
                    JOptionPane.showMessageDialog(null, e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
                }
            }
    
            // Saisie de l'état de produit fini
            String estProduitFiniStr = JOptionPane.showInputDialog(null, "Le produit est-il fini par l'entreprise ? (true/false):", "Ajout d'un produit", JOptionPane.QUESTION_MESSAGE);
            if (estProduitFiniStr == null || estProduitFiniStr.isEmpty()) {
                throw new Vide("estProduitFini");
            }
            boolean estProduitFini = Boolean.parseBoolean(estProduitFiniStr);
    
            // Création d'un objet Produit et écriture dans le fichier CSV
            Produit produit = new Produit(libelle, prixTTC, estProduitFini);
            BufferedWriter writer = new BufferedWriter(new FileWriter(PRODUITS_FILE, true));
            writer.write(produit.toCSVLine());
            writer.newLine();
            writer.close();
    
            JOptionPane.showMessageDialog(null, "Produit ajouté avec succès !", "Succès", JOptionPane.INFORMATION_MESSAGE);
    
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Une erreur est survenue lors de l'écriture dans le fichier " + PRODUITS_FILE, "Erreur", JOptionPane.ERROR_MESSAGE);
        } catch (Vide e) {
            JOptionPane.showMessageDialog(null, "Le champ " + e.getMessage() + " ne peut pas être vide.", "Erreur", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    
        // Méthode pour supprimer un produit
      
    static void supprimerProduit() {
    try {
        System.out.println("Suppression d'un produit");
        String libelleToDelete = JOptionPane.showInputDialog(null, "Libellé du produit à supprimer:", "Suppression d'un produit", JOptionPane.QUESTION_MESSAGE);
        if (libelleToDelete == null || libelleToDelete.isEmpty()) {
            throw new Vide("libelleToDelete");
        }

        List<String> lines = UtilCSV.readFromFile(PRODUITS_FILE);
        List<String> newLines = new ArrayList<>();

        boolean produitTrouve = false;

        for (String line : lines) {
            String[] attributs = line.split(",");
            if (!attributs[0].equals(libelleToDelete)) {
                newLines.add(line);
            } else {
                produitTrouve = true;
            }
        }

        if (!produitTrouve) {
            throw new ProduitIntrouvableException(libelleToDelete);
        } else {
            UtilCSV.writeLinesToFile(PRODUITS_FILE, newLines);
            JOptionPane.showMessageDialog(null, "Produit supprimé avec succès !", "Succès", JOptionPane.INFORMATION_MESSAGE);
        }

    } catch (Vide e) {
        JOptionPane.showMessageDialog(null, "Le champ " + e.getMessage() + " ne peut pas être vide.", "Erreur", JOptionPane.ERROR_MESSAGE);
    } catch (ProduitIntrouvableException e) {
        JOptionPane.showMessageDialog(null, e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
    }
}

        
        
   
    //Fonction utilisé pour consulter un produit par le libelle, principalement utilisé plus tard pour trouver le prix à partir d'un String libelle.
    private static Produit consulterProduitByLibelle(String libelle) {
        List<String> lines = UtilCSV.readFromFile(PRODUITS_FILE); //on crée une liste de chaines ayent les enregistrements des produits.
        for (String line : lines) {
            String[] Attributs = line.split(",");
            if (Attributs.length >= 3 && Attributs[0].equalsIgnoreCase(libelle)) {
                //à travers le libelle l'attribut numéro 0 on cherche les autres attributs avec parseDouble, parseBoolean qui modifie de String vers Double ou Boolean....
                double prixTTC = Double.parseDouble(Attributs[1]);
                boolean estProduitFini = Boolean.parseBoolean(Attributs[2]);
                return new Produit(libelle, prixTTC, estProduitFini);
            }
        }
        return null;
    }
        // Méthode pour calculer le prix total d'une liste de produits utilisé pour enregistrer une commande avec plusieurs produits.
    private static double calculerPrixTotal(List<String> produitLibelles) throws ProduitIntrouvableException {
        double totalPrice = 0.0;
        for (String libelle : produitLibelles) {
            Produit produit = consulterProduitByLibelle(libelle);
            if (produit != null) {
                totalPrice += produit.getPrixTTC(); //simple recherche des produits qu'on veut  et on trouve leurs prix et on les somme.
            } else {
                throw new ProduitIntrouvableException(libelle);
            }
        }
        return totalPrice;
    }

        // Méthode pour enregistrer une commande

        static void enregistrerCommande() {
            try {
                System.out.println("Enregistrement d'une commande");
        
                // Demande à l'utilisateur d'entrer l'ID du fournisseur avec une boîte de dialogue
                String idFournisseurInput = JOptionPane.showInputDialog(null, "ID du Fournisseur de la commande :", "Enregistrement d'une commande", JOptionPane.QUESTION_MESSAGE);
                if (idFournisseurInput == null || idFournisseurInput.isEmpty()) {
                    throw new Vide("ID du Fournisseur");
                }
                int IDfournisseur = Integer.parseInt(idFournisseurInput);
        
                // Vérification de l'existence du fournisseur dans le fichier CSV
                List<String> lines = UtilCSV.readFromFile(FOURNISSEURS_FILE);
                boolean fournisseurTrouve = false;
        
                for (int i = 1; i < lines.size(); i++) {
                    String[] parts = lines.get(i).split(",");
                    if (Integer.parseInt(parts[0]) == IDfournisseur) {
                        fournisseurTrouve = true;
                        break;
                    }
                }
        
                if (!fournisseurTrouve) {
                    throw new FournisseurIntrouvableException(IDfournisseur);
                }
        
                // Saisie des autres informations de la commande avec des boîtes de dialogue
                String numeroCommandeInput = JOptionPane.showInputDialog(null, "Numéro de commande :", "Enregistrement d'une commande", JOptionPane.QUESTION_MESSAGE);
                if (numeroCommandeInput == null || numeroCommandeInput.isEmpty()) {
                    throw new Vide("Numéro de commande");
                }
                int numeroCommande = Integer.parseInt(numeroCommandeInput);
        
                String dateCommande = JOptionPane.showInputDialog(null, "Date de commande (format dd-mm-yyyy) :", "Enregistrement d'une commande", JOptionPane.QUESTION_MESSAGE);
                if (dateCommande == null || dateCommande.isEmpty()) {
                    throw new Vide("Date de commande");
                }
        
                String dateLivraison = JOptionPane.showInputDialog(null, "Date de livraison (format dd-mm-yyyy) :", "Enregistrement d'une commande", JOptionPane.QUESTION_MESSAGE);
                if (dateLivraison == null || dateLivraison.isEmpty()) {
                    throw new Vide("Date de livraison");
                }
        
                String produitsCommandesInput = JOptionPane.showInputDialog(null, "Liste des produits commandés (séparés par des virgules) :", "Enregistrement d'une commande", JOptionPane.QUESTION_MESSAGE);
                if (produitsCommandesInput == null || produitsCommandesInput.isEmpty()) {
                    throw new Vide("Liste des produits commandés");
                }
                String[] produitsCommandesArray = produitsCommandesInput.split(",");
                List<String> produits = new ArrayList<>();
                for (String produitLibelle : produitsCommandesArray) {
                    produits.add(produitLibelle.trim());
                }
        
                // Calcul du prix total de la commande avec gestion des exceptions de produit introuvable
                double prixTotalCommande = calculerPrixTotal(produits);
        
                // Enregistrement de la commande dans le fichier CSV
                Commande commande = new Commande(IDfournisseur, numeroCommande, dateCommande, dateLivraison, produitsCommandesInput, prixTotalCommande);
                commande.writeToCSVFile(COMMANDES_FILE);
        
                JOptionPane.showMessageDialog(null, "Commande enregistrée avec succès !", "Succès", JOptionPane.INFORMATION_MESSAGE);
        
            } catch (Vide e) {
                JOptionPane.showMessageDialog(null, "Le champ " + e.getMessage() + " ne peut pas être vide.", "Erreur", JOptionPane.ERROR_MESSAGE);
            } catch (FournisseurIntrouvableException | ProduitIntrouvableException e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        }
        

   //Methode qui affiche touts les fournisseurs et les informations liés à eux.

   
   static void consulterFournisseurs() {
       List<String> lines = UtilCSV.readFromFile(FOURNISSEURS_FILE); // Lecture des lignes du fichier des fournisseurs
       
       // Création des colonnes pour la JTable
       String[] columnNames = {"ID", "Nom", "Prénom", "Numéro CIN", "Raison Sociale", "Domaine d'activité", "Est Société"};
       DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
   
       boolean skipHeader = true;
       for (String line : lines) {
           if (skipHeader) {
               skipHeader = false;
               continue;
           }
           
           // Récupération des attributs du fournisseur
           String[] attributs = line.split(",");
           int idFournisseur = Integer.parseInt(attributs[0]);
           String nom = attributs[1];
           String prenom = attributs[2];
           String numeroCIN = attributs[3];
           String raisonSociale = attributs[4];
           String domaineActivite = attributs[5];
           boolean estSociete = Boolean.parseBoolean(attributs[6]);
   
           // Ajout d'une ligne dans la JTable
           Object[] rowData = {idFournisseur, nom, prenom, numeroCIN, raisonSociale, domaineActivite, estSociete};
           tableModel.addRow(rowData);
       }
       
       // Création de la JTable avec le modèle de données
       JTable table = new JTable(tableModel);
       table.setAutoCreateRowSorter(true); // Optionnel : permet de trier les lignes par clic sur l'en-tête de colonne
       
       // Ajout de la JTable à un JScrollPane pour permettre le défilement
       JScrollPane scrollPane = new JScrollPane(table);
       
       // Création et configuration de la JFrame
       JFrame frame = new JFrame("Liste des Fournisseurs");
       frame.getContentPane().add(scrollPane, BorderLayout.CENTER);
       frame.setSize(800, 400);
       frame.setLocationRelativeTo(null); // Centrage de la fenêtre
       frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Ferme seulement la fenêtre sans quitter l'application
       frame.setVisible(true);
   }
   

 
    
    //Cette méthode permet de lier une commande à un fournisseur en modifiant le fichier de commandes
    static void lierCommandeFournisseur() {
        try {
            JFrame frame = new JFrame("Lier une commande à un fournisseur");
            
            // Numéro de commande
            String numeroCommandeInput = JOptionPane.showInputDialog(frame, "Numéro de commande à lier :");
            if (numeroCommandeInput == null) return; // Si l'utilisateur clique sur Annuler
            
            int numeroCommande = Integer.parseInt(numeroCommandeInput.trim());
            
            // Nouvel identifiant du fournisseur
            String nouveauFournisseurIdInput = JOptionPane.showInputDialog(frame, "Entrez le nouvel identifiant du fournisseur :");
            if (nouveauFournisseurIdInput == null) return; // Si l'utilisateur clique sur Annuler
            
            int nouveauFournisseurId = Integer.parseInt(nouveauFournisseurIdInput.trim());
            
            List<String> lines = UtilCSV.readFromFile(COMMANDES_FILE);
            boolean commandeTrouvee = false;
    
            for (int i = 1; i < lines.size(); i++) { // Start from index 1 to skip the header
                String[] parts = lines.get(i).split(",");
                if (Integer.parseInt(parts[1].trim()) == numeroCommande) {
                    parts[0] = String.valueOf(nouveauFournisseurId);
                    lines.set(i, String.join(",", parts));
                    commandeTrouvee = true;
                    break;
                }
            }
            
            if (commandeTrouvee) {
                UtilCSV.writeLinesToFile(COMMANDES_FILE, lines);
                JOptionPane.showMessageDialog(frame, "Commande liée avec succès à un nouveau fournisseur.", "Succès", JOptionPane.INFORMATION_MESSAGE);
            } else {
                throw new CommandeIntrouvableException(numeroCommande);
            }
            
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Veuillez entrer un numéro valide.", "Erreur de Format", JOptionPane.ERROR_MESSAGE);
        } catch (CommandeIntrouvableException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Commande Introuvable", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erreur : " + e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }

        //Cette méthode permet de consulter le catalogue des produits enregistrés.
        static void consulterCatalogueProduits() {
            JFrame frame = new JFrame("Catalogue des produits");
        
            List<String> lines = UtilCSV.readFromFile(PRODUITS_FILE);
        
            String[] columnNames = {"Libellé", "Prix TTC", "Est Fini"};
            DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
        
            for (int i = 1; i < lines.size(); i++) { // Commence à l'indice 1 pour sauter la première ligne
        String line = lines.get(i);
        String[] attributs = line.split(",");
        String libelle = attributs[0];
        String prixTTC = attributs[1];
        String estFini = attributs[2];

        Object[] data = {libelle, prixTTC, estFini};
        tableModel.addRow(data);
    }
        
            JTable table = new JTable(tableModel);
            JScrollPane scrollPane = new JScrollPane(table);
        
            frame.add(scrollPane);
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.pack();
            frame.setVisible(true);
        }
    static void consulterIDFournisseur() {
        JFrame frame = new JFrame("Consulter un fournisseur par critères");
        
        // Choix du type de fournisseur (société ou personne)
        int choixTypeFournisseur = JOptionPane.showOptionDialog(frame,
                "Est-ce une société ?",
                "Type de fournisseur",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                new String[]{"Oui", "Non"},
                "Oui");
    
        boolean isSociete = (choixTypeFournisseur == JOptionPane.YES_OPTION);
        String consulterRaisonSociale = "";
        String consulterNom = "";
        String consulterPrenom = "";
    
        if (isSociete) {
            consulterRaisonSociale = JOptionPane.showInputDialog(frame, "Raison sociale de la société :");
        } else {
            consulterNom = JOptionPane.showInputDialog(frame, "Nom de la personne :");
            consulterPrenom = JOptionPane.showInputDialog(frame, "Prénom de la personne :");
        }
    
        List<String> lines = UtilCSV.readFromFile(FOURNISSEURS_FILE);
        boolean skipHeader = true;
        boolean fournisseurTrouve = false;

        for (String line : lines) {
            if (skipHeader) {
                skipHeader = false;
                continue;
            }
            String[] attributs = line.split(",");
            int idFournisseur = Integer.parseInt(attributs[0]);
            String nom = attributs[1];
            String prenom = attributs[2];
            String raisonSociale = attributs[4];
            boolean estSocieteFournisseur = Boolean.parseBoolean(attributs[6]);
    
            if (estSocieteFournisseur && isSociete && raisonSociale.equalsIgnoreCase(consulterRaisonSociale)) {
                JOptionPane.showMessageDialog(frame, "Société - Raison sociale: " + raisonSociale + ", ID: " + idFournisseur,
                        "Résultat de la recherche", JOptionPane.INFORMATION_MESSAGE);
                        fournisseurTrouve = true;
            } else if (!estSocieteFournisseur && !isSociete && nom.equalsIgnoreCase(consulterNom) && prenom.equalsIgnoreCase(consulterPrenom)) {
                JOptionPane.showMessageDialog(frame, "Personne - Nom: " + nom + ", Prénom: " + prenom + ", ID: " + idFournisseur,
                        "Résultat de la recherche", JOptionPane.INFORMATION_MESSAGE);
                        fournisseurTrouve = true;
            } 
        }

        if (fournisseurTrouve==false) {
            JOptionPane.showMessageDialog(frame, "Aucun fournisseur trouvé avec les critères spécifiés.",
            "Résultat de la recherche", JOptionPane.INFORMATION_MESSAGE);
        }
    }



}


