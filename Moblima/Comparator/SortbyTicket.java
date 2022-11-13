package Moblima.Comparator;
import java.util.Comparator;

import Moblima.Entities.Movie;
/**
 * Class to sort movies by ticket sales
 * @author Ian
 * @version 1.0
 */
public class SortbyTicket implements Comparator<Movie> {
	/**
	 *compare movie averageRatings and sort them in descending order
	 *@param Movie objects
     * @return the value 0 if  o1.getTicketsSize is numerically equal to o2.getTicketsSize;
     * a value less than 0 if 
     * o1.getTicketsSize is numerically less than o2.getTicketsSize and a value greater than 0
     * if o1.getTicketsSize is numerically greater than o2.getTicketsSize.
     * */
	@Override
    public int compare(Movie o1, Movie o2) {

        return Integer.compare(o1.getTicketsSize(), o2.getTicketsSize());
}
}
