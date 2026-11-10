public class TaskQueue {

    private String[] buffer;
    private int head;
    private int tail;
    private int size;

    public TaskQueue(int capacity) {
        this.buffer = new String[capacity];
        head = 0;
        tail = 0;
        size = 0;
    }

    public void enqueue(String task) {
        if (size == buffer.length) {
            System.err.println("Queue overflow! Task rejected.");
            return;
        }
        buffer[tail] = task;
        tail = (tail + 1) % buffer.length;
        size++;
    }

    public String dequeue() {
        if (size == 0) {
            System.err.println("Queue is empty!");
            return null;
        }
        String task = buffer[head];
        buffer[head] = null;
        head = (head + 1) % buffer.length;
        size--;
        return task;
    }

    public static void main(String[] args) {
        TaskQueue queue = new TaskQueue(1);
        queue.enqueue("Task 1");
        queue.enqueue("Task 2");
        queue.enqueue("Task 3");
        String pulledTask = queue.dequeue();
        queue.enqueue("Task 4");
        System.out.println("Executed task: " + pulledTask);
    }

}

