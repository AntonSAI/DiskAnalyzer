package by.diskanalyzer.java.main.file_mover;

import java.io.*;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;


public class FastFileMover {
    private static int bufferSize = 100;

    private static void fileChannelMove(String input, String output) throws IOException {
        FileChannel source = new FileInputStream(new File(input)).getChannel();
        FileChannel dest = new FileOutputStream(new File(output)).getChannel();
        try {
            source.transferTo(0, source.size(), dest);
        } finally {
            source.close();
            dest.close();
        }
    }

    private static void fileMoveWithBuffer(String input, String output) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(input);
        BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream, bufferSize);

        FileOutputStream outputStream = new FileOutputStream(output);
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(outputStream, bufferSize);
        int i;
        while ((i = bufferedInputStream.read()) != -1) {
            bufferedOutputStream.write(i);
        }
    }

    private static void fileMove(String input, String output) {
        Path sourcePath = Paths.get(input);
        Path destinationPath = Paths.get(output);
        try {
            Files.move(sourcePath, destinationPath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
