
/*
  문제명 : Add Two Numbers
  유형 : 문자열
  난이도 : ★★★★
  날짜 : 21/07/04
  링크 : https://leetcode.com/explore/interview/card/top-interview-questions-medium/107/linked-list/783/
  해설 : https://velog.io/@yeoj1n/Leetcode-2.Add-Two-Numbers-Java
*/

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
		ListNode answer = new ListNode();
		ListNode node = answer;
		
		int carry = 0;
        int sum = carry;
        while(l1 != null || l2 != null){
            sum = carry;
            if(l1 != null){
                sum += l1.val;
                l1 = l1.next;
            }
            if(l2 != null){
                sum += l2.val;
                l2 = l2.next;
            }
            carry = sum / 10;
            sum %= 10;

            ListNode temp = new ListNode(sum);
            node.next = temp;
            node = node.next;            
        }
        if(carry > 0){
            ListNode temp2 = new ListNode(carry);
            node.next = temp2;
            node = node.next;

        }
    
    return answer.next;
    }

}

/*
  문제명 : Implement strStr()
  유형 : 문자열
  난이도 : ★★★
  날짜 : 21/07/03
  링크 : https://leetcode.com/explore/interview/card/top-interview-questions-easy/127/strings/885/
  피드백 : 시간초과
*/
class Solution {
    public int strStr(String haystack, String needle) {
        int idx = -1;
        if("".equals(needle)){
            return 0;
        }
        if(haystack.length() < needle.length()){
            return -1;
        }
        
        for(int i = 0; i < haystack.length(); i++){      
            for(int j = 0; j < needle.length(); j++){
                if(i + j >= haystack.length()){
                    idx = -1;
                }
                else if(haystack.charAt(i + j) == needle.charAt(j)){
                    idx = i;
                }
                else{
                    idx = -1;
                    break;
                }
            }
            if(idx != -1){
                break;
            }
        }
        return idx;
    }
}

/*
  문제명 : Valid Palindrome
  유형 : 문자열
  난이도 : ★★★
  날짜 : 21/07/03
  링크 : https://leetcode.com/explore/interview/card/top-interview-questions-easy/127/strings/883/
  해설 : -
*/
class Solution {
    public boolean isPalindrome(String s) {
        String results = s.replaceAll("[^0-9a-zA-Z]", ""); //숫자, 알파벳만 남기고 제거
        results = results.toLowerCase();
        
        return isPalin(results);
    }

    private static boolean isPalin(String input) {
        int len = input.length();

        for (int i=0; i<len; i++) {
            if (input.charAt(i) != input.charAt(len-i-1)) {
                return false;
            }
        }

        return true;
    }
}


/*
  문제명 : First Unique Character in a String
  유형 : 문자열
  난이도 : ★
  날짜 : 21/07/03
  링크 : https://leetcode.com/explore/interview/card/top-interview-questions-easy/127/strings/881/
  해설 : -
*/
class Solution {
    public int firstUniqChar(String s) {
        int nonRepeatIndex = -1;
        int flag = 0;
        for(int i=0;i<s.length();i++){
            s.charAt(i);
            for(int j = 0; j < s.length(); j++){
                if(i != j && s.charAt(i) == s.charAt(j)){
                    flag = 1;
                    break;
                }
            }
            if(flag == 0){
                nonRepeatIndex = i;
                break;
            }
            flag = 0;
        }
        return nonRepeatIndex;
    }
}


/*
  문제명 : CyclicRotation
  유형 : 배열
  난이도 : ★
  날짜 : 21/07/01
  링크 : codility Lesson 2 (https://app.codility.com/programmers/lessons/2-arrays/)
  해설 : -
*/
class Solution {
    public int[] solution(int[] A, int K) {
        // write your code in Java SE 8
        int[] res = A;
        int tmp = 0;
        int len = A.length;
        for(int i = 0; i < K; i++) {
            tmp = res[len - 1];
            for(int j = len-1; j > 0; j--){
                res[j] = res[j-1];
            }
            res[0] = tmp;
        }
        return res;
    }
}

/*
  문제명 : BinaryGap
  유형 : 이진법 및 문자열
  난이도 : ★
  날짜 : 21/07/01
  링크 : codility Lesson 1
  해설 : -
*/
class Solution {
    public int solution(int N) {
        int flag = 0;
        int count = 0;
        int max = 0;
        String binaryString = Integer.toBinaryString(N);
        String[] strArray = binaryString.split("");

        for(String s : strArray) {
            if(s.equals("0") && flag == 0){
                flag = 1;
                count++;
            }
            else if (s.equals("0") && flag == 1){
                count++;
            }
            else{
                flag = 0;
                if(max < count){
                    max = count;
                }
                count = 0;
            }
        }
        return max;
    }
}


/*
  문제명 : 미로 탐색
  유형 : BFS
  난이도 : ★★★
  날짜 : 21/07/01
  링크 : https://www.acmicpc.net/problem/2178
  해설 : https://youngest-programming.tistory.com/195
*/
import java.util.*;

public class Main{
    
    private static int[][] map;
    private static boolean[][] visited;
    private static int[] dx = {-1, 0, 1, 0};
    private static int[] dy = {0, -1, 0, 1};
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt(); //행
        int M = sc.nextInt(); //열
        
        map = new int[M + 2][N + 2]; //BFS 시작점을 0, 0이 아닌 1, 1과 같은 형태로 사용하기 위해, 그리고 상,하,좌,우 탐색을 하는 방식이므로 여백(?)이 있어야 배열인덱스 초과 오류 없이 탐색이 가능하다
        visited = new boolean[M + 2][N + 2];
        
        sc.nextLine();
        for(int i = 1; i < N + 1; i++){
            String str = sc.nextLine();
            for(int j = 1; j < M + 1; j++){
                map[j][i] = str.charAt(j - 1) - '0';
            }
        }
        
        bfs(1,1);
        System.out.println(map[M][N]);
    }
    
    private static void bfs(int x, int y){
        Queue<Point> queue = new LinkedList<Point>();
        queue.offer(new Point(x, y)); //최초 시작지점 큐 삽입
        visited[x][y] = true; //최초 시작지점 방문처리
        
        while (!queue.isEmpty()){ //큐가 비어질때까지
            Point point = queue.poll(); //큐에서 하나를 뺀다
            for(int i = 0; i < 4; i++){
                int x2 = point.x + dx[i];
                int y2 = point.y + dy[i];
                
                if (!(map[x2][y2] == 0 || visited[x2][y2] == true)){//탐색가능 조건
                    visited[x2][y2] = true;
                    queue.offer(new Point(x2, y2));
                    //이전 좌표 값의 + 1을 가진다.
                    map[x2][y2] = map[point.x][point.y] + 1; //좌
                }
            }
        }
    }
    
    static class Point {
        int x;
        int y;
        
        //생성자
        Point(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
}

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
