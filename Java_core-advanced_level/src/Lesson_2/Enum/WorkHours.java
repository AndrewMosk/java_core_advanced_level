package Lesson_2.Enum;

class WorkHours {
    static String getWorkingHours(DayOfWeek dayOfWeek){
        int remainingHours = 0;

        if (dayOfWeek == DayOfWeek.SUNDAY || dayOfWeek == DayOfWeek.SATURDAY){
            return dayOfWeek.getRus() + " выходной.";
        }

        for (DayOfWeek day : DayOfWeek.values()) {
            if (day == dayOfWeek){
                remainingHours += day.getWorkHours();
                continue;
            }
            if (remainingHours != 0){
                remainingHours += day.getWorkHours();
            }
        }
        return "До конца рабочей недели " + remainingHours + " часов.";
    }
}