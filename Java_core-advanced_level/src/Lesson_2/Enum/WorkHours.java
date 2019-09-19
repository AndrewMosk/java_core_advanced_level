package Lesson_2.Enum;

class WorkHours {
    static String getWorkingHours(DayOfWeek dayOfWeek){
        String result;
        int remainingHours = 0;
        boolean dayFound = false;

        if (dayOfWeek!=DayOfWeek.SUNDAY && dayOfWeek!=DayOfWeek.SATURDAY){
            for (DayOfWeek day : DayOfWeek.values()) {
                if (day == dayOfWeek){
                    dayFound = true;
                }
                if (dayFound){
                    remainingHours = remainingHours + day.getWorkHours();
                }
            }
        }

        if (remainingHours == 0){
            result = dayOfWeek.getRus() + " выходной.";
        }else {
            result = "До конца рабочей недели " + remainingHours + " часов.";
        }

        return result;
    }
}
