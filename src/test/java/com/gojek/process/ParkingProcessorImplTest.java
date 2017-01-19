package com.gojek.process;

import org.junit.Assert;
import org.junit.Test;

public class ParkingProcessorImplTest {

	@Test
	public void testProcess() {
		ParkingProcessorImpl parkingProcessor = new ParkingProcessorImpl();
	    
	    Assert.assertEquals("Wrong input, Please try again", parkingProcessor.process("create_parking_lot"));
		Assert.assertEquals("Wrong input, Please try again", parkingProcessor.process("create_parking_lot APSPS"));
		Assert.assertEquals("Invalid Slot Numbers, should not be less then 1, Please try again", parkingProcessor.process("create_parking_lot 0"));
		Assert.assertEquals("Created a parking lot with 2 slots", parkingProcessor.process("create_parking_lot 2"));
		Assert.assertEquals("Wrong input, Please try again", parkingProcessor.process("park"));
		Assert.assertEquals("Wrong input, Please try again", parkingProcessor.process("park 45242"));
		Assert.assertTrue(parkingProcessor.process("park AP45AA5643 Red").contains("Allocated slot number: "));
		Assert.assertTrue(parkingProcessor.process("park AP45AA5644 Red").contains("Allocated slot number: "));
		Assert.assertEquals("Sorry, parking lot is full", parkingProcessor.process("park AP45AA5643 Red"));
		Assert.assertEquals("Wrong input, Please try again", parkingProcessor.process("leave"));
		Assert.assertEquals("Slot number 1 is free", parkingProcessor.process("leave 1"));
		Assert.assertEquals("Wrong input, Please try again", parkingProcessor.process("registration_numbers_for_cars_with_colour"));
		Assert.assertEquals("AP45AA5644", parkingProcessor.process("registration_numbers_for_cars_with_colour Red"));
		Assert.assertEquals("Not found", parkingProcessor.process("registration_numbers_for_cars_with_colour Blue"));
		Assert.assertEquals("Wrong input, Please try again", parkingProcessor.process("slot_numbers_for_cars_with_colour"));
		Assert.assertEquals("Wrong input, Please try again", parkingProcessor.process("slot_number_for_registration_number"));
		Assert.assertEquals("Not found", parkingProcessor.process("slot_number_for_registration_number SDEW"));
		Assert.assertEquals("2", parkingProcessor.process("slot_number_for_registration_number AP45AA5644"));
	}

}
