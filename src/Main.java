import java.io.*;
import java.util.*;

public class Main {
    public static int MAXPOSITION = 2000;
    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<String>();
        File file = new File("input.txt");
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            for (String line; (line = br.readLine()) != null; ) {
                if (!line.isEmpty()) {
                    list.add(line);
                }
            }
        } catch (Exception ignored) {}
        LinkedList<Integer> horizontalPositionsList = getHorizPosit(list);
        System.out.println("result: "+getResultsList(horizontalPositionsList));
    }

    static int getResultsList(LinkedList<Integer> horizontalPositionsList) {
        int sum, result = MAXPOSITION;
        for (int i = 1; i < MAXPOSITION; i++) {
            sum = 0;
            for(Integer number: horizontalPositionsList){
                int num = Math.abs(number-i);
                sum += (num+1) * num/2;
            }
            if(sum < result){
                result = sum;
            }
        }
       return result;
    }

    static LinkedList<Integer> getHorizPosit(ArrayList<String> list){
        LinkedList<Integer> myList = new LinkedList<>();
        String[] strArr = list.get(0).split(",");
        for (String str: strArr){
            myList.add(Integer.parseInt(str));
        }
        return myList;
    }
}