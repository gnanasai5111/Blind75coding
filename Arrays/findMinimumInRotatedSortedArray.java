Suppose an array of length n sorted in ascending order is rotated between 1 and n times. 
For example, the array nums = [0,1,2,4,5,6,7] might become:

[4,5,6,7,0,1,2] if it was rotated 4 times.
[0,1,2,4,5,6,7] if it was rotated 7 times.
Notice that rotating an array [a[0], a[1], a[2], ..., a[n-1]] 1 time results in the array [a[n-1], a[0], a[1], a[2], ..., a[n-2]].

Given the sorted rotated array nums of unique elements, return the minimum element of this array.

You must write an algorithm that runs in O(log n) time.

Input: nums = [3,4,5,1,2]
Output: 1
Explanation: The original array was [1,2,3,4,5] rotated 3 times.

First approach : Brute Force

- Sort the array and return the first element 

class Solution {
    public int findMin(int[] nums) {
        Arrays.sort(nums);
        return nums[0];       
    }
}

Time complexity - o(NlogN)
Space complexity - o(N)

Second approach : Traverse the array 

- Just traverse through the array and find minimum element 

class Solution {
    public int findMin(int[] nums) {
        int min=nums[0];
        for(int i=1;i<nums.length;i++){
            if(nums[i]<min){
                min=nums[i];
            }
        }
        return min;      
    }
}

Time complexity - o(N)
Space complexity - o(1)

Third approach : Recursion 

- use binary search .
- Start with two pointer one at left and other at right.
- Base case would be whenever left is equal to right , i.e, exactly having one element . Just return the element
- if last element is greater than the start, then the array is already sorted , so just return first element
- Find the mid element , if mid is greater than mid+1 or mid-1> mid then return mid+1 and mid respectively
- if nums[l]<=nums[mid] then left half is sorted go to right half else go to left half.
- Repeat this process till you find min element.
  
class Solution {
    public int findMinUsingBinarySearch(int l,int r,int nums[]){
        if(l==r){
            return nums[l];
        }
        if (nums[r] > nums[0]) {
            return nums[0];
        }
        int mid=(l+r)/2;
        if(mid + 1 <= r && nums[mid] > nums[mid + 1]){
            return nums[mid+1];
        }
        else if(mid - 1 >= l && nums[mid-1]>nums[mid]){
            return nums[mid];
        }
        if(nums[l]<=nums[mid]){
            return findMinUsingBinarySearch(mid+1,r,nums);
        }
        else{
            return findMinUsingBinarySearch(l,mid-1,nums);
        }
    }
    public int findMin(int[] nums) {
        return findMinUsingBinarySearch(0,nums.length-1,nums);   
    }
}

Time complexity - o(logN)
Space complexity - o(N)

Fourth approach - Iterative approach :

- If an array is rotated and sorted, we already know that for every index, one of the 2 halves of the array will always be sorted.
- Based on this observation, we adopted a straightforward two-step process to eliminate one-half of the rotated sorted array. 
- First, we identify the sorted half of the array. 
- Once found, we determine if the target is located within this sorted half. 
- If not, we eliminate that half from further consideration. 
- Conversely, if the target does exist in the sorted half, we eliminate the other half.

class Solution {
    public int findMin(int[] nums) {
        int l=0;
        int r=nums.length-1;
        int ans=Integer.MAX_VALUE;
        while(l<=r){
            int mid=(l+r)/2;
            if(nums[l]<=nums[mid]){
                ans=Math.min(ans,nums[l]);
                l=mid+1;
            }
            else{
                ans=Math.min(nums[mid],ans);
                r=mid-1;
            }
        }
        return ans;
    }
}

Time complexity - o(logN)
Space complexity - o(1)
