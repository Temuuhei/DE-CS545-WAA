import React, { useState } from 'react';

export const Pageone = (props) => {

    const [user, setUser] = useState({
        firstname : "",
        lastname : "",
        profession: "Programmer",

    });

    const handleOnSubmit = () => {
        props.history.push("/pagetwo", {user : user});
    }

    const  handleFieldChange = (e) => {
        setUser({...user, [e.target.name] : e.target.value});
    }

    let page1 = (
        <form>
            <div>
                <label>
                    Firstname:<span>    </span>
                        <input 
                            name="firsname"
                            type="text"
                            placeholder="Firstname"
                            onChange={handleFieldChange} />
                </label>
                <br/>
                <lable>
                    Lastname:<span>    </span>
                        <input 
                            name="lastname"
                            type="text"
                            placeholder="Lastname"
                            onChange={handleFieldChange} />
                </lable>
                <br/>
                <label>
                    Profession:<span>    </span>
                    <select onChange={handleFieldChange} name = "profession">
                        <option value="programmer">Programmer</option>
                        <option value="manager" defaultValue >Manager</option>
                        <option value="tester">Tester</option>
                        <option value="architect">Architect</option>
                    </select>
                </label>
            </div>
            <button onClick={handleOnSubmit}>Next</button>
        </form>
    );

    return page1;
}