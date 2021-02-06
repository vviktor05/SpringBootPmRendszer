import React from 'react';
import ReactDOM from 'react-dom';
import { BrowserRouter } from "react-router-dom";
import 'bootstrap/dist/css/bootstrap.min.css';
import App from './App';
import './index.css';
import GlobalStateProvider from './store/GlobalStateProvider';

ReactDOM.render(
  <React.Fragment>
    <GlobalStateProvider>
      <BrowserRouter>
        <App />
      </BrowserRouter>
    </GlobalStateProvider>
  </React.Fragment>,
  document.getElementById('root')
);