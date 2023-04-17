import React from 'react';

const Countereight = ({counter, onCounterChange}) => {
  // const [count, setCount] = useState(48);

  const handleIncrement = () => {
    onCounterChange(counter + 8);
  };

  const handleDecrement = () => {
    onCounterChange(counter - 8);
  };

  return (
      <table>
          <tr>
              <th> <h2>{counter}</h2></th>
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
