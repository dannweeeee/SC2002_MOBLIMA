package Moblima.Utils;
import java.util.Comparator;

import Moblima.Entities.Movie;
public class SortbyTicket implements Comparator<Movie> {
	@Override
    public int compare(Movie o1, Movie o2) {

        return Integer.compare(o1.getTicketsSize(), o2.getTicketsSize());
}
}
