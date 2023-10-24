import java.util.Random;

public class Question_Generator {

	public static void main(String[] args) 
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
		 
		 DiffNum = random.nextInt(5);
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
		
		System.out.printf("%s", Question);
		System.out.printf("\n %d", Solution);
	}

}
