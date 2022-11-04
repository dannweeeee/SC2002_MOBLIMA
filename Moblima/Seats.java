package Moblima;

public class Seats {
    private String row;
    private String col;

    public Seats(String row, String col){
        this.row = row;
        this.col = col;
    }

    public void setRow(String row){
        this.row = row;
    }

    public void setCol(String col){
        this.col = col;
    }

    public String getSeat(){
        return this.row + this.col;
    }
}
