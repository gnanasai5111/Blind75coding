Swap given two numbers and print them. (Try to do it without a temporary variable.) and return it.

- The Intuition to solve this problem is when you xor any two same numbers, the answer is 0 . so for b we do(a^b^b) and for a we do (a^b^a)
  
class Solution{
    static List<Integer> get(int a,int b)
    {
        // code here
        a=a^b;
        b=a^b;
        a=a^b;
        List<Integer> res=new ArrayList<>();
        res.add(a);
        res.add(b);
        return res;
    }
}

