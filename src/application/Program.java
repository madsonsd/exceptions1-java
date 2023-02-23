package application;

import java.text.ParseException;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import model.entities.Reservation;

public class Program {

	public static void main(String[] args) throws ParseException {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		Scanner sc = new Scanner(System.in);

		System.out.print("Room number: ");
		int number = sc.nextInt();
		sc.nextLine();
		System.out.print("Check-in date (dd/MM/yyyy): ");
		LocalDate checkIn = LocalDate.parse(sc.nextLine(), dtf);
		System.out.print("Check-out date (dd/MM/yyyy): ");
		LocalDate checkOut = LocalDate.parse(sc.nextLine(), dtf);

		// if (Duration.between(checkIn.atStartOfDay(),
		// checkOut.atStartOfDay()).toDays() < 0) {
		if (!checkOut.isAfter(checkIn)) {
			System.out.println("Error in reservation: Check-out date must be after check-in date");
		} else {
			Reservation reservation = new Reservation(number, checkIn, checkOut);
			System.out.println("Reservation " + reservation);

			System.out.println();
			System.out.println("Enter data to update the reservation:");
			System.out.print("Check-in date (dd/MM/yyyy): ");
			checkIn = LocalDate.parse(sc.nextLine(), dtf);
			System.out.print("Check-out date (dd/MM/yyyy): ");
			checkOut = LocalDate.parse(sc.nextLine(), dtf);

			String error = reservation.updateDates(checkIn, checkOut);
			if (error != null) {
				System.out.println("Error in reservation " + error);
			}
			else {
				System.out.println("Reservation " + reservation);
			}
		}
		
		sc.close();
	}
}
