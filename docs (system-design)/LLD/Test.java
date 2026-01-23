// class Singleton {
//     private static Singleton instance = null;

//     private Singleton() {
//         System.out.println("Singleton constructor called.");
//     }

//     public synchronized static Singleton getInstance() {
//         if (instance == null) {
//             instance = new Singleton();
//         }
//         return instance;
//     }
// }
class Singleton {
    private static Singleton instance = null;

    private Singleton() {
        System.out.println("Singleton constructor called.");
    }

    public static Singleton getInstance() {
        if (instance == null) {
            synchronized (Singleton.class) {
                if (instance == null) {
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }
}

public class Test {
    public static void main(String[] args) {
        Runnable task = () -> {
            Singleton s = Singleton.getInstance();
            System.out.println(
                    Thread.currentThread().getName() +
                            " got instance with hashCode: " +
                            s.hashCode());
        };

        Thread t1 = new Thread(task, "Thread-1");
        Thread t2 = new Thread(task, "Thread-2");

        t1.start();
        t2.start();
    }
}