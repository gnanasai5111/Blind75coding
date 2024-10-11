Given an m x n grid of characters board and a string word, return true if word exists in the grid.
The word can be constructed from letters of sequentially adjacent cells, where adjacent cells are horizontally or vertically neighboring.
The same letter cell may not be used more than once.

Example 1:

Input: board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "ABCCED"
Output: true


First approach :

- This code defines a solution for the problem of searching for a word in a 2D board of characters.
- The class contains two main methods: exist() and searchNext().
- The exist() method scans the entire board to find the first character of the word. It does this by iterating over every cell in
  the board.
- When it finds a match, it calls searchNext() to continue searching for the rest of the word starting from that cell.
- The searchNext() method performs a recursive depth-first search (DFS) to explore all four possible directions: up, down, left,
  and right. If the current cell matches the corresponding character in the word, it marks the cell as visited by temporarily 
  changing its value. 
- It then recursively checks the adjacent cells. If the word is fully matched, the method returns true. 
- If a mismatch occurs or the boundaries of the board are exceeded, it returns false.
- The board is reset to its original state after every recursive call, ensuring that each search is isolated and unaffected by previous 
  explorations.
- In the end, if any path successfully matches the word, the exist() method returns true. Otherwise, it returns false.

class Solution {
    public boolean searchNext(int i,int j,int index,char board[][], String word){
        if (index == word.length()){
            return true;
        }
        if(i<0 || j<0 || i==board.length || j==board[0].length || word.charAt(index)!=board[i][j]){
            return false;
        }
         char c = board[i][j];
         board[i][j] = '!';
         boolean top=searchNext(i-1,j,index+1,board,word);
         boolean bottom=searchNext(i+1,j,index+1,board,word);
         boolean left=searchNext(i,j-1,index+1,board,word);
         boolean right=searchNext(i,j+1,index+1,board,word);
         board[i][j] = c;
         return top || bottom || left || right;

    }
    public boolean exist(int index,char board[][], String word){
        for(int i=0;i<board.length;i++){
            for(int j=0;j<board[i].length;j++){
                if(word.charAt(index)==board[i][j]){
                    if(searchNext(i,j,index,board,word)){
                        return true;
                    }
                }
            }
        }
        return false;
    }
    public boolean exist(char[][] board, String word) {
         int index=0;
         return exist(index,board,word);
        
    }
}

Time complexity - O(m*n*4^k)
Space complexity - O(K),
