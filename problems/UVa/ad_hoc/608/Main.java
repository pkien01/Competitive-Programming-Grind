/*input
2
ABCD EFGH even
ABCI EFJK up
ABIJ EFGH even
ABC FGJ down
DC HG down
ABIHG JKEDF up
*/
import java.util.*;
import java.io.*;

class Main {
    void runFromFile() {
        final String IODir = "D:/Kien/competitive_programming";
        final File inputFile = new File(IODir + "/input.txt");
        final File outputFile = new File(IODir + "/output.txt");
        try {
            System.setIn(new FileInputStream(inputFile));
            System.setOut(new PrintStream(outputFile));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    boolean sameWeight(String s1, String s2, char fake) {
    	int pos1 = s1.indexOf(fake), pos2 = s2.indexOf(fake);
    	if (pos1 < 0 && pos2 < 0) return true;
    	if (pos1 >= 0 && pos2 >= 0) return true;
    	return false;
    }
    String solve(String[] lines) {
    	for (int i = 0; i < 12; i++) {
    		char curFake = (char)(i + 'A');
    		boolean ok = true;
    		int weightCmp = 0;
    		for (String line: lines) {
    			String[] line_split = line.split(" ");
    			if (line_split[2].equals("even")) {
    				if (!sameWeight(line_split[0], line_split[1], curFake)) {
    					ok = false;
    					break;
    				}
    			}
    			else if (line_split[2].equals("up")) {
    				if (sameWeight(line_split[0], line_split[1], curFake)) {
    					ok = false;
    					break;
    				}
    				if (line_split[0].indexOf(curFake) >= 0) {
    					if (weightCmp == 0) weightCmp = 1;
    					if (weightCmp != 1) {
    						ok = false;
    						break;
    					}
    				}
    				else if (line_split[1].indexOf(curFake) >= 0) {
    					if (weightCmp == 0) weightCmp = -1;
    					if (weightCmp != -1) {
    						ok = false;
    						break;
    					}
    				}
    			}
    			else if (line_split[2].equals("down")) {
    				if (sameWeight(line_split[0], line_split[1], curFake)) {
    					ok = false;
    					break;
    				}
    				if (line_split[0].indexOf(curFake) >= 0) {
    					if (weightCmp == 0) weightCmp = -1;
    					if (weightCmp != -1) {
    						ok = false;
    						break;
    					}
    				}
    				else if (line_split[1].indexOf(curFake) >= 0) {
    					if (weightCmp == 0) weightCmp = 1;
    					if (weightCmp != 1) {
    						ok = false;
    						break;
    					}
    				}
    			}
    		}
    		if (ok && weightCmp != 0) return curFake + " is the counterfeit coin and it is " + (weightCmp < 0? "light.":"heavy.");
    	}
    	return "Not found";
    }
    public static void main(String[] args) {
        Main obj = new Main();
        //obj.runFromFile();
        Scanner inp = new Scanner(System.in);
        int numTests = inp.nextInt(); inp.nextLine();
        while (numTests-- > 0) {
        	String[] lines = new String[3];
        	for (int i = 0; i < 3; i++) lines[i] = inp.nextLine();
        	System.out.println(obj.solve(lines));
        }
    }
}