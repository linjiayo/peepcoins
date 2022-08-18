import React from 'react';
import { Route } from 'react-router-dom';

import ErrorBoundaryRoutes from 'app/shared/error/error-boundary-routes';

import CryptoUser from './crypto-user';
import CryptoUserDetail from './crypto-user-detail';
import CryptoUserUpdate from './crypto-user-update';
import CryptoUserDeleteDialog from './crypto-user-delete-dialog';

const CryptoUserRoutes = () => (
  <ErrorBoundaryRoutes>
    <Route index element={<CryptoUser />} />
    <Route path="new" element={<CryptoUserUpdate />} />
    <Route path=":id">
      <Route index element={<CryptoUserDetail />} />
      <Route path="edit" element={<CryptoUserUpdate />} />
      <Route path="delete" element={<CryptoUserDeleteDialog />} />
    </Route>
  </ErrorBoundaryRoutes>
);

export default CryptoUserRoutes;
