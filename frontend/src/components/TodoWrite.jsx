import React, { useEffect } from "react";
import { Button, Form, Input } from "antd";
import { useTodos } from "../context/TodoProvider";

const TodoWrite = ({ oldTodo, onClose }) => {
  const { handleAddTodo, handleUpdateTodo, todoTypeObj } = useTodos();
  const [form] = Form.useForm();

  useEffect(() => {
    if (oldTodo) {
      form.setFieldsValue({
        todo: {
          title: oldTodo.title,
          content: oldTodo.content,
        },
      });
    }
  }, [oldTodo, form]);

  const layout = {
    labelCol: {
      span: 8,
    },
    wrapperCol: {
      span: 16,
    },
  };

  const validateMessages = {
    required: "${label} is required!",
  };
  const onFinish = (values) => {
    if (oldTodo) {
      const todo = {
        ...values.todo,
      };
      handleUpdateTodo(oldTodo.id, todo);
    } else {
      const todo = {
        ...values.todo,
        todoStatus: "TODO",
        todoType: todoTypeObj.todoType,
        localDate: todoTypeObj.localDate,
      };
      handleAddTodo(todo);
    }
    form.resetFields();
    onClose();
  };

  return (
    <Form
      {...layout}
      form={form}
      onFinish={onFinish}
      style={{
        maxWidth: "90%",
      }}
      validateMessages={validateMessages}
      requiredMark={false}
    >
      <Form.Item
        name={["todo", "title"]}
        label="Title"
        rules={[{ required: true }]}
      >
        <Input maxLength={20} />
      </Form.Item>
      <Form.Item
        name={["todo", "content"]}
        label="Content"
        rules={[{ required: true }]}
      >
        <Input.TextArea maxLength={100} autoSize={{ minRows: 3, maxRows: 6 }} />
      </Form.Item>
      <Form.Item label={null}>
        <Button type="primary" htmlType="submit">
          Submit
        </Button>
      </Form.Item>
    </Form>
  );
};

export default TodoWrite;
