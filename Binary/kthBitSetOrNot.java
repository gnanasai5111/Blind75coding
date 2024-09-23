Given a number n and a bit number k, check if kth index bit of n is set or not. A bit is called set if it is 1. 
Position of set bit '1' should be indexed starting with 0 from LSB side in binary representation of the number.
Note: Index is starting from 0. You just need to return true or false, driver code will take care of printing "Yes" and "No".

Input: n = 4, k = 0
Output: No
Explanation: Binary representation of 4 is 100, in which 0th index bit from LSB is not set. So, return false.

Brute Force:

- Convert the number to binary format and get the index of that element .

class CheckBit {
    // Function to check if Kth bit is set or not.
    static boolean checkKthBit(int n, int k) {
        // Your code here
        ArrayList<Integer> bin=new ArrayList<>();
        while(n>0){
            bin.add(n%2);
            n=n/2;
        }
        if(bin.size()-1<k){
            return false;
        }
        return bin.get(k)==1?true:false;
    }
}

Time complexity - o(logn)
Space complexity - o(1)

Second approach :

- Intution is using the left shift operator to  (1<<k) as it moves 1 to the k digits left. so when we do & for n and (1<<k) we will
  get the answer
class CheckBit {
    // Function to check if Kth bit is set or not.
    static boolean checkKthBit(int n, int k) {
        // Your code here
        return(n & (1 << k)) != 0;
    }
}

- Similar approach using right shift operator, we shift n right after k digits. so we do & with 1 to get the answer

class CheckBit {
    // Function to check if Kth bit is set or not.
    static boolean checkKthBit(int n, int k) {
        // Your code here
        return ((n >> k ) & 1) != 0;
    }
}





