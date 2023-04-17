import React from 'react';

export const Component2 = () => {
    let component2 = (
        <table>
            <tr>
                <th>
                    <button onClick={e => alert(e.target.innerText)}> Say Hi From button 3</button>
                </th>
            </tr>
        </table>
    );
    return component2;
}