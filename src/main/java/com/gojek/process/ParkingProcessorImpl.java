package com.gojek.process;

import java.util.List;

import com.gojek.car.Car;
import com.gojek.park.ParkingManagement;
import com.gojek.park.impl.ParkingManagementImpl;
import com.gojek.park.util.Command;
import com.gojek.park.util.CommonUtil;
import com.gojek.parkinglot.ParkingLot;
import com.gojek.parkinglot.Slot;

public class ParkingProcessorImpl implements ParkingProcessor {
	
	private ParkingManagement parkingManagement = null;;

	public String process(String strCommand) {
		try {
			String[] commandArray = strCommand.split(" ");
			Command command = Command.getCommand(commandArray[0]);
			if (command == null) {
				return "Wrong input, Please try again";
				
			}

			switch (command) {

			case create_parking_lot:
				
				if (commandArray.length != 2 || CommonUtil.getInteger(commandArray[1]) == null) {
					return "Wrong input, Please try again";
				}
				Integer slotSize = CommonUtil.getInteger(commandArray[1]);
				if(slotSize < 1) {
					return "Invalid Slot Numbers, should not be less then 1, Please try again";
				}
				ParkingLot parkingLot = new ParkingLot(slotSize);
				parkingManagement = new ParkingManagementImpl(parkingLot);
				return "Created a parking lot with "+slotSize+" slots";

			case park:
				
				if (!isVaildCommand(Command.park, parkingManagement) || commandArray.length != 3) {
					return "Wrong input, Please try again";
				}
				
				Slot parkedSlot = parkingManagement.park(new Car(commandArray[1], commandArray[2]));
				if (parkedSlot == null) {
					return "Sorry, parking lot is full";
				}
				if(parkedSlot.getId() == 0) {
					return "Invalid Car Data";
				}
				return "Allocated slot number: " + parkedSlot.getId();

			case leave:
				
				if (!isVaildCommand(Command.leave, parkingManagement) || commandArray.length != 2 || CommonUtil.getInteger(commandArray[1]) == null) {
					return "Wrong input, Please try again";
				}

				return "Slot number " + parkingManagement.unpark(CommonUtil.getInteger(commandArray[1])).getId() + " is free";

			case status:
				
				if (commandArray.length != 1) {
					return "Wrong input, Please try again";
				}
				StringBuilder builder = new StringBuilder("Slot No.	Registration Number		Colour");
				builder.append(System.getProperty("line.separator"));
				if(parkingManagement == null) {
					return builder.append("").toString();
				}
				List<Slot> statusSlots = parkingManagement.status();
				for (Slot slot : statusSlots) {
					if (slot != null && slot.getCar() != null) {
						builder.append(slot.getId() + "		" + slot.getCar().getRegistrationNumber() + "			" + slot.getCar().getColor());
						builder.append(System.getProperty("line.separator"));
					}
				}

				return builder.toString();

			case registration_numbers_for_cars_with_colour:
				
				if(commandArray.length != 2) {
					return "Wrong input, Please try again";
				}
				
				if(!isVaildCommand(Command.registration_numbers_for_cars_with_colour, parkingManagement)) {
					return "No Such car parked";
				}
				
				List<String> cardRegNoList = parkingManagement.getAllCarsInColor(commandArray[1]);
				if(cardRegNoList == null || cardRegNoList.size() == 0){
					return "Not found";
				}
				
				builder = new StringBuilder();
				int i = 1;
				for(String regNo : cardRegNoList) {
					builder.append(regNo);
					if(i < cardRegNoList.size()) {
						i++;
						builder.append(", ");
					}
				}
				return builder.toString();

			case slot_numbers_for_cars_with_colour:
				
				if(commandArray.length != 2) {
					return "Wrong input, Please try again";
				}
				
				if(!isVaildCommand(Command.slot_numbers_for_cars_with_colour, parkingManagement)) {
					return "No Such car parked";
				}

				List<Slot> slots = parkingManagement.getAllSlotsWithColor(commandArray[1]);
				if(slots == null || slots.size() == 0){
					return "Not found";
				}

				builder = new StringBuilder();
				i = 1;
				for(Slot slot : slots) {
					builder.append(slot.getId());
					if(i < slots.size()) {
						i++;
						builder.append(", ");
					}
				}
				return builder.toString();

			case slot_number_for_registration_number:
				
				if(commandArray.length != 2) {
					return "Wrong input, Please try again";
				}
				
				if(!isVaildCommand(Command.slot_number_for_registration_number, parkingManagement)) {
					return "No Such car parked";
				}

				Slot slot = parkingManagement.getCarSlotWithRegNo(commandArray[1]);
				if(slot == null) {
					return "Not found";
				}
				return slot.getId() + "";

			default:
				return "Invalid Input Please try Again";

			}
		} catch (IllegalArgumentException e) {
			return e.getMessage();
		} catch(Exception e) {
			return e.getMessage();
		}

	}
	
	private boolean isVaildCommand(Command command, ParkingManagement parkingManagement) {
		
		if(command == null) {
			return false;
		}
		
		if(!(command.equals(Command.create_parking_lot) && command.equals(Command.status)) && parkingManagement == null){
			return false;
		}
		return true;
		
	}

}
