package Moblima.Comparator;
import java.util.Comparator;

import Moblima.Entities.Movie;
/**
 * Class to sort movies by rating
 * @author Ian
 * @version 1.0
 */
public class SortbyRating implements Comparator<Movie> {
	/**
	 * compare movie averageRatings and sort them in descending order
	 * @param o1 movie 1 for comparison
	 * @param o2 movie 2 for comparison
     * @return the value 0 if rating 1 is numerically equal to rating 2 a value 
     * less than 0 if 
     * rating1 is numerically less than rating2; and a value greater than 0
     * if rating1 is numerically greater than rating2.
     * */
	@Override
    public int compare(Movie o1, Movie o2) {
		Double rating1, rating2;
		if (o1.getAverageRatings()=="NA")
			rating1 = 0.0;
		else
			rating1 = Double.parseDouble(o1.getAverageRatings());
		
		if (o2.getAverageRatings()=="NA")
			rating2 = 0.0;
		else
			rating2 = Double.parseDouble(o2.getAverageRatings());
		
        return Double.compare(rating1, rating2);
}
}
