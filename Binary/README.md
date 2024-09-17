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


