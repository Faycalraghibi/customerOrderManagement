
//Ces classes d'exceptions vont être utilisés avec d'autre classes imported.


// Classe d'exception pour indiquer qu'un produit est introuvable aprés une recherche à partir de son libellé.
class ProduitIntrouvableException extends Exception {
    public ProduitIntrouvableException(String libelle) {
        super("Produit introuvable pour le libellé : " + libelle);
    }
}
// Classe d'exception pour indiquer qu'un produit est introuvable (aprés une recherche à partir de son ID)
class FournisseurIntrouvableException extends Exception {
    public FournisseurIntrouvableException(int ID) {
        super("L'ID " + ID + " n'est associé à aucun fournisseur. ");
    }
}


// Classe d'exception pour indiquer qu'une commande est introuvable à partir de son numéro
class CommandeIntrouvableException extends Exception {
    public CommandeIntrouvableException(int num ) {
        super("Le numéro " + num + " n'est associé à aucune commande. ") ;
    }
} 

// Classe d'exception pour indiquer qu'un produit est introuvable (CIN)
class ClientIntrouvableException extends Exception {
    public ClientIntrouvableException(String CIN ) {
        super("Le CIN " + CIN + " n'est associé à aucun Client. ") ;
    }
} 


// Classe d'exception personnalisée pour indiquer qu'un champ est vide aprés l'entrée de l'utilisateur.
class Vide extends Exception {
    public Vide(String VarManquante) {
        super("Le champs " + VarManquante + " est vide.");
    }
}