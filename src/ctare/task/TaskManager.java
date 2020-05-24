package ctare.task;

import java.util.ArrayList;

/**
 * Created by ctare on 2020/05/19.
 */
public class TaskManager {
    public final ArrayList<Task> tasks = new ArrayList<>();

    { // test
        tasks.add(new TestTask());
    }

    public Task getTask() {
        // TODO: アルゴリズムを考える
        for (Task task : tasks) {
            if (task.at() > 0) {
                return task;
            }
        }
        return null;
    }
}
