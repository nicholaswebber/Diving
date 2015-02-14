/** 
 * Author: Nicholas A. Webber 
 * Date: 02/05/2015 
 * Unit 2 Assignment: Diving 
 */ 

import java.util.Scanner;

public class Diving {
	
	public static void main(String[] args) {
		new Diving();//Diving defines: keyboard input, and score.
						//Score is defined by the calculateScore method
							/**calculateScore defines difficulty, creates an array of scores, 
							sorts scores, adds scores skipping lowest and highest values, 
							and returns the score based on the arithmetic provided in the program instructions*/
								//Difficulty is defined by inputValidDegreeOfDifficulty which ensures a value 1.2 to 3.8
								/**Scores array is defined by inputAllScores which records 7 floating point values 
								within a range of 0 to 10 verified by the method inputValidScore*/
								//Sort(scores) 
	}
	
	private Scanner keyboard; // declaring variables

	public Diving() {
		keyboard = new Scanner(System.in);
		float score = calculateScore();

		System.out.print("\n\nDiver's Final Score: " + score);
	}

	public float calculateScore() { // calculate scores
		float difficulty = inputValidDegreeOfDifficulty(); // entering and validating diff
		float[] scores = inputAllScores(); // collects scores from judges
		scores = findMin(scores);// applying another method to the scores
		float score = 0;
		for (int i = 1; i < scores.length - 1; i++){
			// Array is sorted in ascending order, we're dropping the lowest and highest scores from the average.
			score += scores[i]; // Starting with 1, adds all scores by pulling from scores array.
		}
		return (score * difficulty * .6f); // returning final score
	}

	public float inputValidDegreeOfDifficulty() { // finding difficulty
		float difficulty = 0; // new variable
		while (true) { // input difficulty, loops infinitely
			System.out.print("Please Enter the Difficulty: ");
			difficulty = keyboard.nextFloat();
			System.out.println("");
			if (difficulty >= 1.2 && difficulty <= 3.8) // Verifies values from 1.2 to 3.8
				break; // if so then break
			else
				System.out.println("Invalid Difficulty."); // if not return fault and loop
		}
		return difficulty;
	}

	public float[] inputAllScores() { // creates the array of all scores
		float[] scores = new float[7]; // creates array size of 7
		for (int i = 0; i < 7; i++)
			// adds scores into array
			scores[i] = inputValidScore(i);//This is a clever way of incrementing the judgeNumber seen below with the scores
		return scores;
	}

	public float inputValidScore(int judgeNumber) { // checks if valid score, assuming 0-10 is the valid range
		float score = 0;
		while (true) { // loops infinitely until a valid score is entered
			System.out.print("Judge " + (judgeNumber + 1) + " score: ");
			score = keyboard.nextFloat();
			if (score >= 0 && score <= 10)// checks score
				break;
			else
				System.out.println("Invalid Score.");
		}
		return score;
	}
	
	/*public float[] sort(float[] array) {
		for(int i=0; i<array.length-1; i++){ //Sorting an array with values 0-6
			findMin(i, array); //Sorting in ascending order so we look for the minimum
			}
		return array;
	}*/
	
	private float[] findMin(float[] array) {
		
		for(int i=0; i<array.length; i++) { //1st loop start=0 i=0, 2nd i=1, 3rd i=2, 4th i=3 start=0 
			float min = array[0]; //First loop min = 7.5, 2nd loop 7.5, 3rd loop 7.5,	
			if(array[i]<min) {//Checks to see if the current array value is lower than the previously recorded minimum 
								//3rd if 5.7<7.5, 4th loop 8.25<5.7, 5th loop 6.72<5.7, 6th loop 10<5.7, 7th loop 3.46<5.7,
				swap(i,0,array);// Enters current array index, 0 index to be replaced as it was the previously recorded minimum 
								//3rd loop i=2, start=0, array{7.5, 9.5, 5.7, 8.25, 6.72, 10.0, 3.46}, 7th loop i=6 start=0 array{5.7, 7.5, 9.5, 8.25, 6.72, 10.0, 3.46}
			} 
			else if(i!=0 && array[i]<array[i-1]){//Cannot equal index 0 or out of bounds, above index 0 this checks the current index against the previous index 
				int lessThan=i-1;//Created a variable so as not to disturb the i index count.
				while(array[i]<array[lessThan]) {//Locates the area where the current value fits in order with other values
					lessThan--;
				}
				swap(i,lessThan+1,array);//Enters current array index, the index to be replaced
			}
		}
		return array;
	}
	//					3		2
	private void swap(int i, int replacedNumber, float[] array) { // 3rd loop j=2 replacedNumber=0, 4th replacedNumber=2
		float temp = array[i]; // Records the current value which must be moved, to allow another value to move into it's place 
		// 3rd loop temp = 5.7 (array[2]), 4th temp=8.25
		for(int j=i; j>replacedNumber; j--) {//Starts at current index, goes until the final index value that needs to be replaced 
		// 3rd main loop copies array[1] (value 9.5) to array[2] then array[0] (value 7.5) to array[1], 4th  copies [2] (9.5) to [3] 
			array[j] = array[j-1];// Replaces index [current-1] value with current index value (effectively moving all relevant values up a cell)
		}
		array[replacedNumber] = temp;// Finally replacing the minimum index value that needed replacing. 
		//3rd main loop copies temp (value 5.7) to array[0], 4th temp to [2] (8.25)
		}
}