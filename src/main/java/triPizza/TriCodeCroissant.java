package triPizza;

import java.util.Comparator;

import Bean.Pizza;

public class TriCodeCroissant implements Comparator<Pizza>{

	public int compare(Pizza p1, Pizza p2) {

		int ComparePrice = 0;
		ComparePrice = p1.getCode().compareTo(p2.getCode());
		return ComparePrice;
	}

}
