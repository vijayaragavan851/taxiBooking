package myTaxiBooking;

import java.lang.*;
import java.util.*;

public class Booking {
	
	public static List<Taxi> getFreeTaxis(List<Taxi> taxis,char pickupPoint,char dropPoint,int pickupTime){
		
		List<Taxi> freeTaxis = new ArrayList<>();
		
		for(Taxi taxi:taxis) {
			
			if((taxi.freeTime <= pickupTime) && (Math.abs((taxi.currentSpot - '0')-(pickupPoint - '0')) <= pickupTime - taxi.freeTime)){
				freeTaxis.add(taxi);
			}

		}
		return freeTaxis;
	}
	
	public static void main(String arg[]){
		
		Scanner scanner = new Scanner(System.in);
		
		List<Taxi> taxis = Taxi.createTaxis(4);
		
		int id = 1;
		
		while(true) {
			System.out.println("--------------------------------------------");
			System.out.println("\t\tWelcome to Taxi Booking");
			System.out.println("--------------------------------------------");
			System.out.println("1-> Book Taxi");
			System.out.println("2->Print Details");
			System.out.print("Enter your choice:");
			int choice = scanner.nextInt();
			
			switch(choice) {
			    
			   case 1:
				   int customerId = id;
				   System.out.print("Enter the pickup point:");
				   char pickupPoint = scanner.next().charAt(0);
				   System.out.print("Enter the drop point:");
				   char dropPoint = scanner.next().charAt(0);
				   System.out.print("Enter the pickup time:");
				   int pickupTime = scanner.nextInt();
				   
				   if((pickupPoint >='A' && pickupPoint <='F') && (dropPoint>='A' && dropPoint <='F')) {
					   System.out.println("The input read successfully");
					   List<Taxi> freeTaxis =  getFreeTaxis(taxis,pickupPoint,dropPoint,pickupTime);
					   
					   if(freeTaxis.size() == 0) {
						   System.out.println("There is no taxi available now");
						   break;
					   }
					   
					   Collections.sort(freeTaxis,(a,b)->a.totalEarnings- b.totalEarnings);
					   System.out.println(freeTaxis);
					   bookTaxi(freeTaxis,customerId,pickupPoint,dropPoint,pickupTime);
					   id++;
				   }else {
					   System.out.println("The given input is invalid");
					   break;
				   }
				   break;
			   case 2:
				   for(Taxi taxi:taxis) 
					      taxi.printDetails();
				   for(Taxi taxi:taxis) 
				      taxi.printTaxiDetails();
				   break;
			  default:
				  return;
			}
		}
		
	}

	private static void bookTaxi(List<Taxi> freeTaxis, int customerId, char pickupPoint, char dropPoint,
			int pickupTime) {
		
		
		Collections.sort(freeTaxis,(a,b)-> Math.abs(a.currentSpot - dropPoint) - Math.abs(b.currentSpot - dropPoint));
		        
		Taxi nearByTaxi = freeTaxis.get(0);
		
		
		int distance = Math.abs((pickupPoint-'0') - (dropPoint - '0'))*15;
		
		nearByTaxi.currentSpot = dropPoint;
		
		int amount = (distance-5)*10 + 100;
		
		nearByTaxi.totalEarnings += amount;
		
		nearByTaxi.freeTime = pickupTime + distance/15;
		nearByTaxi.trips.add(customerId+"\t\t"+customerId+"\t"+pickupPoint+"\t"+dropPoint+"\t"+pickupTime+"\t\t"+nearByTaxi.freeTime+"\t\t"+amount);
		
		System.out.println("Taxi:"+nearByTaxi.taxiId+" is allocated");
		
	}

}
