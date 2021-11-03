package CS285_Project_219110021;

import java.io.File;
import java.io.FileWriter;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        Scanner input = new Scanner(System.in);
        System.out.println("Please enter the first prime number: ");
        int p1 = input.nextInt();
        System.out.println("Please enter the second prime number: ");
        int p2 = input.nextInt();

        System.out.println("The encrypted message is: ");
        System.out.println(encryptString(p1,p2));
        System.out.println("The decrypted message is: ");
        System.out.println(decryptString(p1,p2));
    }

    public static String encryptString(int p1, int p2) throws Exception {
        FileWriter outFile = new FileWriter("EncryptFile.txt");
        File inFile = new File("InputFile.txt");
        Scanner inScan = new Scanner(inFile);
        String values = "";
        while(inScan.hasNextLine()){
            values = values.concat(inScan.nextLine() + "\r");
        }

        int i = 0;
        char res;
        String finalRes = "";

        while(i < values.length()){
            int k = values.charAt(i);
            res = (char) ((k * p1 + p2) % 128);

            finalRes = finalRes.concat(Character.toString(res));
            i++;
        }
        outFile.write(finalRes);
        outFile.close();
        return finalRes;
    }

    public static String decryptString(int p1, int p2) throws Exception {
        FileWriter outFile = new FileWriter("OutputFile.txt");
        File inFile = new File("EncryptFile.txt");
        Scanner inScan = new Scanner(inFile);
        String values = "";
        while(inScan.hasNextLine()){
            values = values.concat(inScan.nextLine() + "\r");
        }
        values = values.substring(0, values.length() - 1);

        int i = 0;
        String finalRes = "";

        while(i < values.length()){
            int res = -1;
            for (int alpha = 0; alpha < 128; alpha++){
                int k = values.charAt(i);
                res = ((k - ((p1 * alpha) + p2)) % 128);
                if(res == 0){
                    finalRes = finalRes.concat(String.valueOf((char) alpha));
                }
            }
            i++;
        }
        outFile.write(finalRes);
        outFile.close();
        return finalRes;
    }
}
