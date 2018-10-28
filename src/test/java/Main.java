import com.codingame.astarcraft.game.Robot;
import com.codingame.game.Referee;
import com.codingame.gameengine.runner.SoloGameRunner;

import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        String runCommand = "mono /home/eulerschezahl/Dokumente/Programmieren/challenges/CodinGame/AStarCraft/bin/Debug/AStarCraft.exe";

        // uncomment to play a single testcase
        // SoloGameRunner gr = new SoloGameRunner();
        // gr.setAgent(runCommand);
        // gr.setTestCase("test19.json");
        // gr.start();

        // testcases that you only want to play once (perfect score is easy, more time for the other tests)
        HashMap<Integer, Integer> fixedScores = new HashMap<>();
        fixedScores.put(1, 23);
        fixedScores.put(2, 46);
        fixedScores.put(3, 22);
        fixedScores.put(4, 44);
        fixedScores.put(5, 11);

        int iterations = 5;
        for (String bot : new String[]{ runCommand }) { // feel free to add more versions and test multiple in a single run
            int best = 0;
            double total = 0;
            for (int testCase = 1; testCase <= 30; testCase++) {
                int testBest = 0;
                String solution = "";
                System.out.print("testcase " + testCase + ": ");
                for (int iteration = 0; iteration < iterations; iteration++) {
                    Robot.globalId = 0;

                    SoloGameRunner gameRunner = new SoloGameRunner();
                    gameRunner.setAgent(bot);
                    gameRunner.setTestCase("test" + testCase + ".json");
                    gameRunner.simulate();
                    total += Referee.score;
                    System.out.print(Referee.score + " | ");
                    if (Referee.score > testBest) {
                        testBest = Referee.score;
                        solution = Referee.solution;
                    }
                    if (fixedScores.containsKey(testCase)) {
                        int expected = fixedScores.get(testCase);
                        if (expected != Referee.score) System.out.print("!!!!!");
                        total += Referee.score * (iterations - 1);
                        testBest = expected;
                        break;
                    }
                }
                best += testBest;
                System.out.println(" => " + testBest + ": " + solution);
            }
            System.out.println("bot: " + bot);
            System.out.println("average score: " + total / iterations);
            System.out.println("max score: " + best);
            System.out.println();
        }
    }
}
