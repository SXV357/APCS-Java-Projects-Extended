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
                int minRows = 2; int comparisonCount = 0; int traversedCols = 0;
                if (count == this.grid.length) minRows = this.grid.length;
                for (int x = 0; x < this.grid[0].length; x++){
                    for (int y = 0; y < this.grid.length; y++){
                        if (this.grid[y][x] == ch){
                            comparisonCount++;
                        }
                        if ((y == this.grid.length - 1 && this.grid[y][x] != ch && comparisonCount != count) || (x == this.grid[0].length - 1 && comparisonCount == count)){
                            traversedCols++;
                        }                        
                    }
                    if (traversedCols == 1 && comparisonCount == count) return minRows;
                    else if (traversedCols > 1 && comparisonCount == count) return minRows * traversedCols;
                } 
            }
        }
        return 0;
    }

    public int countPlus(){
        int plusCount = 0;
        int lenUpArm = 0; int lenDownArm = 0; int lenLeftArm = 0; int lenRightArm = 0;
        for (int i = 1; i < grid.length - 2; i++){
            for (int j = 1; j < grid[0].length - 2; j++){
                // Each of the arms begins with the valid middle character
                if (grid[i][j] != ' ' && grid[i-1][j] == grid[i][j] && grid[i+1][j] == grid[i][j] && grid[i][j-1] == grid[i][j] && grid[i][j+1] == grid[i][j]){
                    // loop to traverse up
                    for (int k = i - 1; k >= 0; k--){
                        if (this.grid[k][j] == this.grid[i][j]){
                            lenUpArm++;
                        }
                    }
                    // loop to traverse down
                    for (int l = i + 1; l <= grid.length - 1; l++){
                        if (this.grid[l][j] == this.grid[i][j]){
                            lenDownArm++;
                        }
                    }
                    // loop to traverse left
                    for (int x = j - 1; x >= 0; x--){
                        if (this.grid[i][x] == this.grid[i][j]){
                            lenLeftArm++;
                        }
                    }
                    // loop to traverse right
                    for (int y = j + 1; y < grid[0].length - 1; y++){
                        if (this.grid[i][y] == this.grid[i][j]){
                            lenRightArm++;
                        }
                    }
                }
            }
            if (lenUpArm >= 2 && lenDownArm >= 2 && lenLeftArm >= 2 && lenRightArm >= 2){
                if (lenUpArm == lenDownArm && lenUpArm == lenLeftArm && lenUpArm == lenRightArm){
                    plusCount++;
                }
            }
        }
        return plusCount;
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