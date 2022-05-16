import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/** 2022.05.16 오후 7:28 박훈종 작성
 *  testa.txt는 1부터 10만까지 값을 누적하여 한 줄씩 기록합니다.
 *  testb.txt는 1부터 10만까지 1000개의 값을 랜덤으로 생성하여 한 줄씩 기록합니다.
 *  사용자의 동의를 받아 testb와 testa를 비교하여 testa에 중복되어 있는 데이터를 제거하여 ArrayList로 testa파일에 재생성합니다.
 */


public class test {
    public static void main(String[] args) throws IOException {

        // 파일 경로 설정
        String fileAName = "/Users/jongdroid/Desktop/test/testa.txt";
        String fileBName = "/Users/jongdroid/Desktop/test/testb.txt";

        // 파일 쓰기 --> fileA, fileB로 두 개의 파일을 각각 설정
        BufferedWriter fileA = new BufferedWriter(new FileWriter(fileAName, true));
        BufferedWriter fileB = new BufferedWriter(new FileWriter(fileBName, true));

        // 파일 읽기 (파일 경로로부터 파일을 읽는다)
        BufferedReader readerA = new BufferedReader(new FileReader("/Users/jongdroid/Desktop/test/testa.txt"));
        BufferedReader readerB = new BufferedReader(new FileReader("/Users/jongdroid/Desktop/test/testb.txt"));

        List<String> aLines = new ArrayList<String>();
        List<String> bLines = new ArrayList<String>();

        String aLine = null;
        String bLine = null;

        for (int i = 1; i <= 100000; i++) {
            String data = i + "";
            fileA.write(data);
            fileA.newLine();
        }

        for (int i = 1; i <= 1000; i++) {
            String ranNum = "";
            ranNum = String.valueOf((int) (Math.random() * 100000));
            fileB.write(ranNum);
            fileB.newLine();
        }

        System.out.println("textA=번호 10만개, textB 랜덤의=1000개 번호를 생성하였습니다.");
        fileA.flush();
        fileB.close();


        System.out.println("중복된 데이터를 제거하시겠습니까? (yes or no)");
        Scanner sc = new Scanner(System.in);
        String saveAnswer = sc.nextLine();

        // 사용자가 데이터 중복제거를 동의하였습니다.
        if (saveAnswer.equals("yes")) {
            System.out.println("중복 데이터 제거를 진행하겠습니다.");

            // 상단에서 a,b 파일에 write한 데이터들을 한 줄씩 읽은 후 저장합니다.
            try {
                while ((aLine = readerA.readLine()) != null) {
                    aLines.add(aLine);
                }
                readerA.close();

                while ((bLine = readerB.readLine()) != null) {
                    bLines.add(bLine);
                }
                readerB.close();

                for (String b : bLines) {
                    boolean isEquals = false;
                    for (String a : aLines) {
                        if (b.equals(a)) isEquals = true;
                    }

                    if (isEquals) {
                        // System.out.println("제거 대상 데이터입니다." + b);
                        aLines.remove(b);
                        fileA.flush();
                    }
                }

                fileA.write("중복제거를 완료하였습니다." + String.valueOf(aLines));
                fileA.newLine();
                fileA.close();
                System.out.println("작업이 완료되었습니다. 메모장을 확인해보세요.");

            } catch (Exception e) {
            }
        }

        else {
            System.out.println("Yes를 입력하시면 중복된 데이터를 제거합니다. 프로그램을 종료합니다.");
            System.exit(0);
        }
    }
}