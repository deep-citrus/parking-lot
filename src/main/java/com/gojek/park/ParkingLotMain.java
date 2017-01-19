package com.gojek.park;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import com.gojek.process.ParkingProcessor;
import com.gojek.process.ParkingProcessorImpl;

public class ParkingLotMain {

	public static void main(String[] args) {

		ParkingProcessor processor = new ParkingProcessorImpl();
		Scanner input = null;
		BufferedReader br = null;
		try {
			if(args.length > 0) {
				String fileName = args[0];
				File file = new File(fileName);
				if(file.isDirectory() || !file.exists()) {
					System.out.println("Wrong Input.Exiting...");
					return;
				}
				String sCurrentLine;
				br = new BufferedReader(new FileReader(fileName));
				while ((sCurrentLine = br.readLine()) != null) {
					System.out.println(processor.process(sCurrentLine));
				}
				return;
			}
		
			input = new Scanner(System.in);
			
			System.out.println("Input :");
			while (input.hasNextLine()) {
				String commandInput = input.nextLine();
				System.out.println(processor.process(commandInput));
				System.out.println("Input :");
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(input != null)
				input.close();
			
			if(br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

	}

}
