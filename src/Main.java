import java.io.*;
import java.util.*;

public class Main {
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

        LinkedList<Integer> listOfFishes = getNumberOfFishs(list);
        Map<Integer,Long> myMap = getMap(listOfFishes);
        int round = 0;
        while(round < 256){
            calculate(myMap);
            round++;
        }
        long count = myMap.values().stream().reduce((long)0,Long::sum);
        System.out.println(count);
    }
    static void calculate(Map<Integer,Long> myMap){
        long zeroIndex = myMap.get(0);
        for (int i = 0; i < myMap.size()-1; i++){
            myMap.put(i,myMap.get(i+1));
        }
        myMap.put(6,myMap.get(6)+zeroIndex);
        myMap.put(8,zeroIndex);
    }

    static Map<Integer,Long> getMap(LinkedList<Integer> listOfFishes){
        Map<Integer,Long> myMap = new HashMap<>();
        for (int i = 0; i < 9; i++){
            myMap.put(i,(long)0);
        }
        for (Integer num: listOfFishes){
            myMap.put(num,myMap.get(num)+1);
        }
        return myMap;
    }

    static LinkedList<Integer> getNumberOfFishs(ArrayList<String> list){
        LinkedList<Integer> myList = new LinkedList<>();
        String[] strArr = list.get(0).split(",");
        for (String str: strArr){
            myList.add(Integer.parseInt(str));
        }
        return myList;
    }
}