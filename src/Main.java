import java.io.*;
import java.util.*;


public class Main {
    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<Integer>();
        File file = new File("input.txt");
        try(BufferedReader br = new BufferedReader(new FileReader(file))) {
            for(String line; (line = br.readLine()) != null; ) {
                list.add(Integer.parseInt(line));
            }
        }catch (Exception ignored){}

        System.out.println(getMeasurement(list));
        System.out.println(getTreeMeasurement(list));


    }
    static int getMeasurement(ArrayList<Integer> list){
        int count = 0, prevNumber = list.get(0);
        for (int i = 1 ; i < list.size(); i++){
            if(prevNumber < list.get(i)){
                count++;
            }
            prevNumber = list.get(i);
        }
        return count;
    }
    static int getTreeMeasurement(ArrayList<Integer> list){
        int count = 0, prevNumber = list.get(0) + list.get(1) + list.get(2), currNumber = 0;
        Queue<Integer> queue = new LinkedList<Integer>();
        queue.add(list.get(0));
        queue.add(list.get(1));
        queue.add(list.get(2));
        for (int i = 3 ; i < list.size(); i++){
            currNumber = 0;
            queue.remove();
            queue.add(list.get(i));
            for (int num : queue){
                currNumber += num;
            }
            if(currNumber > prevNumber){
                count++;
            }
            prevNumber = currNumber;
        }
        return count;
    }
}