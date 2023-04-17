import React from 'react';

export const Component = () => {


    let component = (
        <table>
            <tr>
                <th>
                <button onClick={e => alert(e.target.innerText)}>Say Welcome from Button 1</button>
                </th>
            </tr>
        </table>
        
    );

    return component;

}