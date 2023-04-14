import axios from 'axios'

const TASKS_REST_API_URL='http://localhost:8080/tasks';

class TaskService {

    getTasks() {
        return axios.get(TASKS_REST_API_URL);
    }

    getTasksById(id) {
        return axios.get(TASKS_REST_API_URL+"/"+id)
    }

    addTask(task) {
        return axios.post(TASKS_REST_API_URL,task);
    }

    deleteTask(id) {
        return axios.delete(TASKS_REST_API_URL+"/"+id);
    }
}

export default new TaskService();