import './App.css';
import { BrowserRouter, Routes, Route } from 'react-router-dom';
import { BookList } from './components/BookList';
import { AddBook } from './components/AddBook';
import React, { useState } from 'react';

function App() {


  const initialList  = [
    {isbn:"1234567", title:"Harry Potter", author:"J.K Rowling", price: 19.99},
    {isbn:"12345678", title:"Harry Potter 2", author:"J.K Rowling", price: 19.99},
    {isbn:"12345679", title:"Harry Potter 3", author:"J.K Rowling", price: 19.99},
  ];

  const [booklist, setBooklist] = useState(initialList);

  const onAddBook = (book) => {
    setBooklist(booklist.concat(book));
  };

  const onRemoveBook = (isbn) => {
    setBooklist(booklist.filter((book) => book.isbn !== isbn));
  }

  return (
    <div>
      <BrowserRouter>
        <Routes>
          <Route exact path="/" element = {<BookList booklist={booklist} removeBookFunction={onRemoveBook}/>} />
          <Route exact path="addbook" element = {<AddBook addBookFunction={onAddBook}/>} />
        </Routes>
      </BrowserRouter>
    </div>
  );
}

export default App;
