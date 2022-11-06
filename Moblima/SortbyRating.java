package Moblima;
import java.util.Comparator;
public class SortbyRating implements Comparator<Movie> {
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
