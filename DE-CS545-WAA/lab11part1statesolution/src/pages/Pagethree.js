import React, { useState } from 'react';

export const Pagethree = (props) => {
    const[user, setUser] = useState(props.location.state.user);

    const handleOnSubmit = () => {
        props.history.push("/pagefour", {user: {
            ...user,
            cardtype: cardtype
        }});
      }

    const [cardtype, setCardType] = useState('');

    const handleFieldChange = (e) => {
    setUser({...user, [e.target.name]: e.target.value });
    }
   
    const handleCardTypeChange = (e) => {
        setCardType(e.target.value);
    };


    let page3 = (
        <div>
            <div>
                <label>
                    Page 1:
                    <div>Firstname : {user.firstname} </div>
                    <div>Lastname : {user.lastname} </div>
                    <div>Profession : {user.profession} </div>
                </label>
                <label>
                    Page 2:
                    <div>Street : {user.street} </div>
                    <div>City : {user.city} </div>
                    <div>Zip : {user.zip} </div>
                    <div>State : {user.state} </div>
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
                                name="cardnumber"
                                onChange={handleFieldChange} />
                    </label>
                    <br/>
                    </div>
                    <div>
                    <lable>
                        Type:<span>     </span>
                        <label>
                        <input 
                            name="cardtype"
                            type="radio"
                            value="Visa"
                            checked={cardtype === "Visa"}
                            onChange={handleCardTypeChange}
                        />
                        Visa
                    </label>
                    <label>
                        <input 
                        name="cardtype"
                        type="radio"
                        value="Mastercard"
                        checked={cardtype === "Mastercard"}
                        onChange={handleCardTypeChange}
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