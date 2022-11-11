package Moblima.Entities;

/**
 * Seats class for all shows
 * @author Marcus Kho
 *
 */
public class Seats {
    private String row;
    private String col;

    /**
     * Default Constructor for Seats
     * @param row row of seat
     * @param col column of seat
     */
    public Seats(String row, String col){
        this.row = row;
        this.col = col;
    }

    /**
     * Set method for Row
     * @param row row of seat
     */
    public void setRow(String row){
        this.row = row;
    }

    /**
     * Set method for Column
     * @param col column of seat
     */
    public void setCol(String col){
        this.col = col;
    }

    /**
     * Get Seat function
     * @return Row + column format (E.g. A1)
     */
    @Override
    public String toString(){
        return this.row + this.col;
    }
}
