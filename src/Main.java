import java.io.*;
import java.util.*;


public class Main {
    private static final int MAXLEN = 5;
    private static int BOARDLEN;
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
        ArrayList<Integer> listDrawNumbers = getListOfDrawNumbers(list.get(0));
        ArrayList<Integer[][]> listOfBoards = getListOfBoards(getListOfBoardNumbers(list),false);
        ArrayList<Integer[][]> listOfPlayBoards = getListOfBoards(getListOfBoardNumbers(list),true);
        BOARDLEN = listOfBoards.size();
        int y = 0,x = 5;
        ArrayList<Integer> drawNumbers = new ArrayList<Integer>();
        for (int i = 0; i < listDrawNumbers.size(); i++,y++) {
            if(y == x){
                checkBoards(listOfBoards,listOfPlayBoards,drawNumbers);
                x++;
                y = 0;
                drawNumbers = new ArrayList<Integer>();
            }
            drawNumbers.add(listDrawNumbers.get(i));
        }
    }
    static void checkBoards(ArrayList<Integer[][]> boards,ArrayList<Integer[][]> playableBoards ,ArrayList<Integer> drawNumbers){
        int sum = 0;
        for (int i = 0; i < drawNumbers.size(); i++) {
            Iterator<Integer[][]> iterator = boards.iterator();
            int j = 0;
            while(iterator.hasNext()){
                Integer[][] board = iterator.next();
                for (int k = 0; k < MAXLEN; k++) {
                    for (int l = 0; l < MAXLEN; l++) {
                        if(board[k][l].equals(drawNumbers.get(i))){
                            playableBoards.get(j)[k][l] = drawNumbers.get(i);
                            if((sum = checkForWinningBoard(board,playableBoards.get(j),drawNumbers.get(i))) > 0){
                                if(boards.size() == BOARDLEN || boards.size() == 1){
                                    System.out.println("Result: "+sum);
                                }
                                iterator.remove();
                                playableBoards.remove(playableBoards.get(j));
                                j--;
                            }
                        }
                    }
                }
                j++;
            }
        }
    }
    static int checkForWinningBoard(Integer[][] board,Integer[][] playableBoards, int drawNumber){
        int horizontal = 0, vertical = 0;
        for (int j = 0; j < MAXLEN; j++) {
            for (int k = 0; k < MAXLEN; k++) {
                if (!playableBoards[j][k].equals(-1)) {
                    vertical++;
                }
                if (vertical == 5) {
                    return calculateWinner(playableBoards, board, drawNumber);
                }
                if (!playableBoards[k][j].equals(-1)) {
                    horizontal++;
                }
                if (horizontal == 5) {
                    return calculateWinner(playableBoards, board, drawNumber);
                }
            }
            vertical = 0;
            horizontal = 0;
        }
        return -1;
    }
    static int calculateWinner(Integer[][] winningBoard,Integer[][] board,int drawNumber){
        int sum = 0;
        for (int k = 0; k < MAXLEN; k++) {
            for (int l = 0; l < MAXLEN; l++) {
                if(!board[k][l].equals(winningBoard[k][l])){
                    sum += board[k][l];
                }
            }
        }
        return sum * drawNumber;
    }

    static ArrayList<Integer> getListOfDrawNumbers(String str){
        ArrayList<Integer> list = new ArrayList<Integer>();
        String[] arrStr = str.split(",");
        for (String s : arrStr){
            list.add(Integer.parseInt(s));
        }
        return list;
    }

    static ArrayList<Integer> getListOfBoardNumbers(ArrayList<String> list){
        ArrayList<Integer> listBoardNumber = new ArrayList<Integer>();
        for (int i = 1; i < list.size(); i++) {
            String[] arrStr = list.get(i).split(" ");
            for (String s : arrStr){
                if(!s.isEmpty()){
                    listBoardNumber.add(Integer.parseInt(s));
                }
            }
        }
        return listBoardNumber;
    }

    static ArrayList<Integer[][]> getListOfBoards(ArrayList<Integer> list,boolean isEmpty){
        ArrayList<Integer[][]> listOfBoards = new ArrayList<>();
        int y = 0;
        Integer[][] board = new Integer[MAXLEN][MAXLEN];
        for (int i = 0; i < list.size(); i++) {
            if(i%5 == 0 && i != 0){
                y++;
            }
            if(y == 5){
                listOfBoards.add(board);
                y = 0;
                board = new Integer[MAXLEN][MAXLEN];
            }
            if(isEmpty) {
                board[y][i%5] = -1;
            }else{
                board[y][i%5] = list.get(i);
            }
            if(i == list.size() - 1){
                listOfBoards.add(board);
            }
        }
        return listOfBoards;
    }
}