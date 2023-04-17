import React from 'react';

const Counterfive = ({counter, onCounterChange}) => {
  // const [count, setCount] = useState(10);

  const handleIncrement = () => {
    onCounterChange(counter + 5);
  };

  const handleDecrement = () => {
    onCounterChange(counter - 5);
  };

  return (
      <table>
          <tr>
              <th> <h2>{counter}</h2></th>
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
