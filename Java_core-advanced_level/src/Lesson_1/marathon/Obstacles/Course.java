package Lesson_1.marathon.Obstacles;

import Lesson_1.marathon.Competitors.*;

public class Course {
    Obstacle[] course;

    public Course(Obstacle[] _course){
        course = _course;
    }

    public void start(Team team){
        Competitor[] competitors = team.getCompetitors();

        for (Competitor competitor : competitors) {
            for (Obstacle obstacle : course) {
                obstacle.doIt(competitor);
                if (!competitor.isOnDistance()) break;
            }
        }

    }

}
