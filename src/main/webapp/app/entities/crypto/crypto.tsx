import React, { useState, useEffect } from 'react';
import InfiniteScroll from 'react-infinite-scroll-component';
import { Link, useLocation, useNavigate } from 'react-router-dom';
import { Button, Table } from 'reactstrap';
import { Translate, getSortState } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { ASC, DESC, ITEMS_PER_PAGE, SORT } from 'app/shared/util/pagination.constants';
import { overridePaginationStateWithQueryParams } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { ICrypto } from 'app/shared/model/crypto.model';
import { getEntities, reset } from './crypto.reducer';

export const Crypto = () => {
  const dispatch = useAppDispatch();

  const location = useLocation();
  const navigate = useNavigate();

  const [paginationState, setPaginationState] = useState(
    overridePaginationStateWithQueryParams(getSortState(location, ITEMS_PER_PAGE, 'id'), location.search)
  );
  const [sorting, setSorting] = useState(false);

  const cryptoList = useAppSelector(state => state.crypto.entities);
  const loading = useAppSelector(state => state.crypto.loading);
  const totalItems = useAppSelector(state => state.crypto.totalItems);
  const links = useAppSelector(state => state.crypto.links);
  const entity = useAppSelector(state => state.crypto.entity);
  const updateSuccess = useAppSelector(state => state.crypto.updateSuccess);

  const getAllEntities = () => {
    dispatch(
      getEntities({
        page: paginationState.activePage - 1,
        size: paginationState.itemsPerPage,
        sort: `${paginationState.sort},${paginationState.order}`,
      })
    );
  };

  const resetAll = () => {
    dispatch(reset());
    setPaginationState({
      ...paginationState,
      activePage: 1,
    });
    dispatch(getEntities({}));
  };

  useEffect(() => {
    resetAll();
  }, []);

  useEffect(() => {
    if (updateSuccess) {
      resetAll();
    }
  }, [updateSuccess]);

  useEffect(() => {
    getAllEntities();
  }, [paginationState.activePage]);

  const handleLoadMore = () => {
    if ((window as any).pageYOffset > 0) {
      setPaginationState({
        ...paginationState,
        activePage: paginationState.activePage + 1,
      });
    }
  };

  useEffect(() => {
    if (sorting) {
      getAllEntities();
      setSorting(false);
    }
  }, [sorting]);

  const sort = p => () => {
    dispatch(reset());
    setPaginationState({
      ...paginationState,
      activePage: 1,
      order: paginationState.order === ASC ? DESC : ASC,
      sort: p,
    });
    setSorting(true);
  };

  const handleSyncList = () => {
    resetAll();
  };

  return (
    <div>
      <h2 id="crypto-heading" data-cy="CryptoHeading">
        Cryptos
        <div className="d-flex justify-content-end">
          <Button className="me-2" color="info" onClick={handleSyncList} disabled={loading}>
            <FontAwesomeIcon icon="sync" spin={loading} /> Refresh list
          </Button>
          <Link to="/crypto/new" className="btn btn-primary jh-create-entity" id="jh-create-entity" data-cy="entityCreateButton">
            <FontAwesomeIcon icon="plus" />
            &nbsp; Create a new Crypto
          </Link>
        </div>
      </h2>
      <div className="table-responsive">
        <InfiniteScroll
          dataLength={cryptoList ? cryptoList.length : 0}
          next={handleLoadMore}
          hasMore={paginationState.activePage - 1 < links.next}
          loader={<div className="loader">Loading ...</div>}
        >
          {cryptoList && cryptoList.length > 0 ? (
            <Table responsive>
              <thead>
                <tr>
                  <th className="hand" onClick={sort('id')}>
                    Id <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={sort('symbol')}>
                    Symbol <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={sort('name')}>
                    Name <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={sort('currentPrice')}>
                    Current Price <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={sort('marketCap')}>
                    Market Cap <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={sort('marketCapRank')}>
                    Market Cap Rank <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={sort('totalVolume')}>
                    Total Volume <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={sort('high24')}>
                    High 24 <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={sort('low24')}>
                    Low 24 <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={sort('priceChange24')}>
                    Price Change 24 <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={sort('priceChangePercentage24hr')}>
                    Price Change Percentage 24 Hr <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={sort('totalSupply')}>
                    Total Supply <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={sort('ath')}>
                    Ath <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={sort('athDate')}>
                    Ath Date <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={sort('atl')}>
                    Atl <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={sort('atlDate')}>
                    Atl Date <FontAwesomeIcon icon="sort" />
                  </th>
                  <th>
                    Watch List <FontAwesomeIcon icon="sort" />
                  </th>
                  <th />
                </tr>
              </thead>
              <tbody>
                {cryptoList.map((crypto, i) => (
                  <tr key={`entity-${i}`} data-cy="entityTable">
                    <td>
                      <Button tag={Link} to={`/crypto/${crypto.id}`} color="link" size="sm">
                        {crypto.id}
                      </Button>
                    </td>
                    <td>{crypto.symbol}</td>
                    <td>{crypto.name}</td>
                    <td>{crypto.currentPrice}</td>
                    <td>{crypto.marketCap}</td>
                    <td>{crypto.marketCapRank}</td>
                    <td>{crypto.totalVolume}</td>
                    <td>{crypto.high24}</td>
                    <td>{crypto.low24}</td>
                    <td>{crypto.priceChange24}</td>
                    <td>{crypto.priceChangePercentage24hr}</td>
                    <td>{crypto.totalSupply}</td>
                    <td>{crypto.ath}</td>
                    <td>{crypto.athDate}</td>
                    <td>{crypto.atl}</td>
                    <td>{crypto.atlDate}</td>
                    <td>{crypto.watchList ? <Link to={`/watch-list/${crypto.watchList.id}`}>{crypto.watchList.id}</Link> : ''}</td>
                    <td className="text-end">
                      <div className="btn-group flex-btn-group-container">
                        <Button tag={Link} to={`/crypto/${crypto.id}`} color="info" size="sm" data-cy="entityDetailsButton">
                          <FontAwesomeIcon icon="eye" /> <span className="d-none d-md-inline">View</span>
                        </Button>
                        <Button tag={Link} to={`/crypto/${crypto.id}/edit`} color="primary" size="sm" data-cy="entityEditButton">
                          <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
                        </Button>
                        <Button tag={Link} to={`/crypto/${crypto.id}/delete`} color="danger" size="sm" data-cy="entityDeleteButton">
                          <FontAwesomeIcon icon="trash" /> <span className="d-none d-md-inline">Delete</span>
                        </Button>
                      </div>
                    </td>
                  </tr>
                ))}
              </tbody>
            </Table>
          ) : (
            !loading && <div className="alert alert-warning">No Cryptos found</div>
          )}
        </InfiniteScroll>
      </div>
    </div>
  );
};

export default Crypto;
