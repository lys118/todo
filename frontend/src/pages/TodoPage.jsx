import React from "react";
import TodoList from "../components/TodoList";
import TodoAdd from "../components/TodoAdd";
import { Layout, Typography, Flex } from "antd";

const TodoPage = () => {
  const { Header, Sider, Content } = Layout;
  const { Title } = Typography;

  const layoutStyle = {
    minHeight: "100vh",
  };

  const headerStyle = {
    background: "#ffc",
    textAlign: "center",
    height: "100px",
  };

  const sideStyle = {
    background: "#ffa",
    textAlign: "center",
  };
  return (
    <Layout style={layoutStyle}>
      <Header style={headerStyle}>
        <Title level={1}>MY TODO LIST</Title>
      </Header>
      <Layout>
        <Sider width="15%" style={sideStyle}>
          <TodoAdd />
        </Sider>
        <Content>
          <Flex gap="large" justify="center">
            <TodoList title={"To Do"} />
            <TodoList title={"In Progress"} />
            <TodoList title={"Done"} />
          </Flex>
        </Content>
      </Layout>
    </Layout>
  );
};

export default TodoPage;
