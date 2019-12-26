#include<stdio.h>
#include<stdlib.h>
#include<string.h>
#include<time.h>

// define a date stucture used to contain the question and answers
struct user
{
	char que[sizeof(char) * 256];
	char ans[10][50];
	char correctAns[10][5];
}user_group[1000];

int main(void)
{
	void gate(void);
	printf("********************************************************\n");
	printf("WELCOME TO MR.CHAOZY's MULTIPLE CHOICES QUESTION SYSTEM\n");
	printf("********************************************************\n");
	printf("PLEASE FOLLOW THE INSTRUCTION OR IT WILL CRASH EASILY\n\n");
	gate();
	return 0;
}

void gate(void)
{
	void write(void);
	void edit(void);
	void examination(void);
	void readFile(void);
	void displayTheFile(void);
	void add(void);
	printf("**************************************************\n");
	printf("   0. Create a new .txt file to store your questions\n");
	printf("   1. Edit the file\n");
	printf("   2. Start the examination\n");
	printf("   3. Display the whole File\n");
	// where you can see a bug, the last line is read twice

	printf("   4. Append a question in the end of the file.\n");
	printf("   5. Exit\n");

	char instruction;
	printf("**************************************************\n");
	printf("		So, what is your choice?\n");
	scanf("%s", &instruction);

	//simple FSM, choose the next action
	switch(instruction)
	{
		case '0': 
			write(); 
			break;
		case '1': 
			edit(); 
			break;
		case '2': 
			examination(); 
			break;
		case '3':
			displayTheFile();
			break;
		case '4': 
			add();
			break;
		case '5':
			break;
		default: 
			printf("Please respect my system and follow the instruction\n");
		gate();
	}
}

//read the data in the file and write to user_group array
void readFile(void)
{
	FILE *fp;
	fp = fopen("quiz.txt", "r");
	int MAXSIZE = 1000, index = 0;
	char str[1000];
	while (!feof(fp))
	{	
		// ptr points to each charater in the line
		int spaceCount = 0, ptr = 0, indOfAns = -2, indOfCorrect = 0;
		//read one line from the file
		fgets(str, MAXSIZE, fp);

		int iOfQue = 0, iOfAns = 0, iOfCorrect = 0;
		//"@"" is used as the terminator
		while(str[ptr] != '@') 
		{	
			//space is used as separator here
			if(str[ptr] == '\t')
			{	
				spaceCount++;
				indOfAns++;
				ptr++;
				iOfAns = 0;
			}
			if (str[ptr] == '@') break;

			//the question part is one space apart from the index
			if (spaceCount == 1)
			{
				strncpy(&user_group[index].que[iOfQue], &str[ptr], 1);
				iOfQue++;
			}
			// another space between the quesitons and possible answers
			if (spaceCount > 1)
			{				
				strncpy(&user_group[index].ans[indOfAns][iOfAns], &str[ptr], 1);
				iOfAns++;
			}
			ptr++;

			// stop the loop once the terminator @ has been met
			if (str[ptr] == '@') break;
		}

		//read the valid answers part
		ptr++;
		spaceCount = 0;
		while(str[ptr] != '\n')
		{
			if(str[ptr] == '\t')
			{	
				spaceCount++;
				indOfCorrect++;
				ptr++;
			}
			if(str[ptr] == '\n')
				break;
			strncpy(&user_group[index].correctAns[indOfCorrect][iOfCorrect], &str[ptr], 1);
			iOfCorrect++;
			ptr++;
		}
		index++;
	}
	fclose(fp);
}

void displayTheFile(void)
{
	readFile();
	int index = 0;
	// loop through the array
	while (*user_group[index].que)
	{
		printf("Quesiton%i. %s\n", index, user_group[index].que);
		printf("	Possible Answers:\n");
		int iOfAns = 0, iOfCorrect = 0;
		while (*user_group[index].ans[iOfAns])
		{
			printf("	%i. %s\n", iOfAns, user_group[index].ans[iOfAns]);
			iOfAns++;
		}
		printf("	Correct Answers:\n");
		while (*user_group[index].correctAns[iOfCorrect])
		{
			printf("	%i. %s\n", iOfCorrect, user_group[index].correctAns[iOfCorrect]);
			iOfCorrect++;
		}
		index++;
		puts("\n");
	}
	gate();
}

