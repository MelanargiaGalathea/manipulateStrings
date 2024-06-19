import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class StringTest {

    static Scanner sc = new Scanner(System.in);
    static String haystack = "Hallo, Welt! Ich gehe, gehe in die Welt hinaus! Ich komme wieder, komme wieder.";
    static boolean exit = false;
    static String OPTIONS = "a - Wollen sie im haystack nach einem bestimmten Substring suchen?\n" +
            "b - Wollen Sie aus dem SubString das zweite Vorkommen aus dem haystack löschen?\n" +
            "e - Wollen Sie das Programm beenden?\n" +
            "Wählen Sie eine der Optionen a, b oder e!";
    static int start;

    public static void main(String[] args) {

        do {
            System.out.println(OPTIONS);
            String option = sc.nextLine().trim().toLowerCase();

            switch (option) {
                case "a":
                    System.out.println("Nach welchem Wort wollen Sie suchen?");
                    String wordToFind = sc.nextLine();
                    int[] indices = IndexOfAsArray(wordToFind);
                    if(indices.length == 0)
                        System.out.println("Des gesuchte Wort ist nicht vorhanden.");
                    if(indices.length == 1)
                    System.out.println("Des gesuchte Wort kommt an dem Index " + Arrays.toString(indices) + "vor.");
                    else if(indices.length > 1)
                        System.out.println("Des gesuchte Wort kommt an den Indices " + Arrays.toString(indices) + "vor.");
                    break;
                case "b":
                    try {
                        System.out.println("Nach welchem Wort wollen Sie suchen?");
                        String searchString  = sc.nextLine();
                        System.out.println("Soll das zweite Vorkommen des Strings " + searchString + " gelöscht werden? (j/n)?");
                        String confirm = sc.nextLine().trim().toLowerCase();
                        switch (confirm) {
                            case "j":
                                start = searchSecondIndex(haystack, searchString);

                                System.out.println("Der String " + "'" + searchString + "'" + " an Index-Position: " + "[" + start + "]" + " wurde gelöscht.");
                                String newStr = delSecOcc(haystack, searchString, start);
                                System.out.println("Neuer String: " + newStr);
                                break;
                            case "n":
                                break;
                        }
                    } catch (StringIndexOutOfBoundsException e) {
                        System.out.println("Der gesuchte String ist länger als der Text, in dem gesucht wird.");
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                case "e":
                    exit = true;
                    break;
            }
        } while (!exit);
    }


    // delSecOcc returns String str without the second Occurence of String delStr
    public static String delSecOcc (String str, String delStr, int index) {
        return str.substring(0, index) + str.substring(index+delStr.length());
    }

    // is searching for the Index of the second occurence of String (word) in a String (fullstr)
    public static int searchSecondIndex (String fullstr, String word) throws StringIndexOutOfBoundsException{

        int index = -1;
        int firstIndex;
        String shortstr;

        if (fullstr.length() >= word.length()) {
            for(int i = 0; i < fullstr.length(); i++) {
                if (fullstr.contains(word)) {
                    index = fullstr.indexOf(word);
                    firstIndex = index;
                    shortstr = fullstr.substring(index + word.length());
                    for (int i2 = 0; i2 < shortstr.length(); i2++) {
                        if (shortstr.contains(word)) {
                            index = shortstr.indexOf(word) + firstIndex + word.length();
                            break;
                        } else index = -1;
                    }
                }
            }
        }
        return index;
    }

    public static int[] IndexOfAsArray (String needle) {

        ArrayList<Integer> indices = new ArrayList<>();
        int index = 0;

        while ((index = haystack.indexOf(needle, index)) > -1) {
            indices.add(index);
            index++;
        }
        return fromListToArray(indices);
    }

    public static int[] fromListToArray(ArrayList<Integer> list) {
        int[] arr = new int[list.size()];
        int index = 0;
        for(int i:list) {
            arr[index] = i;
            index++;
        }
        return arr;
    }
}

