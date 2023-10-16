import java.util.Scanner;
import java.lang.Math;
public class DifficultyTest {
	
	public static boolean answer = false;
	public static double difficultyRatio = 1.0;
	public static int difficultyNumerator = 1;
	public static int difficultyDenominator = 1;
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		for(int i = 0; i<10; i++) {
				System.out.println("Was the answer true or false?\n");
				try {
				answer = scanner.nextBoolean();
				}catch (Exception e) {
					System.out.println("uh oh");
					return;
				}
			if(answer == true) {
				difficultyNumerator++;
				System.out.printf("Adding value to numerator to make it %d\n",difficultyNumerator);
			}else {
				difficultyDenominator++;
				System.out.printf("Adding value to denominator to make it %d\n",difficultyDenominator);
			}
			difficultyRatio = (double)difficultyNumerator/(double)difficultyDenominator;
			System.out.printf("Current ratio is %d/%d, or %.4f\n", difficultyNumerator, difficultyDenominator, difficultyRatio);
		}
		if(difficultyRatio >= 0 && difficultyRatio <= 0.2) {
			System.out.printf("Greatly decreasing difficulty...\n");
		}else if(difficultyRatio > 0.2 && difficultyRatio <= 0.6) {
			System.out.printf("Decreasing difficulty...\n");
		}else if(difficultyRatio > 0.6 && difficultyRatio <= 1.5) {
			System.out.printf("Difficulty remains unchanged...\n");
		}else if(difficultyRatio > 1.5 && difficultyRatio <= 4) {
			System.out.printf("Increasing difficulty...\n");
		}else if(difficultyRatio > 4) {
			System.out.printf("Greatly Increasing difficulty...\n");
		}else {
			System.out.printf("Difficulty remains unchanged...\n");
		}

	}

}
