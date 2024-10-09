You are given an n x n 2D matrix representing an image, rotate the image by 90 degrees (clockwise).
You have to rotate the image in-place, which means you have to modify the input 2D matrix directly. DO NOT allocate another 2D matrix and do the rotation.

Example 1:
Input: matrix = [[1,2,3],[4,5,6],[7,8,9]]
Output: [[7,4,1],[8,5,2],[9,6,3]]


Brute Force :

- The method rotates a given 2D matrix 90 degrees clockwise. First, a new matrix res is created to store the rotated values.
- Each element of the original matrix is placed into its new position in res, where the element at position matrix[i][j] moves to res[j][n-i-1].
- The idea is making the row as column. 
- This effectively shifts rows into columns to achieve the 90-degree rotation.
- After constructing res, the values are copied back into the original matrix, ensuring that the original matrix reflects the rotated version.


class Solution {
    public void rotate(int[][] matrix) {
        int n=matrix.length;
        int res[][]=new int[n][n];
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                res[j][n-i-1]=matrix[i][j];
            }
        }
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                matrix[i][j]=res[i][j];
            }
        }
    }
}

Time complexity - o(N*N)
Space complexity - o(N*N)
