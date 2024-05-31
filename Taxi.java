package myTaxiBooking;
import java.util.*;

public class Taxi {

	  static int id = 1;
	  
	  int taxiId;
	  char currentSpot ;
	  int freeTime;
	  int totalEarnings;
	  List<String>trips;
	  
	  Taxi(){
		  this.taxiId = id++;
		  this.currentSpot = 'A';
		  this.freeTime = 6;
		  this.totalEarnings = 0;
		  this.trips = new ArrayList<>();
	  }
	  
	  public String toString() {
		  return "Taxi ID:"+this.taxiId;
	  }
	  
	  public static List<Taxi> createTaxis(int n){
		  List<Taxi> taxis = new ArrayList<>();
		  
		  for(int i=0;i<n;i++) {
			  Taxi taxi = new Taxi();
			  taxis.add(taxi);
		  }
		  return taxis;
	  }
	  
	  public void printDetails() {
		  System.out.println("TaxiID:"+ this.taxiId +" Total Earnings:"+this.totalEarnings);
		  System.out.println("TaxiID    BookingID    CustomerID    From    To    PickupTime    DropTime    Amount");
	        for(String trip : trips)
	        {
	            System.out.println(this.taxiId + "          " + trip);
	        }
	        System.out.println("--------------------------------------------------------------------------------------\n");
	  }
	  
	  public void printTaxiDetails(){
		  System.out.println("Taxi ID:"+this.taxiId+" Total Earnings:"+this.totalEarnings+" Current Spot:"+ this.currentSpot+" Free Time:"+this.freeTime+"");
	  }
	  
}
