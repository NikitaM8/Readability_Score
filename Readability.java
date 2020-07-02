package readability;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Readability {
    private int wordCount = 0; //word counter
    private int sentenceCount = 0; //sentence counter
    private int characterCount = 0; //character counter
    private int syllables = 0;
    private int polysyllables = 0;
    private double score = 0; //score of text
    private String filePath;

    //regexp for checking whether it word last in sentence or not
    private final String regExp = "\\w+[!.?]";

    public Readability(String args) {
        filePath = args;
        processText();
    }

    private void processText() {
        //String filePath = "/Users/ann/Downloads/dataset_91069.txt";
        File file = new File(this.filePath); //init file object

        //try to read file
        try {
            Scanner scanner = new Scanner(file);

            System.out.println("The text is:");

            while (scanner.hasNext()) {
                String inputLine = scanner.nextLine(); //read next line

                System.out.println(inputLine); //output current line

                String[] words = inputLine.split("\\s");

                //process current line of text
                for (int i = 0; i < words.length; i++) {
                    this.wordCount++; //word increment

                    this.characterCount += words[i].length(); //char increment

                    countSyllables(words[i]); //count syllables in exact word

                    if (words[i].matches(regExp) && i != words.length - 1) {
                        this.sentenceCount++;
                    }
                }

                this.sentenceCount++;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void printData() {
        //output our data
        System.out.println();
        System.out.println("Words: " + this.wordCount);
        System.out.println("Sentences: " + this.sentenceCount);
        System.out.println("Characters: " + this.characterCount);
        System.out.println("Syllables: " + this.syllables);
        System.out.println("Polysyllables: " + this.polysyllables);
        System.out.println();

    }

    /**
     *
     * @param s - Text Score Index
     * @return - actual minimum age for reader
     */
    private int forWho(double s) {
        int actualAge = 0;

        switch ((int) s + 1) {
            case 1:
                System.out.println("about 5 year olds.");
                actualAge = 5;
                break;
            case 2:
                System.out.println("about 6 year olds.");
                actualAge = 6;
                break;
            case 3:
                System.out.println("about 7 year olds.");
                actualAge = 7;
                break;
            case 4:
                System.out.println("about 9 year olds.");
                actualAge = 8;
                break;
            case 5:
                System.out.println("about 10 year olds.");
                actualAge = 10;
                break;
            case 6:
                System.out.println("about 11 year olds.");
                actualAge = 11;
                break;
            case 7:
                System.out.println("about 12 year olds.");
                actualAge = 12;
                break;
            case 8:
                System.out.println("about 13 year olds.");
                actualAge = 13;
                break;
            case 9:
                System.out.println("about 14 year olds.");
                actualAge = 14;
                break;
            case 10:
                System.out.println("about 15 year olds.");
                actualAge = 15;
                break;
            case 11:
                System.out.println("about 16 year olds.");
                actualAge = 16;
                break;
            case 12:
                System.out.println("about 17 year olds.");
                actualAge = 17;
                break;
            case 13:
                System.out.println("about 18 year olds.");
                actualAge = 18;
                break;
            default:
                System.out.println("about 24 year olds.");
                actualAge = 24;
                break;
        }

        return actualAge;
    }

    /**
     *
     * @param w - word
     */
    private void countSyllables(String w) {
        int syllablesInWord = 0;

        String regExp = "\\w+[!.?,]";

        if (w.matches(regExp)) {
            w = w.substring(0, w.length() - 1);
        }

        w = w.toLowerCase(); //cast chars of word to lower case

        //check each char (apart of last) for not double vowel
        for (int i = 0; i < w.length() - 1; i++) {
            if ((w.charAt(i) == 'a' || w.charAt(i) == 'e' || w.charAt(i) == 'i'
                    || w.charAt(i) == 'o' || w.charAt(i) == 'u' || w.charAt(i) == 'y')
                    && (w.charAt(i + 1) != 'a' && w.charAt(i + 1) != 'e' && w.charAt(i + 1) != 'i'
                    && w.charAt(i + 1) != 'o' && w.charAt(i + 1) != 'u' && w.charAt(i + 1) != 'y')) {
                syllablesInWord++;
            }
        }

        int lastIndex = w.length() - 1;

        //if last char is vowel and not e, then we have additional vowel
        if (w.charAt(lastIndex) == 'a' || w.charAt(lastIndex) == 'i'
                || w.charAt(lastIndex) == 'o' || w.charAt(lastIndex) == 'u' || w.charAt(lastIndex) == 'y') {
            syllablesInWord++;
        } /*else {
            if (w.charAt(w.length() - 2) == 'l' && w.charAt(lastIndex) == 'e') {
                syllablesInWord++;
            }
        }*/

        //if we don't find any syllables in word, then assume that it have 1 syllable
        if (syllablesInWord == 0) {
            syllablesInWord++;
        }

        //System.out.println(w + " " + syllablesInWord);

        //plus syllables of this word to all text syllables
        this.syllables += syllablesInWord;

        //check for polysyllables
        if (syllablesInWord > 2) {
            this.polysyllables++;
        }
    }

    public void chooseAndCalcMethodOfScore() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the score you want to calculate (ARI, FK, SMOG, CL, all):");

        String command = scanner.next();
        int ageARI = 0;
        int ageFK = 0;
        int ageSMOG = 0;
        int ageCL = 0;

        switch (command) {
            case "ARI":
                System.out.print("Automated Readability Index: ");
                double ari = ARI();
                System.out.printf("%.2f", ari);
                System.out.print(" ");
                forWho(ari);
                break;
            case "FK":
                System.out.print("Flesch–Kincaid readability tests: ");
                double fk = FK();
                System.out.printf("%.2f", fk);
                System.out.print(" ");
                forWho(fk);
                break;
            case "SMOG":
                System.out.print("Simple Measure of Gobbledygook: ");
                double smog = SMOG();
                System.out.printf("%.2f", smog);
                System.out.print(" ");
                forWho(smog);
                break;
            case "CL":
                System.out.print("Coleman–Liau index: ");
                double cl = CL();
                System.out.printf("%.2f", cl);
                System.out.print(" ");
                forWho(cl);
                break;
            case "all":
                System.out.print("Automated Readability Index: ");
                ari = ARI();
                System.out.printf("%.2f", ari);
                System.out.print(" ");
                ageARI = forWho(ari);

                System.out.print("Flesch–Kincaid readability tests: ");
                fk = FK();
                System.out.printf("%.2f", fk);
                System.out.print(" ");
                ageFK = forWho(fk);

                System.out.print("Simple Measure of Gobbledygook: ");
                smog = SMOG();
                System.out.printf("%.2f", smog);
                System.out.print(" ");
                ageSMOG = forWho(smog);

                System.out.print("Coleman–Liau index: ");
                cl = CL();
                System.out.printf("%.2f", cl);
                System.out.print(" ");
                ageCL = forWho(cl);

                System.out.println();
                double avg = ((double) ageARI + ageFK + ageSMOG + ageCL) / 4;
                System.out.println("This text should be understood in average by " + avg + " year olds.");
                break;
            default:
                System.out.println("Unknown command");
        }
    }

    /**
     *
     * @return - ARI score
     */
    public double ARI() {
        return 4.71 * ((double) this.characterCount / this.wordCount)
                + 0.5 * ((double) this.wordCount / this.sentenceCount) - 21.43;
    }

    /**
     *
     * @return - FK score
     */
    public double FK() {
        return 0.39 * ((double) this.wordCount / this.sentenceCount)
                + 11.8 * ((double) syllables / wordCount) - 15.59;
    }

    /**
     *
     * @return - SMOG score
     */
    public double SMOG() {
        return 1.043 * Math.sqrt((double) this.polysyllables * 30 / this.sentenceCount) + 3.1291;
    }

    /**
     *
     * @return - CL score
     */
    public double CL() {
        double l = (double) this.characterCount / this.wordCount * 100;
        double s = (double) this.sentenceCount / this.wordCount * 100;

        return 0.0588 * l - 0.296 * s - 15.8;
    }
}
