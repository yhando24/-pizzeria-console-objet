package triPizza;

import java.util.Comparator;

import Bean.Pizza;

public class TriPrixCroissant implements Comparator<Pizza>{

	public int compare(Pizza p1, Pizza p2) {

		int ComparePrice = 0;
		if(p1.getPrix()>p2.getPrix()) {
			ComparePrice = -1;
		}else {
			ComparePrice = 1;
		}
		return ComparePrice;
	}

}
