import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;


public class Solution3 {
    static char[] Answer;

    public static void main(String[] args) throws IOException {
        /*
         * 아래의 System.setIn 함수는 sample_input.txt 를 read only 형식으로 열어 표준 입력 스트림으로 redirection 합니다.
         * 따라서, 키보드를 통한 표준 입력으로 입력 값을 전달하는 대신 sample_input.txt 파일 내에 존재하는 데이터를 표준 입력 스트림으로 전달합니다. 본
         * 문제에 대한 소스코드를 테스트함에 있어, 원하는 입력 데이터를 sample_input.txt에 저장하여 System.setIn 함수를 이용하면, 표준 입력
         * (키보드 입력) 대신 sample_input.txt 파일의 데이터가 전달됩니다. 그러므로 테스트를 수행할 때에는 아래의 System.setIn 함수의 주석을
         * 제거하여 사용할 수 있습니다. 코드를 제출하실 때에는 반드시 System.setIn 함수를 지우거나 주석 처리 해야합니다.
         */
        System.setIn(new FileInputStream("sample_input3.txt"));

        /*
         * 표준입력 System.in 으로부터 스캐너를 만들어 데이터를 읽어옵니다.
         */
        Scanner sc = new Scanner(System.in);

        int T; // 테스트 케이스의 수
        int i, l, key;
        int decodedCodeInt = 0;
        String tmp;

        Answer = new char[200];

        /* 테스트 케이스의 수 T */
        T = sc.nextInt();
        sc.nextLine();

        /* 각 테스트 케이스에 대한 루프문 */
        for (i = 0; i < T; i++) {

            /*************************************************************************************/
            // 이 곳에 알고리즘을 구현합니다.
            // Input 배열에 저장된 입력 데이터에 대한 정답을 Answer 배열에 저장하는 것을 가정합니다.
            /*************************************************************************************/
            tmp = sc.nextLine();

            l = 0;
            key = Integer.parseInt(tmp.substring(0, 3), 2);
            // System.out.println("key:" + key);

            for (int j = 3; j < tmp.length(); j = j + 8) {
                decodedCodeInt = Integer.parseInt(tmp.substring(j, j + 8), 2) - key;
                Answer[l++] = (char) ('A' + decodedCodeInt);
            }

            /* 출력부분 */
            System.out.printf("#%d ", i + 1);
            tmp = new String(Answer, 0, Answer.length);
            System.out.printf("%s\n", tmp);
            for (l = 0; l < 200; l++)
                Answer[l] = 0;
        }

        sc.close();
    }
}