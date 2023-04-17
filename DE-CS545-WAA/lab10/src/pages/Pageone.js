import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';

export const Pageone = () => {
    const navigate = useNavigate();
    const [ firstName, setFirstName ] = useState('');
    const [ lastName, setLastName ] = useState('');
    const [ profession, setProfession ] = useState('Programmer');

    const gotoNextPage = () => {
        navigate('/pagetwo', {state:{firstName : firstName, lastName : lastName, profession : profession}});
    }

    let page1 = (
        <form>
            <div>
                <label>
                    Firstname:<span>    </span>
                        <input 
                            type="text"
                            placeholder="Firstname"
                            value={firstName}
                            onChange={e => setFirstName(e.target.value)} />
                </label>
                <br/>
                <lable>
                    Lastname:<span>    </span>
                        <input 
                            type="text"
                            placeholder="Lastname"
                            value={lastName}
                            onChange={e => setLastName(e.target.value)} />
                </lable>
                <br/>
                <label>
                    Profession:<span>    </span>
                    <select value={profession} onChange={e=> setProfession(e.target.value)}>
                        <option value="programmer">Programmer</option>
                        <option value="manager" defaultValue >Manager</option>
                        <option value="tester">Tester</option>
                        <option value="architect">Architect</option>
                    </select>
                </label>
            </div>
            <button onClick={gotoNextPage}>Next</button>
        </form>
    );

    return page1;
}