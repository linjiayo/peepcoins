import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import {} from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './crypto.reducer';

export const CryptoDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const cryptoEntity = useAppSelector(state => state.crypto.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="cryptoDetailsHeading">Crypto</h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">Id</span>
          </dt>
          <dd>{cryptoEntity.id}</dd>
          <dt>
            <span id="symbol">Symbol</span>
          </dt>
          <dd>{cryptoEntity.symbol}</dd>
          <dt>
            <span id="name">Name</span>
          </dt>
          <dd>{cryptoEntity.name}</dd>
          <dt>
            <span id="currentPrice">Current Price</span>
          </dt>
          <dd>{cryptoEntity.currentPrice}</dd>
          <dt>
            <span id="marketCap">Market Cap</span>
          </dt>
          <dd>{cryptoEntity.marketCap}</dd>
          <dt>
            <span id="marketCapRank">Market Cap Rank</span>
          </dt>
          <dd>{cryptoEntity.marketCapRank}</dd>
          <dt>
            <span id="totalVolume">Total Volume</span>
          </dt>
          <dd>{cryptoEntity.totalVolume}</dd>
          <dt>
            <span id="high24">High 24</span>
          </dt>
          <dd>{cryptoEntity.high24}</dd>
          <dt>
            <span id="low24">Low 24</span>
          </dt>
          <dd>{cryptoEntity.low24}</dd>
          <dt>
            <span id="priceChange24">Price Change 24</span>
          </dt>
          <dd>{cryptoEntity.priceChange24}</dd>
          <dt>
            <span id="priceChangePercentage24hr">Price Change Percentage 24 Hr</span>
          </dt>
          <dd>{cryptoEntity.priceChangePercentage24hr}</dd>
          <dt>
            <span id="totalSupply">Total Supply</span>
          </dt>
          <dd>{cryptoEntity.totalSupply}</dd>
          <dt>
            <span id="ath">Ath</span>
          </dt>
          <dd>{cryptoEntity.ath}</dd>
          <dt>
            <span id="athDate">Ath Date</span>
          </dt>
          <dd>{cryptoEntity.athDate}</dd>
          <dt>
            <span id="atl">Atl</span>
          </dt>
          <dd>{cryptoEntity.atl}</dd>
          <dt>
            <span id="atlDate">Atl Date</span>
          </dt>
          <dd>{cryptoEntity.atlDate}</dd>
          <dt>Watch List</dt>
          <dd>{cryptoEntity.watchList ? cryptoEntity.watchList.id : ''}</dd>
        </dl>
        <Button tag={Link} to="/crypto" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Back</span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/crypto/${cryptoEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
        </Button>
      </Col>
    </Row>
  );
};

export default CryptoDetail;
