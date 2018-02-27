import edu.princeton.cs.algs4.MaxPQ;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Solution {

    public static void main(String[] args) {
        int[] nums = {10, 0, 1, 2, 3, 4, 5};
        int target = 5;
        int pivot = findPivot(nums, 0, nums.length - 1);
        int result = -1;
        if(target >= nums[pivot] && target <= nums[nums.length -1]){
           result = binarySearch(nums, pivot,nums.length -1, target);
        } else if(pivot > 0 && target <= nums[pivot - 1] && target >= nums[0]){
            result = binarySearch(nums, 0, pivot - 1, target);
        }
        System.out.println(result);
    }

    private static int binarySearch(int[] nums, int left, int right, int target) {
        if(left > right)
            return -1;
        int mid = (left + right) / 2;
        if ( nums[mid] == target) {
            return mid;
        }
        if( target > nums[mid]){
            return binarySearch(nums, mid + 1, right,target);
        }
        if (target < nums[mid]){
            return binarySearch(nums, left, mid -1, target);
        }
        return -1;
    }

    private static int findPivot(int[] nums, int left, int right) {
        if(left == right - 1)
            return left > right ? left : right;

        int mid = (left + right)/2 ;
        if (nums[left] > nums[mid]) {
            return findPivot(nums, left, mid);
        } else if (nums[right] < nums[mid]){
            return findPivot(nums, mid, right);
        }

        return 0;
    }

}