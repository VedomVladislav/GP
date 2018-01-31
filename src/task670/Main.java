package task670;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {

        Integer N = Files.lines(new File("./src/task670/INPUT").toPath()).map(Integer::parseInt).findFirst().get();
        List<Integer> list = Main.createList();
        list = delete(list);
        saveFile(list.get(N-1));
    }

    private static List<Integer> createList(){
        List<Integer> newList = new ArrayList<>();
        for(int i = 1; i < 10000; i++) {
            newList.add(i);
        }
        return newList;
    }

    private static List<Integer> delete(List<Integer> list) {
        for(int i = 0; i < list.size(); i ++) {
            if(list.get(i).toString().length() > 1) {
                String checkString = list.get(i).toString();
                checkString = sortString(checkString);
                for (int j = 0; j < checkString.length() - 1; j++) {
                    if(checkString.charAt(j) == checkString.charAt(j+1)) {
                        list.remove(i);
                        i--;
                        break;
                    }
                }
            }
        }
        return list;
    }

    private static String sortString(String inputString)
    {
        char tempArray[] = inputString.toCharArray();
        Arrays.sort(tempArray);
        return new String(tempArray);
    }

//    private static Integer readFromFile(String nameFile) {
//        Integer intFromFile = 0;
//        try {
//            FileInputStream fis = new FileInputStream(nameFile);
//            BufferedReader br = new BufferedReader(new InputStreamReader(fis));
//            intFromFile = Integer.valueOf(br.readLine());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return intFromFile;
//    }

    private static void saveFile(Integer outputInt) {
        File file;
        FileOutputStream fos = null;
        try {
            file = new File("./src/task670/OUTPUT.txt");
            fos = new FileOutputStream(file);

            if (!file.exists()) {
                file.createNewFile();
            }
            byte[] bytesArray = outputInt.toString().getBytes();

            fos.write(bytesArray);
            fos.flush();
            System.out.println("File Written Successfully");
        }
        catch (IOException ioe) {
            ioe.printStackTrace();
        }
        finally {
            try {
                if (fos != null)
                {
                    fos.close();
                }
            }
            catch (IOException ioe) {
                System.out.println("Error in closing the Stream");
            }
        }
    }
}