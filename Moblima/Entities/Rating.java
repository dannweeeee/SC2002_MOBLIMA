package Moblima.Entities;

/**
 * Rating class
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
     * Set method for rating 
     * @param rating
     */
    public void SetRating(double rating) {
        this.rating = rating;

    }

    /**
     * Get method for rating
     * @return rating
     */
    public double GetRating() {

        return rating;
    }
    /**
     * Get method for User
     * @return user 
     */
    public User GetUser() {

        return this.user;
    }

}