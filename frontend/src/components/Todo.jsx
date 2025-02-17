import React, { useState } from "react";
import { Card, Button, Modal, Tooltip } from "antd";
import { useTodos } from "../context/TodoProvider";
import TodoWrite from "./TodoWrite";

const Todo = ({ todo }) => {
  const { handleUpdateTodoStatus, handleDeleteTodo } = useTodos();
  const [isModalVisible, setIsModalVisible] = useState(false);

  const handleCardClick = () => {
    setIsModalVisible(true);
  };

  const handleModalClose = () => {
    setIsModalVisible(false);
  };

  const handleStatusChange = () => {
    const newStatus = todo.todoStatus === "TODO" ? "DONE" : "TODO";
    handleUpdateTodoStatus(todo.id, newStatus);
  };

  const handleDeleteClick = () => {
    const userConfirmed = window.confirm("정말 삭제하시겠습니까?");
    if (userConfirmed) {
      handleDeleteTodo(todo.id);
    }
  };

  const truncateTitle = (title) => {
    return title.length > 11 ? title.slice(0, 10) + "..." : title;
  };

  return (
    <>
      <Card
        title={
          <div
            style={{
              display: "flex",
              justifyContent: "space-between",
              alignItems: "center",
            }}
          >
            <Tooltip title={todo.title}>{truncateTitle(todo.title)}</Tooltip>
            <div style={{ display: "flex", gap: "10px" }}>
              <Button type="primary" danger onClick={handleDeleteClick}>
                삭제
              </Button>
              <Button type="default" onClick={handleStatusChange}>
                {todo.todoStatus === "TODO" ? "완료하기" : "완료취소"}
              </Button>
            </div>
          </div>
        }
        size="default"
        style={{ marginBottom: "10px", cursor: "pointer" }}
        onClick={handleCardClick}
      >
        {todo.content}
      </Card>
      <Modal
        title="update TODO"
        open={isModalVisible}
        onOk={handleModalClose}
        onCancel={handleModalClose}
        footer={null}
        width={400}
      >
        <TodoWrite oldTodo={todo} onClose={handleModalClose} />
      </Modal>
    </>
  );
};

export default Todo;
