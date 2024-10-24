Given two strings s and t, return true if t is an  anagram of s, and false otherwise.

Example 1:

Input: s = "anagram", t = "nagaram"

Output: true

Approach 1 :

- This code defines a function isAnagram that checks if two strings, s and t, are anagrams of each other. 
- It uses two integer arrays, c1 and c2, both of size 26 (representing the 26 letters of the English alphabet), 
  to store the frequency of each character in both strings.
- First, it iterates through string s, updating the corresponding index in c1 based on each character's position in the alphabet
  (s.charAt(i) - 'a'). Similarly, it iterates through string t and updates c2 for each character.
- After populating the frequency arrays, it compares c1 and c2 element by element.
- If the frequencies for any letter don't match, it returns false, indicating that the strings are not anagrams.
- If all the elements match, it returns true, meaning the two strings are anagrams.

class Solution {
    public boolean isAnagram(String s, String t) {
        int c1[]=new int[26];
        int c2[]=new int[26];
        for(int i=0;i<s.length();i++){
            c1[s.charAt(i)-'a']++;
        }
        for(int i=0;i<t.length();i++){
            c2[t.charAt(i)-'a']++;
        }
        for(int i=0;i<26;i++){
            if(c1[i]!=c2[i]){
                return false;
            }
        }
        return true;

    }
}

Time complexity - o(M+N)
Space complexity - o(26)

Second approach :

- This code defines a method isAnagram that checks if two strings, s and t, are anagrams.
- It uses an integer array c1 of size 26 to track the frequency of characters in string s.
- Each element of c1 corresponds to a letter of the alphabet, with the index determined by subtracting 'a' from the character.
- First, the method loops through string s, incrementing the respective index in c1 for each character. 
- Then, it iterates through string t, decrementing the value in c1 for each character. 
- If any character from t is not present in s (i.e., its corresponding count in c1 is zero), the method immediately returns false.
- Finally, the method checks if all the values in c1 are zero, meaning that both strings contain the same characters in the
  same frequency. 
- If any value in c1 is greater than zero, it returns false. Otherwise, it returns true, indicating that the strings are anagrams

class Solution {
    public boolean isAnagram(String s, String t) {
        int c1[]=new int[26];
        for(int i=0;i<s.length();i++){
            c1[s.charAt(i)-'a']++;
        }
        for(int i=0;i<t.length();i++){
            if(c1[t.charAt(i)-'a']>0){
                c1[t.charAt(i)-'a']--;
            }
            else{
                return false;
            }
        }
        for(int i=0;i<26;i++){
            if(c1[i]>0){
                return false;
            }
        }
        return true;

    }
}

Time complexity - o(M+N)
Space complexity - o(26)

Approach 3 :

- This method checks whether two strings, s and t, are anagrams by comparing their sorted versions. 
- It first checks if the lengths of s and t are equal. If the lengths are not the same, the function immediately returns false,
  as strings of different lengths cannot be anagrams.
- Next, it converts both strings into character arrays (str1 and str2) and sorts them using Arrays.sort(). 
- Once sorted, it compares the characters at each index of both arrays. If any characters at the same position in the sorted arrays 
  differ, it returns false, indicating that the strings are not anagrams.
- If all characters match after sorting, it returns true, meaning the strings are anagrams of each other.

class Solution {
    public boolean isAnagram(String s, String t) {
    if (s.length() != t.length()) {
        return false;
    }
    char[] str1 = s.toCharArray();
    char[] str2 = t.toCharArray();
    Arrays.sort(str1);
    Arrays.sort(str2);
    for(int i=0;i<str1.length;i++){
        if(str1[i]!=str2[i]){
            return false;
        }
    }
    return true;

    }
}

Time complexity - o(MlogM+NlogGN)
Space complexity - o(N+M)
