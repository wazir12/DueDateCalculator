package org.emarsys;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class DueDateCalculator {

    //Define Work Day Start Hour
    private static final LocalTime WORK_START = LocalTime.of(9, 0);
    //Define Work Day End Hour
    private static final LocalTime WORK_END = LocalTime.of(17, 0);

    //Holiday List
    private static final ArrayList<LocalDate> non_weekend_holidays=new ArrayList(List.of( LocalDate.of(2024,6,18)));


    public LocalDateTime calculateDueDate(LocalDateTime submitTime, int turnaroundHours) {
        //1. Initialize the dueDate to submitTime
        LocalDateTime dueDateTime = submitTime;
        //2. continue processing until all turnaround hours are accounted for
        while (turnaroundHours > 0) {
            //Step 3: check if the current time is within the working hours of the working day
            if (isWorkingDay(dueDateTime) && isWithinWorkingHours(dueDateTime)) {
                //Step 4: calculate the remaining working hours for the current day
                int remainingWorkHoursToday = WORK_END.getHour() - dueDateTime.getHour();
                //Step 5:  Decides how many hours to add to dueDatetime
                if (turnaroundHours <= remainingWorkHoursToday) {
                    //Step: add the turnaroundHours to dueDateTime and set turnaroundAroundHours to 0
                    dueDateTime = dueDateTime.plusHours(turnaroundHours);
                    turnaroundHours = 0;
                } else {
                    //Add remaining working Hours for today to dueDateTime
                    dueDateTime = dueDateTime.plusHours(remainingWorkHoursToday);
                    //Subtract the remainingWorking  hours from turnaroundTime, as it is greater
                    turnaroundHours -= remainingWorkHoursToday;
                }
            }
            //Step 6: If Any turnaround hours are still left move it to next working day
            if (turnaroundHours > 0) {
                dueDateTime = moveToNextWorkingDayStart(dueDateTime);
            }
        }
        //Step 7: Return the final due date time
        return dueDateTime;
    }

    /**
     * Helper Method
     * Check if the submit date is not weekend or holiday
     * @param dateTime
     * @return
     */
    private boolean isWorkingDay(LocalDateTime dateTime) {
        DayOfWeek dayOfWeek = dateTime.getDayOfWeek();
        boolean checkIfHoliday= non_weekend_holidays.contains(dateTime.toLocalDate());
        return dayOfWeek != DayOfWeek.SATURDAY && dayOfWeek != DayOfWeek.SUNDAY && !checkIfHoliday;
    }

    /**
     * Helper Method
     * To Check if the submit date falls within the working start and end hours range.
     * @param dateTime
     * @return
     */
    private boolean isWithinWorkingHours(LocalDateTime dateTime) {
        LocalTime time = dateTime.toLocalTime();
        return !time.isBefore(WORK_START) && !time.isAfter(WORK_END);
    }

    /**
     * Helper Method
     * Advances the current day to the next working day start (9 AM), skipping holidays and weekends
     * @param dateTime
     * @return
     */

    private LocalDateTime moveToNextWorkingDayStart(LocalDateTime dateTime) {
        dateTime = dateTime.plusDays(1).withHour(WORK_START.getHour()).withMinute(0);
        while (!isWorkingDay(dateTime)) {
            dateTime = dateTime.plusDays(1);
        }
        return dateTime;
    }
}
