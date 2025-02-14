import React from "react";
import TodoList from "../components/TodoList";
import TodoAdd from "../components/TodoAdd";
import { Layout } from "antd";

const Todo = () => {
  const { Header, Sider, Content } = Layout;
  return (
    <Layout>
      <Header style={{ background: "#fff" }}>
        <h1>Todo List</h1>
      </Header>
      <Layout>
        <Sider style={{ background: "#fff" }}>
          <TodoAdd />
        </Sider>
        <Content>
          <TodoList title={"To Do"} />
          <TodoList title={"In Progress"} />
          <TodoList title={"Done"} />
        </Content>
      </Layout>
    </Layout>
  );
};

export default Todo;
