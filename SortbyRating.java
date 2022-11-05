package Moblima;
import java.util.Comparator;
public class SortbyRating implements Comparator<Movie> {
	@Override
    public int compare(Movie o1, Movie o2) {

        return Double.compare(o1.getAverageRatings(), o2.getAverageRatings());
}
}
