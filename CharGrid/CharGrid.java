public class CharGrid {
    private char[][] grid;

    public CharGrid(char[][] grid){
        this.grid = grid;
    }

    public int charArea(char ch){
        int count = 0;
        for (int i = 0; i < grid.length; i++){
            for (int j = 0; j < grid[0].length; j++){
                if (grid[i][j] == ch){
                    count++;
                }
            }
        }
        if (count >= 1){
            if (count == 1) return 1;
            else {
                int minRows = 2; int minCols = 1; int comparisonCount = 0; int traversedCols = 0;
                if (count == grid.length) minRows = grid.length;
                for (int x = 0; x < grid[0].length; x++){
                    for (int y = 0; y < grid.length; y++){
                        if (grid[y][x] == ch){
                            comparisonCount++;
                        }
                        if ((y == grid.length - 1 && grid[y][x] != ch && comparisonCount != count) || (x == grid[0].length - 1 && comparisonCount == count)){
                            traversedCols++;
                        }
                    }
                    if (comparisonCount == count){ // if count after traversing first column equals actual count
                        return minRows * traversedCols;
                    }
                } 
            }
        }
        return 0;
    }

    public int countPlus(){
        return 0;
    }

    @Override
    public String toString(){
        String res = "";
        for (char[] arr: this.grid){
            for (char ch: arr){
                res += " " + ch;
            }
            res += "\n";
        }
        return res;
    }
}
