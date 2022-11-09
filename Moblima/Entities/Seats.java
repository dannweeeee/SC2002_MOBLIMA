package Moblima.Entities;

/**
 * @author 
 *
 */
public class Seats {
    private String row;
    private String col;

    /**
     * @param row
     * @param col
     */
    public Seats(String row, String col){
        this.row = row;
        this.col = col;
    }

    /**
     * @param row
     */
    public void setRow(String row){
        this.row = row;
    }

    /**
     * @param col
     */
    public void setCol(String col){
        this.col = col;
    }

    /**
     * @return
     */
    public String getSeat(){
        return this.row + this.col;
    }
}
