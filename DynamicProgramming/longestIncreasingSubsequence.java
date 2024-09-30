Given an integer array nums, return the length of the longest strictly increasing subsequence.

Example 1:

Input: nums = [10,9,2,5,3,7,101,18]
Output: 4
Explanation: The longest increasing subsequence is [2,3,7,101], therefore the length is 4.


First approach : (Recursion)

- This solution calculates the length of the longest increasing subsequence (LIS) in an array using recursion. 
- The findLongestIncreasingSubsequence function starts from a given index and explores all possible subsequences by checking the 
  subsequent elements. 
- If an element is larger than the current one, it recursively extends the subsequence. 
- The lengthOfLIS function iterates over all possible starting points in the array, and for each index, it computes the
  longest subsequence starting from that index.
- The final result is the maximum length found among all starting points.

class Solution {
    public int findLongestIncreasingSubsequence(int index,int nums[],int length){
        if(index>=nums.length){
            return length;
        }
        int max=length;
        for(int i=index+1;i<nums.length;i++){
            if(nums[i]>nums[index]){
                int val=findLongestIncreasingSubsequence(i,nums,length+1);
                max=Math.max(max,val);
            }
        }
        return max;
    }
    
    public int lengthOfLIS(int[] nums) {
        int maxLength = 0;
        for (int i = 0; i < nums.length; i++) {
            int currentLength = findLongestIncreasingSubsequence(i, nums, 1);
            maxLength = Math.max(maxLength, currentLength);
        }
        return maxLength;
    }
}

Time complexity - exponential
Space complexity - o(N)

Optimised recursive approach :

- This implementation defines a recursive approach to find the length of the longest increasing subsequence (LIS) in an array of
  integers.
- The findLongestIncreasingSubsequence method takes three parameters: the current index, the previous index of the last included
  element in the subsequence, and the input array.
- It checks if the current index has reached the end of the array, returning 0 if so. 
- It calculates two possibilities: the length of the LIS if the current element is not included (notTake) by moving to the next 
  index without changing the previous index, and the length if the current element is included (take), provided it is greater than
  the previous element, incrementing the count and moving to the next index while updating the previous index.
- The method returns the maximum value between notTake and take. The lengthOfLIS method initializes the process by calling 
  findLongestIncreasingSubsequence with the starting index 0 and -1 as the previous index, indicating no previous element. 

class Solution {
    public int findLongestIncreasingSubsequence(int index,int prevIndex,int nums[]){
        if(index==nums.length){
            return 0;
        }
        int notTake=findLongestIncreasingSubsequence(index+1,prevIndex,nums);
        int take=0;
        if(prevIndex==-1 || nums[index]>nums[prevIndex]){
            take=1+findLongestIncreasingSubsequence(index+1,index,nums);
        }
        return Math.max(notTake,take);
    }
    
    public int lengthOfLIS(int[] nums) {
        return findLongestIncreasingSubsequence(0,-1, nums);
    }
}

Time complexity - o(2^N)
Space complexity - o(N)


Second approach : Memoization
- The findLongestIncreasingSubsequence function takes the current index, the previous index, the input array nums, and a memoization
  table memo.
- The base case checks if the current index has reached the end of the array; if so, it returns 0, indicating no further elements 
  can be added to the subsequence. 
- Before computing values, the function checks if the result for the current state has already been computed and stored in the memo
  table.
- If a valid cached result exists, it returns that value.
- The function then recursively calculates the maximum length of the subsequence by considering two cases: not taking the current
  element (by moving to the next index) and taking the current element (if it is greater than the last selected element in the
  subsequence).
- If the current element is taken, it increments the length and continues the search.
- The computed maximum value is stored in the memo table for future reference, allowing for optimized performance by avoiding 
  redundant calculations. The lengthOfLIS method initializes the memoization table and calls the recursive function starting
  from the first index with an invalid previous index (-1).

class Solution {
    public int findLongestIncreasingSubsequence(int index,int prevIndex,int nums[],int memo[][]){
        if(index==nums.length){
            return 0;
        }
        if(memo[index][prevIndex+1]!=0){
            return memo[index][prevIndex+1];
        }
        int notTake=findLongestIncreasingSubsequence(index+1,prevIndex,nums,memo);
        int take=0;
        if(prevIndex==-1 || nums[index]>nums[prevIndex]){
            take=1+findLongestIncreasingSubsequence(index+1,index,nums,memo);
        }
        return memo[index][prevIndex+1]=Math.max(notTake,take);
    }
    
