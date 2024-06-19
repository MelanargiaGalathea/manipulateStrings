public class StringTest2 {

    public static void main(String[] args) {

        String str = "Hallo, Welt! Ich gehe, gehe in die Welt hinaus! Ich komme wieder, komme wieder.";

        String str2 = "Welt";

        int begin = searchIndex(str, str2);

        System.out.println("Der String, der gelöscht werden soll, befindet sich an Index-Position: " + begin);

        String newStr = delSecOcc(str, str2, begin);

        System.out.println(newStr);
    }

    public static String delSecOcc (String str, String delStr, int index) {
        int firstIndex = str.indexOf(delStr, index);
        if (firstIndex == -1) return str;

        int secondIndex = str.indexOf(delStr, firstIndex + delStr.length());
        if (secondIndex == -1) return str;

        return str.substring(0, secondIndex) + str.substring(secondIndex + delStr.length());
    }

    public static int searchIndex(String fullstr, String word) {
        int firstIndex = fullstr.indexOf(word);
        if (firstIndex == -1) return -1;

        int secondIndex = fullstr.indexOf(word, firstIndex + word.length());
        return secondIndex;
    }
}