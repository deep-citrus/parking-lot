package com.gojek.park;

import java.util.List;

import com.gojek.car.Car;
import com.gojek.parkinglot.Slot;

public interface ParkingManagement {
	
	Slot park(Car car);
	
	Slot unpark(int slotNumber);
	
	List<Slot> status();
	
	List<String> getAllCarsInColor(String color);
	
	Slot getCarSlotWithRegNo(String regNo);
	
	List<Slot> getAllSlotsWithColor(String color);
		
}
