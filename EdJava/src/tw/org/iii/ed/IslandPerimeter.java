package tw.org.iii.ed;

public class IslandPerimeter {
	public static void main(String[] args){
		int[][] grid ={
					  {0,1,0,0},
                      {1,1,1,0},
                      {0,1,0,0},
                      {1,1,0,0}
                      };
		System.out.println(IslandPerimeter(grid));
	}
	public static int IslandPerimeter(int[][] grid) {
		int area = 0, side = 0;
		for(int i = 0; i < grid.length; i++){
			for(int j = 0; j < grid[0].length; j++){
				if(grid[i][j] == 1) area++;
			}
		}
		for(int i = 0; i < grid.length; i++){
			for(int j = 0; j < grid[0].length - 1; j++){
				if(grid[i][j] == 1 && grid[i][j + 1] == 1) side++;
			}
		}
		for(int i = 0; i < grid.length - 1; i++){
			for(int j = 0; j < grid[0].length; j++){
				if(grid[i][j] == 1 && grid[i+1][j] == 1) side++;
			}
		}
		return area * 4 - side * 2;
	}
}