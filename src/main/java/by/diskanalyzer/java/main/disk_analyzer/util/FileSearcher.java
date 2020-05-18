package by.diskanalyzer.java.main.disk_analyzer.util;

import by.diskanalyzer.java.main.disk_analyzer.view.SelectionViewer;

import java.io.File;
import java.util.Scanner;

public class FileSearcher {

    public static void search(File dirDirectory, int numberOfFileOperation) {
        FileOperation operation = new FileOperation(dirDirectory);
        switch (numberOfFileOperation) {
            case 1:
                System.out.println(operation.findFileWithMaxCountSInName());
                break;
            case 2:
                System.out.println(operation.findLargestFile());
                break;
            case 3:
                System.out.println(operation.findAverageFileSize());
                break;
            case 4:
                operation.printAlphabeticalOrderList();
                break;
            default:
                System.out.println("Unknown operation");
        }
    }

    public static void appRunner() {
        boolean flag = true;
        while (flag) {
            FileSearcher.search(SelectionViewer.getSelectedFilePath(), SelectionViewer.getSelectedFunctionNumber());
            System.out.println("Press  0 to exit");
            if (new Scanner(System.in).nextLine().equals("0"))
                flag = false;
        }
    }
}
