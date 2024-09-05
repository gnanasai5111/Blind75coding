


Approach 1 : Brute force

-  For each starting index i,  calculate the sum of all possible subarrays that begin at i by adding elements one by one in a nested loop.
-  Update the maxSum to keep track of the largest sum encountered. 
-  Finally, it returns the maximum subarray sum found during the process.

class Solution {
    public int maxSubArray(int[] nums) {
        int maxSum=nums[0];
        for(int i=0;i<nums.length;i++){
            int sum=0;
            for(int j=i;j<nums.length;j++){
                sum=sum+nums[j];
                maxSum=Math.max(maxSum,sum);
            }
        }
        return maxSum;      
    }
}

Time complexity - o(N*N)
Space complexity - o(1)

Approach 2 : Kadanes algorithm -Dynamic programming 

- You sum all the elements in the array , if sum is less than zero, than make the sum to zero and also find max sum in every iteration.

class Solution {
    public int maxSubArray(int[] nums) {
        int sum=0;
        int max=Integer.MIN_VALUE;
        for(int i=0;i<nums.length;i++){
            sum=sum+nums[i];
            max=Math.max(max,sum);
            if(sum<0){
                sum=0;
            }   
        }
        return max;      
    }
}

Time complexity - o(N)
Space complexity - o(1)
