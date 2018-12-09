package model.bean;

import exception.StockageException;

/**
 * 
 * @author Youcef Handoura	
 *	Bean Pizza
 */
public class Pizza {

	

	/** 
	 * 
	 */
	private int id;
	private String code;
	private String désignation;
	private float prix;
	private Categorie CategoriePizza;
	private static  int compteur = 0;
	
	
	
	 private final int CODE_LENGHT = 4;
	 private final int PRIX_MAX = 30;
	 private final int PRIX_MIN = 5;
	
	/**
	 * 
	 * @param code : designe le CODE de la pizza
	 * @param désignation : Designe le nom utilitaire de la pizza
	 * @param prix : Prix en euros de la pizza
	 * @param id : Identifiant unique qui ajoute un au compteur static
	 */
	public Pizza(String code, String désignation, float prix, Categorie CategoriePizza) {
		this.id = compteur++;
		this.code = code;
		this.désignation = désignation;
		this.prix = prix;	
		this.CategoriePizza = CategoriePizza;
	}
	
	public Pizza() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 * @return l'id de la pizza
	 */
	public int getId() {
		return id;
	}
	/**
	 * 
	 * @param id modifie l'id de la pizza
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * 
	 * @return le code de la pizza
	 */
	public String getCode() {
		return code;
	}
	/**
	 * 
	 * @param code de la pizza
	 */
	public void setCode(String code) {
		this.code = code;
	}
	/**
	 * 
	 * @return la désignation de la pizza
	 */
	public String getDésignation() {
		return désignation;
	}
	/**
	 * 
	 * @param désignation de la pizza
	 */
	public void setDésignation(String désignation) {
		this.désignation = désignation;
	}
	/**
	 * 
	 * @return le prix en float de la pizza en euro
	 */
	public float getPrix() {
		return prix;
	}
	/**
	 * 
	 * @param prix de la pizza en float
	 */
	public void setPrix(float prix) {
		this.prix = prix;
	}

	
	
	public Categorie getCategoriePizza() {
		return CategoriePizza;
	}

	public void setCategoriePizza(Categorie categoriePizza) {
		CategoriePizza = categoriePizza;
	}

	@Override
	public String toString() {
		return  "id " + id + " => " + code + " -> " + désignation + "("+ prix + "€). Elle est de type " +  CategoriePizza;
	}
	
	
	
	public void dataControl() throws StockageException{
		
		String message = "";
		
		if(this.id<0) {
			message += " l'id ne peut pas etre négatif. \r\n";
		}
		
		if(this.code.trim().length()> CODE_LENGHT) {
			message += " le code ne peut pas avoir plus de " + CODE_LENGHT + " caracteres. \r\n";
		}
		if(this.prix<PRIX_MIN || this.prix > PRIX_MAX) {
			message +=  " le prix doit etre compris entre " + PRIX_MIN + " et " + PRIX_MAX + ". \r\n";
		}
		
		if(!message.isEmpty() ||  message.trim().length() > 0) {
			throw new StockageException(message);
		}
	}
	
}
