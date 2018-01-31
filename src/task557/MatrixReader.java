package task557;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MatrixReader implements AutoCloseable {

    private int matrixsCount;
    private int matrixsSize;
    private int i;
    private int j;
    private int p;
    private int matrixReaded = 0;

    private RandomAccessFile randomAccessFile;

    public MatrixReader(File file) throws IOException {
        randomAccessFile = new RandomAccessFile(file, "r");
        init();
    }

    private void init() throws IOException {
        List<Integer> matrixParams = Stream.of(randomAccessFile.readLine().split(" ")).map(Integer::parseInt).collect(Collectors.toList());
        matrixsCount = matrixParams.get(0);
        matrixsSize = matrixParams.get(1);

        List<Integer> elementCoordinates = Stream.of(randomAccessFile.readLine().split(" ")).map(Integer::parseInt).collect(Collectors.toList());
        i = elementCoordinates.get(0);
        j = elementCoordinates.get(1);

        p = Integer.parseInt(randomAccessFile.readLine().trim());
    }

    public boolean hasNext(){
        return matrixReaded != matrixsCount;
    }

    public List<List<Integer>> nextMatrix() throws IOException {
        List<List<Integer>> result = new ArrayList<>();
        String inputBuffer;
        while ((inputBuffer = randomAccessFile.readLine()).isEmpty()){}

        result.add(Stream.of(inputBuffer.split(" ")).map(Integer::parseInt).collect(Collectors.toList()));

        for (int readedStrings = 0; readedStrings < matrixsSize - 1; readedStrings ++){
            inputBuffer = randomAccessFile.readLine();
            result.add(Stream.of(inputBuffer.split(" ")).map(Integer::parseInt).collect(Collectors.toList()));
        }

        matrixReaded ++;
        return result;
    }

    public int getI() {
        return i;
    }

    public int getJ() {
        return j;
    }

    public int getP() {
        return p;
    }

    @Override
    public void close() throws Exception {
        randomAccessFile.close();
    }
}
