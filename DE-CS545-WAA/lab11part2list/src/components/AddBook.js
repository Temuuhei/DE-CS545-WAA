import React, { useState } from 'react';
import { useLocation, useNavigate } from 'react-router-dom';


export const AddBook = ({addBookFunction}) =>{
    const location = useLocation();
    const navigate = useNavigate();
    const cleanbook = {isbn:"", title: "", author:"", price: 0};
    const [book, setBook] = useState(cleanbook);

    const handleSubmit = (e) => {
        e.preventDefault();
        addBookFunction(book);
        navigate('/');
    }

    const handleFieldChange = (e) => {
        setBook({...book, [e.target.name] : e.target.value});
    }


    let pageadd = (
        <div>
            <h2>Add a new book</h2>
            <form onSubmit={handleSubmit}>
                <div>
                ISBN
                  <input
                    type="text"
                    placeholder="ISBN"
                    name="isbn"
                    value={book.isbn}
                    onChange={handleFieldChange} />
                </div>
                <div>
                Title
                  <input
                    type="text"
                    placeholder="Title"
                    name="title"
                    value={book.title}
                    onChange={handleFieldChange} />
                </div>
                <div>
                Author
                  <input
                    type="text"
                    placeholder="Author"
                    name="author"
                    value={book.author}
                    onChange={handleFieldChange} />
                </div>
                <div>
                Price
                  <input
                    type="num"
                    placeholder="0.00"
                    name="price"
                    value={book.price}
                    onChange={handleFieldChange} />
                </div>
                <button type="submit">Add Book</button>
            </form>
        </div>
    );

    return pageadd;
}