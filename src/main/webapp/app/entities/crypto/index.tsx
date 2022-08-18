import React from 'react';
import { Route } from 'react-router-dom';

import ErrorBoundaryRoutes from 'app/shared/error/error-boundary-routes';

import Crypto from './crypto';
import CryptoDetail from './crypto-detail';
import CryptoUpdate from './crypto-update';
import CryptoDeleteDialog from './crypto-delete-dialog';

const CryptoRoutes = () => (
  <ErrorBoundaryRoutes>
    <Route index element={<Crypto />} />
    <Route path="new" element={<CryptoUpdate />} />
    <Route path=":id">
      <Route index element={<CryptoDetail />} />
      <Route path="edit" element={<CryptoUpdate />} />
      <Route path="delete" element={<CryptoDeleteDialog />} />
    </Route>
  </ErrorBoundaryRoutes>
);

export default CryptoRoutes;
