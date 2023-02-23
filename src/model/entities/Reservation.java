package model.entities;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Reservation {
	
	public static final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	
	private Integer roomNumber;
	private LocalDate checkIn;
	private LocalDate checkOut;
	
	public Reservation(Integer roomNumber, LocalDate checkIn, LocalDate checkOut) {
		this.roomNumber = roomNumber;
		this.checkIn = checkIn;
		this.checkOut = checkOut;
	}

	public Integer getRoomNumber() {
		return roomNumber;
	}

	public void setRoomNumber(Integer roomNumber) {
		this.roomNumber = roomNumber;
	}

	public LocalDate getCheckIn() {
		return checkIn;
	}

	public LocalDate getCheckOut() {
		return checkOut;
	}
	
	public Integer duration() {
		return (int) Duration.between(checkIn.atStartOfDay(), checkOut.atStartOfDay()).toDays();
	}
	
	public String updateDates(LocalDate checkIn, LocalDate checkOut) {
		LocalDate now = LocalDate.now();
		if (checkIn.isBefore(now) || checkOut.isBefore(now)) {
			return "Reservation dates for update must be future dates";
		}
		if (!checkOut.isAfter(checkIn)) {
			return "Check-out date must be after check-in date";
		}
		this.checkIn = checkIn;
		this.checkOut = checkOut;
		return null;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Room ");
		sb.append(roomNumber);
		sb.append(", check-in: ");
		sb.append(checkIn.format(dtf));
		sb.append(", check-out: ");
		sb.append(checkOut.format(dtf));
		sb.append(", ");
		sb.append(duration());
		sb.append(" nights");
		return sb.toString();
	}
	
}
