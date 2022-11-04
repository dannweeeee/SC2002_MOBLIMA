package Moblima;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
//import java.util.ArrayList;
import java.util.Scanner;

public class AdminView {
    private Scanner in;
    private movieHandler movieHandler;
    
    public void writeMovieToTextFile(String fileName) throws IOException{
        in = new Scanner(System.in);
        String movieName = "";
        String movieStatus = "";
        String movieDirector = "";
        String movieSynopsis = "";
        String movieCasts = "";
        //ArrayList<String> movieCasts = new ArrayList<>();
        System.out.print("Enter full name of movie: ");
        movieName = in.nextLine();
        System.out.print("Enter status of movie (Coming Soon, Now Showing): ");
        movieStatus = in.nextLine();
        System.out.print("Enter director of movie: ");
        movieDirector = in.nextLine();
        System.out.print("Enter synopsis of movie: ");
        movieSynopsis = in.nextLine();
        System.out.print("Enter casts of movie (e.g. Steve Rogers, Borat, Mr Bean): ");
        movieCasts = in.nextLine();
        /*while(in.nextLine() != ""){
            movieCastsString = in.nextLine();
            movieCasts.add(movieCastsString);
        }*/
        //FileWriter movieDatabase = new FileWriter(fileName);
        //PrintWriter write = new PrintWriter(movieDatabase);
        BookMyShow bookMyShow = new BookMyShow();
        Movie addNewMovie = new Movie(movieName, movieStatus, movieDirector, movieSynopsis, movieCasts, bookMyShow.getMovieDatabase());
        //write.println(movieCasts);
        //write.close();
}
}
