package com.application.cache;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Application {

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Enter capacity");
		int capacity = 0;
		capacity = Integer.parseInt(br.readLine());
		if(capacity>=1 && capacity<=1000)
		{
			LRUCache<Integer> cache = new LRUCache<>(capacity);
			try {
				int choice = 1;
				while(choice != 0) {
					System.out.println("1: Put\n2: Get\n3: Delete\n0: Exit\n");
					choice = Integer.parseInt(br.readLine());
					int key;
					int value;
					switch (choice) {
					case 1:
						System.out.println("Enter key");
						key = Integer.parseInt(br.readLine());
						System.out.println("Enter Value");
						value = Integer.parseInt(br.readLine());
						if((key>=0 && key <=1000) && (value>=0 && value<=100000))
						{
						cache.put(key, value);
						System.out.println("Inserted\n");
						}
						break;
						
					case 2:
						System.out.println("Enter key");
						key = Integer.parseInt(br.readLine());
						System.out.println("Value is " + cache.get(key) + "\n");
						break;
						
					case 3:
						System.out.println("Enter key");
						key = Integer.parseInt(br.readLine());
						cache.delete(key);
						break;
						
					default:
						System.out.println("Default selection is made!");
					}
				}
			}
			catch(Exception e)
			{
				e.printStackTrace();
				System.out.println("Enter a valid selection");
			}
		}
		
	}

}
