import React from "react";
import { Card } from "antd";

const Todo = () => {
  return (
    <Card
      title="Card title"
      variant="borderless"
      style={{
        width: 300,
      }}
    >
      <p>Card content</p>
      <p>Card content</p>
      <p>Card content</p>
    </Card>
  );
};

export default Todo;
