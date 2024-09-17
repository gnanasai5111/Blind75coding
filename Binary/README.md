## Decimal and Binary Formats

### Decimal (Base 10):
The decimal system is the standard system for denoting integers and non-integer numbers. It is based on 10 different digits (0-9).
Example: 25 in decimal means 2 * 10^1 + 5 * 10^0 = 25.

### Binary (Base 2):

Binary is the numbering system that uses only two digits, 0 and 1. It's used internally by computers and other electronic devices.
Example: The binary number 1101 represents 1 * 2^3 + 1 * 2^2 + 0 * 2^1 + 1 * 2^0 = 13 in decimal.

### Convert decimal to binary

```
class Solution{
	void toBinary(int N) {
		//Write your code here
		if (N == 0) {
            System.out.println(0);
            return;
        }
		StringBuilder res = new StringBuilder();
		while(N>0){
		    int rem=N%2;
		    res.append(rem);
		    N=N/2;
		}
		System.out.print(res.reverse().toString());
	}
}

Time complexity - o(logN)
Space complexity - o(logN)

```

### Convert Binary to Decimal

```

class Solution {
    public int binary_to_decimal(String str) {
        // Code here
        int res=0;
        int prod=1;
        for(int i=str.length()-1;i>=0;i--){
            res=res+prod*(str.charAt(i) - '0');
            prod=prod*2;
        }
        return res;
    }
}

Time complexity - o(N)
Space complexity - o(1)
```

### 1's Complement

- Convert the decimal number to binary equivalent and flip the bits( 1 to 0 and 0 to 1)

### 2's Complement

- Convert the number to  1's complemnt first then add 1 to it

### AND

- The AND operator compares each bit of two numbers and returns 1 only if both bits are 1; otherwise, it returns 0.

### OR

- The OR operator compares each bit of two numbers and returns 1 if at least one of the bits is 1; otherwise, it returns 0.

### XOR

- The XOR operator returns 1 if the corresponding bits of the two numbers are different; otherwise, it returns 0.

### NOT

- The NOT operator is a unary operator that flips all bits of the number (i.e., 0 becomes 1 and 1 becomes 0).


### Negative number binary representation

- Convert the number to 2's compliment

### Left Shift (<<) Operators
- The left shift(<<) is a binary operator that takes two numbers, left shifts the bits of the first operand, and the second operand decides the number of places to shift. In other words, left-shifting an integer “a” with an integer “b” denoted as ‘(a<<b)’ is equivalent to multiplying a with 2^b (2 raised to power b).

### Right Shift(>>) Operators
- Right Shift(>>) is a binary operator that takes two numbers, right shifts the bits of the first operand, and the second operand decides the number of places to shift. In other words, right-shifting an integer “a” with an integer “b” denoted as ‘(a>>b)‘ is equivalent to dividing a with 2^b. 


