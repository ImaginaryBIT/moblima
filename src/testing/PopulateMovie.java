package testing;

import java.io.*;
import java.util.List;
import java.util.ArrayList;

import database.SerializeDB;
import entity.*;
import java.util.Date;

public class PopulateMovie {

	
	public static void main(String[] args) {

		List list;
		try {
			/////////////////////////////// Cinema //////////////////////////////////////////
                        
                        ArrayList<Movie> movieList = new ArrayList<>();
                        ArrayList<ShowTime> showtimeList = new ArrayList<>();
                        ArrayList<Review> reviewList = new ArrayList<>();
                        String[] myStringArray = new String[]{"a","b","c"};
                        Movie newMovie;
      
                        
                        
                        //get cinema from cinema.ser 
                        //populate ticket from cinema seat
                        list = (ArrayList<Cinema>) SerializeDB.readSerializedObject("Cinema.ser");
			for (int i = 0; i < list.size(); i++) {
				Cinema cnma = (Cinema) list.get(i);
                                Ticket[] tckt = new Ticket[cnma.getSeat().length];
                                for(int s = 0; s < cnma.getSeat().length; s++){
                                    
                                    //movie type and getting system setting here
                                    // if newMovie.getType() == "something" price = something
                                    float price = 0.0f;
                                    //if holiday add 
                                    price += 2.0;
                                    if(s == 10 || s == 11 || s == 12){
                                        tckt[s] = new Ticket(s,cnma.getSeat()[s],price,Ticket.SOLD);
                                    }else{
                                        tckt[s] = new Ticket(s,cnma.getSeat()[s],price,Ticket.AVAILABLE);
                                    }
                                    
                                }
                                ShowTime st = new ShowTime(0, cnma,new Date(),tckt);
                                showtimeList.add(st);
                                break;
                        }
                        
                        newMovie = new Movie(0,"Test Mv1",myStringArray, "Unknown","en", "description movie",90,0.5f,reviewList,"anything","PG13", showtimeList, "now showing");
                        movieList.add(newMovie);
			SerializeDB.writeSerializedObject("Movie.ser", movieList);
                        //try print the created movie
                        list = (ArrayList<Cinema>) SerializeDB.readSerializedObject("Movie.ser");
			for (int i = 0; i < list.size(); i++) {
                                Movie mov = (Movie) list.get(i);
                                System.out.println("Movie title	: " + mov.getTitle());
                                System.out.println("Movie Description	: " + mov.getSynopsis());
                                for(int t = 0; t < mov.getShowTimes().size(); t++){
                                        System.out.println("Show Time Id: " + mov.getShowTimes().get(t).getShowTimeId());
                                        System.out.println("Show Time Date: " + mov.getShowTimes().get(t).getShowDate());
                                        System.out.println("Show Time Time: " + mov.getShowTimes().get(t).getShowTime());
                                        int prevRow = 0;
                                        System.out.println("---------Screen----------");
                                        for(int z = 0; z < mov.getShowTimes().get(t).getTickets().length; z++){
                                            
                                                if(mov.getShowTimes().get(t).getTickets()[z].getSeat().getRow() != prevRow){
                                                    System.out.println("");
                                                }
                                                //print layout [00] ,[XX]

                                                if(mov.getShowTimes().get(t).getTickets()[z].getStatus().equals(Ticket.AVAILABLE) ){
                                                    System.out.print("["+mov.getShowTimes().get(t).getTickets()[z].getSeat().getRow()+mov.getShowTimes().get(t).getTickets()[z].getSeat().getColumn()+"]");
                                                }else{
                                                    System.out.print("[--]");
                                                }
                                                
                                                //update the previous row
                                                prevRow = mov.getShowTimes().get(t).getTickets()[z].getSeat().getRow();
                                            
                                            
                                        }
                                        System.out.println("");
                                        System.out.println("--------------------------");
                                }
                        }
                        

		} catch (Exception e) {
                        throw e;
		}
	}
}