You are given an integer array coins representing coins of different denominations and an integer amount representing a 
total amount of money.
Return the fewest number of coins that you need to make up that amount. If that amount of money cannot be made up by any combination 
of the coins, return -1.
You may assume that you have an infinite number of each kind of coin.

Example 1:

Input: coins = [1,2,5], amount = 11
Output: 3
Explanation: 11 = 5 + 5 + 1


First approach : Recursion

- We follow a take or not-take strategy.
- Base case: At index 0, check if the target can be achieved using the coin at that index. 
- If yes, return the number of coins needed; otherwise, return a large value (representing an invalid result).
- For the "not-take" case, move to the previous coin and continue recursion.
- For the "take" case, ensure the coin's value is less than or equal to the target.
- Also, prevent returning a large value that could cause overflow.
- Finally, return the minimum of the take and not-take results.

class Solution {
    public int findMinCoins(int index,int coins[],int target){
        if(index==0){
            if(target%coins[index]==0){
                return target/coins[index];
            }
            return Integer.MAX_VALUE;
        }
        int notTake=findMinCoins(index-1,coins,target);
        int take=Integer.MAX_VALUE;
        if(coins[index]<=target){
            int res=findMinCoins(index,coins,target-coins[index]);
            if(res!=Integer.MAX_VALUE){
                take=1+res;
            }
        }
        return Math.min(notTake,take);
    }
    public int coinChange(int[] coins, int amount) {
        int res=findMinCoins(coins.length-1,coins,amount);
        return res==Integer.MAX_VALUE?-1:res;        
    }
}

Similar approach : (Takes bit more time)

- This method recursively determines the minimum number of coins required to achieve the given target using the available coins.
- Base Case: If the target becomes 0, return 0, as no more coins are needed.
Recursive Case:
- Iterate over the coin denominations (starting from the largest coin).
- For each coin, check if it can be included (i.e., if it is less than or equal to the current target).
- Recursively call findMinCoins with the remaining target after including the coin (i.e., target - coin).
- If the recursive result is valid (not equal to Integer.MAX_VALUE), update minWays to store the minimum number of coins used so far.
- Return the minimum number of coins found.

class Solution {
    public int findMinCoins(int coins[],int target){
        if(target==0){
            return 0;
        }
        int minWays=Integer.MAX_VALUE;
        for(int i=coins.length-1;i>=0;i--){
            if(coins[i]<=target){
                int res=findMinCoins(coins,target-coins[i]);
                if(res!=Integer.MAX_VALUE){
                    minWays=Math.min(minWays,1+res);
                }
            }
        }
        return minWays;
    }
    public int coinChange(int[] coins, int amount) {
        int res=findMinCoins(coins,amount);
        return res==Integer.MAX_VALUE?-1:res;        
    }
}

Time complexity - exponential
space complexity - o(N)

Approach 2 - Memoization

- This Java solution solves the Coin Change problem using recursion with memoization.
- findMinCoins calculates the minimum number of coins needed for a given target amount.
- Base case: When the index is 0, if the target is divisible by the smallest coin, return the number of coins; 
  otherwise, return Integer.MAX_VALUE.
- The memoization table is used to store previously computed results, preventing redundant calculations.
- Two choices are explored: not taking the current coin (move to the previous coin) or taking the coin 
  (if its value is less than or equal to the target).
- If a valid solution is found by taking the coin, add 1 to the result.
- The coinChange method initializes the memo table and calls findMinCoins starting from the largest coin.
- If no valid solution is found, return -1; otherwise, return the minimum number of coins required.
- Memoization reduces the number of recursive calls, optimizing the solution

class Solution {
    public int findMinCoins(int index,int coins[],int target,int memo[][]){
        if(index==0){
            if(target%coins[index]==0){
                return target/coins[index];
            }
            return Integer.MAX_VALUE;
        }
        if(memo[index][target]!=0){
            return memo[index][target];
        }
        int notTake=findMinCoins(index-1,coins,target,memo);
        int take=Integer.MAX_VALUE;
        if(coins[index]<=target){
            int res=findMinCoins(index,coins,target-coins[index],memo);
            if(res!=Integer.MAX_VALUE){
                take=1+res;
            }
        }
        return memo[index][target]=Math.min(notTake,take);
    }
    public int coinChange(int[] coins, int amount) {
        int memo[][]=new int[coins.length][amount+1];
        int res=findMinCoins(coins.length-1,coins,amount,memo);
        
        return res==Integer.MAX_VALUE?-1:res;        
    }
}

Time complexity -O(N * amount)
space complexity - O(N * amount)

Third approach : Tabulation

- This Java solution uses dynamic programming to solve the Coin Change problem.
- An array dp[] is created with size amount + 1, where each index represents the minimum number of coins needed to achieve that amount.
- Base case: dp[0] = 0, meaning 0 coins are needed to achieve an amount of 0.
- For each amount from 1 to amount, the algorithm finds the minimum number of coins required by checking each coin in the coins[] array.
- For each coin that is less than or equal to the current amount, it calculates the remainder (rem = i - coin[j]).
- If the value for the remainder (dp[rem]) is valid (i.e., not Integer.MAX_VALUE), it calculates the minimum number of coins
  required by comparing min with 1 + dp[rem].
- The minimum value is stored in dp[i], representing the fewest coins needed to form that amount.
- After filling the dp[] array, the solution returns the value in dp[amount].
- If dp[amount] is still Integer.MAX_VALUE, it means the amount cannot be formed with the given coins, so -1 is returned.
  Otherwise, the minimum number of coins required is returned.

class Solution {
    public int coinChange(int[] coins, int amount) {
        int dp[]=new int[amount+1];
        dp[0]=0;
        for(int i=1;i<dp.length;i++){
            int min=Integer.MAX_VALUE;
            for(int j=0;j<coins.length;j++){
                if(coins[j]<=i){
                    int rem=i-coins[j];
                     if (dp[rem] != Integer.MAX_VALUE) {
                        min = Math.min(min, 1 + dp[rem]);
                    }
                }
            }
            dp[i]=min;
        } 
        return dp[amount]==Integer.MAX_VALUE?-1:dp[amount];       
    }
}
Time complexity -O(N * amount)
space complexity - O(amount)
