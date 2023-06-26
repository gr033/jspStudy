package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int y = Integer.parseInt(st.nextToken());
		int[] num = new int[n+1];
		num[0] = 0;
		StringTokenizer st1 = new StringTokenizer(br.readLine());
		for(int i=1;i<num.length;i++) {
			num[i] = num[i-1] + Integer.parseInt(st1.nextToken());
		}
		StringBuffer sb = new StringBuffer();
		for(int i=0;i<y;i++) {
			StringTokenizer st2 = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st2.nextToken());
			int e = Integer.parseInt(st2.nextToken());
			sb.append(num[e]-num[s-1]+"\n");
		}
		System.out.println(sb);
	}
}
