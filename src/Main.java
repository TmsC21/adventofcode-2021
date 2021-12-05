import java.io.*;
import java.util.*;

class LineSegment{
    Coordinates start;
    Coordinates end;
    LineSegment(Coordinates start, Coordinates end){
        this.start = start;
        this.end = end;
    }
    public void printCor(){
        System.out.println(start.x +","+end.x+" => "+start.y+","+end.y);
    }
}

class Coordinates{
    int x;
    int y;
    Coordinates(int x, int y){
        this.x = x;
        this.y = y;
    }
}
public class Main {
    private static int XLEN;
    private static int YLEN;
    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<String>();
        File file = new File("input.txt");
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            for (String line; (line = br.readLine()) != null; ) {
                if(!line.isEmpty()){
                    list.add(line);
                }
            }
        } catch (Exception ignored) {
        }
        ArrayList<LineSegment> linesSegmentList = getCoordinates(list);
        String[][] gameTable = getGameTable();
        for (int i = 0; i < linesSegmentList.size(); i++) {
            int XCOR =linesSegmentList.get(i).end.x - linesSegmentList.get(i).start.x;
            int YCOR =linesSegmentList.get(i).end.y - linesSegmentList.get(i).start.y;
                if(XCOR > 0 && YCOR > 0){
                    encreaseX(linesSegmentList.get(i).start.x,linesSegmentList.get(i).end.x,linesSegmentList.get(i).start.y,linesSegmentList.get(i).end.y,gameTable,true,true);
                }else if(XCOR > 0 && YCOR < 0){
                    encreaseX(linesSegmentList.get(i).start.x,linesSegmentList.get(i).end.x,linesSegmentList.get(i).start.y,linesSegmentList.get(i).end.y,gameTable,true,false);
                }else if(XCOR < 0 && YCOR > 0){
                    dencreaseX(linesSegmentList.get(i).start.x,linesSegmentList.get(i).end.x,linesSegmentList.get(i).start.y,linesSegmentList.get(i).end.y,gameTable,true,true);
                }else if(XCOR < 0 && YCOR < 0) {
                    dencreaseX(linesSegmentList.get(i).start.x,linesSegmentList.get(i).end.x,linesSegmentList.get(i).start.y,linesSegmentList.get(i).end.y,gameTable,true,false);
                }else if(XCOR > 0) {
                    encreaseX(linesSegmentList.get(i).start.x,linesSegmentList.get(i).end.x,linesSegmentList.get(i).start.y,linesSegmentList.get(i).end.y,gameTable,false,false);
                } else if(XCOR < 0) {
                    dencreaseX(linesSegmentList.get(i).start.x,linesSegmentList.get(i).end.x,linesSegmentList.get(i).start.y,linesSegmentList.get(i).end.y,gameTable,false,false);
                }else if(YCOR > 0) {
                    encreaseY(linesSegmentList.get(i).start.y,linesSegmentList.get(i).end.y,linesSegmentList.get(i).start.x,gameTable,false);
                }else if(YCOR < 0) {
                    dencreaseY(linesSegmentList.get(i).start.y,linesSegmentList.get(i).end.y,linesSegmentList.get(i).start.x,gameTable,false);
                }else{
                    encreaseY(XCOR,YCOR,YCOR,gameTable,false);
                }
        }
        int count = 0;
        for (int y = 0; y < YLEN; y++) {
            for (int x = 0; x < XLEN; x++) {
                if(!gameTable[y][x].equals(".")){
                    if(Integer.parseInt(gameTable[y][x]) > 1){
                        count++;
                    }
                }
            }
        }
        System.out.println(count);
    }
    static void encreaseX(int xstart, int xend, int ystart,int yend, String[][] gameTable, boolean isXY, boolean isEncreaseY){
        if(isXY){
            if(isEncreaseY){
                for (int x = xstart,y = ystart; x <= xend; x++,y++) {
                    encreaseY(y,yend,x,gameTable,true);
                }
            }else{
                for (int x = xstart,y = ystart; x <= xend; x++,y--) {
                    dencreaseY(y,yend,x,gameTable,true);//done
                }
            }
        }else{
            for (int x = xstart; x <= xend; x++) {
                gameTable[ystart][x] = chechNode(gameTable[ystart][x]);
            }
        }
    }
    static void dencreaseX(int xstart, int xend, int ystart,int yend, String[][] gameTable,boolean isXY, boolean isEncreaseY){
        if(isXY){
            if(isEncreaseY){
                for (int x = xstart,y = ystart; x >= xend; x--,y++) {
                    encreaseY(y,yend,x,gameTable,true);
                }
            }else{
                for (int x = xstart,y = ystart; x >= xend; x--,y--) {
                    dencreaseY(y,yend,x,gameTable,true);
                }
            }
        }else{
            for (int x = xstart; x >= xend; x--) {
                gameTable[ystart][x] = chechNode(gameTable[ystart][x]);
            }
        }
    }
    static void encreaseY(int ystart,int yend, int x,String[][] gameTable,boolean isXY){
        if(isXY){
            gameTable[ystart][x] = chechNode(gameTable[ystart][x]);
        }else{
            for (int y = ystart; y <= yend; y++) {
                gameTable[y][x] = chechNode(gameTable[y][x]);
            }
        }
    }
    static void dencreaseY(int ystart,int yend, int x,String[][] gameTable,boolean isXY){
        if(isXY){
            gameTable[ystart][x] = chechNode(gameTable[ystart][x]);
        }else{
            for (int y = ystart; y >= yend; y--) {
                gameTable[y][x] = chechNode(gameTable[y][x]);
            }
        }
    }

    static String chechNode(String str){
        if(str.equals(".")){
            return "1";
        }else{
            int num = Integer.parseInt(str);
            return String.valueOf(++num);
        }
    }
    static String[][] getGameTable(){
        String[][] table = new String[YLEN][XLEN];
        for (int y = 0; y < YLEN; y++) {
            for (int x = 0; x < XLEN; x++) {
                table[y][x] = ".";
            }
        }
        return table;
    }

    static ArrayList<LineSegment> getCoordinates(ArrayList<String> list){
        ArrayList<LineSegment> myList = new ArrayList<>();
        int biggestX = 0, biggestY = 0;
        for (String line: list) {
            String[] str = line.split(" -> ");
            String[] left = str[0].split(",");
            String[] right = str[1].split(",");
            myList.add(new LineSegment(new Coordinates(Integer.parseInt(left[0]),Integer.parseInt(left[1])),new Coordinates(Integer.parseInt(right[0]),Integer.parseInt(right[1]))));
            if(Integer.parseInt(left[0]) > biggestX){
                biggestX = Integer.parseInt(left[0]);
            }
            if(Integer.parseInt(right[0]) > biggestX){
                biggestX = Integer.parseInt(right[0]);
            }
            if(Integer.parseInt(left[1]) > biggestY){
                biggestY = Integer.parseInt(left[1]);
            }
            if(Integer.parseInt(right[1]) > biggestY){
                biggestY = Integer.parseInt(right[1]);
            }
        }
        XLEN = biggestX+1;
        YLEN = biggestY+1;
        return myList;
    }
}