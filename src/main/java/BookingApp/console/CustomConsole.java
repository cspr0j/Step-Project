package BookingApp.console;

import java.util.Scanner;

public class CustomConsole implements Console {
    Scanner scanner = new Scanner(System.in);

    @Override
    public void println(String line) {
        System.out.println(line);
    }

    @Override
    public void print(String line) {
        System.out.print(line);
    }

    @Override
    public String scanNextLine() {
        return scanner.nextLine();
    }
}
