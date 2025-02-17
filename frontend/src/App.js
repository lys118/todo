import { TodoProvider } from "./context/TodoProvider";
import TodoPage from "./pages/TodoPage";

const App = () => {
  return (
    <TodoProvider>
      <TodoPage />
    </TodoProvider>
  );
};

export default App;