//used to write the questions and answers into an array then return it
char* writeQuestion(int i, FILE *fp)
{
	int j;
	int nOfAns, nOfCorrect;
	char n[5], *line;
	line = malloc(1000);
	// convert the index from integer to string
	sprintf(n, "%d", i);

	// put all the information of a question into one string(array)
	strcat(line, n);
	strcat(line, "\t");

	printf("******Enter the NO.%d question: *******\n", i);
	//the format is used to accept space in the input
	scanf(" %256[0-9a-zA-Z!?() ]", user_group[i].que);

	strcat(line, user_group[i].que);
	strcat(line, "\t");

	// Input possible answers
	printf("%s\n", "How many possible answers do you have(at most 10): ");
	scanf("%d", &nOfAns);

	//ensure the number input is within valid range
	if(nOfAns < 0 || nOfAns > 10)
	{
			printf("Not within valid range. \n");
			gate();
			return 0;
	}
	//scan the possible answers
	for (j = 0; j < nOfAns; j++)
	{
			printf("Enter the NO.%d possible answer: \n", j);
			scanf(" %50[0-9a-zA-Z!?() ]", user_group[i].ans[j]);

			strcat(line, user_group[i].ans[j]);
			strcat(line, "\t");
	}

	strcpy(user_group[i].ans[j+1], "@");
	strncat(line, "@", 1);

	printf("%s", "How many correct answers do you have(at most 10): ");
	scanf("%d", &nOfCorrect);

	if(nOfCorrect < 0 || nOfCorrect > 10)
		{
			printf("Not within valid range. \n");
			gate();
			return 0;
		}
	//scan the indexes of valid answers
	for (j = 0; j < nOfCorrect; j++)
		{	
			//input the indexes of the correct answers
			printf("Enter the index of NO.%d correct answer: \n", j);
			scanf(" %5[0-9a-zA-Z!?() ]", user_group[i].correctAns[j]);

			strcat(line, user_group[i].correctAns[j]);
			strcat(line, "\t");
		}
	strcat(line, "@");
	strcat(line, "\n");
	return line;
}

//append the question in the end
void add(void)
{
	readFile();
	FILE *fp;
	fp = fopen("quiz.txt", "a");

	//find how many questions has exited in the file 
	int index = 0, nOfQues = 0;
	while(*user_group[index].que)
	{	
		nOfQues++;
		index++;
	}
	nOfQues--; 

	//read the question from the input and write to the last line
	char *newLine;
	newLine = writeQuestion(nOfQues, fp);
	fprintf(fp, "%s", newLine);
	free(newLine);
	fclose(fp);
	gate();
}

void swap(int *a, int *b)
{
	int temp;
	temp = *a;
	*a = *b;
	*b = temp;
}

//read the article from www.geeksforgeeks.org/shuffle-a-given-array-using-fisher-yates-shuffle-algorithm/
// shuffle the the intgers in an array, which is used to contain the index of questions or answers
void shuffle(int arr[], int leng)
{	
	// Use a different seed value so that we don't get same 
    // result each time we run this program 
	srand(time(NULL));

	int i, j;
	for (i = leng - 1; i > 0; i--)
	{
		j = rand() % (i + 1);
		swap(&arr[i], &arr[j]);
	}
}

//check if the answer is correct
int assert(int indOfQue, char answer)
{
	int i = 0;
	while (strncmp(user_group[indOfQue].correctAns[i], "@", 1) != 0)
	{
		if (!strncmp(&answer, user_group[indOfQue].correctAns[i], 1))
			return 1;
		i++;
	}
	return 0;
}

