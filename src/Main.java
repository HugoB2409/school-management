public class Main {

    public static void main(String[] args) {
        new Menu(new Database().getConnection());
    }
}