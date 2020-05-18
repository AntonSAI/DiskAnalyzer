package by.diskanalyzer.java.main.disk_analyzer.util;

import java.io.File;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileOperation {
    private List<File> files;

    public FileOperation(File fileDirectory) {
        files = new ArrayList<>();
        retrieveCatalogs(fileDirectory);
    }

    public String findFileWithMaxCountSInName() {
        Comparator<File> maxLetter = ((file1, file2) -> {
            return getCountOfLetter(file1.getName()) - getCountOfLetter(file2.getName());
        });
        return files.stream().max(maxLetter).get().getAbsolutePath();
    }

    public List<File> findLargestFile() {
        List<File> result = new ArrayList<>();
        List<File> tempFiles = files;
        for (int i = 0; i < 5; i++) {
            File tempFile = tempFiles.stream().max((file, t1) -> (int) (file.length() - t1.length())).get();
            result.add(tempFile);
            tempFiles.remove(tempFile);
        }
        return result;
    }

    public double findAverageFileSize() {
        return files.stream().mapToLong(file -> file.length()).average().getAsDouble();
    }

    public void printAlphabeticalOrderList() {
        for (char i = 'a'; i < 'z'; i++) {
            char tempChar = i;
            long filesCount = files.stream().filter(file -> file.isFile()).map(file -> file.getName().toLowerCase()).filter(s -> s.startsWith(tempChar + "")).count();
            long dirCount = files.stream().filter(dir -> dir.isDirectory()).map(dir -> dir.getName().toLowerCase()).filter(s -> s.startsWith(tempChar + "")).count();
            System.out.printf("the letter [%s]-starts with [%s] files and [%s] folders\n", tempChar, filesCount, dirCount);
        }
    }

    private int getCountOfLetter(String string) {
        Pattern pattern = Pattern.compile("([sS])");
        Matcher matcher = pattern.matcher(string);
        int count = 0;
        while (matcher.find())
            count++;
        return count;
    }

    private void retrieveCatalogs(File fileDirectory) {
        if (fileDirectory.isDirectory()) {
            File[] files = fileDirectory.listFiles();
            if (files != null) {
                for (File file : files) {
                    this.files.add(file);

                    if (file.isDirectory()) {
                        retrieveCatalogs(file);
                    }
                }
            }
        }
    }
}
