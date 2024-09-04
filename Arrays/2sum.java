Given an array of integers nums and an integer target, return indices of the two numbers such that they add up to target.

You may assume that each input would have exactly one solution, and you may not use the same element twice.

You can return the answer in any order.

Example 1:

Input: nums = [2,7,11,15], target = 9
Output: [0,1]
Explanation: Because nums[0] + nums[1] == 9, we return [0, 1].

Approach 1 : Brute Force

- I am trying brute force aapproach to try out all possible combinations.
- Traverse the array . For each element in the array , check all the numbers that come after it to see if their sum matches the target
- If sum is equal to target, return the indexes or return empty array.

class Solution {
    public int[] twoSum(int[] nums, int target) {
        for(int i=0;i<nums.length;i++){
            for(int j=i+1;j<nums.length;j++){
                if(nums[i]+nums[j]==target){
                    return new int[]{i,j};
                }
            }
        }
        return new int[]{};
    }
}

Time complexity - o(N*N)
Space complexity - o(1)

Approach 2 : HashMap - Two pass

- We can store all the elements in hashmap with key as element and index as a value.
- Nextly for each element in the array, we check if it has target-element in the hashmap to find the pair thats equal to target
- And also make sure they are not the same elements by comparing the indexes. 
- If match is found, return the indexes else return empty array

class Solution {
    public int[] twoSum(int[] nums, int target) {
        HashMap<Integer,Integer> map=new HashMap<>();
        for(int i=0;i<nums.length;i++){
            map.put(nums[i],i);
        }
        for(int i=0;i<nums.length;i++){
            if(map.containsKey(target-nums[i]) && map.get(target-nums[i])!=i){
                return new int[]{i,map.get(target-nums[i])};
            }
        }
        return new int[]{};
    }
}

Time complexity - o(N)
Space complexity - o(N)

Approach 3  : HashMap - one pass

- Instead of making hashmap seperately and traversing the array seperately, we can do it in a single pass
- for each element in the array , check if target-element is in the hashmap , if it is just return the elements and make sure the pair indexes are not same 
- If its not exist in the hashmap then put the current element in hashmap.
- Repeat the process for all elements in the array.

class Solution {
    public int[] twoSum(int[] nums, int target) {
        HashMap<Integer,Integer> map=new HashMap<>();
        for(int i=0;i<nums.length;i++){
            if(map.containsKey(target-nums[i]) && map.get(target-nums[i])!=i){
                return new int[]{i,map.get(target-nums[i])};
            }
            else{
                map.put(nums[i],i);
            }
        }
        return new int[]{};
    }
}

Time complexity - o(N)
Space complexity - o(N)
