package main;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int x = Integer.parseInt(st.nextToken());
		int y = Integer.parseInt(st.nextToken());
		HashMap<String, String> map = new HashMap<>();
		ArrayList<String> list = new ArrayList<>();
		int cnt=0;
		for(int i=0;i<x;i++) {
			String name = br.readLine();
			map.put(name, name);
		}
		for(int i=0;i<y;i++) {
			String str = br.readLine();
			if(map.get(str) != null) {
				cnt++;
				list.add(str);
			}
		}
		Collections.sort(list);
		System.out.println(cnt);
		for(int i=0;i<list.size();i++) {
			System.out.println(list.get(i));
		}
	}
}
