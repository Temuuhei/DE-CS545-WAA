import React, { useState } from 'react';

const Counterfive = () => {
  const [count, setCount] = useState(10);

  const handleIncrement = () => {
    setCount(count + 5);
  };

  const handleDecrement = () => {
    setCount(count - 5);
  };

  return (
      <table>
          <tr>
              <th> <h2>{count}</h2></th>
          </tr>
          <tr>
              <th>
                <button onClick={handleIncrement}>+5</button>
                <span> </span>
                <button onClick={handleDecrement}>-5</button>
              </th>
          </tr>
      </table>
  );
};

export default Counterfive;
