package org.emarsys;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        DueDateCalculator calculator = new DueDateCalculator();
        Scanner scanner = new Scanner(System.in);
        System.out.println(" Submit Issue (Enter Yes/No to proceed)? ");
        String inputString = scanner.nextLine();
        if(inputString.toLowerCase().equals("yes")){
            System.out.println("Enter Turnaround time (in hours-) (Integer ): ");

            int turnAroundTime = scanner.nextInt();
            LocalDateTime currentDateTime = calculateCurrentDateTime();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

            System.out.println("Issue Submitted at: "+currentDateTime.format(formatter));
            LocalDateTime resolutionDate = calculator.calculateDueDate(currentDateTime, turnAroundTime);
            System.out.println("This issue will be resolved by (RESULT): "+resolutionDate.format(formatter));

            System.out.println("\n\n");
            System.out.println("Please Note:\n" +
                    "Working hours are from 9 AM to 5 PM on every working day, Monday to Friday.\n" +
                    "Exception: June 18, 2024, is assumed to be a holiday.");

        }else{
            System.exit(0);
        }

    }

    public static LocalDateTime calculateCurrentDateTime(){
        return LocalDateTime.now();
    }
}