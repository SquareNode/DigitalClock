import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.IntStream;

class DigitalClock{

    private String digits;
    private char[][] art;


    DigitalClock(String s){
        this.digits = s;
        art = new char[5][20];
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 20; j++) {
                art[i][j]=' ';
            }
        }
        fill_digits(s);
    }

    void fill_digit(int digit, int pos){

        if (digit == 2 || digit == 3 || digit == 0 || digit == 9 || digit == 8
                || digit==5 || digit==6) {
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 5; j++) {
                    art[2*i][j+pos*5]='#';
                }

            }
            art[1][pos*5]='#';
            art[1][pos*5+4]='#';
            art[3][pos*5]='#';
            art[3][pos*5+4]='#';
            if(digit==8)
                return;
            if(digit == 9){
                art[3][pos*5]=' ';
                for (int i = 0; i < 4; i++) {
                    art[4][pos*5+i] = ' ';
                }
            }
            if(digit == 0){
                art[2][pos*5+1] = ' ';
                art[2][pos*5+2] = ' ';
                art[2][pos*5+3] = ' ';
            }
            if(digit==3) {
                art[1][pos*5]=' ';
                art[3][pos*5]=' ';
            }
            if(digit==2){
                art[1][pos*5]=' ';
                art[3][pos*5+4]=' ';
            }
            if(digit==5){
                art[1][pos*5+4]=' ';
                art[3][pos*5]=' ';
            }
            if(digit==6)
                art[1][pos*5+4]=' ';
        }
        if(digit==1){
            for (int i = 0; i < 5; i++) {
                art[i][pos*5+4] = '#';
            }
        }
        if(digit==4){
            for (int i = 0; i < 5; i++) {
                art[i][pos*5+4] = '#';
            }
            for (int i = 0; i < 4; i++) {
                art[2][pos*5+i] = '#';
            }

            art[0][pos*5] = '#';
            art[1][pos*5] = '#';

        }
        if(digit==7){
            for (int i = 0; i < 5; i++) {
                art[i][pos*5+4] = '#';
            }
            for (int i = 0; i < 4; i++) {
                art[0][pos*5+i] = '#';
            }
        }

    }

    void fill_digits(String s){
        int [] arr = new int[4];
        if(s.length() != 5 || s.charAt(2) != ':') {
            System.out.println("Invalid input!");
            throw new IllegalArgumentException();
        }
        s = s.substring(0,2) + s.substring(3);
        for (int i = 0; i < 4; i++) {

            arr[i] = Integer.parseInt(String.valueOf(s.charAt(i)));

        }
        for (int i = 0; i < 4; i++) {
            fill_digit(arr[i], i);
        }
    }
    @Override
    public String toString() {

        char[][] art_primm = new char[5][29];

        //first digit
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                art_primm[i][j] = art[i][j];
            }
        }

        //sep
        for (int i = 0; i < 5; i++) {
            art_primm[i][5] = ' ';
        }

        //second digit
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                art_primm[i][j+6] = art[i][j+5];
            }
        }

        //sep
        for (int i = 0; i < 5; i++) {
            art_primm[i][11] = ' ';
        }
        //:
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                art_primm[i][j+12] = ' ';
            }
        }
        art_primm[1][14] = '#';
        art_primm[3][14] = '#';

        //sep
        for (int i = 0; i < 5; i++) {
            art_primm[i][17] = ' ';
        }

        //third digit

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                art_primm[i][j+18] = art[i][j+10];
            }
        }

        //sep
        for (int i = 0; i < 5; i++) {
            art_primm[i][23] = ' ';
        }
        //fourth digit

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                art_primm[i][j+24] = art[i][j+15];
            }
        }


        String res="";
        for(var x : art_primm) {
            for (var y : x)
                res += y;
            res += '\n';
        }
        //trim last new line
        res=res.substring(0, res.length()-1);

        return res;
    }
    public static void main(String[] args) throws IOException {
        InputStreamReader reader = new InputStreamReader(System.in, StandardCharsets.UTF_8);
        BufferedReader in = new BufferedReader(reader);
        String line;
        while ((line = in.readLine()) != null) {
            try{
                createClock(line);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    public static void createClock(String input) {

        DigitalClock c = new DigitalClock(input);
        System.out.println(c);
    }
}
