# Project-1-submission
The following submissons can be found in the following areas

basicStats/SetOperations:

-Set Theory: Union Intersection Compliment

basicStats/StatsFormulas:

-Binomial
-Geometric
-Hyper Geometric
-Negative Binomial
-Possion
-Chevychev
-Permutations 
-Combinations
-Factorial (Use Big Integer)

basicStats/StatsLibrary:

-Mean Median Mode
-Standard Deviation / Variance
-Conditional and Bayes
-Determining Independance

MonteHall:

-Monte's 3 Doors / Deal or No Deal Program

birhdayProbability:

-Birthday Checker

CarCSV:

-CarFactory//Extra credit attempt using runtime to launch excel

Hand:

-Hands

Note:There is no github paper becuase you said if we already did the paper we don't need to write it again, however here is a link to another github I that I have made changes to
-https://github.com/Frankie199922/Soft-Eng-Client-Project 
Note: I belive that I was suppose choose someone from class to commit to however when it comes to the overall point of proving that I know how to work github I think that link does I know how to commit,push, and copy and repo at the least

Another Note:If commit times make it look like I did everything in a few hours it's becuase I have one repo for all my fall classes and I just sperated the project 1 portion for this submission when I was mostly done.

About Extra Credit Attempt:
For an extra credit attempt added a method to to the CarCSV assignment section where excel launches after writing all files for the assignment. 
The way this method works is by using java's Runtime class. The runtime is the program used in order to run java program on an actual computer. 
With this being said becuase runtime interacts directly with the computer I was able to use os.name is order to determine the user's operating system
and then launch Excel through Runtime to send command line arguments. The one flaw in my approach is that it only works if Excel is in the default location. 
When Attempting to add the functionality it looked like there may have been another method that uses the java Desktop class. The reason I went with runtime was due to errors importing
java.awt.Desktop and time running short for the project however given more time if I was able to get the import working then I belive the code would be more simplistic. To my knowledge 
it seems like the Desktop class has some sort of predefined application determined to launch given a file type so I believe desktop class might work by doing some system or actual desktop
scan of the computer in order to determine if the application needed for the file exisit and if it does the file will open. Also at the time of searching it seemed like the desktop class 
might have issues ineracting with MAC os and since I switch between windows and mac often runtime was to most convient solution given the allotted amount of time
