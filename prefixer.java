/*
Name: prefixer.java
Execution: java prefixer {-r} file_name
Dependencies: Queue.java, In.java, StdIn.java, Reducer.java

This program converts an expression from a file input to prefix form.
The input is highly restrictive, requiring all operators/operands to be one
space from each other. The variable/number input allowed is in [a-zA-Z_0-9],
while the operator input is {"+", "-", "/", "*"}
*/

public class prefixer
{
	
	//Linked Structure for Binary Expression Tree
	private class TreeNode
	{
		String item;
		boolean variable;
		TreeNode left, right;
			
		public TreeNode()
		{
			return;
		}
		
		public TreeNode(String item, TreeNode left, TreeNode right)
		{
			
			this.item = item;
			this.left = left;
			this.right = right;		
		}		
	}
	
	//prints result using Preorder traversal of binary tree
	public void printPrefix(TreeNode tree)
	{
		if (tree == null) return;
		
		String current_item = tree.item;

		if (current_item.matches("\\+|-|/|\\*"))
			{
				System.out.print("( ");
				System.out.print(tree.item + " ");
				printPrefix(tree.left);
				printPrefix(tree.right);
				System.out.print(") ");
			}
			
		else 
		{
			System.out.print(tree.item + " ");
			printPrefix(tree.left);
			printPrefix(tree.right);
		
		}
		
	}
	

	//Determines if s is expected token, deletes if it is
	public static boolean getToken(Queue<String> tokenlist, String s)
	{
		if (tokenlist.peek() == null) return false;
		
		if (tokenlist.peek().equals(s))
		{
	
			String removed = tokenlist.dequeue();
			return true;
		}
		else return false;
	}
	
	//Gets the number/expression ready for binary expression tree
	public TreeNode getNumber(Queue<String> tokenlist)
	{
		if (getToken(tokenlist, "("))
		{
			TreeNode x = getSumDiff(tokenlist);
			boolean y = getToken(tokenlist, ")");
			return x;
		}
		else
		{
			
			String x = tokenlist.peek();
			if (!x.matches("[a-zA-Z_0-9]*")) return null;
			String removed = tokenlist.dequeue();
			TreeNode number = new TreeNode();
			number.item = removed;
			return number;
		}
	}
	
	//Adds sum/difference to binary expression tree
	public TreeNode getSumDiff(Queue<String> tokenlist)
	{
		TreeNode a = getProdQuo(tokenlist);
		if (getToken(tokenlist, "+"))
		{
			TreeNode b = getSumDiff(tokenlist);
			return new TreeNode("+", a, b);
		}
		else if (getToken(tokenlist, "-"))
		{
			TreeNode b = getSumDiff(tokenlist);
			return new TreeNode("-", a, b);
		}
		else
		{ 
			return a;
		}
	}
	
	//Adds the product/quotient for binary expression tree
	public TreeNode getProdQuo(Queue<String> tokenlist)
	{
		
		TreeNode a = getNumber(tokenlist);
	
		if (getToken(tokenlist, "*"))
		{
		
			TreeNode b = getProdQuo(tokenlist);
			return new TreeNode("*", a, b);
			
		}
		else if (getToken(tokenlist, "/"))
		{
			TreeNode b = getProdQuo(tokenlist);
			return new TreeNode("/", a, b);
		}
		else
		{
			return a; }
	}
	
	//start method for converting to binary expression tree/prefixer
	public void start(Queue<String> tokenlist, boolean reduce)
	{
		TreeNode a = getSumDiff(tokenlist);
		printPrefix(a);
		System.out.println();
	}
	
	public static void main(String[] args)
	{
		
		//Determines if input expects output to be reduced
	 	int args_length = args.length;
		boolean reduce = false;
		int file_numb = 0;
		if (args_length == 2)
		{
			for (int i = 0; i < 2; i++)
			{
				if (args[i].equals("-r"))
				{
					reduce = true;
					file_numb = 1 - i;
				}		
			}
		}
	
		//If reduction expected, calls Reducer class
		if (reduce)
		{
			Reducer tree = new Reducer();
			In in = new In(args[file_numb]);
			Queue<String> tokenlist = new Queue<String>();

			while(!in.isEmpty())
			{
				String str = in.readString();
				tokenlist.enqueue(str);

			}
			tree.startReducer(tokenlist, reduce);
		}
		
		//Else uses methods in this class for normal conversion
		//to prefix
		else 
		{
		prefixer tree = new prefixer();
		In in = new In(args[file_numb]);
		Queue<String> tokenlist = new Queue<String>();
	
		while(!in.isEmpty())
		{
			String str = in.readString();
			tokenlist.enqueue(str);
			
		}
		tree.start(tokenlist, reduce);
		}	
	}
}