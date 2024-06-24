---

# Customer Order Management

## Overview

Provide a brief overview of the project, its purpose, and its features.

## Table of Contents

1. [Installation](#installation)
2. [Usage](#usage)
3. [File Structure](#file-structure)
4. [Dependencies](#dependencies)
5. [Contributing](#contributing)
6. [License](#license)

## Installation

Describe how to install and set up the project. Include any prerequisites or dependencies needed.

### Prerequisites

- List any software or tools that need to be installed (e.g., Java JDK, IDE).
- Specify any setup or configuration required (e.g., environment variables).

### Steps

1. Clone the repository:
   ```bash
   git clone https://github.com/Faycalraghibi/project.git
   ```
   
2. Navigate to the project directory:
   ```bash
   cd project
   ```
   
3. Compile the source files (if applicable):
   ```bash
   javac Main.java
   ```

## Usage

Provide instructions on how to use the project.

### Running the Project

1. Execute the main class (if applicable):
   ```bash
   java Main
   ```
   
2. Follow on-screen instructions or command line prompts.

### Features
Here are the main features of your project along with examples of commands or interactions:
1. **Ajouter un client**
   - **Example:** Ajouter un nouveau client avec nom, prénom et numéro CIN.

2. **Supprimer un client**
   - **Example:** Supprimer un client existant en saisissant son numéro CIN.

3. **Ajouter un fournisseur**
   - **Example:** Ajouter un nouveau fournisseur avec nom, prénom et numéro CIN (pour une personne physique) ou raison sociale et domaine d'activité (pour une société).

4. **Supprimer un fournisseur**
   - **Example:** Supprimer un fournisseur existant en fournissant son ID.

5. **Ajouter un produit**
   - **Example:** Ajouter un nouveau produit avec libellé, prix TTC et statut de fin de série.

6. **Supprimer un produit**
   - **Example:** Supprimer un produit existant en fournissant son libellé.

7. **Enregistrer une commande**
   - **Example:** Enregistrer une nouvelle commande avec numéro de commande, dates de commande et de livraison, ainsi que les produits commandés.

8. **Consulter la liste des fournisseurs**
   - **Example:** Afficher la liste des fournisseurs disponibles avec leurs détails comme l'ID, le nom, le prénom, etc.

9. **Lier une commande à un fournisseur**
   - **Example:** Associer une commande à un fournisseur existant en fournissant le numéro de commande et l'ID du fournisseur.

10. **Consulter le catalogue des produits**
    - **Example:** Afficher le catalogue des produits disponibles avec leurs détails comme le libellé, le prix TTC, etc.

11. **Consulter l'ID d'un fournisseur**
    - **Example:** Rechercher et afficher l'ID d'un fournisseur en fonction de son nom et prénom ou de sa raison sociale.

12. **Quitter**
    - **Example:** Permet de quitter l'application.

These features cover a range of functionalities for managing clients, suppliers, products, orders, and their relationships within the application. Adjust the examples as needed to fit the specific implementation details of your project.

## File Structure

Explain the organization of files within your project directory.

```
project/
│
├── bin/             # Compiled Java class files
├── data/            # CSV files for data storage
├── src/             # Source code files
│   ├── Main.java
│   ├── Utilitaire.java
│   ├── ...
│
├── .gitignore       # Git ignore file
└── README.md        # Project README file
```
## Contributing

Provide guidelines for how others can contribute to your project.

1. Fork the repository.
2. Create a new branch (`git checkout -b feature/branch-name`).
3. Make changes and commit (`git commit -am 'Add new feature'`).
4. Push to the branch (`git push origin feature/branch-name`).
5. Create a new Pull Request.
