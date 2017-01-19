package com.gojek.parkinglot;

import com.gojek.car.Car;

public class Slot {
	
	private int id;
	private Car car;
	
	public Slot(int id) {
		this.id = id;
	}
	
	public int getId() {
		return id;
	}

	public Car removeCar() {
		Car car = getCar();
		setCar(null);
		return car;
	}
	
	public Slot addCar(Car car) {
		if(car == null) {
			return new Slot(0);
		}
		Car existingCar = getCar();
		if(existingCar == null) {
			setCar(car);
			return this;
		}		
		return null;
	}

	
	public Car getCar() {
		return car;
	}
	public void setCar(Car car) {
		this.car = car;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Slot other = (Slot) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
	
	

}
