package Moblima.Entities;

/**
 * @author Ian
 *
 */
public class Review {
	private String Review;
	private User user;
	
	/**
	 * @param review
	 * @param user
	 */
	public Review(String review,User user) {
        this.Review = review;
        this.user=user;
    }

    /**
     * @param review
     */
    public void SetReview(String review) {
        this.Review = review;

    }

    /**
     * @return
     */
    public String GetReview() {

        return this.Review;
    }
    /**
     * @return
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
