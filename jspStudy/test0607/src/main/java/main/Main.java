package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int x = Integer.parseInt(br.readLine());
		int y = Integer.parseInt(br.readLine());
		int sum = 0;
		int cnt = 0;
		int min = y;
		for(int i=x;i<=y;i++) {
			int cal = 0;
			for(int j=1;j<=i;j++) {
				if(i%j==0) {
					cal ++;
				}
			}
			if(cal==2) {
				cnt ++;
				sum += i;
				if(min>i) {
					min = i;
				}
			}
		}
		if(cnt ==0) {
			System.out.println(-1);
		}else {
			System.out.println(sum);
			System.out.println(min);
		}
	}
}
