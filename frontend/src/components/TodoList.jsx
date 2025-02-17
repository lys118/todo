import React from "react";
import Todo from "./Todo";
import { Flex } from "antd";
const TodoList = ({ title, todos }) => {
  const divStyle = {
    width: "400px",
  };
  return (
    <div style={divStyle}>
      <h1 style={{ textAlign: "center" }}>{title}</h1>
      {todos.map((todo) => (
        <Todo key={todo.id} todo={todo} />
      ))}
    </div>
  );
};

export default TodoList;
