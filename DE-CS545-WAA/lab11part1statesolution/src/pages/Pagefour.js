import React from 'react';


export const Pagefour = (props) => {

    const user = props.location.state.user;
    
    let page4 = (
        <div>
            <h3>Thank you for your information !</h3>
            <div>
                <label>
                    Page 1:
                    <div> Firstname : {user.firstname} </div>
                    <div> Lastname : {user.lastname} </div>
                    <div> Profession : {user.profession} </div>
                </label>
                <label>
                    Page 2:
                    <div> Street : {user.street} </div>
                    <div> City : {user.city} </div>
                    <div> Zip : {user.zip} </div>
                    <div> State : {user.state} </div>
                </label>
                <label>
                    Page 3:
                    <div> Creditcard Number : {user.cardnumber} </div>
                    <div> Type : {user.cardtype} </div>
                </label>
            </div>
        </div>
    )

    return page4;
}