// ask for answers from user and return the score
int test(int indOfQue)
{
	int score = 0, nOfCorrect, j;
	char answer[5];

	puts("\n");
	printf("%s\n","How many correct answers for this question: ");
	scanf("%i", &nOfCorrect);

	for (j = 0; j < nOfCorrect; j++)
		{	
			//input the indexes of the correct answers
			printf("Enter the index of NO.%d correct answer: \n", j);
			scanf("%s", answer);
			// calculate the score
			if (!assert(indOfQue, *answer))
				score -= 1;
			else
				score += 1;
		}
	return score;
}

// display the questions and possible answers
void DisplayQuestion(int index, int displayOrder)
{	
	int i = 0, j, nOfAns = 0;
	while(*user_group[index].ans[i])
	{
		nOfAns += 1;
		i++;
	}
	int arr[nOfAns], ins[nOfAns];

	for (i = 0; i < nOfAns; i++)
		arr[i] = i;
	shuffle(arr, nOfAns);

	printf("********* Questions NO.%i ************\n", displayOrder);
	printf("\t%s\n", user_group[index].que);

	for (i = 0; i < nOfAns; i++)
		printf("\t%i %s\n", arr[i], user_group[index].ans[arr[i]]);
	// i am suposed to display the index from zero to the largest here
	// but in that case, user will enter the index of answer
	// in a different style from what i have stored in the array
	// so i can only output the indexes of the answers in the array
}

void examination(void)
{
	readFile();
	int nOfQues = 0, index = 0, actualMark = 0;
	//find how many questions in the file
	while(*user_group[index].que)
	{	
		nOfQues++;
		index++;
	}
	if (!nOfQues)
	{
		printf("There is no quesiton in the file.\n");
		gate();
		return ;
	}
	nOfQues--;  // due to the bug that the last line is always read twice

	//create an array with nOfQues integer elements
	int arr[nOfQues], i;
	for (i = 0; i < nOfQues; i++)
		arr[i] = i;
	shuffle(arr, nOfQues);

	//display question one at a time and start the test
	for (i = 0; i < nOfQues; i++)
	{
		DisplayQuestion(arr[i], i);
		actualMark += test(arr[i]);
	}
	printf("You got %i points\n", actualMark);
	gate();
}

//Enter the questions into an empty file
void write(void)
{	
	FILE *fp;
	fp = fopen("quiz.txt", "w");

	int nOfQue, i;

	char n[5], *line;
	printf("%s\n", "----------Quetions input section-----------");
	printf("%s\n", "How many questions are you gonna input: ");
	scanf("%i", &nOfQue);

	for (i = 0; i < nOfQue; i++)	
	{
		line = writeQuestion(i, fp);
		fputs(line, fp);
		free(line);
	}
	fclose(fp);
	gate();
}

// it copies all of the date in the file into a new one
// then the editing question is met, the new one is writed in
// finally the old one is replaced by the new one
void edit(void)
{
	readFile();
	int MaxChar = 1000;

	FILE *fp1, *fp2;
	int indexOfTar, currentLine = 0;
	char temp[] = {"temp.txt"}, old[] = {"quiz.txt"};

	char str[256];
	char *newLine;
	fp1 = fopen("quiz.txt", "r");
	fp2 = fopen("temp.txt", "w");

	printf(" Input the index of the question you want to edit: ");
    scanf("%d", &indexOfTar);

    newLine = writeQuestion(indexOfTar, fp1);

    indexOfTar++;
    //loop till the end of the file
    while(!feof(fp1))
    {
        fgets(str, MaxChar, fp1);
 
        if(!feof(fp1))
        {
        	currentLine++;
    		if(currentLine != indexOfTar)
    			fputs(str, fp2);
    		else
    			fputs(newLine, fp2);
    	}
    }
    fclose(fp1);
    fclose(fp2);
    // replace the file
    rename(temp, old);
    printf(" Replacement did successfully..!! \n");
    free(newLine);
    gate();
}