
/*
  문제명 : 미로 탐색
  유형 : BFS
  난이도 : ★★★
  날짜 : 21/06/26
  링크 : https://www.acmicpc.net/problem/2178
  해설 : https://youngest-programming.tistory.com/195
*/


/*
  문제명 : 타겟 넘버
  유형 : DFS
  난이도 : ★★
  날짜 : 21/06/26
  링크 : https://programmers.co.kr/learn/courses/30/lessons/43165
*/
class TargetNumber {
    public int solution(int[] numbers, int target) {
        int ans = 0;
        ans = bfs(numbers, target, 1, numbers[0]) + bfs(numbers, target, 1, -numbers[0]);
        
        return ans;
    }
    

    public int bfs(int[] numbers, int target, int i, int sum){
        if(i == numbers.length){
            if(sum == target){
                return 1;
            }
            else{
                return 0;
            }
        }
        int result = 0;
        result += bfs(numbers, target, i+1, sum + numbers[i]);
        result += bfs(numbers, target, i+1, sum - numbers[i]);
        
        return result;
    }
}
