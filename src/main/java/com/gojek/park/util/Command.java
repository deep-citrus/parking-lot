package com.gojek.park.util;

public enum Command {
	
	create_parking_lot,
	park,
	leave,
	status,
	registration_numbers_for_cars_with_colour,
	slot_numbers_for_cars_with_colour,
	slot_number_for_registration_number;
	
	public static Command getCommand(String srtCommand) {
		for(Command command : Command.values()) {
			if(srtCommand.equalsIgnoreCase(command.name())) {
				return command;
			}
		}
		return null;
	}
	
}
