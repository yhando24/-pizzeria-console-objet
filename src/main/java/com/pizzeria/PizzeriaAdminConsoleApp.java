package com.pizzeria;

import java.util.Scanner;

import classe.Pizza;

public class PizzeriaAdminConsoleApp {

	public static void main(String[] args) {
		
		
		Pizza [] pizzas = {
							new Pizza("PEP","Péperoni",12.50),
							new Pizza("MAR","Margherita",14.00),
							new Pizza("REIN","La Reine",11.50),
							new Pizza("FRO","La 4 fromages",12.00),
							new Pizza("CAN","La cannibale",12.50),
							new Pizza("SAV","La savoyarde",13.00),
							new Pizza("ORI","L'orientale",13.50),
							new Pizza("IND","L'indienne",14.00),
		};
		
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
				System.out.println(" Liste des pizzas");
				for (Pizza pizza : pizzas) {
					System.out.println(pizza.toString());
					
				}
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
				
				
				Pizza [] pizzasTemp = new Pizza[pizzas.length+1];
				
				pizzasTemp = pizzas;
				
				pizzas = new Pizza[pizzasTemp.length];
				
				pizzas = pizzasTemp;
				Pizza pizzaToAdd = new Pizza(code, nom, prix);
				pizzas[pizzas.length-1] = pizzaToAdd;
				
				break;
				
			case 3:
				System.out.println(" Mise à jour d'une pizza");
				
				// remise a 0 du scanner
				sc.nextLine();
				
				
				System.out.println(" Liste des pizzas");
				for (Pizza pizza : pizzas) {
					System.out.println(pizza.toString());
					
				}
				
				System.out.println("Veuillez choisir le code de la pizza à modifier");
				String codePizza = sc.nextLine();
				
				
				Pizza pizzaToModif = new Pizza();
				for (Pizza pizza : pizzas) {
					if(pizza.getCode().equals(codePizza)) {
						pizzaToModif = pizza;
						
				
					
						
						System.out.println(" Veuillez saisir le nouveau code :");
						String newCode = sc.nextLine();
						
						System.out.println(" Veuillez saisir le nouveau nom (sans espace) :");
						String newNom = sc.nextLine();
						
						System.out.println(" Veuillez saisir le nouveau prix :");
						Double newPrix = sc.nextDouble();
						
		 			
						// modification de la pizza
						pizzaToModif.setCode(newCode);
						pizzaToModif.setDésignation(newNom);
						pizzaToModif.setPrix(newPrix);
						
						
						//rajout au tableau
						pizzas[pizzaToModif.getId()] = pizzaToModif;
						
					} else {
						System.out.println("Ce code n'existe pas");
					}
					
				}
				break;
				
			case 4:			
				System.out.println(" Suppression d'une pizza");
				

				System.out.println("Veuillez choisir le code de la pizza à supprimer");
				//remise a 0 
				sc.nextLine();
				
				String codePizzaAsupprimer = sc.nextLine();
				
				
			
		
					
					Pizza [] pizzasTemp2 = new Pizza[pizzas.length-1];
					
					
					
					int i = 0;
					for (Pizza pizzaDelete : pizzas) {
						if(!pizzaDelete.getCode().equals(codePizzaAsupprimer)) {
						pizzasTemp2[i] = pizzaDelete;
						i++;
						}
					}
					
					pizzas = new Pizza[pizzasTemp2.length];
					
					pizzas = pizzasTemp2;

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
