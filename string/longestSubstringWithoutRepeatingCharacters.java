Given a string s, find the length of the longest substring without repeating characters.

Example 1:

Input: s = "abcabcbb"
Output: 3
Explanation: The answer is "abc", with the length of 3.

First approach :

- This code aims to find the length of the longest substring in a given string s without repeating characters.
- First, it checks if the input string is empty. If so, it returns 0 as there is no valid substring.
- Otherwise, the variable max is initialized to 1 to store the length of the longest valid substring found.
- The outer loop iterates through the string, starting with each character i.
- For each i, the inner loop starts at the next character j and checks if adding this character would create a substring without repeating characters.
- The flag flag is used to track whether a repetition is found.
- Another loop iterates backward from j-1 to i to compare the current character s.charAt(j) with the characters before it. 
- If no match is found, the substring length (length) is incremented, and the maximum length max is updated accordingly.
- However, if a match (repetition) is found, the flag is set to true, and the inner loop breaks early, moving to the next starting position i.
- Finally, the function returns the value of max, which represents the length of the longest substring without repeating characters.

class Solution {
    public int lengthOfLongestSubstring(String s) {
        if(s.length()==0){
            return 0;
        }
        int max=1;
        for(int i=0;i<s.length();i++){
            
            for(int j=i+1;j<s.length();j++){
                boolean flag=false; 
                int length=1;         
                for(int k=j-1;k>=i;k--){
                    if(s.charAt(j)!=s.charAt(k)){
                        length++;
                    }
                    else{
                        flag=true;
                        break;
                    }
                }
                if(flag){
                    break;
                }
                else{
                    max=Math.max(max,length);
                }
                
            }
        }
        return max;
        
    }
}

Time complexity - o(N*N*N)
Space complexity - o(1)

Approach 2 :

- This code calculates the length of the longest substring in a string s without repeating characters.
- It first checks if the input string is empty. If so, it returns 0 since there are no substrings.
- Otherwise, it initializes the variable max to store the length of the longest valid substring found.
- The outer loop iterates through the string, with i representing the starting point of the substring. 
- For each i, a HashSet is created to store characters encountered in the current substring and to check for duplicates.
- The inner loop, starting at j = i, processes each character in the substring from i onwards.
- If the current character s.charAt(j) is already in the set (indicating a repeating character), the loop breaks, and the substring checking ends for this starting point.
- If no repeat is found, the code updates the maximum length max with the difference between j and i, plus one (to account for the current character).
- Each character is then added to the set.
- Finally, the method returns max, representing the length of the longest substring without repeating characters.

class Solution {
    public int lengthOfLongestSubstring(String s) {
        if(s.length()==0){
            return 0;
        }
        int max=0;
        for(int i=0;i<s.length();i++){
            HashSet<Character> set=new HashSet<>();
            for(int j=i;j<s.length();j++){
                if(set.contains(s.charAt(j))){
                    break;
                }
                max=Math.max(max,j-i+1);
                set.add(s.charAt(j));
                
            }
        }
        return max;
        
    }
}

Time complexity - o(N*N)
Space complexity - o(N)

Approach 3 : Two pointer

- This code calculates the length of the longest substring in a string s that contains no repeating characters.
- It first checks if the input string is empty. If so, it returns 0. 
- Otherwise, it initializes max to keep track of the maximum length found and uses two pointers, left and right, to define a sliding window over the string. 
- A HashSet is used to store the characters within the current window.
- The right pointer iterates through the string, expanding the window.
- If the character at right is already in the set (indicating a repetition), the inner while loop moves the left pointer forward, removing characters from the set
  until the repeating character is no longer in the window.
- For each non-repeating substring, the length is calculated as right - left + 1, and max is updated if this length is greater than the previously recorded maximum.
- Finally, the method returns max, which holds the length of the longest substring without repeating characters.

class Solution {
    public int lengthOfLongestSubstring(String s) {
        if(s.length()==0){
            return 0;
        }
        int max=0;
        int left=0;
        HashSet<Character> set=new HashSet<>();
        for(int right=0;right<s.length();right++){
            while(left<right && set.contains(s.charAt(right))){
                set.remove(s.charAt(left));
                left++;
            }
            set.add(s.charAt(right));
            max = Math.max(max, right - left + 1);
        }
        return max;
        
    }
}

Time complexity - o(N)
Space complexity - o(N)

Approach 4 : Two pointer optimised

- This code finds the length of the longest substring in a string s that contains no repeating characters.
- The function begins by checking if the input string is empty, returning 0 if true. 
- Otherwise, it initializes max to store the length of the longest substring, left as the start pointer of the sliding window, and a HashMap to map each character in the string 
  to its latest index.
- As the right pointer iterates through the string, it checks if the current character at right has been seen before using the map.
- If it has, it updates the left pointer to be the maximum of its current position or one position after the last occurrence of the repeating character to ensure no duplicates 
  in the current window.
- For each new character, it updates the map with the character's current position (right). The length of the current substring is then calculated as right - left + 1,
  and max is updated to store the maximum length found.
- Finally, the function returns max, which holds the length of the longest substring without repeating characters.

class Solution {
    public int lengthOfLongestSubstring(String s) {
        if(s.length()==0){
            return 0;
        }
        int max=0;
        int left=0;
        HashMap<Character,Integer> map=new HashMap<>();
        for(int right=0;right<s.length();right++){
            if(map.containsKey(s.charAt(right))){
                left=Math.max(left,map.get(s.charAt(right))+1);
            }
            map.put(s.charAt(right),right);
            max = Math.max(max, right - left + 1);
        }
        return max;
        
    }
}

Time complexity - o(N)
Space complexity - o(N)
