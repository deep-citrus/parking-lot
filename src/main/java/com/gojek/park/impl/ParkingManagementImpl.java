package com.gojek.park.impl;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

import com.gojek.car.Car;
import com.gojek.park.ParkingManagement;
import com.gojek.park.util.SlotComparator;
import com.gojek.parkinglot.ParkingLot;
import com.gojek.parkinglot.Slot;

public class ParkingManagementImpl implements ParkingManagement {
	
	final private ParkingLot parkingLot;
	final private Queue<Slot> parkingPQ;
	final private Map<String, List<Slot>> colorSlotMap;
	final private Map<String, Slot> regNoSlotMap;
	
	public ParkingManagementImpl(final ParkingLot parkingLot) {
		
		colorSlotMap = new HashMap<String, List<Slot>>();
		regNoSlotMap = new HashMap<String, Slot>();
		
		this.parkingLot = parkingLot;
		this.parkingPQ = new PriorityQueue<Slot>(parkingLot.getParkingSlots().size(), new SlotComparator());
		parkingPQ.addAll(parkingLot.getParkingSlots());
	}
	
	public Slot park(Car car) {
		
		if(car == null) {
			return new Slot(0);
		}
		
		String carRegNo = car.getRegistrationNumber();
		String carColor = car.getColor();
		
		Slot slot = addToParkingLot(car);
		if(slot == null) {
			return null;
		}
		if(slot.getId() != 0) {
			addToColorSlotMap(carColor, slot);		
			addToRegNoSlotMap(carRegNo, slot);
		}

		return slot ;
	}
	
	public Slot unpark(int slotNumber) {
		
		Slot slot = removeFromParkingLot(slotNumber);
		if(slot == null) {
			return null;
		}
		removeFromRegNoSlotMap(slot.getCar().getRegistrationNumber());		
		removeFromColorSlotMap(slot);
		slot.removeCar();
		parkingPQ.offer(parkingLot.getParkingSlots().get(slotNumber - 1));
		return slot;
	}
	
	public List<Slot> status() {
		return parkingLot.getParkingSlots();
	}
	
	
	
	public List<String> getAllCarsInColor(String color) {
		if(!colorSlotMap.containsKey(color)) {
			return null;
		}
		
		List<String> regNoOfGivenColor = new LinkedList<String>();
		
		List<Slot> slotsWithGivenColor = colorSlotMap.get(color);
		if(slotsWithGivenColor == null || slotsWithGivenColor.isEmpty()) {
			return null;
		}
		
		for(Slot slot : slotsWithGivenColor) {
			regNoOfGivenColor.add(slot.getCar().getRegistrationNumber());
		}
		return regNoOfGivenColor;
	}
	
	public Slot getCarSlotWithRegNo(String regNo) {
		if(!regNoSlotMap.containsKey(regNo)) {
			return null;
		}
		return regNoSlotMap.get(regNo);
	}
	
	public List<Slot> getAllSlotsWithColor(String color) {
		if(!colorSlotMap.containsKey(color)) {
			return null;
		}
		return colorSlotMap.get(color);
	}
	
	private Slot addToParkingLot(Car car) {
		
		if(car == null) {
			return new Slot(0);
		}
		if(parkingPQ.size() == 0) {
			return null;
		}
		return parkingPQ.poll().addCar(car);
	}
	
	private Slot removeFromParkingLot(int slotNumber) {
		
		if(slotNumber <= parkingLot.getParkingSlots().size()) {
			Slot slot = parkingLot.getParkingSlots().get(slotNumber - 1);
			Car car = slot.getCar();
			if(car == null) {
				return null;
			}
			
			return slot;
		}
		return null;
	}
	
	private void addToColorSlotMap(String color, Slot slot) {
		
		if(color == null || slot == null) {
			return;
		} 
		
		if(!colorSlotMap.containsKey(color)) {
			List<Slot> slots = new LinkedList<Slot>();
			slots.add(slot);
			colorSlotMap.put(color, slots);
			return;
		}
		colorSlotMap.get(color).add(slot);
		return;

	}
	
	private void removeFromColorSlotMap(Slot slot) {
		
		String color = slot.getCar().getColor();
		
		Iterator<Slot> itr = colorSlotMap.get(color).iterator();
		while(itr.hasNext()) {
			if(itr.next().getId() == slot.getId()) {
				itr.remove();
				break;
			}
		}

	}
	
	private boolean addToRegNoSlotMap(String regNo, Slot slot) {
		
		if(regNoSlotMap.containsKey(regNo)) {
			return false;
		}
		regNoSlotMap.put(regNo, slot);
		return true;
	}
	
	private Slot removeFromRegNoSlotMap(String registrationNumber) {
		if(!regNoSlotMap.containsKey(registrationNumber)) {
			return null;
		}
		return regNoSlotMap.remove(registrationNumber);
	}
	
	public ParkingLot getParkingLot() {
		return parkingLot;
	}
	
	public Queue<Slot> getParkingPQ() {
		return parkingPQ;
	}

	public Map<String, List<Slot>> getColorSlotMap() {
		return colorSlotMap;
	}

	public Map<String, Slot> getRegNoSlotMap() {
		return regNoSlotMap;
	}	
}
