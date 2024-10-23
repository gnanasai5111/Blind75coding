You are given a string s and an integer k. You can choose any character of the string and change it to any other uppercase English
character. You can perform this operation at most k times.

Return the length of the longest substring containing the same letter you can get after performing the above operations.

Example 1:

Input: s = "ABAB", k = 2
Output: 4
Explanation: Replace the two 'A's with two 'B's or vice versa.

First approach :

- This solution solves the problem of finding the longest substring where up to k characters can be replaced to make all characters
  in the substring the same.
- For each starting index i in the string, the algorithm checks all possible substrings starting from i and calculates the 
  frequency of each character in the substring using an array c[].
- It keeps track of the maximum frequency of any character within the current window, represented by maxFreq.
- For each ending index j, it calculates the total length of the current substring and checks whether the number of characters 
  to be replaced (totalLength - maxFreq) is less than or equal to k. 
- If this condition holds, it updates the maximum length ans.
- If the replacements needed exceed k, the inner loop breaks early to avoid unnecessary checks for longer substrings.
- Finally, the function returns the maximum length of a valid substring found.


class Solution {
    public int characterReplacement(String s, int k) {
        int ans=0;
        for(int i=0;i<s.length();i++){
            int[] c=new int[26];
            int maxFreq=0;
            for(int j=i;j<s.length();j++){
                int totalLength=j-i+1;
                c[s.charAt(j)-'A']++;
                maxFreq=Math.max(maxFreq,c[s.charAt(j)-'A']);
                if(totalLength-maxFreq<=k){
                    ans=Math.max(ans,j-i+1);
                }
                else{
                    break;
                }
            }
        }
        return ans;
        
    }
}

Time complexity - o(N*N*26)
Space complexity - o(26)

Approach 2 :

- This solution finds the length of the longest substring where up to k characters can be replaced to make the substring consist of
  only one character.
- The algorithm uses a sliding window approach, with two pointers l and r representing the left and right ends of the window.
- The frequency of characters in the current window is tracked using an array c[].
- As the right pointer r moves, the frequency of the character at position r is incremented, and the maximum frequency of 
  any character in the window is updated.
- The algorithm calculates the total length of the current window and checks if the number of characters to
  be replaced (totalLength - maxFreq) exceeds k.
- If more than k replacements are needed, the left pointer l is moved to shrink the window, reducing the frequency of the leftmost 
  character. Once the window is valid, the result (ans) is updated with the maximum valid window size.
- The loop continues until the right pointer r reaches the end of the string, and finally, the maximum length of the valid 
  substring is returned.

class Solution {
    public int characterReplacement(String s, int k) {
        int ans=0;
        int[] c=new int[26];
        int r=0,l=0,maxFreq=0;
        while(r<s.length()){
            c[s.charAt(r)-'A']++;
            maxFreq=Math.max(maxFreq,c[s.charAt(r)-'A']);
            int totalLength=r-l+1;
            while(totalLength-maxFreq>k){
                c[s.charAt(l) - 'A']--;
                l++;
                maxFreq=Math.max(maxFreq,c[s.charAt(l)-'A']);
                totalLength = r - l + 1; 
            }
            ans=Math.max(ans,r-l+1);
            r++;
        }
        return ans;
        
    }
}

Time complexity - o(N+N)*o(26)
Space complexity - o(26)

Approach 3 :Sliding window optimised

- This solution solves the problem of finding the longest substring where up to k characters can be replaced to make all the
  characters in that substring the same.
- It uses a sliding window technique with two pointers, l and r, representing the left and right boundaries of the window, respectively.
- A frequency array c[] is used to keep track of how many times each character appears in the current window.
- The variable maxFreq keeps track of the highest frequency of any character in the window. 
- The window expands by moving the right pointer r and updating the character count.
- For each step, the algorithm calculates the total length of the current window.
- If the number of characters that need to be replaced (totalLength - maxFreq) exceeds k, the left pointer l is moved to 
  shrink the window, decreasing the frequency of the leftmost character.
- Once the window becomes valid again (i.e., the number of replacements is less than or equal to k), the result (ans) is 
  updated with the largest window size encountered.
- The process continues until the right pointer reaches the end of the string, and the maximum valid substring length is
  returned as the final result.

class Solution {
    public int characterReplacement(String s, int k) {
        int ans=0;
        int[] c=new int[26];
        int r=0,l=0,maxFreq=0;
        while(r<s.length()){
            c[s.charAt(r)-'A']++;
            maxFreq=Math.max(maxFreq,c[s.charAt(r)-'A']);
            int totalLength=r-l+1;
            if(totalLength-maxFreq>k){
                c[s.charAt(l) - 'A']--;
                l++;
                maxFreq=Math.max(maxFreq,c[s.charAt(l)-'A']);
                totalLength = r - l + 1; 
            }
            ans=Math.max(ans,r-l+1);
            r++;
        }
        return ans;
        
    }
}

Time complexity - o(N+N)*o(26)
Space complexity - o(26)
