Given an array of strings strs, group the anagrams together. You can return the answer in any order.

Example 1:

Input: strs = ["eat","tea","tan","ate","nat","bat"]

Output: [["bat"],["nat","tan"],["ate","eat","tea"]]

Explanation:

There is no string in strs that can be rearranged to form "bat".
The strings "nat" and "tan" are anagrams as they can be rearranged to form each other.
The strings "ate", "eat", and "tea" are anagrams as they can be rearranged to form each other.

First approach :

- This method groups anagrams from a given array of strings. It uses a HashMap where the keys are sorted versions of the strings, 
  and the values are lists of strings that are anagrams of each other.
- For each string in the input array, it converts the string into a character array, sorts the array to form the "canonical" version 
  of the string (i.e., all anagrams will have the same sorted version), and then constructs a string from this sorted array.
- If the map doesn't already contain this sorted string as a key, it adds a new entry with the sorted string as the key and an empty
  list as the value.
- The original string is then added to the corresponding list of anagrams in the map.
- After processing all the strings, the method returns a list of all the grouped anagrams by converting the values of the map to an
  ArrayList.

class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> res=new ArrayList<>();
        Map<String, List> map = new HashMap<String, List>();
        for(int i=0;i<strs.length;i++){
            char ch[]=strs[i].toCharArray();
            Arrays.sort(ch);
            StringBuilder sb=new StringBuilder();
            for(int j=0;j<ch.length;j++){
                sb.append(ch[j]);
            }
            String sortedString=sb.toString();
            if(!map.containsKey(sortedString)){
                map.put(sortedString,new ArrayList<>());
            }
            map.get(sortedString).add(strs[i]);
        }
         return new ArrayList(map.values());
    }
}

Time complexity - O(NKlogK)
Space complexity - O(NK)

Approach 2 :

- This method groups anagrams from an array of strings using character frequency counts instead of sorting. 
- It uses a HashMap where each key is a string representing the character frequency of a word, and the values are lists of 
  anagrams corresponding to that frequency pattern.
- For each string in the input array, an integer array c of size 26 is created to count the frequency of each letter in the string.
- The character's position in the alphabet is determined by subtracting 'a' from the character's ASCII value.
- After counting the frequencies, a StringBuilder is used to create a unique string (sortedString) that represents the frequency pattern. 
- The sb appends a '#' symbol and the count of each letter, ensuring that different patterns generate distinct keys.
- If this sortedString doesn't already exist in the map, a new list is created and associated with the key. 
- The current string is then added to the list of anagrams. Finally, the method returns a list of all grouped anagrams by collecting
  the values from the map.
- This approach avoids sorting and efficiently handles anagram grouping using character frequencies.

class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> res=new ArrayList<>();
        Map<String, List> map = new HashMap<String, List>();
        for(int i=0;i<strs.length;i++){
            int c[]=new int[26];
            
            for(int j=0;j<strs[i].length();j++){
                c[strs[i].charAt(j)-'a']++;
            }
            StringBuilder sb=new StringBuilder();
            for(int k=0;k<26;k++){
                 sb.append('#');
                sb.append(c[k]);
            }
             String sortedString=sb.toString();
            if(!map.containsKey(sortedString)){
                map.put(sortedString,new ArrayList<>());
            }
            map.get(sortedString).add(strs[i]);
        }
         return new ArrayList(map.values());
    }
}

Time complexity - O(NK)
Space complexity - O(NK)
