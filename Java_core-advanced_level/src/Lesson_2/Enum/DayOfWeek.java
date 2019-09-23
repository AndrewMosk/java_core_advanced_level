package Lesson_2.Enum;

public enum DayOfWeek {
    SUNDAY("Воскресенье",0), MONDAY("Понедельник",8), TUESDAY("Вторник",8),
    WEDNESDAY("Среда",8), THURSDAY("Четверг",8), FRIDAY("Пятница",8), SATURDAY("Суббота",0);

    private String rus;
    private int workHours;

    DayOfWeek(String rus, int workHours) {
        this.rus = rus;
        this.workHours = workHours;
    }

    public String getRus() {
        return rus;
    }

    public int getWorkHours() {
        return workHours;
    }
}