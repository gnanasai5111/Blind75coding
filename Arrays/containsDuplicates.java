Given an integer array nums, return true if any value appears at least twice in the array, and return false if every element is distinct.

Example 1:

Input: nums = [1,2,3,1]

Output: true

Explanation:
The element 1 occurs at the indices 0 and 3.

Approach 1 : Brute force 

- Try out all possible combinations to check for duplicates
- For each element in the array ,check all the numbers that come after it to see if they are equal.
- Repeat this process for all the elements in the array. If you find any match return true else return false.

class Solution {
    public boolean containsDuplicate(int[] nums) {
        for(int i=0;i<nums.length;i++){
            for(int j=i+1;j<nums.length;j++){
                if(nums[i]==nums[j]){
                    return true;
                }
            }
        }
        return false;
    }
}

Time complexity - o(N*N)
Space complexity - o(1)

Approach 2 : HashSet

- Add all the elements to Hashset.
- For each element, check is its already existing in the hashset or not .If it is return true else return false

class Solution {
    public boolean containsDuplicate(int[] nums) {
        HashSet<Integer> set=new HashSet<>();
        for(int i=0;i<nums.length;i++){
            if(set.contains(nums[i])){
                return true;
            }
            else{
                set.add(nums[i]);
            }
        }
        return false;
    }
}

Time complexity - o(N)
Space complexity - o(N)

Approach 3 : Sorting 

- Sort the array .
- After sorting, any duplicate elements will be next to each other. Go through the array and compare each element with the one before to it.
- If any two consecutive elements are the same, it means the array contains duplicates.

class Solution {
    public boolean containsDuplicate(int[] nums) {
        Arrays.sort(nums);
        for(int i=1;i<nums.length;i++){
            if(nums[i]==nums[i-1]){
                return true;
            }
        }
        return false;
    }
}

Time complexity - o(NlogN)
Space complexity - o(N)
