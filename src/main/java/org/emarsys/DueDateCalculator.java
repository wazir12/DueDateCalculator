package org.emarsys;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class DueDateCalculator {

    private static final LocalTime WORK_START = LocalTime.of(9, 0);
    private static final LocalTime WORK_END = LocalTime.of(17, 0);

    private static final ArrayList<LocalDate> non_weekend_holidays=new ArrayList(List.of( LocalDate.of(2024,6,18)));


    public LocalDateTime calculateDueDate(LocalDateTime submitTime, int turnaroundHours) {
        LocalDateTime dueDateTime = submitTime;
        while (turnaroundHours > 0) {
            if (isWorkingDay(dueDateTime) && isWithinWorkingHours(dueDateTime)) {
                int remainingWorkHoursToday = WORK_END.getHour() - dueDateTime.getHour();
                if (turnaroundHours <= remainingWorkHoursToday) {
                    dueDateTime = dueDateTime.plusHours(turnaroundHours);
                    turnaroundHours = 0;
                } else {
                    dueDateTime = dueDateTime.plusHours(remainingWorkHoursToday);
                    turnaroundHours -= remainingWorkHoursToday;
                }
            }

            if (turnaroundHours > 0) {
                dueDateTime = moveToNextWorkingDayStart(dueDateTime);
            }
        }
        return dueDateTime;
    }

    private boolean isWorkingDay(LocalDateTime dateTime) {
        DayOfWeek dayOfWeek = dateTime.getDayOfWeek();
        boolean checkIfHoliday= non_weekend_holidays.contains(dateTime.toLocalDate());
        return dayOfWeek != DayOfWeek.SATURDAY && dayOfWeek != DayOfWeek.SUNDAY && !checkIfHoliday;
    }
    private boolean isWithinWorkingHours(LocalDateTime dateTime) {
        LocalTime time = dateTime.toLocalTime();
        return !time.isBefore(WORK_START) && !time.isAfter(WORK_END);
    }
    private LocalDateTime moveToNextWorkingDayStart(LocalDateTime dateTime) {
        dateTime = dateTime.plusDays(1).withHour(WORK_START.getHour()).withMinute(0);
        while (!isWorkingDay(dateTime)) {
            dateTime = dateTime.plusDays(1);
        }
        return dateTime;
    }
}
