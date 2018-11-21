package com.pizzeria;

import java.util.Scanner;

import Dao.PizzaMemDao;
import classe.Pizza;

public class PizzeriaAdminConsoleApp {

	public static void main(String[] args) {
		
		PizzaMemDao dao = new PizzaMemDao();
		
		Pizza [] pizzas = dao.findAllPizzas();
		
		boolean continuer = true;
		
		while(continuer == true) {
			System.out.println(	"***** Pizzeria Administration *****");
			System.out.println("1. Lister les pizzas");
			System.out.println("2. Ajouter une nouvelle pizza");
			System.out.println("3. Mettre à jour une pizza");
			System.out.println("4. Supprimer une pizza");
			System.out.println("99. Sortir ");

	
			Scanner sc = new Scanner(System.in);
			
			int choix = sc.nextInt();
		
			switch (choix) {
			
			case 1:
					dao.listPizza();
					break;
				
			case 2:
				System.out.println(" Ajout d'une nouvelle pizza");
				// remise a 0 du scanner
				sc.nextLine();
				
				System.out.println(" Veuillez saisir le code :");
				String code = sc.nextLine();
				
				System.out.println(" Veuillez saisir le nom (sans espace) :");
				String nom = sc.nextLine();
							
				System.out.println(" Veuillez saisir le prix :");	
				double prix = sc.nextDouble();
				
				Pizza pizzaToAdd = new Pizza(code, nom, prix);
				
				dao.addPizza(pizzaToAdd);

				
				break;
				
			case 3:
				
				
				System.out.println(" Mise à jour d'une pizza");
				
				// remise a 0 du scanner
				sc.nextLine();
				
				// affichage des pizzas
				dao.listPizza();
				
				System.out.println("Veuillez choisir le code de la pizza à modifier");
				String codePizza = sc.nextLine();
				
				// saisie des valeurs
				System.out.println(" Veuillez saisir le nouveau code :");
				String newCode = sc.nextLine();
				System.out.println(" Veuillez saisir le nouveau nom (sans espace) :");
				String newNom = sc.nextLine();			
				System.out.println(" Veuillez saisir le nouveau prix :");
				Double newPrix = sc.nextDouble();
				
				// instanciation d'une nouvelle pizza pour la modifier
				Pizza pizzaToModif = new Pizza();
				
				// modification de la pizza
				pizzaToModif.setCode(newCode);
				pizzaToModif.setDésignation(newNom);
				pizzaToModif.setPrix(newPrix);
				
			
				dao.updatePizza(codePizza, pizzaToModif);

				break;
				
			case 4:		
				
				
				System.out.println(" Suppression d'une pizza");
				System.out.println("Veuillez choisir le code de la pizza à supprimer");
				//remise a 0 
				sc.nextLine();
				
				String codePizzaAsupprimer = sc.nextLine();
					
				dao.deletePizza(codePizzaAsupprimer);
			
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
