You are given an integer array nums. You are initially positioned at the array's first index, and each element in the array represents
your maximum jump length at that position.
Return true if you can reach the last index, or false otherwise.

Example 1:

Input: nums = [2,3,1,1,4]
Output: true
Explanation: Jump 1 step from index 0 to 1, then 3 steps to the last index.


First approach : Recursion

- This code defines a solution for determining if it is possible to reach the end of an array by jumping through it. 
- The function canJump starts at the first index (position 0) and recursively calls the helper method canReachEnd to check if it
  can jump from the current index to the next.
- The canReachEnd function first checks if the current index is the last one in the array, in which case it returns true because 
  the end has been reached.
- If the current index is out of bounds or the number of jumps allowed from that index is zero, it returns false, as no further
  progress is possible.
- It then loops through possible jumps from the current position (up to the maximum allowed by nums[index]), recursively checking 
  each possible jump position. 
- If any of these recursive calls return true, it means reaching the end is possible, so the function returns true. 
- If no valid jump leads to the end, it returns false.

class Solution {
    public boolean canReachEnd(int index,int nums[]){
        if(index==nums.length-1){
            return true;
        }      
        int jumpCount=nums[index];
        if(index>=nums.length || jumpCount==0){
            return false;
        }
        for(int i=1;i<=jumpCount;i++){
            if(canReachEnd(index+i,nums)){
                return true;
            }
        }
        return false;
    }
    public boolean canJump(int[] nums) {
       return canReachEnd(0,nums);
        
    }
}

Time complexity - Exponential o(N^N)
Space complexity - o(N)

Approach 2 : Memoization

- This code is an optimized solution for determining whether you can jump to the end of an array from the first index.
- It introduces memoization to avoid redundant calculations in the recursive function.
- The main function, canJump, initializes a memoization array memo[] of the same length as the input array nums[].
- This memo array is used to store the state of each index:
  2 indicates that the index can reach the end.
  1 indicates that the index cannot reach the end.
  0 indicates that the index has not been processed yet.
- The helper function canReachEnd works recursively. If the current index is the last one in the array, it marks the index in memo
  as 2 and returns true, as the end is reachable.
- If the current index is already marked as 2, it returns true immediately.
- If it's marked as 1, it returns false as it's already determined that this path won't lead to the end.
- The function checks how many jumps are allowed from the current index (nums[index]) and tries all possible jumps.
- If any of the jumps can reach the end, it updates the memo array with 2 for the current index and returns true.
- If no valid path is found, it marks the index as 1 in the memo and returns false.
- This approach reduces redundant calculations and speeds up the process by storing previously computed results.

class Solution {
    public boolean canReachEnd(int index,int nums[],int memo[]){
        if(index==nums.length-1){
            memo[index]=2;
            return true;
        } 
        if(memo[index]==2){
            return true;
        }  
        if(memo[index]==1){
            return false;
        }    
        int jumpCount=nums[index];
        if(index>=nums.length || jumpCount==0){
            memo[index]=1;
            return false;
        }
        for(int i=1;i<=jumpCount;i++){
            if(canReachEnd(index+i,nums,memo)){
                memo[index]=2;
                return true;
            }
        }
        memo[index]=1;
        return false;
    }
    public boolean canJump(int[] nums) {
       int memo[]=new int[nums.length];
       return canReachEnd(0,nums,memo);
        
    }
}

Time complexity - o(N*N)
Space complexity - o(N)

Approach 3 : Dynamic Programming

- This code provides a dynamic programming solution for determining whether you can reach the end of an array from the first index.
- It uses a bottom-up approach with a dp[] array to store whether each index can lead to the end.
- The function initializes the dp[] array with the last index set to 2, meaning that the last index can trivially reach itself 
  (the end).
- Starting from the second-to-last index and moving backward through the array, it checks how far you can jump from each position.
- For each index i, the code checks all possible jump distances (from 1 to nums[i]). If any of the positions reachable by these 
  jumps already leads to the end (i.e., dp[i+j] == 2), the current index i is marked as 2, meaning that from this index, 
  it's possible to reach the end. Once a valid path is found, the loop for that index breaks.
- Finally, the function returns true if the first index (dp[0]) is marked as 2, indicating that it’s possible to reach the end from 
  the start. Otherwise, it returns false.

class Solution {
    public boolean canJump(int[] nums) {
       int dp[]=new int[nums.length];
       dp[nums.length-1]=2;
       for(int i=nums.length-2;i>=0;i--){
        int jumpCount=nums[i];
        for(int j=1;j<=jumpCount;j++){
            if(dp[i+j]==2){
                dp[i]=2;
                break;
            }
        }
       }
       return dp[0]==2?true:false;
        
    }
}

Time complexity - o(N*N)
Space complexity - o(N)

Approach 4 : Greedy

- This code provides a greedy solution for determining whether you can jump to the end of the array starting from the first index.
- The function keeps track of the farthest position (maxIndex) that can be reached as it iterates through the array.
- For each index i, it checks if the current index is beyond the farthest reachable position (maxIndex). 
- If so, it returns false, indicating that the current index cannot be reached, and thus, the end of the array cannot be
  reached either.
- If the current index is reachable, the code updates maxIndex by taking the maximum of the current maxIndex and the farthest 
  position that can be reached from the current index (nums[i] + i). 
- This ensures that the function is always tracking the farthest point you can jump to as it progresses through the array.
- If the loop completes without returning false, the function returns true, indicating that the end of the array is reachable.

class Solution {
    public boolean canJump(int[] nums) {
       int maxIndex=0;
       for(int i=0;i<nums.length;i++){
            if(i>maxIndex){
                return false;
            }
            maxIndex=Math.max(maxIndex,nums[i]+i);
       }
       return true;
        
    }
}

Time complexity - o(N)
Space complexity - o(1)
