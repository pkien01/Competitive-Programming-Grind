import java.util.*;
import java.io.*;

public class Main {
    
	public static void main (String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        int[] arr = new int[n];
        for(int i = 0; i < n; i++) {
            arr[i] = scan.nextInt();
        }
        
        Map<Integer, Integer> freq = new HashMap<>();
        Map<Integer, Set<String>> index = new HashMap<>();
        
        for(int i = 0; i < n; i++) {
            for(int j = i+1; j < n; j++) {
                int sum = arr[i] + arr[j];
                if(freq.containsKey(sum)) {
                    freq.put(sum, freq.get(sum) + 1);
                } else {
                    freq.put(sum, 1);
                }
                
                if(!index.containsKey(sum)) {
                    index.put(sum, new HashSet<String>());
                }    
                
                String stringIndex = i + " " + j;
                index.get(sum).add(stringIndex);
                
            }
        }
        
        
        for(Map.Entry<Integer, Integer> entry : freq.entrySet()) {
            int sum = entry.getKey();
            int frequency = entry.getValue();
            if(frequency >= 2) {
                Set<String> tempSet = index.get(sum);
                if(tempSet.size() > 1) {
                    
                    Iterator<String> iter = tempSet.iterator();
                    int i = 0;
                    Set<Integer> finalIndex = new HashSet<>();
                    while(iter.hasNext() && i < 2) {
                        String s = iter.next();
                        String[] temp = s.split(" ");
                        finalIndex.add(Integer.parseInt(temp[0]) + 1);
                        finalIndex.add(Integer.parseInt(temp[1]) + 1);
                        if(finalIndex.size() == 4) {
                            System.out.println("YES");
                            Iterator<Integer> it = finalIndex.iterator();
                            while(it.hasNext()) {
                                System.out.print(it.next() + " ");
                            }
                            return;
                        }
                        i++;
                    }
                }
            }
        }
        
        System.out.println("NO");
	}
}


