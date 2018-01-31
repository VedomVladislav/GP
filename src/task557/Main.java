package task557;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) throws IOException {
        try (MatrixReader matrixReader = new MatrixReader(new File("C:\\Users\\User\\Desktop\\task1\\src\\task557\\INPUT.txt"))){

            List<List<Integer>> matrix1 = matrixReader.nextMatrix();
            List<List<Integer>> matrix2 = matrixReader.nextMatrix();

            List<Integer> resultLine = getIStringValue(matrix1.get(matrixReader.getI()-1), matrix2).stream().map(e -> e % matrixReader.getP()).collect(Collectors.toList());

            while (matrixReader.hasNext()){
                resultLine = getIStringValue(resultLine, matrixReader.nextMatrix()).stream().map(e -> e % matrixReader.getP()).collect(Collectors.toList());
            }
            try (BufferedWriter bw = new BufferedWriter(new FileWriter("./src/task557/OUTPUT.txt"))){
                int resultValue = resultLine.get(matrixReader.getJ()-1);
                bw.write(resultValue+"");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static List<Integer> getIStringValue(List<Integer> firstMatrixString, List<List<Integer>> secondMatrix){
        List<Integer> result = new ArrayList<>();

        for(int i = 0; i < secondMatrix.size(); i++){
            int finalI = i;
            int element = IntStream.range(0, firstMatrixString.size()).map(j -> firstMatrixString.get(j) * secondMatrix.get(j).get(finalI)).sum();
            result.add(element);
        }

        return result;
    }



}
