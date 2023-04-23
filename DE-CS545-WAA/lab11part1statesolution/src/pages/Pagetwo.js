import React, { useState } from 'react';

export const Pagetwo = (props) => {

    const[user, setUser] = useState(props.location.state.user);

    const handleOnSubmit = () => {
        props.history.push("/pagethree", {user :user});
      }

    const handleFieldChange = (e) => {
    setUser({...user, [e.target.name]: e.target.value });
    }
   

    let page2 = (
        <div>
            <div>
                <label>
                    Page 1:
                    <div>Firstname : {user.firstname} </div>
                    <div>Lastname : {user.lastname} </div>
                    <div>Profession : {user.profession} </div>
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
                                onChange={handleFieldChange} />
                    </label>
                    <br/>
                    <lable>
                        City:<span>    </span>
                            <input 
                                type="text"
                                placeholder="City"
                                name="city"
                                onChange={handleFieldChange} />
                    </lable>
                    <br/>
                    <lable>
                        Zip:<span>    </span>
                            <input 
                                type="text"
                                placeholder="ex:52556"
                                name="zip"
                                onChange={handleFieldChange} />
                    </lable>
                    <br/>
                    <label>
                        State:<span>    </span>
                        <select name="state" onChange={handleFieldChange}>
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