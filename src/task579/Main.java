package task579;

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) {

        List<Integer> list = readFromFile("./src/task579/INPUT.txt");
        Map<String, List<Integer>> map = searchMaxModule(list);
        saveFile(map);
        System.out.println(map);

    }

    private static Map<String, List<Integer>> searchMaxModule(List<Integer> list) {
        int module1 = 0, module2 = 0;
        int countModule1 = 0, countModule2 = 0;
        List<Integer> positionForPositive = new ArrayList<>();
        List<Integer> positionForNegative = new ArrayList<>();
        Map<String, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < list.size(); i++) {
            if(list.get(i) >= 0) {
                module1 += list.get(i);
                countModule1++;
                positionForPositive.add(i+1);
            }
            else {
                module2 += list.get(i);
                countModule2++;
                positionForNegative.add(i+1);
            }
        }
        if(module1 > module2*(-1)) {
            map.put("positions", positionForPositive);
            map.put("counter", Collections.singletonList(countModule1));
        } else {
            map.put("counter", Collections.singletonList(countModule2));
            map.put("positions", positionForNegative);
        }
        return map;
    }

    private static List<Integer> readFromFile(String nameFile) {
        List<Integer> list = new ArrayList<>();
        try {
            FileInputStream fis = new FileInputStream(nameFile);
            BufferedReader br = new BufferedReader(new InputStreamReader(fis));
            String text = br.readLine();
            while ((text = br.readLine()) != null) {
                String[] arr = text.split(" ");
                for (int i = 0; i < arr.length; i++) {
                    list.add(Integer.valueOf(arr[i]));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    private static void saveFile(Map<String, List<Integer>> outputInt) {
        File file;
        FileOutputStream fos = null;
        try {
            file = new File("./src/task579/OUTPUT.txt");
            fos = new FileOutputStream(file);

            if (!file.exists()) {
                file.createNewFile();
            }

            String strForSave = outputInt.get("counter").get(0).toString().concat("\n");
            List<Integer> positions = outputInt.get("positions");
            for(Integer position: positions) {
                strForSave = strForSave.concat(String.valueOf(position) + " ");
            }
            strForSave = strForSave.substring(0, strForSave.length() - 1);
            byte[] bytesArray = strForSave.getBytes();
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
