Given an m x n integer matrix matrix, if an element is 0, set its entire row and column to 0's.
You must do it in place.
  
Example 1:
Input: matrix = [[1,1,1],[1,0,1],[1,1,1]]
Output: [[1,0,1],[0,0,0],[1,0,1]]


First approach : Brute Force 

- This code modifies a matrix such that if any element in the matrix is zero, all elements in that element’s row and column are set to zero as well.
- The process involves making a copy of the original matrix, res, and marking rows and columns in that copy based on the position of zeros in the original matrix.
- The program starts by copying the contents of matrix into res. It then iterates through the matrix to check for any zeroes.
- For every zero found, it marks the corresponding row and column in res to be filled with zeroes.
- Finally, it updates the original matrix by copying the modified values from res back into matrix.


class Solution {
    public void markRow(int res[][],int n,int row){
        for(int j=0;j<n;j++){
            res[row][j]=0;
        }
    }
    public void markCol(int res[][],int m,int col){
        for(int i=0;i<m;i++){
            res[i][col]=0;
        }
    }
    public void setZeroes(int[][] matrix) {
        int m=matrix.length;
        int n=matrix[0].length;
        int res[][]=new int[m][n];
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                res[i][j]=matrix[i][j];
            }
        }
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(matrix[i][j]==0){
                    markRow(res,n,i);
                    markCol(res,m,j);
                }
            }
        }
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){              
                matrix[i][j]=res[i][j];
            }
        }
        
    }
}

Time complexity - o(M*N)
Space complexity - o(M*N)

Approach 2 : Better approach :

- This code modifies a given matrix such that if any element in the matrix is zero, all elements in that element’s row and column are set to zero.
- The algorithm begins by determining the dimensions of the matrix, storing the number of rows in m and the number of columns in n.
- It initializes two arrays, row and col, to keep track of which rows and columns need to be zeroed.
- The first nested loop iterates through the matrix to identify zero elements.
- When a zero is found, the corresponding index in the row array is set to 1 for that row and in the col array for that column.
- In the second nested loop, the algorithm checks the row and col arrays. If a row or column has been marked, it sets the respective position in the original matrix to zero. 
- This effectively updates the entire row and column of each zero found in the initial matrix.

class Solution {
    public void setZeroes(int[][] matrix) {
        int m=matrix.length;
        int n=matrix[0].length;
        int row[]=new int[m];
        int col[]=new int[n];
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(matrix[i][j]==0){
                    row[i]=1;
                    col[j]=1;
                }
            }
        }
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){              
                if(row[i]==1 || col[j]==1){
                    matrix[i][j]=0;
                }
            }
        }
        
    }
}

Time complexity - o(M*N)
Space complexity - o(M+N)

Approach 3 :

- The solution modifies the given matrix to set entire rows and columns to zero if any element in that row or column is zero.
- It starts by checking each element in the matrix. If a zero is found, it marks the first row and first column accordingly.
- Additionally, a variable (col0) is used to track whether the first column itself should be set to zero.
- After marking, the matrix is traversed again, this time updating all elements based on the markers in the first row and first column.
- If the first element of a row or column is zero, the corresponding row or column is set to zero.
- Finally, the first row and the first column are updated based on the markers and the col0 variable to ensure they are also zeroed out if necessary.
- This approach minimizes the need for extra space and avoids altering the matrix until the marking phase is complete.


class Solution {
    public void setZeroes(int[][] matrix) {
        int m=matrix.length;
        int n=matrix[0].length;
        int col0=matrix[0][0];
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(matrix[i][j]==0){
                    matrix[i][0]=0;
                    if(j==0){
                        col0=0;
                    }
                    else{
                        matrix[0][j]=0;
                    }
                }
            }
        }
        for(int i=1;i<m;i++){
            for(int j=1;j<n;j++){
                if(matrix[0][j]==0 || matrix[i][0]==0){
                    matrix[i][j]=0;
                }
            }
        }
        if(matrix[0][0]==0){
            for(int j=0;j<n;j++){
                matrix[0][j]=0;
            }
        }
        if(col0==0){
            for(int i=0;i<m;i++){
                matrix[i][0]=0;
            }
        }
       
        
    }
}

Time complexity - o(M*N)
Space complexity - o(1)

  
