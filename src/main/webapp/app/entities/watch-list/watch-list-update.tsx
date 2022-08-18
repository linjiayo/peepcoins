import React, { useState, useEffect } from 'react';
import { Link, useNavigate, useParams } from 'react-router-dom';
import { Button, Row, Col, FormText } from 'reactstrap';
import { isNumber, ValidatedField, ValidatedForm } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { ICryptoUser } from 'app/shared/model/crypto-user.model';
import { getEntities as getCryptoUsers } from 'app/entities/crypto-user/crypto-user.reducer';
import { IWatchList } from 'app/shared/model/watch-list.model';
import { getEntity, updateEntity, createEntity, reset } from './watch-list.reducer';

export const WatchListUpdate = () => {
  const dispatch = useAppDispatch();

  const navigate = useNavigate();

  const { id } = useParams<'id'>();
  const isNew = id === undefined;

  const cryptoUsers = useAppSelector(state => state.cryptoUser.entities);
  const watchListEntity = useAppSelector(state => state.watchList.entity);
  const loading = useAppSelector(state => state.watchList.loading);
  const updating = useAppSelector(state => state.watchList.updating);
  const updateSuccess = useAppSelector(state => state.watchList.updateSuccess);

  const handleClose = () => {
    navigate('/watch-list');
  };

  useEffect(() => {
    if (isNew) {
      dispatch(reset());
    } else {
      dispatch(getEntity(id));
    }

    dispatch(getCryptoUsers({}));
  }, []);

  useEffect(() => {
    if (updateSuccess) {
      handleClose();
    }
  }, [updateSuccess]);

  const saveEntity = values => {
    const entity = {
      ...watchListEntity,
      ...values,
      cryptoUser: cryptoUsers.find(it => it.id.toString() === values.cryptoUser.toString()),
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
          ...watchListEntity,
          cryptoUser: watchListEntity?.cryptoUser?.id,
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="peepcoinsApp.watchList.home.createOrEditLabel" data-cy="WatchListCreateUpdateHeading">
            Create or edit a Watch List
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <ValidatedForm defaultValues={defaultValues()} onSubmit={saveEntity}>
              {!isNew ? <ValidatedField name="id" required readOnly id="watch-list-id" label="Id" validate={{ required: true }} /> : null}
              <ValidatedField id="watch-list-cryptoUser" name="cryptoUser" data-cy="cryptoUser" label="Crypto User" type="select">
                <option value="" key="0" />
                {cryptoUsers
                  ? cryptoUsers.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.id}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/watch-list" replace color="info">
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

export default WatchListUpdate;
