In order to use this program, go to terminal:

1) change directory to prefixer folder

2) $javac *.java

3) $java prefixer file_name
The above will give you the infix version of the expression inside file_name

4) $java prefixer -r file_name
5) $java prefixer file_name -r
The two above will give you the reduced infix version of the expression inside p1.txt

--------------------------------------------------------------------------

NOTE: Input expressions are highly restrictive, requiring a space between each input operator/operand

Dependencies: The program uses In.java & StdIn.java (for input from a file, taken from Professor Kevin Wayne's booksite) and Queue.java (for creation of a token list, created by me)

---------------------------------------------------------------------------

About Program: This program uses Knuth's general principle of evaluating an expression:

1) Parse Infix String

2) Convert from Infix to Postfix (in this case, prefix)

3) Evaluate the Postfix(Prefix) expression

For step 1), I used a method to parse the string from input into a queue of strings. 
For Step 2), I first represented the queue of strings into a binary expression tree. 
In order to convert to binary expression tree, I use Downey et al.'s method (How to Think like a Computer Scientist).
After having a binary tree representation, printing out the prefix (or postfix, infix) version is fairly straightforward, adding parentheses to the output as appropriate. 
To reduce the expression, I created a class called Reducer that has similar methods to Prefix, but reduces the sub-expressions as they are added to the binary expression tree. 
In order words, I am reducing from the bottom (children) of the tree all the way up to the root. The Reducer class aims to reduce an expression into polynomial form of arbitrary number of variables. 






