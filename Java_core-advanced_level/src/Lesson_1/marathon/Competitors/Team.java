package Lesson_1.marathon.Competitors;

public class Team {
    private String teamName;
    private Competitor[] competitors;

    public Team(String _teamName, Competitor[] _competitors){
        teamName = _teamName;
        competitors = _competitors;
    }

    public void teamInfo(){
        System.out.println("Команда " + teamName);
        for (int i = 0, j = 1; i<competitors.length; i++, j++) {
            System.out.println("Участник " + j + " " + competitors[i].getType() + " " + competitors[i].getName() + ".");
        }
    }

    public void showResults(){
        System.out.println("Команда " + teamName);
        for (Competitor competitor: competitors) {
            competitor.info();
        }
    }

    public Competitor[] getCompetitors(){
        return competitors;
    }




}
