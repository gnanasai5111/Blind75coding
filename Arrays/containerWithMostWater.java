You are given an integer array height of length n.
There are n vertical lines drawn such that the two endpoints of the ith line are (i, 0) and (i, height[i]).
Find two lines that together with the x-axis form a container, such that the container contains the most water.
Return the maximum amount of water a container can store.
Notice that you may not slant the container.

Example 1:
Input: height = [1,8,6,2,5,4,8,3,7]
Output: 49
Explanation: The above vertical lines are represented by array [1,8,6,2,5,4,8,3,7]. 
In this case, the max area of water (blue section) the container can contain is 49.

Approach 1 : Brute force

- In order to find the max area between the lines, we need to traverse the array.
- For each element in the array, check the area for the current element and the elements coming after it . 
- The width is the difference between the indices, and the height is the minimum of the two values.Track the maximum area.

class Solution {
    public int maxArea(int[] height) {
        int max=0;
        int n=height.length;
        for(int i=0;i<n;i++){
            for(int j=i+1;j<n;j++){
                int w=j-i;
                int h=Math.min(height[i],height[j]);
                max=Math.max(max,w*h);
            }
        }
        return max;
    }
}

Time Complexity - o(N*N)
Space Complexity - o(1)

Second approach : Two Pointer 

- Use two pointers: one at the start (left) and one at the end (right) of the array.
- At each step, calculate the area between the lines at the two pointers.
- The width is the distance between the pointers, and the height is the minimum of the two heights.
- Track the maximum area as you move the pointers.
- To maximize the area, move the pointer pointing to the smaller height (since the container's height is limited by the shorter line). 
- This potentially allows for a taller container in the next iteration.
  
class Solution {
    public int maxArea(int[] height) {
        int max=0;
        int j=height.length-1;
        int i=0;
        while(i<j){
            int w=j-i;
            int h=Math.min(height[i],height[j]);
            max=Math.max(max,w*h);
            if(height[i]<height[j]){
                i++;
            }
            else{
                j--;
            }
        }
        return max;
    }
}

Time Complexity - o(N)
Space Complexity - o(1)
