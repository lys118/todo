import React, {
  createContext,
  useState,
  useContext,
  useEffect,
  useCallback,
} from "react";
import {
  getListApi,
  postTodoApi,
  putTodoApi,
  patchTodoStatusApi,
  deleteTodoApi,
} from "../api/TodoApi";

const TodoContext = createContext();

const todoInitData = [
  {
    id: 0,
    title: "",
    content: "",
    todoStatus: "",
    todoType: "",
    localDate: "",
  },
];

const todoTypeObjInitData = {
  todoType: "GENERAL",
  localDate: "",
};
export const TodoProvider = ({ children }) => {
  const [todos, setTodos] = useState([...todoInitData]);
  const [todoTypeObj, setTodoTypeObj] = useState(todoTypeObjInitData);

  //api///////////////////
  const getTodoList = useCallback(async () => {
    try {
      const res = await getListApi(todoTypeObj);
      setTodos(res);
    } catch (error) {
      alert("todo 조회 실패");
    }
  }, [todoTypeObj]);

  const postTodo = useCallback(async (todo) => {
    try {
      const res = await postTodoApi(todo);
      return res;
    } catch (error) {
      alert("todo 작성 실패");
    }
  }, []);

  const putTodo = useCallback(async (todoId, todo) => {
    try {
      const res = await putTodoApi(todoId, todo);
      return res;
    } catch (error) {
      alert("todo 수정 실패");
    }
  }, []);

  const updateTodoStatus = useCallback(async (todoId, todoStatus) => {
    try {
      const res = await patchTodoStatusApi(todoId, todoStatus);
      return res;
    } catch (error) {
      alert("todo 상태변경 실패");
    }
  }, []);

  const deleteTodo = useCallback(async (todoId) => {
    try {
      const res = await deleteTodoApi(todoId);
      return res;
    } catch (error) {
      alert("todo 삭제 실패");
    }
  }, []);

  useEffect(() => {
    getTodoList();
  }, [todoTypeObj]);

  //handle//////////////////////////
  //추가핸들러
  const handleAddTodo = useCallback(
    async (todo) => {
      const result = await postTodo(todo);
      if (result) {
        setTodos((prevTodos) => [...prevTodos, result]);
      }
    },
    [postTodo]
  );

  //수정핸들러
  const handleUpdateTodo = useCallback(
    async (todoId, todo) => {
      const result = await putTodo(todoId, todo);
      if (result) {
        setTodos((prevTodos) =>
          prevTodos.map((t) => (t.id === todoId ? { ...t, ...result } : t))
        );
      }
    },
    [putTodo]
  );

  //상태 업데이트핸들러
  const handleUpdateTodoStatus = useCallback(
    async (todoId, todoStatus) => {
      const result = await updateTodoStatus(todoId, todoStatus);
      if (result === "todoStatus update success") {
        setTodos((prevTodos) =>
          prevTodos.map((t) =>
            t.id === todoId ? { ...t, todoStatus: todoStatus } : t
          )
        );
      }
    },
    [updateTodoStatus]
  );

  //삭제 핸들러
  const handleDeleteTodo = useCallback(
    async (todoId) => {
      const result = await deleteTodo(todoId);
      if (result === "todo delete success") {
        setTodos((prevTodos) => prevTodos.filter((t) => t.id !== todoId));
      }
    },
    [deleteTodo]
  );

  return (
    <TodoContext.Provider
      value={{
        todos,
        todoTypeObj,
        setTodoTypeObj,
        handleAddTodo,
        handleUpdateTodo,
        handleUpdateTodoStatus,
        handleDeleteTodo,
      }}
    >
      {children}
    </TodoContext.Provider>
  );
};

export const useTodos = () => useContext(TodoContext);
