package ui;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Menu {

    Context _ctx = Context.get();

    public void start() {
        System.out.println("Choose the option:\n" +
                "d - show particular date timetable\n" +
                "t - show today timetable\n" +
                "e - exit");
        Scanner reader = new Scanner(System.in);
        this.handleOption(reader.next());
    }

    private void handleOption(String option) {
        DatedLessonPrinter dLP = new DatedLessonPrinter();
        switch (option) {
            case "d":
                System.out.print("Enter the date: ");
                Scanner reader = new Scanner(System.in);
                String date = reader.next();
                System.out.println();
                dLP.print(date);
                break;
            case "t":
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                Date today = new Date();
                String formattedToday = format.format(today);
                dLP.print(formattedToday);
                break;
            case "e":
                System.out.println("Goodbye.");
                System.exit(0);
                break;
            default:
                System.out.println("Wrong option, try again.");
                this.start();
                break;
        }
    }

}

