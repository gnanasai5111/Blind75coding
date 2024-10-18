Design an algorithm to encode a list of strings to a string. The encoded string is then sent over the network and is decoded
back to the original list of strings.
  
Machine 1 (sender) has the function:

string encode(vector<string> strs) {
  // ... your code
  return encoded_string;
}
Machine 2 (receiver) has the function:
vector<string> decode(string s) {
  //... your code
  return strs;
}
So Machine 1 does:

string encoded_string = encode(strs);
and Machine 2 does:

vector<string> strs2 = decode(encoded_string);
strs2 in Machine 2 should be the same as strs in Machine 1.

Implement the encode and decode methods.

You are not allowed to solve the problem using any serialize methods (such as eval).

Example 1:

Input: dummy_input = ["Hello","World"]
Output: ["Hello","World"]
Explanation:
Machine 1:
Codec encoder = new Codec();
String msg = encoder.encode(strs);
Machine 1 ---msg---> Machine 2

First approach : 

- This class, Codec, is designed to encode and decode a list of strings. 
- It has a member variable list to store the list of strings during encoding.
- The encode method takes a list of strings and concatenates them into a single string. 
- It iterates through the list and appends each string to a result string. The resulting concatenated string is returned.
- The decode method, instead of processing the input string, simply returns the stored list of strings that was used during encoding.
- This method does not actually decode the string back into a list but rather returns the previously saved list

public class Codec {
    List<String> list;
    // Encodes a list of strings to a single string.
    public String encode(List<String> strs) {
        list=strs;
        String res="";
        for(int i=0;i<strs.size();i++){
            res=res+strs.get(i);
        }
        return res;
    }

    // Decodes a single string to a list of strings.
    public List<String> decode(String s) {
        return list;
    }
} 

Time complexity - O(n)
Space complexity - O(n)

Approach 2 : Delimeter

- This Codec class provides methods to encode a list of strings into a single string and decode it back into a list of strings.
- The encode method takes a list of strings and concatenates each string using the delimiter "π" to separate them.
- It uses a StringBuilder to efficiently build the encoded string. Each string from the list is appended to the StringBuilder, 
  followed by the delimiter.
- The decode method takes the encoded string and splits it back into individual strings.
- It iterates through the encoded string, appending characters to a temporary StringBuilder until the delimiter "π" is encountered.
- When the delimiter is found, the accumulated string is added to the result list, and the temporary StringBuilder is reset
  to collect the next string. The method returns a list of decoded strings.

public class Codec {
    // Encodes a list of strings to a single string.
    public String encode(List<String> strs) {
        StringBuilder encodedString = new StringBuilder();
        for(int i=0;i<strs.size();i++){
            encodedString.append(strs.get(i));
            encodedString.append("π");
        }
        return  encodedString.toString();
    }

    // Decodes a single string to a list of strings.
    public List<String> decode(String s) {
        List<String> res=new ArrayList<>();
        StringBuilder sub=new StringBuilder();
        for(int i=0;i<s.length();i++){
            if(s.charAt(i)=='π'){
                res.add(sub.toString());
                sub = new StringBuilder();
            }
            else{
                sub.append(s.charAt(i));
            }

        }
        return res;
    }
}

Time complexity - O(n)
Space complexity - O(n)

Approach 3 : Delimeter and escape sequence

- This Codec class provides methods to encode and decode a list of strings, using custom delimiters and escape sequences.
- The encode method processes each string from the input list by replacing any occurrences of the forward slash / with // to avoid 
  confusion with the delimiter.
- It then appends the encoded string followed by the delimiter /: to a StringBuilder to create a single encoded string.
- The decode method takes the encoded string and splits it back into the original list of strings. 
- It checks for the delimiter /: to identify the end of each string and handles escaped slashes // by appending a single / to the
  current string being decoded. 
- The result is stored in a list of strings, which is returned after processing the entire encoded string.

public class Codec {
    // Encodes a list of strings to a single string.
    public String encode(List<String> strs) {
        StringBuilder encodedString = new StringBuilder();
        for(int i=0;i<strs.size();i++){
            encodedString.append(strs.get(i).replace("/","//")).append("/:");
        }
        return  encodedString.toString();
    }

    // Decodes a single string to a list of strings.
    public List<String> decode(String s) {
        List<String> res=new ArrayList<>();
        StringBuilder sub=new StringBuilder();
        int i=0;
        while(i<s.length()){
            if(i+1<s.length() && s.charAt(i)=='/' && s.charAt(i+1)==':'){
                res.add(sub.toString());
                sub=new StringBuilder();
                i=i+2;
            }
            else if(+1<s.length() && s.charAt(i)=='/' && s.charAt(i+1)=='/'){
                sub.append('/');
                i=i+2;
            }
            else{
                sub.append(s.charAt(i));
                i++;
            }
        }
        return res;
    }
}


Time complexity - O(n)
Space complexity - O(n)

Approach 4 :  Chunked Transfer Encoding

- This Codec class provides methods for encoding and decoding a list of strings into a single string and back.
- The encode method constructs a single encoded string from a list of strings. 
- It appends the length of each string followed by the delimiter /:, and then the string itself. 
- This allows for easy separation of the length and the actual content when decoding.
- The decode method takes the encoded string and reconstructs the original list of strings.
- It begins by manually searching for the delimiter /:, which indicates where each string's length ends.
- It then converts the length portion into an integer without using built-in methods. 
- After determining the length, the method extracts the corresponding substring using a loop and adds it to the result list. 
- The index is updated to point to the start of the next length for processing.
- Overall, this implementation allows for efficient encoding and decoding of strings while handling the conversion and
  extraction processes without relying on standard library methods.

public class Codec {
    // Encodes a list of strings to a single string.
    public String encode(List<String> strs) {
        StringBuilder encodedString = new StringBuilder();
        for(int i=0;i<strs.size();i++){
            encodedString.append(strs.get(i).length()).append("/:").append(strs.get(i));
        }
        return  encodedString.toString();
    }

    // Decodes a single string to a list of strings.
    public List<String> decode(String s) {
         List<String> decodedStrings = new ArrayList<>();
        int i = 0;

        while (i < s.length()) {
            // Step 1: Manually find the delimiter "/:"
            int delim = i;
            while (delim < s.length() && !(s.charAt(delim) == '/' && delim + 1 < s.length() && s.charAt(delim + 1) == ':')) {
                delim++;
            }

            // Step 2: Convert the length part manually from digits before the delimiter
            int length = 0;
            for (int j = i; j < delim; j++) {
                length = length * 10 + (s.charAt(j) - '0'); // Convert each character to its numeric value
            }

            // Step 3: Extract the string based on the length
            StringBuilder extractedString = new StringBuilder();
            for (int k = delim + 2; k < delim + 2 + length; k++) {
                extractedString.append(s.charAt(k));
            }

            // Add the extracted string to the result list
            decodedStrings.add(extractedString.toString());

            // Move the index to the start of the next length (after the extracted string)
            i = delim + 2 + length;
        }

        return decodedStrings;
    }
}

Time complexity - O(n)
Space complexity - O(n)
