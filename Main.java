package readability;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String filePath = args[0];
        //String filePath = "/Users/ann/Downloads/dataset_91069.txt";
        File file = new File(filePath); //init file object

        Readability readability = new Readability(filePath);
        readability.printData();
        readability.chooseAndCalcMethodOfScore();
    }
}
