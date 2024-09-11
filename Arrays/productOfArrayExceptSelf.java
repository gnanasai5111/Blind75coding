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

- Intution is , At each index, we need to find the product before and product after, so we calculate prefix and suffix.
- for the first index , there wont be any prefix so we keep it as 1 and for last index there wont be any suffix, so we keep it as 1.
- calculate the product for each index, my multiplying suffix and prefix;

class Solution {
    public int[] productExceptSelf(int[] nums) {
        int n=nums.length;
        int pre[]=new int[n];
        int suff[]=new int[n];
        pre[0]=1;
        suff[n-1]=1;     
        for(int i=1;i<n;i++){
            pre[i]=pre[i-1]*nums[i-1];
            suff[n-i-1]=suff[n-i]*nums[n-i];
        }
        int res[]=new int[n];
        for(int i=0;i<n;i++){
            res[i]=pre[i]*suff[i];
        }
        return res;
    }
}

Time complexity - o(N)
Space complexity - o(N)

Approach 3 :

- This approach is similar to second approach . We calculate the prefix product and store in the answer array.
- For suffix product , we directly calculate by ,multiplying current prefix productwith suff variable declared.
- After each iteration , we update the suffix product my multiplying with the current value;

class Solution {
    public int[] productExceptSelf(int[] nums) {
        int n=nums.length;
        int ans[]=new int[n];
        ans[0]=1;    
        for(int i=1;i<n;i++){
            ans[i]=ans[i-1]*nums[i-1];
        }
        int suff=1;
        for(int i=n-1;i>=0;i--){
            ans[i]=suff*ans[i];
            suff=nums[i]*suff;
        }
        return ans;
    }
}

Time complexity - o(N)
Space complexity - o(1) - (o(N) Space for storing answer)
