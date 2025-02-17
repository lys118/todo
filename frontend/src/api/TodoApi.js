import axios from "axios";
import React from "react";

const TODO_API_SERVER_HOST = "http://localhost:8080/api/todos";

export const getListApi = async (todoTypeObj) => {
  try {
    const res = await axios.get(`${TODO_API_SERVER_HOST}`, {
      params: todoTypeObj,
    });
    return res.data;
  } catch (error) {
    console.error("TODO 조회 실패");
    throw error;
  }
};

export const postTodoApi = async (todo) => {
  try {
    const res = await axios.post(`${TODO_API_SERVER_HOST}`, todo);
    return res.data;
  } catch (error) {
    console.error("TODO 작성 실패");
    throw error;
  }
};

export const putTodoApi = async (todoId, todo) => {
  try {
    const res = await axios.put(`${TODO_API_SERVER_HOST}/${todoId}`, todo);
    return res.data;
  } catch (error) {
    console.error("TODO 수정 실패");
    throw error;
  }
};

export const patchTodoStatusApi = async (todoId, todoStatus) => {
  try {
    const res = await axios.patch(`${TODO_API_SERVER_HOST}/${todoId}`, {
      todoStatus: todoStatus,
    });
    return res.data;
  } catch (error) {
    console.error("TODO 상태 변경 실패");
    throw error;
  }
};

export const deleteTodoApi = async (todoId) => {
  try {
    const res = await axios.delete(`${TODO_API_SERVER_HOST}/${todoId}`);
    return res.data;
  } catch (error) {
    console.error("TODO 삭제 실패");
    throw error;
  }
};
