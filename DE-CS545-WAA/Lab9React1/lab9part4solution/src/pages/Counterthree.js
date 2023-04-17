import React from 'react';

const Counterthree = ({counter, onCounterChange}) => {
// const [count, setCount] = useState(-15);
    const handleIncrement = () => {
        onCounterChange(counter + 3);
    };

    const handleDecrement = () => {
        onCounterChange(counter - 3);
    }

    let component = (
        <table>
            <tr>
                <th> <h2>{counter}</h2></th>
            </tr>
            <tr>
                <button onClick={handleIncrement}>+3</button>
                <span> </span>
                <button onClick={handleDecrement}>-3</button>
            </tr>
        </table>
    );

    return component;

    }

    export default Counterthree;

