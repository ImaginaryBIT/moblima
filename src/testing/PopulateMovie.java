package testing;

import java.util.ArrayList;
import java.util.List;

import database.SerializeDB;
import entity.Cinema;
import entity.Movie;
import entity.Review;
import entity.ShowTime;
import entity.Ticket;
import entity.TimeSlot;

public class PopulateMovie {

	
	public static void main(String[] args) {

		
		try {
			/////////////////////////////// Movie //////////////////////////////////////////
                        SerializeDB.writeSerializedObject("Movie.ser", new ArrayList());
                        createMovie("My Little Pony");
                        createMovie("Marvel Thor: Ragnarok");
                        createMovie("DC Justice League");
                        createMovie("The Promise (2017)");
                        createMovie("Chasing The Dragon");
                        

		} catch (Exception e) {
                        throw e;
		}
	}
        private static void createMovie(String title){
            
            List movieList = (ArrayList<Movie>) SerializeDB.readSerializedObject("Movie.ser");
            
            
            ArrayList<ShowTime> showtimeList = new ArrayList<>();
            ArrayList<Review> reviewList = new ArrayList<>();
            ArrayList<String> myStringArray = new ArrayList<>();
            Movie newMovie;


            List list;
            //get cinema from cinema.ser 
            //populate ticket from cinema seat
            list = (ArrayList<Cinema>) SerializeDB.readSerializedObject("Cinema.ser");
            for (int i = 0; i < list.size(); i++) {
                    Cinema cnma = (Cinema) list.get(i);
                    if(i == 0 || i == 1 || i == 5 || i == 6 || i == 9){
                        //get first 3 timeslot and create show time
                        for(int ts = 0 ; ts < cnma.getTimeSlot().size() ; ts ++){
                            Ticket[] tckt = new Ticket[cnma.getSeat().length];
                            if(ts == 0 || ts == 1 || ts == 2 || ts == 9 || ts == 10 || ts == 19 || ts == 20){
                                for(int s = 0; s < cnma.getSeat().length; s++){
                                    //set price to 0 will be set upon booking base on system setting
                                    float price = 0.0f;

                                    tckt[s] = new Ticket(s,cnma.getSeat()[s],price,Ticket.AVAILABLE);


                                }

                                cnma.getTimeSlot().get(ts).setStatus(TimeSlot.UNAVAILABLE);
                                ShowTime st = new ShowTime(ts, cnma,cnma.getTimeSlot().get(ts).getDateTime(),tckt);
                                showtimeList.add(st);
                            
                            }
                        }

                    }
            }
            int id = movieList.size()+1 ;
            myStringArray.add("actor1");
            myStringArray.add("actor2");
            newMovie = new Movie(id,title,myStringArray, "Jayson Thiessen","English", "description movie",90,reviewList,"Digital","G", showtimeList, "now showing");
            movieList.add(newMovie);
            SerializeDB.writeSerializedObject("Movie.ser", movieList);
            SerializeDB.writeSerializedObject("Cinema.ser", list);
            //try print the created movie
            list = (ArrayList<Cinema>) SerializeDB.readSerializedObject("Movie.ser");
            for (int i = 0; i < list.size(); i++) {

                    /*printing out the seat base on tickets inside showtime of movie*/
                    Movie mov = (Movie) list.get(i);
                    System.out.println("Movie title	: " + mov.getTitle());
                    System.out.println("Movie Description	: " + mov.getSynopsis());
                    for(int t = 0; t < mov.getShowTimes().size(); t++){
                            System.out.println("Show Time Id: " + mov.getShowTimes().get(t).getShowTimeId());
                            System.out.println("Show Time Date: " + mov.getShowTimes().get(t).getShowDate());
                            System.out.println("Show Time Time: " + mov.getShowTimes().get(t).getShowTime());
                            int prevRow = 0;
                            System.out.println("---------Screen----------");
                            //get ticket inside showtime of a movie
                            for(int z = 0; z < mov.getShowTimes().get(t).getTickets().length; z++){
                                    //check if the row change and do the line break
                                    if(mov.getShowTimes().get(t).getTickets()[z].getSeat().getRow() != prevRow){
                                        System.out.println("");
                                    }
                                    //print layout [00] ,[--]

                                    //if ticket is available print row and column else print [--]
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
                    /*end of printing seat */
            }
        }
}
