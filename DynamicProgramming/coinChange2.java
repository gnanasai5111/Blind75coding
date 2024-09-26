You are given an integer array coins representing coins of different denominations and an integer amount representing a total amount of money.
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

- findWays(int index, int target, int coins[]): Recursive function to find combinations of coins to make the target amount.
- Base case: If index == 0, check if the target is divisible by the first coin, returning 1 if true, otherwise 0.
- notTake: Call findWays without including the current coin.
- take: Include the current coin if it doesn't exceed the target, recursively subtract its value from the target.
- Return: Sum of take and notTake, combining both approaches.
- change(int amount, int[] coins): Entry point, starts recursion with the full amount and all coins.

class Solution {
    public int findWays(int index,int target, int coins[]){
        if(index==0){
            if(target%coins[index]==0){
                return 1;
            }
            return 0;
        }
        int notTake=findWays(index-1,target,coins);
        int take=0;
        if(coins[index]<=target){
            take=findWays(index,target-coins[index],coins);
        }
        return take+notTake;
    }
    public int change(int amount, int[] coins) {
        return findWays(coins.length-1,amount,coins);
        
    }
}

Time complexity - exponential
Space complexity - o(N)
