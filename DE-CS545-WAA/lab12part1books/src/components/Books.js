import React, { useState, useEffect } from 'react';
import axios from 'axios';

export const Books = () => {
    const cleanbook = { isbn : "", author : "", title : "", price : ""};
    const [book, setBook] = useState(cleanbook);
    const [booklist, setBooklist] = useState([]);

    const [isbnerror, setIsbnerror] = useState({});
    const [authorerror, setAuthorerror] = useState({});
    const [titleerror, setTitleerror] = useState({});
    const [priceerror, setPriceerror] = useState({});

    const handleSubmit = (e) => {
        e.preventDefault();
        const isValid = formValidation();
        console.log("isValid " + isValid);
        if (book && isValid) {
            alert("Form is valid");
            addBook(book);
        }
        setBook(cleanbook);
        loadBooks();
    }

    const formValidation = () => {
        const isbnerror = {};
        const authorerror = {};
        const titleerror = {};
        const priceerror = {};
        let isValid = true;

        if (book.isbn.trim().length < 2) {
            isbnerror.isbnShort = "ISBN is too short";
            isValid = false;
        }

        if (book.isbn.trim().length > 25) {
            isbnerror.isbnLong = "ISBN is too long";
            isValid = false;
        }

        if (book.author.trim().length > 30) {
            authorerror.authorLong = "Author is too long";
            isValid = false;
        }

        if (book.author.trim().length < 2) {
            authorerror.authorShort = "Author is too short";
            isValid = false;
        }

        if (book.title.trim().length > 60) {
            titleerror.titleLong = "Title is too long";
            isValid = false;
        }

        if (book.title.trim().length < 2) {
            titleerror.titleShort = "Title is too short";
            isValid = false;
        }

        if (book.price < 0) {
            priceerror.priceNotNegative = "Price is not negative";
            isValid = false;
        }

        if (book.price > 600) {
            priceerror.priceNotExpensive = "Price is too expensive";
            isValid = false;
        }
        
        var pattern = new RegExp("^(?=(?:[^0-9]*[0-9]){10}(?:(?:[^0-9]*[0-9]){3})?$)[\\d-]+$");
        if (!pattern.test(book.isbn.trim())) {
            isbnerror.isbnNoIsbn = "Isbn is not correct";
            isValid = false;
        }

        setAuthorerror(authorerror);
        setIsbnerror(isbnerror);
        setTitleerror(titleerror);
        setPriceerror(priceerror);
        return isValid;
    };

    const loadBooks = () => {
        const books = axios.get("http://localhost:8080/books")
                        .then((response) => {
                            console.log(response.data.books);
                            setBooklist(response.data.books);
                        })
    }

    const addBook = (book) => {
        axios.post("http://localhost:8080/books", book)
            .then((response) => {
                loadBooks();
            })
    }

    const handleFieldChange = (e) => {
        setBook({...book, [e.target.name] : e.target.value });
    }

    const removeBook = (e) => {
        let url = "http://localhost:8080/books/"+e.target.value;
        axios.delete(url)
            .then((response) => {
                loadBooks();
            }) 
    };

    useEffect(() => {
        loadBooks();
      }, []);

    return (
        <div>
            <h1>Books</h1>

            <table border="1">
                <thead>
                    <tr><th>Isbn</th><th>Author</th><th>Title</th><th>Price</th></tr>
                </thead>
                <tbody>
                    {booklist.map(book => (
                        <tr key = {book.isbn}>
                            <td>{book.isbn}</td>
                            <td>{book.author}</td>
                            <td>{book.title}</td>
                            <td>{book.price}</td>
                            <td><button onClick={removeBook} value = {book.isbn}>Remove</button></td>
                        </tr>
                    ))}
                </tbody>
            </table>
            <button onClick={loadBooks}>Load Lists</button>
            <h2>Add a new Book</h2>
            <form onSubmit={handleSubmit}>
                <div>
                    ISBN : 
                    <input
                        type="text"
                        placeholder='978-1-45678-123-4'
                        name="isbn"
                        value = {book.isbn}
                        onChange={handleFieldChange}
                    />
                    {Object.keys(isbnerror).map((key) => {
                        return <div style={{color : "red"}}>{isbnerror[key]}</div>
                    })}
                </div>
                <div>
                    Author : 
                    <input
                        type="text"
                        placeholder='J.K Rowling'
                        name="author"
                        value = {book.author}
                        onChange={handleFieldChange}
                    />
                    {Object.keys(authorerror).map((key) => {
                        return <div style={{color : "red"}}>{authorerror[key]}</div>
                    })}
                </div>
                <div>
                    Title : 
                    <input
                        type="text"
                        placeholder='Harry Potter'
                        name="title"
                        value = {book.title}
                        onChange={handleFieldChange}
                    />
                    {Object.keys(titleerror).map((key) => {
                        return <div style={{color : "red"}}>{titleerror[key]}</div>
                    })}
                </div>
                <div>
                    Price : 
                    <input
                        type="number"
                        name="price"
                        value = {book.price}
                        onChange={handleFieldChange}
                    />
                     {Object.keys(priceerror).map((key) => {
                        return <div style={{color : "red"}}>{priceerror[key]}</div>
                    })}
                </div>
                <button type = "submit" className="button is-primary is-rounded is-pulled-right"> Add Book</button>
            </form>
        </div>
    );
};