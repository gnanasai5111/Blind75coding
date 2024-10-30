Given an unsorted array arr of positive integers. One number a from the set [1, 2,....,n] is missing and one number b occurs 
twice in the array. Find numbers a and b.

Note: The test cases are generated such that there always exists one missing and one repeating number within the range [1,n].

Examples:

Input: arr[] = [2, 2]
Output: [2, 1]
Explanation: Repeating number is 2 and smallest positive missing number is 1.

First approach : Brute Force

- This function finds the repeating and missing elements in an integer array arr.
- It initializes two variables, repeating and missing, to -1 as placeholders. 
- The function then iterates over the integers from 1 to arr.length (the expected range of elements in the array).
- For each integer i, it checks how many times i appears in arr by counting occurrences in a nested loop.
- If an integer appears twice, it’s assigned to repeating; if it doesn’t appear at all, it’s assigned to missing.
- Once both the repeating and missing values are found, the function stops further searching to save computation time. 
- Finally, it creates an ArrayList called result, adds repeating and missing to it, and returns the list as the function's output.

class Solution {
    // Function to find two elements in array
    ArrayList<Integer> findTwoElement(int arr[]) {
        // code here
        int repeating=-1;
        int missing=-1;
        for(int i=1;i<=arr.length;i++){
            int count=0;
            for(int j=0;j<arr.length;j++){
                if(arr[j]==i){
                    count++;
                }
            }
            if(count==2){
                repeating=i;
            }
            else if(count==0){
                missing=i;
            }
            if(repeating!=-1 && missing!=-1){
                break;
            }
        }
        ArrayList<Integer> result = new ArrayList<>();
        result.add(repeating);
        result.add(missing);
        return result;
    }
}

Time complexity - o(N*N)
Space complexity - o(1)

Second approach :

- This function identifies the repeating and missing elements in an integer array arr. 
- It first creates a count array c of length arr.length + 1 to keep track of the occurrences of each integer in arr.
- It iterates over arr, incrementing the count for each element in the c array based on its value.
- Then, the function initializes repeating and missing as -1. It iterates over the count array c from 1 to arr.length to check 
  each integer's occurrence count. 
- If an integer appears twice, it assigns that integer to repeating; if it doesn't appear at all, it assigns that integer to missing.
- Once both values are found, the loop stops to save time.
- Finally, the function creates an ArrayList called result, adds repeating and missing to it, and returns this list as output.

class Solution {
    // Function to find two elements in array
    ArrayList<Integer> findTwoElement(int arr[]) {
        // code here
        int c[]=new int[arr.length+1];
        for(int i=0;i<arr.length;i++){
            c[arr[i]]++;
        }
        int repeating=-1;
        int missing=-1;
        for(int i=1;i<=arr.length;i++){
            int count=c[i];
            if(count==2){
                repeating=i;
            }
            else if(count==0){
                missing=i;
            }
            if(repeating!=-1 && missing!=-1){
                break;
            }
        }
        ArrayList<Integer> result = new ArrayList<>();
        result.add(repeating);
        result.add(missing);
        return result;
    }
}

Time complexity - o(N*N)
Space complexity - o(N)
