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
		String[] nums = br.readLine().split(" ");
		int[] sum = new int[y];
		for(int i=0;i<y;i++) {
			StringTokenizer str = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(str.nextToken());
			int end = Integer.parseInt(str.nextToken());
			for(int j=start-1;j<end;j++) {
				sum[i] += Integer.parseInt(nums[j]);
			}
		}
		for(int i=0;i<sum.length;i++) {
			System.out.println(sum[i]);
		}
		
	}
}