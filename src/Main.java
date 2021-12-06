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
        } catch (Exception ignored) {
        }
        LinkedList<Integer> listOfFishes = getNumberOfFishs(list);
        int rounds = 0, count = 0;
        System.out.println(listOfFishes);
        for (Integer number: listOfFishes){
           count = calculate(rounds,count,number);
        }
        System.out.println("");
        System.out.println(count);

    }
    static Integer calculate(int round, int count, Integer number){
        while(round != 256){
            round++;
            if(number == 0){
                number = 7;
                count = calculate(round,count,8);
            }
            number--;
        }
        count++;
        return count;
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