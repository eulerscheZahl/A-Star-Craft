import com.codingame.gameengine.runner.SoloGameRunner;

public class Main {
    public static void main(String[] args) {
        SoloGameRunner gameRunner = new SoloGameRunner();

        // Adds as many player as you need to test your game
        gameRunner.setAgent(RandomAI.class);
        gameRunner.setTestCase("test4.json");

        gameRunner.start(9999);
    }
}