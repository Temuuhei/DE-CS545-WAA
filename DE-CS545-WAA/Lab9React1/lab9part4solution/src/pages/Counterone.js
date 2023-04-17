import React from 'react';

const Counterone = ({counter, onCounterChange}) => {
  // const [count, setCount] = useState(7);
  const handleIncrement = () => {
    onCounterChange(counter + 1);
  };

  const handleDecrement = () => {
    onCounterChange(counter - 1);
  };

  return (
      <table>
          <tr>
              <th> <h2>{counter}</h2></th>
          </tr>
          <tr>
              <th>
                <button onClick={handleIncrement}>+1</button>
                <span> </span>
                <button onClick={handleDecrement}>-1</button>
              </th>
          </tr>
      </table>
  );
};

export default Counterone;
