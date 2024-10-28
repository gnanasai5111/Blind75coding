Given two strings s and t of lengths m and n respectively, return the minimum window substring of s such that every character in t
(including duplicates) is included in the window. If there is no such substring, return the empty string "".

The testcases will be generated such that the answer is unique.

Example 1:

Input: s = "ADOBECODEBANC", t = "ABC"
Output: "BANC"
Explanation: The minimum window substring "BANC" includes 'A', 'B', and 'C' from string t

First approach :

- The function minWindow aims to find the smallest substring in s that contains all characters of t.
- It first creates a frequency map (existingMap) of characters in t to track how many occurrences of each character are required.
- Then, it iterates through each starting index i in s, attempting to find a valid substring beginning at i and containing all 
  characters in t.
- For each starting index i, it creates a copy of existingMap called map and initializes an inner loop with index j, which moves 
  through the characters of s starting from i. 
- The inner loop processes each character s[j], checking if it’s present in map.
- If so, it decreases the count of that character in map, and if the count reaches zero, it removes the character from map.
- When map is empty, all required characters have been found between indices i and j.
- At this point, the function calculates the length of the substring from i to j, and if it’s smaller than the previously found minimum,
  updates min, start, and end to reflect the new minimum substring boundaries. Once the inner loop finds a valid substring,
  it breaks to avoid unnecessary checks beyond j.
- After all iterations, if start hasn’t changed (indicating no valid window was found), it returns an empty string; otherwise, it
  returns the smallest window substring found.

class Solution {
    public String minWindow(String s, String t) {
        
        int min=Integer.MAX_VALUE;
        int start=-1,end=-1;
        HashMap<Character,Integer> existingMap=new HashMap<>();
        for(int k=0;k<t.length();k++){
            existingMap.put(t.charAt(k),existingMap.getOrDefault(t.charAt(k),0)+1);
        }
        
        for(int i=0;i<s.length();i++){
            HashMap<Character, Integer> map = new HashMap<>(existingMap);
            for(int j=i;j<s.length();j++){
                char c=s.charAt(j);
                if(map.containsKey(c)){
                    if(map.get(c)==1){
                        map.remove(c);
                    }
                    else{
                        map.put(c,map.get(c)-1);
                    }
                }
                if(map.size()==0){
                    int length=j-i+1;
                    if(length<min){
                        min=length;
                        start=i;
                        end=j+1;
                    }
                    break;
                }
            }
        }
        if(start==-1){
            return "";
        }
        return s.substring(start,end);
        
    }
}

Time complexity - o(N*N)
Space complexity - o(M)

Approach 2 : sliding window

- The function minWindow finds the smallest substring in s that contains all characters of t, handling cases where characters may 
  appear multiple times. 
- It starts by building a frequency map, target, to count occurrences of each character in t.
- Another map, current, tracks characters in the current window of s.
- Two pointers, left and right, mark the bounds of the window. 
- The right pointer expands the window by moving through s. For each character c in s at position right, if it’s in target,
  it’s added to current, and if current matches the count in target, a variable formed is incremented to track how many required 
  characters are fully matched.
- Once all required characters are matched (formed == required), the left pointer begins to contract the window to find the smallest
  possible valid substring.
- For each contraction, the function checks if the current window length is smaller than the previous minimum, updating min,
  start, and end if so. Then, the count of the character at left is decremented in current, and if this breaks a match with target,
  formed is decremented. Finally, left is incremented to further shrink the window.
- When the entire string s has been processed, if a valid substring was found, it returns the substring from start to end.
- If not, it returns an empty string.

class Solution {
    public String minWindow(String s, String t) {
        
        int min=Integer.MAX_VALUE;
        int start=-1,end=-1;
        HashMap<Character,Integer> target=new HashMap<>();
        HashMap<Character,Integer> current=new HashMap<>();
        for(int k=0;k<t.length();k++){
            target.put(t.charAt(k),target.getOrDefault(t.charAt(k),0)+1);
        }
        int required=target.size();
        int formed=0;
        int left=0,right=0;
        while(right<s.length()){
            char c=s.charAt(right);
            if(target.containsKey(c)){
                current.put(c,current.getOrDefault(c,0)+1);
                if(target.get(c).intValue()==current.get(c).intValue()){
                    formed++;
                }
            }
            while(left<=right && formed==required){
                char ch=s.charAt(left);
                int length=right-left+1;
                if(length<min){
                    min=length;
                    start=left;
                    end=right+1;
                    
                }
                if(current.containsKey(ch)){
                    current.put(ch,current.get(ch)-1);
                    if(target.containsKey(ch) && target.get(ch).intValue()>current.get(ch).intValue()){
                        formed--;
                    }
                }
                left++;
            }

            right++;
        }
        return start==-1?"":s.substring(start,end);
        
        
    }
}

Time complexity - o(M+N)
Space complexity - o(M+N)
