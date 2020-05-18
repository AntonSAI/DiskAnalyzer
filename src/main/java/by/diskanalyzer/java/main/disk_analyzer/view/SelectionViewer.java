package by.diskanalyzer.java.main.disk_analyzer.view;

import by.diskanalyzer.java.main.disk_analyzer.controller.SelectionController;

import java.io.File;
import java.util.Scanner;

public class SelectionViewer {

    public static String inputPath() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    public static String inputNumberOfOperaton() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    public static File getSelectedFilePath() {
        return SelectionController.getSelectedFilePath();
    }

    public static int getSelectedFunctionNumber() {
        return SelectionController.getSelectedFunctionNumber();
    }
}
