package com.gojek.park.impl;

import org.junit.Assert;

import org.junit.Test;

import com.gojek.car.Car;
import com.gojek.parkinglot.ParkingLot;
import com.gojek.parkinglot.Slot;

public class ParkingManagementImplTest {

	@Test(expected=IllegalArgumentException.class)
	public void testParkingLot() {
		new ParkingLot(0);
	}

	
	@Test
	public void testPark() {
		new ParkingManagementImpl(new ParkingLot(1));
		Assert.assertTrue("Null value for car", new Slot(0).equals(new ParkingManagementImpl(new ParkingLot(1)).park(null)));
		Assert.assertTrue("Some value for car", new Slot(1).equals(new ParkingManagementImpl(new ParkingLot(1)).park(new Car("regNo", "Color"))));
	}

	@Test
	public void testUnpark() {
		ParkingManagementImpl parkingManagementImpl = new ParkingManagementImpl(new ParkingLot(1));
		parkingManagementImpl.park(new Car("UP23AA4532", "Red"));
		Assert.assertTrue("Unpark car test", new Slot(1).equals(parkingManagementImpl.unpark(1)));
		Assert.assertTrue("Unpark car without car", null == (parkingManagementImpl.unpark(1)));
	}
}
