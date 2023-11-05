import java.util.Scanner;

public class GUI {

	public static void main(String[] args) {
		Game_Controller Controller = new Game_Controller();
		Scanner scanner = new Scanner(System.in);
		
		System.out.printf("Difficulty Level: %d \n", Controller.getDifficulty_Level());
		System.out.printf("Correct Responses: %d \n", Controller.getCorrect_Responses());
		System.out.printf("Total Responses: %d \n", Controller.getTotal_Responses());
		System.out.printf("Current Question: %s", Controller.getCurrent_Question());
		System.out.printf("\nCurrent Solution: %d \n", Controller.getCurrent_Solution());

		
		while(true)
		{	

			
		System.out.printf("%s", Controller.Generate_Question());	
		int userInput = scanner.nextInt();
		
		
		if(Controller.CheckQuestion(userInput) == 1)
		{
			System.out.printf("Correct Solution!!!!!!! \n");
			Controller.Increment_Correct();
		}
		else if(Controller.CheckQuestion(userInput) == 0)
		{
			System.out.printf("Wrong Solution!!!!!!! \n");
			Controller.Increment_Incorrect();
		}
		else
		{
			System.out.printf("Error!!!!!!! \n");
		}
		
		Controller.Generate_Difficult_Level();
		
			
		System.out.printf("Difficulty Level: %d \n", Controller.getDifficulty_Level());
		System.out.printf("Correct Responses: %d \n", Controller.getCorrect_Responses());
		System.out.printf("Total Responses: %d \n", Controller.getTotal_Responses());
		System.out.printf("Current Question: %s", Controller.getCurrent_Question());
		System.out.printf("Current Solution: %d \n", Controller.getCurrent_Solution());
		
		
		
		}
		
	}

}
