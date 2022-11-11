package Moblima.Entities;

/**
 * Rating class
 * @author Ian
 * @version 1.0
 *
 */
public class Rating {

    private double rating;
    private User user;

    /**
     * Default Constructor for Rating
     * @param rating rating value
     * @param user user who provided the rating
     */
    public Rating(double rating,User user) {
        this.rating = rating;
        this.user=user;
    }

    /**
     * Set method for rating 
     * @param rating updated value of rating
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