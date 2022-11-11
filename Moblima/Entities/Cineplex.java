package Moblima.Entities;
/**
 * @author pc
 *
 */
public class Cineplex {
	private int idCounter = 0;
	private int id;
	private String location;
	private int cineplexNo;
    
    /**
     * @param location
     */
    public Cineplex(String location) {
        idCounter += 1;
        this.id = idCounter;
    	this.location=location;
    }
    
	/**
	 * @return
	 */
	public String getLocation(){
		return this.location;
	}
	
	/**
     * @return
     */
    public int getId(){
        return this.id;
    }

	public void setCineplex(String location) {
        this.location=location;
    }
	
	/**
     * @return
     */
    public void setId(int id) {
    	this.id = id;
    }
	
	/**
	 * @return
	 */
	public int getCineplexNo() {
		return cineplexNo;
	}
	
	/**
	 * @param cineplexNo
	 */
	public void setCineplexNo(int cineplexNo) {
		this.cineplexNo=cineplexNo;
	}
	
	/**
	 *
	 */
	@Override
    public String toString() {
        return id + ": " + location;
    }
}