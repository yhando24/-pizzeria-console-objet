package model.dao.Categorie;

import java.util.ArrayList;
import java.util.List;

import exception.NotExistingPizzaException;
import exception.UpdatePizzaException;
import model.bean.Categorie;
import model.bean.Pizza;

public interface ICategorieDao {

	
	ArrayList <Categorie> findAllCategories();
	void updateCategorie(int id,  String nomCategorie);
	Categorie findCategorieById(int id) throws NotExistingPizzaException;
	void listCategorie(ArrayList <Categorie> categories);
}
