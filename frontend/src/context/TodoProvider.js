import React, { createContext, useState, useContext, useEffect } from "react";
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
export const TodoProvider = ({ children }) => {
  const [todos, setTodos] = useState([...todoInitData]);

  //api///////////////////
  const getTodoList = async () => {
    try {
      const res = await getListApi();
      setTodos(res);
    } catch (error) {
      alert("todo 조회 실패");
    }
  };

  const postTodo = async (todo) => {
    try {
      const res = await postTodoApi(todo);
      return res;
    } catch (error) {
      alert("todo 작성 실패");
    }
  };

  const putTodo = async (todoId, todo) => {
    try {
      const res = await putTodoApi(todoId, todo);
      return res;
    } catch (error) {
      alert("todo 수정 실패");
    }
  };

  const updateTodoStatus = async (todoId, todoStatus) => {
    try {
      const res = await patchTodoStatusApi(todoId, todoStatus);
      return res;
    } catch (error) {
      alert("todo 조회 실패");
    }
  };

  const deleteTodo = async (todoId) => {
    try {
      const res = await deleteTodoApi(todoId);
      return res;
    } catch (error) {
      alert("todo 조회 실패");
    }
  };

  useEffect(() => {
    getTodoList();
  }, []);

  //handle//////////////////////////
  //추가핸들러
  const handleAddTodo = async (todo) => {
    const result = await postTodo(todo);
    if (result) {
      setTodos([...todos, result]);
    }
  };

  //수정핸들러
  const handleUpdateTodo = async (todoId, todo) => {
    console.log(todo);
    const result = await putTodo(todoId, todo);
    if (result) {
      setTodos((prevTodos) =>
        prevTodos.map((todo) =>
          todo.id === todoId ? { ...todo, ...result } : todo
        )
      );
    }
  };

  //상태 업데이트핸들러
  const handleUpdateTodoStatus = async (todoId, todoStatus) => {
    const result = await updateTodoStatus(todoId, todoStatus);
    if (result === "todoStatus update success") {
      setTodos(
        todos.map((todo) =>
          todo.id === todoId ? { ...todo, todoStatus: todoStatus } : todo
        )
      );
    }
  };
  //삭제 핸들러
  const handleDeleteTodo = async (todoId) => {
    const result = await deleteTodo(todoId);
    if (result === "todo delete success") {
      setTodos(todos.filter((todo) => todo.id !== todoId));
    }
  };

  return (
    <TodoContext.Provider
      value={{
        todos,
        getTodoList,
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
