Given a string s containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.

An input string is valid if:

Open brackets must be closed by the same type of brackets.
Open brackets must be closed in the correct order.
Every close bracket has a corresponding open bracket of the same type.
 

Example 1:

Input: s = "()"

Output: true

First approach :

- The code defines a method that checks whether a given string containing only parentheses, curly braces, and square brackets is valid.
- It uses a stack to keep track of opening brackets, and for each character in the string, it performs the following:
- If the character is an opening bracket ((, {, or [), it is pushed onto the stack.
- If the character is a closing bracket (), }, or ]), the stack is checked:
- If the stack is empty, it means there is no corresponding opening bracket, so the method returns false.
- Otherwise, the top of the stack is popped and compared with the current closing bracket.
- If the pair of brackets match (e.g., ( with )), the method continues checking.
- If the brackets do not match, the method returns false.
- After iterating through the string, if the stack is empty, it indicates all opening brackets were properly closed, so the method 
  returns true. If there are any unmatched opening brackets left in the stack, the method returns false.

class Solution {
    public boolean isValid(String s) {
        Stack<Character> st=new Stack<>();
        for(int i=0;i<s.length();i++){
            char ch=s.charAt(i);
            if(ch=='(' || ch=='{' || ch=='['){
                st.push(ch);
            }
            else{
                if(st.isEmpty()){
                    return false;
                }
                char it = st.pop(); 
                if((it == '(' && ch == ')') ||  (it == '[' && ch == ']') || (it == '{' && ch == '}')){
                    continue;
                }
                else{
                    return false;
                }
            }
        }
        return st.isEmpty();
    }
}

Time complexity - o(N)
Space complexity - o(N)
