import React, { useState } from 'react';

const Countereight = () => {
  const [count, setCount] = useState(48);

  const handleIncrement = () => {
    setCount(count + 8);
  };

  const handleDecrement = () => {
    setCount(count - 8);
  };

  return (
      <table>
          <tr>
              <th> <h2>{count}</h2></th>
          </tr>
          <tr>
              <th>
                <button onClick={handleIncrement}>+8</button>
                <span> </span>
                <button onClick={handleDecrement}>-8</button>
              </th>
          </tr>
      </table>
  );
};

export default Countereight;
