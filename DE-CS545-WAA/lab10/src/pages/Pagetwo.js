import React, { useState } from 'react';
import { useLocation, useNavigate } from 'react-router-dom';

export const Pagetwo = () => {
    const location = useLocation();
    const navigate = useNavigate();
    const firstname = location.state.firstName;
    const lastname = location.state.lastName;
    const profession = location.state.profession;

    const [street, setStreet] = useState("");
    const [city, setCity] = useState("");
    const [zip, setZip] = useState("");
    const [state, setState] = useState("");

    const handleOnSubmit = () => {
        navigate('/pagethree', { state : {
            firstname : firstname, lastname : lastname, profession : profession,
            street : street, city : city, zip : zip, state : state
        }});
    }

    let page2 = (
        <div>
            <div>
                <label>
                    Page 1:
                    <div>Firstname : {firstname} </div>
                    <div>Lastname : {lastname} </div>
                    <div>Profession : {profession} </div>
                </label>
            </div>
            <div>
            <form>
                <div>
                    <label>
                        Street:<span>    </span>
                            <input 
                                type="text"
                                placeholder="street"
                                value={street}
                                onChange={e => setStreet(e.target.value)} />
                    </label>
                    <br/>
                    <lable>
                        City:<span>    </span>
                            <input 
                                type="text"
                                placeholder="City"
                                value={city}
                                onChange={e => setCity(e.target.value)} />
                    </lable>
                    <br/>
                    <lable>
                        Zip:<span>    </span>
                            <input 
                                type="text"
                                placeholder="ex:52556"
                                value={zip}
                                onChange={e => setZip(e.target.value)} />
                    </lable>
                    <br/>
                    <label>
                        State:<span>    </span>
                        <select value={state} onChange={e=> setState(e.target.value)}>
                            <option value="Iowa">Iowa</option>
                            <option value="California">CAlifornia</option>
                        </select>
                    </label>
                </div>
            </form>
            <button onClick={handleOnSubmit}>Next</button>
            </div>
            
        </div>
    );
    return page2;

}