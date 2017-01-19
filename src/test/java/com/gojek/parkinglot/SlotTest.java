package com.gojek.parkinglot;

import org.junit.Test;

import com.gojek.car.Car;

import org.junit.Assert;

public class SlotTest {

	@Test
	public void testRemoveCar() {
		Slot slot = new Slot(1);
		Car car = new Car("12345", "Red");
		slot.setCar(car);
		Assert.assertEquals("Remove Car Test", car, slot.removeCar());
		Assert.assertEquals("Remove Car Test", null, slot.getCar());
	}

	@Test
	public void testAddCar() {
		Slot slot = new Slot(1);
		Car car = new Car("12345", "Red");
		Assert.assertEquals("Add Car Test", slot, slot.addCar(car));
		Assert.assertTrue("Add Null Car Test", new Slot(0).equals(slot.addCar(null)));

	}

}
