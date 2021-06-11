package PlaygroundComponents;

import java.util.Scanner;

/**
 * TimeDate class will contain all the details of the exact time and date,
 * it will be used in specifying the time and date of all the slots.
 *
 * @author Mirette Amin Danial     20190570
 * @version 1.0
 * @since 4 June 2021
 */

public class TimeDate {
    public int hours, minutes;
    public boolean dayTime;
    public int day, month, year;
    Scanner scan = new Scanner(System.in);


    /**
     * Default constrsuctor that runs TimeDate user interface.
     */
    public TimeDate() {
        System.out.println("Enter date and time eg.(day month year hours minutes daytime(am or pm)).");
        day = scan.nextInt();
        month = scan.nextInt();
        year = scan.nextInt();
        hours = scan.nextInt();
        minutes = scan.nextInt();
        dayTime = (scan.next().equalsIgnoreCase("PM"));
    }

    /**
     * Overriding toString Function to display TimeDate's information.
     *
     * @return
     */
    @Override
    public String toString() {
        String s = Integer.toString(hours) + ':' + Integer.toString(minutes);
        if (dayTime) {
            s += " PM";
        } else {
            s += " AM";
        }
        return String.format(s + " on " + day + '/' + month + '/' + year);
    }
}
