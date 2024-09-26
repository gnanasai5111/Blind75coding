You are given an integer array coins representing coins of different denominations and an integer amount representing a total
amount of money.
Return the number of combinations that make up that amount.
If that amount of money cannot be made up by any combination of the coins, return 0.
You may assume that you have an infinite number of each kind of coin.
The answer is guaranteed to fit into a signed 32-bit integer.

Example 1:

Input: amount = 5, coins = [1,2,5]
Output: 4
Explanation: there are four ways to make up the amount:
5=5
5=2+2+1
5=2+1+1+1
5=1+1+1+1+1

Approach 1 : (Recursion)

- findWays(int index, int target, int coins[]): Recursive function to find the number of ways to make the target amount using 
  the coins array.
- Base Case 1: If target == 0, it returns 1 because an exact combination of coins has been found.
- Base Case 2: If target < 0 or index < 0, it returns 0 because either the target has gone negative or no coins are left to use.
- Recursive Call:
- findWays(index-1, target, coins): This branch excludes the current coin (move to the next smaller index).
- findWays(index, target - coins[index], coins): This branch includes the current coin (reduce the target by the value of the current 
    coin, keeping the same index to allow multiple uses of that coin).
- change(int amount, int[] coins): This method starts the recursive process by calling findWays with the full amount and the complete
    set of coins.
- This approach uses a combination of "take" (include the current coin) and "not-take" (exclude the current coin) to explore all
    possible combinations recursively.

class Solution {
    public int findWays(int index,int target, int coins[]){
        if(target==0){
            return 1;
        }
        if(target<0 || index < 0){
            return 0;
        }
        return findWays(index-1,target,coins)+findWays(index,target-coins[index],  coins);
    }
    public int change(int amount, int[] coins) {
        return findWays(coins.length-1,amount,coins);
        
    }
}

Time complexity - exponential
Space complexity - o(N)

Approach 2 :

- The code defines a recursive function with memoization to calculate the number of ways to make a given amount using a set of coins.
- It checks if the target is 0 (base case for a valid combination), returns 0 if the target becomes negative or no more coins 
  are available, and uses a 2D memo array to store previously computed results for specific combinations of index (coin) and 
  target (amount). 
- The recursion explores two options: excluding the current coin or including it, summing both possibilities to get the total
  number of combinations.
- The memo array ensures the function doesn't recompute results for the same subproblem, improving efficiency.

class Solution {
    public int findWays(int index,int target, int coins[],int memo[][]){
        if(target==0){
            return 1;
        }
        if(target<0 || index < 0){
            return 0;
        }
        if(memo[index][target]!=0){
            return memo[index][target];
        }
        return memo[index][target]=findWays(index-1,target,coins,memo)+findWays(index,target-coins[index], coins,memo);
    }
    public int change(int amount, int[] coins) {
        int memo[][]=new int[coins.length][amount+1];
        return findWays(coins.length-1,amount,coins,memo);
        
    }
}

Time complexity - o(N*amount)
Space complexity - o(N* amount)
