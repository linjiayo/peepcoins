import React, { useState, useEffect } from 'react';
import { Link, useNavigate, useParams } from 'react-router-dom';
import { Button, Row, Col, FormText } from 'reactstrap';
import { isNumber, ValidatedField, ValidatedForm } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { IWatchList } from 'app/shared/model/watch-list.model';
import { getEntities as getWatchLists } from 'app/entities/watch-list/watch-list.reducer';
import { ICrypto } from 'app/shared/model/crypto.model';
import { getEntity, updateEntity, createEntity, reset } from './crypto.reducer';

export const CryptoUpdate = () => {
  const dispatch = useAppDispatch();

  const navigate = useNavigate();

  const { id } = useParams<'id'>();
  const isNew = id === undefined;

  const watchLists = useAppSelector(state => state.watchList.entities);
  const cryptoEntity = useAppSelector(state => state.crypto.entity);
  const loading = useAppSelector(state => state.crypto.loading);
  const updating = useAppSelector(state => state.crypto.updating);
  const updateSuccess = useAppSelector(state => state.crypto.updateSuccess);

  const handleClose = () => {
    navigate('/crypto');
  };

  useEffect(() => {
    if (!isNew) {
      dispatch(getEntity(id));
    }

    dispatch(getWatchLists({}));
  }, []);

  useEffect(() => {
    if (updateSuccess) {
      handleClose();
    }
  }, [updateSuccess]);

  const saveEntity = values => {
    const entity = {
      ...cryptoEntity,
      ...values,
      watchList: watchLists.find(it => it.id.toString() === values.watchList.toString()),
    };

    if (isNew) {
      dispatch(createEntity(entity));
    } else {
      dispatch(updateEntity(entity));
    }
  };

  const defaultValues = () =>
    isNew
      ? {}
      : {
          ...cryptoEntity,
          watchList: cryptoEntity?.watchList?.id,
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="peepcoinsApp.crypto.home.createOrEditLabel" data-cy="CryptoCreateUpdateHeading">
            Create or edit a Crypto
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <ValidatedForm defaultValues={defaultValues()} onSubmit={saveEntity}>
              {!isNew ? <ValidatedField name="id" required readOnly id="crypto-id" label="Id" validate={{ required: true }} /> : null}
              <ValidatedField label="Symbol" id="crypto-symbol" name="symbol" data-cy="symbol" type="text" />
              <ValidatedField
                label="Name"
                id="crypto-name"
                name="name"
                data-cy="name"
                type="text"
                validate={{
                  required: { value: true, message: 'This field is required.' },
                }}
              />
              <ValidatedField label="Current Price" id="crypto-currentPrice" name="currentPrice" data-cy="currentPrice" type="text" />
              <ValidatedField label="Market Cap" id="crypto-marketCap" name="marketCap" data-cy="marketCap" type="text" />
              <ValidatedField label="Market Cap Rank" id="crypto-marketCapRank" name="marketCapRank" data-cy="marketCapRank" type="text" />
              <ValidatedField label="Total Volume" id="crypto-totalVolume" name="totalVolume" data-cy="totalVolume" type="text" />
              <ValidatedField label="High 24" id="crypto-high24" name="high24" data-cy="high24" type="text" />
              <ValidatedField label="Low 24" id="crypto-low24" name="low24" data-cy="low24" type="text" />
              <ValidatedField label="Price Change 24" id="crypto-priceChange24" name="priceChange24" data-cy="priceChange24" type="text" />
              <ValidatedField
                label="Price Change Percentage 24 Hr"
                id="crypto-priceChangePercentage24hr"
                name="priceChangePercentage24hr"
                data-cy="priceChangePercentage24hr"
                type="text"
              />
              <ValidatedField label="Total Supply" id="crypto-totalSupply" name="totalSupply" data-cy="totalSupply" type="text" />
              <ValidatedField label="Ath" id="crypto-ath" name="ath" data-cy="ath" type="text" />
              <ValidatedField label="Ath Date" id="crypto-athDate" name="athDate" data-cy="athDate" type="text" />
              <ValidatedField label="Atl" id="crypto-atl" name="atl" data-cy="atl" type="text" />
              <ValidatedField label="Atl Date" id="crypto-atlDate" name="atlDate" data-cy="atlDate" type="text" />
              <ValidatedField id="crypto-watchList" name="watchList" data-cy="watchList" label="Watch List" type="select">
                <option value="" key="0" />
                {watchLists
                  ? watchLists.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.id}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/crypto" replace color="info">
                <FontAwesomeIcon icon="arrow-left" />
                &nbsp;
                <span className="d-none d-md-inline">Back</span>
              </Button>
              &nbsp;
              <Button color="primary" id="save-entity" data-cy="entityCreateSaveButton" type="submit" disabled={updating}>
                <FontAwesomeIcon icon="save" />
                &nbsp; Save
              </Button>
            </ValidatedForm>
          )}
        </Col>
      </Row>
    </div>
  );
};

export default CryptoUpdate;
