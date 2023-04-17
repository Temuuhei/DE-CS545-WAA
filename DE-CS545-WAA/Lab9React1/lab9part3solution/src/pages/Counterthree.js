import React, { useState } from 'react';

const Counterthree = () => {
    const [count, setCount] = useState(-15);

    const handleIncrement = () => {
        setCount(count + 3);
    };

    const handleDecrement = () => {
        setCount(count - 3);
    }

    let component = (
        <table>
            <tr>
                <th> <h2>{count}</h2></th>
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

