package classe;

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
	private double prix;
	
	private static  int compteur = 0;
	
	/**
	 * 
	 * @param code : designe le CODE de la pizza
	 * @param désignation : Designe le nom utilitaire de la pizza
	 * @param prix : Prix en euros de la pizza
	 * @param id : Identifiant unique qui ajoute un au compteur static
	 */
	public Pizza(String code, String désignation, double prix) {
		this.id = compteur++;
		this.code = code;
		this.désignation = désignation;
		this.prix = prix;			
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
	public double getPrix() {
		return prix;
	}
	/**
	 * 
	 * @param prix de la pizza en float
	 */
	public void setPrix(double prix) {
		this.prix = prix;
	}

	@Override
	public String toString() {
		return code + " -> " + désignation + "("+ prix + "€)";
	}
	
	
	
}
