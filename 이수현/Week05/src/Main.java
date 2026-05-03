import java.util.*;

public class Main {
    private List<String> assignments;
    private Map<String, Set<String>> submissions;

    public Main(List<String> assignments) {
        this.assignments = new ArrayList<>(assignments);
        this.submissions = new HashMap<>();
        // 각 과제에 빈 Set 초기화
        for (String a : assignments) {
            submissions.put(a, new HashSet<>());
        }
    }

    public void submit(String assignment, String student) {
        // TODO: submissions에서 해당 과제 Set 가져와서 student 추가
        // Set이니까 중복은 자동으로 처리됩니다!
        submissions.get(assignment).add(student);
    }

    public List<String> getNotSubmitted(String assignment, List<String> allStudents) {
        List<String> notSubmitted = new ArrayList<>(allStudents);
        notSubmitted.removeAll(submissions.get(assignment));
        return notSubmitted;
    }

    public double getSubmissionRate(String assignment, int totalStudents) {
        // TODO: (제출자 수 / 전체 학생 수) * 100
        int count = submissions.get(assignment).size();
        return (double) count / totalStudents * 100;

    }

    public void printReport() {
        // TODO: 각 과제별 제출자 목록 출력 (정렬된 순서로)
        for (String ass : assignments){
            List<String> sorted = new ArrayList<>(submissions.get(ass));
            Collections.sort(sorted);
            System.out.println("["+ass+"]" + sorted.size() + "명: " + sorted);
        }
    }

    public static void main(String[] args) {
        List<String> hw = List.of("컬렉션 실습", "정렬 구현", "Map 활용");
        List<String> students = List.of("Alice", "Bob", "Charlie", "Diana");

        Main tracker = new Main(hw);
        tracker.submit("컬렉션 실습", "Alice");
        tracker.submit("컬렉션 실습", "Bob");
        tracker.submit("컬렉션 실습", "Alice"); // 중복 — 무시돼야 함
        tracker.submit("정렬 구현", "Charlie");

        tracker.printReport();
        System.out.println("미제출: " + tracker.getNotSubmitted("컬렉션 실습", students));
        System.out.printf("제출률: %.0f%%%n", tracker.getSubmissionRate("컬렉션 실습", students.size()));
    }
}