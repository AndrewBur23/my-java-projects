public class Repetition {
    public static void main(String[] args) {
        String[] teams = {"Spartak Moscow", "Burnley" , "Legia Warszawa" , "Barcelona" , "Manchester United"};
        for (String team : teams) {
            System.out.println(team);
            if (team.equals("Spartak Moscow")) {
                System.out.println("Spartak Moscow - Champion!");
            } else {
                System.out.println("I want to visit a match of " + team);
            }
        }

    }
}