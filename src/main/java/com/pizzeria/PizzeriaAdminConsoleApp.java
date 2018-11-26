package com.pizzeria;

import java.util.Collections;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import Bean.Pizza;
import Dao.PizzaMemDao;
import exception.DeletePizzaException;
import exception.SavePizzaException;
import exception.UpdatePizzaException;
import javafx.application.Application; 
import javafx.scene.Scene; 
import javafx.scene.layout.Pane; 
import javafx.scene.paint.Color; 
import javafx.scene.shape.Rectangle; 
import javafx.stage.Stage;
import model.PizzaEnum;
import triPizza.TriPrixCroissant;
import triPizza.TriPrixDecroissant;
import javafx.application.Application; 
import javafx.scene.Scene; 
import javafx.scene.layout.Pane; 
import javafx.scene.paint.Color; 
import javafx.scene.shape.Rectangle; 
import javafx.stage.Stage; 
import javafx.application.Application; 
import javafx.scene.Scene; 
import javafx.scene.layout.Pane; 
import javafx.scene.paint.Color; 
import javafx.scene.shape.Rectangle; 
import javafx.stage.Stage; 
  



public class PizzeriaAdminConsoleApp  {

	public static void main(String[] args) throws UpdatePizzaException  {
		
		
	
		PizzaMemDao dao = new PizzaMemDao();
		
		List <Pizza> pizzas = dao.findAllPizzas();
		
		boolean continuer = true;
		
		while(continuer == true) {
			System.out.println(	"***** Pizzeria Administration *****");
			System.out.println("1. Lister les pizzas");
			System.out.println("2. Ajouter une nouvelle pizza");
			System.out.println("3. Mettre à jour une pizza");
			System.out.println("4. Supprimer une pizza");
			System.out.println("5. Trier les pizza par prix decroissant");
			System.out.println("6. Trier les pizza par prix Croissant");
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
				sc.nextLine();
				System.out.println(" Veuillez saisir la categorie :");	
			
			
				
			
				
				
				try {
					// verification du type de pizza et presence du type
					PizzaEnum type = PizzaEnum.valueOf(sc.nextLine().toUpperCase());
					Pizza pizzaToAdd = new Pizza(code, nom, prix, type);
					dao.addPizza(pizzaToAdd);
				} catch (SavePizzaException e1) {
				
				System.err.println(e1.getMessage());
					
				// si le type de pizza existe pas on le met dans autre
				} catch (IllegalArgumentException et) {
					System.out.println("Ce type de pizza n'existe pas, pizza ajoutée a la categorie Autre");
					Pizza pizzaToAdd = new Pizza(code, nom, prix, PizzaEnum.AUTRE);
					try {
						dao.addPizza(pizzaToAdd);
					} catch (SavePizzaException e) {
						System.err.println(e.getMessage());
					
					}
				}
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
				sc.nextLine();
				System.out.println(" Veuillez saisir la categorie :");	
				PizzaEnum type;
				
				try {
					// verification du type de pizza et presence du type
					 type = PizzaEnum.valueOf(sc.nextLine().toUpperCase());
				}catch (IllegalArgumentException et) {
					System.err.println("Ce type de pizza n'existe pas, pizza ajoutée a la categorie Autre");
					 type = PizzaEnum.AUTRE;
				}
				// instanciation d'une nouvelle pizza pour la modifier
				Pizza pizzaToModif = new Pizza();
				
				// modification de la pizza
				pizzaToModif.setCode(newCode);
				pizzaToModif.setDésignation(newNom);
				pizzaToModif.setPrix(newPrix);
				pizzaToModif.setCategoriePizza(type);
				
				try{
					dao.updatePizza(codePizza, pizzaToModif);
				}catch(UpdatePizzaException e) {
					
					System.err.println(e.getMessage());
				}

				break;
				
			case 4:		
				
				
				System.out.println(" Suppression d'une pizza");
				System.out.println("Veuillez choisir le code de la pizza à supprimer");
				//remise a 0 
				sc.nextLine();
				
				String codePizzaAsupprimer = sc.nextLine();
					
				try {
					dao.deletePizza(codePizzaAsupprimer);
				} catch (DeletePizzaException e) {
				 System.err.println(e.getMessage());
				}
			
					break;
					
					
			case 5:
				TriPrixCroissant tridecroissant = new TriPrixCroissant();
				
				Collections.sort(pizzas, tridecroissant);
				System.out.println("Pizza trier par prix decroisant \r\n");
				dao.listPizza();
				break;
				
			case 6:
				TriPrixDecroissant tricroissant = new TriPrixDecroissant();
				
				Collections.sort(pizzas, tricroissant);
				System.out.println("Pizza trier par prix croisant \r\n");
				dao.listPizza();
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
