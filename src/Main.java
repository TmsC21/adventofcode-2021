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
        System.out.println(getPwrConsum(list));
        System.out.println(getRating(new ArrayList<String>(list),true)*getRating(new ArrayList<String>(list),false));
    }
    static int getRating(ArrayList<String> list, boolean isOxygen){
        int one = 0, zero = 0, len = list.get(0).length();
        String str = "";
        for (int i = 0; i < len; i++) {
            Iterator<String> iter = list.iterator();
            for (int y = 0; y < list.size(); y++) {
                if (list.get(y).charAt(i) == '1') {
                    one++;
                } else {
                    zero++;
                }
                if (y == list.size() - 1) {
                    if(isOxygen){
                        if (one > zero) {
                            str = str + "1";
                        } else if (one == zero) {
                            str = str + "1";
                        } else {
                            str = str + "0";
                        }
                    }else{
                        if (one > zero) {
                            str = str + "0";
                        } else if (one == zero) {
                            str = str + "0";
                        } else {
                            str = str + "1";
                        }
                    }
                    one = 0;
                    zero = 0;
                }
            }
            if (str.charAt(i) == '1') {
                while (iter.hasNext()) {
                    if (iter.next().charAt(i) != '1') {
                        iter.remove();
                    }
                }
            } else {
                while (iter.hasNext()) {
                    if (iter.next().charAt(i) != '0') {
                        iter.remove();
                    }
                }
            }
            if(list.size() <= 1){
                break;
            }
        }
        return Integer.parseInt(list.get(0),2);
    }
    static int getPwrConsum(ArrayList<String> list){
        int one = 0, zero = 0, len = list.get(0).length();
        String gamma  = "";
        String epsilon = "";
        for (int i = 0; i < len; i++) {
            for (int y = 0; y < list.size(); y++) {
                if (list.get(y).charAt(i) == '1') {
                    one++;
                } else {
                    zero++;
                }
                if (y == list.size() - 1) {
                    if (one > zero) {
                        gamma  = gamma + "1";
                        epsilon = epsilon + "0";
                    } else {
                        gamma  = gamma + "0";
                        epsilon = epsilon + "1";
                    }
                    one = 0;
                    zero = 0;
                }
            }
        }
        return Integer.parseInt(gamma,2) * Integer.parseInt(epsilon,2);
    }
}