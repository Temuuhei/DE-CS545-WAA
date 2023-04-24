import { useState } from 'react';

const initialTodoList = [
  { todoid: '1', name: 'Presentation' },
  { todoid: '2', name: 'Cook tsuivan' },
  { todoid: '3', name: 'Pick up sanjaa nar' },
];

const initialDoneList = [
  { todoid: '1', name: 'Login page' },
  { todoid: '2', name: 'Payment methods' },
  { todoid: '3', name: 'Stripe connections' },
];

const TodoList = () => {
  const [todoList, setTodoList] = useState(initialTodoList);
  const [doneList, setDoneList] = useState(initialDoneList);

  const handleTodoToDone = (todoid) => {
    const todoItem = todoList.find((item) => item.todoid === todoid);
    setTodoList(todoList.filter((item) => item.todoid !== todoid));
    setDoneList([...doneList, todoItem]);
  };

  const handleDoneToTodo = (todoid) => {
    const doneItem = doneList.find((item) => item.todoid === todoid);
    setDoneList(doneList.filter((item) => item.todoid !== todoid));
    setTodoList([...todoList, doneItem]);
  };

  return (
    <div>
       <h1>Todo List</h1>
            <table border="1">
                <thead>
                    <tr>
                        <th>Number</th>
                        <th>Name</th>
                    </tr>
                </thead>
                <tbody>
                    {todoList.map(
                        todo => (
                            <tr key={todo.todoid}>
                                <td>{todo.todoid}</td>
                                <td>{todo.name}</td>
                                <td><button onClick={() => handleTodoToDone(todo.todoid)} >Done</button></td>
                            </tr>
                        )
                    )}
                </tbody>
            </table>
        <h1>Done List</h1>
        <table border="1">
            <thead>
                <tr>
                    <th>Number</th>
                    <th>Name</th>
                </tr>
            </thead>
            <tbody>
                {doneList.map(
                    todo => (
                        <tr key={todo.todoid}>
                            <td>{todo.todoid}</td>
                            <td>{todo.name}</td>
                            <td><button onClick={() => handleDoneToTodo(todo.todoid)} >Todo</button></td>
                        </tr>
                    )
                )}
            </tbody>
        </table>
    </div>
  );
};

export default TodoList;
