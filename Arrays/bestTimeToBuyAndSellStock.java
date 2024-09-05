You are given an array prices where prices[i] is the price of a given stock on the ith day.

You want to maximize your profit by choosing a single day to buy one stock and choosing a different day in the future to sell that stock.

Return the maximum profit you can achieve from this transaction. If you cannot achieve any profit, return 0.

Example 1:

Input: prices = [7,1,5,3,6,4]
Output: 5
Explanation: Buy on day 2 (price = 1) and sell on day 5 (price = 6), profit = 6-1 = 5.
Note that buying on day 2 and selling on day 1 is not allowed because you must buy before you sell.

Approach 1 : Brute Force 

- In order to get maximum profit, we have a choose a day to buy one stock and choose a different day in the future to sell the stock.
- So for each day, we need to check whether if we can sell it for more than the current day in the future or next days 
- For each element in the array , check if there is greater element to the right .
- Repeat this process and find the max profit.

class Solution {
    public int maxProfit(int[] prices) {
        int max=0;
        for(int i=0;i<prices.length;i++){
            for(int j=i+1;j<prices.length;j++){
                if(prices[j]>prices[i]){
                    max=Math.max(max,prices[j]-prices[i]);
                }
            }
        } 
        return max;    
    }
}

Time complexity - o(N*N)
Space complexity - o(1)

Approach 2 : (Suffix array)

- We want to find the largest price for each day that comes after that day. To do this efficiently, we use a suffix array.
- For the last day ,  the largest price is just the price on that day itself because there are no future days.
- For each previous day,we compare the price of that day with the largest price of the next day. 
- This will help us to get the next greater element for each element
- Finally traverse the array and find max difference to get max profit

class Solution {
    public int maxProfit(int[] prices) {
        int max=0;
        int maxFuturePrices[]=new int[prices.length];
        for(int i=prices.length-1;i>=0;i--){
            if(i==prices.length-1){
                maxFuturePrices[i]=prices[i];
            }
            else{
                maxFuturePrices[i]=Math.max(prices[i],maxFuturePrices[i+1]);
            }
        } 
        for(int i=0;i<prices.length;i++){
            if(maxFuturePrices[i]-prices[i]>max){
                max=maxFuturePrices[i]-prices[i];
            }
        }
        return max;    
    }
}

Time complexity - o(N)
Space complexity - o(N)

Approach 3 :

- Initially we take day 0 as lowest price . 
- we the traverse the next days and see if we can sell the stock for more profit.
- And also for each day , we have check for the lowest price so far. This is done to get maxProfit.

class Solution {
    public int maxProfit(int[] prices) {
        int maxProfit=0;
        int lowestPrice=prices[0]; 
        for(int i=1;i<prices.length;i++){
            maxProfit=Math.max(maxProfit,prices[i]-lowestPrice);
            lowestPrice=Math.min(lowestPrice,prices[i]);
        }
        return maxProfit;    
    }
}

Time complexity - o(N)
Space complexity - o(1)
