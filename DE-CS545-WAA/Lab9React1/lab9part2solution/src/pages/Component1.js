import React from 'react';

export const Component1 = () => {

    let component_1 = (
        <table>
            <tr>
                <th><button onClick={e => alert(e.target.innerText)}>Say Hello From Button 1</button></th>
            </tr>
        </table>
    );

    return component_1;
}