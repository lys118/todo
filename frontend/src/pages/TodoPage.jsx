import React, { useEffect, useState } from "react";
import TodoList from "../components/TodoList";
import { Layout, Typography, Button, Modal, Flex } from "antd";
import { useTodos } from "../context/TodoProvider";
import TodoWrite from "../components/TodoWrite";

const TodoPage = () => {
  const { Header, Sider, Content } = Layout;
  const { Title } = Typography;
  const { todos } = useTodos();

  //모달창설정////////////////////////
  const [isModalVisible, setIsModalVisible] = useState(false);

  const showModal = () => {
    setIsModalVisible(true);
  };

  const handleOk = () => {
    setIsModalVisible(false);
  };

  const handleCancel = () => {
    setIsModalVisible(false);
  };
  //css////////////////////////////////////
  const layoutStyle = {
    minHeight: "100vh",
  };

  const headerStyle = {
    background: "#ffd",
    textAlign: "center",
    height: "100px",
  };
  const siderStyle = {
    background: "#fcc",
    textAlign: "center",
  };
  const buttonContainerStyle = {
    display: "flex",
    justifyContent: "center",
    margin: "20px 0",
  };

  return (
    <Layout style={layoutStyle}>
      <Header style={headerStyle}>
        <Title level={1}>MY TODO LIST</Title>
      </Header>
      <Layout>
        <Sider style={siderStyle} width={400}>
          <Title level={2}>평소에 할일</Title>
          <Title level={2}>오늘의 할일</Title>
        </Sider>
        <Content>
          <div style={buttonContainerStyle}>
            <Button type="primary" onClick={showModal}>
              Add Todo
            </Button>
          </div>
          <Flex gap="large" justify="center">
            <TodoList
              title={"To Do"}
              todos={todos.filter((todo) => todo.todoStatus === "TODO")}
            />
            <TodoList
              title={"Done"}
              todos={todos.filter((todo) => todo.todoStatus === "DONE")}
            />
          </Flex>
        </Content>
      </Layout>
      <Modal
        title="add Todo"
        open={isModalVisible}
        onOk={handleOk}
        onCancel={handleCancel}
        footer={null}
        width={400}
      >
        <TodoWrite onClose={handleCancel} />
      </Modal>
    </Layout>
  );
};

export default TodoPage;
