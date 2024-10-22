Given an integer x, return true if x is a palindrome and false otherwise.

Example 1:

Input: x = 121
Output: true
Explanation: 121 reads as 121 from left to right and from right to left.

First approach :

- This solution defines a method isPalindrome that checks if a given integer x is a palindrome (reads the same forward and backward). 
- It first handles negative numbers, returning false since negative numbers cannot be palindromes.
- Then, it initializes two variables: rev to store the reversed number and curr to store the current value of x.
- In a while loop, the digits of curr are extracted one by one using modulo operation (curr % 10) and appended to rev to
  build the reversed number. 
- The original number curr is then divided by 10 to remove the last digit. 
- Once all digits are processed, the reversed number rev is compared to the original number x, and the method returns true if they 
  are equal, indicating that x is a palindrome; otherwise, it returns false

class Solution {
    public boolean isPalindrome(int x) {
        if(x<0){
            return false;
        }
        int rev=0;
        int curr=x;
        while(curr!=0){
            int rem=curr%10;
            rev=rev*10+rem;
            curr=curr/10;
        }
        return rev==x;    
    }
}

Time complexity - o(log10N +)
Space complexity - o(1)
