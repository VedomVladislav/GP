package task278;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) throws IOException {
        List<String> sequences = Files.lines(new File("./src/task278/INPUT.txt").toPath()).collect(Collectors.toList());
        String regexp = Stream.of(sequences.get(0).split("(?!^)")).collect(Collectors.joining(".*"));
        Pattern pattern = Pattern.compile(regexp);

        try (BufferedWriter bw = new BufferedWriter(new FileWriter("./src/task278/OUTPUT.txt"))){

            if(pattern.matcher(sequences.get(1)).find()){
                bw.write("YES");
            }
            else {
                bw.write("NO");
            }
        }
    }
}
