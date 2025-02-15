import React from "react";
import Todo from "./Todo";
import { Flex } from "antd";
const TodoList = ({ title }) => {
  return (
    <div>
      <h1 style={{ textAlign: "center" }}>{title}</h1>
      <Todo />
      <Todo />
    </div>
  );
};

export default TodoList;
