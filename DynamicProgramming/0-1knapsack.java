You are given weights and values of items, and put these items in a knapsack of capacity W to get the maximum total value in the knapsack.
Note that we have only one quantity of each item.
In other words, given two integer arrays val and wt which represent values and weights associated with items respectively.
Also given an integer W which represents knapsack capacity, find out the maximum sum values subset of val[] such that the sum of the 
weights of the corresponding subset is smaller than or equal to W.
You cannot break an item, either pick the complete item or don't pick it (0-1 property).

Examples :

Input: W = 4, val[] = {1,2,3}, wt[] = {4,5,1}
Output: 3
Explanation: Choose the last item that weighs 1 unit and holds a value of 3.

First approach (Recursion)

- Base Case: If index reaches the end of the items (index == wt.length), return 0 since no more items are available.
- notTake: Skips the current item and moves to the next item.
- take: Takes the current item if its weight (wt[index]) is less than or equal to the remaining capacity (W), adds its value to the 
  result, and reduces the available weight by the item's weight.
- The function returns the maximum of either taking or not taking the current item.

class Solution {
    static int findMaxSum(int index, int W,int wt[],int val[]){
        if(index==wt.length){
            return 0;
        }
        int notTake=findMaxSum(index+1,W,wt,val);
        int take=Integer.MIN_VALUE;
        if(wt[index]<=W){
            take=val[index]+findMaxSum(index+1,W-wt[index],wt,val);
        }
        return Math.max(notTake,take);
    }
    // Function to return max value that can be put in knapsack of capacity W.
    static int knapSack(int W, int wt[], int val[]) {
        // your code here
        return findMaxSum(0,W,wt,val);
    }
}

Time complexity - o(2^N)
Space complexity - o(N)


Approach 2 :

- The code solves the 0/1 Knapsack problem using recursion with memoization.
- A 2D memo[][] array is created to store the maximum value that can be obtained using the items starting from a given index with
  a certain remaining knapsack capacity.
- Initially, memo[][] is filled with 0 to indicate that no subproblem has been solved yet.
- The base case returns 0 when all items have been processed.
- Before performing any calculations, the algorithm checks if the result for the current subproblem has already been computed 
  and stored in memo[][].
- If so, the stored result is returned directly, avoiding redundant calculations. 
- The function explores two options: not taking the current item (moving to the next item) or taking the current item 
  (if its weight fits in the knapsack) and then calculating the maximum value that can be obtained. 
- The computed result is stored in memo[][] for future reference.
- The final result is obtained by calling the recursive function for the initial conditions and returning the maximum value
  that can be placed in the knapsack with the given constraints.

class Solution {
    static int findMaxSum(int index, int W,int wt[],int val[],int memo[][]){
        if(index==wt.length){
            return 0;
        }
        if(memo[index][W]!=0){
            return memo[index][W];
        }
        int notTake=findMaxSum(index+1,W,wt,val,memo);
        int take=Integer.MIN_VALUE;
        if(wt[index]<=W){
            take=val[index]+findMaxSum(index+1,W-wt[index],wt,val,memo);
        }
        return memo[index][W]=Math.max(notTake,take);
    }
    // Function to return max value that can be put in knapsack of capacity W.
    static int knapSack(int W, int wt[], int val[]) {
        // your code here
          int[][] memo = new int[wt.length][W + 1];
        return findMaxSum(0,W,wt,val,memo);
    }
}

Time complexity - o(N*W)
Space complexity - o(N*W)

Approach 3 : Tabulation

- This code solves the 0/1 Knapsack problem using dynamic programming. 
- The dp table is initialized with dimensions [wt.length+1][W+1], where wt.length is the number of items and W is the knapsack capacity.
- The outer loop iterates through items, and the inner loop iterates through possible capacities from 1 to W.\
- If the current item can fit into the knapsack (wt[i-1] <= j), it updates the dp[i][j] by choosing the maximum of either not
  taking the item or taking it.
- The final result is stored in dp[wt.length][W], representing the maximum value that can be placed in the knapsack with the given
  constraints.

class Solution {

    // Function to return max value that can be put in knapsack of capacity W.
    static int knapSack(int W, int wt[], int val[]) {
        // your code here
          int[][] dp = new int[wt.length+1][W + 1];
          for(int i=1;i<dp.length;i++){
              for(int j=1;j<=W;j++){
                 if (wt[i-1] <= j) {  // If the item can fit in the knapsack
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i-1][j - wt[i - 1]] + val[i-1]);
                 } else {
                    dp[i][j] = dp[i - 1][j];  // Skip the current item
                 }
              }
          }
        return dp[dp.length-1][W];
    }
}

Time complexity - o(N*W)
Space complexity - o(N*W)

Fourth Approach :

- This code implements the 0/1 Knapsack problem using dynamic programming with space optimization. 
- The dp[] array is used to store the maximum value that can be achieved for different knapsack capacities.
- In each iteration over the items (i), a new array curr[] is created to store the values for the current item. 
- If the current item can fit into the knapsack (wt[i-1] <= j), the curr[j] value is updated with the maximum of not taking the
  item (dp[j]) or taking it (dp[j - wt[i-1]] + val[i-1]). 
- The dp[] array is updated after each item to reflect the current state of the knapsack. 
- This allows for space optimization since only two arrays (dp[] and curr[]) are used instead of a full 2D table. 
- The final result, which is the maximum value that can fit into the knapsack of capacity W, is stored in dp[W].

class Solution {

    // Function to return max value that can be put in knapsack of capacity W.
    static int knapSack(int W, int wt[], int val[]) {
        // your code here
          int[] dp = new int[W + 1];
          for(int i=1;i<=wt.length;i++){
               int[] curr = new int[W + 1];
              for(int j=1;j<=W;j++){
                 if (wt[i-1] <= j) {  // If the item can fit in the knapsack
                    curr[j] = Math.max(dp[j], dp[j - wt[i - 1]] + val[i-1]);
                 } 
                 else{
                     curr[j]=dp[j];
                 }
              }
              dp=curr;
          }
        return dp[W];
    }
}

Time complexity - o(N*W)
Space complexity - o(W)

