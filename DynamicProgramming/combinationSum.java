Given an array of distinct integers candidates and a target integer target, return a list of all unique combinations of
candidates where the chosen numbers sum to target. You may return the combinations in any order.
The same number may be chosen from candidates an unlimited number of times. Two combinations are unique if the frequency
of at least one of the chosen numbers is different.
The test cases are generated such that the number of unique combinations that sum up to target is less than 150 combinations for
the given input.

Example 1:

Input: candidates = [2,3,6,7], target = 7
Output: [[2,2,3],[7]]
Explanation:
2 and 3 are candidates, and 2 + 2 + 3 = 7. Note that 2 can be used multiple times.
7 is a candidate, and 7 = 7.
These are the only two combinations.

Approach 1 : Recursion 1

- The provided code defines a solution for the combination sum problem, which aims to find all unique combinations of numbers
  from a given array that sum up to a specified target.
- The main method, combinationSum, initializes a hash set to store the unique combinations. It then calls the helper function 
  getAllCombinations starting with the first index and the target. 
- This function performs a depth-first search (DFS) to explore all possible combinations of the candidate numbers.
- In getAllCombinations, if the target becomes zero, it indicates that a valid combination has been found. 
- This combination is added to the result set after being converted to an array list to ensure proper storage. 
- The function iterates through the candidates starting from the given index, checking if each candidate can contribute to the target.
- If a candidate can be included, it is added to the current combination (inner), and the function is called recursively,
  decreasing the target by the value of the included candidate. 
- After exploring the path that includes the candidate, the code backtracks by removing the last added candidate, allowing for 
   exploration of other potential combinations.
- The use of a hash set ensures that only unique combinations are stored, eliminating duplicates.
- Finally, the method returns the results as a list of lists, containing all unique combinations that sum to the specified target.

class Solution {
    public void getAllCombinations(int index,int candidates[], int target,HashSet<List<Integer>> res,ArrayList<Integer> inner){
        if(target==0){
            res.add(new ArrayList<>(inner));
            return;
        }
        for(int i=index;i<candidates.length;i++){
            if(candidates[i]<=target){
                inner.add(candidates[i]);
                getAllCombinations(i,candidates,target-candidates[i],res,inner);
                inner.remove(inner.size()-1);
            }
        }
    }
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        HashSet<List<Integer>> res=new HashSet<>();
        getAllCombinations(0,candidates,target,res,new ArrayList<>());
        return new ArrayList<>(res);
    }
}

Time complexity - exponential
Space complexity - o(K*X)

Approach 2 :

- The provided code implements a solution to the combination sum problem, where the goal is to find all unique combinations 
  of numbers from a given array that sum up to a specified target.
- The approach uses a recursive backtracking method to explore all potential combinations.
- The method getAllCombSum is called recursively with the current index, target value, candidates, result list, and an inner
  list to keep track of the current combination.
- The base case checks if the index has reached the end of the candidates array.
- If so, it verifies if the target has been reduced to zero, indicating that a valid combination has been found.
- In this case, the current combination stored in the inner list is added to the results.
- The function first checks whether the current candidate at the given index can be included in the current combination. 
- If it can, it adds the candidate to the inner list, recursively calls itself with the updated target value
  (subtracting the candidate value), and then removes the candidate from the inner list to backtrack and explore other possibilities.
- After checking the inclusion of the current candidate, the function proceeds to explore the next candidate by incrementing the
  index and calling itself again, allowing the algorithm to explore all combinations that include or exclude each candidate.
- Finally, the combinationSum method initializes the result list and calls the recursive helper function, returning the list of all 
  unique combinations that meet the target sum. 
- The overall structure allows for exhaustive searching of combinations while utilizing backtracking to avoid unnecessary paths.

class Solution {
    public void getAllCombSum(int index,int target,int[] candidates,List<List<Integer>> res,List<Integer> inner){
        if(index==candidates.length){
            if(target==0){
                res.add(new ArrayList<>(inner));
            }
            return;
        }
        if (candidates[index] <= target) {
        inner.add(candidates[index]);
        getAllCombSum(index,target-candidates[index],candidates,res,inner);
        inner.remove(inner.size()-1);
        }
        getAllCombSum(index+1,target,candidates,res,inner);
    }
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> res=new ArrayList<>();
        getAllCombSum(0,target,candidates,res,new ArrayList<>());
        return res;
    }
}


Time complexity - exponential
Space complexity - o(K*X)
