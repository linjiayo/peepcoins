import './home.scss';

import React, { useEffect, useState } from 'react';
import { Link } from 'react-router-dom';
import BasicTable from 'app/components/crypto_table';

import { Row, Col, Alert } from 'reactstrap';

import { useAppSelector } from 'app/config/store';
import axios from 'axios';
import CryptoTable from 'app/components/crypto_table';

export const Home = () => {
  const account = useAppSelector(state => state.authentication.account);
  const [cryptos, setCryptos] = useState([]);

  useEffect(() => {
    axios
      .get(`api/cryptos`)
      .then(res => {
        setCryptos(res.data);
      })
      .catch(error => console.log(error));
  }, []);

  return (
    <div>
      {account?.login ? (
        <div>
          <Alert color="success">You are logged in as user &quot;{account.login}&quot;.</Alert>
        </div>
      ) : (
        <div>
          <Alert color="warning">
            You don&apos;t have an account yet?&nbsp;
            <Link to="/account/register" className="alert-link">
              Register a new account
            </Link>
          </Alert>
        </div>
      )}
      <h1>Current Cryptocurrency Prices by Market Cap</h1>
      <div className="table">
        <CryptoTable cryptos={cryptos} />
      </div>
    </div>
  );
};

export default Home;
