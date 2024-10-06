import java.util.Random;
import java.util.Scanner;
public class MineSweeper {
    static int rowNumber;
    static int colNumber;
    static String[][] map;
    static String[][] Usermap;

    MineSweeper(int rowNumber, int colNumber){
        if (rowNumber<2){
            System.out.println("Satır sayısı 2 den kücük olamaz");
        }
        else{
            this.rowNumber = rowNumber;
        }

        if (colNumber<2){
            System.out.println("Sütun sayısı 2 den kücük olamaz");
        }
        else{
            this.colNumber = colNumber;
        }
        map = new String[this.rowNumber][this.colNumber];
        Usermap = new String[this.rowNumber][this.colNumber];

        createMap();
        User();
    }

    static void run(){
        Scanner input = new Scanner(System.in);
        int satir,sutun,win_count=0;
        int win = (rowNumber*colNumber)-mineNumber();
        System.out.println("Mayınlı Tarla:");
        showMap(map);
        System.out.println("OYUNA HOŞGELDİNİZ");
        while (true) {
            showMap(Usermap);
            System.out.println("Satır sayısı giriniz");
            satir = input.nextInt();
            if(satir>rowNumber){
               System.out.println("Satır sayısı haritadan büyük olamaz");
               System.out.println("Satır sayısı giriniz");
               satir = input.nextInt();
            }

            System.out.println("Sütun sayısı giriniz");
            sutun = input.nextInt();
            if(sutun>rowNumber){
                System.out.println("Sütun sayısı haritadan büyük olamaz");
                System.out.println("Sütun sayısı giriniz");
                sutun = input.nextInt();
            }

            if (map[satir][sutun] == "*"){
                System.out.println("Oyunu kaybettiniz!");
                break;
            }
            else {
                int mineCount = countMines(satir, sutun);
                Usermap[satir][sutun] = Integer.toString(mineCount);
                win_count+=1;
                if (win==win_count){
                    System.out.println("OYUNU KAZANDINIZ");
                    break;
                }

            }
        }

    }

    static void showMap(String[][] mapName){
        for(int i=0; i<rowNumber; i++){
            for(int j=0; j<colNumber; j++){
                System.out.print(mapName[i][j] + " ");
            }
            System.out.println();
        }
    }

    static int createNumber(int limit){
        Random random = new Random();

        int randomNumber = random.nextInt(limit);
        return randomNumber;
    }

    static int mineNumber(){
        return (colNumber*rowNumber)/4;
    }

    static void createMine(){
       int mine = mineNumber();
       int count = 0;
       int a,b=0;
       while (count<mine){
           a = createNumber(rowNumber);
           b = createNumber(colNumber);
           if(map[a][b]==null) {
               map[a][b] = "*";
               count+=1;
           }
           else{
               continue;
           }
       }
    }

    static void createMap(){
        createMine();
        for(int i=0; i<rowNumber; i++){
            for(int j=0; j<colNumber; j++) {
                if (map[i][j] != "*") {
                     map[i][j] = "-";
                }
            }
        }

    }

    static void User(){
        for(int i=0; i<rowNumber; i++){
            for(int j=0; j<colNumber; j++) {
                 Usermap[i][j] = "-";
            }
        }

    }

    static int countMines(int row, int col) {
        int mineCount = 0;

        int[][] directions = {
                {-1, -1}, {-1, 0}, {-1, 1},
                {0, -1},        {0, 1},
                {1, -1}, {1, 0}, {1, 1}
        };

        for (int[] direction : directions) {
            int newRow = row + direction[0];
            int newCol = col + direction[1];

            if (newRow >= 0 && newRow < rowNumber && newCol >= 0 && newCol < colNumber) {
                if (map[newRow][newCol].equals("*")) {
                    mineCount++;
                }
            }
        }

        return mineCount;
    }

}
