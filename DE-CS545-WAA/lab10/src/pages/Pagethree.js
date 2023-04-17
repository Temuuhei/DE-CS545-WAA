import React, { useState } from 'react';
import { useLocation, useNavigate } from 'react-router-dom';

export const Pagethree = () => {
    const location = useLocation();
    const navigate = useNavigate();
    // from page pagetwo
    const firstname = location.state.firstname;
    const lastname = location.state.lastname;
    const profession = location.state.profession;
    const street = location.state.street;
    const city = location.state.city;
    const zip = location.state.zip;
    const state = location.state.state;

    const [cardNumber, setCardNumber] = useState("");
    const [cardType, setCardType] = useState("");

    const handleOnSubmit = () => {
        navigate('/pagefour', {
            state : {
                firstname : firstname, lastname : lastname, profession : profession,
                street : street, city : city, zip : zip, state : state,
                cardnumber : cardNumber, cardType : cardType
            }
        });
    }

    let page3 = (
        <div>
            <div>
                <label>
                    Page 1:
                    <div>Firstname : {firstname} </div>
                    <div>Lastname : {lastname} </div>
                    <div>Profession : {profession} </div>
                </label>
                <label>
                    Page 2:
                    <div>Street : {street} </div>
                    <div>City : {city} </div>
                    <div>Zip : {zip} </div>
                    <div>State : {state} </div>
                </label>
            </div>
            <form>
                <div>
                    <div>
                    <label>
                        Creditcard number :<span>    </span>
                            <input 
                                type="text"
                                placeholder="123456789"
                                value={cardNumber}
                                onChange={e => setCardNumber(e.target.value)} />
                    </label>
                    <br/>
                    </div>
                    <div>
                    <lable>
                        Type:<span>     </span>
                        <label>
    <input 
        type="radio"
        value="Visa"
        checked={cardType === "Visa" }
        onChange={ e=> setCardType(e.target.value)}
    />
    Visa
</label>
<label>
    <input 
        type="radio"
        value="Mastercard"
        checked={cardType === "Mastercard" }
        onChange={ e=> setCardType(e.target.value)}
    />
    Mastercard
</label>

                    </lable>
                    </div>
                </div>
            </form>
            <br/>
            <button onClick={handleOnSubmit}>Next</button>
        </div>
    );
    return page3;

}