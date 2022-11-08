package Moblima.Entities;

/**
 * @author Ian
 *
 *
 */
public class Rating {

    private double rating;
    private User user;

    /**
     * @param rating
     * @param user
     */
    public Rating(double rating,User user) {
        this.rating = rating;
        this.user=user;
    }

    /**
     * @param rating
     */
    public void SetRating(double rating) {
        this.rating = rating;

    }

    /**
     * @return
     */
    public double GetRating() {

        return rating;
    }
    /**
     * @return
     */
    public User GetUser() {

        return this.user;
    }

}