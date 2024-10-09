Given an m x n matrix, return all elements of the matrix in spiral order.

Example 1:

Input: matrix = [[1,2,3],[4,5,6],[7,8,9]]
Output: [1,2,3,6,9,8,7,4,5]


Approach :

- The function takes a 2D matrix and returns the elements in spiral order.
- It first initializes four boundaries: top, bottom, left, and right, representing the current bounds of the matrix to traverse.
- These boundaries shrink inward as the matrix is traversed in layers.
- The process starts by moving from the left to the right along the top boundary and then moving downwards along the right boundary.
- If the top boundary is still less than or equal to the bottom boundary, it moves from right to left along the bottom boundary. 
- Similarly, if the left boundary is still less than or equal to the right boundary, it moves upwards along the left boundary.
- After traversing each side of the current boundary, the boundaries are adjusted inward to continue the spiral motion until all elements are processed. 
- The result is a list containing the matrix elements in spiral order.


class Solution {
    public List<Integer> spiralOrder(int[][] matrix) {
        int m=matrix.length;
        int n=matrix[0].length;
        int top=0;
        int left=0;
        int bottom=m-1;
        int right=n-1;
        List<Integer> res=new ArrayList<>();
        while(left<=right && top<=bottom){
            for(int i=left;i<=right;i++){
                res.add(matrix[top][i]);
            }
            top++;
            for(int i=top;i<=bottom;i++){
                res.add(matrix[i][right]);
            }
            right--;
            if(top<=bottom){
                for(int i=right;i>=left;i--){
                    res.add(matrix[bottom][i]);
                }
                bottom--;
            }
            if(left<=right){
                for(int i=bottom;i>=top;i--){
                    res.add(matrix[i][left]);
                }
                left++;
            }
        }
        return res;
    }
}

Time complexity - o(M*N)
Space complexity - o(1)
