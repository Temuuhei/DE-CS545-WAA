import React from "react";

export const Component3 = () => {

    let component3 = (

        <table>
            <tr>
                <th>
                   <button onClick={ (e) => alert(e.target.innerText)}>Say GoodBy from button 4</button> 
                </th>
            </tr>
        </table>
    );

    return component3;
}