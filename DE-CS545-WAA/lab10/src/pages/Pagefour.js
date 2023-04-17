import React from 'react';
import { useLocation } from 'react-router-dom';


export const Pagefour = () => {
    const location = useLocation();
    // const navigate = useNavigate();
    const firstname = location.state.firstname;
    const lastname = location.state.lastname;
    const profession  = location.state.profession;

    const street = location.state.street;
    const city = location.state.city;
    const zip = location.state.zip;
    const state = location.state.state;

    const cardNumber = location.state.cardnumber;
    const cardType = location.state.cardType;

    let page4 = (
        <div>
            <h3>Thank you for your information !</h3>
            <div>
                <label>
                    Page 1:
                    <div> Firstname : {firstname} </div>
                    <div> Lastname : {lastname} </div>
                    <div> Profession : {profession} </div>
                </label>
                <label>
                    Page 2:
                    <div> Street : {street} </div>
                    <div> City : {city} </div>
                    <div> Zip : {zip} </div>
                    <div> State : {state} </div>
                </label>
                <label>
                    Page 3:
                    <div> Creditcard Number : {cardNumber} </div>
                    <div> Type : {cardType} </div>
                </label>
            </div>
        </div>
    )

    return page4;
}