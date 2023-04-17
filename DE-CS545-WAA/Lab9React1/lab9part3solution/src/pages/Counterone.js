import React, { useState } from 'react';

const Counterone = () => {
  const [count, setCount] = useState(7);

  const handleIncrement = () => {
    setCount(count + 1);
  };

  const handleDecrement = () => {
    setCount(count - 1);
  };

  return (
      <table>
          <tr>
              <th> <h2>{count}</h2></th>
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
