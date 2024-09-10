Given an integer array nums, return all the triplets [nums[i], nums[j], nums[k]] such that i != j, i != k, and j != k,
and nums[i] + nums[j] + nums[k] == 0.

Notice that the solution set must not contain duplicate triplets.

Example 1:

Input: nums = [-1,0,1,2,-1,-4]
Output: [[-1,-1,2],[-1,0,1]]
Explanation: 
nums[0] + nums[1] + nums[2] = (-1) + 0 + 1 = 0.
nums[1] + nums[2] + nums[4] = 0 + 1 + (-1) = 0.
nums[0] + nums[3] + nums[4] = (-1) + 2 + (-1) = 0.
The distinct triplets are [-1,0,1] and [-1,-1,2].
Notice that the order of the output and the order of the triplets does not matter.

  First approach : Brute Force

- First, we will declare a set data structure as we want unique triplets.
- Then we will use the first loop(say i) that will run from 0 to n-1.
- Inside it, there will be the second loop(say j) that will run from i+1 to n-1.
- Then there will be the third loop(say k) that runs from j+1 to n-1.
- Now, inside these 3 nested loops, we will check the sum i.e. arr[i]+arr[j]+arr[k], and if it is equal to the target i.e.
- 0 we will sort this triplet and insert it in the set data structure.
- Finally, we will return the list of triplets stored in the set data structure.

class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        int n=nums.length;
        HashSet<List<Integer>> set=new HashSet<>();
        for(int i=0;i<n-2;i++){
            for(int j=i+1;j<n-1;j++){
                for(int k=j+1;k<n;k++){
                    if(nums[i]+nums[j]+nums[k]==0){
                        List<Integer> inner=new ArrayList<>();
                        inner.add(nums[i]);
                        inner.add(nums[j]);
                        inner.add(nums[k]);
                        Collections.sort(inner);
                        set.add(inner);
                    }
                }
            }
        }
        return new ArrayList<>(set);
    }
}

Time complexity - o(N*N*N* log(no. of unique triplets))
Space complexity - o(No of triplets)

Approach 2 : Hashset

- First, we will declare a set data structure as we want unique triplets.
- Then we will use the first loop(say i) that will run from 0 to n-1.
- Inside it, there will be the second loop(say j) that will run from i+1 to n-1.
- Before the second loop, we will declare another HashSet to store the array elements as we intend to search
  for the third element using this HashSet.
- Inside the second loop, we will calculate the value of the third element i.e. -(arr[i]+arr[j]).
- If the third element exists in the HashSet, we will sort these 3 values i.e. arr[i], arr[j], and the
  third element, and insert it in the set data structure declared in step 1.
- After that, we will insert the j-th element i.e. arr[j] in the HashSet as we only want to insert those array elements that are in between indices i and j.
- Finally, we will return a list of triplets stored in the set data structure.

class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        int n=nums.length;
        HashSet<List<Integer>> set=new HashSet<>();
        for(int i=0;i<n-1;i++){
            HashSet<Integer> s=new HashSet<>();
            for(int j=i+1;j<n;j++){
                int sum=-1 *(nums[i]+nums[j]);
                if(s.contains(sum)){
                    List<Integer> inner=new ArrayList<>();
                    inner.add(nums[i]);
                    inner.add(nums[j]);
                    inner.add(sum);
                    Collections.sort(inner);
                    set.add(inner);
                }
                s.add(nums[j]);
            }
        }
        return new ArrayList<>(set);
    }
}

Time complexity - o(N*N log(no. of unique triplets))
Space complexity - o(No of triplets)


Approach 3 : Two Pointer

- Among the 3 pointers, 1 will be fixed and 2 will be moving.
- In each iteration, we will check if the sum i.e.arr[i]+arr[j]+arr[k] is equal to the target i.e. 0. 
- If the sum is greater, then we need lesser elements and so we will decrease the value of k(i.e. k--). 
- If the sum is lesser than the target, we need a bigger value and so we will increase the value of j (i.e. j++). 
- If the sum is equal to the target, we will simply insert the triplet i.e. arr[i], arr[j], arr[k],
  into our answer and move the pointers j and k skipping the duplicate elements.

class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        int n=nums.length;
        Arrays.sort(nums);
        List<List<Integer>> ans=new ArrayList<>();
        for(int i=0;i<n;i++){
            if (i != 0 && nums[i] == nums[i - 1]) continue;
            int j=i+1;
            int k=n-1;
            while(j<k){
                int sum=nums[i]+nums[j]+nums[k];
                if(sum<0){
                    j++;
                }
                else if(sum>0){
                    k--;
                }
                else{
                    List<Integer> temp = Arrays.asList(nums[i], nums[j], nums[k]);
                    ans.add(temp);
                    j++;
                    k--;
                    while(j<k && nums[j]==nums[j-1]){
                        j++;
                    }
                    while(j<k && nums[k]==nums[k+1]){
                        k--;
                    }
                }
            }
            
        }
        return ans;
    }
}

Time complexity - O(NlogN)+O(N*N)
Space complexity - o(No of triplets)
