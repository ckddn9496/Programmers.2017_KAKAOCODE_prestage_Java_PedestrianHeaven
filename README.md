# Programmers.2017_KAKAOCODE_prestage_Java_PedestrianHeaven

## 프로그래머스 > 코딩테스트 연습 > 2017 카카오코드 예선 > 보행자천국

### 1. 문제설명

문제: https://programmers.co.kr/learn/courses/30/lessons/1832

input으로 지도의 길이로 세로 `m`, 가로 `m`, 지도의 값 `int[][] city_map`이 들어온다. `city_map`은 세가지 값을 가진다. `0`인 경우 자동차가 자유롭게 지나갈 수 있따. `1`인 경우 자동차의 통행이 금지되어 지나갈 수 없으며, `2`인 경우 좌회전과 우회전이 안된다. 즉, 오던 방향으로만 진행 가능하다.

이때, `(0,0)`부터 `(m-1,n-1)`로 가는 경우의 수를 `20170805`로 나누었을 때 나머지값을 return하는 문제.

### 2. 풀이

`(m+2, n+2)`크기의 배열을 선언하여 중앙에 `city_map`의 정보를 담았다. 이 이차원 배열에서 `map[i][j]`안의 값은 시작점 부터 `(i,j)`까지 경우의 수를 의미한다. `(i,j)`의 값은 `map[i][j]`의 상태에 따라 다르게 계산될 수 있다. `0`을 가지면 왼쪽과 위, 두 방향에서 오는 값을 더하여 계산이 가능하다. 왼쪽이나 위가 `-1`을 가지면 해당 방향에서 오는 길이 없기 때문에 더할 값이 없으며 `-2`일 경우 같은 방향에서 오는 값만 받아야 하므로 왼쪽과 위쪽 방향을 고려하여 건너뛴 다음의 `0`이 나오는 지점에 값을 전달해 주거나, `-1`이 나오는 경우 길이 없이지는것을 판단해 주어야 한다. 위과정을 수행 한 후, `map[m][n]`을 return하면 최종 도착지까지의 길의 경우의 수를 얻을 수 있다.

```java

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


```
