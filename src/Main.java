import java.io.*;
import java.util.*;


public class Main {
    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<String>();
        File file = new File("input.txt");
        try(BufferedReader br = new BufferedReader(new FileReader(file))) {
            for(String line; (line = br.readLine()) != null; ) {
                list.add(line);
            }
        }catch (Exception ignored){}

        System.out.println(getDepth(list));
        System.out.println(getAim(list));
    }

    static int getDepth(ArrayList<String> list){
        int horizontal = 0, vertical = 0;
        for (int i = 0; i < list.size(); i++) {
            String[] str = list.get(i).split(" ");
            switch (str[0]){
                case "forward":
                    horizontal += Integer.parseInt(str[1]);
                    break;
                case "up":
                    vertical -= Integer.parseInt(str[1]);
                    break;
                case "down":
                    vertical += Integer.parseInt(str[1]);
                    break;
            }
        }
        return horizontal * vertical;
    }

    static int getAim(ArrayList<String> list){
        int horizontal = 0, vertical = 0, aim = 0;
        for (int i = 0; i < list.size(); i++) {
            String[] str = list.get(i).split(" ");
            switch (str[0]){
                case "forward":
                    horizontal += Integer.parseInt(str[1]);
                    vertical += aim * Integer.parseInt(str[1]);
                    break;
                case "up":
                    aim -= Integer.parseInt(str[1]);
                    break;
                case "down":
                    aim += Integer.parseInt(str[1]);
                    break;
            }
        }
        return horizontal * vertical;
    }
}