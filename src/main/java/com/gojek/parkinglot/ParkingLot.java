package com.gojek.parkinglot;

import java.util.ArrayList;
import java.util.List;

public class ParkingLot {
	
		private List<Slot> parkingSlots;
		
		public ParkingLot(int size) {
			if(size < 1) {
				throw new IllegalArgumentException();
			}
			this.parkingSlots = new ArrayList<Slot>(size);
			for(int i = 0; i < size; i++) {
				Slot slot = new Slot(i+1);
				parkingSlots.add(slot);
			}
		}

		public List<Slot> getParkingSlots() {
			return parkingSlots;
		}

		public void setParkingSlots(List<Slot> parkingSlots) {
			this.parkingSlots = parkingSlots;
		}
		
}
