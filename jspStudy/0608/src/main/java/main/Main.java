package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	int n = Integer.parseInt(br.readLine());
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	HashMap<Integer, Integer> map = new HashMap<>();
    	int x = 0;
    	while(st.hasMoreTokens()) {
    		map.put(Integer.parseInt(st.nextToken()), x);
    		x++;
    	}
    	ArrayList<Integer> keylist = new ArrayList<>(map.keySet());
    	keylist.sort((s1, s2)->s1.compareTo(s2));
    	for(int key : keylist) {
    		System.out.print(key +" ");
    	}
    }
}
