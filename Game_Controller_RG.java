import java.util.Random;

public class Game_Controller_RG 
{
	
	private static int Correct_Responses = 0;
	private static int Difficulty_Level = 0;
	private static int Total_Responses = 0;
	private static int Current_Solution = 0;
	private static String Current_Question = "Empty";

	public static void main(String[] args) 
		{
	
		}

	

	void Generate_Difficult_Level() 
	{
		double difficultyRatio;
	
	
		difficultyRatio = (double)Correct_Responses/(double)Total_Responses;
		
		if(difficultyRatio >= 0 && difficultyRatio <= 0.30) 
			{
				Difficulty_Level = 0;
			}
		else if(difficultyRatio > 0.30 && difficultyRatio <= 0.60) 
			{
				Difficulty_Level = 1;
			}
		else if(difficultyRatio > 0.60 && difficultyRatio <= 0.70) 
			{
				Difficulty_Level = 2;
			}
		else if(difficultyRatio > 0.70 && difficultyRatio <= 0.90) 
			{
				Difficulty_Level = 3;
			}
		else if(difficultyRatio > 0.90) 
			{
				Difficulty_Level = 4;
			}
		else 
			{
				
			}
		
	}


	String Generate_Question()
	{
		 int min = 0;
		 int max = 0;
		 int QueryFlag = 0;
		 
		 int NumA;
		 int NumB;
		 int NumC;
		 
		 int Operation;
		 
		 //input
		 int DiffNum = 0;
		 //Output
		 String Question = "Error";
		 int Solution = 0;
		 
		 //instantiations
		 Random random = new Random();
		 
		 DiffNum = Difficulty_Level;
		 Operation = random.nextInt(4);
		 //0 Multiply w/ Add
		 //1 Multiply w/ Subtract
		 //2 Divide w/ Multiply
		 //3 Add w/ Subtract
		 
		switch(DiffNum)
			 {
			 	case 0:
				 	{
				 		 min = 0;
				         max = 6;
				         QueryFlag = 0;
				         break;
				 	}
			 	case 1:
				 	{
				 		 min = 0;
				         max = 12;
				         QueryFlag = 0;
				         break;
				 	}
			 	case 2:
				 	{
				 		 min = 0;
				         max = 15;
				         QueryFlag = 0;
				         break;
				 	}
			 	case 3:
				 	{
				 		 min = 0;
				         max = 6;
				         QueryFlag = 1;
				         break;
				 	}
			 	case 4:
				 	{
				 		 min = 0;
				         max = 12;
				         QueryFlag = 1;
				         break;
				 	}
				default:
					{
						System.out.printf("Range Generation Error");
					}
		
			 }
			 
			 NumA = random.nextInt(max - min + 1) + min;
			 NumB = random.nextInt(max - min + 1) + min;
			 NumC = random.nextInt(max - min + 1) + min;
			 
			 
		if((Operation == 0) || (Operation == 1))
			 {
				 if((QueryFlag == 1) && (Operation == 0))
					 {
						 Solution = ((NumA*NumB) + NumC);
						 Question = String.format("(%d x %d) + %d = ?", NumA, NumB, NumC);
					 }
				 else if((QueryFlag == 1) && (Operation == 1))
					 {
					 	if(((NumA*NumB) - NumC) < 0)
						 	{
						 		int result = NumA * NumB - NumC;
	
						        while (result < 0) 
						        {
						            NumA++;
						            result = NumA * NumB - NumC;
						        }
						 	}
					 	else
						 	{
						 		
						 	}
					 
						 Solution = ((NumA*NumB) - NumC);
						 Question = String.format("(%d x %d) - %d = ?", NumA, NumB, NumC);
					 }				 
				 else if(QueryFlag == 0)
					 {
						 Solution = NumA*NumB;
						 Question = String.format("%d x %d = ?", NumA, NumB);				 
					 }
				 else 
				 	{
					 
				 	}
		
			 }
		
		if(Operation == 2)
			{
				int Scale = random.nextInt(5) + 1;
				
				if(NumB == 0)
					{
						NumB = Scale;
					}
				
				NumA = NumB * Scale;
				
				if(QueryFlag == 0)
					{
						Solution = (NumA/NumB);
						Question = String.format("%d / %d = ?", NumA, NumB);
					}
				else if(QueryFlag == 1)
					{
						Solution = ((NumA/NumB) * NumC);
						Question = String.format("(%d / %d) x %d = ?", NumA, NumB, NumC);	
					}
				else
					{
						
					}
				
			}
		if(Operation == 3)
			{
				if(QueryFlag == 0)
					{
						Solution = (NumA + NumB);
						Question = String.format("%d + %d = ?", NumA, NumB);
					}
				else if(QueryFlag == 1)
					{
				 		int result = (NumA + NumB) - NumC;
				 		
				        while (result < 0) 
				        {
				            NumA++;
				            result = (NumA + NumB) - NumC;
				        }						
					
						Solution = ((NumA+NumB) - NumC);
						Question = String.format("(%d + %d) - %d = ?", NumA, NumB, NumC);	
					}
				else
					{
						
					}
			}				
		
		Current_Question = Question;
		Current_Solution = Solution;
		
		return Question;
	}

	int CheckQuestion(String User_Solution)
	{
		//dummy code
		
		int IsCorrect = 0;
		int intValue = 0;
		
		try 
			{
				intValue = Integer.parseInt(User_Solution);
		}catch (NumberFormatException e) {
			IsCorrect = 0;
		}
		
		if(intValue == Current_Solution)
			{
				IsCorrect = 1;
	
			}
		else if(intValue != Current_Solution)
			{
				IsCorrect = 0;
							
			}
		else
			{
				
			}
			
			
		
		return IsCorrect;
		
	}

	
	void Increment_Correct()
	{
		Correct_Responses++;
		Total_Responses++;		
	}
	
	void Increment_Incorrect()
	{
		Total_Responses++;	
	}


	public int getCorrect_Responses() {
		return Correct_Responses;
	}

	public void setCorrect_Responses(int correct_Responses) {
		Correct_Responses = correct_Responses;
	}

	public int getDifficulty_Level() {
		return Difficulty_Level;
	}

	public void setDifficulty_Level(int difficulty_Level) {
		Difficulty_Level = difficulty_Level;
	}

	public int getTotal_Responses() {
		return Total_Responses;
	}

	public void setTotal_Responses(int total_Responses) {
		Total_Responses = total_Responses;
	}

	public int getCurrent_Solution() {
		return Current_Solution;
	}

	public void setCurrent_Solution(int current_Solution) {
		Current_Solution = current_Solution;
	}

	public String getCurrent_Question() {
		return Current_Question;
	}

	public void setCurrent_Question(String current_Question) {
		Current_Question = current_Question;
	}

}