    public int lengthOfLIS(int[] nums) {
        int memo[][]=new int[nums.length][nums.length+1];
        return findLongestIncreasingSubsequence(0,-1, nums,memo);
    }
}


Time complexity - o(N)
Space complexity - o(N)

Approach 3 : Tabulation

- This solution implements a dynamic programming approach to find the length of the longest increasing subsequence (LIS) in an array
  of integers.
- The dp array is used to store the length of the longest increasing subsequence that ends at each index.
- Initially, every element in the dp array is set to 1 because each individual element is considered an increasing subsequence
  of length 1 by itself.
- The outer loop iterates over each element starting from the second one, while the inner loop checks all the previous elements. 
- If the current element is greater than a previous element, it means the current element can extend the increasing subsequence 
  that ends at the previous element.
- In that case, the dp value at the current index is updated to be the maximum of its current value or the length of the 
  subsequence ending at the previous element plus one.
- At each iteration, the variable longest is updated to keep track of the maximum value in the dp array, which represents the 
  length of the longest increasing subsequence found so far

class Solution {
    public int lengthOfLIS(int[] nums) {
        int[] dp = new int[nums.length];
        Arrays.fill(dp, 1);
        int longest=1;
        for (int i = 1; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            longest = Math.max(dp[i], longest);
        }
       
        return longest;
    }
}

Time complexity- o(N*N)
Space complexity - (N)

Approach 4 :

- This solution uses a more efficient approach to find the length of the longest increasing subsequence (LIS) by maintaining a 
  list that represents the smallest possible increasing subsequence. 
- The list sub initially contains the first element of the array.
- As the loop iterates through the array, if the current number is greater than the last number in the list, it is appended to the
  list, extending the increasing subsequence.
- If the current number is not greater than the last number in the list, it means the current number can replace a value in the 
  list to maintain a smaller subsequence.
- The solution uses a linear search to find the smallest element in the list that is greater than or equal to the current number 
  and replaces it with the current number.
- This ensures that the list always contains the smallest possible values for any increasing subsequence of that length.
- At the end of the loop, the size of the list sub represents the length of the longest increasing subsequence. 

class Solution {
    public int lengthOfLIS(int[] nums) {
        ArrayList<Integer> sub = new ArrayList<>();
        sub.add(nums[0]);
        for (int i = 1; i < nums.length; i++) {
            if(nums[i]>sub.get(sub.size()-1)){
                sub.add(nums[i]);
            }
            else{
                int j=0;
                while(nums[i]>sub.get(j)){
                    j++;
                }
                sub.set(j,nums[i]);
            }
        }
       
        return sub.size();
    }
}


Time complexity- o(N*N)
Space complexity - (N)

Approach 5 : Binary search 

- This solution uses a combination of dynamic programming with binary search to efficiently compute the length of the longest increasing
  subsequence (LIS). 
- It maintains a list sub that represents the smallest possible increasing subsequence. 
- Initially, the first element of nums is added to this list.
- As the loop iterates over nums, if the current element is greater than the last element of the sub list, 
  it gets appended to the list, extending the subsequence. 
- However, if the current element is smaller than or equal to the last element of sub, the algorithm uses a binary search to find the 
  correct position to replace the element in the list.
- This ensures that sub maintains the smallest possible values for any increasing subsequence of that length, without necessarily
  forming the actual subsequence.
- The binary search method helps in finding the correct index where the current number should replace an existing element in sub,
  keeping the operation efficient. 
- By doing this, the list sub always remains sorted, and its size at the end represents the length of the LIS.

class Solution {
    public int binarySearch(ArrayList<Integer> sub, int num) {
        int l=0;
        int r=sub.size()-1;
        while(l<r){
            int mid=(l+r)/2;
            if(sub.get(mid)==num){
                return mid;
            }
            else if(sub.get(mid)>num){
                r=mid;
            }
            else{
                l=mid+1;
            }
        }
        return l;
    }
    public int lengthOfLIS(int[] nums) {
        ArrayList<Integer> sub = new ArrayList<>();
        sub.add(nums[0]);
        for (int i = 1; i < nums.length; i++) {
            if(nums[i]>sub.get(sub.size()-1)){
                sub.add(nums[i]);
            }
            else{
                int j = binarySearch(sub, nums[i]);
                sub.set(j,nums[i]);
            }
        }
       
        return sub.size();
    }
}

Time complexity- o(N*logN)
Space complexity - (N)
