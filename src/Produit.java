class Produit {
    private String libelle;
    private double prixTTC;
    private boolean estProduitFini;

        // Constructeur de la classe Produit
    public Produit(String libelle, double prixTTC, boolean estProduitFini) {
        this.libelle = libelle;
        this.prixTTC = prixTTC;
        this.estProduitFini = estProduitFini;
    }

        // Méthode pour obtenir le prix TTC du produit
    public double getPrixTTC() {
        return prixTTC;
    }
            // Méthode pour obtenir une représentation textuelle du produit au format CSV pour effectuer des enregistrement avec la méthode ajouterProduit().
    public String toCSVLine() {
        return libelle + "," + prixTTC + "," + estProduitFini;
    }
}
