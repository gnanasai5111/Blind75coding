Given an array of meeting time intervals where intervals[i] = [starti, endi], determine if a person could attend all meetings.
Example 1:

Input: intervals = [[0,30],[5,10],[15,20]]
Output: false


First approach :

- This Java method, canAttendMeetings, checks if a person can attend all given meetings without any overlaps in schedule.
- It accepts a 2D array, intervals, where each sub-array represents a meeting's start and end times. The method iterates through each pair of meetings, using a nested loop to compare
  each meeting's timing with every other meeting's timing that comes after it.
- For each pair, it assigns variables s1, e1, s2, and e2 to represent the start and end times of the two meetings. 
- It then checks if the start of one meeting falls within the time range of the other meeting, indicating a conflict. If any overlap is found, the method returns false, indicating
  it's impossible to attend all meetings. If no overlapping meetings are found after all comparisons, the method returns true, indicating that attending all meetings is feasible.

class Solution {
    public boolean canAttendMeetings(int[][] intervals) {
        for(int i=0;i<intervals.length;i++){
            for(int j=i+1;j<intervals.length;j++){
                int s1=intervals[i][0];
                int e1=intervals[i][1];
                int s2=intervals[j][0];
                int e2=intervals[j][1];
                if(s1>=s2 && s1<e2 ||  s2>=s1 && s2<e1){
                    return false;
                }
            }
        }
        return true;
    }
}

Time complexity - o(N*N)
Space complexity - o(1)

Second approach :

