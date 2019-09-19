package Lesson_1.marathon;

import Lesson_1.marathon.Competitors.*;
import Lesson_1.marathon.Obstacles.*;

public class Main {
    public static void main(String[] args) {
        Team team = new Team("team1", new Competitor[]{new Human("Боб"), new Cat("Барсик"), new Dog("Бобик")});
        team.teamInfo();
        Course course = new Course(new Obstacle[]{new Cross(80), new Wall(2), new Wall(1), new Cross(120)});
        course.start(team);
        team.showResults();
    }
}
