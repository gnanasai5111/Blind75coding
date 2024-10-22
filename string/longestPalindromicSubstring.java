Given a string s, return the longest palindromicsubstring in s.

Example 1:

Input: s = "babad"
Output: "bab"
Explanation: "aba" is also a valid answer.


First approach : 

- The solution defines a method isPalindrome that checks whether a substring of s from index i to j is a palindrome. 
- It iterates over the substring, comparing characters from both ends towards the middle.
- If any mismatch is found, it returns false, otherwise, it returns true.
- The main method longestPalindrome finds the longest palindromic substring in the input string s.
- It uses two nested loops: the outer loop iterates over all starting points i, and the inner loop iterates over possible
  ending points j.
- For each pair of indices (i, j), it checks if the substring length is greater than the current maximum length (max).
- If it's a palindrome and longer than the previously found palindrome, it updates max and stores the current substring as the result.
- Finally, it returns the longest palindromic substring.


class Solution {
    public boolean isPalindrome(String sb,int i, int j){
        while(i<j){
            if(sb.charAt(i)!=sb.charAt(j)){
                return false;
            }
            i++;
            j--;
        }
        return true;
    }
    public String longestPalindrome(String s) {
        int max=0;
        String res="";
        for(int i=0;i<s.length();i++){
            for(int j=i;j<s.length();j++){
                if(max>j-i+1){
                    continue;
                }
                if(isPalindrome(s, i, j)){
                    if(max<j-i+1){
                        max=Math.max(max,j-i+1);
                        res=s.substring(i,j+1);
                    }
                    
                }
            }
        }
        return res;
    }
}

Time complexity - o(N*N*N)
Space complexity - o(N)

Approach 2 :

- This solution finds the longest palindromic substring using dynamic programming.
- It initializes a 2D array dp where dp[i][j] is 1 if the substring s[i...j] is a palindrome, otherwise 0. 
- Initially, single characters are marked as palindromes (dp[i][i] = 1), and adjacent characters that are equal are also
  considered palindromes.
- The algorithm then checks for substrings of increasing lengths starting from 3.
- For each substring s[i...j], it checks if the outer characters are the same (s.charAt(i) == s.charAt(j)) and if the inner substring
  s[i+1...j-1] is a palindrome (dp[i+1][j-1] == 1).
- If both conditions are satisfied, the substring is marked as a palindrome in the dp table.
- The algorithm keeps track of the starting and ending indices of the longest palindromic substring found, and finally returns the 
  longest palindrome using s.substring(start, end + 1).

class Solution {
    public String longestPalindrome(String s) {
        int n=s.length();
        int dp[][]=new int[n][n];
        int start=0;
        int end=0;
        int max=0;
        for(int i=0;i<n;i++){
            dp[i][i]=1;
            if(i+1<n){
                if(s.charAt(i)==s.charAt(i+1)){
                    dp[i][i+1]=1;
                    start=i;
                    end=i+1;
                    max=2;
                }             
            }
        }
        // Check for lengths greater than 2
        for (int len = 3; len <= n; len++) { // length of substring
            for (int i = 0; i <= n - len; i++) { // start index
                int j = i + len - 1; // end index
                
                // Check if s[i..j] is a palindrome
                if (s.charAt(i) == s.charAt(j) && dp[i + 1][j - 1]==1) {
                    dp[i][j] = 1;
                    
                    if (len > max) {
                        start = i;
                        end = j;
                        max=len;
                    }
                }
            }
        }
        return s.substring(start,end+1);
        
    }
}

Time complexity - o(N*N)
Space complexity - o(N)


Third approach : expand around center

- The solution uses the expand-around-center technique to find the longest palindromic substring.
- The method expand checks whether a substring is a palindrome by expanding outward from the given center.
- It keeps expanding as long as the characters at the start and end indices are the same.
- Once the expansion stops, it returns the length of the palindrome.
- In the main method longestPalindrome, the algorithm iterates through each character of the string and treats it as the center of
  both an odd-length and even-length palindrome. 
- It calls the expand method to find the longest palindrome that can be expanded from that center. 
- For odd-length palindromes, it expands with the same starting and ending point (i, i), and for even-length palindromes, 
  it expands with adjacent centers (i, i+1).
- After each expansion, it checks if the palindrome found is longer than the previous longest one.
- If it is, the starting and ending indices are updated to reflect the new longest palindrome. 
- Finally, the algorithm returns the longest palindromic substring by using the updated start and end indices to slice 
  the original string.

class Solution {
    public int expand(int start,int end,String s){
        while(start>=0 && end<s.length()){
            if(s.charAt(start)==s.charAt(end)){
                start--;
                end++;
            }
            else{
                break;
            }
        }
        return end-start-1;
    }
    public String longestPalindrome(String s) {
        int n=s.length();
        int start=0;
        int end=0;
        int max=0;
        for(int i=0;i<n;i++){
            int oddLength=expand(i,i,s);
            if(oddLength>max){
                max=oddLength;
                start=i-(oddLength/2);
                end=i+(oddLength/2);
            }
            int evenLength=expand(i,i+1,s);
            if(evenLength>max){
                max=evenLength;
                start=i-(evenLength/2-1);
                end=i+evenLength/2;
            }
        }
        return s.substring(start,end+1);
        
    }
}

Time complexity - o(N*N)
Space complexity - o(1)
