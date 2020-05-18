package by.diskanalyzer.java.main.disk_analyzer.controller;

import by.diskanalyzer.java.main.disk_analyzer.entity.Info;
import by.diskanalyzer.java.main.disk_analyzer.view.SelectionViewer;

import java.io.File;

public class SelectionController {

    public static final int MIN_RANGE = 1;
    public static final int MAX_RANGE = 4;

    public static File getSelectedFilePath() {
        System.out.println(Info.PATH_INFO);
        return SelectionController.getSelectedFilePath(SelectionViewer.inputPath());
    }

    public static int getSelectedFunctionNumber() {
        System.out.println(Info.SEARCH_OPERATION);
        return SelectionController.getSelectedFunctionNumber(SelectionViewer.inputNumberOfOperaton());
    }

    private static File getSelectedFilePath(String filePath) {
        File path = new File(filePath);
        if (path.exists() && path.isDirectory()) {
            return path;
        } else {
            System.out.printf("You entered [%s] it's incorrect path. Pleas try again\n", filePath);
            repeatSelectFilePath();
        }
        return path;
    }

    private static File repeatSelectFilePath() {
        return SelectionController.getSelectedFilePath(SelectionViewer.inputPath());
    }

    private static int getSelectedFunctionNumber(String numberOperation) {
        int number = 0;
        try {
            number = Integer.parseInt(numberOperation);
            isNumberInRange(number);
            return number;
        } catch (NumberFormatException e) {
            repeatSelectedFunctionNumber();
        }
        return number;
    }

    private static int repeatSelectedFunctionNumber() {
        return SelectionController.getSelectedFunctionNumber(SelectionViewer.inputNumberOfOperaton());
    }

    private static boolean isNumberInRange(int number) {
        if (isInRange(number))
            return true;
        printNotRangeInfo(number);
        throw new NumberFormatException();
    }

    private static boolean isInRange(int number) {
        return number > MIN_RANGE && number < MAX_RANGE;
    }

    private static void printNotRangeInfo(int number) {
        System.out.printf("You entered an incorrect number [%s]\n" +
                "It's not in the range [%s-%s]\n", number, MIN_RANGE + 1, MAX_RANGE - 1);
    }
}
