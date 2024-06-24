import javax.swing.*;
import java.awt.*;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Menu Principal");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 600); // Taille de la fenêtre
        frame.setLocationRelativeTo(null); // Centrer la fenêtre sur l'écran

        JPanel panel = new JPanel(new GridLayout(0, 1, 10, 5)); // 1 colonne, espacement de5 pixels

        // Création des boutons pour chaque option du menu avec une taille préférée
        JButton[] buttons = {
                createButton("Ajouter un client", e -> Utilitaire.ajouterClient()),
                createButton("Supprimer un client", e -> Utilitaire.supprimerClient()),
                createButton("Ajouter un fournisseur", e -> Utilitaire.ajouterFournisseur()),
                createButton("Supprimer un fournisseur", e -> Utilitaire.supprimerFournisseur()),
                createButton("Ajouter un produit", e -> Utilitaire.ajouterProduit()),
                createButton("Supprimer un produit", e -> Utilitaire.supprimerProduit()),
                createButton("Enregistrer une commande", e -> Utilitaire.enregistrerCommande()),
                createButton("Consulter la liste des fournisseurs", e -> Utilitaire.consulterFournisseurs()),
                createButton("Lier une commande à un fournisseur", e -> Utilitaire.lierCommandeFournisseur()),
                createButton("Consulter le catalogue des produits", e -> Utilitaire.consulterCatalogueProduits()),
                createButton("Consulter l'ID d'un fournisseur", e -> Utilitaire.consulterIDFournisseur()),
                createButton("Quitter", e -> System.exit(0))
        };

        // Ajuster la taille préférée des boutons pour occuper plus d'espace
        for (JButton button : buttons) {
            button.setPreferredSize(new Dimension(500, 60)); // Taille personnalisée pour chaque bouton
            panel.add(button);
        }

        frame.add(panel);
        frame.setVisible(true);
    }

    // Méthode utilitaire pour créer un bouton avec un ActionListener
    private static JButton createButton(String label, ActionListener listener) {
        JButton button = new JButton(label);
        button.setHorizontalAlignment(SwingConstants.CENTER);
        button.addActionListener(listener);
        return button;
    }
}
