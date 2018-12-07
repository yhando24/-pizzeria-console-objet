package com.pizzeria;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import exception.DeletePizzaException;
import exception.SavePizzaException;
import exception.UpdatePizzaException;
import model.bean.Categorie;
import model.bean.Pizza;
import model.dao.Categorie.CategorieDao;
import model.dao.pizza.PizzaMemDao;
import triPizza.TriCodeCroissant;
import triPizza.TriPrixDecroissant; 
  



public class PizzeriaAdminConsoleApp  {

	public static void main(String[] args) throws UpdatePizzaException  {
		
		
	
		PizzaMemDao dao = new PizzaMemDao();
		
		CategorieDao CatDao = new CategorieDao();
		
		ArrayList <Pizza> pizzas = dao.findAllPizzas();
		
		boolean continuer = true;
		
		while(continuer == true) {
			System.out.println(	"***** Pizzeria Administration *****");
			System.out.println("1. Lister les pizzas");
			System.out.println("2. Ajouter une nouvelle pizza");
			System.out.println("3. Mettre à jour une pizza");
			System.out.println("4. Supprimer une pizza");
			System.out.println("5. Trouver les pizza de votre categorie");
			System.out.println("6. Trier les pizza par prix decroissant");
			System.out.println("7. Trier les pizza par code Croissant");
			System.out.println("99. Sortir ");

	
			Scanner sc = new Scanner(System.in);
			
			int choix = sc.nextInt();
		
			switch (choix) {
			
			case 1: // liste les pizza
					dao.listPizza(dao.findAllPizzas());
					break;
				
			case 2: // pour ajouter une pizza
				System.out.println(" Ajout d'une nouvelle pizza");
				// remise a 0 du scanner
				sc.nextLine();
				
				System.out.println(" Veuillez saisir le code :");
				String code = sc.nextLine();
				
				System.out.println(" Veuillez saisir le nom (sans espace) :");
				String nom = sc.nextLine();
							
				System.out.println(" Veuillez saisir le prix :");	
				float prix = sc.nextFloat();
				sc.nextLine();
				System.out.println(" Veuillez saisir la categorie :");	
			
			
				
			
				
				
				try {

					Pizza pizzaToAdd = new Pizza(code, nom, prix, new Categorie(sc.nextInt()));
					dao.addPizza(pizzaToAdd);
				} catch (SavePizzaException e1) {
				
				System.err.println(e1.getMessage());
		
				}
				break;
				
			case 3: // pour la mise a jour d'une pizza
				
				
				System.out.println(" Mise à jour d'une pizza");
		
				// remise a 0 du scanner
				sc.nextLine();
				
				// affichage des pizzas
				dao.listPizza(dao.findAllPizzas());
				
				System.err.println("Veuillez choisir l'id de la pizza à modifier");
				int id = sc.nextInt();
				boolean exist = dao.isPizzaExists(id);
				if(exist) {
							// saisie des valeurs
					sc.nextLine();
					System.err.println(" Veuillez saisir le nouveau code :");
					String newCode = sc.nextLine();
					System.err.println(" Veuillez saisir le nouveau nom (sans espace) :");
					String newNom = sc.nextLine();			
					System.err.println(" Veuillez saisir le nouveau prix :");
					float newPrix = sc.nextFloat();
					
					Categorie categorie = new Categorie();
					System.out.println(" Veuillez saisir la categorie : \r\n"
							+ "1 => FROMAGE \r\n"
							+ "2 => VIANDE \r\n"
							+ "3 => POISSON \r\n"
							
							+ "4 => AUTRE \r\n"
							);	
			
					categorie.setId(sc.nextInt());
				
					// instanciation d'une nouvelle pizza pour la modifier
					Pizza pizzaToModif = new Pizza();
					
					// modification de la pizza
					pizzaToModif.setCode(newCode);
					pizzaToModif.setDésignation(newNom);
					pizzaToModif.setPrix(newPrix);
					pizzaToModif.setCategoriePizza(categorie);
					
					try{
						dao.updatePizza(id, pizzaToModif);
					}catch(UpdatePizzaException e) {
						
						System.err.println(e.getMessage());
					}
				}else {
					System.err.println("Aucune Pizza avec cette id n'existe");
				}
				break;
				
			case 4:		// suppression d'une pizza
				
				

				//remise a 0 
				dao.listPizza(dao.findAllPizzas());
				System.out.println(" Suppression d'une pizza");
				System.err.println("Veuillez choisir l'id de la pizza à supprimer");
				int idPizzaDelete = sc.nextInt();
					
				try {
					dao.deletePizza(idPizzaDelete);
				} catch (DeletePizzaException e) {
				 System.err.println(e.getMessage());
				}
			
					break;
					
			case 5: 
				System.err.println("Saisir le genre de pizza que vous desirez");
				CatDao.listCategorie(CatDao.findAllCategories());
				sc.nextLine();
				dao.listPizza(dao.findPizzasByCategorieName( sc.nextLine()));
				
				break;
					
			case 6: 
				TriPrixDecroissant triDecroissant = new TriPrixDecroissant();
				
				Collections.sort(pizzas, triDecroissant);
				System.out.println("Pizza trier par prix decroisant \r\n");
				dao.listPizza(pizzas);
				break;
			
				
			case 7:
				TriCodeCroissant tridecroissant = new TriCodeCroissant();
				
				Collections.sort(pizzas, tridecroissant);
				System.out.println("Pizza trier par code croisant \r\n");
				dao.listPizza(pizzas);
				break;
				
			case 99:
				System.out.println(" Au revoir");
				continuer = false;
				break;

			default:
				break;
			}
		
		}
		
	}


  

	
	

}
