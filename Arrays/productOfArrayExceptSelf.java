Given an integer array nums, return an array answer such that answer[i] is equal to the product of all the elements of nums except nums[i].

The product of any prefix or suffix of nums is guaranteed to fit in a 32-bit integer.

You must write an algorithm that runs in O(n) time and without using the division operation.

Example 1:

Input: nums = [1,2,3,4]
Output: [24,12,8,6]

First approach : Brute Force

- For each element in the array , find the product of all elements in the array except that element and push it to the result array.
- Repeat this process for all the elements in the array.

class Solution {
    public int[] productExceptSelf(int[] nums) {
        int res[]=new int[nums.length];
        for(int i=0;i<nums.length;i++){
            int prod=1;
            for(int j=0;j<nums.length;j++){
                if(j!=i){
                    prod=prod*nums[j];
                }
            }
            res[i]=prod;
        }
        return res;      
    }
}

Time complexity - o(N*N)
Space complexity - o(1)

Approach 2:
