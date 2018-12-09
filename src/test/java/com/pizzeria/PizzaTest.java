package com.pizzeria;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.sql.Connection;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import exception.DeletePizzaException;
import exception.NotExistingPizzaException;
import exception.StockageException;
import exception.UpdatePizzaException;
import model.bean.Categorie;
import model.bean.Pizza;

import model.dao.pizza.PizzaMemDao;
import utils.Connect;
import utils.ResetBDD;



public class PizzaTest {
	
	
	static Pizza pizza = new Pizza("Mer", "Merguez", 10, new Categorie(1));
	static PizzaMemDao dao;
	static List <Pizza> pizzas;
	
	@BeforeClass
	public static void setUpBeforeClass() {
		 dao = new PizzaMemDao();
	}

	
	
	@Before
	public  void setUpBefore() {
		Connection co = null;
		try {
			co = Connect.getConnection();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ResetBDD.delete(co );
		ResetBDD.add(co);
	}

	@Test
	public void TestverifierDonneePizza ()  {
		
		
		 
	
					try {
						pizza.dataControl();

					} catch (StockageException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
		assertNotNull("La categorie est  null", pizza.getCategoriePizza());
		assertNotNull("Le code est  null", pizza.getCode());
		assertNotNull("La designation est  null", pizza.getDésignation());
		assertNotNull("L'id est null", pizza.getId());
		assertNotNull("Le prix est  null", pizza.getPrix());
		
	}
	
	@Test
	public void TestverifUppercase ()  {
		

			Boolean verifUppercase = pizza.getCode().toUpperCase().equals(pizza.getCode());
			
			assertTrue("Le code est pas en majusucule",verifUppercase);
			
	}
		
	@Test
	public void TestverifPrix ()  {
		
			pizza.setPrix(25);
		 
		 try {
			pizza.dataControl();
		} catch (StockageException e) {
			fail("le prix est soit negatif, soit non compris dans l'intervalle de prix");
		}
		
		
		
	}
	
	@Test
	public void TestverifDesignationAndCode ()  {
		
		pizza.setCode("sqdq");
	pizza.setDésignation("abbaba");
		PizzaMemDao dao = new PizzaMemDao();

		 
		Boolean exist = false; 
		
		
		for (Pizza pizzo : pizzas) {
			
			if(	pizzo.getCode().equals(pizza.getCode())) {
				
				fail("le code de la pizza existe");
				exist = true;
			}
			
			if( pizzo.getDésignation().equals(pizza.getDésignation())) {
				fail("la designation de la pizza existe");
				exist = true;	
			}
		}	
	}
	
	
	@Test
	public void TestnonRegression ()  {
		
		final int CODE_LENGHT = 4;
		final int PRIX_MAX = 30;
		final int PRIX_MIN = 5;
		System.out.println(pizza.getCode() + "sds");
		
		String message ="";
		
	
		if(pizza == null) {
			fail(" la pizza est null. \r\n");
		}
		
		if(pizza.getId()<0) {
			message += " l'id ne peut pas etre négatif. \r\n";
		}
		
		if(pizza.getCode().trim().length()> CODE_LENGHT) {
			message += " le code ne peut pas avoir plus de " + CODE_LENGHT + " caracteres. \r\n";
		}
		if(pizza.getPrix()<PRIX_MIN || pizza.getPrix() > PRIX_MAX) {
			message +=  " le prix doit etre compris entre " + PRIX_MIN + " et " + PRIX_MAX + ". \r\n";
		}
		
		if(!message.isEmpty() ||  message.trim().length() > 0) {
			fail(message);
		}
		
	}

	@Test
	public void TestUpdateOK()  {
		
		int id  = 1;
		try {
			dao.updatePizza(id, pizza);
		} catch (UpdatePizzaException e) {
			fail("La pizza n'existe pas");
			e.printStackTrace();
		}		
	}
	
	@Test (expected = UpdatePizzaException.class)
	public void TestUpdateNotExistPizza() throws UpdatePizzaException {
		
		
		int id = 10;
		dao.updatePizza(id, pizza);	
	}
	
	
	@Test(expected = StockageException.class)
	public void TestUpdateDataNotCorrect() throws StockageException   {
		
		pizza.setPrix(50);
		
		pizza.dataControl();
		
			
	}
	
	
	@Test(expected = StockageException.class)
	public void TestAddDataNotCorred() throws StockageException   {
		
		pizza.setCode("sqssdq");
		pizza.setPrix(50);
		
		pizza.dataControl();
		
			
	}
	
	
	
	
	@Test
	public void NotDeleteIfNotExist() throws DeletePizzaException {

		
		if(dao.isPizzaExists(10)) {
			dao.deletePizza(10);
		}else {
			fail("cette pizza n'existe pas");
		}
		
	}
	
	@Test(expected = DeletePizzaException.class)
	public void DeleteNotExistPizza() throws DeletePizzaException {
	

			dao.deletePizza(8);

		
	}
	
	
	@Test
	public void TestReadExistingPizza()  {

	
		
	Pizza pizzaTest = null;
	try {
		pizzaTest = dao.findPizzaById(2);
	} catch (NotExistingPizzaException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

		assertNotNull(pizzaTest);
	}
	
	@Test(expected = NotExistingPizzaException.class)
	public void TestReadNotExistingPizza() throws NotExistingPizzaException {

		String codeTest = "sqqsddqd";
	
		
	Pizza pizzaTest = dao.findPizzaById(10);



	}
	
	@After 
	public void tearDown() {
		pizza = null;
		pizzas = null;
	}
	
	@AfterClass 
	public static void tearDownAfterClass() {
		dao = null;
	}
	
	
}
