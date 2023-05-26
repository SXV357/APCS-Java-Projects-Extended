import java.util.ArrayList;

public class BachelorsProbsTester {
    public static void main(String[] args){
        System.out.println(BachelorsProbs.withoutDupes(new int[] {1, 1, 2}));
        System.out.println(BachelorsProbs.withoutDupes(new int[] {2, 4, 6, 8, 8, 10}));
        int[] modified = BachelorsProbs.collapse(new int[] {1,2,3,4,5});
        for (int n: modified){System.out.print(n + " ");}
    }
}

class BachelorsProbs{

    public static int withoutDupes(int[] nums){
        int temp = Integer.MAX_VALUE;
        int count = 0;
        for (int i = 0; i < nums.length; i++){
            if (nums[i] != temp){
                temp = nums[i];
                count++;
            }
        }
        return count;
    }

    public static int[] collapse(int[] nums){
        if (nums.length % 2 == 0){
            ArrayList<Integer> n = new ArrayList<Integer>();
            for (int i = 0; i <= nums.length - 2; i+= 2){
                n.add(nums[i] + nums[i+1]);
            }
            int[] reference = new int[n.size()];
            for (int i = 0; i < n.size(); i++){
                reference[i] = n.get(i);
            }
            return reference;
        }
        else {
            ArrayList<Integer> n2 = new ArrayList<Integer>();
            for (int j = 0; j <= nums.length - 2; j+=2){
                n2.add(nums[j] + nums[j+1]);
            }
            n2.add(nums[nums.length- 1]);
            int[] reference2 = new int[n2.size()];
            for (int j =0; j < n2.size();j++){
                reference2[j] = n2.get(j);
            }
            return reference2;
        }
    }
}
