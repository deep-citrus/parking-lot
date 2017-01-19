package com.gojek.park.util;

import java.util.Comparator;

import com.gojek.parkinglot.Slot;

public class SlotComparator implements Comparator<Slot> {

	public int compare(Slot slot1, Slot slot2) {
		return slot1.getId() - slot2.getId();
	}
}
