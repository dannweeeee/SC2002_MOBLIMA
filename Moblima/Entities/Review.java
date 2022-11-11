package Moblima.Entities;

/**
 * Review class for movie
 * @author Ian
 * @version 1.0
 */
public class Review {
	private String Review;
	private User user;
	
	/**
     * Constructor of Review
	 * @param review review added by user
	 * @param user user object who entered the review
	 */
	public Review(String review,User user) {
        this.Review = review;
        this.user=user;
    }

    /**
     * Set method for review
     * @param review updated review value
     */
    public void SetReview(String review) {
        this.Review = review;

    }

    /**
     * Get method for review
     * @return review
     */
    public String GetReview() {

        return this.Review;
    }
    /**
     * Get method for user
     * @return user
     */
    public User GetUser() {

        return this.user;
    }

    /**
     *
     */
    @Override
    public String toString() {
        return "User: "+ this.user.getName()+'\n'+Review;
    }
}
