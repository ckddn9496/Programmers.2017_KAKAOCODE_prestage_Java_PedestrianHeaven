// SKILL CHECK LEVEL3 01
// 보행자 천국
public class Main {

	public static void main(String[] args) {
//		int m = 3;
//		int n = 3;
//		int[][] cityMap = {{0, 0, 0}, {0, 0, 0}, {0, 0, 0}}; 
		// return 6

//		int m = 3;
//		int n = 6;
//		int[][] cityMap = {{0, 2, 0, 0, 0, 2}, {0, 0, 2, 0, 1, 0}, {1, 0, 0, 2, 2, 0}};
		// return 2

		int m = 4;
		int n = 6;
		int[][] cityMap = {{0, 0, 0, 0, 0, 0}, {0, 0, 2, 0, 1, 0}, {0, 2, 0, 0, 0, 0}, {1,0,0,0,0,0}};
		// return 2

		
		System.out.println(new Solution().solution(m, n, cityMap));
	}

}

class Solution {
	int MOD = 20170805;
	int[][] map;
	public int solution(int m, int n, int[][] cityMap) {
		map = new int[m+2][n+2];
		
		for (int i = 1; i <= m; i++) {
			for (int j = 1; j <= n; j++) {
				if (cityMap[i-1][j-1] == 1)
					map[i][j] = -1;
				else if (cityMap[i-1][j-1] == 2)
					map[i][j] = -2;
				else 
					map[i][j] = 0;
			}
		}
		map[1][1] = 1;
//		printMap(map);
		
		for (int i = 1; i <= m; i++) {
			for (int j = 1; j <= n; j++) {
				
				if (map[i][j] == -1) {
					map[i][j] = 0;
					
				} else if (map[i][j] == -2) {
					// 오른쪽
					if (map[i][j-1] >= 0) {
						for (int idx = j+1; idx < n+2; idx++) {
							if (map[i][idx] >= 0) {
								map[i][idx] = (map[i][idx] + map[i][j-1]) % MOD;
								break;
							} else if (map[i][idx] == -1) {
								break;
							}
						}
					}
					
					// 아래쪽
					if (map[i-1][j] >= 0) {
						for (int idx = i+1; idx < m+2; idx++) {
							if (map[idx][j] >= 0) {
								map[idx][j] = (map[idx][j] + map[i-1][j]) % MOD;
								break;
							} else if (map[idx][j] == -1) {
								break;
							}
								
						}
					}

				} else { // status == 0
					if (map[i-1][j] >= 0)
						map[i][j] = (map[i][j] + map[i-1][j]) % MOD;
					if (map[i][j-1] >= 0)
						map[i][j] = (map[i][j] + map[i][j-1]) % MOD;
				}
				
			}
		}
//		printMap(map);
		return map[m][n];
	}
	
//	private void printMap(int[][] map) {
//		for (int i = 0; i < map.length; i++) {
//			for (int j = 0; j < map[0].length; j++) {
//				System.out.print(String.format("%4d", map[i][j]));
//			}
//			System.out.println();
//		} 
//		System.out.println("\n\n");
//	}
}