Given an integer array nums, find a subarray that has the largest product, and return the product.
The test cases are generated so that the answer will fit in a 32-bit integer.
Example 1:

Input: nums = [2,3,-2,4]
Output: 6
Explanation: [2,3] has the largest product 6.

First approach : Brute Force

- Traverse the array, and for each element , check product for all sub arrays starting from the element and find max in it.

class Solution {
    public int maxProduct(int[] nums) {
        int max=nums[0];
        for(int i=0;i<nums.length;i++){
            int prod=1;
            for(int j=i;j<nums.length;j++){
                prod=prod*nums[j];
                max=Math.max(max,prod);
            }
        }
        return max;
    }
}

Time complexity - o(N*N)
Space complexity - o(1)

Second approach : Prefix and suffix

- If the given array only contains positive numbers: If this is the case, we can confidently say that the maximum product subarray
  will be the entire array itself.
- If the given also array contains an even number of negative numbers: As we know, an even number of negative numbers always results
  in a positive number. So, also, in this case, the answer will be the entire array itself.
- If the given array also contains an odd number of negative numbers: Now, an odd number of negative numbers when multiplied result
  in a negative number. Removal of 1 negative number out of the odd number of negative numbers will leave us with an even number of 
  negatives.
- Hence the idea is to remove 1 negative number from the result.
- Now we need to decide which 1 negative number to remove such that the remaining subarray yields the maximum product.
- Upon observation, we notice that each chosen negative number divides the array into two parts.
- The answer will either be the prefix or the suffix of that negative number.
- To find the answer, we will check all possible prefix subarrays (starting from index 0) and all possible suffix subarrays
  (starting from index n-1).
- The maximum product obtained from these prefix and suffix subarrays will be our final answer.
- If the array contains 0’s as well: We should never consider 0’s in our answer(as considering 0 will always result in 0) and we want 
  to obtain the maximum possible product. 


class Solution {
    public int maxProduct(int[] nums) {
        int max=nums[0];
        int pref=1;
        int suff=1;
        for(int i=0;i<nums.length;i++){
           if(pref==0){
            pref=1;
           }
           if(suff==0){
            suff=1;
           }
           pref*=nums[i];
           suff*=nums[nums.length-i-1];
           max=Math.max(max,Math.max(pref,suff));
        }
        return max;
    }
}

Time complexity - o(N)
Space complexity - o(1)

Approach 3:

- At each index, we find the min and max , beacuse of negative indexes we have to find min as well. 
- Beacause multiplying two negative numbers can be positive. so we keep track of min as well.

class Solution {
    public int maxProduct(int[] nums) {
        int max=nums[0];
        int min=nums[0];
        int ans=nums[0];
        for(int i=1;i<nums.length;i++){
           int temp=Math.max(nums[i],Math.max(nums[i]*max,nums[i]*min));
           min=Math.min(nums[i],Math.min(nums[i]*max,nums[i]*min));
           max=temp;
           ans=Math.max(max,ans);
        }
        return ans;
    }
}

Time complexity - o(N)
Space complexity - o(1)
