class Solution {
    public void moveZeroes(int[] nums) {
        int A = 0;

        for(int k = 0; k < nums.length; k++) {
            if(nums[k] != 0) {
                nums[A] = nums[k];
                A++;
            }

        }

        for(int i = A;i <nums.length; i++) {
            nums[i] = 0;
        }
    }
}