package Moblima;
import java.util.Comparator;
public class SortbyTicket implements Comparator<Movie> {
	@Override
    public int compare(Movie o1, Movie o2) {

        return Integer.compare(o1.getTicketsSize(), o2.getTicketsSize());
}
}
