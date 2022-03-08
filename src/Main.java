import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String con;
        int studentId = 0;

        List<Student> studentList = new ArrayList<>(); // student 객체를 담을 리스트

        /* 학생정보 입력 */
        inputStudentInfo(sc, studentId, studentList);

        /*석차구하기(StudentMgm.getRank) + 조회*/
        StudentMgm.getRank(studentList);
        showStudentsInfo(studentList);

        /*성적순 정렬(StudentMgm.sortByScore) + 조회*/
        StudentMgm.sortByScore(studentList);
        showStudentsInfo(studentList);
    }


    private static void inputStudentInfo(Scanner sc, int studentId, List<Student> studentList) {
        String con;
        while (true) {
            System.out.print("이름 : ");
            String name = sc.nextLine();
            if (!CheckCollectInput.checkName(name)) {
                System.out.println("유효하지 않은 이름 입니다.");
                continue;
            }
            Integer[] scores=null;
            while(scores==null) {
                try {
                    System.out.print("점수(국.영.수) : ");
                    scores = Arrays.stream(sc.nextLine().split(" "))// 공백으로 받은 결과를
                            .map(Integer::parseInt)                // integer로 casting하고
                            .toArray(Integer[]::new);                               // Integer 배열로 변경
                    if (!CheckCollectInput.checkScores(scores)) {                   //유효하지않으면 다시입력
                        System.out.println("유효하지 않은 점수 입력입니다.");
                        scores=null;
                    }
                }catch (Exception e){
                    scores=null;
                    e.printStackTrace();
                }
            }

            String addr = null;
            while(addr==null) {
                try {
                    System.out.print("주소 : ");
                    addr = sc.nextLine();
                    if (!CheckCollectInput.checkAddress(addr)) {
                        System.out.println("유효하지 않은 주소 입력입니다.");
                        addr=null;
                    }
                }
                catch (Exception e){
                    addr=null;
                    e.printStackTrace();
                }
            }

            // student 객체 생성 --> studentList에 추가
            studentList.add(new Student(++studentId, name, scores, addr)); // id+1 해서 객체생성

            // continue 체크...
            System.out.println("...is continue? (y/n)");
            con = sc.nextLine();
            if (!con.equals("y")) break;
        }
    }

    private static void showStudentsInfo(List<Student> studentList){ // FB : 학생정보 출력은 하나의 메서드로...
        System.out.println("학생 정보입니다.");
        for (Student student : studentList) {
            System.out.print("석차 = " + student.getRank() + " ");
            System.out.print("학번 = " + student.getStudentId() + " ");
            System.out.print("이름 = " + student.getName() + " ");
            System.out.print("총점 = " + student.getTotal()+" ");
            System.out.println("주소 = " + student.getAddr());
        }
    }
}
